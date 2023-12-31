package org.crayne.hrm.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jcabi.aspects.Cacheable;
import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.level.LocalLevelProperties;
import org.crayne.hrm.api.level.data.color.ColorProperty;
import org.crayne.hrm.api.level.data.object.type.LazyLevelObject;
import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.type.*;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
import org.crayne.hrm.api.savefile.encrypt.LevelDataEncryption;
import org.crayne.hrm.repository.commit.HRMCommit;
import org.crayne.hrm.repository.commit.HRMCommitHistory;
import org.crayne.hrm.repository.commit.HRMCommitInfo;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class HRMRepository {

    @Nullable
    private LocalLevel currentLevelProgress;

    @NotNull
    private final File commitDirectory;

    @NotNull
    private final File commitHistoryFile;

    @NotNull
    private final HRMRepositoryInfo repositoryInfo;

    @NotNull
    private final File directory;

    @NotNull
    private static final String CURRENT_LEVEL_XML = "hrm-current-level.xml";

    @NotNull
    private static final String HRM_COMMITS_DIRECTORY = ".hrmc";

    @NotNull
    private static final String HRM_COMMITS_FILE = "hrm-commits.json";

    @NotNull
    private static final String HRM_REPO_FILE = "hrm-repo.json";

    @NotNull
    public static final Gson HRM_REPOSITORY_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();

    public HRMRepository(@NotNull final HRMRepositoryInfo repositoryInfo, @NotNull final File directory) {
        this.directory = directory;
        this.repositoryInfo = repositoryInfo;
        this.commitDirectory = new File(this.directory, HRM_COMMITS_DIRECTORY);
        this.commitHistoryFile = new File(this.directory, HRM_COMMITS_FILE);
        this.currentLevelProgress = null;
    }

    public static boolean isHRMRepository(@NotNull final File directory) {
        final File commitDirectory = new File(directory, HRM_COMMITS_DIRECTORY);
        final File commitHistoryFile = new File(directory, HRM_COMMITS_FILE);

        final File hrmCurrentLevelXML = new File(directory, CURRENT_LEVEL_XML);
        return directory.isDirectory() && hrmCurrentLevelXML.isFile() && commitDirectory.isDirectory() && commitHistoryFile.isFile();
    }

    @NotNull
    public static HRMRepository readRepository(@NotNull final File directory) {
        final File repositoryInfoFile = new File(directory, HRM_REPO_FILE);
        try {
            final HRMRepositoryInfo repositoryInfo = HRMRepositoryInfo.load(repositoryInfoFile);
            return new HRMRepository(repositoryInfo, directory);
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
    }

    private boolean initFileStructure() {
        final File hrmCurrentLevelXML = new File(directory, CURRENT_LEVEL_XML);
        final File repositoryInfoFile = new File(directory, HRM_REPO_FILE);
        if (directory.isDirectory() && hrmCurrentLevelXML.isFile() && repositoryInfoFile.isFile() && commitDirectory.isDirectory() && commitHistoryFile.isFile()) return true;

        final boolean createdRepoInfo;
        try {
            repositoryInfo.save(repositoryInfoFile);
        } catch (final IOException e) {
            throw new HRMRepositoryException("Could not create repository info file");
        }

        final boolean createdMainDirectory = directory.isDirectory() || directory.mkdirs();
        if (!createdMainDirectory) throw new HRMRepositoryException("Could not create hrm repository directory");

        final boolean createdCommitDirectory = commitDirectory.isDirectory() || commitDirectory.mkdirs();
        if (!createdCommitDirectory) throw new HRMRepositoryException("Could not create hrm repository commit directory");

        try {
            final boolean createdCommitHistory = commitHistoryFile.isFile() || commitHistoryFile.createNewFile();
            if (!createdCommitHistory) throw new HRMRepositoryException("Could not create commit history json file");
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
        final File appdataDirectory = repositoryInfo.appdataDirectory();
        if (appdataDirectory != null && !appdataDirectory.isDirectory()) throw new HRMRepositoryException("Appdata directory was not found, check if the file path is correct");
        return false;
    }

    @NotNull
    public Optional<LevelIDCollection> pull(@NotNull final Map<HRMCommitInfo, HRMCommit> commits, @NotNull final DefaultElement clonedLevel) {
        final LocalLevel decryptedClonedLevel = LevelDataDecryption.decryptLevel(clonedLevel);

        final LocalLevel settingsOnly = decryptedClonedLevel.createSettingsOnlyLevel();
        HRMCommit.mergeCommits(commits.values(), settingsOnly).applyChanges(settingsOnly);

        final LevelIDCollection collisions = findCollisions(settingsOnly);
        if (collisions.anyPresent()) return Optional.of(collisions);

        assert clone(commits, decryptedClonedLevel, clonedLevel, true);
        return Optional.empty();
    }

    private boolean clone(@NotNull final Map<HRMCommitInfo, HRMCommit> commits, @NotNull final LocalLevel decryptedClonedLevel, @NotNull final DefaultElement clonedLevel, final boolean pull) {
        if (!pull && initFileStructure()) return false;
        try {
            final HRMCommitHistory history = pull ? HRMCommitHistory.readJson(commitHistoryFile) : new HRMCommitHistory();

            for (@NotNull final HRMCommitInfo commitInfo : commits.keySet()) {
                final HRMCommit commit = commits.get(commitInfo);

                writeCommit(commitInfo, commit);
                history.commitInfos().add(commitInfo);
            }
            history.writeJson(commitHistoryFile);
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
        syncRepositoryLevelData(clonedLevel);
        updateIngameProgress(decryptedClonedLevel);
        return true;
    }

    public boolean clone(@NotNull final Map<HRMCommitInfo, HRMCommit> commits, @NotNull final DefaultElement clonedLevel) {
        return clone(commits, LevelDataDecryption.decryptLevel(clonedLevel), clonedLevel, false);
    }

    @NotNull
    @Cacheable
    private LevelIDCollection findCollisions(@NotNull final LocalLevel clonedLevel) {
        final LocalLevel currentLevel = currentLevelProgress().orElseThrow(HRMRepositoryException::new);
        return findCollisions(clonedLevel, currentLevel);
    }

    private void fixCollisions(@NotNull final LocalLevel clonedLevel) {
        final LocalLevel currentLevel = currentLevelProgress().orElseThrow(HRMRepositoryException::new);
        fixLevelIDCollisions(clonedLevel, currentLevel);
    }

    @NotNull
    private static LevelIDCollection findCollisions(@NotNull final LocalLevel clonedLevel, @NotNull final LocalLevel currentLevel) {
        return findLevelIDCollisions(clonedLevel, currentLevel);
    }

    @NotNull
    @Cacheable
    private static LevelIDCollection findLevelIDCollisions(@NotNull final LocalLevel clonedLevel, @NotNull final LocalLevel currentLevel) {
        final LevelIDCollection clonedLevelIDs  = extractAllLevelIDs(clonedLevel);
        final LevelIDCollection currentLevelIDs = extractAllLevelIDs(currentLevel);
        return findLevelIDCollisions(clonedLevelIDs, currentLevelIDs);
    }

    private static void fixLevelIDCollisions(@NotNull final LocalLevel clonedLevel, @NotNull final LocalLevel currentLevel) {
        final LevelIDCollection clonedLevelIDs  = extractAllLevelIDs(clonedLevel);
        final LevelIDCollection currentLevelIDs = extractAllLevelIDs(currentLevel);
        final LevelIDCollection collisions = findLevelIDCollisions(clonedLevelIDs, currentLevelIDs);

        changeLevelIDs(currentLevel,
                fixIDCollisions(collisions.itemIDs(), clonedLevelIDs.itemIDs(), currentLevelIDs.itemIDs()),
                fixIDCollisions(collisions.blockIDs(), clonedLevelIDs.blockIDs(), currentLevelIDs.blockIDs()),
                fixIDCollisions(collisions.groupIDs(), clonedLevelIDs.groupIDs(), currentLevelIDs.groupIDs()),
                fixIDCollisions(collisions.linkIDs(), clonedLevelIDs.linkIDs(), currentLevelIDs.linkIDs()),
                fixIDCollisions(collisions.colorIDs(), clonedLevelIDs.colorIDs(), currentLevelIDs.colorIDs())
        );
    }

    @NotNull
    private static Map<Integer, Integer> fixIDCollisions(@NotNull final Set<Integer> collidedIDs,
                                                         @NotNull final Set<Integer> clonedLevelIDs,
                                                         @NotNull final Set<Integer> currentLevelIDs) {
        final Map<Integer, Integer> idFixes = new HashMap<>();
        for (final int collidedID : collidedIDs) {
            final int nextFreeID = nextFree(clonedLevelIDs, currentLevelIDs);
            idFixes.put(collidedID, nextFreeID);
        }
        return idFixes;
    }

    private static int nextFree(@NotNull final Set<Integer> clonedLevelIDs, @NotNull final Set<Integer> currentLevelIDs) {
        for (int id = 1; id < Short.MAX_VALUE; id++) {
            if (!clonedLevelIDs.contains(id) && !currentLevelIDs.contains(id)) return id;
        }
        return 0;
    }

    @NotNull
    @Cacheable
    private static LevelIDCollection findLevelIDCollisions(@NotNull final LevelIDCollection clonedLevelIDs, @NotNull final LevelIDCollection currentLevelIDs) {
        return new LevelIDCollection(
                findIDCollisions(clonedLevelIDs.itemIDs, currentLevelIDs.itemIDs),
                findIDCollisions(clonedLevelIDs.blockIDs, currentLevelIDs.blockIDs),
                findIDCollisions(clonedLevelIDs.groupIDs, currentLevelIDs.groupIDs),
                findIDCollisions(clonedLevelIDs.linkIDs, currentLevelIDs.linkIDs),
                findIDCollisions(clonedLevelIDs.colorIDs, currentLevelIDs.colorIDs)
        );
    }

    @NotNull
    private static <T> Set<T> findIDCollisions(@NotNull final Set<T> set1, @NotNull final Set<T> set2) {
        final Set<T> finalSet = new HashSet<>(set1);
        finalSet.retainAll(set2);
        return finalSet;
    }

    public record LevelIDCollection(@NotNull Set<Integer> itemIDs, @NotNull Set<Integer> blockIDs,
                                     @NotNull Set<Integer> groupIDs, @NotNull Set<Integer> linkIDs,
                                     @NotNull Set<Integer> colorIDs) {

        public boolean anyPresent() {
            return !itemIDs.isEmpty() || !blockIDs.isEmpty() || !groupIDs.isEmpty()
                    || !linkIDs.isEmpty() || !colorIDs.isEmpty();
        }

    }

    @NotNull
    @Cacheable
    private static LevelIDCollection extractAllLevelIDs(@NotNull final LocalLevel level) {
        final Set<Integer> itemIDs  = new HashSet<>();
        final Set<Integer> blockIDs = new HashSet<>();
        final Set<Integer> groupIDs = new HashSet<>();
        final Set<Integer> linkIDs  = new HashSet<>();
        final Set<Integer> colorIDs = new HashSet<>();

        for (@NotNull final LazyLevelObject lazyLevelObject : level.data().levelObjects()) {
            final LevelObject object = lazyLevelObject.levelObject();
            if (object instanceof final ItemTrigger itemTrigger)       addItemID(itemTrigger, itemIDs);
            if (object instanceof final BlockTrigger blockTrigger)     addBlockID(blockTrigger, blockIDs);
            if (object instanceof final BiBlockTrigger blockTrigger)   addSecondBlockID(blockTrigger, blockIDs);
            if (object instanceof final TargetTrigger targetTrigger)   addTargetGroupID(targetTrigger, groupIDs);
            if (object instanceof final BiTargetTrigger targetTrigger) addSecondGroupID(targetTrigger, groupIDs);

            groupIDs.addAll(object.groupIDs());
            addLinkedID(object, linkIDs);
        }
        level.data().levelSettings().levelColorProperties().forEach(color -> addColorChannelID(color, colorIDs));
        return new LevelIDCollection(itemIDs, blockIDs, groupIDs, linkIDs, colorIDs);
    }

    private static void changeLevelIDs(@NotNull final LocalLevel level,
                                       @NotNull final Map<Integer, Integer> itemIDMap,
                                       @NotNull final Map<Integer, Integer> blockIDMap,
                                       @NotNull final Map<Integer, Integer> groupIDMap,
                                       @NotNull final Map<Integer, Integer> linkIDMap,
                                       @NotNull final Map<Integer, Integer> colorIDMap) {

        for (@NotNull final LazyLevelObject lazyLevelObject : level.data().levelObjects()) {
            final LevelObject object = lazyLevelObject.levelObject();
            if (object instanceof final ItemTrigger itemTrigger)       changeItemID(itemTrigger, itemIDMap);
            if (object instanceof final BlockTrigger blockTrigger)     changeBlockID(blockTrigger, blockIDMap);
            if (object instanceof final BiBlockTrigger blockTrigger)   changeSecondBlockID(blockTrigger, blockIDMap);
            if (object instanceof final TargetTrigger targetTrigger)   changeGroupID(targetTrigger, groupIDMap);
            if (object instanceof final BiTargetTrigger targetTrigger) changeSecondGroupID(targetTrigger, groupIDMap);

            for (final int groupID : object.groupIDs()) {
                if (!groupIDMap.containsKey(groupID)) continue;

                object.groupIDs().remove((Integer) groupID);
                object.groupIDs().add(groupIDMap.get(groupID));
            }
            changeLinkedID(object, linkIDMap);
        }
        level.data().levelSettings().levelColorProperties().forEach(color -> changeColorChannelID(color, colorIDMap));
    }

    private static void changeID(@NotNull final Consumer<Integer> idConsumer, @NotNull final Supplier<Integer> idGetter,
                                 @NotNull final Map<Integer, Integer> itemIDMap) {
        if (itemIDMap.containsKey(idGetter.get())) idConsumer.accept(itemIDMap.get(idGetter.get()));
    }

    private static void addID(@NotNull final Supplier<Integer> idGetter, @NotNull final Collection<Integer> addTo) {
        final int id = idGetter.get();
        if (id != 0) addTo.add(id);
    }

    private static void changeItemID(@NotNull final ItemTrigger itemTrigger, @NotNull final Map<Integer, Integer> itemIDMap) {
        changeID(itemTrigger::itemID, itemTrigger::itemID, itemIDMap);
    }

    private static void addItemID(@NotNull final ItemTrigger itemTrigger, @NotNull final Collection<Integer> itemIDs) {
        addID(itemTrigger::itemID, itemIDs);
    }

    private static void changeBlockID(@NotNull final BlockTrigger blockTrigger, @NotNull final Map<Integer, Integer> blockIDMap) {
        changeID(blockTrigger::blockID, blockTrigger::blockID, blockIDMap);
    }

    private static void addBlockID(@NotNull final BlockTrigger blockTrigger, @NotNull final Collection<Integer> blockIDs) {
        addID(blockTrigger::blockID, blockIDs);
    }

    private static void changeSecondBlockID(@NotNull final BiBlockTrigger blockTrigger, @NotNull final Map<Integer, Integer> blockIDMap) {
        changeID(blockTrigger::secondBlockID, blockTrigger::secondBlockID, blockIDMap);
    }

    private static void addSecondBlockID(@NotNull final BiBlockTrigger blockTrigger, @NotNull final Collection<Integer> blockIDs) {
        addID(blockTrigger::secondBlockID, blockIDs);
    }

    private static void changeGroupID(@NotNull final TargetTrigger targetTrigger, @NotNull final Map<Integer, Integer> groupIDMap) {
        changeID(targetTrigger::targetGroupID, targetTrigger::targetGroupID, groupIDMap);
    }

    private static void addTargetGroupID(@NotNull final TargetTrigger targetTrigger, @NotNull final Collection<Integer> groupIDs) {
        addID(targetTrigger::targetGroupID, groupIDs);
    }

    private static void changeSecondGroupID(@NotNull final BiTargetTrigger targetTrigger, @NotNull final Map<Integer, Integer> groupIDMap) {
        changeID(targetTrigger::secondGroupID, targetTrigger::secondGroupID, groupIDMap);
    }

    private static void addSecondGroupID(@NotNull final BiTargetTrigger targetTrigger, @NotNull final Collection<Integer> groupIDs) {
        addID(targetTrigger::secondGroupID, groupIDs);
    }

    private static void changeLinkedID(@NotNull final LevelObject object, @NotNull final Map<Integer, Integer> linkedIDMap) {
        changeID(object::linkedGroupID, object::linkedGroupID, linkedIDMap);
    }

    private static void addLinkedID(@NotNull final LevelObject object, @NotNull final Collection<Integer> linkedIDs) {
        addID(object::linkedGroupID, linkedIDs);
    }

    private static void changeColorChannelID(@NotNull final ColorProperty colorProperty, @NotNull final Map<Integer, Integer> linkedIDMap) {
        changeID(colorProperty::channelIndex, colorProperty::channelIndex, linkedIDMap);
    }

    private static void addColorChannelID(@NotNull final ColorProperty colorProperty, @NotNull final Collection<Integer> linkedIDs) {
        addID(colorProperty::channelIndex, linkedIDs);
    }

    private static void removeZeroes(@NotNull final Set<Integer> integers) {
        integers.remove(0);
    }

    public boolean init() {
        if (initFileStructure()) return false;

        syncRepositoryLevelData();
        updateIngameProgress(currentLevelProgressIngame()); // creates default level if it wasnt there before
        return true;
    }

    @NotNull
    public DefaultElement currentLevelProgressIngameXML() {
        final File ccLocalLevelsDatFile = ccLocalLevelsDat();
        final String levelName = repositoryInfo.levelName();

        final List<DefaultElement> levelDocuments = LevelDataDecryption.decryptAllLevelDocuments(ccLocalLevelsDatFile);
        return levelDocuments.stream()
                .filter(ld -> levelName.equals(LevelDataDecryption.decryptLevelProperties(ld).levelName()))
                .findAny()
                .orElse(new LocalLevelProperties(levelName).encryptLevelProperties());
    }

    @NotNull
    private File ccLocalLevelsDat() {
        return ccLocalLevelsDat(0);
    }

    @NotNull
    private File ccLocalLevelsDat(final int index) {
        final File appdataDirectory = repositoryInfo.appdataDirectory();
        if (appdataDirectory == null) throw new HRMRepositoryException("Cannot use method reliant on local appdata in a remote repository");

        final File ccLocalLevelsDatFile = new File(appdataDirectory, "CCLocalLevels" + (index == 0 ? "" : "" + index) + ".dat");
        if (!ccLocalLevelsDatFile.isFile() && index == 0) throw new HRMRepositoryException("CCLocalLevels.dat was not found in appdata directory, check if the file path is correct");
        return ccLocalLevelsDatFile;
    }

    public void updateIngameProgress(@NotNull final LocalLevel clonedLevel) {
        final File ccLocalLevelsDatFile = ccLocalLevelsDat();
        final File ccLocalLevelsDatFile2 = ccLocalLevelsDat(2);

        LevelDataEncryption.encryptLevel(ccLocalLevelsDatFile, clonedLevel);
        final boolean deletedBackupCCLocalLevels = ccLocalLevelsDatFile2.delete();

        if (!deletedBackupCCLocalLevels && ccLocalLevelsDatFile2.isFile())
            throw new HRMRepositoryException("Could not delete CCLocalLevels2.dat, expect the game to not " +
                    "be able to start back up (manually delete the file or try again)");
    }

    public void loadCurrentRepositoryProgress() {
        final File repositoryProgress = new File(directory, CURRENT_LEVEL_XML);
        if (!repositoryProgress.isFile()) throw new HRMRepositoryException("Cannot load current repository progress; File not found");

        try {
            final DefaultElement levelElement = (DefaultElement) DocumentHelper.parseText(Files.readString(repositoryProgress.toPath())).getRootElement();
            currentLevelProgress = LevelDataDecryption.decryptLevel(levelElement);
        } catch (final IOException | DocumentException e) {
            throw new HRMRepositoryException(e);
        }
    }

    @NotNull
    public LocalLevel currentLevelProgressIngame() {
        return LevelDataDecryption.decryptLevel(currentLevelProgressIngameXML());
    }

    @NotNull
    public Optional<LocalLevel> currentLevelProgress() {
        return Optional.ofNullable(currentLevelProgress);
    }

    public void currentLevelProgress(@NotNull final LocalLevel currentLevelProgress) {
        this.currentLevelProgress = currentLevelProgress;
    }

    public void syncLevelSettings() {
        final File appdataDirectory = repositoryInfo.appdataDirectory();
        if (appdataDirectory == null) throw new HRMRepositoryException("Cannot use method reliant on local appdata in a remote repository");
        if (!appdataDirectory.isDirectory()) throw new HRMRepositoryException("Cannot sync respository's level settings with level in appdata folder: Directory not found");

        final LocalLevel currentProgressIngame = currentLevelProgressIngame();
        syncLevelSettings(currentProgressIngame);
    }

    public void syncLevelSettings(@NotNull final LocalLevel localLevelToSyncWith) {
        final LocalLevel settingsOnly = localLevelToSyncWith.createSettingsOnlyLevel();
        loadCurrentRepositoryProgress();
        if (currentLevelProgress == null) {
            currentLevelProgress = settingsOnly;
            return;
        }
        currentLevelProgress.copySettings(settingsOnly);
    }

    @NotNull
    public HRMCommit createCommit(@NotNull final LocalLevel commitProgress) {
        syncLevelSettings(commitProgress);
        assert currentLevelProgress != null; // should never be the case, since syncLevelSettings handles that

        return new HRMCommit(commitProgress, currentLevelProgress);
    }

    @NotNull
    public HRMCommit createLocalProgressCommit() {
        return createCommit(currentLevelProgressIngame());
    }

    public void commit(@NotNull final HRMCommit commit, @NotNull final HRMCommitInfo commitInfo) {
        try {
            writeCommit(commitInfo, commit);
            final HRMCommitHistory commitHistory = HRMCommitHistory.readJson(commitHistoryFile);
            commitHistory.commitInfos().add(commitInfo);
            commitHistory.writeJson(commitHistoryFile);
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
        syncRepositoryLevelData();
    }

    private void writeCommit(@NotNull final HRMCommitInfo commitInfo, @NotNull final HRMCommit commit) throws IOException {
        File file;
        int duplicate = 0;
        do {
            file = new File(commitDirectory, commitInfo.timestampFormatted() + (duplicate == 0 ? "" : "_D" + duplicate) + ".hrmc");
            duplicate++;
        } while (file.exists());

        Files.writeString(file.toPath(), commit.encodeCommit());
    }

    public void syncRepositoryLevelData() {
        syncRepositoryLevelData(currentLevelProgressIngameXML());
    }

    public void syncRepositoryLevelData(@NotNull final DefaultElement levelElement) {
        syncRepositoryLevelData(levelElement, CURRENT_LEVEL_XML);
    }

    public void syncRepositoryLevelData(@NotNull final DefaultElement levelElement, @NotNull final String xmlFileName) {
        final File hrmCurrentLevelXML = new File(directory, xmlFileName);
        try {
            Files.writeString(hrmCurrentLevelXML.toPath(), levelElement.asXML());
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
    }

    @NotNull
    public File commitDirectory() {
        return commitDirectory;
    }

    @NotNull
    public File commitHistoryFile() {
        return commitHistoryFile;
    }

    @NotNull
    public HRMRepositoryInfo repositoryInfo() {
        return repositoryInfo;
    }

    @NotNull
    public File directory() {
        return directory;
    }

}

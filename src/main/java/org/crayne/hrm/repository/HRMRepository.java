package org.crayne.hrm.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.level.data.color.ColorProperty;
import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.collision.CollisionBlockObject;
import org.crayne.hrm.api.level.data.object.type.trigger.collision.CollisionTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.SpawnTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.StopTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.TouchTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.CountTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.InstantCountTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.ItemCounter;
import org.crayne.hrm.api.level.data.object.type.trigger.item.pickup.PickupItemObject;
import org.crayne.hrm.api.level.data.object.type.trigger.item.pickup.PickupTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.FollowPlayerYTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.FollowTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.MoveTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.RotateTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.AlphaTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.AnimateTrigger;
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

@SuppressWarnings("unused")
public class HRMRepository {

    @Nullable
    private LocalLevel currentLevelProgress;

    @NotNull
    private final File commitDirectory;

    @NotNull
    private final File commitHistoryFile;

    @NotNull
    private String name;

    @NotNull
    private String levelName;

    @NotNull
    private File appdataDirectory;

    @NotNull
    private final File directory;

    private static final String CURRENT_LEVEL_XML = "hrm-current-level.xml";

    @NotNull
    public static final Gson HRM_REPOSITORY_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();

    public HRMRepository(@NotNull final String name, @NotNull final String levelName, @NotNull final File appdataDirectory, @NotNull final File directory) {
        this.directory = directory;
        this.name = name;
        this.levelName = levelName;
        this.appdataDirectory = appdataDirectory;
        this.commitDirectory = new File(this.directory, ".hrmc");
        this.commitHistoryFile = new File(this.directory, "hrm-commits.json");
        this.currentLevelProgress = null;
    }

    private boolean initDirectories() {
        final File hrmCurrentLevelXML = new File(directory, CURRENT_LEVEL_XML);
        if (directory.isDirectory() && hrmCurrentLevelXML.isFile() && commitDirectory.isDirectory() && commitHistoryFile.isFile()) return true;

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

        if (!appdataDirectory.isDirectory()) throw new HRMRepositoryException("Appdata directory was not found, check if the file path is correct");
        return false;
    }

    @NotNull
    public Pair<Boolean, Optional<LevelIDCollection>> clone(@NotNull final Map<HRMCommitInfo, HRMCommit> commits, @NotNull final DefaultElement clonedLevel, final boolean pull) {
        if (!pull && initDirectories()) return Pair.of(false, Optional.empty());
        if (pull) {
            final LevelIDCollection collisions = findCollisions(clonedLevel);
            if (collisions.anyPresent()) return Pair.of(false, Optional.of(collisions));
        }

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
        updateIngameProgress(clonedLevel);
        return Pair.of(true, Optional.empty());
    }

    @NotNull
    private LevelIDCollection findCollisions(@NotNull final DefaultElement otherLevel) {
        final LocalLevel clonedLevel = LevelDataDecryption.decryptLevel(otherLevel);
        final LocalLevel currentLevel = currentLevelProgress().orElseThrow(HRMRepositoryException::new);
        return findCollisions(clonedLevel, currentLevel);
    }

    @NotNull
    private static LevelIDCollection findCollisions(@NotNull final LocalLevel clonedLevel, @NotNull final LocalLevel currentLevel) {
        return findLevelIDCollisions(clonedLevel, currentLevel);
    }

    @NotNull
    private static LevelIDCollection findLevelIDCollisions(@NotNull final LocalLevel clonedLevel, @NotNull final LocalLevel currentLevel) {
        final LevelIDCollection clonedLevelIDs  = extractAllLevelIDs(clonedLevel);
        final LevelIDCollection currentLevelIDs = extractAllLevelIDs(currentLevel);

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
    private static LevelIDCollection extractAllLevelIDs(@NotNull final LocalLevel level) {
        final Set<Integer> itemIDs = new HashSet<>();
        final Set<Integer> blockIDs = new HashSet<>();
        final Set<Integer> groupIDs = new HashSet<>();
        final Set<Integer> linkIDs = new HashSet<>();
        final Set<Integer> colorIDs = new HashSet<>();

        for (@NotNull final LevelObject object : level.data().levelObjects()) {
            switch (object.type()) {
                case PICKUP_TRIGGER         -> itemIDs .add(((PickupTrigger)        object).itemID());
                case ITEM_COUNTER           -> itemIDs .add(((ItemCounter)          object).itemID());
                case COLLISION_BLOCK        -> blockIDs.add(((CollisionBlockObject) object).blockID());
                case TOGGLE_TRIGGER         -> groupIDs.add(((ToggleTrigger)        object).targetGroupID());
                case TOUCH_TRIGGER          -> groupIDs.add(((TouchTrigger)         object).targetGroupID());
                case SPAWN_TRIGGER          -> groupIDs.add(((SpawnTrigger)         object).targetGroupID());
                case STOP_TRIGGER           -> groupIDs.add(((StopTrigger)          object).targetGroupID());
                case ALPHA_TRIGGER          -> groupIDs.add(((AlphaTrigger)         object).targetGroupID());
                case ANIMATE_TRIGGER        -> groupIDs.add(((AnimateTrigger)       object).targetGroupID());
                case FOLLOW_PLAYERY_TRIGGER -> groupIDs.add(((FollowPlayerYTrigger) object).targetGroupID());
                case PICKUP_ITEM -> {
                    final PickupItemObject pickupItem = (PickupItemObject) object;
                    itemIDs.add(pickupItem.itemID());
                    groupIDs.add(pickupItem.targetGroupID());
                }
                case COUNT_TRIGGER -> {
                    final CountTrigger countTrigger = (CountTrigger) object;
                    itemIDs.add(countTrigger.itemID());
                    groupIDs.add(countTrigger.targetGroupID());
                }
                case INSTANT_COUNT_TRIGGER -> {
                    final InstantCountTrigger countTrigger = (InstantCountTrigger) object;
                    itemIDs.add(countTrigger.itemID());
                    groupIDs.add(countTrigger.targetGroupID());
                }
                case COLLISION_TRIGGER -> {
                    final CollisionTrigger collisionTrigger = (CollisionTrigger) object;
                    blockIDs.add(collisionTrigger.firstBlockID());
                    blockIDs.add(collisionTrigger.secondBlockID());
                    groupIDs.add(collisionTrigger.targetGroupID());
                }
                case FOLLOW_TRIGGER -> {
                    final FollowTrigger followTrigger = (FollowTrigger) object;
                    groupIDs.add(followTrigger.targetGroupID());
                    groupIDs.add(followTrigger.secondaryGroupID());
                }
                case MOVE_TRIGGER -> {
                    final MoveTrigger moveTrigger = (MoveTrigger) object;
                    groupIDs.add(moveTrigger.targetGroupID());
                    groupIDs.add(moveTrigger.targetPositionGroupID());
                }
                case ROTATE_TRIGGER -> {
                    final RotateTrigger rotateTrigger = (RotateTrigger) object;
                    groupIDs.add(rotateTrigger.targetGroupID());
                    groupIDs.add(rotateTrigger.centerGroupID());
                }
            }
            groupIDs.addAll(object.groupIDs());
            linkIDs.add(object.linkedGroupID());
        }
        for (@NotNull final ColorProperty colorProperty : level.data().levelSettings().levelColorProperties()) {
            colorIDs.add(colorProperty.channelIndex());
        }
        removeZeroes(itemIDs);
        removeZeroes(blockIDs);
        removeZeroes(groupIDs);
        removeZeroes(linkIDs);
        removeZeroes(colorIDs);
        return new LevelIDCollection(itemIDs, blockIDs, groupIDs, linkIDs, colorIDs);
    }

    private static void removeZeroes(@NotNull final Set<Integer> integers) {
        integers.remove(0);
    }

    public boolean init() {
        if (initDirectories()) return false;

        syncRepositoryLevelData();
        return true;
    }

    @NotNull
    public DefaultElement currentLevelProgressIngameXML() {
        final File ccLocalLevelsDatFile = ccLocalLevelsDat();

        final List<DefaultElement> levelDocuments = LevelDataDecryption.decryptAllLevelDocuments(ccLocalLevelsDatFile);
        return levelDocuments.stream()
                .filter(ld -> levelName.equals(LevelDataDecryption.decryptLevelProperties(ld).levelName()))
                .findAny()
                .orElseThrow(() -> new HRMRepositoryException("Level '" + levelName + "' was not found in the savefile" +
                        " of the specified appdata folder (" + ccLocalLevelsDatFile.getAbsolutePath() + ")"));
    }

    @NotNull
    private File ccLocalLevelsDat() {
        return ccLocalLevelsDat(0);
    }

    @NotNull
    private File ccLocalLevelsDat(final int index) {
        final File ccLocalLevelsDatFile = new File(appdataDirectory, "CCLocalLevels" + (index == 0 ? "" : "" + index) + ".dat");
        if (!ccLocalLevelsDatFile.isFile()) throw new HRMRepositoryException("CCLocalLevels.dat was not found in appdata directory, check if the file path is correct");

        return ccLocalLevelsDatFile;
    }

    public void updateIngameProgress(@NotNull final DefaultElement newLevel) {
        final File ccLocalLevelsDatFile = ccLocalLevelsDat();
        final File ccLocalLevelsDatFile2 = ccLocalLevelsDat(2);

        LevelDataEncryption.encryptLevel(ccLocalLevelsDatFile, LevelDataDecryption.decryptLevel(newLevel));
        final boolean deletedBackupCCLocalLevels = ccLocalLevelsDatFile2.delete();

        if (!deletedBackupCCLocalLevels)
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
        if (!appdataDirectory.isDirectory()) throw new HRMRepositoryException("Cannot sync respository's level settings with level in appdata folder: Directory not found");

        final LocalLevel currentProgressIngame = currentLevelProgressIngame();
        syncLevelSettings(currentProgressIngame);
    }

    public void syncLevelSettings(@NotNull final LocalLevel localLevelToSyncWith) {
        final LocalLevel settingsOnly = localLevelToSyncWith.createSettingsOnlyLevel();
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
    public String name() {
        return name;
    }

    public void name(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    public String levelName() {
        return levelName;
    }

    public void levelName(@NotNull final String levelName) {
        this.levelName = levelName;
    }

    @NotNull
    public File appdataDirectory() {
        return appdataDirectory;
    }

    public void appdataDirectory(@NotNull final File appdataDirectory) {
        this.appdataDirectory = appdataDirectory;
    }

    @NotNull
    public File directory() {
        return directory;
    }

}

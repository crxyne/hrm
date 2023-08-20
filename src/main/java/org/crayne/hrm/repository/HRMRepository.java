package org.crayne.hrm.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public boolean init() {
        final File hrmCurrentLevelXML = new File(directory, "hrm-current-level.xml");
        if (directory.isDirectory() && hrmCurrentLevelXML.isFile() && commitDirectory.isDirectory() && commitHistoryFile.isFile()) return false;

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
        syncRepositoryLevelData();
        return true;
    }

    @NotNull
    public DefaultElement currentLevelProgressIngameXML() {
        final File ccLocalLevelsDatFile = new File(appdataDirectory, "CCLocalLevels.dat");
        if (!ccLocalLevelsDatFile.isFile()) throw new HRMRepositoryException("CCLocalLevels.dat was not found in appdata directory, check if the file path is correct");

        final List<DefaultElement> levelDocuments = LevelDataDecryption.decryptAllLevelDocuments(ccLocalLevelsDatFile);
        return levelDocuments.stream()
                .filter(ld -> levelName.equals(LevelDataDecryption.decryptLevelProperties(ld).levelName()))
                .findAny()
                .orElseThrow(() -> new HRMRepositoryException("Level '" + levelName + "' was not found in the savefile" +
                        " of the specified appdata folder (" + ccLocalLevelsDatFile.getAbsolutePath() + ")"));
    }

    public void loadCurrentRepositoryProgress() {
        final File repositoryProgress = new File(directory, "hrm-current-level.xml");
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

    public void commit(@NotNull final HRMCommit commit, @NotNull final String message) {
        final String currentTimeFormatted = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        try {
            Files.writeString(new File(commitDirectory, currentTimeFormatted + ".hrmc").toPath(), commit.encodeCommit());
            final HRMCommitHistory commitHistory = HRMCommitHistory.readJson(commitHistoryFile);
            commitHistory.commitInfos().add(new HRMCommitInfo(message, currentTimeFormatted));
            commitHistory.writeJson(commitHistoryFile);
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
        syncRepositoryLevelData();
    }

    public void syncRepositoryLevelData() {
        final DefaultElement levelElement = currentLevelProgressIngameXML();
        final File hrmCurrentLevelXML = new File(directory, "hrm-current-level.xml");
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

package org.crayne.hrm.repository;

import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
import org.dom4j.tree.DefaultElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class HRMRepository {

    @Nullable
    private LocalLevel currentLevelProgress;

    @NotNull
    private final File commitDirectory;

    @NotNull
    private final File commitHistory;

    @NotNull
    private String name;

    @NotNull
    private String levelName;

    @NotNull
    private File appdataDirectory;

    @NotNull
    private final File directory;

    public HRMRepository(@NotNull final String name, @NotNull final String levelName, @NotNull final File appdataDirectory, @NotNull final File directory) {
        this.directory = directory;
        this.name = name;
        this.levelName = levelName;
        this.appdataDirectory = appdataDirectory;
        this.commitDirectory = new File(this.directory, ".hrmc");
        this.commitHistory = new File(this.directory, "hrm-commits.json");
        this.currentLevelProgress = null;
    }

    public boolean init() {
        final File hrmCurrentLevelXML = new File(directory, "hrm-current-level.xml");
        if (directory.isDirectory() && hrmCurrentLevelXML.isFile() && commitDirectory.isDirectory() && commitHistory.isFile()) return false;

        final boolean createdMainDirectory = directory.isDirectory() || directory.mkdirs();
        if (!createdMainDirectory) throw new HRMRepositoryException("Could not create hrm repository directory");

        final boolean createdCommitDirectory = commitDirectory.isDirectory() || commitDirectory.mkdirs();
        if (!createdCommitDirectory) throw new HRMRepositoryException("Could not create hrm repository commit directory");

        try {
            final boolean createdCommitHistory = commitHistory.isFile() || commitHistory.createNewFile();
            if (!createdCommitHistory) throw new HRMRepositoryException("Could not create commit history json file");
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }

        if (!appdataDirectory.isDirectory()) throw new HRMRepositoryException("Appdata directory was not found, check if the file path is correct");
        final File ccLocalLevelsDatFile = new File(appdataDirectory, "CCLocalLevels.dat");
        if (!ccLocalLevelsDatFile.isFile()) throw new HRMRepositoryException("CCLocalLevels.dat was not found in appdata directory, check if the file path is correct");

        final List<DefaultElement> levelDocuments = LevelDataDecryption.decryptAllLevelDocuments(ccLocalLevelsDatFile);
        final DefaultElement levelElement = levelDocuments.stream()
                .filter(ld -> levelName.equals(LevelDataDecryption.decryptLevelProperties(ld).levelName()))
                .findAny()
                .orElseThrow(() -> new HRMRepositoryException("Level '" + levelName + "' was not found in the savefile" +
                        " of the specified appdata folder (" + ccLocalLevelsDatFile.getAbsolutePath() + ")"));

        try {
            Files.writeString(hrmCurrentLevelXML.toPath(), levelElement.asXML());
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
        return true;
    }

    @NotNull
    public Optional<LocalLevel> currentLevelProgress() {
        return Optional.ofNullable(currentLevelProgress);
    }

    public void currentLevelProgress(@NotNull final LocalLevel currentLevelProgress) {
        this.currentLevelProgress = currentLevelProgress;
    }

    @NotNull
    public File commitDirectory() {
        return commitDirectory;
    }

    @NotNull
    public File commitHistory() {
        return commitHistory;
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

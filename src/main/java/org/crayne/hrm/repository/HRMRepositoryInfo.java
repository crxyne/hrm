package org.crayne.hrm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HRMRepositoryInfo {

    @NotNull
    private String name;

    @NotNull
    private String levelName;

    @Nullable
    private String appdataPath;

    public HRMRepositoryInfo(@NotNull final String name, @NotNull final String levelName, @Nullable final String appdataPath) {
        this.name = name;
        this.levelName = levelName;
        this.appdataPath = appdataPath;
    }

    @NotNull
    public static HRMRepositoryInfo load(@NotNull final File repositoryInfoJsonFile) throws IOException {
        final HRMRepositoryInfo repositoryInfo = HRMRepository.HRM_REPOSITORY_GSON.fromJson(Files.readString(repositoryInfoJsonFile.toPath()), HRMRepositoryInfo.class);

        try {
            repositoryInfo.save(repositoryInfoJsonFile);
        } catch (final IOException e) {
            throw new HRMRepositoryException(e);
        }
        return repositoryInfo;
    }

    public void save(@NotNull final File repositoryInfoJsonFile) throws IOException {
        Files.writeString(repositoryInfoJsonFile.toPath(), HRMRepository.HRM_REPOSITORY_GSON.toJson(this));
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

    @Nullable
    public File appdataDirectory() {
        return appdataPath == null ? null : new File(appdataPath);
    }

    @Nullable
    public String appdataPath() {
        return appdataPath;
    }

    public void appdataPath(@Nullable final String appdataPath) {
        this.appdataPath = appdataPath;
    }

}

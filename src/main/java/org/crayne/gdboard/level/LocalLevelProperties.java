package org.crayne.gdboard.level;

import org.crayne.gdboard.savefile.decrypt.LevelDataDecryption;
import org.crayne.gdboard.savefile.property.PropertyUtil;
import org.crayne.gdboard.level.data.LevelEditorCamera;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.zip.DataFormatException;

@SuppressWarnings("unused")
public class LocalLevelProperties {

    @Nullable
    private String levelName;

    @Nullable
    private String levelDescription;

    @Nullable
    private String creatorName;

    private int officialSongID, customSongID, attempts, levelType, levelVersion, secondsSpentEditing, objectCount;

    @NotNull
    private LevelEditorCamera camera;

    @Nullable
    private String innerLevelString;

    public LocalLevelProperties(@NotNull final String levelName, @Nullable final String levelDescription, @NotNull final String creatorName, @NotNull final LevelEditorCamera camera) {
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.creatorName = creatorName;
        this.camera = camera;
    }

    public void putProperty(@NotNull final String key, @Nullable final String data) {
        if (!key.startsWith("k")) return;

        final String keyID = key.substring(1);
        if (keyID.equals("4")) {
            try {
                if (data == null) return;
                innerLevelString = LevelDataDecryption.decompressZlibBytes(LevelDataDecryption.decodeBase64(data));
            } catch (final DataFormatException e) {
                innerLevelString = null;
            }
            return;
        }
        switch (keyID) {
            case "2" -> levelName = data;
            case "5" -> creatorName = data;
            case "16" -> levelVersion = PropertyUtil.parseIntValue(data, 1);
            case "18" -> attempts = PropertyUtil.parseIntValue(data, 0);
            case "21" -> levelType = PropertyUtil.parseIntValue(data, 2);
            case "48" -> objectCount = PropertyUtil.parseIntValue(data, 0);
            case "80" -> secondsSpentEditing = PropertyUtil.parseIntValue(data, 0);
            case "I1" -> camera.positionX(PropertyUtil.parseFloatValue(data, 0));
            case "I2" -> camera.positionY(PropertyUtil.parseFloatValue(data, 0));
            case "I3" -> camera.zoom(PropertyUtil.parseFloatValue(data, 1));
        }
    }

    public LocalLevelProperties() {
        this.camera = LevelEditorCamera.defaultCamera();
    }

    @Nullable
    public String levelName() {
        return levelName;
    }

    public void levelName(@NotNull final String levelName) {
        this.levelName = levelName;
    }

    @Nullable
    public String levelDescription() {
        return levelDescription;
    }

    public void levelDescription(@Nullable final String levelDescription) {
        this.levelDescription = levelDescription;
    }

    public int officialSongID() {
        return officialSongID;
    }

    public void officialSongID(final int officialSongID) {
        this.officialSongID = officialSongID;
    }

    public int customSongID() {
        return customSongID;
    }

    public void customSongID(final int customSongID) {
        this.customSongID = customSongID;
    }

    @Nullable
    public String creatorName() {
        return creatorName;
    }

    public void creatorName(@Nullable final String creatorName) {
        this.creatorName = creatorName;
    }

    public int attempts() {
        return attempts;
    }

    public void attempts(final int attempts) {
        this.attempts = attempts;
    }

    public int levelType() {
        return levelType;
    }

    public void levelType(final int levelType) {
        this.levelType = levelType;
    }

    public int levelVersion() {
        return levelVersion;
    }

    public void levelVersion(final int levelVersion) {
        this.levelVersion = levelVersion;
    }

    public int secondsSpentEditing() {
        return secondsSpentEditing;
    }

    public void secondsSpentEditing(final int secondsSpentEditing) {
        this.secondsSpentEditing = secondsSpentEditing;
    }

    public int objectCount() {
        return objectCount;
    }

    public void objectCount(final int objectCount) {
        this.objectCount = objectCount;
    }

    @NotNull
    public LevelEditorCamera camera() {
        return camera;
    }

    public void camera(@NotNull final LevelEditorCamera camera) {
        this.camera = camera;
    }

    @Nullable
    public String innerLevelString() {
        return innerLevelString;
    }

    public void innerLevelString(@Nullable final String innerLevelString) {
        this.innerLevelString = innerLevelString;
    }

    @NotNull
    public String toString() {
        return "LocalLevelProperties{" +
                "levelName='" + levelName + '\'' +
                ", levelDescription='" + levelDescription + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", officialSongID=" + officialSongID +
                ", customSongID=" + customSongID +
                ", attempts=" + attempts +
                ", levelType=" + levelType +
                ", levelVersion=" + levelVersion +
                ", secondsSpentEditing=" + secondsSpentEditing +
                ", objectCount=" + objectCount +
                ", camera=" + camera +
                '}';
    }

}

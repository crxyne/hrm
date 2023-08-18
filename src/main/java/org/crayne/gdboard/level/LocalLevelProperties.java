package org.crayne.gdboard.level;

import org.crayne.gdboard.level.data.LevelEditorCamera;
import org.crayne.gdboard.savefile.decrypt.LevelDataDecryption;
import org.crayne.gdboard.savefile.property.PropertyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
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
    private int binaryVersion;

    @NotNull
    private LevelEditorCamera camera;

    @Nullable
    private transient String innerLevelString;

    public LocalLevelProperties(@NotNull final String levelName, @Nullable final String levelDescription, @NotNull final String creatorName, @NotNull final LevelEditorCamera camera) {
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.creatorName = creatorName;
        this.camera = camera;
        this.binaryVersion = 0;
    }

    public void putProperty(@NotNull final String key, @Nullable final String data) {
        if (!key.startsWith("k")) return;

        final String keyID = key.substring(1);
        if (keyID.equals("4") && data != null) {
            try {
                final byte[] base64Bytes = LevelDataDecryption.decodeBase64(data);
                innerLevelString = LevelDataDecryption.decompressZlibBytes(base64Bytes);
            } catch (final DataFormatException e) {
                innerLevelString = null;
            }
            return;
        }
        switch (keyID) {
            case "2" -> levelName = data;
            case "3" -> levelDescription = data == null ? null : new String(LevelDataDecryption.decodeBase64(data), StandardCharsets.UTF_8);
            case "5" -> creatorName = data;
            case "8" -> officialSongID = PropertyUtil.parseIntValue(data, 0);
            case "45" -> customSongID = PropertyUtil.parseIntValue(data, 0);
            case "16" -> levelVersion = PropertyUtil.parseIntValue(data, 1);
            case "50" -> binaryVersion = PropertyUtil.parseIntValue(data, 0);
            case "18" -> attempts = PropertyUtil.parseIntValue(data, 0);
            case "21" -> levelType = PropertyUtil.parseIntValue(data, 2);
            case "48" -> objectCount = PropertyUtil.parseIntValue(data, 0);
            case "80" -> secondsSpentEditing = PropertyUtil.parseIntValue(data, 0);
            case "I1" -> camera.positionX(PropertyUtil.parseFloatValue(data, 0));
            case "I2" -> camera.positionY(PropertyUtil.parseFloatValue(data, 0));
            case "I3" -> camera.zoom(PropertyUtil.parseFloatValue(data, 1));
        }
    }

    public int binaryVersion() {
        return binaryVersion;
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

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LocalLevelProperties that = (LocalLevelProperties) o;

        if (officialSongID != that.officialSongID) return false;
        if (customSongID != that.customSongID) return false;
        if (attempts != that.attempts) return false;
        if (levelType != that.levelType) return false;
        if (levelVersion != that.levelVersion) return false;
        if (secondsSpentEditing != that.secondsSpentEditing) return false;
        if (objectCount != that.objectCount) return false;
        if (binaryVersion != that.binaryVersion) return false;
        if (!Objects.equals(levelName, that.levelName)) return false;
        if (!Objects.equals(levelDescription, that.levelDescription))
            return false;
        if (!Objects.equals(creatorName, that.creatorName)) return false;
        return camera.equals(that.camera);
    }

    public int hashCode() {
        int result = levelName != null ? levelName.hashCode() : 0;
        result = 31 * result + (levelDescription != null ? levelDescription.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + officialSongID;
        result = 31 * result + customSongID;
        result = 31 * result + attempts;
        result = 31 * result + levelType;
        result = 31 * result + levelVersion;
        result = 31 * result + secondsSpentEditing;
        result = 31 * result + objectCount;
        result = 31 * result + binaryVersion;
        result = 31 * result + camera.hashCode();
        return result;
    }
}

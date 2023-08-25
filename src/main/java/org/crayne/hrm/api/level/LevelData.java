package org.crayne.hrm.api.level;

import org.crayne.hrm.api.level.data.object.type.LazyLevelObject;
import org.crayne.hrm.api.level.data.settings.LevelSettings;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class LevelData {

    @NotNull
    private final LevelSettings levelSettings;

    private Set<LazyLevelObject> levelObjects;

    public LevelData(@NotNull final LevelSettings settings, final Collection<LazyLevelObject> levelObjects) {
        this.levelSettings = settings;
        this.levelObjects = new HashSet<>(levelObjects);
    }

    public LevelData() {
        this.levelSettings = new LevelSettings();
        this.levelObjects = new HashSet<>();
    }

    public LevelData(@NotNull final LevelData data) {
        this(data, true);
    }

    public LevelData(@NotNull final LevelData data, final boolean copyObjects) {
        this.levelSettings = data.levelSettings;
        this.levelObjects = copyObjects ? new HashSet<>(data.levelObjects) : new HashSet<>();
    }

    @NotNull
    public LevelSettings levelSettings() {
        return levelSettings;
    }

    @NotNull
    public Collection<LazyLevelObject> levelObjects() {
        return Collections.unmodifiableCollection(levelObjects);
    }

    protected void levelObjects(@NotNull final Collection<LazyLevelObject> levelObjects) {
        this.levelObjects = new HashSet<>(levelObjects);
    }

    protected void removeLevelObjects(@NotNull final Predicate<LazyLevelObject> predicate) {
        this.levelObjects.removeIf(predicate);
    }

    protected void removeAllLevelObjects(@NotNull final Collection<LazyLevelObject> levelObjects) {
        this.levelObjects.removeAll(levelObjects);
    }

    protected void addLevelObjects(@NotNull final Collection<? extends LazyLevelObject> levelObjects) {
        this.levelObjects.addAll(levelObjects);
    }

    protected void clearLevelObjects() {
        this.levelObjects.clear();
    }

    @NotNull
    public String toString() {
        return "LevelData{" +
                "levelSettings=" + levelSettings +
                ", levelObjects=" + levelObjects +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelData levelData = (LevelData) o;

        if (!levelSettings.equals(levelData.levelSettings)) return false;
        return Objects.equals(levelObjects, levelData.levelObjects);
    }

    public int hashCode() {
        int result = levelSettings.hashCode();
        result = 31 * result + (levelObjects != null ? levelObjects.hashCode() : 0);
        return result;
    }
}

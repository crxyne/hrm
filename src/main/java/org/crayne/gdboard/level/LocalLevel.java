package org.crayne.gdboard.level;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public class LocalLevel {

    @NotNull
    private final LocalLevelProperties properties;

    @NotNull
    private final LevelData data;

    public LocalLevel(@NotNull final LocalLevel localLevel) {
        this.properties = localLevel.properties;
        this.data = localLevel.data;
    }

    public LocalLevel(@NotNull final LocalLevelProperties properties, @NotNull final LevelData data) {
        this(properties, data, true);
    }

    public LocalLevel(@NotNull final LocalLevelProperties properties, @NotNull final LevelData data, final boolean copyObjects) {
        this.properties = properties;
        this.data = new LevelData(data, copyObjects);
    }

    public LocalLevel(@NotNull final LocalLevel localLevel, final boolean copyObjects) {
        this(localLevel.properties, localLevel.data, copyObjects);
    }

    public LocalLevelProperties properties() {
        return properties;
    }

    public LevelData data() {
        return data;
    }

    public void levelObjects(@NotNull final Collection<LevelObject> levelObjects) {
        properties.objectCount(levelObjects.size());
        data.levelObjects(levelObjects);
    }

    public void removeLevelObjects(@NotNull final Predicate<LevelObject> predicate) {
        properties.objectCount(properties().objectCount() - data.levelObjects().stream().filter(predicate).toList().size());
        data.removeLevelObjects(predicate);
    }

    public void addLevelObjects(@NotNull final Collection<? extends LevelObject> levelObjects) {
        properties.objectCount(properties().objectCount() + levelObjects.size());
        data.addLevelObjects(levelObjects);
    }

    public void clearLevelObjects() {
        data.clearLevelObjects();
        properties.objectCount(0);
    }

    @NotNull
    public String toString() {
        return "LocalLevel{" +
                "properties=" + properties +
                ", data=" + data +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LocalLevel that = (LocalLevel) o;

        System.out.println("check: " + properties.equals(that.properties));
        if (!properties.equals(that.properties)) return false;
        return data.equals(that.data);
    }

    public int hashCode() {
        int result = properties.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

}

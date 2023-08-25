package org.crayne.hrm.api.level;

import org.crayne.hrm.api.level.data.color.ColorProperty;
import org.crayne.hrm.api.level.data.object.type.LazyLevelObject;
import org.crayne.hrm.api.level.data.settings.LevelSettings;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class LocalLevel {

    @NotNull
    private LocalLevelProperties properties;

    @NotNull
    private LevelData data;

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

    // copies all settings of one level into a new, empty level without colors and without objects (everything else is copied)
    @NotNull
    public LocalLevel createSettingsOnlyLevel() {
        final LocalLevelProperties newProperties = new LocalLevelProperties(properties);
        newProperties.innerLevelString(null);

        final LevelData newData = new LevelData(data.levelSettings().copyWithoutColors(), new HashSet<>());
        return new LocalLevel(newProperties, newData);
    }

    public void copySettings(@NotNull final LocalLevel settingsLevel) {
        final String innerLevelString = properties.innerLevelString();
        this.properties = new LocalLevelProperties(settingsLevel.properties);
        properties.innerLevelString(innerLevelString);

        final Collection<LazyLevelObject> levelObjects = data.levelObjects();
        final Set<ColorProperty> levelColorProperties = data.levelSettings().levelColorProperties();
        final LevelSettings settings = settingsLevel.data.levelSettings().copyWithoutColors();
        settings.addAllColorProperties(levelColorProperties);

        this.data = new LevelData(settings, levelObjects);
    }

    @NotNull
    public LocalLevelProperties properties() {
        return properties;
    }

    @NotNull
    public LevelData data() {
        return data;
    }

    private void updateObjectCount() {
        properties.objectCount(data.levelObjects().size());
    }

    public void levelObjects(@NotNull final Collection<LazyLevelObject> levelObjects) {
        data.levelObjects(levelObjects);
        updateObjectCount();
    }

    public void removeLevelObjects(@NotNull final Predicate<LazyLevelObject> predicate) {
        data.removeLevelObjects(predicate);
        updateObjectCount();
    }

    public void removeAllLevelObjects(@NotNull final Collection<LazyLevelObject> levelObjects) {
        data.removeAllLevelObjects(levelObjects);
        updateObjectCount();
    }

    public void addLevelObjects(@NotNull final Collection<? extends LazyLevelObject> levelObjects) {
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

        if (!properties.equals(that.properties)) return false;
        return data.equals(that.data);
    }

    public int hashCode() {
        int result = properties.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

}

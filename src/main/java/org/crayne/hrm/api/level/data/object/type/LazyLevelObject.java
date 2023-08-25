package org.crayne.hrm.api.level.data.object.type;

import org.crayne.hrm.api.level.data.object.ObjectID;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("unused")
public class LazyLevelObject {

    @NotNull
    private final String loadPropertiesLater;

    @Nullable
    private LevelObject loadedLevelObject;

    public LazyLevelObject(@NotNull final String loadPropertiesLater) {
        this.loadPropertiesLater = loadPropertiesLater;
    }

    @NotNull
    public LevelObject levelObject() {
        if (loadedLevelObject != null) return loadedLevelObject;

        final Properties properties = new Properties(PropertyUtil.decodeProperties(loadPropertiesLater, ","));
        loadedLevelObject = ObjectID.parse(properties);
        return loadedLevelObject;
    }

    @NotNull
    public String propertiesString() {
        if (loadedLevelObject == null) return loadPropertiesLater;

        return PropertyUtil.encodeProperties(loadedLevelObject.createProperties().propertiesMap(), ",");
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LazyLevelObject that = (LazyLevelObject) o;

        return loadPropertiesLater.equals(that.loadPropertiesLater) || Objects.equals(loadedLevelObject, that.loadedLevelObject);
    }

    public int hashCode() {
        int result = loadPropertiesLater.hashCode();
        result = 31 * result + (loadedLevelObject != null ? loadedLevelObject.hashCode() : 0);
        return result;
    }
}

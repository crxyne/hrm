package org.crayne.gdboard.level.data.object.type.general;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class OrbObject extends LevelObject {

    private boolean multiActivateEnabled;

    public OrbObject(final int objectID, final float positionX, final float positionY, final boolean multiActivateEnabled) {
        super(objectID, positionX, positionY);
        this.multiActivateEnabled = multiActivateEnabled;
    }

    public OrbObject(@NotNull final LevelObject levelObject, final boolean multiActivateEnabled) {
        super(levelObject);
        this.multiActivateEnabled = multiActivateEnabled;
    }

    public OrbObject(final int objectID, final float positionX, final float positionY) {
        this(objectID, positionX, positionY, false);
    }

    public OrbObject(@NotNull final LevelObject levelObject) {
        this(levelObject, false);
    }

    public OrbObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.multiActivateEnabled = objectProperties.booleanProperty(LevelObjectProperty.ORB_MULTI_ACTIVATE);
    }

    public boolean multiActivateEnabled() {
        return multiActivateEnabled;
    }

    public void multiActivateEnabled(final boolean multiActivateEnabled) {
        this.multiActivateEnabled = multiActivateEnabled;
    }

    @NotNull
    public String toString() {
        return "OrbObject{" +
                "multiActivateEnabled=" + multiActivateEnabled +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final OrbObject orbObject = (OrbObject) o;

        return multiActivateEnabled == orbObject.multiActivateEnabled;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (multiActivateEnabled ? 1 : 0);
        return result;
    }
}

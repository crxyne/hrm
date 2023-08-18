package org.crayne.gdboard.level.data.object.type.general;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.decoration.ColorableObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ToggleOrbObject extends ColorableObject {

    private boolean multiActivateEnabled;
    private int targetGroupID;
    private boolean activateGroup;

    public ToggleOrbObject(final int objectID, final float positionX, final float positionY, final boolean multiActivateEnabled, final int targetGroupID, final boolean activateGroup) {
        super(objectID, positionX, positionY);
        this.multiActivateEnabled = multiActivateEnabled;
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public ToggleOrbObject(@NotNull final LevelObject levelObject, final boolean multiActivateEnabled, final int targetGroupID, final boolean activateGroup) {
        super(levelObject);
        this.multiActivateEnabled = multiActivateEnabled;
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public ToggleOrbObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public ToggleOrbObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public ToggleOrbObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.multiActivateEnabled = objectProperties.booleanProperty(LevelObjectProperty.ORB_MULTI_ACTIVATE);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.activateGroup = objectProperties.booleanProperty(LevelObjectProperty.ACTIVATE_GROUP);
    }

    public boolean multiActivateEnabled() {
        return multiActivateEnabled;
    }

    public void multiActivateEnabled(final boolean multiActivateEnabled) {
        this.multiActivateEnabled = multiActivateEnabled;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public boolean activateGroup() {
        return activateGroup;
    }

    public void activateGroup(final boolean activateGroup) {
        this.activateGroup = activateGroup;
    }

    @NotNull
    public String toString() {
        return "ToggleOrbObject{" +
                "multiActivateEnabled=" + multiActivateEnabled +
                ", targetGroupID=" + targetGroupID +
                ", activateGroup=" + activateGroup +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final ToggleOrbObject that = (ToggleOrbObject) o;

        if (multiActivateEnabled != that.multiActivateEnabled) return false;
        if (targetGroupID != that.targetGroupID) return false;
        return activateGroup == that.activateGroup;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (multiActivateEnabled ? 1 : 0);
        result = 31 * result + targetGroupID;
        result = 31 * result + (activateGroup ? 1 : 0);
        return result;
    }
}

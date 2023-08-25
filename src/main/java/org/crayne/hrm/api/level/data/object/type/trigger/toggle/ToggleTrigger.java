package org.crayne.hrm.api.level.data.object.type.trigger.toggle;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class ToggleTrigger extends Trigger implements TargetTrigger {

    private int targetGroupID;
    private boolean activateGroup;

    public ToggleTrigger(final int objectID, final float positionX, final float positionY, final int targetGroupID, final boolean activateGroup) {
        super(objectID, positionX, positionY);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public ToggleTrigger(@NotNull final LevelObject levelObject, final int targetGroupID, final boolean activateGroup) {
        super(levelObject);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public ToggleTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public ToggleTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public ToggleTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.activateGroup = objectProperties.booleanProperty(LevelObjectProperty.ACTIVATE_GROUP);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1049, 1812);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);
        properties.putBooleanProperty(LevelObjectProperty.ACTIVATE_GROUP, activateGroup);

        return properties;
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
        return "ToggleTrigger{" +
                "targetGroupID=" + targetGroupID +
                ", activateGroup=" + activateGroup +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final ToggleTrigger that = (ToggleTrigger) o;

        if (targetGroupID != that.targetGroupID) return false;
        return activateGroup == that.activateGroup;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + targetGroupID;
        result = 31 * result + (activateGroup ? 1 : 0);
        return result;
    }
}

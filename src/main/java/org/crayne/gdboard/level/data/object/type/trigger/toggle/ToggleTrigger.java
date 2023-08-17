package org.crayne.gdboard.level.data.object.type.trigger.toggle;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class ToggleTrigger extends Trigger {

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
        this.targetGroupID = objectProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID);
        this.activateGroup = objectProperties.booleanProperty(LevelObjectData.ACTIVATE_GROUP);
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

}

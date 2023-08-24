package org.crayne.hrm.api.level.data.object.type.trigger.general;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class StopTrigger extends Trigger implements TargetTrigger {

    private int targetGroupID;

    public StopTrigger(final int objectID, final float positionX, final float positionY, final int targetGroupID) {
        super(objectID, positionX, positionY);
        this.targetGroupID = targetGroupID;
    }

    public StopTrigger(@NotNull final LevelObject levelObject, final int targetGroupID) {
        super(levelObject);
        this.targetGroupID = targetGroupID;
    }

    public StopTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public StopTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public StopTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);

        return properties;
    }

    @NotNull
    public String toString() {
        return "StopTrigger{" +
                "targetGroupID=" + targetGroupID +
                "} " + super.toString();
    }
}

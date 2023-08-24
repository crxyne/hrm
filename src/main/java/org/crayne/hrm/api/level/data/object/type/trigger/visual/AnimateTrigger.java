package org.crayne.hrm.api.level.data.object.type.trigger.visual;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class AnimateTrigger extends Trigger implements TargetTrigger {

    private int animationID; // 76
    private int targetGroupID; // 51

    public AnimateTrigger(final int objectID, final float positionX, final float positionY,
                          final boolean touchTriggered, final int animationID, final int targetGroupID) {
        super(objectID, positionX, positionY, touchTriggered);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(@NotNull final LevelObject levelObject, final boolean touchTriggered,
                          final int animationID, final int targetGroupID) {
        super(levelObject, touchTriggered);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(final int objectID, final float positionX, final float positionY, final boolean spawnTriggered,
                          final boolean multiTriggered, final int animationID, final int targetGroupID) {
        super(objectID, positionX, positionY, spawnTriggered, multiTriggered);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(@NotNull final LevelObject levelObject, final boolean spawnTriggered,
                          final boolean multiTriggered, final int animationID, final int targetGroupID) {
        super(levelObject, spawnTriggered, multiTriggered);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(final int objectID, final float positionX, final float positionY,
                          final int animationID, final int targetGroupID) {
        super(objectID, positionX, positionY);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(@NotNull final LevelObject levelObject, final int animationID, final int targetGroupID) {
        super(levelObject);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public AnimateTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public AnimateTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.animationID = objectProperties.integerProperty(LevelObjectProperty.ANIMATION_ID);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.ANIMATION_ID, animationID);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);

        return properties;
    }

    public int animationID() {
        return animationID;
    }

    public void animationID(final int animationID) {
        this.animationID = animationID;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    @NotNull
    public String toString() {
        return "AnimateTrigger{" +
                "animationID=" + animationID +
                ", targetGroupID=" + targetGroupID +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final AnimateTrigger that = (AnimateTrigger) o;

        if (animationID != that.animationID) return false;
        return targetGroupID == that.targetGroupID;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + animationID;
        result = 31 * result + targetGroupID;
        return result;
    }
}

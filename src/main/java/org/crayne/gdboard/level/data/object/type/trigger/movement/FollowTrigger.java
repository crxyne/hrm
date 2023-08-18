package org.crayne.gdboard.level.data.object.type.trigger.movement;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class FollowTrigger extends Trigger {

    private float modifierX, modifierY; // 72, 73
    private float triggerDuration; // 10
    private int targetGroupID; // 51
    private int secondaryGroupID; // 71

    public FollowTrigger(final int objectID, final float positionX, final float positionY, final float modifierX,
                         final float modifierY, final float triggerDuration, final int targetGroupID, final int secondaryGroupID) {
        super(objectID, positionX, positionY);
        this.modifierX = modifierX;
        this.modifierY = modifierY;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.secondaryGroupID = secondaryGroupID;
    }

    public FollowTrigger(@NotNull final LevelObject levelObject, final float modifierX, final float modifierY,
                         final float triggerDuration, final int targetGroupID, final int secondaryGroupID) {
        super(levelObject);
        this.modifierX = modifierX;
        this.modifierY = modifierY;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.secondaryGroupID = secondaryGroupID;
    }

    public FollowTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.modifierX = 1.0f;
        this.modifierY = 1.0f;
        this.triggerDuration = 0.5f;
    }

    public FollowTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.modifierX = 1.0f;
        this.modifierY = 1.0f;
        this.triggerDuration = 0.5f;
    }

    public FollowTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.modifierX = objectProperties.floatProperty(LevelObjectProperty.FOLLOW_X_MOD);
        this.modifierY = objectProperties.floatProperty(LevelObjectProperty.FOLLOW_Y_MOD);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectProperty.DURATION);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.secondaryGroupID = objectProperties.integerProperty(LevelObjectProperty.SECOND_TARGET_GROUP_ID);
    }

    public float modifierX() {
        return modifierX;
    }

    public void modifierX(final float modifierX) {
        this.modifierX = modifierX;
    }

    public float modifierY() {
        return modifierY;
    }

    public void modifierY(final float modifierY) {
        this.modifierY = modifierY;
    }

    public float triggerDuration() {
        return triggerDuration;
    }

    public void triggerDuration(final float triggerDuration) {
        this.triggerDuration = triggerDuration;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public int secondaryGroupID() {
        return secondaryGroupID;
    }

    public void secondaryGroupID(final int secondaryGroupID) {
        this.secondaryGroupID = secondaryGroupID;
    }

    @NotNull
    public String toString() {
        return "FollowTrigger{" +
                "modifierX=" + modifierX +
                ", modifierY=" + modifierY +
                ", triggerDuration=" + triggerDuration +
                ", targetGroupID=" + targetGroupID +
                ", secondaryGroupID=" + secondaryGroupID +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final FollowTrigger that = (FollowTrigger) o;

        if (Float.compare(that.modifierX, modifierX) != 0) return false;
        if (Float.compare(that.modifierY, modifierY) != 0) return false;
        if (Float.compare(that.triggerDuration, triggerDuration) != 0) return false;
        if (targetGroupID != that.targetGroupID) return false;
        return secondaryGroupID == that.secondaryGroupID;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifierX != +0.0f ? Float.floatToIntBits(modifierX) : 0);
        result = 31 * result + (modifierY != +0.0f ? Float.floatToIntBits(modifierY) : 0);
        result = 31 * result + (triggerDuration != +0.0f ? Float.floatToIntBits(triggerDuration) : 0);
        result = 31 * result + targetGroupID;
        result = 31 * result + secondaryGroupID;
        return result;
    }
}

package org.crayne.hrm.api.level.data.object.type.trigger.movement;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.BiTargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class FollowTrigger extends Trigger implements BiTargetTrigger {

    private float modifierX, modifierY;
    private float triggerDuration;
    private int targetGroupID;
    private int secondGroupID;

    public FollowTrigger(final int objectID, final float positionX, final float positionY, final float modifierX,
                         final float modifierY, final float triggerDuration, final int targetGroupID, final int secondGroupID) {
        super(objectID, positionX, positionY);
        this.modifierX = modifierX;
        this.modifierY = modifierY;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.secondGroupID = secondGroupID;
    }

    public FollowTrigger(@NotNull final LevelObject levelObject, final float modifierX, final float modifierY,
                         final float triggerDuration, final int targetGroupID, final int secondGroupID) {
        super(levelObject);
        this.modifierX = modifierX;
        this.modifierY = modifierY;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.secondGroupID = secondGroupID;
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
        this.secondGroupID = objectProperties.integerProperty(LevelObjectProperty.SECOND_TARGET_GROUP_ID);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1347);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putFloatProperty(LevelObjectProperty.FOLLOW_X_MOD, modifierX);
        properties.putFloatProperty(LevelObjectProperty.FOLLOW_Y_MOD, modifierY);
        properties.putFloatProperty(LevelObjectProperty.DURATION, triggerDuration);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);
        properties.putIntProperty(LevelObjectProperty.SECOND_TARGET_GROUP_ID, secondGroupID);

        return properties;
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

    public int secondGroupID() {
        return secondGroupID;
    }

    public void secondGroupID(final int secondaryGroupID) {
        this.secondGroupID = secondaryGroupID;
    }

    @NotNull
    public String toString() {
        return "FollowTrigger{" +
                "modifierX=" + modifierX +
                ", modifierY=" + modifierY +
                ", triggerDuration=" + triggerDuration +
                ", targetGroupID=" + targetGroupID +
                ", secondaryGroupID=" + secondGroupID +
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
        return secondGroupID == that.secondGroupID;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifierX != 0.0f ? Float.floatToIntBits(modifierX) : 0);
        result = 31 * result + (modifierY != 0.0f ? Float.floatToIntBits(modifierY) : 0);
        result = 31 * result + (triggerDuration != 0.0f ? Float.floatToIntBits(triggerDuration) : 0);
        result = 31 * result + targetGroupID;
        result = 31 * result + secondGroupID;
        return result;
    }
}

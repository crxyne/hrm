package org.crayne.hrm.api.level.data.object.type.trigger.movement;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.BiTargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class RotateTrigger extends Trigger implements BiTargetTrigger {

    private int degrees;
    private int times360;
    private boolean lockObjectRotation;

    @NotNull
    private EasingType easingType;

    private float easingRate;

    private float triggerDuration;
    private int targetGroupID;
    private int centerGroupID;

    public RotateTrigger(final int objectID, final float positionX, final float positionY, final int degrees,
                         final int times360, final boolean lockObjectRotation, @NotNull final EasingType easingType,
                         final float easingRate, final float triggerDuration, final int targetGroupID, final int centerGroupID) {
        super(objectID, positionX, positionY);
        this.degrees = degrees;
        this.times360 = times360;
        this.lockObjectRotation = lockObjectRotation;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.centerGroupID = centerGroupID;
    }

    public RotateTrigger(@NotNull final LevelObject levelObject, final int degrees, final int times360, final boolean lockObjectRotation,
                         @NotNull final EasingType easingType, final float easingRate, final float triggerDuration,
                         final int targetGroupID, final int centerGroupID) {
        super(levelObject);
        this.degrees = degrees;
        this.times360 = times360;
        this.lockObjectRotation = lockObjectRotation;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.centerGroupID = centerGroupID;
    }

    public RotateTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.degrees = objectProperties.integerProperty(LevelObjectProperty.ROTATE_DEGREES);
        this.times360 = objectProperties.integerProperty(LevelObjectProperty.ROTATE_TIMES_360);
        this.lockObjectRotation = objectProperties.booleanProperty(LevelObjectProperty.LOCK_OBJECT_ROTATION);
        this.easingType = objectProperties.easingTypeProperty(LevelObjectProperty.EASING_TYPE);
        this.easingRate = objectProperties.floatProperty(LevelObjectProperty.EASING_RATE);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectProperty.DURATION);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.centerGroupID = objectProperties.integerProperty(LevelObjectProperty.SECOND_TARGET_GROUP_ID);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1346);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.ROTATE_DEGREES, degrees);
        properties.putIntProperty(LevelObjectProperty.ROTATE_TIMES_360, times360);
        properties.putBooleanProperty(LevelObjectProperty.LOCK_OBJECT_ROTATION, lockObjectRotation);
        properties.putEasingTypeProperty(LevelObjectProperty.EASING_TYPE, easingType);
        properties.putFloatProperty(LevelObjectProperty.EASING_RATE, easingRate);
        properties.putFloatProperty(LevelObjectProperty.DURATION, triggerDuration);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);
        properties.putIntProperty(LevelObjectProperty.SECOND_TARGET_GROUP_ID, centerGroupID);

        return properties;
    }

    public int degrees() {
        return degrees;
    }

    public void degrees(final int degrees) {
        this.degrees = degrees;
    }

    public int times360() {
        return times360;
    }

    public void times360(final int times360) {
        this.times360 = times360;
    }

    public boolean lockObjectRotation() {
        return lockObjectRotation;
    }

    public void lockObjectRotation(final boolean lockObjectRotation) {
        this.lockObjectRotation = lockObjectRotation;
    }

    @NotNull
    public EasingType easingType() {
        return easingType;
    }

    public void easingType(@NotNull final EasingType easingType) {
        this.easingType = easingType;
    }

    public float easingRate() {
        return easingRate;
    }

    public void easingRate(final float easingRate) {
        this.easingRate = easingRate;
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

    public int centerGroupID() {
        return centerGroupID;
    }

    public void centerGroupID(final int secondaryGroupID) {
        this.centerGroupID = secondaryGroupID;
    }

    public int secondGroupID() {
        return centerGroupID;
    }

    public void secondGroupID(final int secondaryGroupID) {
        this.centerGroupID = secondaryGroupID;
    }

    @NotNull
    public String toString() {
        return "RotateTrigger{" +
                "degrees=" + degrees +
                ", times360=" + times360 +
                ", lockObjectRotation=" + lockObjectRotation +
                ", easingType=" + easingType +
                ", easingRate=" + easingRate +
                ", triggerDuration=" + triggerDuration +
                ", targetGroupID=" + targetGroupID +
                ", centerGroupID=" + centerGroupID +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final RotateTrigger that = (RotateTrigger) o;

        if (degrees != that.degrees) return false;
        if (times360 != that.times360) return false;
        if (lockObjectRotation != that.lockObjectRotation) return false;
        if (Float.compare(that.easingRate, easingRate) != 0) return false;
        if (Float.compare(that.triggerDuration, triggerDuration) != 0) return false;
        if (targetGroupID != that.targetGroupID) return false;
        if (centerGroupID != that.centerGroupID) return false;
        return easingType == that.easingType;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + degrees;
        result = 31 * result + times360;
        result = 31 * result + (lockObjectRotation ? 1 : 0);
        result = 31 * result + easingType.hashCode();
        result = 31 * result + (easingRate != 0.0f ? Float.floatToIntBits(easingRate) : 0);
        result = 31 * result + (triggerDuration != 0.0f ? Float.floatToIntBits(triggerDuration) : 0);
        result = 31 * result + targetGroupID;
        result = 31 * result + centerGroupID;
        return result;
    }
}

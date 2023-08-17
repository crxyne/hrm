package org.crayne.gdboard.level.data.object.type.trigger.movement;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class RotateTrigger extends Trigger {

    private int degrees;
    private int times360;
    private boolean lockObjectRotation;

    @NotNull
    private EasingType easingType;

    private float easingRate;

    private float triggerDuration;
    private int targetGroupID;
    private int secondaryGroupID;

    public RotateTrigger(final int objectID, final float positionX, final float positionY, final int degrees,
                         final int times360, final boolean lockObjectRotation, @NotNull final EasingType easingType,
                         final float easingRate, final float triggerDuration, final int targetGroupID, final int secondaryGroupID) {
        super(objectID, positionX, positionY);
        this.degrees = degrees;
        this.times360 = times360;
        this.lockObjectRotation = lockObjectRotation;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.secondaryGroupID = secondaryGroupID;
    }

    public RotateTrigger(@NotNull final LevelObject levelObject, final int degrees, final int times360, final boolean lockObjectRotation,
                         @NotNull final EasingType easingType, final float easingRate, final float triggerDuration,
                         final int targetGroupID, final int secondaryGroupID) {
        super(levelObject);
        this.degrees = degrees;
        this.times360 = times360;
        this.lockObjectRotation = lockObjectRotation;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
        this.secondaryGroupID = secondaryGroupID;
    }

    public RotateTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.degrees = objectProperties.integerProperty(LevelObjectData.ROTATE_DEGREES);
        this.times360 = objectProperties.integerProperty(LevelObjectData.ROTATE_TIMES_360);
        this.lockObjectRotation = objectProperties.booleanProperty(LevelObjectData.LOCK_OBJECT_ROTATION);
        this.easingType = objectProperties.easingTypeProperty(LevelObjectData.EASING_TYPE);
        this.easingRate = objectProperties.floatProperty(LevelObjectData.EASING_RATE);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectData.DURATION);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID);
        this.secondaryGroupID = objectProperties.integerProperty(LevelObjectData.SECOND_TARGET_GROUP_ID);
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

    public int secondaryGroupID() {
        return secondaryGroupID;
    }

    public void secondaryGroupID(final int secondaryGroupID) {
        this.secondaryGroupID = secondaryGroupID;
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
                ", secondaryGroupID=" + secondaryGroupID +
                "} " + super.toString();
    }
}

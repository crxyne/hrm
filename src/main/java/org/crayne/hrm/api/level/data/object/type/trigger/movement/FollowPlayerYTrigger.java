package org.crayne.hrm.api.level.data.object.type.trigger.movement;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class FollowPlayerYTrigger extends Trigger implements TargetTrigger {

    private float followSpeed; // 90
    private float followDelay; // 91
    private float followYOffset; // 92
    private float maxFollowSpeed; // 105
    private float triggerDuration; // 10
    private int targetGroupID; // 51

    public FollowPlayerYTrigger(final int objectID, final float positionX, final float positionY,
                                final float followSpeed, final float followDelay, final float followYOffset,
                                final float maxFollowSpeed, final float triggerDuration, final int targetGroupID) {
        super(objectID, positionX, positionY);
        this.followSpeed = followSpeed;
        this.followDelay = followDelay;
        this.followYOffset = followYOffset;
        this.maxFollowSpeed = maxFollowSpeed;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
    }

    public FollowPlayerYTrigger(@NotNull final LevelObject levelObject, final float followSpeed, final float followDelay,
                                final float followYOffset, final float maxFollowSpeed, final float triggerDuration, final int targetGroupID) {
        super(levelObject);
        this.followSpeed = followSpeed;
        this.followDelay = followDelay;
        this.followYOffset = followYOffset;
        this.maxFollowSpeed = maxFollowSpeed;
        this.triggerDuration = triggerDuration;
        this.targetGroupID = targetGroupID;
    }

    public FollowPlayerYTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.followSpeed = 1.0f;
        this.triggerDuration = 0.5f;
    }

    public FollowPlayerYTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public FollowPlayerYTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.followSpeed = objectProperties.floatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_SPEED);
        this.followDelay = objectProperties.floatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_DELAY);
        this.followYOffset = objectProperties.floatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_OFFSET);
        this.maxFollowSpeed = objectProperties.floatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_MAX_SPEED);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectProperty.DURATION);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putFloatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_SPEED, followSpeed);
        properties.putFloatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_DELAY, followDelay);
        properties.putFloatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_OFFSET, followYOffset);
        properties.putFloatProperty(LevelObjectProperty.FOLLOW_PLAYER_Y_MAX_SPEED, maxFollowSpeed);
        properties.putFloatProperty(LevelObjectProperty.DURATION, triggerDuration);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);

        return properties;
    }

    public float followSpeed() {
        return followSpeed;
    }

    public void followSpeed(final float followSpeed) {
        this.followSpeed = followSpeed;
    }

    public float followDelay() {
        return followDelay;
    }

    public void followDelay(final float followDelay) {
        this.followDelay = followDelay;
    }

    public float followYOffset() {
        return followYOffset;
    }

    public void followYOffset(final float followYOffset) {
        this.followYOffset = followYOffset;
    }

    public float maxFollowSpeed() {
        return maxFollowSpeed;
    }

    public void maxFollowSpeed(final float maxFollowSpeed) {
        this.maxFollowSpeed = maxFollowSpeed;
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

    @NotNull
    public String toString() {
        return "FollowPlayerYTrigger{" +
                "followSpeed=" + followSpeed +
                ", followDelay=" + followDelay +
                ", followYOffset=" + followYOffset +
                ", maxFollowSpeed=" + maxFollowSpeed +
                ", triggerDuration=" + triggerDuration +
                ", targetGroupID=" + targetGroupID +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final FollowPlayerYTrigger that = (FollowPlayerYTrigger) o;

        if (Float.compare(that.followSpeed, followSpeed) != 0) return false;
        if (Float.compare(that.followDelay, followDelay) != 0) return false;
        if (Float.compare(that.followYOffset, followYOffset) != 0) return false;
        if (Float.compare(that.maxFollowSpeed, maxFollowSpeed) != 0) return false;
        if (Float.compare(that.triggerDuration, triggerDuration) != 0) return false;
        return targetGroupID == that.targetGroupID;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (followSpeed != 0.0f ? Float.floatToIntBits(followSpeed) : 0);
        result = 31 * result + (followDelay != 0.0f ? Float.floatToIntBits(followDelay) : 0);
        result = 31 * result + (followYOffset != 0.0f ? Float.floatToIntBits(followYOffset) : 0);
        result = 31 * result + (maxFollowSpeed != 0.0f ? Float.floatToIntBits(maxFollowSpeed) : 0);
        result = 31 * result + (triggerDuration != 0.0f ? Float.floatToIntBits(triggerDuration) : 0);
        result = 31 * result + targetGroupID;
        return result;
    }
}

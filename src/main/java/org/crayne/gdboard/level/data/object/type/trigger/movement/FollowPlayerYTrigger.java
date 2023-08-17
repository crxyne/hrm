package org.crayne.gdboard.level.data.object.type.trigger.movement;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class FollowPlayerYTrigger extends Trigger {

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
        this.followSpeed = objectProperties.floatProperty(LevelObjectData.FOLLOW_PLAYER_Y_SPEED);
        this.followDelay = objectProperties.floatProperty(LevelObjectData.FOLLOW_PLAYER_Y_DELAY);
        this.followYOffset = objectProperties.floatProperty(LevelObjectData.FOLLOW_PLAYER_Y_OFFSET);
        this.maxFollowSpeed = objectProperties.floatProperty(LevelObjectData.FOLLOW_PLAYER_Y_MAX_SPEED);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectData.DURATION);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID);
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
}

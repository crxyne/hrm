package org.crayne.gdboard.level.data.object.type.trigger;

public class MoveTrigger extends Trigger {

    private int moveOffsetX, moveOffsetY; // 28, 29
    private int easingType; // 30
    private float easingRate; // 85
    private boolean lockToPlayerX, lockToPlayerY; // 58, 59
    private boolean useMoveTarget; // 100
    private int targetPosCoordinates; // 101
    private int targetGroupID; // 51
    private int targetPositionGroupID; // 71
    private float triggerDuration; // 10

    public MoveTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int moveOffsetX, final int moveOffsetY, final int easingType,
                       final float easingRate, final boolean lockToPlayerX, final boolean lockToPlayerY, final int targetGroupID, final float triggerDuration) {
        super(spawnTriggered, multiTriggered);
        this.moveOffsetX = moveOffsetX;
        this.moveOffsetY = moveOffsetY;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.lockToPlayerX = lockToPlayerX;
        this.lockToPlayerY = lockToPlayerY;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final boolean touchTriggered, final int moveOffsetX, final int moveOffsetY, final int easingType, final float easingRate,
                       final boolean lockToPlayerX, final boolean lockToPlayerY, final int targetGroupID, final float triggerDuration) {
        super(touchTriggered);
        this.moveOffsetX = moveOffsetX;
        this.moveOffsetY = moveOffsetY;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.lockToPlayerX = lockToPlayerX;
        this.lockToPlayerY = lockToPlayerY;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final int moveOffsetX, final int moveOffsetY, final int easingType, final float easingRate, final boolean lockToPlayerX,
                       final boolean lockToPlayerY, final int targetGroupID, final float triggerDuration) {
        this.moveOffsetX = moveOffsetX;
        this.moveOffsetY = moveOffsetY;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.lockToPlayerX = lockToPlayerX;
        this.lockToPlayerY = lockToPlayerY;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int easingType, final float easingRate, final boolean useMoveTarget,
                       final int targetPosCoordinates, final int targetGroupID, final int targetPositionGroupID, final float triggerDuration) {
        super(spawnTriggered, multiTriggered);
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.useMoveTarget = useMoveTarget;
        this.targetPosCoordinates = targetPosCoordinates;
        this.targetGroupID = targetGroupID;
        this.targetPositionGroupID = targetPositionGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final boolean touchTriggered, final int easingType, final float easingRate, final boolean useMoveTarget, final int targetPosCoordinates,
                       final int targetGroupID, final int targetPositionGroupID, final float triggerDuration) {
        super(touchTriggered);
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.useMoveTarget = useMoveTarget;
        this.targetPosCoordinates = targetPosCoordinates;
        this.targetGroupID = targetGroupID;
        this.targetPositionGroupID = targetPositionGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final int easingType, final float easingRate, final boolean useMoveTarget, final int targetPosCoordinates, final int targetGroupID,
                       final int targetPositionGroupID, final float triggerDuration) {
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.useMoveTarget = useMoveTarget;
        this.targetPosCoordinates = targetPosCoordinates;
        this.targetGroupID = targetGroupID;
        this.targetPositionGroupID = targetPositionGroupID;
        this.triggerDuration = triggerDuration;
    }

    public int moveOffsetX() {
        return moveOffsetX;
    }

    public void moveOffsetX(final int moveOffsetX) {
        this.moveOffsetX = moveOffsetX;
    }

    public int moveOffsetY() {
        return moveOffsetY;
    }

    public void moveOffsetY(final int moveOffsetY) {
        this.moveOffsetY = moveOffsetY;
    }

    public int easingType() {
        return easingType;
    }

    public void easingType(final int easingType) {
        this.easingType = easingType;
    }

    public float easingRate() {
        return easingRate;
    }

    public void easingRate(final float easingRate) {
        this.easingRate = easingRate;
    }

    public boolean lockToPlayerX() {
        return lockToPlayerX;
    }

    public void lockToPlayerX(final boolean lockToPlayerX) {
        this.lockToPlayerX = lockToPlayerX;
    }

    public boolean lockToPlayerY() {
        return lockToPlayerY;
    }

    public void lockToPlayerY(final boolean lockToPlayerY) {
        this.lockToPlayerY = lockToPlayerY;
    }

    public boolean useMoveTarget() {
        return useMoveTarget;
    }

    public void useMoveTarget(final boolean useMoveTarget) {
        this.useMoveTarget = useMoveTarget;
    }

    public int targetPosCoordinates() {
        return targetPosCoordinates;
    }

    public void targetPosCoordinates(final int targetPosCoordinates) {
        this.targetPosCoordinates = targetPosCoordinates;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public int targetPositionGroupID() {
        return targetPositionGroupID;
    }

    public void targetPositionGroupID(final int targetPositionGroupID) {
        this.targetPositionGroupID = targetPositionGroupID;
    }

    public float triggerDuration() {
        return triggerDuration;
    }

    public void triggerDuration(final float triggerDuration) {
        this.triggerDuration = triggerDuration;
    }

}

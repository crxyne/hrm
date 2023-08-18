package org.crayne.gdboard.level.data.object.type.trigger.movement;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyDataType;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class MoveTrigger extends Trigger {

    private int moveOffsetX, moveOffsetY;

    @NotNull
    private EasingType easingType;

    private float easingRate;
    private boolean lockToPlayerX, lockToPlayerY;
    private boolean useMoveTarget;
    
    @NotNull
    private TargetCoordinateExclusion targetCoordinateExclusion;
    private int targetGroupID;
    private int targetPositionGroupID;
    private float triggerDuration;

    public MoveTrigger(final int objectID, final float positionX, final float positionY, final int moveOffsetX, final int moveOffsetY,
                       @NotNull final EasingType easingType, final float easingRate, final boolean lockToPlayerX, final boolean lockToPlayerY,
                       final int targetGroupID, final float triggerDuration) {
        super(objectID, positionX, positionY);
        this.moveOffsetX = moveOffsetX;
        this.moveOffsetY = moveOffsetY;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.lockToPlayerX = lockToPlayerX;
        this.lockToPlayerY = lockToPlayerY;
        this.targetGroupID = targetGroupID;
        this.targetCoordinateExclusion = TargetCoordinateExclusion.FOLLOW_BOTH;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(@NotNull final LevelObject levelObject, final int moveOffsetX, final int moveOffsetY, @NotNull final EasingType easingType,
                       final float easingRate, final boolean lockToPlayerX, final boolean lockToPlayerY,
                       final int targetGroupID, final float triggerDuration) {
        super(levelObject);
        this.moveOffsetX = moveOffsetX;
        this.moveOffsetY = moveOffsetY;
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.lockToPlayerX = lockToPlayerX;
        this.lockToPlayerY = lockToPlayerY;
        this.targetGroupID = targetGroupID;
        this.targetCoordinateExclusion = TargetCoordinateExclusion.FOLLOW_BOTH;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final int objectID, final float positionX, final float positionY, @NotNull final EasingType easingType,
                       final float easingRate, final boolean useMoveTarget, @NotNull final TargetCoordinateExclusion targetCoordinateExclusion, final int targetGroupID,
                       final int targetPositionGroupID, final float triggerDuration) {
        super(objectID, positionX, positionY);
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.useMoveTarget = useMoveTarget;
        this.targetCoordinateExclusion = targetCoordinateExclusion;
        this.targetGroupID = targetGroupID;
        this.targetPositionGroupID = targetPositionGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(@NotNull final LevelObject levelObject, @NotNull final EasingType easingType, final float easingRate,
                       final boolean useMoveTarget, @NotNull final TargetCoordinateExclusion targetCoordinateExclusion, final int targetGroupID,
                       final int targetPositionGroupID, final float triggerDuration) {
        super(levelObject);
        this.easingType = easingType;
        this.easingRate = easingRate;
        this.useMoveTarget = useMoveTarget;
        this.targetCoordinateExclusion = targetCoordinateExclusion;
        this.targetGroupID = targetGroupID;
        this.targetPositionGroupID = targetPositionGroupID;
        this.triggerDuration = triggerDuration;
    }

    public MoveTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.triggerDuration = 0.5f;
        this.easingType = EasingType.NONE;
        this.easingRate = 2.0f;
        this.targetCoordinateExclusion = TargetCoordinateExclusion.FOLLOW_BOTH;
    }

    public MoveTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.triggerDuration = 0.5f;
        this.easingType = EasingType.NONE;
        this.easingRate = 2.0f;
        this.targetCoordinateExclusion = TargetCoordinateExclusion.FOLLOW_BOTH;
    }

    public MoveTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.moveOffsetX               = objectProperties.integerProperty(LevelObjectProperty.MOVE_OFFSET_X);
        this.moveOffsetY               = objectProperties.integerProperty(LevelObjectProperty.MOVE_OFFSET_Y);
        this.easingType                = objectProperties.easingTypeProperty(LevelObjectProperty.EASING_TYPE);
        this.easingRate                = objectProperties.floatProperty(LevelObjectProperty.EASING_RATE);
        this.lockToPlayerX             = objectProperties.booleanProperty(LevelObjectProperty.LOCK_TO_PLAYER_X);
        this.lockToPlayerY             = objectProperties.booleanProperty(LevelObjectProperty.LOCK_TO_PLAYER_Y);
        this.useMoveTarget             = objectProperties.booleanProperty(LevelObjectProperty.MOVE_USE_TARGET);
        this.targetCoordinateExclusion = objectProperties.moveTargetCoordinateExclusionProperty(LevelObjectProperty.MOVE_TARGET_EXCLUSION);
        this.targetGroupID             = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.targetPositionGroupID     = objectProperties.integerProperty(LevelObjectProperty.SECOND_TARGET_GROUP_ID);
        this.triggerDuration           = objectProperties.floatProperty(LevelObjectProperty.DURATION);
    }

    public enum TargetCoordinateExclusion {
        FOLLOW_BOTH(0),
        FOLLOW_X_ONLY(1),
        FOLLOW_Y_ONLY(2);
        
        private final int id;
        
        TargetCoordinateExclusion(final int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }

        @NotNull
        public static PropertyDataType datatype() {
            return PropertyDataType.MOVE_TARGET_POS_EXCLUSION;
        }
        
        @NotNull
        public static TargetCoordinateExclusion of(final int id) {
            return switch (id) {
                case 1 -> FOLLOW_X_ONLY;
                case 2 -> FOLLOW_Y_ONLY;
                default -> FOLLOW_BOTH;
            };
        }
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

    @NotNull
    public TargetCoordinateExclusion targetCoordinateExclusion() {
        return targetCoordinateExclusion;
    }

    public void targetCoordinateExclusion(@NotNull final TargetCoordinateExclusion targetCoordinateExclusion) {
        this.targetCoordinateExclusion = targetCoordinateExclusion;
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

    @NotNull
    public String toString() {
        return "MoveTrigger{" +
                "moveOffsetX=" + moveOffsetX +
                ", moveOffsetY=" + moveOffsetY +
                ", easingType=" + easingType +
                ", easingRate=" + easingRate +
                ", lockToPlayerX=" + lockToPlayerX +
                ", lockToPlayerY=" + lockToPlayerY +
                ", useMoveTarget=" + useMoveTarget +
                ", targetCoordinateExclusion=" + targetCoordinateExclusion +
                ", targetGroupID=" + targetGroupID +
                ", targetPositionGroupID=" + targetPositionGroupID +
                ", triggerDuration=" + triggerDuration +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final MoveTrigger that = (MoveTrigger) o;

        if (moveOffsetX != that.moveOffsetX) return false;
        if (moveOffsetY != that.moveOffsetY) return false;
        if (Float.compare(that.easingRate, easingRate) != 0) return false;
        if (lockToPlayerX != that.lockToPlayerX) return false;
        if (lockToPlayerY != that.lockToPlayerY) return false;
        if (useMoveTarget != that.useMoveTarget) return false;
        if (targetGroupID != that.targetGroupID) return false;
        if (targetPositionGroupID != that.targetPositionGroupID) return false;
        if (Float.compare(that.triggerDuration, triggerDuration) != 0) return false;
        if (easingType != that.easingType) return false;
        return targetCoordinateExclusion == that.targetCoordinateExclusion;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + moveOffsetX;
        result = 31 * result + moveOffsetY;
        result = 31 * result + easingType.hashCode();
        result = 31 * result + (easingRate != +0.0f ? Float.floatToIntBits(easingRate) : 0);
        result = 31 * result + (lockToPlayerX ? 1 : 0);
        result = 31 * result + (lockToPlayerY ? 1 : 0);
        result = 31 * result + (useMoveTarget ? 1 : 0);
        result = 31 * result + targetCoordinateExclusion.hashCode();
        result = 31 * result + targetGroupID;
        result = 31 * result + targetPositionGroupID;
        result = 31 * result + (triggerDuration != +0.0f ? Float.floatToIntBits(triggerDuration) : 0);
        return result;
    }
}

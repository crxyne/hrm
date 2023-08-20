package org.crayne.hrm.api.savefile.property.data;

import org.crayne.hrm.api.level.data.color.ColorHSBModifier;
import org.crayne.hrm.api.level.data.object.ZLayer;
import org.crayne.hrm.api.level.data.object.type.trigger.general.TouchTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.InstantCountTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.pickup.PickupItemObject;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.EasingType;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.MoveTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.hrm.api.level.data.settings.start.GameMode;
import org.crayne.hrm.api.level.data.settings.start.GameSpeed;
import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public enum LevelObjectProperty {

    OBJECT_ID(1, PropertyDataType.INT, 0),
    X_POSITION(2, PropertyDataType.FLOAT, 0.0f),
    Y_POSITION(3, PropertyDataType.FLOAT, 0.0f),
    FLIPPED_HORIZ(4, PropertyDataType.BOOL, false),
    FLIPPED_VERT(5, PropertyDataType.BOOL, false),
    ROTATION(6, PropertyDataType.FLOAT, 0.0f),
    RED_COMP(7, PropertyDataType.INT, 255),
    GREEN_COMP(8, PropertyDataType.INT, 255),
    BLUE_COMP(9, PropertyDataType.INT, 255),
    DURATION(10, PropertyDataType.FLOAT, 0.5f),
    TOUCH_TRIGGERED(11, PropertyDataType.BOOL, false),
    PORTAL_SPECIAL_PROPERTY(13, PropertyDataType.BOOL, true),
    PLAYER_COLOR_1(15, PropertyDataType.BOOL, false),
    PLAYER_COLOR_2(16, PropertyDataType.BOOL, false),
    BLENDING(17, PropertyDataType.BOOL, false),
    EDITOR_L1(20, PropertyDataType.INT, 0),
    MAIN_COLOR_ID(21, PropertyDataType.INT, 0),
    SECOND_COLOR_ID(22, PropertyDataType.INT, 0),
    TARGET_COLOR_ID(23, PropertyDataType.INT, 0),
    Z_LAYER(24, PropertyDataType.Z_LAYER, ZLayer.T1),
    Z_ORDER(25, PropertyDataType.INT, 1),
    MOVE_OFFSET_X(28, PropertyDataType.INT, 0),
    MOVE_OFFSET_Y(29, PropertyDataType.INT, 0),
    EASING_TYPE(30, PropertyDataType.EASING_TYPE, EasingType.NONE),
    TEXT_BASE64(31, PropertyDataType.BASE_64_STRING, "QQ=="),
    SCALING(32, PropertyDataType.FLOAT, 1.0f),
    GROUP_PARENT(34, PropertyDataType.BOOL, false),
    OPACITY(35, PropertyDataType.FLOAT, 1.0f),
    MAIN_COLOR_HSB_CHECKED(41, PropertyDataType.BOOL, false),
    SECOND_COLOR_HSB_CHECKED(42, PropertyDataType.BOOL, false),
    MAIN_COLOR_HSB(43, PropertyDataType.HSB_MODIFIER, ColorHSBModifier.none()),
    SECOND_COLOR_HSB(44, PropertyDataType.HSB_MODIFIER, ColorHSBModifier.none()),
    PULSE_FADE_IN(45, PropertyDataType.FLOAT, 0.0f),
    PULSE_HOLD(46, PropertyDataType.FLOAT, 0.0f),
    PULSE_FADE_OUT(47, PropertyDataType.FLOAT, 0.0f),
    PULSE_MODE(48, PropertyDataType.PULSE_MODE, PulseTrigger.Mode.COLOR),
    COPIED_COLOR_HSB(49, PropertyDataType.HSB_MODIFIER, ColorHSBModifier.none()),
    COPIED_COLOR_ID(50, PropertyDataType.INT, 0),
    TARGET_GROUP_ID(51, PropertyDataType.INT, 0),
    PULSE_TARGET_TYPE(52, PropertyDataType.PULSE_TARGET, PulseTrigger.Target.CHANNEL),
    TELEPORT_Y_OFFSET(54, PropertyDataType.FLOAT, 0.0f),
    TELEPORT_EASE(55, PropertyDataType.BOOL, false),
    ACTIVATE_GROUP(56, PropertyDataType.BOOL, false),
    GROUP_IDS(57, PropertyDataType.INT_ARRAY, new ArrayList<>()),
    LOCK_TO_PLAYER_X(58, PropertyDataType.BOOL, false),
    LOCK_TO_PLAYER_Y(59, PropertyDataType.BOOL, false),
    COPY_OPACITY(60, PropertyDataType.BOOL, false),
    EDITOR_L2(61, PropertyDataType.INT, 0),
    SPAWN_TRIGGERED(62, PropertyDataType.BOOL, false),
    SPAWN_DELAY(63, PropertyDataType.FLOAT, 0.0f),
    DONT_FADE(64, PropertyDataType.BOOL, false),
    PULSE_MAIN_ONLY(65, PropertyDataType.BOOL, false),
    PULSE_DETAIL_ONLY(66, PropertyDataType.BOOL, false),
    DONT_ENTER(67, PropertyDataType.BOOL, false),
    ROTATE_DEGREES(68, PropertyDataType.INT, 0),
    ROTATE_TIMES_360(69, PropertyDataType.INT, 0),
    LOCK_OBJECT_ROTATION(70, PropertyDataType.BOOL, false),
    SECOND_TARGET_GROUP_ID(71, PropertyDataType.INT, 0),
    FOLLOW_X_MOD(72, PropertyDataType.FLOAT, 1.0f),
    FOLLOW_Y_MOD(73, PropertyDataType.FLOAT, 1.0f),
    SHAKE_STRENGTH(75, PropertyDataType.FLOAT, 0.0f),
    ANIMATION_ID(76, PropertyDataType.INT, 0),
    COUNT(77, PropertyDataType.INT, 0),
    SUBTRACT_COUNT(78, PropertyDataType.BOOL, false),
    PICKUP_MODE(79, PropertyDataType.PICKUP_ITEM_MODE, PickupItemObject.Mode.NONE),
    ITEM_OR_BLOCK_ID(80, PropertyDataType.INT, 0),
    TOUCH_HOLD_MODE(81, PropertyDataType.BOOL, false),
    TOUCH_TOGGLE_MODE(82, PropertyDataType.TOUCH_TOGGLE_MODE, TouchTrigger.ToggleMode.NONE),
    SHAKE_INTERVAL(84, PropertyDataType.FLOAT, 0.0f),
    EASING_RATE(85, PropertyDataType.FLOAT, 2.0f),
    PULSE_EXCLUSIVE(86, PropertyDataType.BOOL, false),
    MULTI_TRIGGERED(87, PropertyDataType.BOOL, false),
    INSTANT_COUNT_COMPARISON(88, PropertyDataType.INSTANT_COUNT_COMPARISON, InstantCountTrigger.Comparison.EQUALS),
    TOUCH_DUAL_MODE(89, PropertyDataType.BOOL, false),
    FOLLOW_PLAYER_Y_SPEED(90, PropertyDataType.FLOAT, 0.0f),
    FOLLOW_PLAYER_Y_DELAY(91, PropertyDataType.FLOAT, 0.0f),
    FOLLOW_PLAYER_Y_OFFSET(92, PropertyDataType.FLOAT, 0.0f),
    COLLISION_TRIGGER_ON_EXIT(93, PropertyDataType.BOOL, false),
    COLLISION_DYNAMIC_BLOCK(94, PropertyDataType.BOOL, false),
    COLLISION_SECOND_BLOCK_ID(95, PropertyDataType.INT, 0),
    DISABLE_GLOW(96, PropertyDataType.BOOL, false),
    ROTATABLE_SPEED(97, PropertyDataType.FLOAT, 0.0f),
    ROTATABLE_DISABLE(98, PropertyDataType.BOOL, false),
    ORB_MULTI_ACTIVATE(99, PropertyDataType.BOOL, false),
    MOVE_USE_TARGET(100, PropertyDataType.BOOL, false),
    MOVE_TARGET_EXCLUSION(101, PropertyDataType.MOVE_TARGET_POS_EXCLUSION, MoveTrigger.TargetCoordinateExclusion.FOLLOW_BOTH),
    SPAWN_EDITOR_DISABLE(102, PropertyDataType.BOOL, false),
    HIGH_DETAIL(103, PropertyDataType.BOOL, false),
    COUNT_MULTI_ACTIVATE(104, PropertyDataType.BOOL, false),
    FOLLOW_PLAYER_Y_MAX_SPEED(105, PropertyDataType.FLOAT, 1.0f),
    ANIMATION_RANDOMIZE_START(106, PropertyDataType.BOOL, false),
    ANIMATION_SPEED(107, PropertyDataType.FLOAT, 1.0f),
    LINKED_GROUP_ID(108, PropertyDataType.INT, 0),
    START_POS_GAME_MODE("kA2", PropertyDataType.GAME_MODE, GameMode.CUBE),
    START_POS_GAME_SPEED("kA4", PropertyDataType.GAME_SPEED, GameSpeed.NORMAL),
    START_POS_MINI_MODE("kA3", PropertyDataType.BOOL, false),
    START_POS_DUAL_MODE("kA8", PropertyDataType.BOOL, false),
    START_POS_IS_OBJECT("kA9", PropertyDataType.BOOL, false),
    START_POS_FLIP_GRAVITY("kA11", PropertyDataType.BOOL, false),
    LEVEL_IS_TWO_PLAYER_MODE("kA10", PropertyDataType.BOOL, false),
    LEVEL_COLOR_STRING("kS38", PropertyDataType.STRING, ""),
    LEVEL_SONG_OFFSET("kA13", PropertyDataType.FLOAT, 0.0f),
    LEVEL_GUIDELINE_STRING("kA14", PropertyDataType.STRING, ""),
    LEVEL_MUSIC_FADE_IN("kA15", PropertyDataType.BOOL, false),
    LEVEL_MUSIC_FADE_OUT("kA16", PropertyDataType.BOOL, false),
    LEVEL_BACKGROUND_ID("kA6", PropertyDataType.INT, 0),
    LEVEL_GROUND_ID("kA7", PropertyDataType.INT, 0),
    LEVEL_GROUND_LINE_ID("kA17", PropertyDataType.INT, 0),
    LEVEL_FONT_ID("kA18", PropertyDataType.INT, 0),

    COLOR_PROPERTY_RED("1", PropertyDataType.INT, 255),
    COLOR_PROPERTY_GREEN("2", PropertyDataType.INT, 255),
    COLOR_PROPERTY_BLUE("3", PropertyDataType.INT, 255),
    COLOR_PROPERTY_BLENDING("5", PropertyDataType.BOOL, false),
    COLOR_PROPERTY_CHANNEL_ID("6", PropertyDataType.INT, 0),
    COLOR_PROPERTY_OPACITY("7", PropertyDataType.FLOAT, 1.0f),
    COLOR_PROPERTY_COPIED_COLOR_ID("9", PropertyDataType.INT, 0),
    COLOR_PROPERTY_COPIED_COLOR_HSB("10", PropertyDataType.HSB_MODIFIER, ColorHSBModifier.none()),
    COLOR_PROPERTY_PLAYER_COLOR("4", PropertyDataType.INT, -1),
    COLOR_PROPERTY_TO_OPACITY("15", PropertyDataType.FLOAT, 1.0f),
    COLOR_PROPERTY_TOGGLE_OPACITY("8", PropertyDataType.BOOL, true),
    COLOR_PROPERTY_TO_RED("11", PropertyDataType.INT, 255),
    COLOR_PROPERTY_TO_GREEN("12", PropertyDataType.INT, 255),
    COLOR_PROPERTY_TO_BLUE("13", PropertyDataType.INT, 255),
    COLOR_PROPERTY_UNKNOWN_VALUE("18", PropertyDataType.BOOL, false),
    COLOR_PROPERTY_COPY_OPACITY("17", PropertyDataType.BOOL, false),
    ;

    @NotNull
    private final String idString;

    @NotNull
    private final PropertyDataType type;

    @NotNull
    private final Object defaultValue;

    LevelObjectProperty(final int id, @NotNull final PropertyDataType type, @NotNull final Object defaultValue) {
        this.idString = "" + id;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    LevelObjectProperty(@NotNull final String idString, @NotNull final PropertyDataType type, @NotNull final Object defaultValue) {
        this.idString = idString;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    @NotNull
    public Object defaultValue() {
        return defaultValue;
    }

    @NotNull
    public PropertyDataType datatype() {
        return type;
    }

    @NotNull
    public String idString() {
        return idString;
    }

    @Nullable
    public static LevelObjectProperty of(final int id) {
        return of("" + id);
    }

    @Nullable
    public static LevelObjectProperty of(@NotNull final String idString) {
        return Arrays.stream(values()).filter(lod -> lod.idString.equals(idString)).findAny().orElse(null);
    }

}

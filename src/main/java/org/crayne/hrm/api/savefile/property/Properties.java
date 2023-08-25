package org.crayne.hrm.api.savefile.property;

import com.jcabi.aspects.Cacheable;
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
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public record Properties(@NotNull Map<String, String> propertiesMap) {

    public Properties(@NotNull final Map<String, String> propertiesMap) {
        this.propertiesMap = new LinkedHashMap<>(propertiesMap);
    }

    public void putAll(@NotNull final HashMap<String, String> other) {
        propertiesMap.putAll(other);
    }

    public void putAll(@NotNull final Properties other) {
        propertiesMap.putAll(other.propertiesMap);
    }

    private static void checkDatatypes(@NotNull final PropertyDataType wanted, @NotNull final PropertyDataType got) {
        if (got != wanted)
            throw new UnsupportedOperationException("Property datatype mismatch (wanted: " + wanted.name().toLowerCase() + ", got: " + got.name().toLowerCase() + ")");
    }

    @Nullable
    @Cacheable
    public Integer nullableIntegerProperty(@NotNull final LevelObjectProperty propertyData) {
        return PropertyUtil.parseNullableIntegerValue(propertiesMap.get(propertyData.idString()));
    }

    @NotNull
    @Cacheable
    private <T> T property(@NotNull final LevelObjectProperty propertyData,
                           @NotNull final Supplier<PropertyDataType> dataTypeSupplier, @NotNull final Function<Integer, T> mapper,
                           @NotNull final Class<T> clazz) {
        return property(propertyData, dataTypeSupplier, mapper, clazz, clazz.cast(propertyData.defaultValue()));
    }

    @NotNull
    @Cacheable
    private <T> T property(@NotNull final LevelObjectProperty propertyData,
                           @NotNull final Supplier<PropertyDataType> dataTypeSupplier, @NotNull final Function<Integer, T> mapper,
                           @NotNull final Class<T> clazz, @NotNull final T defaultValue) {
        Properties.checkDatatypes(dataTypeSupplier.get(), propertyData.datatype());

        return Optional
                .ofNullable(nullableIntegerProperty(propertyData))
                .map(mapper)
                .orElse(defaultValue);
    }

    public void deleteProperty(@NotNull final LevelObjectProperty propertyData) {
        propertiesMap.remove(propertyData.idString());
    }

    @Cacheable
    public int integerProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.INT, propertyData.datatype());
        return PropertyUtil.parseIntValue(propertiesMap.get(propertyData.idString()), (int) propertyData.defaultValue());
    }

    public void putIntProperty(@NotNull final LevelObjectProperty propertyData, final int i) {
        putIntegerProperty(propertyData, i, true);
    }

    public void putIntProperty(@NotNull final LevelObjectProperty propertyData, final int i, final boolean alwaysPut) {
        putIntProperty(propertyData, i, true, alwaysPut);
    }

    private void putIntegerProperty(@NotNull final LevelObjectProperty propertyData, final int i, final boolean checkDatatypes) {
        putIntProperty(propertyData, i, checkDatatypes, false);
    }

    private void putIntProperty(@NotNull final LevelObjectProperty propertyData, final int i, final boolean checkDatatypes, final boolean alwaysPut) {
        if (checkDatatypes) checkDatatypes(PropertyDataType.INT, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(i)) return;

        propertiesMap.put(propertyData.idString(), "" + i);
    }

    @Cacheable
    public float floatProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.FLOAT, propertyData.datatype());
        return PropertyUtil.parseFloatValue(propertiesMap.get(propertyData.idString()), (float) propertyData.defaultValue());
    }

    public void putFloatProperty(@NotNull final LevelObjectProperty propertyData, final float f) {
        putFloatProperty(propertyData, f, false);
    }

    public void putFloatProperty(@NotNull final LevelObjectProperty propertyData, final float f, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.FLOAT, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(f)) return;
        final String str = "" + f;

        propertiesMap.put(propertyData.idString(), str.endsWith(".0") ? str.substring(0, str.length() - ".0".length()) : str);
    }

    @Cacheable
    public boolean booleanProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.BOOL, propertyData.datatype());
        return PropertyUtil.parseBooleanValue(propertiesMap.get(propertyData.idString()), (boolean) propertyData.defaultValue());
    }

    public void putBooleanProperty(@NotNull final LevelObjectProperty propertyData, final boolean b) {
        putBooleanProperty(propertyData, b, false);
    }

    public void putBooleanProperty(@NotNull final LevelObjectProperty propertyData, final boolean b, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.BOOL, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(b)) return;

        putIntegerProperty(propertyData, 1, false);
    }

    @NotNull
    @Cacheable
    public List<Integer> integerArrayProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.INT_ARRAY, propertyData.datatype());
        //noinspection unchecked
        return PropertyUtil.parseIntegerArray(propertiesMap.get(propertyData.idString()), (List<Integer>) propertyData.defaultValue());
    }

    public void putIntegerArrayProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final List<Integer> integers) {
        putIntegerArrayProperty(propertyData, integers, false);
    }

    public void putIntegerArrayProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final List<Integer> integers, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.INT_ARRAY, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(integers)) return;

        propertiesMap.put(propertyData.idString(), String.join(".", integers.stream().map(String::valueOf).toList()));
    }

    @NotNull
    @Cacheable
    public String stringBase64Property(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.BASE_64_STRING, propertyData.datatype());
        return Optional.ofNullable(propertiesMap.get(propertyData.idString())).orElse((String) propertyData.defaultValue());
    }

    public void putStringBase64Property(@NotNull final LevelObjectProperty propertyData, @NotNull final String encodedText) {
        checkDatatypes(PropertyDataType.BASE_64_STRING, propertyData.datatype());
        propertiesMap.put(propertyData.idString(), encodedText);
    }

    @NotNull
    @Cacheable
    public String stringProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.STRING, propertyData.datatype());
        return Optional.ofNullable(propertiesMap.get(propertyData.idString())).orElse((String) propertyData.defaultValue());
    }

    public void putStringProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final String text) {
        putStringProperty(propertyData, text, false);
    }

    public void putStringProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final String text, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.STRING, propertyData.datatype());
        if (!alwaysPut && text.equals(propertyData.defaultValue())) return;

        propertiesMap.put(propertyData.idString(), text);
    }

    @NotNull
    @Cacheable
    public ColorHSBModifier hsbModifierProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.HSB_MODIFIER, propertyData.datatype());
        return Optional.ofNullable(PropertyUtil.parseHSBValue(propertiesMap.get(propertyData.idString()))).orElse((ColorHSBModifier) propertyData.defaultValue());
    }

    public void putHSBModifierProperty(@NotNull final LevelObjectProperty propertyData, @Nullable final ColorHSBModifier hsbModifier) {
        putHSBModifierProperty(propertyData, hsbModifier, false);
    }

    public void putHSBModifierProperty(@NotNull final LevelObjectProperty propertyData, @Nullable final ColorHSBModifier hsbModifier, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.HSB_MODIFIER, propertyData.datatype());
        if (hsbModifier == null || (!alwaysPut && propertyData.defaultValue().equals(hsbModifier))) return;

        propertiesMap.put(propertyData.idString(), hsbModifier.toHSBString());
    }

    @NotNull
    @Cacheable
    public EasingType easingTypeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, EasingType::datatype, EasingType::of, EasingType.class);
    }

    public void putEasingTypeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final EasingType easingType) {
        putEasingTypeProperty(propertyData, easingType, false);
    }

    public void putEasingTypeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final EasingType easingType, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.EASING_TYPE, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(easingType)) return;

        putIntegerProperty(propertyData, easingType.id(), false);
    }

    @NotNull
    @Cacheable
    public PulseTrigger.Mode pulseModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, PulseTrigger.Mode::datatype, PulseTrigger.Mode::of, PulseTrigger.Mode.class);
    }

    public void putPulseModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final PulseTrigger.Mode mode) {
        putPulseModeProperty(propertyData, mode, false);
    }

    public void putPulseModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final PulseTrigger.Mode mode, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.PULSE_MODE, propertyData.datatype());
        if (propertyData.defaultValue().equals(mode)) return;

        putIntegerProperty(propertyData, mode.id(), false);
    }

    @NotNull
    @Cacheable
    public PulseTrigger.Target pulseTargetProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, PulseTrigger.Target::datatype, PulseTrigger.Target::of, PulseTrigger.Target.class);
    }

    public void putPulseTargetProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final PulseTrigger.Target target) {
        putPulseTargetProperty(propertyData, target, false);
    }

    public void putPulseTargetProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final PulseTrigger.Target target, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.PULSE_TARGET, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(target)) return;

        putIntegerProperty(propertyData, target.id(), false);
    }

    @NotNull
    @Cacheable
    public PickupItemObject.Mode pickupItemModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, PickupItemObject.Mode::datatype, PickupItemObject.Mode::of, PickupItemObject.Mode.class);
    }

    public void putPickupItemModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final PickupItemObject.Mode mode) {
        putPickupItemModeProperty(propertyData, mode, false);
    }

    public void putPickupItemModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final PickupItemObject.Mode mode, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.PICKUP_ITEM_MODE, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(mode)) return;

        putIntegerProperty(propertyData, mode.id(), false);
    }

    @NotNull
    @Cacheable
    public TouchTrigger.ToggleMode touchToggleModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, TouchTrigger.ToggleMode::datatype,
                TouchTrigger.ToggleMode::of, TouchTrigger.ToggleMode.class);
    }

    public void putTouchToggleModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final TouchTrigger.ToggleMode toggleMode) {
        putTouchToggleModeProperty(propertyData, toggleMode, false);
    }

    public void putTouchToggleModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final TouchTrigger.ToggleMode toggleMode, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.TOUCH_TOGGLE_MODE, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(toggleMode)) return;

        putIntegerProperty(propertyData, toggleMode.id(), false);
    }

    @NotNull
    @Cacheable
    public InstantCountTrigger.Comparison instantCountComparisonProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, InstantCountTrigger.Comparison::datatype,
                InstantCountTrigger.Comparison::of, InstantCountTrigger.Comparison.class);
    }

    public void putInstantCountComparisonProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final InstantCountTrigger.Comparison comparison) {
        putInstantCountComparisonProperty(propertyData, comparison, false);
    }

    public void putInstantCountComparisonProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final InstantCountTrigger.Comparison comparison, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.INSTANT_COUNT_COMPARISON, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(comparison)) return;

        putIntegerProperty(propertyData, comparison.id(), false);
    }

    @NotNull
    @Cacheable
    public MoveTrigger.TargetCoordinateExclusion moveTargetCoordinateExclusionProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, MoveTrigger.TargetCoordinateExclusion::datatype,
                MoveTrigger.TargetCoordinateExclusion::of, MoveTrigger.TargetCoordinateExclusion.class);
    }

    public void putMoveTargetExclusionProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final MoveTrigger.TargetCoordinateExclusion targetCoordinateExclusion) {
        putMoveTargetExclusionProperty(propertyData, targetCoordinateExclusion, false);
    }

    public void putMoveTargetExclusionProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final MoveTrigger.TargetCoordinateExclusion targetCoordinateExclusion, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.MOVE_TARGET_POS_EXCLUSION, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(targetCoordinateExclusion)) return;

        putIntegerProperty(propertyData, targetCoordinateExclusion.id(), false);
    }

    @NotNull
    @Cacheable
    public ZLayer zLayerProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, ZLayer::datatype, ZLayer::of, ZLayer.class);
    }

    public void putZLayerProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final ZLayer zLayer) {
        checkDatatypes(PropertyDataType.Z_LAYER, propertyData.datatype());
        putIntegerProperty(propertyData, zLayer.id(), false);
    }

    @NotNull
    @Cacheable
    public GameMode gameModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, GameMode::datatype, GameMode::of, GameMode.class);
    }

    public void putGameModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final GameMode gameMode) {
        putGameModeProperty(propertyData, gameMode, false);
    }

    public void putGameModeProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final GameMode gameMode, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.GAME_MODE, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(gameMode)) return;

        putIntegerProperty(propertyData, gameMode.id(), false);
    }

    @NotNull
    @Cacheable
    public GameSpeed gameSpeedProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, GameSpeed::datatype, GameSpeed::of, GameSpeed.class);
    }

    public void putGameSpeedProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final GameSpeed speed) {
        putGameSpeedProperty(propertyData, speed, false);
    }

    public void putGameSpeedProperty(@NotNull final LevelObjectProperty propertyData, @NotNull final GameSpeed speed, final boolean alwaysPut) {
        checkDatatypes(PropertyDataType.GAME_SPEED, propertyData.datatype());
        if (!alwaysPut && propertyData.defaultValue().equals(speed)) return;

        putIntegerProperty(propertyData, speed.id(), false);
    }

    @NotNull
    @Cacheable
    public ZLayer zLayerProperty(final int objectID) {
        return property(LevelObjectProperty.Z_LAYER, ZLayer::datatype, ZLayer::of, ZLayer.class, (ZLayer) LevelObjectProperty.Z_LAYER.defaultValue());
    }

    public void putZLayerProperty(final int objectID, @NotNull final ZLayer zLayer) {
        putZLayerProperty(objectID, zLayer, false);
    }

    public void putZLayerProperty(final int objectID, @NotNull final ZLayer zLayer, final boolean alwaysPut) {
        if (!alwaysPut && LevelObjectProperty.Z_LAYER.defaultValue().equals(zLayer)) return;
        putIntegerProperty(LevelObjectProperty.Z_LAYER, zLayer.id(), false);
    }

    @Cacheable
    public int zOrderProperty(final int objectID) {
        return PropertyUtil.parseIntValue(propertiesMap.get(LevelObjectProperty.Z_ORDER.idString()), (int) LevelObjectProperty.Z_ORDER.defaultValue());
    }

    public void putZOrderProperty(final int objectID, final int zOrder) {
        putZOrderProperty(objectID, zOrder, false);
    }

    public void putZOrderProperty(final int objectID, final int zOrder, final boolean alwaysPut) {
        if (!alwaysPut && LevelObjectProperty.Z_ORDER.defaultValue().equals(zOrder)) return;
        putIntegerProperty(LevelObjectProperty.Z_ORDER, zOrder, false);
    }

    @NotNull
    public String toString() {
        return propertiesMap.toString();
    }

}

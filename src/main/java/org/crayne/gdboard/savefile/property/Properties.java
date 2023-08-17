package org.crayne.gdboard.savefile.property;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
import org.crayne.gdboard.level.data.object.ObjectID;
import org.crayne.gdboard.level.data.object.ZLayer;
import org.crayne.gdboard.level.data.object.type.trigger.general.TouchTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.InstantCountTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.pickup.PickupItemObject;
import org.crayne.gdboard.level.data.object.type.trigger.movement.EasingType;
import org.crayne.gdboard.level.data.object.type.trigger.movement.MoveTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.gdboard.level.data.settings.start.GameMode;
import org.crayne.gdboard.level.data.settings.start.GameSpeed;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.crayne.gdboard.savefile.property.data.PropertyData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public record Properties(@NotNull Map<String, String> propertiesMap) {

    public Properties(@NotNull final Map<String, String> propertiesMap) {
        this.propertiesMap = new HashMap<>(propertiesMap);
    }

    private static void checkDatatypes(@NotNull final PropertyDataType wanted, @NotNull final PropertyDataType got) {
        if (got != wanted)
            throw new UnsupportedOperationException("Property datatype mismatch (wanted: " + wanted.name().toLowerCase() + ", got: " + got.name().toLowerCase() + ")");
    }

    @Nullable
    public Integer nullableIntegerProperty(@NotNull final PropertyData propertyData) {
        return PropertyUtil.parseNullableIntegerValue(propertiesMap.get(propertyData.idString()));
    }

    @NotNull
    private <T> T property(@NotNull final PropertyData propertyData,
                           @NotNull final Supplier<PropertyDataType> dataTypeSupplier, @NotNull final Function<Integer, T> mapper,
                           @NotNull final Class<T> clazz) {
        return property(propertyData, dataTypeSupplier, mapper, clazz, clazz.cast(propertyData.defaultValue()));
    }

    @NotNull
    private <T> T property(@NotNull final PropertyData propertyData,
                           @NotNull final Supplier<PropertyDataType> dataTypeSupplier, @NotNull final Function<Integer, T> mapper,
                           @NotNull final Class<T> clazz, @NotNull final T defaultValue) {
        Properties.checkDatatypes(dataTypeSupplier.get(), propertyData.datatype());

        return Optional
                .ofNullable(nullableIntegerProperty(propertyData))
                .map(mapper)
                .orElse(defaultValue);
    }

    public int integerProperty(@NotNull final PropertyData propertyData) {
        checkDatatypes(PropertyDataType.INT, propertyData.datatype());
        return PropertyUtil.parseIntValue(propertiesMap.get(propertyData.idString()), (int) propertyData.defaultValue());
    }

    public float floatProperty(@NotNull final PropertyData propertyData) {
        checkDatatypes(PropertyDataType.FLOAT, propertyData.datatype());
        return PropertyUtil.parseFloatValue(propertiesMap.get(propertyData.idString()), (float) propertyData.defaultValue());
    }

    public boolean booleanProperty(@NotNull final PropertyData propertyData) {
        checkDatatypes(PropertyDataType.BOOL, propertyData.datatype());
        return PropertyUtil.parseBooleanValue(propertiesMap.get(propertyData.idString()), (boolean) propertyData.defaultValue());
    }

    @NotNull
    public List<Integer> integerArrayProperty(@NotNull final PropertyData propertyData) {
        checkDatatypes(PropertyDataType.INT_ARRAY, propertyData.datatype());
        return PropertyUtil.parseIntegerArray(propertiesMap.get(propertyData.idString()), (int[]) propertyData.defaultValue());
    }

    @NotNull
    public String stringBase64Property(@NotNull final PropertyData propertyData) {
        checkDatatypes(PropertyDataType.BASE_64_STRING, propertyData.datatype());
        return Optional.ofNullable(propertiesMap.get(propertyData.idString())).orElse((String) propertyData.defaultValue());
    }

    @NotNull
    public ColorHSBModifier hsbModifierProperty(@NotNull final PropertyData propertyData) {
        checkDatatypes(PropertyDataType.HSB_MODIFIER, propertyData.datatype());
        return Optional.ofNullable(PropertyUtil.parseHSBValue(propertiesMap.get(propertyData.idString()))).orElse((ColorHSBModifier) propertyData.defaultValue());
    }

    @NotNull
    public EasingType easingTypeProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, EasingType::datatype, EasingType::of, EasingType.class);
    }

    @NotNull
    public PulseTrigger.Mode pulseModeProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, PulseTrigger.Mode::datatype, PulseTrigger.Mode::of, PulseTrigger.Mode.class);
    }

    @NotNull
    public PulseTrigger.Target pulseTargetProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, PulseTrigger.Target::datatype, PulseTrigger.Target::of, PulseTrigger.Target.class);
    }

    @NotNull
    public PickupItemObject.Mode pickupItemModeProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, PickupItemObject.Mode::datatype, PickupItemObject.Mode::of, PickupItemObject.Mode.class);
    }

    @NotNull
    public TouchTrigger.ToggleMode touchToggleModeProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, TouchTrigger.ToggleMode::datatype,
                TouchTrigger.ToggleMode::of, TouchTrigger.ToggleMode.class);
    }

    @NotNull
    public InstantCountTrigger.Comparison instantCountComparisonProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, InstantCountTrigger.Comparison::datatype,
                InstantCountTrigger.Comparison::of, InstantCountTrigger.Comparison.class);
    }

    @NotNull
    public MoveTrigger.TargetCoordinateExclusion moveTargetCoordinateExclusionProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, MoveTrigger.TargetCoordinateExclusion::datatype,
                MoveTrigger.TargetCoordinateExclusion::of, MoveTrigger.TargetCoordinateExclusion.class);
    }

    @NotNull
    public ZLayer zLayerProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, ZLayer::datatype, ZLayer::of, ZLayer.class);
    }

    @NotNull
    public GameMode gameModeProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, GameMode::datatype, GameMode::of, GameMode.class);
    }

    @NotNull
    public GameSpeed gameSpeedProperty(@NotNull final PropertyData propertyData) {
        return property(propertyData, GameSpeed::datatype, GameSpeed::of, GameSpeed.class);
    }

    @NotNull
    public ZLayer zLayerProperty(final int objectID) {
        return property(LevelObjectData.Z_LAYER, ZLayer::datatype, ZLayer::of, ZLayer.class, ObjectID.defaultZLayerOfObjectID(objectID));
    }

    public int zOrderProperty(final int objectID) {
        return PropertyUtil.parseIntValue(propertiesMap.get(LevelObjectData.Z_ORDER.idString()), ObjectID.defaultZOrderOfObjectID(objectID));
    }

    @NotNull
    public String toString() {
        return propertiesMap.toString();
    }

}

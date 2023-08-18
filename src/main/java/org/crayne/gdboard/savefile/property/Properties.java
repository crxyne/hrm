package org.crayne.gdboard.savefile.property;

import com.jcabi.aspects.Cacheable;
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
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
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

    @Cacheable
    public int integerProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.INT, propertyData.datatype());
        return PropertyUtil.parseIntValue(propertiesMap.get(propertyData.idString()), (int) propertyData.defaultValue());
    }

    @Cacheable
    public float floatProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.FLOAT, propertyData.datatype());
        return PropertyUtil.parseFloatValue(propertiesMap.get(propertyData.idString()), (float) propertyData.defaultValue());
    }

    @Cacheable
    public boolean booleanProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.BOOL, propertyData.datatype());
        return PropertyUtil.parseBooleanValue(propertiesMap.get(propertyData.idString()), (boolean) propertyData.defaultValue());
    }

    @NotNull
    @Cacheable
    public List<Integer> integerArrayProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.INT_ARRAY, propertyData.datatype());
        return PropertyUtil.parseIntegerArray(propertiesMap.get(propertyData.idString()), (int[]) propertyData.defaultValue());
    }

    @NotNull
    @Cacheable
    public String stringBase64Property(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.BASE_64_STRING, propertyData.datatype());
        return Optional.ofNullable(propertiesMap.get(propertyData.idString())).orElse((String) propertyData.defaultValue());
    }

    @NotNull
    @Cacheable
    public ColorHSBModifier hsbModifierProperty(@NotNull final LevelObjectProperty propertyData) {
        checkDatatypes(PropertyDataType.HSB_MODIFIER, propertyData.datatype());
        return Optional.ofNullable(PropertyUtil.parseHSBValue(propertiesMap.get(propertyData.idString()))).orElse((ColorHSBModifier) propertyData.defaultValue());
    }

    @NotNull
    @Cacheable
    public EasingType easingTypeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, EasingType::datatype, EasingType::of, EasingType.class);
    }

    @NotNull
    @Cacheable
    public PulseTrigger.Mode pulseModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, PulseTrigger.Mode::datatype, PulseTrigger.Mode::of, PulseTrigger.Mode.class);
    }

    @NotNull
    @Cacheable
    public PulseTrigger.Target pulseTargetProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, PulseTrigger.Target::datatype, PulseTrigger.Target::of, PulseTrigger.Target.class);
    }

    @NotNull
    @Cacheable
    public PickupItemObject.Mode pickupItemModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, PickupItemObject.Mode::datatype, PickupItemObject.Mode::of, PickupItemObject.Mode.class);
    }

    @NotNull
    @Cacheable
    public TouchTrigger.ToggleMode touchToggleModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, TouchTrigger.ToggleMode::datatype,
                TouchTrigger.ToggleMode::of, TouchTrigger.ToggleMode.class);
    }

    @NotNull
    @Cacheable
    public InstantCountTrigger.Comparison instantCountComparisonProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, InstantCountTrigger.Comparison::datatype,
                InstantCountTrigger.Comparison::of, InstantCountTrigger.Comparison.class);
    }

    @NotNull
    @Cacheable
    public MoveTrigger.TargetCoordinateExclusion moveTargetCoordinateExclusionProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, MoveTrigger.TargetCoordinateExclusion::datatype,
                MoveTrigger.TargetCoordinateExclusion::of, MoveTrigger.TargetCoordinateExclusion.class);
    }

    @NotNull
    @Cacheable
    public ZLayer zLayerProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, ZLayer::datatype, ZLayer::of, ZLayer.class);
    }

    @NotNull
    @Cacheable
    public GameMode gameModeProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, GameMode::datatype, GameMode::of, GameMode.class);
    }

    @NotNull
    @Cacheable
    public GameSpeed gameSpeedProperty(@NotNull final LevelObjectProperty propertyData) {
        return property(propertyData, GameSpeed::datatype, GameSpeed::of, GameSpeed.class);
    }

    @NotNull
    @Cacheable
    public ZLayer zLayerProperty(final int objectID) {
        return property(LevelObjectProperty.Z_LAYER, ZLayer::datatype, ZLayer::of, ZLayer.class, ObjectID.defaultZLayerOfObjectID(objectID));
    }

    @Cacheable
    public int zOrderProperty(final int objectID) {
        return PropertyUtil.parseIntValue(propertiesMap.get(LevelObjectProperty.Z_ORDER.idString()), ObjectID.defaultZOrderOfObjectID(objectID));
    }

    @NotNull
    public String toString() {
        return propertiesMap.toString();
    }

}

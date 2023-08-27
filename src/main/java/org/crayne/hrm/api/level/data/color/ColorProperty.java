package org.crayne.hrm.api.level.data.color;

import org.crayne.hrm.api.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyUtil;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("unused")
public class ColorProperty {

    private int red, green, blue;
    private boolean blending;
    private int channelIndex;
    private float opacity;
    private int copiedColorChannelIndex;
    private boolean copyOpacity;

    @Nullable
    private ColorHSBModifier copiedColorHSBModifier;

    public ColorProperty(final int channelIndex, final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.channelIndex = channelIndex;
        this.opacity = 1;
    }

    public ColorProperty(final int channelIndex, final int red, final int green, final int blue, final boolean blending, final float opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.blending = blending;
        this.opacity = opacity;
        this.channelIndex = channelIndex;
    }

    public ColorProperty(final int channelIndex, final int copiedColorChannelIndex, final boolean copyOpacity, @Nullable final ColorHSBModifier copiedColorHSBModifier) {
        this.channelIndex = channelIndex;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
    }

    public ColorProperty(@NotNull final String colorString) {
        final Properties colorProperties = new Properties(PropertyUtil.decodeProperties(colorString, "_"));

        this.red                     = colorProperties.integerProperty(LevelObjectProperty.COLOR_PROPERTY_RED);
        this.green                   = colorProperties.integerProperty(LevelObjectProperty.COLOR_PROPERTY_GREEN);
        this.blue                    = colorProperties.integerProperty(LevelObjectProperty.COLOR_PROPERTY_BLUE);
        this.blending                = colorProperties.booleanProperty(LevelObjectProperty.COLOR_PROPERTY_BLENDING);
        this.channelIndex            = colorProperties.integerProperty(LevelObjectProperty.COLOR_PROPERTY_CHANNEL_ID);
        this.opacity                 = colorProperties.floatProperty(LevelObjectProperty.COLOR_PROPERTY_OPACITY);
        this.copiedColorChannelIndex = colorProperties.integerProperty(LevelObjectProperty.COLOR_PROPERTY_COPIED_COLOR_ID);
        this.copiedColorHSBModifier  = colorProperties.hsbModifierProperty(LevelObjectProperty.COLOR_PROPERTY_COPIED_COLOR_HSB);
        this.copyOpacity             = colorProperties.booleanProperty(LevelObjectProperty.COLOR_PROPERTY_COPY_OPACITY);
    }

    @NotNull
    public String createColorString() {
        final Properties colorProperties = new Properties(new HashMap<>());
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_RED, red, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_GREEN, green, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_BLUE, blue, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_TO_RED, 255, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_TO_GREEN, 255, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_TO_BLUE, 255, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_PLAYER_COLOR, -1, true);
        colorProperties.putFloatProperty(LevelObjectProperty.COLOR_PROPERTY_TO_OPACITY, 1.0f, true);
        colorProperties.putBooleanProperty(LevelObjectProperty.COLOR_PROPERTY_TOGGLE_OPACITY, true, true);
        colorProperties.putBooleanProperty(LevelObjectProperty.COLOR_PROPERTY_UNKNOWN_VALUE, true, true);

        colorProperties.putBooleanProperty(LevelObjectProperty.COLOR_PROPERTY_BLENDING, blending);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_CHANNEL_ID, channelIndex);
        colorProperties.putFloatProperty(LevelObjectProperty.COLOR_PROPERTY_OPACITY, opacity, true);
        colorProperties.putIntProperty(LevelObjectProperty.COLOR_PROPERTY_COPIED_COLOR_ID, copiedColorChannelIndex);
        colorProperties.putHSBModifierProperty(LevelObjectProperty.COLOR_PROPERTY_COPIED_COLOR_HSB, copiedColorHSBModifier);
        colorProperties.putBooleanProperty(LevelObjectProperty.COLOR_PROPERTY_COPY_OPACITY, copyOpacity);

        return PropertyUtil.encodeProperties(colorProperties.propertiesMap(), "_");
    }

    public ColorProperty(@NotNull final Properties triggerProperties, final boolean pulseTrigger) {
        final PulseTrigger.Target targetType = triggerProperties.pulseTargetProperty(LevelObjectProperty.PULSE_TARGET_TYPE);

        this.red                     = triggerProperties.integerProperty(LevelObjectProperty.RED_COMP);
        this.green                   = triggerProperties.integerProperty(LevelObjectProperty.GREEN_COMP);
        this.blue                    = triggerProperties.integerProperty(LevelObjectProperty.BLUE_COMP);
        this.blending                = triggerProperties.booleanProperty(LevelObjectProperty.BLENDING);
        this.opacity                 = triggerProperties.floatProperty(LevelObjectProperty.OPACITY);
        this.channelIndex            = pulseTrigger && targetType == PulseTrigger.Target.GROUP
                                        ? triggerProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID)
                                        : triggerProperties.integerProperty(LevelObjectProperty.TARGET_COLOR_ID);

        this.copiedColorHSBModifier  = triggerProperties.hsbModifierProperty(LevelObjectProperty.COPIED_COLOR_HSB);
        this.copiedColorChannelIndex = triggerProperties.integerProperty(LevelObjectProperty.COPIED_COLOR_ID);
        this.copyOpacity             = triggerProperties.booleanProperty(LevelObjectProperty.COPY_OPACITY);
    }

    @NotNull
    public Properties createProperties(final boolean pulseTrigger, @Nullable final PulseTrigger.Target targetType) {
        final Properties properties = new Properties(new HashMap<>());
        if (this.equals(none())) return properties;

        properties.putIntProperty(LevelObjectProperty.RED_COMP, red, true);
        properties.putIntProperty(LevelObjectProperty.GREEN_COMP, green, true);
        properties.putIntProperty(LevelObjectProperty.BLUE_COMP, blue, true);
        properties.putBooleanProperty(LevelObjectProperty.BLENDING, blending);
        properties.putFloatProperty(LevelObjectProperty.OPACITY, opacity);
        properties.putIntProperty(targetType == PulseTrigger.Target.GROUP
                ? LevelObjectProperty.TARGET_GROUP_ID
                : LevelObjectProperty.TARGET_COLOR_ID, channelIndex);

        properties.putHSBModifierProperty(LevelObjectProperty.COPIED_COLOR_HSB, copiedColorHSBModifier);
        properties.putIntProperty(LevelObjectProperty.COPIED_COLOR_ID, copiedColorChannelIndex);
        properties.putBooleanProperty(LevelObjectProperty.COPY_OPACITY, copyOpacity);

        return properties;
    }

    @NotNull
    public static ColorProperty none(final int channelIndex) {
        return new ColorProperty(channelIndex, 255, 255, 255);
    }

    @NotNull
    public static ColorProperty none() {
        return none(0);
    }

    public boolean defaultColor() {
        return switch (channelIndex) {
            case 1001, 1009 -> red == 0   && green == 102 && blue == 255 && !blending && opacity == 1.0f;
            case 1002       -> red == 255 && green == 255 && blue == 255 && blending  && opacity == 1.0f;
            case 1000       -> red == 40  && green == 125 && blue == 255 && !blending && opacity == 1.0f;
            case 1005, 1006 -> blending;
            default         -> red == 255 && green == 255 && blue == 255 && !blending && opacity == 1.0f;
        };
    }

    public int red() {
        return red;
    }

    public void red(final int red) {
        this.red = red;
    }

    public int green() {
        return green;
    }

    public void green(final int green) {
        this.green = green;
    }

    public int blue() {
        return blue;
    }

    public void blue(final int blue) {
        this.blue = blue;
    }

    public boolean blending() {
        return blending;
    }

    public void blending(final boolean blending) {
        this.blending = blending;
    }

    public int channelIndex() {
        return channelIndex;
    }

    public void channelIndex(final int channelIndex) {
        this.channelIndex = channelIndex;
    }

    public float opacity() {
        return opacity;
    }

    public void opacity(final float opacity) {
        this.opacity = opacity;
    }

    public int copiedColorChannelIndex() {
        return copiedColorChannelIndex;
    }

    public void copiedColorChannelIndex(final int copiedColorChannelIndex) {
        this.copiedColorChannelIndex = copiedColorChannelIndex;
    }

    public boolean copyOpacity() {
        return copyOpacity;
    }

    public void copyOpacity(final boolean copyOpacity) {
        this.copyOpacity = copyOpacity;
    }

    @NotNull
    public ColorHSBModifier copiedColorHSBModifier() {
        return copiedColorHSBModifier == null ? ColorHSBModifier.none() : copiedColorHSBModifier;
    }

    public void copiedColorHSBModifier(final ColorHSBModifier copiedColorHSBModifier) {
        this.copiedColorHSBModifier = copiedColorHSBModifier;
    }

    public boolean copyColorMode() {
        return copiedColorChannelIndex != 0;
    }

    @NotNull
    public String toString() {
        if (copyColorMode()) return "ColorProperty(copying){" +
                "channelIndex=" + channelIndex +
                ", blending=" + blending +
                ", opacity=" + opacity +
                ", copiedColorChannelIndex=" + copiedColorChannelIndex +
                ", copyOpacity=" + copyOpacity +
                ", copiedColorHSBModifier=" + copiedColorHSBModifier +
                '}';

        return "ColorProperty(standalone){" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", blending=" + blending +
                ", channelIndex=" + channelIndex +
                ", opacity=" + opacity +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ColorProperty that = (ColorProperty) o;

        if (red != that.red) return false;
        if (green != that.green) return false;
        if (blue != that.blue) return false;
        if (blending != that.blending) return false;
        if (channelIndex != that.channelIndex) return false;
        if (Float.compare(that.opacity, opacity) != 0) return false;
        if (copiedColorChannelIndex != that.copiedColorChannelIndex) return false;
        if (copyOpacity != that.copyOpacity) return false;
        return Objects.equals(copiedColorHSBModifier, that.copiedColorHSBModifier);
    }

    public int hashCode() {
        int result = red;
        result = 31 * result + green;
        result = 31 * result + blue;
        result = 31 * result + (blending ? 1 : 0);
        result = 31 * result + channelIndex;
        result = 31 * result + (opacity != 0.0f ? Float.floatToIntBits(opacity) : 0);
        result = 31 * result + copiedColorChannelIndex;
        result = 31 * result + (copyOpacity ? 1 : 0);
        result = 31 * result + (copiedColorHSBModifier != null ? copiedColorHSBModifier.hashCode() : 0);
        return result;
    }
}

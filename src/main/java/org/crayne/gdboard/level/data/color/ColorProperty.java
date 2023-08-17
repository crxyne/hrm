package org.crayne.gdboard.level.data.color;

import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyUtil;
import org.crayne.gdboard.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

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

    public static final int CHANNEL_ID_NOT_FOUND = -1;

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
        final Map<String, String> colorProperties = PropertyUtil.decodeProperties(colorString, "_");

        this.red                     = PropertyUtil.parseIntValue(colorProperties.get("1"), 255);
        this.green                   = PropertyUtil.parseIntValue(colorProperties.get("2"), 255);
        this.blue                    = PropertyUtil.parseIntValue(colorProperties.get("3"), 255);
        this.blending                = PropertyUtil.parseBooleanValue(colorProperties.get("5"));
        this.channelIndex            = PropertyUtil.parseIntValue(colorProperties.get("6"), CHANNEL_ID_NOT_FOUND);
        this.opacity                 = PropertyUtil.parseFloatValue(colorProperties.get("7"), 1);
        this.copiedColorChannelIndex = PropertyUtil.parseIntValue(colorProperties.get("9"), CHANNEL_ID_NOT_FOUND);
        this.copiedColorHSBModifier  = PropertyUtil.parseHSBValue(colorProperties.get("10"));
        this.copyOpacity             = PropertyUtil.parseBooleanValue(colorProperties.get("17"));
    }

    public ColorProperty(@NotNull final Properties triggerProperties, final boolean pulseTrigger) {
        final PulseTrigger.Target targetType = triggerProperties.pulseTargetProperty(LevelObjectData.PULSE_TARGET_TYPE);

        this.red                     = triggerProperties.integerProperty(LevelObjectData.RED_COMP);
        this.green                   = triggerProperties.integerProperty(LevelObjectData.GREEN_COMP);
        this.blue                    = triggerProperties.integerProperty(LevelObjectData.BLUE_COMP);
        this.blending                = triggerProperties.booleanProperty(LevelObjectData.BLENDING);
        this.opacity                 = triggerProperties.floatProperty(LevelObjectData.OPACITY);
        this.channelIndex            = pulseTrigger && targetType == PulseTrigger.Target.CHANNEL
                                        ? triggerProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID)
                                        : triggerProperties.integerProperty(LevelObjectData.TARGET_COLOR_ID);

        this.copiedColorHSBModifier  = triggerProperties.hsbModifierProperty(LevelObjectData.COPIED_COLOR_HSB);
        this.copiedColorChannelIndex = triggerProperties.integerProperty(LevelObjectData.COPIED_COLOR_ID);
        this.copyOpacity             = triggerProperties.booleanProperty(LevelObjectData.COPY_OPACITY);
    }

    @NotNull
    public static ColorProperty none(final int channelIndex) {
        return new ColorProperty(channelIndex, 255, 255, 255);
    }

    @NotNull
    public static ColorProperty none() {
        return none(CHANNEL_ID_NOT_FOUND);
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
        return copiedColorChannelIndex != CHANNEL_ID_NOT_FOUND;
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

}

package org.crayne.gdboard.level.data;

import org.crayne.gdboard.decrypt.PropertyDecodeUtil;
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
        final Map<String, String> colorProperties = PropertyDecodeUtil.decodeProperties(colorString, "_");

        this.red                     = PropertyDecodeUtil.parseIntValue(colorProperties.get("1"), 255);
        this.green                   = PropertyDecodeUtil.parseIntValue(colorProperties.get("2"), 255);
        this.blue                    = PropertyDecodeUtil.parseIntValue(colorProperties.get("3"), 255);
        this.blending                = PropertyDecodeUtil.parseBooleanValue(colorProperties.get("5"));
        this.channelIndex            = PropertyDecodeUtil.parseIntValue(colorProperties.get("6"), CHANNEL_ID_NOT_FOUND);
        this.opacity                 = PropertyDecodeUtil.parseFloatValue(colorProperties.get("7"), 0);
        this.copiedColorChannelIndex = PropertyDecodeUtil.parseIntValue(colorProperties.get("9"), CHANNEL_ID_NOT_FOUND);
        this.copiedColorHSBModifier  = PropertyDecodeUtil.parseHSBValue(colorProperties.get("10"));
        this.copyOpacity             = PropertyDecodeUtil.parseBooleanValue(colorProperties.get("17"));
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

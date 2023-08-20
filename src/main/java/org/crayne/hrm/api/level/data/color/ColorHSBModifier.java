package org.crayne.hrm.api.level.data.color;

import org.crayne.hrm.api.savefile.property.PropertyUtil;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ColorHSBModifier {

    private float hueModifier, saturationModifier, brightnessModifier;
    private boolean saturationAdditionMode, brightnessAdditionMode;

    public ColorHSBModifier(final float hueModifier, final float saturationModifier, final float brightnessModifier, boolean saturationAdditionMode, boolean brightnessAdditionMode) {
        this.hueModifier = hueModifier;
        this.saturationModifier = saturationModifier;
        this.brightnessModifier = brightnessModifier;
        this.saturationAdditionMode = saturationAdditionMode;
        this.brightnessAdditionMode = brightnessAdditionMode;
    }

    @NotNull
    public static ColorHSBModifier none() {
        return new ColorHSBModifier(0, 0, 0, false, false);
    }

    public ColorHSBModifier(@NotNull final String hsbString) {
        final String[] split = hsbString.split("a");
        if (split.length != 5) throw new IllegalArgumentException("Invalid hsb string: " + hsbString);

        this.hueModifier            = PropertyUtil.tryParseFloat(split[0], 0);
        this.saturationModifier     = PropertyUtil.tryParseFloat(split[1], 0);
        this.brightnessModifier     = PropertyUtil.tryParseFloat(split[2], 0);
        this.saturationAdditionMode = PropertyUtil.parseBooleanValue(split[3]);
        this.brightnessAdditionMode = PropertyUtil.parseBooleanValue(split[4]);
    }

    @NotNull
    public String toHSBString() {
        return String.join("a", "" + hueModifier, "" + saturationModifier,
                "" + brightnessModifier, "" + saturationAdditionMode, "" + brightnessAdditionMode);
    }

    public float hue() {
        return hueModifier;
    }

    public void hue(final float hue) {
        this.hueModifier = hue;
    }

    public float saturation() {
        return saturationModifier;
    }

    public void saturation(final float saturation) {
        this.saturationModifier = saturation;
    }

    public float brightness() {
        return brightnessModifier;
    }

    public void brightness(final float brightness) {
        this.brightnessModifier = brightness;
    }

    public boolean saturationChecked() {
        return saturationAdditionMode;
    }

    public void saturationChecked(final boolean saturationChecked) {
        this.saturationAdditionMode = saturationChecked;
    }

    public boolean brightnessChecked() {
        return brightnessAdditionMode;
    }

    public void brightnessChecked(final boolean brightnessChecked) {
        this.brightnessAdditionMode = brightnessChecked;
    }

    @NotNull
    public String toString() {
        return "ColorHSBModifier{" +
                "hueModifier=" + hueModifier +
                ", saturationModifier=" + saturationModifier +
                ", brightnessModifier=" + brightnessModifier +
                ", saturationAdditionMode=" + saturationAdditionMode +
                ", brightnessAdditionMode=" + brightnessAdditionMode +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ColorHSBModifier that = (ColorHSBModifier) o;

        if (Float.compare(that.hueModifier, hueModifier) != 0) return false;
        if (Float.compare(that.saturationModifier, saturationModifier) != 0) return false;
        if (Float.compare(that.brightnessModifier, brightnessModifier) != 0) return false;
        if (saturationAdditionMode != that.saturationAdditionMode) return false;
        return brightnessAdditionMode == that.brightnessAdditionMode;
    }

    public int hashCode() {
        int result = (hueModifier != 0.0f ? Float.floatToIntBits(hueModifier) : 0);
        result = 31 * result + (saturationModifier != 0.0f ? Float.floatToIntBits(saturationModifier) : 0);
        result = 31 * result + (brightnessModifier != 0.0f ? Float.floatToIntBits(brightnessModifier) : 0);
        result = 31 * result + (saturationAdditionMode ? 1 : 0);
        result = 31 * result + (brightnessAdditionMode ? 1 : 0);
        return result;
    }
}

package org.crayne.hrm.api.level.data.object.type.trigger.visual.color;

import org.crayne.hrm.api.level.data.color.ColorProperty;
import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class PulseTrigger extends Trigger {

    private float pulseFadeIn, pulseHold, pulseFadeOut; // 45, 46, 47

    @NotNull
    private Mode pulseMode; // 48

    @NotNull
    private Target pulseTargetType; // 52

    private boolean mainOnly, detailOnly; // 65, 66
    private boolean exclusive; // 86

    private int targetID; // 51

    @NotNull
    private ColorProperty colorProperty;

    public PulseTrigger(final int objectID, final float positionX, final float positionY, @NotNull final ColorProperty colorProperty,
                        @NotNull final Target pulseTargetType, final int targetID) {
        super(objectID, positionX, positionY);
        this.pulseTargetType = pulseTargetType;
        this.targetID = targetID;
        this.pulseMode = Mode.COLOR;
        this.colorProperty = ColorProperty.none();
    }

    public PulseTrigger(@NotNull final LevelObject levelObject, @NotNull final ColorProperty colorProperty,
                        @NotNull final Target pulseTargetType, final int targetID) {
        super(levelObject);
        this.colorProperty = colorProperty;
        this.pulseTargetType = pulseTargetType;
        this.targetID = targetID;
        this.pulseMode = Mode.COLOR;
    }

    public PulseTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.colorProperty   = new ColorProperty(objectProperties, true);
        this.pulseFadeIn     = objectProperties.floatProperty(LevelObjectProperty.PULSE_FADE_IN);
        this.pulseHold       = objectProperties.floatProperty(LevelObjectProperty.PULSE_HOLD);
        this.pulseFadeOut    = objectProperties.floatProperty(LevelObjectProperty.PULSE_FADE_OUT);
        this.pulseMode       = objectProperties.pulseModeProperty(LevelObjectProperty.PULSE_MODE);
        this.pulseTargetType = objectProperties.pulseTargetProperty(LevelObjectProperty.PULSE_TARGET_TYPE);
        this.mainOnly        = objectProperties.booleanProperty(LevelObjectProperty.PULSE_MAIN_ONLY);
        this.detailOnly      = objectProperties.booleanProperty(LevelObjectProperty.PULSE_DETAIL_ONLY);
        this.exclusive       = objectProperties.booleanProperty(LevelObjectProperty.PULSE_EXCLUSIVE);
        this.targetID        = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putAll(colorProperty.createProperties(true, pulseTargetType));
        properties.putFloatProperty(LevelObjectProperty.PULSE_FADE_IN, pulseFadeIn);
        properties.putFloatProperty(LevelObjectProperty.PULSE_HOLD, pulseHold);
        properties.putFloatProperty(LevelObjectProperty.PULSE_FADE_OUT, pulseFadeOut);
        properties.putPulseModeProperty(LevelObjectProperty.PULSE_MODE, pulseMode);
        properties.putPulseTargetProperty(LevelObjectProperty.PULSE_TARGET_TYPE, pulseTargetType);
        properties.putBooleanProperty(LevelObjectProperty.PULSE_MAIN_ONLY, mainOnly);
        properties.putBooleanProperty(LevelObjectProperty.PULSE_DETAIL_ONLY, detailOnly);
        properties.putBooleanProperty(LevelObjectProperty.PULSE_EXCLUSIVE, exclusive);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetID);

        return properties;
    }

    public enum Mode {
        COLOR(0), HSB_COPY_COLOR(1);

        private final int id;

        Mode(final int id) {
            this.id = id;
        }

        @NotNull
        public static PropertyDataType datatype() {
            return PropertyDataType.PULSE_MODE;
        }

        @NotNull
        public static Mode of(final int id) {
            return id == 1 ? HSB_COPY_COLOR : COLOR;
        }

        public int id() {
            return id;
        }

    }

    public enum Target {
        GROUP(0), CHANNEL(1);

        private final int id;

        Target(final int id) {
            this.id = id;
        }

        @NotNull
        public static PropertyDataType datatype() {
            return PropertyDataType.PULSE_TARGET;
        }

        @NotNull
        public static Target of(final int id) {
            return id == 1 ? CHANNEL : GROUP;
        }

        public int id() {
            return id;
        }

    }

    @NotNull
    public ColorProperty colorProperty() {
        return colorProperty;
    }

    public void colorProperty(@NotNull final ColorProperty colorProperty) {
        this.colorProperty = colorProperty;
    }

    public float pulseFadeIn() {
        return pulseFadeIn;
    }

    public void pulseFadeIn(final float pulseFadeIn) {
        this.pulseFadeIn = pulseFadeIn;
    }

    public float pulseHold() {
        return pulseHold;
    }

    public void pulseHold(final float pulseHold) {
        this.pulseHold = pulseHold;
    }

    public float pulseFadeOut() {
        return pulseFadeOut;
    }

    public void pulseFadeOut(final float pulseFadeOut) {
        this.pulseFadeOut = pulseFadeOut;
    }

    @NotNull
    public Mode pulseMode() {
        return pulseMode;
    }

    public void pulseMode(@NotNull final Mode pulseMode) {
        this.pulseMode = pulseMode;
    }

    @NotNull
    public Target pulseTargetType() {
        return pulseTargetType;
    }

    public void pulseTargetType(@NotNull final Target pulseTargetType) {
        this.pulseTargetType = pulseTargetType;
    }

    public boolean mainOnly() {
        return mainOnly;
    }

    public void mainOnly(final boolean mainOnly) {
        this.mainOnly = mainOnly;
    }

    public boolean detailOnly() {
        return detailOnly;
    }

    public void detailOnly(final boolean detailOnly) {
        this.detailOnly = detailOnly;
    }

    public boolean exclusive() {
        return exclusive;
    }

    public void exclusive(final boolean exclusive) {
        this.exclusive = exclusive;
    }

    public int targetID() {
        return targetID;
    }

    public void targetID(final int targetGroupID) {
        this.targetID = targetGroupID;
    }

    @NotNull
    public String toString() {
        return "PulseTrigger{" +
                "pulseFadeIn=" + pulseFadeIn +
                ", pulseHold=" + pulseHold +
                ", pulseFadeOut=" + pulseFadeOut +
                ", pulseMode=" + pulseMode +
                ", pulseTargetType=" + pulseTargetType +
                ", mainOnly=" + mainOnly +
                ", detailOnly=" + detailOnly +
                ", exclusive=" + exclusive +
                ", targetID=" + targetID +
                ", colorProperty=" + colorProperty +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final PulseTrigger that = (PulseTrigger) o;

        if (Float.compare(that.pulseFadeIn, pulseFadeIn) != 0) return false;
        if (Float.compare(that.pulseHold, pulseHold) != 0) return false;
        if (Float.compare(that.pulseFadeOut, pulseFadeOut) != 0) return false;
        if (mainOnly != that.mainOnly) return false;
        if (detailOnly != that.detailOnly) return false;
        if (exclusive != that.exclusive) return false;
        if (targetID != that.targetID) return false;
        if (pulseMode != that.pulseMode) return false;
        if (pulseTargetType != that.pulseTargetType) return false;
        return colorProperty.equals(that.colorProperty);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pulseFadeIn != 0.0f ? Float.floatToIntBits(pulseFadeIn) : 0);
        result = 31 * result + (pulseHold != 0.0f ? Float.floatToIntBits(pulseHold) : 0);
        result = 31 * result + (pulseFadeOut != 0.0f ? Float.floatToIntBits(pulseFadeOut) : 0);
        result = 31 * result + pulseMode.hashCode();
        result = 31 * result + pulseTargetType.hashCode();
        result = 31 * result + (mainOnly ? 1 : 0);
        result = 31 * result + (detailOnly ? 1 : 0);
        result = 31 * result + (exclusive ? 1 : 0);
        result = 31 * result + targetID;
        result = 31 * result + colorProperty.hashCode();
        return result;
    }
}

package org.crayne.gdboard.level.data.object.type.trigger.visual.color;

import org.crayne.gdboard.level.data.color.ColorProperty;
import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyDataType;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
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
        this.pulseFadeIn     = objectProperties.floatProperty(LevelObjectData.PULSE_FADE_IN);
        this.pulseHold       = objectProperties.floatProperty(LevelObjectData.PULSE_HOLD);
        this.pulseFadeOut    = objectProperties.floatProperty(LevelObjectData.PULSE_FADE_OUT);
        this.pulseMode       = objectProperties.pulseModeProperty(LevelObjectData.PULSE_MODE);
        this.pulseTargetType = objectProperties.pulseTargetProperty(LevelObjectData.PULSE_TARGET_TYPE);
        this.mainOnly        = objectProperties.booleanProperty(LevelObjectData.PULSE_MAIN_ONLY);
        this.detailOnly      = objectProperties.booleanProperty(LevelObjectData.PULSE_DETAIL_ONLY);
        this.exclusive       = objectProperties.booleanProperty(LevelObjectData.PULSE_EXCLUSIVE);
        this.targetID        = objectProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID);
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
}

package org.crayne.hrm.api.level.data.object.type.trigger.general;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class TouchTrigger extends Trigger implements TargetTrigger {

    private boolean touchHoldMode;

    @NotNull
    private ToggleMode touchToggleMode;

    private boolean touchDualMode;
    private int targetGroupID;

    public TouchTrigger(final int objectID, final float positionX, final float positionY, final boolean touchHoldMode,
                        @NotNull final ToggleMode touchToggleMode, final boolean touchDualMode, final int targetGroupID) {
        super(objectID, positionX, positionY);
        this.touchHoldMode = touchHoldMode;
        this.touchToggleMode = touchToggleMode;
        this.touchDualMode = touchDualMode;
        this.targetGroupID = targetGroupID;
    }

    public TouchTrigger(@NotNull final LevelObject levelObject, final boolean touchHoldMode, @NotNull final ToggleMode touchToggleMode,
                        final boolean touchDualMode, final int targetGroupID) {
        super(levelObject);
        this.touchHoldMode = touchHoldMode;
        this.touchToggleMode = touchToggleMode;
        this.touchDualMode = touchDualMode;
        this.targetGroupID = targetGroupID;
    }

    public TouchTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.touchToggleMode = ToggleMode.NONE;
    }

    public TouchTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.touchToggleMode = ToggleMode.NONE;
    }

    public TouchTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.touchHoldMode   = objectProperties.booleanProperty(LevelObjectProperty.TOUCH_HOLD_MODE);
        this.touchToggleMode = objectProperties.touchToggleModeProperty(LevelObjectProperty.TOUCH_TOGGLE_MODE);
        this.touchDualMode   = objectProperties.booleanProperty(LevelObjectProperty.TOUCH_DUAL_MODE);
        this.targetGroupID   = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1595);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putBooleanProperty(LevelObjectProperty.TOUCH_HOLD_MODE, touchHoldMode);
        properties.putTouchToggleModeProperty(LevelObjectProperty.TOUCH_TOGGLE_MODE, touchToggleMode);
        properties.putBooleanProperty(LevelObjectProperty.TOUCH_DUAL_MODE, touchDualMode);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);

        return properties;
    }

    public enum ToggleMode {
        NONE(0),
        TOGGLE_ON(1),
        TOGGLE_OFF(2);

        private final int id;

        ToggleMode(final int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }

        @NotNull
        public static PropertyDataType datatype() {
            return PropertyDataType.TOUCH_TOGGLE_MODE;
        }

        @NotNull
        public ToggleMode ofID(final int id) {
            return of(id);
        }

        @NotNull
        public static ToggleMode of(final int id) {
            return switch (id) {
                case 1 -> TOGGLE_ON;
                case 2 -> TOGGLE_OFF;
                default -> NONE;
            };
        }

    }

    public boolean touchHoldMode() {
        return touchHoldMode;
    }

    public void touchHoldMode(final boolean touchHoldMode) {
        this.touchHoldMode = touchHoldMode;
    }

    @NotNull
    public ToggleMode touchToggleMode() {
        return touchToggleMode;
    }

    public void touchToggleMode(@NotNull final ToggleMode touchToggleMode) {
        this.touchToggleMode = touchToggleMode;
    }

    public boolean touchDualMode() {
        return touchDualMode;
    }

    public void touchDualMode(final boolean touchDualMode) {
        this.touchDualMode = touchDualMode;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    @NotNull
    public String toString() {
        return "TouchTrigger{" +
                "touchHoldMode=" + touchHoldMode +
                ", touchToggleMode=" + touchToggleMode +
                ", touchDualMode=" + touchDualMode +
                ", targetGroupID=" + targetGroupID +
                "} " + super.toString();
    }

}

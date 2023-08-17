package org.crayne.gdboard.level.data.object.type.trigger.visual.color;

import org.crayne.gdboard.level.data.color.ColorProperty;
import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ColorTrigger extends Trigger {

    // TODO after finishing up parsing from savefile, convert all of these properties into one single ColorProperty object, same for pulse
    private boolean playerColor1, playerColor2; // 15, 16

    private float triggerDuration; // 10

    @NotNull
    private ColorProperty colorProperty;


    public ColorTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.triggerDuration = 0.5f;
        this.colorProperty = ColorProperty.none();
    }

    public ColorTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.triggerDuration = 0.5f;
        this.colorProperty = ColorProperty.none();
    }

    public ColorTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.colorProperty   = new ColorProperty(objectProperties, false);
        this.playerColor1    = objectProperties.booleanProperty(LevelObjectData.PLAYER_COLOR_1);
        this.playerColor2    = objectProperties.booleanProperty(LevelObjectData.PLAYER_COLOR_2);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectData.DURATION);
    }

    @NotNull
    public ColorProperty colorProperty() {
        return colorProperty;
    }

    public void colorProperty(@NotNull final ColorProperty colorProperty) {
        this.colorProperty = colorProperty;
    }

    public boolean playerColor1() {
        return playerColor1;
    }

    public void playerColor1(final boolean playerColor1) {
        this.playerColor1 = playerColor1;
        this.playerColor2 = !playerColor1;
    }

    public boolean playerColor2() {
        return playerColor2;
    }

    public void playerColor2(final boolean playerColor2) {
        this.playerColor2 = playerColor2;
        this.playerColor1 = !playerColor2;
    }

    public float triggerDuration() {
        return triggerDuration;
    }

    public void triggerDuration(final float triggerDuration) {
        this.triggerDuration = triggerDuration;
    }

    @NotNull
    public String toString() {
        return "ColorTrigger{" +
                "playerColor1=" + playerColor1 +
                ", playerColor2=" + playerColor2 +
                ", triggerDuration=" + triggerDuration +
                ", colorProperty=" + colorProperty +
                "} " + super.toString();
    }
}

package org.crayne.gdboard.level.data.object.type.trigger.visual;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class ShakeTrigger extends Trigger {

    private float shakeStrength; // 75
    private float interval; // 84
    private float triggerDuration; // 10

    public ShakeTrigger(final int objectID, final float positionX, final float positionY, final boolean touchTriggered,
                        final float shakeStrength, final float interval, final float triggerDuration) {
        super(objectID, positionX, positionY, touchTriggered);
        this.shakeStrength = shakeStrength;
        this.interval = interval;
        this.triggerDuration = triggerDuration;
    }

    public ShakeTrigger(@NotNull final LevelObject levelObject, final boolean touchTriggered, final float shakeStrength,
                        final float interval, final float triggerDuration) {
        super(levelObject, touchTriggered);
        this.shakeStrength = shakeStrength;
        this.interval = interval;
        this.triggerDuration = triggerDuration;
    }

    public ShakeTrigger(final int objectID, final float positionX, final float positionY, final boolean spawnTriggered,
                        final boolean multiTriggered, final float shakeStrength, final float interval, final float triggerDuration) {
        super(objectID, positionX, positionY, spawnTriggered, multiTriggered);
        this.shakeStrength = shakeStrength;
        this.interval = interval;
        this.triggerDuration = triggerDuration;
    }

    public ShakeTrigger(@NotNull final LevelObject levelObject, final boolean spawnTriggered, final boolean multiTriggered,
                        final float shakeStrength, final float interval, final float triggerDuration) {
        super(levelObject, spawnTriggered, multiTriggered);
        this.shakeStrength = shakeStrength;
        this.interval = interval;
        this.triggerDuration = triggerDuration;
    }

    public ShakeTrigger(final int objectID, final float positionX, final float positionY, final float shakeStrength,
                        final float interval, final float triggerDuration) {
        super(objectID, positionX, positionY);
        this.shakeStrength = shakeStrength;
        this.interval = interval;
        this.triggerDuration = triggerDuration;
    }

    public ShakeTrigger(@NotNull final LevelObject levelObject, final float shakeStrength, final float interval,
                        final float triggerDuration) {
        super(levelObject);
        this.shakeStrength = shakeStrength;
        this.interval = interval;
        this.triggerDuration = triggerDuration;
    }

    public ShakeTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.triggerDuration = 0.5f;
    }

    public ShakeTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.triggerDuration = 0.5f;
    }

    public ShakeTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.shakeStrength = objectProperties.floatProperty(LevelObjectData.SHAKE_STRENGTH);
        this.interval = objectProperties.floatProperty(LevelObjectData.SHAKE_INTERVAL);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectData.DURATION);
    }

    public float shakeStrength() {
        return shakeStrength;
    }

    public void shakeStrength(final float shakeStrength) {
        this.shakeStrength = shakeStrength;
    }

    public float interval() {
        return interval;
    }

    public void interval(final float interval) {
        this.interval = interval;
    }

    public float triggerDuration() {
        return triggerDuration;
    }

    public void triggerDuration(final float triggerDuration) {
        this.triggerDuration = triggerDuration;
    }

    @NotNull
    public String toString() {
        return "ShakeTrigger{" +
                "shakeStrength=" + shakeStrength +
                ", interval=" + interval +
                ", triggerDuration=" + triggerDuration +
                "} " + super.toString();
    }
}

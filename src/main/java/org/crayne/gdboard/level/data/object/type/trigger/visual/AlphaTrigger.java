package org.crayne.gdboard.level.data.object.type.trigger.visual;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class AlphaTrigger extends Trigger {

    private float opacity; // 35
    private int targetGroupID; // 51
    private float triggerDuration; // 10

    public AlphaTrigger(final int objectID, final float positionX, final float positionY, final boolean touchTriggered,
                        final float opacity, final int targetGroupID, final float triggerDuration) {
        super(objectID, positionX, positionY, touchTriggered);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(@NotNull final LevelObject levelObject, final boolean touchTriggered, final float opacity,
                        final int targetGroupID, final float triggerDuration) {
        super(levelObject, touchTriggered);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(final int objectID, final float positionX, final float positionY, final boolean spawnTriggered,
                        final boolean multiTriggered, final float opacity, final int targetGroupID, final float triggerDuration) {
        super(objectID, positionX, positionY, spawnTriggered, multiTriggered);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(@NotNull final LevelObject levelObject, final boolean spawnTriggered, final boolean multiTriggered,
                        final float opacity, final int targetGroupID, final float triggerDuration) {
        super(levelObject, spawnTriggered, multiTriggered);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(final int objectID, final float positionX, final float positionY, final float opacity,
                        final int targetGroupID, final float triggerDuration) {
        super(objectID, positionX, positionY);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(@NotNull final LevelObject levelObject, final float opacity,
                        final int targetGroupID, final float triggerDuration) {
        super(levelObject);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.opacity = 1.0f;
        this.triggerDuration = 0.5f;
    }

    public AlphaTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.opacity = 1.0f;
        this.triggerDuration = 0.5f;
    }

    public AlphaTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.opacity = objectProperties.floatProperty(LevelObjectData.OPACITY);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectData.DURATION);
    }

    public float opacity() {
        return opacity;
    }

    public void opacity(final float opacity) {
        this.opacity = opacity;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public float triggerDuration() {
        return triggerDuration;
    }

    public void triggerDuration(final float triggerDuration) {
        this.triggerDuration = triggerDuration;
    }

    @NotNull
    public String toString() {
        return "AlphaTrigger{" +
                "opacity=" + opacity +
                ", targetGroupID=" + targetGroupID +
                ", triggerDuration=" + triggerDuration +
                "} " + super.toString();
    }
}

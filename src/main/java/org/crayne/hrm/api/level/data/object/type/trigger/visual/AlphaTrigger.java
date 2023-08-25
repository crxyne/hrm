package org.crayne.hrm.api.level.data.object.type.trigger.visual;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class AlphaTrigger extends Trigger implements TargetTrigger {

    private float opacity;
    private int targetGroupID;
    private float triggerDuration;

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
        this.opacity = objectProperties.floatProperty(LevelObjectProperty.OPACITY);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.triggerDuration = objectProperties.floatProperty(LevelObjectProperty.DURATION);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1007);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putFloatProperty(LevelObjectProperty.OPACITY, opacity);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);
        properties.putFloatProperty(LevelObjectProperty.DURATION, triggerDuration);

        return properties;
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

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final AlphaTrigger that = (AlphaTrigger) o;

        if (Float.compare(that.opacity, opacity) != 0) return false;
        if (targetGroupID != that.targetGroupID) return false;
        return Float.compare(that.triggerDuration, triggerDuration) == 0;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (opacity != 0.0f ? Float.floatToIntBits(opacity) : 0);
        result = 31 * result + targetGroupID;
        result = 31 * result + (triggerDuration != 0.0f ? Float.floatToIntBits(triggerDuration) : 0);
        return result;
    }
}

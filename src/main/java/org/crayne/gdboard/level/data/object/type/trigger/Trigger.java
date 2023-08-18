package org.crayne.gdboard.level.data.object.type.trigger;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class Trigger extends LevelObject { // transition triggers are not counted as trigger objects, since they do not have any of the below parameters and are only treated like normal level objects

    private boolean touchTriggered; // 11
    private boolean spawnTriggered; // 62
    private boolean multiTriggered; // 87

    public Trigger(final int objectID, final float positionX, final float positionY, final boolean touchTriggered) {
        super(objectID, positionX, positionY);
        this.touchTriggered = touchTriggered;
    }

    public Trigger(@NotNull final LevelObject levelObject, final boolean touchTriggered) {
        super(levelObject);
        this.touchTriggered = touchTriggered;
    }

    public Trigger(final int objectID, final float positionX, final float positionY, final boolean spawnTriggered, final boolean multiTriggered) {
        super(objectID, positionX, positionY);
        this.spawnTriggered = spawnTriggered;
        this.multiTriggered = multiTriggered;
    }

    public Trigger(@NotNull final LevelObject levelObject, final boolean spawnTriggered, final boolean multiTriggered) {
        super(levelObject);
        this.spawnTriggered = spawnTriggered;
        this.multiTriggered = multiTriggered;
    }

    public Trigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public Trigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public Trigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.touchTriggered = objectProperties.booleanProperty(LevelObjectProperty.TOUCH_TRIGGERED);
        this.spawnTriggered = objectProperties.booleanProperty(LevelObjectProperty.SPAWN_TRIGGERED);
        this.multiTriggered = objectProperties.booleanProperty(LevelObjectProperty.MULTI_TRIGGERED);
    }

    public boolean touchTriggered() {
        return touchTriggered;
    }

    public void touchTriggered(final boolean touchTriggered) {
        this.touchTriggered = touchTriggered;
        this.spawnTriggered = !touchTriggered;
    }

    public boolean spawnTriggered() {
        return spawnTriggered;
    }

    public void spawnTriggered(final boolean spawnTriggered) {
        this.spawnTriggered = spawnTriggered;
        this.touchTriggered = !spawnTriggered;
    }

    public boolean multiTriggered() {
        return multiTriggered;
    }

    public void multiTriggered(final boolean multiTriggered) {
        this.multiTriggered = multiTriggered;
    }

    @NotNull
    public String toString() {
        return "Trigger{" +
                "touchTriggered=" + touchTriggered +
                ", spawnTriggered=" + spawnTriggered +
                ", multiTriggered=" + multiTriggered +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final Trigger trigger = (Trigger) o;

        if (touchTriggered != trigger.touchTriggered) return false;
        if (spawnTriggered != trigger.spawnTriggered) return false;
        return multiTriggered == trigger.multiTriggered;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (touchTriggered ? 1 : 0);
        result = 31 * result + (spawnTriggered ? 1 : 0);
        result = 31 * result + (multiTriggered ? 1 : 0);
        return result;
    }

}

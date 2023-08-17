package org.crayne.gdboard.level.data.object.type.trigger;

import org.crayne.gdboard.level.data.object.type.LevelObject;

public class TriggerObject extends LevelObject {

    private boolean touchTriggered; // 11
    private boolean spawnTriggered; // 62
    private boolean multiTriggered; // 87

    public TriggerObject(final boolean spawnTriggered, final boolean multiTriggered) {
        this.touchTriggered = !spawnTriggered;
        this.spawnTriggered = spawnTriggered;
        this.multiTriggered = multiTriggered;
    }

    public TriggerObject(final boolean touchTriggered) {
        this.touchTriggered = touchTriggered;
    }

    public TriggerObject() {

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
}

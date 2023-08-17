package org.crayne.gdboard.level.data.object.type.trigger.general;

import org.crayne.gdboard.level.data.object.type.trigger.Trigger;

public class AlphaTrigger extends Trigger {

    private float opacity; // 35
    private int targetGroupID; // 51
    private float triggerDuration; // 10

    public AlphaTrigger(final boolean spawnTriggered, final boolean multiTriggered, final float opacity,
                        final int targetGroupID, final float triggerDuration) {
        super(spawnTriggered, multiTriggered);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(final boolean touchTriggered, final float opacity, final int targetGroupID, final float triggerDuration) {
        super(touchTriggered);
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
    }

    public AlphaTrigger(final float opacity, final int targetGroupID, final float triggerDuration) {
        this.opacity = opacity;
        this.targetGroupID = targetGroupID;
        this.triggerDuration = triggerDuration;
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

}

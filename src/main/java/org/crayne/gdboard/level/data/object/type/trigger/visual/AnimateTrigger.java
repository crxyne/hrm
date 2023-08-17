package org.crayne.gdboard.level.data.object.type.trigger.general;

import org.crayne.gdboard.level.data.object.type.trigger.Trigger;

public class AnimateTrigger extends Trigger {

    private int animationID; // 76
    private int targetGroupID; // 51

    public AnimateTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int animationID, final int targetGroupID) {
        super(spawnTriggered, multiTriggered);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(final boolean touchTriggered, final int animationID, final int targetGroupID) {
        super(touchTriggered);
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public AnimateTrigger(final int animationID, final int targetGroupID) {
        this.animationID = animationID;
        this.targetGroupID = targetGroupID;
    }

    public int animationID() {
        return animationID;
    }

    public void animationID(final int animationID) {
        this.animationID = animationID;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

}

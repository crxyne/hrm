package org.crayne.gdboard.level.data.object.type.trigger.general;

import org.crayne.gdboard.level.data.object.type.trigger.Trigger;

public class ToggleTrigger extends Trigger {

    private int targetGroupID; // 51
    private boolean activateGroup; // 56

    public ToggleTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int targetGroupID, final boolean activateGroup) {
        super(spawnTriggered, multiTriggered);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public ToggleTrigger(final boolean touchTriggered, final int targetGroupID, final boolean activateGroup) {
        super(touchTriggered);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public ToggleTrigger(final int targetGroupID, final boolean activateGroup) {
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public boolean activateGroup() {
        return activateGroup;
    }

    public void activateGroup(final boolean activateGroup) {
        this.activateGroup = activateGroup;
    }
}

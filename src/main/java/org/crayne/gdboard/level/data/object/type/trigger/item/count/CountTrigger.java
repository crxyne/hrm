package org.crayne.gdboard.level.data.object.type.trigger.item;

import org.crayne.gdboard.level.data.object.type.trigger.toggle.ToggleTrigger;

public class CountTrigger extends ToggleTrigger {

    private int itemID; // 80
    private int count; // 77
    private boolean multiActivate; // 104

    public CountTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int targetGroupID,
                        final boolean activateGroup, final int itemID, final int count, final boolean multiActivate) {
        super(spawnTriggered, multiTriggered, targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.multiActivate = multiActivate;
    }

    public CountTrigger(final boolean touchTriggered, final int targetGroupID, final boolean activateGroup,
                        final int itemID, final int count, final boolean multiActivate) {
        super(touchTriggered, targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.multiActivate = multiActivate;
    }

    public CountTrigger(final int targetGroupID, final boolean activateGroup, final int itemID,
                        final int count, final boolean multiActivate) {
        super(targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.multiActivate = multiActivate;
    }

    public int itemID() {
        return itemID;
    }

    public void itemID(final int itemBlockID) {
        this.itemID = itemBlockID;
    }

    public int count() {
        return count;
    }

    public void count(final int count) {
        this.count = count;
    }

    public boolean multiActivate() {
        return multiActivate;
    }

    public void multiActivate(final boolean multiActivate) {
        this.multiActivate = multiActivate;
    }

}

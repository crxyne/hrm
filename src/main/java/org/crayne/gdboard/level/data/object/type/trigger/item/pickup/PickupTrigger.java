package org.crayne.gdboard.level.data.object.type.trigger.item;

import org.crayne.gdboard.level.data.object.type.trigger.Trigger;

public class PickupTrigger extends Trigger {

    private int count; // 77
    private int itemBlockID; // 80

    public PickupTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int count, final int itemBlockID) {
        super(spawnTriggered, multiTriggered);
        this.count = count;
        this.itemBlockID = itemBlockID;
    }

    public PickupTrigger(final boolean touchTriggered, final int count, final int itemBlockID) {
        super(touchTriggered);
        this.count = count;
        this.itemBlockID = itemBlockID;
    }

    public PickupTrigger(final int count, final int itemBlockID) {
        this.count = count;
        this.itemBlockID = itemBlockID;
    }

    public int count() {
        return count;
    }

    public void count(final int count) {
        this.count = count;
    }

    public int itemBlockID() {
        return itemBlockID;
    }

    public void itemBlockID(final int itemBlockID) {
        this.itemBlockID = itemBlockID;
    }
}

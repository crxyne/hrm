package org.crayne.gdboard.level.data.object.type.trigger.toggle;

public class CollisionTrigger extends ToggleTrigger {

    private boolean triggerOnExit; // 93
    private int itemBlockID; // 80
    private int secondBlockID; // 95

    public CollisionTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int targetGroupID,
                            final boolean activateGroup, final boolean triggerOnExit, final int itemBlockID,
                            final int secondBlockID) {
        super(spawnTriggered, multiTriggered, targetGroupID, activateGroup);
        this.triggerOnExit = triggerOnExit;
        this.itemBlockID = itemBlockID;
        this.secondBlockID = secondBlockID;
    }

    public CollisionTrigger(final boolean touchTriggered, final int targetGroupID, final boolean activateGroup,
                            final boolean triggerOnExit, final int itemBlockID, final int secondBlockID) {
        super(touchTriggered, targetGroupID, activateGroup);
        this.triggerOnExit = triggerOnExit;
        this.itemBlockID = itemBlockID;
        this.secondBlockID = secondBlockID;
    }

    public CollisionTrigger(final int targetGroupID, final boolean activateGroup, final boolean triggerOnExit,
                            final int itemBlockID, final int secondBlockID) {
        super(targetGroupID, activateGroup);
        this.triggerOnExit = triggerOnExit;
        this.itemBlockID = itemBlockID;
        this.secondBlockID = secondBlockID;
    }

    public boolean triggerOnExit() {
        return triggerOnExit;
    }

    public void triggerOnExit(final boolean triggerOnExit) {
        this.triggerOnExit = triggerOnExit;
    }

    public int itemBlockID() {
        return itemBlockID;
    }

    public void itemBlockID(final int itemBlockID) {
        this.itemBlockID = itemBlockID;
    }

    public int secondBlockID() {
        return secondBlockID;
    }

    public void secondBlockID(final int secondBlockID) {
        this.secondBlockID = secondBlockID;
    }

}

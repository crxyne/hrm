package org.crayne.gdboard.level.data.object.type.trigger.collision;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class CollisionTrigger extends ToggleTrigger {

    private boolean triggerOnExit;
    private int firstBlockID;
    private int secondBlockID;

    public CollisionTrigger(final int objectID, final float positionX, final float positionY, final int targetGroupID,
                            final boolean activateGroup, final boolean triggerOnExit, final int firstBlockID,
                            final int secondBlockID) {
        super(objectID, positionX, positionY, targetGroupID, activateGroup);
        this.triggerOnExit = triggerOnExit;
        this.firstBlockID = firstBlockID;
        this.secondBlockID = secondBlockID;
    }

    public CollisionTrigger(@NotNull final LevelObject levelObject, final int targetGroupID, final boolean activateGroup,
                            final boolean triggerOnExit, final int firstBlockID, final int secondBlockID) {
        super(levelObject, targetGroupID, activateGroup);
        this.triggerOnExit = triggerOnExit;
        this.firstBlockID = firstBlockID;
        this.secondBlockID = secondBlockID;
    }

    public CollisionTrigger(final int objectID, final float positionX, final float positionY,
                            final int targetGroupID, final boolean activateGroup) {
        super(objectID, positionX, positionY, targetGroupID, activateGroup);
    }

    public CollisionTrigger(@NotNull final LevelObject levelObject, final int targetGroupID,
                            final boolean activateGroup) {
        super(levelObject, targetGroupID, activateGroup);
    }

    public CollisionTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public CollisionTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public CollisionTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.triggerOnExit = objectProperties.booleanProperty(LevelObjectData.COLLISION_TRIGGER_ON_EXIT);
        this.firstBlockID = objectProperties.integerProperty(LevelObjectData.ITEM_OR_BLOCK_ID);
        this.secondBlockID = objectProperties.integerProperty(LevelObjectData.COLLISION_SECOND_BLOCK_ID);
    }

    public boolean triggerOnExit() {
        return triggerOnExit;
    }

    public void triggerOnExit(final boolean triggerOnExit) {
        this.triggerOnExit = triggerOnExit;
    }

    public int firstBlockID() {
        return firstBlockID;
    }

    public void firstBlockID(final int firstBlockID) {
        this.firstBlockID = firstBlockID;
    }

    public int secondBlockID() {
        return secondBlockID;
    }

    public void secondBlockID(final int secondBlockID) {
        this.secondBlockID = secondBlockID;
    }

    @NotNull
    public String toString() {
        return "CollisionTrigger{" +
                "triggerOnExit=" + triggerOnExit +
                ", firstBlockID=" + firstBlockID +
                ", secondBlockID=" + secondBlockID +
                "} " + super.toString();
    }
}

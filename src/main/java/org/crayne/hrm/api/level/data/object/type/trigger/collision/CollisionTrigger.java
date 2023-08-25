package org.crayne.hrm.api.level.data.object.type.trigger.collision;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.type.BiBlockTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.crayne.hrm.api.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class CollisionTrigger extends ToggleTrigger implements BiBlockTrigger {

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
        this.triggerOnExit = objectProperties.booleanProperty(LevelObjectProperty.COLLISION_TRIGGER_ON_EXIT);
        this.firstBlockID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
        this.secondBlockID = objectProperties.integerProperty(LevelObjectProperty.COLLISION_SECOND_BLOCK_ID);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1815);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putBooleanProperty(LevelObjectProperty.COLLISION_TRIGGER_ON_EXIT, triggerOnExit);
        properties.putIntProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID, firstBlockID);
        properties.putIntProperty(LevelObjectProperty.COLLISION_SECOND_BLOCK_ID, secondBlockID);

        return properties;
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

    public int blockID() {
        return firstBlockID;
    }

    public void blockID(final int firstBlockID) {
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

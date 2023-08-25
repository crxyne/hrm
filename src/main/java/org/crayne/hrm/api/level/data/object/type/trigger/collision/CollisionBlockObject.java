package org.crayne.hrm.api.level.data.object.type.trigger.collision;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.type.BlockTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class CollisionBlockObject extends LevelObject implements BlockTrigger {

    private boolean dynamicBlock;
    private int blockID;

    public CollisionBlockObject(final int objectID, final float positionX, final float positionY, final boolean dynamicBlock, final int blockID) {
        super(objectID, positionX, positionY);
        this.dynamicBlock = dynamicBlock;
        this.blockID = blockID;
    }

    public CollisionBlockObject(@NotNull final LevelObject levelObject, final boolean dynamicBlock, final int blockID) {
        super(levelObject);
        this.dynamicBlock = dynamicBlock;
        this.blockID = blockID;
    }

    public CollisionBlockObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.dynamicBlock = objectProperties.booleanProperty(LevelObjectProperty.COLLISION_DYNAMIC_BLOCK);
        this.blockID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1816);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putBooleanProperty(LevelObjectProperty.COLLISION_DYNAMIC_BLOCK, dynamicBlock);
        properties.putIntProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID, blockID);

        return properties;
    }

    public CollisionBlockObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public CollisionBlockObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public boolean dynamicBlock() {
        return dynamicBlock;
    }

    public void dynamicBlock(final boolean dynamicBlock) {
        this.dynamicBlock = dynamicBlock;
    }

    public int blockID() {
        return blockID;
    }

    public void blockID(final int blockID) {
        this.blockID = blockID;
    }

    @NotNull
    public String toString() {
        return "CollisionBlockObject{" +
                "dynamicBlock=" + dynamicBlock +
                ", blockID=" + blockID +
                "} " + super.toString();
    }
}

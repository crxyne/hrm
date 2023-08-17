package org.crayne.gdboard.level.data.object.type.trigger.collision;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class CollisionBlockObject extends LevelObject {

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
        this.dynamicBlock = objectProperties.booleanProperty(LevelObjectData.COLLISION_DYNAMIC_BLOCK);
        this.blockID = objectProperties.integerProperty(LevelObjectData.ITEM_OR_BLOCK_ID);
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

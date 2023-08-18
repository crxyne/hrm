package org.crayne.gdboard.level.data.object.type.trigger.item.count;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.decoration.ColorableObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ItemCounter extends ColorableObject {

    private int itemID;

    public ItemCounter(final int objectID, final float positionX, final float positionY, final int itemID) {
        super(objectID, positionX, positionY);
        this.itemID = itemID;
    }

    public ItemCounter(@NotNull final LevelObject levelObject, final int itemID) {
        super(levelObject);
        this.itemID = itemID;
    }

    public ItemCounter(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.itemID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
    }

    public int itemID() {
        return itemID;
    }

    public void itemID(final int itemID) {
        this.itemID = itemID;
    }

    @NotNull
    public String toString() {
        return "ItemCounter{" +
                "itemID=" + itemID +
                "} " + super.toString();
    }
}

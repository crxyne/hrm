package org.crayne.hrm.api.level.data.object.type.trigger.item.pickup;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.Trigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.ItemTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class PickupTrigger extends Trigger implements ItemTrigger {

    private int count;
    private int itemID;

    public PickupTrigger(final int objectID, final float positionX, final float positionY,
                         final int count, final int itemID) {
        super(objectID, positionX, positionY);
        this.count = count;
        this.itemID = itemID;
    }

    public PickupTrigger(@NotNull final LevelObject levelObject, final int count, final int itemID) {
        super(levelObject);
        this.count = count;
        this.itemID = itemID;
    }

    public PickupTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.count = objectProperties.integerProperty(LevelObjectProperty.COUNT);
        this.itemID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.COUNT, count);
        properties.putIntProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID, itemID);

        return properties;
    }

    public int count() {
        return count;
    }

    public void count(final int count) {
        this.count = count;
    }

    public int itemID() {
        return itemID;
    }

    public void itemID(final int itemID) {
        this.itemID = itemID;
    }

    @NotNull
    public String toString() {
        return "PickupTrigger{" +
                "count=" + count +
                ", itemID=" + itemID +
                "} " + super.toString();
    }
}

package org.crayne.hrm.api.level.data.object.type.trigger.item.count;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.type.ItemTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.crayne.hrm.api.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class CountTrigger extends ToggleTrigger implements ItemTrigger {

    private int itemID;
    private int count;
    private boolean multiActivate;

    public CountTrigger(final int objectID, final float positionX, final float positionY,
                        final int targetGroupID, final boolean activateGroup, final int itemID,
                        final int count, final boolean multiActivate) {
        super(objectID, positionX, positionY, targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.multiActivate = multiActivate;
    }

    public CountTrigger(@NotNull final LevelObject levelObject, final int targetGroupID, final boolean activateGroup,
                        final int itemID, final int count, final boolean multiActivate) {
        super(levelObject, targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.multiActivate = multiActivate;
    }

    public CountTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public CountTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public CountTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.itemID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
        this.count = objectProperties.integerProperty(LevelObjectProperty.COUNT);
        this.multiActivate = objectProperties.booleanProperty(LevelObjectProperty.COUNT_MULTI_ACTIVATE);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1611);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID, itemID);
        properties.putIntProperty(LevelObjectProperty.COUNT, count);
        properties.putBooleanProperty(LevelObjectProperty.COUNT_MULTI_ACTIVATE, multiActivate);

        return properties;
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

    @NotNull
    public String toString() {
        return "CountTrigger{" +
                "itemID=" + itemID +
                ", count=" + count +
                ", multiActivate=" + multiActivate +
                "} " + super.toString();
    }

}

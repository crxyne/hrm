package org.crayne.hrm.api.level.data.object.type.trigger.item.pickup;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.decoration.ColorableObject;
import org.crayne.hrm.api.level.data.object.type.trigger.type.ItemTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.type.TargetTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class PickupItemObject extends ColorableObject implements TargetTrigger, ItemTrigger {

    private int targetGroupID;
    private boolean activateGroup;

    private boolean subtractCount;

    @NotNull
    private Mode pickupMode;
    private int itemID;

    public PickupItemObject(final int objectID, final float positionX, final float positionY, final int targetGroupID,
                            final boolean activateGroup) {
        super(objectID, positionX, positionY);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
        this.subtractCount = false;
        this.pickupMode = Mode.TOGGLE;
        this.itemID = 0;
    }

    public PickupItemObject(@NotNull final LevelObject levelObject, final int targetGroupID, final boolean activateGroup) {
        super(levelObject);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
        this.subtractCount = false;
        this.pickupMode = Mode.TOGGLE;
        this.itemID = 0;
    }

    public PickupItemObject(final int objectID, final float positionX, final float positionY, final boolean subtractCount, final int itemID) {
        super(objectID, positionX, positionY);
        this.subtractCount = subtractCount;
        this.itemID = itemID;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItemObject(@NotNull final LevelObject levelObject, final boolean subtractCount, final int itemID) {
        super(levelObject);
        this.subtractCount = subtractCount;
        this.itemID = itemID;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItemObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.subtractCount = false;
        this.itemID = 0;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItemObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.subtractCount = false;
        this.itemID = 0;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItemObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.subtractCount = objectProperties.booleanProperty(LevelObjectProperty.SUBTRACT_COUNT);
        this.pickupMode = objectProperties.pickupItemModeProperty(LevelObjectProperty.PICKUP_MODE);
        this.itemID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.activateGroup = objectProperties.booleanProperty(LevelObjectProperty.ACTIVATE_GROUP);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1275, 1587, 1589, 1598, 1614);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putBooleanProperty(LevelObjectProperty.SUBTRACT_COUNT, subtractCount);
        properties.putPickupItemModeProperty(LevelObjectProperty.PICKUP_MODE, pickupMode);
        properties.putIntProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID, itemID);
        properties.putIntProperty(LevelObjectProperty.TARGET_GROUP_ID, targetGroupID);
        properties.putBooleanProperty(LevelObjectProperty.ACTIVATE_GROUP, activateGroup);

        return properties;
    }

    public enum Mode {
        NONE(0),
        PICKUP(1),
        TOGGLE(2);

        private final int id;

        Mode(final int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }

        @NotNull
        public static PropertyDataType datatype() {
            return PropertyDataType.PICKUP_ITEM_MODE;
        }

        @NotNull
        public static Mode of(final int id) {
            return switch (id) {
                case 1 -> PICKUP;
                case 2 -> TOGGLE;
                default -> NONE;
            };
        }

    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public boolean activateGroup() {
        return activateGroup;
    }

    public void activateGroup(final boolean activateGroup) {
        this.activateGroup = activateGroup;
    }

    public boolean subtractCount() {
        return subtractCount;
    }

    public void subtractCount(final boolean subtractCount) {
        this.subtractCount = subtractCount;
    }

    @NotNull
    public Mode pickupMode() {
        return pickupMode;
    }

    public void pickupMode(@NotNull final Mode pickupMode) {
        this.pickupMode = pickupMode;
    }

    public int itemID() {
        return itemID;
    }

    public void itemID(final int itemBlockID) {
        this.itemID = itemBlockID;
    }

    @NotNull
    public String toString() {
        return "PickupItemObject{" +
                "targetGroupID=" + targetGroupID +
                ", activateGroup=" + activateGroup +
                ", subtractCount=" + subtractCount +
                ", pickupMode=" + pickupMode +
                ", itemID=" + itemID +
                "} " + super.toString();
    }
}

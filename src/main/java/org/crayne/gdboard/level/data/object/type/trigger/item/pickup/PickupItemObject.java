package org.crayne.gdboard.level.data.object.type.trigger.item.pickup;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.decoration.ColorableObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyDataType;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class PickupItem extends ColorableObject {

    private int targetGroupID;
    private boolean activateGroup;

    private boolean subtractCount;

    @NotNull
    private Mode pickupMode;
    private int itemID;

    public PickupItem(final int objectID, final float positionX, final float positionY, final int targetGroupID,
                      final boolean activateGroup) {
        super(objectID, positionX, positionY);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
        this.subtractCount = false;
        this.pickupMode = Mode.TOGGLE;
        this.itemID = 0;
    }

    public PickupItem(@NotNull final LevelObject levelObject, final int targetGroupID, final boolean activateGroup) {
        super(levelObject);
        this.targetGroupID = targetGroupID;
        this.activateGroup = activateGroup;
        this.subtractCount = false;
        this.pickupMode = Mode.TOGGLE;
        this.itemID = 0;
    }

    public PickupItem(final int objectID, final float positionX, final float positionY, final boolean subtractCount, final int itemID) {
        super(objectID, positionX, positionY);
        this.subtractCount = subtractCount;
        this.itemID = itemID;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItem(@NotNull final LevelObject levelObject, final boolean subtractCount, final int itemID) {
        super(levelObject);
        this.subtractCount = subtractCount;
        this.itemID = itemID;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItem(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.subtractCount = false;
        this.itemID = 0;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItem(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.subtractCount = false;
        this.itemID = 0;
        this.pickupMode = Mode.PICKUP;
    }

    public PickupItem(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.subtractCount = objectProperties.booleanProperty(LevelObjectData.SUBTRACT_COUNT);
        this.pickupMode = objectProperties.pickupItemModeProperty(LevelObjectData.PICKUP_MODE);
        this.itemID = objectProperties.integerProperty(LevelObjectData.ITEM_OR_BLOCK_ID);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectData.TARGET_GROUP_ID);
        this.activateGroup = objectProperties.booleanProperty(LevelObjectData.ACTIVATE_GROUP);
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

    public int itemBlockID() {
        return itemID;
    }

    public void itemBlockID(final int itemBlockID) {
        this.itemID = itemBlockID;
    }

}

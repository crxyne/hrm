package org.crayne.gdboard.level.data.object.type.trigger.item.count;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyDataType;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class InstantCountTrigger extends ToggleTrigger {

    private int itemID; // 80
    private int count; // 77

    @NotNull
    private Comparison instantCountComparison; // 88

    public InstantCountTrigger(final int objectID, final float positionX, final float positionY,
                               final int targetGroupID, final boolean activateGroup, final int itemID,
                               final int count, @NotNull final Comparison instantCountComparison) {
        super(objectID, positionX, positionY, targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.instantCountComparison = instantCountComparison;
    }

    public InstantCountTrigger(@NotNull final LevelObject levelObject, final int targetGroupID, final boolean activateGroup,
                               final int itemID, final int count, @NotNull final Comparison instantCountComparison) {
        super(levelObject, targetGroupID, activateGroup);
        this.itemID = itemID;
        this.count = count;
        this.instantCountComparison = instantCountComparison;
    }

    public InstantCountTrigger(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        this.instantCountComparison = Comparison.EQUALS;
    }

    public InstantCountTrigger(@NotNull final LevelObject levelObject) {
        super(levelObject);
        this.instantCountComparison = Comparison.EQUALS;
    }

    public InstantCountTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.itemID = objectProperties.integerProperty(LevelObjectData.ITEM_OR_BLOCK_ID);
        this.count = objectProperties.integerProperty(LevelObjectData.COUNT);
        this.instantCountComparison = objectProperties.instantCountComparisonProperty(LevelObjectData.INSTANT_COUNT_COMPARISON);
    }

    public enum Comparison {
        EQUALS(0),
        LARGER(1),
        SMALLER(2);

        private final int id;

        Comparison(final int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }

        @NotNull
        public static PropertyDataType datatype() {
            return PropertyDataType.INSTANT_COUNT_COMPARISON;
        }

        @NotNull
        public static Comparison of(final int id) {
            return switch (id) {
                case 1 -> LARGER;
                case 2 -> SMALLER;
                default -> EQUALS;
            };
        }

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

    @NotNull
    public Comparison instantCountComparison() {
        return instantCountComparison;
    }

    public void instantCountComparison(@NotNull final Comparison instantCountComparison) {
        this.instantCountComparison = instantCountComparison;
    }

    @NotNull
    public String toString() {
        return "InstantCountTrigger{" +
                "itemID=" + itemID +
                ", count=" + count +
                ", instantCountComparison=" + instantCountComparison +
                "} " + super.toString();
    }
}

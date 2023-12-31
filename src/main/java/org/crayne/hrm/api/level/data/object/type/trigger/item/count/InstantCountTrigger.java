package org.crayne.hrm.api.level.data.object.type.trigger.item.count;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.trigger.type.ItemTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.crayne.hrm.api.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class InstantCountTrigger extends ToggleTrigger implements ItemTrigger {

    private int itemID;
    private int count;

    @NotNull
    private Comparison instantCountComparison;

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
        this.itemID = objectProperties.integerProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID);
        this.count = objectProperties.integerProperty(LevelObjectProperty.COUNT);
        this.instantCountComparison = objectProperties.instantCountComparisonProperty(LevelObjectProperty.INSTANT_COUNT_COMPARISON);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1811);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.ITEM_OR_BLOCK_ID, itemID);
        properties.putIntProperty(LevelObjectProperty.COUNT, count);
        properties.putInstantCountComparisonProperty(LevelObjectProperty.INSTANT_COUNT_COMPARISON, instantCountComparison);

        return properties;
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

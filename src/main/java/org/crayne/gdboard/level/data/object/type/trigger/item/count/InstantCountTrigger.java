package org.crayne.gdboard.level.data.object.type.trigger.item;

import org.crayne.gdboard.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.jetbrains.annotations.NotNull;

public class InstantCountTrigger extends ToggleTrigger {

    private int itemID; // 80
    private int count; // 77

    @NotNull
    private Comparison instantCountComparison; // 88

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
        public static Comparison of(final int id) {
            return switch (id) {
                case 1 -> LARGER;
                case 2 -> SMALLER;
                default -> EQUALS;
            };
        }

    }

    public InstantCountTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int targetGroupID,
                               final boolean activateGroup, final int itemBlockID, final int count,
                               @NotNull final Comparison instantCountComparison) {
        super(spawnTriggered, multiTriggered, targetGroupID, activateGroup);
        this.itemID = itemBlockID;
        this.count = count;
        this.instantCountComparison = instantCountComparison;
    }

    public InstantCountTrigger(final boolean touchTriggered, final int targetGroupID, final boolean activateGroup,
                               final int itemBlockID, final int count, @NotNull final Comparison instantCountComparison) {
        super(touchTriggered, targetGroupID, activateGroup);
        this.itemID = itemBlockID;
        this.count = count;
        this.instantCountComparison = instantCountComparison;
    }

    public InstantCountTrigger(final int targetGroupID, final boolean activateGroup, final int itemBlockID,
                               final int count, @NotNull final Comparison instantCountComparison) {
        super(targetGroupID, activateGroup);
        this.itemID = itemBlockID;
        this.count = count;
        this.instantCountComparison = instantCountComparison;
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
}

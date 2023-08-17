package org.crayne.gdboard.level.data.settings;

public class LevelCosmeticSettings {

    private int backgroundID, groundID, groundLineID, fontID;

    public LevelCosmeticSettings(final int backgroundID, final int groundID, final int groundLineID, final int fontID) {
        this.backgroundID = backgroundID;
        this.groundID = groundID;
        this.groundLineID = groundLineID;
        this.fontID = fontID;
    }

    public int backgroundID() {
        return backgroundID;
    }

    public void backgroundID(final int backgroundID) {
        this.backgroundID = backgroundID;
    }

    public int groundID() {
        return groundID;
    }

    public void groundID(final int groundID) {
        this.groundID = groundID;
    }

    public int groundLineID() {
        return groundLineID;
    }

    public void groundLineID(final int groundLineID) {
        this.groundLineID = groundLineID;
    }

    public int fontID() {
        return fontID;
    }

    public void fontID(final int fontID) {
        this.fontID = fontID;
    }
}

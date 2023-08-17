package org.crayne.gdboard.level.data.settings;

import org.crayne.gdboard.savefile.property.PropertyUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@SuppressWarnings("unused")
public class LevelCosmetics {

    private int backgroundID, groundID, groundLineID, fontID;

    public LevelCosmetics(final int backgroundID, final int groundID, final int groundLineID, final int fontID) {
        this.backgroundID = backgroundID;
        this.groundID = groundID;
        this.groundLineID = groundLineID;
        this.fontID = fontID;
    }

    public LevelCosmetics(@NotNull final Map<String, String> levelSettings) {
        this.backgroundID = PropertyUtil.parseIntValue(levelSettings.get("kA6"), 0);
        this.groundID     = PropertyUtil.parseIntValue(levelSettings.get("kA7"), 0);
        this.groundLineID = PropertyUtil.parseIntValue(levelSettings.get("kA17"), 0);
        this.fontID       = PropertyUtil.parseIntValue(levelSettings.get("kA18"), 0);
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

    @NotNull
    public String toString() {
        return "LevelCosmetics{" +
                "backgroundID=" + backgroundID +
                ", groundID=" + groundID +
                ", groundLineID=" + groundLineID +
                ", fontID=" + fontID +
                '}';
    }

}

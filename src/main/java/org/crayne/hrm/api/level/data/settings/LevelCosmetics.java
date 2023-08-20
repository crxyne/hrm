package org.crayne.hrm.api.level.data.settings;

import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

@SuppressWarnings("unused")
public class LevelCosmetics {

    private int backgroundID, groundID, groundLineID, fontID;

    public LevelCosmetics(final int backgroundID, final int groundID, final int groundLineID, final int fontID) {
        this.backgroundID = backgroundID;
        this.groundID = groundID;
        this.groundLineID = groundLineID;
        this.fontID = fontID;
    }

    public LevelCosmetics() {
        this.backgroundID = 0;
        this.groundID = 0;
        this.groundLineID = 0;
        this.fontID = 0;
    }

    public LevelCosmetics(@NotNull final Properties levelSettings) {
        this.backgroundID = levelSettings.integerProperty(LevelObjectProperty.LEVEL_BACKGROUND_ID);
        this.groundID     = levelSettings.integerProperty(LevelObjectProperty.LEVEL_GROUND_ID);
        this.groundLineID = levelSettings.integerProperty(LevelObjectProperty.LEVEL_GROUND_LINE_ID);
        this.fontID       = levelSettings.integerProperty(LevelObjectProperty.LEVEL_FONT_ID);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = new Properties(new HashMap<>());
        properties.putIntProperty(LevelObjectProperty.LEVEL_BACKGROUND_ID, backgroundID);
        properties.putIntProperty(LevelObjectProperty.LEVEL_GROUND_ID, groundID);
        properties.putIntProperty(LevelObjectProperty.LEVEL_GROUND_LINE_ID, groundLineID);
        properties.putIntProperty(LevelObjectProperty.LEVEL_FONT_ID, fontID);

        return properties;
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

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelCosmetics that = (LevelCosmetics) o;

        if (backgroundID != that.backgroundID) return false;
        if (groundID != that.groundID) return false;
        if (groundLineID != that.groundLineID) return false;
        return fontID == that.fontID;
    }

    public int hashCode() {
        int result = backgroundID;
        result = 31 * result + groundID;
        result = 31 * result + groundLineID;
        result = 31 * result + fontID;
        return result;
    }
}

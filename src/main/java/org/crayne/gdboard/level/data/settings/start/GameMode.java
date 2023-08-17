package org.crayne.gdboard.level.data.settings.start;

import org.crayne.gdboard.savefile.property.PropertyDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum GameMode {

    CUBE(0),
    SHIP(1),
    BALL(2),
    UFO(3),
    WAVE(4),
    ROBOT(5),
    SPIDER(6),

    UNKNOWN(-1);

    private final int id;

    GameMode(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    @NotNull
    public static PropertyDataType datatype() {
        return PropertyDataType.GAME_MODE;
    }

    @NotNull
    public static GameMode of(final int id) {
        return Arrays.stream(values()).filter(gamemode -> gamemode.id == id).findAny().orElse(UNKNOWN);
    }

}

package org.crayne.gdboard.level.data.settings.startobject;

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

    private final int gamemodeID;

    GameMode(final int gamemodeID) {
        this.gamemodeID = gamemodeID;
    }

    public int gamemodeID() {
        return gamemodeID;
    }

    @NotNull
    public static GameMode ofID(final int gamemodeID) {
        return Arrays.stream(values()).filter(gamemode -> gamemode.gamemodeID == gamemodeID).findAny().orElse(UNKNOWN);
    }

}

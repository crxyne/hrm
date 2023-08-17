package org.crayne.gdboard.level.data.settings.startobject;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum GameSpeed {

    NORMAL(0),
    SLOW(1),
    DOUBLE(2),
    TRIPLE(3),
    QUADRUPLE(4),
    UNKNOWN(-1);

    private final int gamespeedID;

    GameSpeed(final int gamespeedID) {
        this.gamespeedID = gamespeedID;
    }

    public int gameSpeedID() {
        return gamespeedID;
    }

    @NotNull
    public static GameSpeed ofID(final int gamespeedID) {
        return Arrays.stream(values()).filter(gameSpeed -> gameSpeed.gamespeedID == gamespeedID).findAny().orElse(UNKNOWN);
    }

}

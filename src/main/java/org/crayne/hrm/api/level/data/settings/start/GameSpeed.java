package org.crayne.hrm.api.level.data.settings.start;

import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum GameSpeed {

    NORMAL(0),
    SLOW(1),
    DOUBLE(2),
    TRIPLE(3),
    QUADRUPLE(4),
    UNKNOWN(-1);

    private final int id;

    GameSpeed(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    @NotNull
    public static PropertyDataType datatype() {
        return PropertyDataType.GAME_SPEED;
    }

    @NotNull
    public static GameSpeed of(final int id) {
        return Arrays.stream(values()).filter(gameSpeed -> gameSpeed.id == id).findAny().orElse(UNKNOWN);
    }

}

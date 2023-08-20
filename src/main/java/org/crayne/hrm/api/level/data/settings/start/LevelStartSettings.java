package org.crayne.hrm.api.level.data.settings.start;

import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.level.data.object.type.trigger.general.StartPositionTrigger;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LevelStartSettings extends StartPositionTrigger {

    public LevelStartSettings(@NotNull final GameMode gameMode, @NotNull final GameSpeed gameSpeed,
                              final boolean miniGameMode, final boolean dualGameMode, final boolean flipGravity) {
        super(0, 0, 0, gameMode, gameSpeed, miniGameMode, dualGameMode, flipGravity, false);
    }

    public LevelStartSettings() {
        super(0, 0, 0, GameMode.CUBE, GameSpeed.NORMAL, false, false, false, false);
    }

    public LevelStartSettings(@NotNull final Properties objectProperties) {
        super(objectProperties);
    }

    @NotNull
    public String toString() {
        return "LevelStartSettings{" +
                "gameMode=" + gameMode() +
                ", gameSpeed=" + gameSpeed() +
                ", miniGameMode=" + miniGameMode() +
                ", dualGameMode=" + dualGameMode() +
                ", flipGravity=" + flipGravity() +
                '}';
    }

}

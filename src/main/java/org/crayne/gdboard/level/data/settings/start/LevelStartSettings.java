package org.crayne.gdboard.level.data.settings.start;

import org.crayne.gdboard.level.data.object.type.trigger.general.StartPositionTrigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LevelStartSettings extends StartPositionTrigger {

    public LevelStartSettings(@NotNull final GameMode gameMode, @NotNull final GameSpeed gameSpeed,
                              final boolean miniGameMode, final boolean dualGameMode, final boolean flipGravity) {
        super(0, 0, 0, gameMode, gameSpeed, miniGameMode, dualGameMode, flipGravity);
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

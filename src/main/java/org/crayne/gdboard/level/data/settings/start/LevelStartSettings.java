package org.crayne.gdboard.level.data.settings.start;

import org.crayne.gdboard.decrypt.PropertyDecodeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class LevelStartObject {

    @NotNull
    private GameMode gameMode;

    @NotNull
    private GameSpeed gameSpeed;

    private boolean miniGameMode;
    private boolean dualGameMode;

    private boolean flipGravity;

    public LevelStartObject(@NotNull final GameMode gameMode, @NotNull final GameSpeed gameSpeed,
                            final boolean miniGameMode, final boolean dualGameMode, final boolean flipGravity) {
        this.gameMode = gameMode;
        this.gameSpeed = gameSpeed;
        this.miniGameMode = miniGameMode;
        this.dualGameMode = dualGameMode;
        this.flipGravity = flipGravity;
    }

    public LevelStartObject(@NotNull final Map<String, String> levelSettings) {
        this.gameMode     = GameMode.ofID(PropertyDecodeUtil.parseIntValue(levelSettings.get("kA2"), -1));
        this.gameSpeed    = GameSpeed.ofID(PropertyDecodeUtil.parseIntValue(levelSettings.get("kA4"), -1));
        this.miniGameMode = PropertyDecodeUtil.parseBooleanValue(levelSettings.get("kA3"));
        this.dualGameMode = PropertyDecodeUtil.parseBooleanValue(levelSettings.get("kA8"));
        this.flipGravity  = PropertyDecodeUtil.parseBooleanValue(levelSettings.get("kA11"));
    }

    @NotNull
    public GameMode gameMode() {
        return gameMode;
    }

    public void gameMode(@NotNull final GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @NotNull
    public GameSpeed gameSpeed() {
        return gameSpeed;
    }

    public void gameSpeed(@NotNull final GameSpeed gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public boolean miniGameMode() {
        return miniGameMode;
    }

    public void miniGameMode(final boolean miniGameMode) {
        this.miniGameMode = miniGameMode;
    }

    public boolean dualGameMode() {
        return dualGameMode;
    }

    public void dualGameMode(final boolean dualGameMode) {
        this.dualGameMode = dualGameMode;
    }

    public boolean flipGravity() {
        return flipGravity;
    }

    public void flipGravity(final boolean flipGravity) {
        this.flipGravity = flipGravity;
    }

    @NotNull
    public String toString() {
        return "LevelStartObject{" +
                "gameMode=" + gameMode +
                ", gameSpeed=" + gameSpeed +
                ", miniGameMode=" + miniGameMode +
                ", dualGameMode=" + dualGameMode +
                ", flipGravity=" + flipGravity +
                '}';
    }

}

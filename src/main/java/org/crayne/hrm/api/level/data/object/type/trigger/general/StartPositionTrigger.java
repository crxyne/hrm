package org.crayne.hrm.api.level.data.object.type.trigger.general;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.settings.start.GameMode;
import org.crayne.hrm.api.level.data.settings.start.GameSpeed;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class StartPositionTrigger extends LevelObject {

    @NotNull
    private GameMode gameMode;

    @NotNull
    private GameSpeed gameSpeed;

    private boolean miniGameMode;
    private boolean dualGameMode;

    private boolean flipGravity;
    private final boolean startObject;

    public StartPositionTrigger(final int objectID, final float positionX, final float positionY, @NotNull final GameMode gameMode,
                                @NotNull final GameSpeed gameSpeed, final boolean miniGameMode, final boolean dualGameMode, final boolean flipGravity, final boolean startObject) {
        super(objectID, positionX, positionY);
        this.gameMode = gameMode;
        this.gameSpeed = gameSpeed;
        this.miniGameMode = miniGameMode;
        this.dualGameMode = dualGameMode;
        this.flipGravity = flipGravity;
        this.startObject = startObject;
    }

    public StartPositionTrigger(@NotNull final LevelObject levelObject, @NotNull final GameMode gameMode, @NotNull final GameSpeed gameSpeed,
                                final boolean miniGameMode, final boolean dualGameMode, final boolean flipGravity) {
        super(levelObject);
        this.gameMode = gameMode;
        this.gameSpeed = gameSpeed;
        this.miniGameMode = miniGameMode;
        this.dualGameMode = dualGameMode;
        this.flipGravity = flipGravity;
        this.startObject = true;
    }

    public StartPositionTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.gameMode     = objectProperties.gameModeProperty(LevelObjectProperty.START_POS_GAME_MODE);
        this.gameSpeed    = objectProperties.gameSpeedProperty(LevelObjectProperty.START_POS_GAME_SPEED);
        this.miniGameMode = objectProperties.booleanProperty(LevelObjectProperty.START_POS_MINI_MODE);
        this.dualGameMode = objectProperties.booleanProperty(LevelObjectProperty.START_POS_DUAL_MODE);
        this.startObject  = objectProperties.booleanProperty(LevelObjectProperty.START_POS_IS_OBJECT);
        this.flipGravity  = objectProperties.booleanProperty(LevelObjectProperty.START_POS_FLIP_GRAVITY);
    }


    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(31);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putGameModeProperty(LevelObjectProperty.START_POS_GAME_MODE, gameMode);
        properties.putGameSpeedProperty(LevelObjectProperty.START_POS_GAME_SPEED, gameSpeed);
        properties.putBooleanProperty(LevelObjectProperty.START_POS_MINI_MODE, miniGameMode);
        properties.putBooleanProperty(LevelObjectProperty.START_POS_DUAL_MODE, dualGameMode);
        properties.putBooleanProperty(LevelObjectProperty.START_POS_IS_OBJECT, startObject);
        properties.putBooleanProperty(LevelObjectProperty.START_POS_FLIP_GRAVITY, flipGravity);

        return properties;
    }

    public boolean startObject() {
        return startObject;
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
        return "StartPositionTrigger{" +
                "gameMode=" + gameMode +
                ", gameSpeed=" + gameSpeed +
                ", miniGameMode=" + miniGameMode +
                ", dualGameMode=" + dualGameMode +
                ", flipGravity=" + flipGravity +
                ", startObject=" + startObject +
                "} " + super.toString();
    }
}

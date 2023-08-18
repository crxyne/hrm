package org.crayne.gdboard.level.data.object.type.trigger.general;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
@SuppressWarnings("unused")
public class SpawnTrigger extends Trigger {

    private int targetGroupID; // 51
    private float spawnDelay; // 63
    private boolean editorDisable; // 102

    public SpawnTrigger(final int objectID, final float positionX, final float positionY, final boolean touchTriggered,
                        final int targetGroupID, final float spawnDelay, final boolean editorDisable) {
        super(objectID, positionX, positionY, touchTriggered);
        this.targetGroupID = targetGroupID;
        this.spawnDelay = spawnDelay;
        this.editorDisable = editorDisable;
    }

    public SpawnTrigger(@NotNull final LevelObject levelObject, final boolean touchTriggered, final int targetGroupID,
                        final float spawnDelay, final boolean editorDisable) {
        super(levelObject, touchTriggered);
        this.targetGroupID = targetGroupID;
        this.spawnDelay = spawnDelay;
        this.editorDisable = editorDisable;
    }

    public SpawnTrigger(final int objectID, final float positionX, final float positionY, final boolean spawnTriggered,
                        final boolean multiTriggered, final int targetGroupID, final float spawnDelay, final boolean editorDisable) {
        super(objectID, positionX, positionY, spawnTriggered, multiTriggered);
        this.targetGroupID = targetGroupID;
        this.spawnDelay = spawnDelay;
        this.editorDisable = editorDisable;
    }

    public SpawnTrigger(@NotNull final LevelObject levelObject, final boolean spawnTriggered, final boolean multiTriggered,
                        final int targetGroupID, final float spawnDelay, final boolean editorDisable) {
        super(levelObject, spawnTriggered, multiTriggered);
        this.targetGroupID = targetGroupID;
        this.spawnDelay = spawnDelay;
        this.editorDisable = editorDisable;
    }

    public SpawnTrigger(final int objectID, final float positionX, final float positionY, final int targetGroupID,
                        final float spawnDelay, final boolean editorDisable) {
        super(objectID, positionX, positionY);
        this.targetGroupID = targetGroupID;
        this.spawnDelay = spawnDelay;
        this.editorDisable = editorDisable;
    }

    public SpawnTrigger(@NotNull final LevelObject levelObject, final int targetGroupID, final float spawnDelay,
                        final boolean editorDisable) {
        super(levelObject);
        this.targetGroupID = targetGroupID;
        this.spawnDelay = spawnDelay;
        this.editorDisable = editorDisable;
    }

    public SpawnTrigger(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.targetGroupID = objectProperties.integerProperty(LevelObjectProperty.TARGET_GROUP_ID);
        this.spawnDelay = objectProperties.floatProperty(LevelObjectProperty.SPAWN_DELAY);
        this.editorDisable = objectProperties.booleanProperty(LevelObjectProperty.SPAWN_EDITOR_DISABLE);
    }

    public int targetGroupID() {
        return targetGroupID;
    }

    public void targetGroupID(final int targetGroupID) {
        this.targetGroupID = targetGroupID;
    }

    public float spawnDelay() {
        return spawnDelay;
    }

    public void spawnDelay(final float spawnDelay) {
        this.spawnDelay = spawnDelay;
    }

    public boolean editorDisable() {
        return editorDisable;
    }

    public void editorDisable(final boolean editorDisable) {
        this.editorDisable = editorDisable;
    }

    @NotNull
    public String toString() {
        return "SpawnTrigger{" +
                "targetGroupID=" + targetGroupID +
                ", spawnDelay=" + spawnDelay +
                ", editorDisable=" + editorDisable +
                "} " + super.toString();
    }
}

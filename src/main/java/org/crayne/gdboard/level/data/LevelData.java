package org.crayne.gdboard.level.data;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.settings.LevelSettings;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class LevelData {

    @NotNull
    private final LevelSettings levelSettings;

    @NotNull
    private final Set<LevelObject> levelObjects;

    public LevelData(@NotNull final LevelSettings settings, @NotNull final Set<LevelObject> levelObjects) {
        this.levelSettings = settings;
        this.levelObjects = levelObjects;
    }

    @NotNull
    public LevelSettings levelSettings() {
        return levelSettings;
    }

    @NotNull
    public Set<LevelObject> levelObjects() {
        return levelObjects;
    }

    @NotNull
    public String toString() {
        return "LevelData{" +
                "levelSettings=" + levelSettings +
                ", levelObjects=" + levelObjects +
                '}';
    }

}

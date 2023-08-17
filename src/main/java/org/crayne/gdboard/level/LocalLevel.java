package org.crayne.gdboard.level;

import org.crayne.gdboard.level.data.LevelData;
import org.jetbrains.annotations.NotNull;

public record LocalLevel(@NotNull LocalLevelProperties properties, @NotNull LevelData data) {
    @NotNull
    public String toString() {
        return "LocalLevel{" +
                "properties=" + properties +
                ", data=" + data +
                '}';
    }

}

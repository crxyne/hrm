package org.crayne.gdboard.level;

import org.jetbrains.annotations.NotNull;

public class LocalLevelData {

    private final LocalLevelProperties properties;

    public LocalLevelData(@NotNull final LocalLevelProperties properties) {
        this.properties = properties;
    }

    @NotNull
    public LocalLevelProperties properties() {
        return properties;
    }

}

package org.crayne.gdboard.savefile.decrypt;

import org.jetbrains.annotations.NotNull;

public class LevelDecryptionException extends RuntimeException {

    public LevelDecryptionException(@NotNull final String message) {
        super(message);
    }

    public LevelDecryptionException(@NotNull final Throwable t) {
        super(t);
    }

    public LevelDecryptionException() {
        super();
    }

}

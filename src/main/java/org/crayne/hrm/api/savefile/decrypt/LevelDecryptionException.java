package org.crayne.hrm.api.savefile.decrypt;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
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

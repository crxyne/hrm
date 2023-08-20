package org.crayne.hrm.api.savefile.encrypt;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LevelEncryptionException extends RuntimeException {

    public LevelEncryptionException(@NotNull final String message) {
        super(message);
    }

    public LevelEncryptionException(@NotNull final Throwable t) {
        super(t);
    }

    public LevelEncryptionException() {
        super();
    }

}

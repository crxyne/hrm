package org.crayne.hrm.repository.commit;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class HRMCommitDecodeException extends RuntimeException {

    public HRMCommitDecodeException() {
        super();
    }

    public HRMCommitDecodeException(@NotNull final Throwable t) {
        super(t);
    }

    public HRMCommitDecodeException(@NotNull final String message) {
        super(message);
    }

}

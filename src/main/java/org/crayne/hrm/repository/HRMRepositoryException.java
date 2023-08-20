package org.crayne.hrm.repository;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class HRMRepositoryException extends RuntimeException {

    public HRMRepositoryException(@NotNull final Throwable t) {
        super(t);
    }

    public HRMRepositoryException() {
        super();
    }

    public HRMRepositoryException(@NotNull final String msg) {
        super(msg);
    }

}

package org.crayne.hrmcli.util.config;

import org.jetbrains.annotations.NotNull;

public class HRMConfigException extends RuntimeException {

    public HRMConfigException(@NotNull final Throwable t) {
        super(t);
    }

    public HRMConfigException() {
        super();
    }

    public HRMConfigException(@NotNull final String msg) {
        super(msg);
    }

}

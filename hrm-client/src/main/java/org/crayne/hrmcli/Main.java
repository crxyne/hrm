package org.crayne.hrmcli;

import org.jetbrains.annotations.NotNull;

public class Main {

    public static void main(@NotNull final String... args) {
        final HRMClient client = new HRMClient(args);
        client.runCommand(args);
    }

}
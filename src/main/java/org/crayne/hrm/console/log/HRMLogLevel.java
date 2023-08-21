package org.crayne.hrm.console.log;

import org.crayne.hrm.console.ansi.AnsiColor;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public enum HRMLogLevel {

    INFO(AnsiColor.foreground(Color.LIGHT_GRAY)),
    DEBUG(new AnsiColor(Color.BLACK, Color.MAGENTA)),
    WARN(AnsiColor.foreground(Color.YELLOW)),
    ERROR(AnsiColor.foreground(Color.RED)),
    FATAL(new AnsiColor(Color.BLACK, Color.RED)),
    SUCCESS(AnsiColor.foreground(Color.GREEN)),
    NONE(null);

    @Nullable
    private final AnsiColor color;

    HRMLogLevel(@Nullable final AnsiColor color) {
        this.color = color;
    }

    @Nullable
    public AnsiColor color() {
        return color;
    }

}

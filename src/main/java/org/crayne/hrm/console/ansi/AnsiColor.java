package org.crayne.hrm.console.ansi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@SuppressWarnings("unused")
public class AnsiColor {

    @Nullable
    private final Color fg;

    @Nullable
    private final Color bg;
    private final boolean @NotNull [] flags;

    protected AnsiColor(@Nullable final Color fg, @Nullable final Color bg, final boolean @NotNull [] flags) {
        if (flags.length != 9) throw new IllegalArgumentException("Expected exactly 12 color flags (reset, bold, dim, italic, underline, blinking, inverse, hidden, strikethrough)");
        this.fg = fg;
        this.bg = bg;
        this.flags = flags;
    }

    public AnsiColor(@Nullable final Color fg, @Nullable final Color bg) {
        this.fg = fg;
        this.bg = bg;
        this.flags = new boolean[9];
    }

    @NotNull
    public static AnsiColor foreground(@NotNull final Color fg) {
        return new AnsiColor(fg, null);
    }

    @NotNull
    public static AnsiColor background(@NotNull final Color bg) {
        return new AnsiColor(null, bg);
    }


    @NotNull
    public static final String ANSI_BEGIN = "\33[";

    @NotNull
    private static final String RGB_BEGIN = "8;2;";

    @NotNull
    public static final String RESET = ANSI_BEGIN + "0m";

    @NotNull
    public static final String BOLD = ANSI_BEGIN + "1m";

    @NotNull
    public static final String DIM = ANSI_BEGIN + "2m";

    @NotNull
    public static final String ITALIC = ANSI_BEGIN + "3m";

    @NotNull
    public static final String UNDERLINE = ANSI_BEGIN + "4m";

    @NotNull
    public static final String BLINKING = ANSI_BEGIN + "5m";

    @NotNull
    public static final String INVERSE = ANSI_BEGIN + "7m";

    @NotNull
    public static final String HIDDEN = ANSI_BEGIN + "8m";

    @NotNull
    public static final String STRIKETHROUGH = ANSI_BEGIN + "9m";

    @NotNull
    public static final String RGB_FG_BEGIN = ANSI_BEGIN + "3" + RGB_BEGIN;

    @NotNull
    public static final String RGB_BG_BEGIN = ANSI_BEGIN + "4" + RGB_BEGIN;

    @NotNull
    public static final AnsiColor RESET_ANSI_COLOR = new AnsiColorBuilder().reset(true).build();

    @NotNull
    private static String rgb(final int r, final int g, final int b) {
        return r + ";" + g + ";" + b + "m";
    }

    @NotNull
    public static String fg(final int r, final int g, final int b) {
        return RGB_FG_BEGIN + rgb(r, g, b);
    }

    @NotNull
    public static String fg(final Color color) {
        return color == null ? "" : fg(color.getRed(), color.getGreen(), color.getBlue());
    }

    @NotNull
    public static String bg(final int r, final int g, final int b) {
        return RGB_BG_BEGIN + rgb(r, g, b);
    }

    @NotNull
    public static String bg(final Color color) {
        return color == null ? "" : bg(color.getRed(), color.getGreen(), color.getBlue());
    }

    @NotNull
    private static String flag(final boolean flag, final int index) {
        return flag ? ANSI_BEGIN + index + "m" : "";
    }

    public boolean flag(final int index) {
        return flags[index];
    }

    @NotNull
    private static String flags(final boolean[] flags) {
        return
                flag(flags[0], 0)
                        + flag(flags[1], 1)
                        + flag(flags[2], 2)
                        + flag(flags[3], 3)
                        + flag(flags[4], 4)
                        + flag(flags[5], 5)
                        + flag(flags[6], 7)
                        + flag(flags[7], 8)
                        + flag(flags[8], 9);
    }

    @Nullable
    public Color fg() {
        return fg;
    }

    @Nullable
    public Color bg() {
        return bg;
    }

    @NotNull
    public String toString(@NotNull final String str) {
        return fg(fg) + bg(bg) + flags(flags) + str;
    }

    @NotNull
    public String toString() {
        return toString("");
    }

}

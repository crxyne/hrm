package org.crayne.hrm.console.ansi;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings("unused")
public class AnsiColor {

    private final Color fg;
    private final Color bg;
    private final boolean[] flags;

    protected AnsiColor(final Color fg, final Color bg, final boolean[] flags) {
        if (flags.length != 9) throw new IllegalArgumentException("Expected exactly 12 color flags (reset, bold, dim, italic, underline, blinking, inverse, hidden, strikethrough)");
        this.fg = fg;
        this.bg = bg;
        this.flags = flags;
    }

    public AnsiColor(final Color fg, final Color bg) {
        this.fg = fg;
        this.bg = bg;
        this.flags = new boolean[9];
    }

    public static AnsiColor foreground(@NotNull final Color fg) {
        return new AnsiColor(fg, null);
    }

    public static AnsiColor background(@NotNull final Color bg) {
        return new AnsiColor(null, bg);
    }

    public static final String ANSI_BEGIN = "\33[";

    private static final String RGB_BEGIN = "8;2;";
    public static final String RESET = ANSI_BEGIN + "0m";
    public static final String BOLD = ANSI_BEGIN + "1m";
    public static final String DIM = ANSI_BEGIN + "2m";
    public static final String ITALIC = ANSI_BEGIN + "3m";
    public static final String UNDERLINE = ANSI_BEGIN + "4m";
    public static final String BLINKING = ANSI_BEGIN + "5m";
    public static final String INVERSE = ANSI_BEGIN + "7m";
    public static final String HIDDEN = ANSI_BEGIN + "8m";
    public static final String STRIKETHROUGH = ANSI_BEGIN + "9m";

    public static final String RGB_FG_BEGIN = ANSI_BEGIN + "3" + RGB_BEGIN;
    public static final String RGB_BG_BEGIN = ANSI_BEGIN + "4" + RGB_BEGIN;

    public static final AnsiColor RESET_ANSI_COLOR = new AnsiColorBuilder().reset(true).build();

    private static String rgb(final int r, final int g, final int b) {
        return r + ";" + g + ";" + b + "m";
    }

    public static String fg(final int r, final int g, final int b) {
        return RGB_FG_BEGIN + rgb(r, g, b);
    }

    public static String fg(final Color color) {
        return color == null ? "" : fg(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static String bg(final int r, final int g, final int b) {
        return RGB_BG_BEGIN + rgb(r, g, b);
    }

    public static String bg(final Color color) {
        return color == null ? "" : bg(color.getRed(), color.getGreen(), color.getBlue());
    }

    private static String flag(final boolean flag, final int index) {
        return flag ? ANSI_BEGIN + index + "m" : "";
    }

    public boolean flag(final int index) {
        return flags[index];
    }

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

    public Color fg() {
        return fg;
    }

    public Color bg() {
        return bg;
    }

    public String toString(@NotNull final String str) {
        return fg(fg) + bg(bg) + flags(flags);
    }

    public String toString() {
        return toString("");
    }
}

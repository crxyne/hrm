package org.crayne.hrm.console.ansi;

import java.awt.*;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class AnsiColorBuilder {

    private Color fg;
    private Color bg;
    private final boolean[] flags;

    public AnsiColorBuilder() {
        this.flags = new boolean[9];
    }

    public AnsiColorBuilder fg(final Color fg) {
        this.fg = fg;
        return this;
    }

    public AnsiColorBuilder bg(final Color bg) {
        this.bg = bg;
        return this;
    }

    public AnsiColorBuilder reset(final boolean b) {
        flags[0] = b;
        return this;
    }

    public AnsiColorBuilder bold(final boolean b) {
        flags[1] = b;
        return this;
    }

    public AnsiColorBuilder dim(final boolean b) {
        flags[2] = b;
        return this;
    }

    public AnsiColorBuilder italic(final boolean b) {
        flags[3] = b;
        return this;
    }

    public AnsiColorBuilder underline(final boolean b) {
        flags[4] = b;
        return this;
    }

    public AnsiColorBuilder blinking(final boolean b) {
        flags[5] = b;
        return this;
    }

    public AnsiColorBuilder inverted(final boolean b) {
        flags[6] = b;
        return this;
    }

    public AnsiColorBuilder hidden(final boolean b) {
        flags[7] = b;
        return this;
    }

    public AnsiColorBuilder strikethrough(final boolean b) {
        flags[8] = b;
        return this;
    }

    public Color fg() {
        return fg;
    }

    public Color bg() {
        return bg;
    }

    public boolean reset() {
        return flags[0];
    }

    public boolean bold() {
        return flags[1];
    }

    public boolean dim() {
        return flags[2];
    }

    public boolean italic() {
        return flags[3];
    }

    public boolean underline() {
        return flags[4];
    }

    public boolean blinking() {
        return flags[5];
    }

    public boolean inverted() {
        return flags[6];
    }

    public boolean hidden() {
        return flags[7];
    }

    public boolean strikethrough() {
        return flags[8];
    }

    public AnsiColor build() {
        return new AnsiColor(fg, bg, flags);
    }

}

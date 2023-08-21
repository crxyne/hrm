package org.crayne.hrm.console.ansi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Optional;

@SuppressWarnings("unused")
public class ComponentPart {

    @Nullable
    private AnsiColor color;

    @NotNull
    private final String text;

    public ComponentPart(@Nullable final AnsiColor color, @Nullable final String text) {
        this.color = color;
        this.text = text == null ? "" : text;
    }

    public ComponentPart(@NotNull final ComponentPart other) {
        this(other.color, other.text);
    }

    @NotNull
    public static ComponentPart empty() {
        return new ComponentPart(null, null);
    }

    @NotNull
    public static ComponentPart plain(@NotNull final String text) {
        return new ComponentPart(null, text);
    }

    @NotNull
    public static ComponentPart of(@Nullable final AnsiColor color, @Nullable final String text) {
        return new ComponentPart(color, text);
    }

    @NotNull
    public static ComponentPart of(@NotNull final Color color, @Nullable final String text) {
        return new ComponentPart(AnsiColor.foreground(color), text);
    }

    @NotNull
    public String text() {
        return text;
    }

    @NotNull
    public Optional<AnsiColor> color() {
        return Optional.ofNullable(color);
    }

    @NotNull
    public ComponentPart color(@Nullable final AnsiColor color) {
        this.color = color;
        return this;
    }

    @NotNull
    public String toString() {
        final boolean colorFound = color != null;
        final String resultText = text;
        if (!colorFound) return resultText;

        return (color.toString(resultText));
    }

}

package org.crayne.hrm.console.log;

import org.crayne.hrm.console.ansi.AnsiColor;
import org.crayne.hrm.console.ansi.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;
import java.io.PrintStream;

@SuppressWarnings("unused")
public class HRMLogger {

    private boolean enableAnsiEscapeSequences;

    @NotNull
    private final PrintStream outputStream;

    public HRMLogger(@Nullable final OutputStream outputStream, final boolean enableAnsiEscapeSequences) {
        this.outputStream = new PrintStream(outputStream == null ? OutputStream.nullOutputStream() : outputStream);
        this.enableAnsiEscapeSequences = enableAnsiEscapeSequences;
    }

    public HRMLogger(final boolean enableAnsiEscapeSequences) {
        this(System.out, enableAnsiEscapeSequences);
    }

    public HRMLogger() {
        this(true);
    }

    public boolean enableAnsiEscapeSequences() {
        return enableAnsiEscapeSequences;
    }

    public void enableAnsiEscapeSequences(final boolean enableAnsiEscapeSequences) {
        this.enableAnsiEscapeSequences = enableAnsiEscapeSequences;
    }

    @NotNull
    public PrintStream outputStream() {
        return outputStream;
    }

    public void log(@NotNull final HRMLogLevel logLevel, @NotNull final String message) {
        if (logLevel == HRMLogLevel.NONE) {
            outputStream.println(message);
            return;
        }
        if (!enableAnsiEscapeSequences) {
            outputStream.println("[" + logLevel.name() + "]: " + message);
            return;
        }
        outputStream.println(TextComponent.of(logLevel.color(), "[" + logLevel.name() + "]:")
                .append(TextComponent.of(AnsiColor.RESET_ANSI_COLOR, " " + message)));
    }

    public void info(@NotNull final String message) {
        log(HRMLogLevel.INFO, message);
    }

    public void debug(@NotNull final String message) {
        log(HRMLogLevel.DEBUG, message);
    }

    public void error(@NotNull final String message) {
        log(HRMLogLevel.ERROR, message);
    }

    public void fatal(@NotNull final String message) {
        log(HRMLogLevel.FATAL, message);
    }

    public void warn(@NotNull final String message) {
        log(HRMLogLevel.WARN, message);
    }

    public void none(@NotNull final String message) {
        log(HRMLogLevel.NONE, message);
    }

    public void success(@NotNull final String message) {
        log(HRMLogLevel.SUCCESS, message);
    }


}

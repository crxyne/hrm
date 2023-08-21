package org.crayne.hrm.repository.commit;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
public final class HRMCommitInfo {

    @NotNull
    private final String message;

    @NotNull
    private final String authorUsername;

    @NotNull
    private final String timestampFormatted;

    public HRMCommitInfo(@NotNull final String message, @NotNull final String authorUsername) {
        this.message = message;
        this.authorUsername = authorUsername;
        this.timestampFormatted = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    }

    @NotNull
    public String authorUsername() {
        return authorUsername;
    }

    @NotNull
    public String message() {
        return message;
    }

    @NotNull
    public String timestampFormatted() {
        return timestampFormatted;
    }

    @NotNull
    public String toString() {
        return "HRMCommitInfo{" +
                "message='" + message + '\'' +
                ", authorUsername='" + authorUsername + '\'' +
                ", timestampFormatted='" + timestampFormatted + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (HRMCommitInfo) obj;
        return Objects.equals(this.message, that.message) &&
                Objects.equals(this.timestampFormatted, that.timestampFormatted);
    }

    public int hashCode() {
        return Objects.hash(message, timestampFormatted);
    }

}

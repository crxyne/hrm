package org.crayne.hrm.repository.commit;

import org.jetbrains.annotations.NotNull;

public record HRMCommitInfo(@NotNull String message, @NotNull String timestampFormatted) {}

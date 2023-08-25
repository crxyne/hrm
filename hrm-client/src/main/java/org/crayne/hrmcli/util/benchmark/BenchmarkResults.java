package org.crayne.hrmcli.util.benchmark;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record BenchmarkResults(long worstTime, long bestTime, double averageTime, @NotNull List<Long> results) {
}

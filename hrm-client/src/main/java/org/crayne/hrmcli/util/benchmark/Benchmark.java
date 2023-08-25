package org.crayne.hrmcli.util.benchmark;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record Benchmark(@NotNull Runnable runnable) {

    @NotNull
    public BenchmarkResults runBenchmark(final int amountOfRuns) {
        final List<Long> benchmark = new ArrayList<>();
        for (int i = 0; i < amountOfRuns; i++) {
            final long start = System.currentTimeMillis();

            runnable.run();

            final long end = System.currentTimeMillis();
            benchmark.add(end - start);
        }
        return new BenchmarkResults(
                benchmark.stream().mapToLong(l -> l).max().orElse(-1),
                benchmark.stream().mapToLong(l -> l).min().orElse(-1),
                benchmark.stream().mapToLong(l -> l).average().orElse(-1),
                benchmark
        );
    }

}

package org.playground.leetcode.helpers;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AveragingOperationTimer implements AutoCloseable {
    private static final Map<String, List<Long>> history = new HashMap<>();

    private final long start = System.nanoTime();
    private final Logger logger;
    private final String message;

    public AveragingOperationTimer(Logger logger, String message) {
        this.logger = logger;
        this.message = message;
    }

    @Override
    public void close() {
        List<Long> pair = history.computeIfAbsent(message, s -> new ArrayList<>(List.of(0L, 0L)));
        pair.set(0, pair.get(0) + 1);
        pair.set(1, pair.get(1) + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
        logger.info(message, pair.get(1) / pair.get(0));
    }

    public static void reset() {
        history.clear();
    }
}

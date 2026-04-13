package com.marcosmoreira.opticademo.demo.generator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generates sequential references with a given prefix, e.g. "CL-00001", "OV-00001", "RC-00001".
 * Thread-safe via AtomicInteger per prefix.
 */
public class ReferenceGenerator {

    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();
    private final int padding;

    public ReferenceGenerator() {
        this(5);
    }

    public ReferenceGenerator(int padding) {
        this.padding = padding;
    }

    /**
     * Generates the next reference for the given prefix.
     * Example: next("CL") -> "CL-00001", then "CL-00002", etc.
     */
    public String next(String prefix) {
        AtomicInteger counter = counters.computeIfAbsent(prefix, k -> new AtomicInteger(0));
        int seq = counter.incrementAndGet();
        return String.format("%s-%0" + padding + "d", prefix, seq);
    }

    /**
     * Generates a reference with a specific sequence number (does not increment counter).
     */
    public String of(String prefix, int seq) {
        return String.format("%s-%0" + padding + "d", prefix, seq);
    }
}

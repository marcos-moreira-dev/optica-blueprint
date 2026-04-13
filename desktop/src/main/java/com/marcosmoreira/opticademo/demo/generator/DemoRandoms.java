package com.marcosmoreira.opticademo.demo.generator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility for random selection from lists, random amounts, and random states.
 */
public class DemoRandoms {

    private DemoRandoms() {
        // utility class
    }

    /**
     * Returns a random element from the given list.
     */
    public static <T> T pick(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    /**
     * Returns a random element from the given array.
     */
    @SafeVarargs
    public static <T> T pick(T... options) {
        if (options == null || options.length == 0) {
            return null;
        }
        return options[ThreadLocalRandom.current().nextInt(options.length)];
    }

    /**
     * Generates a random double between min (inclusive) and max (exclusive), rounded to 2 decimals.
     */
    public static double randomAmount(double min, double max) {
        double value = ThreadLocalRandom.current().nextDouble(min, max);
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Generates a random int between min (inclusive) and max (inclusive).
     */
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Returns true with the given probability (0.0 to 1.0).
     */
    public static boolean chance(double probability) {
        return ThreadLocalRandom.current().nextDouble() < probability;
    }
}

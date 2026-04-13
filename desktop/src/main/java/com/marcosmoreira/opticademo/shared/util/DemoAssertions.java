package com.marcosmoreira.opticademo.shared.util;

/**
 * Lightweight assertion utility for demo and validation purposes.
 * Throws IllegalArgumentException on failure.
 */
public final class DemoAssertions {

    private DemoAssertions() {
        // utility class
    }

    /**
     * Throws IllegalArgumentException if the object is null.
     *
     * @param object  the object to check
     * @param message the error message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws IllegalArgumentException if the string is null, empty, or blank.
     *
     * @param value   the string to check
     * @param message the error message
     */
    public static void notBlank(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws IllegalArgumentException if the condition is false.
     *
     * @param condition the condition that must be true
     * @param message   the error message
     */
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws IllegalArgumentException if the number is negative.
     *
     * @param value   the number to check
     * @param message the error message
     */
    public static void notNegative(Number value, String message) {
        if (value == null || value.doubleValue() < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws IllegalArgumentException if the number is zero or negative.
     *
     * @param value   the number to check
     * @param message the error message
     */
    public static void positive(Number value, String message) {
        if (value == null || value.doubleValue() <= 0) {
            throw new IllegalArgumentException(message);
        }
    }
}

package com.marcosmoreira.opticademo.shared.util;

import java.text.Normalizer;

/**
 * Utility class for common string operations.
 */
public final class StringUtils {

    private StringUtils() {
        // utility class
    }

    /**
     * Returns true if the string is null, empty, or contains only whitespace.
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Returns true if the string is not null and contains at least one non-whitespace character.
     */
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * Capitalizes the first letter of the string, lowercases the rest.
     * Returns empty string if input is blank.
     */
    public static String capitalize(String s) {
        if (isBlank(s)) {
            return "";
        }
        String trimmed = s.trim();
        if (trimmed.length() == 1) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }

    /**
     * Capitalizes the first letter of each word in the string.
     */
    public static String capitalizeWords(String s) {
        if (isBlank(s)) {
            return "";
        }
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(capitalize(words[i]));
        }
        return sb.toString();
    }

    /**
     * Truncates the string to the specified maximum length, appending "..." if truncated.
     */
    public static String truncate(String s, int maxLength) {
        if (s == null) {
            return null;
        }
        if (s.length() <= maxLength) {
            return s;
        }
        return s.substring(0, maxLength).trim() + "...";
    }

    /**
     * Normalizes a string by removing accents and converting to lowercase.
     */
    public static String normalize(String s) {
        if (isBlank(s)) {
            return "";
        }
        String normalized = Normalizer.normalize(s.trim(), Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{M}", "");
        return normalized.toLowerCase();
    }

    /**
     * Extracts initials from a full name.
     * "Juan Carlos Perez" -> "JCP"
     * Returns empty string if input is blank.
     */
    public static String initials(String fullName) {
        if (isBlank(fullName)) {
            return "";
        }
        String[] parts = fullName.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                sb.append(Character.toUpperCase(part.charAt(0)));
            }
        }
        return sb.toString();
    }

    /**
     * Returns a default string if the input is blank.
     */
    public static String defaultIfBlank(String s, String defaultValue) {
        return isBlank(s) ? defaultValue : s;
    }

    /**
     * Returns an empty string if the input is null.
     */
    public static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}

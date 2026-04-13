package com.marcosmoreira.opticademo.shared.query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;

public class FilterSupport {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FilterSupport() {
    }

    /**
     * Returns true if the text matches any of the given fields (case-insensitive).
     * Empty or null text returns true (no filter applied).
     */
    public static boolean matchesText(String text, String... fields) {
        if (text == null || text.isBlank()) {
            return true;
        }
        String normalized = text.trim().toLowerCase();
        for (String field : fields) {
            if (field != null && field.toLowerCase().contains(normalized)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the filter is null, "Todos", "all" (case-insensitive),
     * or exactly matches the value.
     */
    public static boolean matchesExact(String value, String filter) {
        if (filter == null || filter.isBlank()) {
            return true;
        }
        String normalized = filter.trim();
        if (normalized.equalsIgnoreCase("todos") ||
                normalized.equalsIgnoreCase("todas") ||
                normalized.equalsIgnoreCase("all") ||
                normalized.equalsIgnoreCase("todo")) {
            return true;
        }
        return normalized.equals(value);
    }

    /**
     * Returns true if the date falls within the range defined by from and to (dd/MM/yyyy).
     * Null or empty from/to values are treated as unbounded.
     */
    public static boolean inRange(String date, String from, String to) {
        if (date == null || date.isBlank()) {
            return true;
        }
        LocalDate target = parseDate(date);
        if (target == null) {
            return true;
        }

        if (from != null && !from.isBlank()) {
            LocalDate fromDate = parseDate(from);
            if (fromDate != null && target.isBefore(fromDate)) {
                return false;
            }
        }

        if (to != null && !to.isBlank()) {
            LocalDate toDate = parseDate(to);
            if (toDate != null && target.isAfter(toDate)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if any value in the collection matches the filter text (case-insensitive).
     * Null or empty filter returns true.
     */
    public static boolean anyMatch(Collection<String> values, String filter) {
        if (filter == null || filter.isBlank()) {
            return true;
        }
        if (values == null || values.isEmpty()) {
            return false;
        }
        String normalized = filter.trim().toLowerCase();
        for (String value : values) {
            if (value != null && value.toLowerCase().contains(normalized)) {
                return true;
            }
        }
        return false;
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date.trim(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

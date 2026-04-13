package com.marcosmoreira.opticademo.shared.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for date operations using dd/MM/yyyy format.
 */
public final class DateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateUtils() {
        // utility class
    }

    /**
     * Formats a LocalDate to dd/MM/yyyy string.
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(FORMATTER);
    }

    /**
     * Parses a dd/MM/yyyy string to LocalDate.
     *
     * @throws java.time.format.DateTimeParseException if the format is invalid
     */
    public static LocalDate parse(String date) {
        if (isBlank(date)) {
            return null;
        }
        return LocalDate.parse(date.trim(), FORMATTER);
    }

    /**
     * Parses a dd/MM/yyyy string to LocalDate, returning fallbackDate on error.
     */
    public static LocalDate parseOrDefault(String date, LocalDate fallbackDate) {
        try {
            return parse(date);
        } catch (DateTimeParseException e) {
            return fallbackDate;
        }
    }

    /**
     * Returns the number of days between two dates (positive if end is after start).
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Returns the number of days between two dd/MM/yyyy strings.
     */
    public static long daysBetween(String start, String end) {
        LocalDate s = parse(start);
        LocalDate e = parse(end);
        return daysBetween(s, e);
    }

    /**
     * Returns true if date1 is strictly before date2.
     */
    public static boolean isBefore(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.isBefore(date2);
    }

    /**
     * Returns true if dateString1 is strictly before dateString2.
     */
    public static boolean isBefore(String dateString1, String dateString2) {
        LocalDate d1 = parse(dateString1);
        LocalDate d2 = parse(dateString2);
        return isBefore(d1, d2);
    }

    /**
     * Returns true if date1 is strictly after date2.
     */
    public static boolean isAfter(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.isAfter(date2);
    }

    /**
     * Returns true if dateString1 is strictly after dateString2.
     */
    public static boolean isAfter(String dateString1, String dateString2) {
        LocalDate d1 = parse(dateString1);
        LocalDate d2 = parse(dateString2);
        return isAfter(d1, d2);
    }

    /**
     * Returns true if the given date is today.
     */
    public static boolean isToday(LocalDate date) {
        if (date == null) {
            return false;
        }
        return date.equals(LocalDate.now());
    }

    /**
     * Returns true if the dd/MM/yyyy string represents today.
     */
    public static boolean isToday(String date) {
        LocalDate d = parse(date);
        return isToday(d);
    }

    /**
     * Returns a new LocalDate with the specified number of days added.
     */
    public static LocalDate addDays(LocalDate date, long days) {
        if (date == null) {
            return null;
        }
        return date.plusDays(days);
    }

    /**
     * Returns a dd/MM/yyyy string with the specified number of days added.
     */
    public static String addDays(String date, long days) {
        LocalDate d = parse(date);
        if (d == null) {
            return "";
        }
        return format(addDays(d, days));
    }

    /**
     * Returns today's date as a dd/MM/yyyy string.
     */
    public static String today() {
        return format(LocalDate.now());
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}

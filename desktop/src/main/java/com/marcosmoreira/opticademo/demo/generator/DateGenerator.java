package com.marcosmoreira.opticademo.demo.generator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates date strings in dd/MM/yyyy format with various strategies.
 */
public class DateGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final ZoneId ZONE = ZoneId.systemDefault();

    /**
     * Formats a LocalDate to dd/MM/yyyy.
     */
    public String format(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Returns today's date as dd/MM/yyyy.
     */
    public String today() {
        return format(LocalDate.now(ZONE));
    }

    /**
     * Generates a random date between startDate and endDate (inclusive).
     */
    public String randomBetween(LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) return format(startDate);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        return format(startDate.plusDays(randomDays));
    }

    /**
     * Generates a random date in the past, up to maxDaysBack days ago.
     */
    public String pastDate(int maxDaysBack) {
        LocalDate today = LocalDate.now(ZONE);
        LocalDate start = today.minusDays(maxDaysBack);
        return randomBetween(start, today.minusDays(1));
    }

    /**
     * Generates a random future date, up to maxDaysForward days ahead.
     */
    public String futureDate(int maxDaysForward) {
        LocalDate today = LocalDate.now(ZONE);
        LocalDate end = today.plusDays(maxDaysForward);
        return randomBetween(today.plusDays(1), end);
    }

    /**
     * Returns a date that is exactly daysOffset days from today (negative = past, positive = future).
     */
    public String offsetDays(int daysOffset) {
        return format(LocalDate.now(ZONE).plusDays(daysOffset));
    }
}

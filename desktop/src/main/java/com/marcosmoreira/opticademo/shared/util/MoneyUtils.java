package com.marcosmoreira.opticademo.shared.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * Utility class for monetary operations and formatting.
 */
public final class MoneyUtils {

    private static final DecimalFormat MONEY_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        MONEY_FORMAT = new DecimalFormat("$ #,##0.00", symbols);
        MONEY_FORMAT.setMinimumFractionDigits(2);
        MONEY_FORMAT.setMaximumFractionDigits(2);
    }

    private MoneyUtils() {
        // utility class
    }

    /**
     * Formats a double value as "$ X.XX".
     */
    public static String format(double amount) {
        return MONEY_FORMAT.format(amount);
    }

    /**
     * Formats a double value; returns "$ 0.00" for null-like values.
     */
    public static String formatOrDefault(Double amount) {
        if (amount == null) {
            return "$ 0.00";
        }
        return format(amount);
    }

    /**
     * Parses a money string (e.g. "$ 1,234.56" or "1234.56") to double.
     *
     * @throws NumberFormatException if the string cannot be parsed
     */
    public static double parse(String amount) {
        if (amount == null || amount.trim().isEmpty()) {
            return 0.0;
        }
        String cleaned = amount.trim()
                .replace("$", "")
                .replace(" ", "");
        try {
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            try {
                return MONEY_FORMAT.parse(cleaned).doubleValue();
            } catch (ParseException pe) {
                throw new NumberFormatException("Cannot parse money value: " + amount);
            }
        }
    }

    /**
     * Calculates the discount amount given a price and a percentage.
     *
     * @param price           the original price
     * @param discountPercent the discount percentage (0-100)
     * @return the discount amount
     */
    public static double calculateDiscount(double price, double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        return price * (discountPercent / 100.0);
    }

    /**
     * Calculates the final price after applying a discount percentage.
     *
     * @param price           the original price
     * @param discountPercent the discount percentage (0-100)
     * @return the discounted price
     */
    public static double applyDiscount(double price, double discountPercent) {
        return price - calculateDiscount(price, discountPercent);
    }

    /**
     * Calculates the total from an array of amounts.
     */
    public static double calculateTotal(double... amounts) {
        double total = 0.0;
        for (double amount : amounts) {
            total += amount;
        }
        return round(total);
    }

    /**
     * Rounds a double to 2 decimal places.
     */
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Formats cents as a dollar string (e.g. 1234 -> "$ 12.34").
     */
    public static String formatCents(long cents) {
        return format(cents / 100.0);
    }

    /**
     * Converts a dollar amount to cents.
     */
    public static long toCents(double dollars) {
        return Math.round(dollars * 100);
    }
}

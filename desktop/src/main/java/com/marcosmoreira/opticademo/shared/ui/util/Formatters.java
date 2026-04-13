package com.marcosmoreira.opticademo.shared.ui.util;

import com.marcosmoreira.opticademo.shared.util.DateUtils;
import com.marcosmoreira.opticademo.shared.util.MoneyUtils;

import java.time.LocalDate;

/**
 * Static formatting helpers for displaying data in JavaFX UI labels and controls.
 */
public final class Formatters {

    private Formatters() {
        // utility class
    }

    /**
     * Formats a LocalDate for display in a label (dd/MM/yyyy).
     * Returns "--" if the date is null.
     */
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "--";
        }
        return DateUtils.format(date);
    }

    /**
     * Formats a dd/MM/yyyy string for display.
     * Returns "--" if the string is null or blank.
     */
    public static String formatDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return "--";
        }
        return date.trim();
    }

    /**
     * Formats a monetary amount for display.
     * Returns "$ 0.00" if the amount is null.
     */
    public static String formatMoney(Double amount) {
        if (amount == null) {
            return "$ 0.00";
        }
        return MoneyUtils.format(amount);
    }

    /**
     * Formats a primitive double monetary amount for display.
     */
    public static String formatMoney(double amount) {
        return MoneyUtils.format(amount);
    }

    /**
     * Formats a phone number for display.
     * If the raw value has 10 digits, formats as "(XXX) XXX-XXXX".
     * Otherwise returns the original string or "--" if blank.
     */
    public static String formatPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return "--";
        }
        String digits = phone.replaceAll("\\D", "");
        if (digits.length() == 10) {
            return String.format("(%s) %s-%s",
                    digits.substring(0, 3),
                    digits.substring(3, 6),
                    digits.substring(6));
        }
        return phone.trim();
    }

    /**
     * Formats a percentage value for display (e.g. 15.5 -> "15.50%").
     */
    public static String formatPercent(Double value) {
        if (value == null) {
            return "0.00%";
        }
        return String.format("%.2f%%", value);
    }

    /**
     * Formats an integer quantity for display, returning "--" if null.
     */
    public static String formatQuantity(Integer qty) {
        if (qty == null) {
            return "--";
        }
        return String.valueOf(qty);
    }

    /**
     * Formats a name for display, capitalizing words.
     * Returns "--" if blank.
     */
    public static String formatName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "--";
        }
        return capitalizeWords(name.trim());
    }

    private static String capitalizeWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            if (!words[i].isEmpty()) {
                sb.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    sb.append(words[i].substring(1).toLowerCase());
                }
            }
        }
        return sb.toString();
    }
}

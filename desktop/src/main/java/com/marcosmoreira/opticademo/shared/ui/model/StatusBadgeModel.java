package com.marcosmoreira.opticademo.shared.ui.model;

/**
 * Model for a status badge with predefined style variants.
 */
public record StatusBadgeModel(String text, String styleClass) {

    public static StatusBadgeModel success(String text) {
        return new StatusBadgeModel(text, "badge-success");
    }

    public static StatusBadgeModel warning(String text) {
        return new StatusBadgeModel(text, "badge-warning");
    }

    public static StatusBadgeModel danger(String text) {
        return new StatusBadgeModel(text, "badge-danger");
    }

    public static StatusBadgeModel info(String text) {
        return new StatusBadgeModel(text, "badge-info");
    }

    public static StatusBadgeModel neutral(String text) {
        return new StatusBadgeModel(text, "badge-neutral");
    }
}

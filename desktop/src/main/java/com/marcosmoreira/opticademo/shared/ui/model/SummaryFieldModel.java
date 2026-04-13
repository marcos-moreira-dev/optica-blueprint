package com.marcosmoreira.opticademo.shared.ui.model;

/**
 * Model for a single field in a summary side panel.
 */
public record SummaryFieldModel(String label, String value, boolean isHighlighted) {

    public SummaryFieldModel(String label, String value) {
        this(label, value, false);
    }
}

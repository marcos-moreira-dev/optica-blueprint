package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.DatePicker;

/**
 * Custom DatePicker with baked-in Optica styles.
 * <p>
 * Usage:
 * <pre>
 *   var picker = new OpticaDatePicker("Fecha inicio");
 *   picker.valueProperty().addListener((obs, old, val) -> onDateChange(val));
 * </pre>
 *
 * @author Optica Demo Desktop
 */
public class OpticaDatePicker extends DatePicker {

    public OpticaDatePicker() {
        this(null);
    }

    public OpticaDatePicker(String promptText) {
        if (promptText != null) {
            setPromptText(promptText);
        }
        getStyleClass().add("optica-date-picker");
        setPrefWidth(150);
    }

    /**
     * Sets the preferred width and returns {@code this} for chaining.
     */
    public OpticaDatePicker withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }
}

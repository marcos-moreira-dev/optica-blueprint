package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.TextField;

/**
 * Custom TextField with baked-in Optica styles.
 * <p>
 * Usage:
 * <pre>
 *   var field = new OpticaTextField("Buscar...");
 *   field.textProperty().addListener((obs, old, val) -> search(val));
 * </pre>
 *
 * @author Optica Demo Desktop
 */
public class OpticaTextField extends TextField {

    public OpticaTextField() {
        this(null);
    }

    public OpticaTextField(String promptText) {
        if (promptText != null) {
            setPromptText(promptText);
        }
        getStyleClass().add("optica-text-field");
    }

    /**
     * Sets the preferred width and returns {@code this} for chaining.
     */
    public OpticaTextField withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }
}

package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.ComboBox;

/**
 * Custom ComboBox with baked-in Optica styles.
 * <p>
 * Replaces the pattern of creating a plain {@code ComboBox} and manually
 * setting styleClasses in FXML or code.
 * <p>
 * Usage:
 * <pre>
 *   var combo = new OpticaComboBox("Seleccionar...", "Todos", "Activo", "Inactivo");
 *   combo.setOnAction(e -> filter(combo.getValue()));
 * </pre>
 *
 * @author Optica Demo Desktop
 */
public class OpticaComboBox extends ComboBox<String> {

    public OpticaComboBox() {
        this("Seleccionar...");
    }

    public OpticaComboBox(String promptText) {
        super();
        setPromptText(promptText);
        getStyleClass().add("optica-combo-box");
        setPrefWidth(200);
    }

    public OpticaComboBox(String promptText, String... items) {
        this(promptText);
        getItems().addAll(items);
        if (items.length > 0) {
            setValue(items[0]);
        }
    }

    /**
     * Sets the preferred width and returns {@code this} for chaining.
     */
    public OpticaComboBox withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }

    /**
     * Adds a change listener in a fluent way.
     */
    public OpticaComboBox onChange(Runnable action) {
        valueProperty().addListener((obs, oldVal, newVal) -> action.run());
        return this;
    }
}

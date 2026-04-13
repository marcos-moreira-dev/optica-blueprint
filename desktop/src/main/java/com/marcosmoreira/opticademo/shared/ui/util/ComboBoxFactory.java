package com.marcosmoreira.opticademo.shared.ui.util;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

/**
 * Factory utility for creating preconfigured JavaFX ComboBox controls.
 */
public final class ComboBoxFactory {

    private ComboBoxFactory() {
        // utility class
    }

    /**
     * Creates a ComboBox with the given prompt text and items.
     *
     * @param prompt the prompt text shown when no selection is made
     * @param items  the items to populate the combo box
     * @return a configured ComboBox
     */
    public static ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> combo = new ComboBox<>();
        combo.setPromptText(prompt);
        if (items != null && items.length > 0) {
            combo.setItems(FXCollections.observableArrayList(items));
        }
        combo.setMaxWidth(Double.MAX_VALUE);
        return combo;
    }

    /**
     * Creates an editable ComboBox with the given prompt text and items.
     *
     * @param prompt the prompt text shown when no selection is made
     * @param items  the items to populate the combo box
     * @return an editable configured ComboBox
     */
    public static ComboBox<String> createEditableCombo(String prompt, String... items) {
        ComboBox<String> combo = createCombo(prompt, items);
        combo.setEditable(true);
        return combo;
    }

    /**
     * Creates a ComboBox with an "all" option prepended to the items.
     *
     * @param prompt       the prompt text
     * @param allLabel     the label for the "all" option (e.g. "-- Todos --")
     * @param items        the items to populate
     * @return a configured ComboBox with the "all" option first
     */
    public static ComboBox<String> createFilterCombo(String prompt, String allLabel, String... items) {
        ComboBox<String> combo = new ComboBox<>();
        combo.setPromptText(prompt);
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add(allLabel);
        if (items != null) {
            for (String item : items) {
                list.add(item);
            }
        }
        combo.setItems(FXCollections.observableArrayList(list));
        combo.getSelectionModel().selectFirst();
        combo.setMaxWidth(Double.MAX_VALUE);
        return combo;
    }
}

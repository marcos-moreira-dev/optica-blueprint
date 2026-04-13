package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * Controller for a filter bar containing search, filter combos, and clear action.
 */
public class FilterBarController {

    @FXML
    private FlowPane rootPane;

    @FXML
    private TextField searchField;

    @FXML
    private HBox filtersContainer;

    @FXML
    private Button clearButton;

    private Runnable onSearch;
    private Runnable onClear;

    public void initialize() {
        if (rootPane != null) {
            rootPane.getStyleClass().add("filter-bar");
        }
        if (searchField != null) {
            searchField.setPromptText("Buscar...");
            searchField.setOnAction(e -> triggerSearch());
        }
        if (clearButton != null) {
            clearButton.setOnAction(e -> triggerClear());
        }
    }

    /**
     * Returns the current search text.
     */
    public String getSearchText() {
        return searchField != null ? searchField.getText() : "";
    }

    /**
     * Clears all filters including search text.
     */
    public void clearFilters() {
        if (searchField != null) {
            searchField.clear();
        }
        if (filtersContainer != null) {
            for (var node : filtersContainer.getChildren()) {
                if (node instanceof ComboBox<?> combo) {
                    combo.getSelectionModel().clearSelection();
                }
            }
        }
    }

    /**
     * Adds a filter ComboBox to the bar.
     */
    public void addFilter(ComboBox<?> filter) {
        if (filtersContainer != null && filter != null) {
            filtersContainer.getChildren().add(filter);
        }
    }

    /**
     * Sets the search callback.
     */
    public void setOnSearch(Runnable onSearch) {
        this.onSearch = onSearch;
    }

    /**
     * Sets the clear filters callback.
     */
    public void setOnClear(Runnable onClear) {
        this.onClear = onClear;
    }

    private void triggerSearch() {
        if (onSearch != null) {
            onSearch.run();
        }
    }

    private void triggerClear() {
        clearFilters();
        if (onClear != null) {
            onClear.run();
        }
    }
}

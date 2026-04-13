package com.marcosmoreira.opticademo.shared.ui.components;

import com.marcosmoreira.opticademo.shared.ui.model.SummaryFieldModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller for a summary side panel with scrollable fields and optional action buttons.
 */
public class SummarySidePanelController {

    @FXML
    private ScrollPane rootPane;

    @FXML
    private VBox contentBox;

    @FXML
    private Label panelTitleLabel;

    @FXML
    private VBox fieldsContainer;

    @FXML
    private HBox actionsContainer;

    public void initialize() {
        if (rootPane != null) {
            rootPane.getStyleClass().add("summary-panel");
        }
        if (contentBox != null) {
            contentBox.getStyleClass().add("summary-panel-content");
        }
        if (actionsContainer == null) {
            actionsContainer = new HBox(8);
            actionsContainer.getStyleClass().add("summary-actions");
            if (contentBox != null) {
                contentBox.getChildren().add(actionsContainer);
            }
        }
    }

    /**
     * Sets the panel title.
     */
    public void setTitle(String title) {
        if (panelTitleLabel != null) {
            panelTitleLabel.setText(title != null ? title : "");
        }
    }

    /**
     * Adds a summary field to the panel.
     */
    public void addField(SummaryFieldModel field) {
        if (fieldsContainer == null) {
            return;
        }

        HBox fieldRow = new HBox(4);
        fieldRow.getStyleClass().add("summary-field-row");

        Label labelNode = new Label(field.label() + ":");
        labelNode.getStyleClass().add("summary-field-label");

        Label valueNode = new Label(field.value() != null ? field.value() : "");
        valueNode.getStyleClass().add("summary-field-value");
        if (field.isHighlighted()) {
            valueNode.getStyleClass().add("summary-field-highlighted");
        }

        HBox.setHgrow(valueNode, Priority.ALWAYS);

        fieldRow.getChildren().addAll(labelNode, valueNode);
        fieldsContainer.getChildren().add(fieldRow);
    }

    /**
     * Clears all fields from the panel.
     */
    public void clearFields() {
        if (fieldsContainer != null) {
            fieldsContainer.getChildren().clear();
        }
    }

    /**
     * Sets action buttons for the panel.
     */
    public void setActions(List<Button> actions) {
        if (actionsContainer == null) {
            return;
        }
        actionsContainer.getChildren().clear();
        if (actions != null) {
            actionsContainer.getChildren().addAll(actions);
        }
    }

    /**
     * Adds a single action button.
     */
    public void addAction(Button action) {
        if (actionsContainer != null && action != null) {
            actionsContainer.getChildren().add(action);
        }
    }
}

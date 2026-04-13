package com.marcosmoreira.opticademo.shared.ui.components;

import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controller for a status badge component.
 */
public class StatusBadgeController {

    @FXML
    private VBox rootPane;

    @FXML
    private Label badgeLabel;

    public void initialize() {
        if (rootPane != null) {
            rootPane.getStyleClass().add("status-badge");
        }
    }

    /**
     * Sets the badge model, updating text and style class.
     */
    public void setStatus(StatusBadgeModel model) {
        if (badgeLabel != null) {
            badgeLabel.setText(model.text());
            badgeLabel.getStyleClass().removeIf(c -> c.startsWith("badge-"));
            badgeLabel.getStyleClass().add(model.styleClass());
        }
    }

    /**
     * Convenience method to set badge text and style directly.
     */
    public void setText(String text, String styleClass) {
        if (badgeLabel != null) {
            badgeLabel.setText(text);
            badgeLabel.getStyleClass().removeIf(c -> c.startsWith("badge-"));
            if (styleClass != null) {
                badgeLabel.getStyleClass().add(styleClass);
            }
        }
    }
}

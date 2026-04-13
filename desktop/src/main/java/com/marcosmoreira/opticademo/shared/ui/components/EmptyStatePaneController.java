package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controller for an empty state component displayed when no data is available.
 */
public class EmptyStatePaneController {

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox contentBox;

    @FXML
    private Label messageLabel;

    @FXML
    private Label subMessageLabel;

    @FXML
    private VBox actionContainer;

    public void initialize() {
        if (rootPane != null) {
            rootPane.getStyleClass().add("empty-state");
        }
        if (actionContainer == null) {
            actionContainer = new VBox(8);
            actionContainer.getStyleClass().add("empty-state-actions");
            if (contentBox != null) {
                contentBox.getChildren().add(actionContainer);
            }
        }
    }

    /**
     * Sets the main message.
     */
    public void setMessage(String message) {
        if (messageLabel != null) {
            messageLabel.setText(message != null ? message : "");
        }
    }

    /**
     * Sets the secondary/sub message.
     */
    public void setSubMessage(String subMessage) {
        if (subMessageLabel != null) {
            subMessageLabel.setText(subMessage != null ? subMessage : "");
            subMessageLabel.setVisible(subMessage != null && !subMessage.isEmpty());
            subMessageLabel.setManaged(subMessage != null && !subMessage.isEmpty());
        }
    }

    /**
     * Sets an action button for the empty state.
     */
    public void setAction(Button action) {
        if (actionContainer != null) {
            actionContainer.getChildren().clear();
            if (action != null) {
                actionContainer.getChildren().add(action);
            }
        }
    }
}

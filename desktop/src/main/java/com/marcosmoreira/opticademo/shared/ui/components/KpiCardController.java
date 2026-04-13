package com.marcosmoreira.opticademo.shared.ui.components;

import com.marcosmoreira.opticademo.shared.ui.model.KpiCardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controller for a KPI card component.
 */
public class KpiCardController {

    @FXML
    private VBox rootPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label subtitleLabel;

    @FXML
    private Label iconLabel;

    public void initialize() {
        if (rootPane != null) {
            rootPane.getStyleClass().add("kpi-card");
        }
    }

    /**
     * Sets the KPI card model, updating all labels.
     */
    public void setModel(KpiCardModel model) {
        if (titleLabel != null) {
            titleLabel.setText(model.title() != null ? model.title() : "");
        }
        if (valueLabel != null) {
            valueLabel.setText(model.value() != null ? model.value() : "");
        }
        if (subtitleLabel != null) {
            subtitleLabel.setText(model.subtitle() != null ? model.subtitle() : "");
        }
        if (iconLabel != null) {
            iconLabel.setText(model.icon() != null ? model.icon() : "");
        }
    }
}

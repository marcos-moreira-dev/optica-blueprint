package com.marcosmoreira.opticademo.app;

import com.marcosmoreira.opticademo.app.navigation.ModuleId;
import com.marcosmoreira.opticademo.app.navigation.ModuleNavigator;
import com.marcosmoreira.opticademo.app.navigation.NavigationItem;
import com.marcosmoreira.opticademo.app.session.CurrentSucursalContext;
import com.marcosmoreira.opticademo.app.session.CurrentUserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainLayoutController {

    @FXML private Label lblModuleName;
    @FXML private Label lblModuleDescription;
    @FXML private Label lblUser;
    @FXML private Label lblSucursal;
    @FXML private ComboBox<String> cmbSucursal;
    @FXML private StackPane contentHost;
    @FXML private VBox sidebarItems;

    private ModuleNavigator navigator;
    private final Map<ModuleId, Button> moduleButtons = new LinkedHashMap<>();

    private static final List<NavigationItem> NAV_ITEMS = List.of(
            new NavigationItem(ModuleId.INICIO, "\u2302"),
            new NavigationItem(ModuleId.AGENDA, "\uD83D\uDCC5"),
            new NavigationItem(ModuleId.CLIENTES, "\uD83D\uDC64"),
            new NavigationItem(ModuleId.RECETAS, "\uD83D\uDC41"),
            new NavigationItem(ModuleId.VENTA_OPTICA, "\uD83E\uDD43"),
            new NavigationItem(ModuleId.ORDENES_LABORATORIO, "\uD83D\uDCCB"),
            new NavigationItem(ModuleId.INVENTARIO, "\uD83D\uDCE6"),
            new NavigationItem(ModuleId.CAJA, "\uD83D\uDCB0"),
            new NavigationItem(ModuleId.ENTREGAS, "\u2705"),
            new NavigationItem(ModuleId.SEGUIMIENTO, "\uD83D\uDD14"),
            new NavigationItem(ModuleId.REPORTES, "\uD83D\uDCCA"),
            new NavigationItem(ModuleId.CONFIGURACION, "\u2699"),
            new NavigationItem(ModuleId.SEGUROS, "\uD83D\uDEE1"),
            new NavigationItem(ModuleId.PROVEEDORES, "\uD83C\uFE2E"),
            new NavigationItem(ModuleId.COMPRAS, "\uD83D\uDED2"),
            new NavigationItem(ModuleId.USUARIOS_ROLES, "\uD83D\uDD11"),
            new NavigationItem(ModuleId.TALLER, "\uD83D\uDD27"),
            new NavigationItem(ModuleId.NOTIFICACIONES, "\uD83D\uDD14"),
            new NavigationItem(ModuleId.SUCURSALES, "\uD83C\uFE22")
    );

    @FXML
    public void initialize() {
        navigator = new ModuleNavigator(contentHost);

        setupTopBar();
        buildSidebar();
        navigator.navigateTo(ModuleId.INICIO);
        updateModuleTitle(ModuleId.INICIO);
    }

    private void setupTopBar() {
        if (lblUser != null) {
            lblUser.setText(CurrentUserContext.getCurrentUser());
        }
        if (lblSucursal != null) {
            lblSucursal.setText(CurrentSucursalContext.getCurrentSucursal());
        }
        if (cmbSucursal != null) {
            cmbSucursal.getItems().addAll("Matriz Centro", "Norte Mall");
            cmbSucursal.setValue("Matriz Centro");
            cmbSucursal.valueProperty().addListener((obs, old, val) -> {
                if (val != null) {
                    CurrentSucursalContext.setSucursal(val);
                    if (lblSucursal != null) lblSucursal.setText(val);
                }
            });
        }
    }

    private void buildSidebar() {
        if (sidebarItems == null) return;

        sidebarItems.getChildren().clear();

        for (NavigationItem item : NAV_ITEMS) {
            Button btn = new Button(item.icon() + "  " + item.getDisplayName());
            btn.getStyleClass().add("sidebar-button");
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> {
                navigator.navigateTo(item.moduleId());
                updateModuleTitle(item.moduleId());
                highlightButton(item.moduleId());
            });
            moduleButtons.put(item.moduleId(), btn);
            sidebarItems.getChildren().add(btn);
        }

        highlightButton(ModuleId.INICIO);
    }

    private void highlightButton(ModuleId moduleId) {
        for (Map.Entry<ModuleId, Button> entry : moduleButtons.entrySet()) {
            Button btn = entry.getValue();
            if (entry.getKey() == moduleId) {
                btn.getStyleClass().add("active");
            } else {
                btn.getStyleClass().remove("active");
            }
        }
    }

    private void updateModuleTitle(ModuleId moduleId) {
        if (lblModuleName != null) {
            lblModuleName.setText(moduleId.getDisplayName());
        }
    }
}

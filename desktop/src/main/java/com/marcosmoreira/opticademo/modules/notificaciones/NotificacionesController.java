package com.marcosmoreira.opticademo.modules.notificaciones;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.notificacion.Notificacion;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.model.SummaryFieldModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Notificaciones module.
 * Manages 7 sub-views, filter bar, and persistent right panel with notification summary.
 */
public class NotificacionesController {

    // ---- Top bar ----
    @FXML
    private Button enviarNotificacionBtn;

    @FXML
    private Button actualizarListadoBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> tipoCombo;

    @FXML
    private ComboBox<String> canalCombo;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> prioridadCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private CheckBox soloPendientesYCriticasCheck;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnBandeja;

    @FXML
    private ToggleButton btnNotifCliente;

    @FXML
    private ToggleButton btnNotifInterna;

    @FXML
    private ToggleButton btnPlantillas;

    @FXML
    private ToggleButton btnHistorial;

    @FXML
    private ToggleButton btnAlertas;

    @FXML
    private ToggleButton btnPreferencias;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionBandeja;

    @FXML
    private VBox sectionNotifCliente;

    @FXML
    private VBox sectionNotifInterna;

    @FXML
    private VBox sectionPlantillas;

    @FXML
    private VBox sectionHistorial;

    @FXML
    private VBox sectionAlertas;

    @FXML
    private VBox sectionPreferencias;

    // ---- Sub-view 1: Bandeja general ----
    @FXML
    private Label lblBandejaCount;

    @FXML
    private TableView<NotificacionesRowModel.BandejaRow> bandejaTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBReferencia;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBTipo;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBClienteOCaso;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBCanal;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBEstado;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBPrioridad;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBFecha;

    @FXML
    private TableColumn<NotificacionesRowModel.BandejaRow, String> colBModuloOrigen;

    @FXML
    private Label footerBandeja;

    // ---- Sub-view 2: Notificaciones al cliente ----
    @FXML
    private TableView<NotificacionesRowModel.NotifClienteRow> notifClienteTable;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifClienteRow, String> colNCCliente;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifClienteRow, String> colNCTipo;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifClienteRow, String> colNCCanal;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifClienteRow, String> colNCEstado;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifClienteRow, String> colNCFecha;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifClienteRow, String> colNCResultado;

    // ---- Sub-view 3: Notificaciones operativas internas ----
    @FXML
    private TableView<NotificacionesRowModel.NotifInternaRow> notifInternaTable;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifInternaRow, String> colNIReferencia;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifInternaRow, String> colNITipo;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifInternaRow, String> colNIArea;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifInternaRow, String> colNIEstado;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifInternaRow, String> colNIPrioridad;

    @FXML
    private TableColumn<NotificacionesRowModel.NotifInternaRow, String> colNIFecha;

    // ---- Sub-view 4: Campanas y plantillas ----
    @FXML
    private TableView<NotificacionesRowModel.PlantillaRow> plantillasTable;

    @FXML
    private TableColumn<NotificacionesRowModel.PlantillaRow, String> colPPlantilla;

    @FXML
    private TableColumn<NotificacionesRowModel.PlantillaRow, String> colPTipo;

    @FXML
    private TableColumn<NotificacionesRowModel.PlantillaRow, String> colPCanalSugerido;

    @FXML
    private TableColumn<NotificacionesRowModel.PlantillaRow, String> colPEstado;

    @FXML
    private Button btnNuevaPlantilla;

    // ---- Sub-view 5: Historial de envios y respuestas ----
    @FXML
    private TableView<NotificacionesRowModel.HistorialEnvioRow> historialTable;

    @FXML
    private TableColumn<NotificacionesRowModel.HistorialEnvioRow, String> colHFecha;

    @FXML
    private TableColumn<NotificacionesRowModel.HistorialEnvioRow, String> colHReferencia;

    @FXML
    private TableColumn<NotificacionesRowModel.HistorialEnvioRow, String> colHClienteOCaso;

    @FXML
    private TableColumn<NotificacionesRowModel.HistorialEnvioRow, String> colHCanal;

    @FXML
    private TableColumn<NotificacionesRowModel.HistorialEnvioRow, String> colHEstado;

    @FXML
    private TableColumn<NotificacionesRowModel.HistorialEnvioRow, String> colHResultado;

    // ---- Sub-view 6: Alertas criticas ----
    @FXML
    private Label lblAlertasCount;

    @FXML
    private TableView<NotificacionesRowModel.AlertaCriticaRow> alertasTable;

    @FXML
    private TableColumn<NotificacionesRowModel.AlertaCriticaRow, String> colAReferencia;

    @FXML
    private TableColumn<NotificacionesRowModel.AlertaCriticaRow, String> colATipo;

    @FXML
    private TableColumn<NotificacionesRowModel.AlertaCriticaRow, String> colACasoAfectado;

    @FXML
    private TableColumn<NotificacionesRowModel.AlertaCriticaRow, String> colAPrioridad;

    @FXML
    private TableColumn<NotificacionesRowModel.AlertaCriticaRow, String> colAEstado;

    @FXML
    private TableColumn<NotificacionesRowModel.AlertaCriticaRow, String> colAAccionSugerida;

    @FXML
    private Button btnAtenderAlerta;

    // ---- Sub-view 7: Preferencias de notificacion ----
    @FXML
    private CheckBox chkWhatsApp;

    @FXML
    private CheckBox chkSMS;

    @FXML
    private CheckBox chkEmail;

    @FXML
    private CheckBox chkLlamada;

    @FXML
    private Button btnGuardarPreferencias;

    // ---- Right panel: Notification summary ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipo;

    @FXML
    private Label summaryCanal;

    @FXML
    private Label summaryCliente;

    @FXML
    private Label summaryOrdenRelacionada;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryEstadoActual;

    @FXML
    private Label summaryFechaHora;

    @FXML
    private VBox summaryFieldsContainer;

    @FXML
    private Button summaryBtnReenviar;

    @FXML
    private Button summaryBtnMarcarLeida;

    @FXML
    private Button summaryBtnVerDetalle;

    // ---- Facade ----
    private NotificacionesFacade facade;

    private NotificacionesFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new NotificacionesFacade(store);
        this.currentFilters = new NotificacionesFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Bandeja();
        setupSubView2_NotifCliente();
        setupSubView3_NotifInterna();
        setupSubView4_Plantillas();
        setupSubView5_Historial();
        setupSubView6_Alertas();
        setupSubView7_Preferencias();
        setupActionButtons();
        setupSummaryPanel();

        showSubView(0);
        if (btnBandeja != null) btnBandeja.setSelected(true);

        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (tipoCombo != null) {
            List<String> tipos = facade.getTipos();
            String[] items = tipos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Tipo", "Todos", items);
            replaceInParent(tipoCombo, combo);
            tipoCombo = combo;
            tipoCombo.setOnAction(e -> applyFilters());
        }

        if (canalCombo != null) {
            List<String> canales = facade.getCanales();
            String[] items = canales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Canal", "Todos", items);
            replaceInParent(canalCombo, combo);
            canalCombo = combo;
            canalCombo.setOnAction(e -> applyFilters());
        }

        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (prioridadCombo != null) {
            List<String> prioridades = facade.getPrioridades();
            String[] items = prioridades.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Prioridad", "Todas", items);
            replaceInParent(prioridadCombo, combo);
            prioridadCombo = combo;
            prioridadCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            List<String> sucursales = facade.getSucursales();
            String[] items = sucursales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", items);
            replaceInParent(sucursalCombo, combo);
            sucursalCombo = combo;
            sucursalCombo.setOnAction(e -> applyFilters());
        }

        if (searchField != null) {
            searchField.textProperty().addListener((obs, old, val) -> applyFilters());
        }

        if (desdePicker != null) {
            desdePicker.valueProperty().addListener((obs, old, val) -> applyFilters());
        }

        if (hastaPicker != null) {
            hastaPicker.valueProperty().addListener((obs, old, val) -> applyFilters());
        }

        if (soloPendientesYCriticasCheck != null) {
            soloPendientesYCriticasCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
        }

        if (limpiarFiltrosBtn != null) {
            limpiarFiltrosBtn.setOnAction(e -> clearFilters());
        }
    }

    private void replaceInParent(javafx.scene.control.Control oldControl, javafx.scene.control.Control newControl) {
        if (oldControl.getParent() instanceof javafx.scene.layout.Pane parent) {
            int idx = parent.getChildren().indexOf(oldControl);
            parent.getChildren().set(idx, newControl);
        }
    }

    // ------------------------------------------------------------------ Sub-view toggle

    private void setupSubViewToggle() {
        ToggleGroup group = new ToggleGroup();
        if (btnBandeja != null) {
            btnBandeja.setToggleGroup(group);
            btnBandeja.setOnAction(e -> showSubView(0));
        }
        if (btnNotifCliente != null) {
            btnNotifCliente.setToggleGroup(group);
            btnNotifCliente.setOnAction(e -> showSubView(1));
        }
        if (btnNotifInterna != null) {
            btnNotifInterna.setToggleGroup(group);
            btnNotifInterna.setOnAction(e -> showSubView(2));
        }
        if (btnPlantillas != null) {
            btnPlantillas.setToggleGroup(group);
            btnPlantillas.setOnAction(e -> showSubView(3));
        }
        if (btnHistorial != null) {
            btnHistorial.setToggleGroup(group);
            btnHistorial.setOnAction(e -> showSubView(4));
        }
        if (btnAlertas != null) {
            btnAlertas.setToggleGroup(group);
            btnAlertas.setOnAction(e -> showSubView(5));
        }
        if (btnPreferencias != null) {
            btnPreferencias.setToggleGroup(group);
            btnPreferencias.setOnAction(e -> showSubView(6));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionBandeja,
                sectionNotifCliente,
                sectionNotifInterna,
                sectionPlantillas,
                sectionHistorial,
                sectionAlertas,
                sectionPreferencias
        };

        for (int i = 0; i < sections.length; i++) {
            if (sections[i] != null) {
                sections[i].setVisible(i == index);
                sections[i].setManaged(i == index);
            }
        }

        switch (index) {
            case 0 -> loadSubView1_Bandeja();
            case 1 -> loadSubView2_NotifCliente();
            case 2 -> loadSubView3_NotifInterna();
            case 3 -> loadSubView4_Plantillas();
            case 4 -> loadSubView5_Historial();
            case 5 -> loadSubView6_Alertas();
            case 6 -> {} // Preferencias doesn't need data load
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Bandeja general

    private void setupSubView1_Bandeja() {
        if (colBReferencia != null) colBReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colBTipo != null) colBTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colBClienteOCaso != null) colBClienteOCaso.setCellValueFactory(data -> data.getValue().clienteOCasoProperty());
        if (colBCanal != null) colBCanal.setCellValueFactory(data -> data.getValue().canalProperty());
        if (colBEstado != null) colBEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colBPrioridad != null) colBPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colBFecha != null) colBFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colBModuloOrigen != null) colBModuloOrigen.setCellValueFactory(data -> data.getValue().moduloOrigenProperty());

        if (colBEstado != null) {
            colBEstado.setCellFactory(createBandejaStatusBadgeCell());
        }

        if (bandejaTable != null) {
            bandejaTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onBandejaRowSelected(newVal));
        }
    }

    private void loadSubView1_Bandeja() {
        if (bandejaTable == null) return;

        PageResult<NotificacionesRowModel.BandejaRow> pageResult =
                facade.getBandeja(currentFilters, currentPageRequest);
        ObservableList<NotificacionesRowModel.BandejaRow> data =
                FXCollections.observableArrayList(pageResult.getItems());
        bandejaTable.setItems(data);

        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages(pageResult.getTotalPages());
            paginationBarController.setTotalItems(pageResult.getTotalItems());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        if (lblBandejaCount != null) {
            lblBandejaCount.setText(data.size() + " notificaciones visibles");
        }

        if (footerBandeja != null) {
            footerBandeja.setText("Mostrando " + data.size() + " de " + pageResult.getTotalItems() + " notificaciones");
        }

        if (!data.isEmpty()) {
            bandejaTable.getSelectionModel().selectFirst();
        }
    }

    private void onBandejaRowSelected(NotificacionesRowModel.BandejaRow row) {
        if (row == null) return;

        DemoStore store = App.getDemoStore();
        Notificacion notif = store.notificaciones.stream()
                .filter(n -> row.referencia().equals(n.getReferencia()))
                .findFirst()
                .orElse(null);

        NotificacionesSummaryModel summary = facade.buildSummary(notif);
        updateSummaryPanel(summary);
    }

    // ------------------------------------------------------------------ Sub-view 2: Notificaciones al cliente

    private void setupSubView2_NotifCliente() {
        if (colNCCliente != null) colNCCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colNCTipo != null) colNCTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colNCCanal != null) colNCCanal.setCellValueFactory(data -> data.getValue().canalProperty());
        if (colNCEstado != null) colNCEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colNCFecha != null) colNCFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colNCResultado != null) colNCResultado.setCellValueFactory(data -> data.getValue().resultadoProperty());

        if (colNCEstado != null) {
            colNCEstado.setCellFactory(createNotifClienteStatusBadgeCell());
        }
    }

    private void loadSubView2_NotifCliente() {
        if (notifClienteTable == null) return;
        List<NotificacionesRowModel.NotifClienteRow> rows = facade.getNotifCliente();
        notifClienteTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 3: Notificaciones operativas internas

    private void setupSubView3_NotifInterna() {
        if (colNIReferencia != null) colNIReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colNITipo != null) colNITipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colNIArea != null) colNIArea.setCellValueFactory(data -> data.getValue().areaProperty());
        if (colNIEstado != null) colNIEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colNIPrioridad != null) colNIPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colNIFecha != null) colNIFecha.setCellValueFactory(data -> data.getValue().fechaProperty());

        if (colNIEstado != null) {
            colNIEstado.setCellFactory(createNotifInternaStatusBadgeCell());
        }
    }

    private void loadSubView3_NotifInterna() {
        if (notifInternaTable == null) return;
        List<NotificacionesRowModel.NotifInternaRow> rows = facade.getNotifInterna();
        notifInternaTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 4: Campanas y plantillas

    private void setupSubView4_Plantillas() {
        if (colPPlantilla != null) colPPlantilla.setCellValueFactory(data -> data.getValue().plantillaProperty());
        if (colPTipo != null) colPTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colPCanalSugerido != null) colPCanalSugerido.setCellValueFactory(data -> data.getValue().canalSugeridoProperty());
        if (colPEstado != null) colPEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colPEstado != null) {
            colPEstado.setCellFactory(createPlantillaStatusBadgeCell());
        }

        if (btnNuevaPlantilla != null) {
            btnNuevaPlantilla.setOnAction(e -> onNuevaPlantilla());
        }
    }

    private void loadSubView4_Plantillas() {
        if (plantillasTable == null) return;
        List<NotificacionesRowModel.PlantillaRow> rows = facade.getPlantillas();
        plantillasTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 5: Historial de envios y respuestas

    private void setupSubView5_Historial() {
        if (colHFecha != null) colHFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHReferencia != null) colHReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHClienteOCaso != null) colHClienteOCaso.setCellValueFactory(data -> data.getValue().clienteOCasoProperty());
        if (colHCanal != null) colHCanal.setCellValueFactory(data -> data.getValue().canalProperty());
        if (colHEstado != null) colHEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colHResultado != null) colHResultado.setCellValueFactory(data -> data.getValue().resultadoProperty());

        if (colHEstado != null) {
            colHEstado.setCellFactory(createHistorialStatusBadgeCell());
        }
    }

    private void loadSubView5_Historial() {
        if (historialTable == null) return;
        List<NotificacionesRowModel.HistorialEnvioRow> rows = facade.getHistorialEnvios();
        historialTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 6: Alertas criticas

    private void setupSubView6_Alertas() {
        if (colAReferencia != null) colAReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colATipo != null) colATipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colACasoAfectado != null) colACasoAfectado.setCellValueFactory(data -> data.getValue().casoAfectadoProperty());
        if (colAPrioridad != null) colAPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colAEstado != null) colAEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colAAccionSugerida != null) colAAccionSugerida.setCellValueFactory(data -> data.getValue().accionSugeridaProperty());

        if (colAEstado != null) {
            colAEstado.setCellFactory(createAlertaStatusBadgeCell());
        }

        if (btnAtenderAlerta != null) {
            btnAtenderAlerta.setOnAction(e -> onAtenderAlerta());
        }
    }

    private void loadSubView6_Alertas() {
        if (alertasTable == null) return;
        List<NotificacionesRowModel.AlertaCriticaRow> rows = facade.getAlertasCriticas();
        ObservableList<NotificacionesRowModel.AlertaCriticaRow> data = FXCollections.observableArrayList(rows);
        alertasTable.setItems(data);

        if (lblAlertasCount != null) {
            lblAlertasCount.setText(rows.size() + " alertas activas");
        }
    }

    // ------------------------------------------------------------------ Sub-view 7: Preferencias de notificacion

    private void setupSubView7_Preferencias() {
        if (chkWhatsApp != null) chkWhatsApp.setSelected(true);
        if (chkSMS != null) chkSMS.setSelected(true);
        if (chkEmail != null) chkEmail.setSelected(false);
        if (chkLlamada != null) chkLlamada.setSelected(false);

        if (btnGuardarPreferencias != null) {
            btnGuardarPreferencias.setOnAction(e -> onGuardarPreferencias());
        }
    }

    private void onGuardarPreferencias() {
        // Placeholder: save notification preferences
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (enviarNotificacionBtn != null) {
            enviarNotificacionBtn.setOnAction(e -> onEnviarNotificacion());
        }
        if (actualizarListadoBtn != null) {
            actualizarListadoBtn.setOnAction(e -> {
                showSubView(getCurrentSubViewIndex());
                loadSummaryPanel();
            });
        }
    }

    private int getCurrentSubViewIndex() {
        if (btnBandeja != null && btnBandeja.isSelected()) return 0;
        if (btnNotifCliente != null && btnNotifCliente.isSelected()) return 1;
        if (btnNotifInterna != null && btnNotifInterna.isSelected()) return 2;
        if (btnPlantillas != null && btnPlantillas.isSelected()) return 3;
        if (btnHistorial != null && btnHistorial.isSelected()) return 4;
        if (btnAlertas != null && btnAlertas.isSelected()) return 5;
        if (btnPreferencias != null && btnPreferencias.isSelected()) return 6;
        return 0;
    }

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Bandeja();
    }

    private void applyFilters() {
        String desde = desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().toString() : "";
        String hasta = hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().toString() : "";

        currentFilters = new NotificacionesFilters(
                searchField != null ? searchField.getText() : "",
                tipoCombo != null && tipoCombo.getValue() != null ? tipoCombo.getValue() : "Todos",
                canalCombo != null && canalCombo.getValue() != null ? canalCombo.getValue() : "Todos",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                prioridadCombo != null && prioridadCombo.getValue() != null ? prioridadCombo.getValue() : "Todas",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                desde,
                hasta,
                soloPendientesYCriticasCheck != null && soloPendientesYCriticasCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Bandeja();
    }

    private void clearFilters() {
        currentFilters = new NotificacionesFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (tipoCombo != null) tipoCombo.getSelectionModel().selectFirst();
        if (canalCombo != null) canalCombo.getSelectionModel().selectFirst();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (prioridadCombo != null) prioridadCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloPendientesYCriticasCheck != null) soloPendientesYCriticasCheck.setSelected(false);

        loadSubView1_Bandeja();
    }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnReenviar != null) {
            summaryBtnReenviar.setOnAction(e -> onReenviar());
        }
        if (summaryBtnMarcarLeida != null) {
            summaryBtnMarcarLeida.setOnAction(e -> onMarcarLeida());
        }
        if (summaryBtnVerDetalle != null) {
            summaryBtnVerDetalle.setOnAction(e -> onVerDetalle());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(NotificacionesSummaryModel.empty());
    }

    private void updateSummaryPanel(NotificacionesSummaryModel summary) {
        if (summary == null) return;

        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia() != null ? summary.referencia() : "Sin seleccion");
        if (summaryTipo != null) summaryTipo.setText(summary.tipo() != null ? summary.tipo() : "");
        if (summaryCanal != null) summaryCanal.setText(summary.canal() != null ? summary.canal() : "");
        if (summaryCliente != null) summaryCliente.setText(summary.cliente() != null ? summary.cliente() : "");
        if (summaryOrdenRelacionada != null) summaryOrdenRelacionada.setText(summary.ordenRelacionada() != null ? summary.ordenRelacionada() : "");
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal() != null ? summary.sucursal() : "");
        if (summaryEstadoActual != null) summaryEstadoActual.setText(summary.estadoActual() != null ? summary.estadoActual() : "");
        if (summaryFechaHora != null) summaryFechaHora.setText(summary.fechaHora() != null ? summary.fechaHora() : "");

        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Modulo origen", summary.moduloOrigen()),
                    new SummaryFieldModel("Accion sugerida", summary.accionSugerida()),
                    new SummaryFieldModel("Observacion", summary.observacionClave())
            );

            for (SummaryFieldModel field : fields) {
                Label fieldLabel = new Label();
                fieldLabel.getStyleClass().add("summary-field-label");
                fieldLabel.setText(field.label() + ":");

                Label fieldValue = new Label();
                fieldValue.getStyleClass().add("summary-field-value");
                if (field.isHighlighted()) {
                    fieldValue.getStyleClass().add("highlighted");
                }
                fieldValue.setText(field.value() != null ? field.value() : "-");

                VBox fieldBox = new VBox(2, fieldLabel, fieldValue);
                fieldBox.getStyleClass().add("summary-field-row");
                summaryFieldsContainer.getChildren().add(fieldBox);
            }
        }
    }

    // ------------------------------------------------------------------ Status badge cell factories

    private javafx.util.Callback<TableColumn<NotificacionesRowModel.BandejaRow, String>, TableCell<NotificacionesRowModel.BandejaRow, String>> createBandejaStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<NotificacionesRowModel.NotifClienteRow, String>, TableCell<NotificacionesRowModel.NotifClienteRow, String>> createNotifClienteStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<NotificacionesRowModel.NotifInternaRow, String>, TableCell<NotificacionesRowModel.NotifInternaRow, String>> createNotifInternaStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<NotificacionesRowModel.PlantillaRow, String>, TableCell<NotificacionesRowModel.PlantillaRow, String>> createPlantillaStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<NotificacionesRowModel.HistorialEnvioRow, String>, TableCell<NotificacionesRowModel.HistorialEnvioRow, String>> createHistorialStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<NotificacionesRowModel.AlertaCriticaRow, String>, TableCell<NotificacionesRowModel.AlertaCriticaRow, String>> createAlertaStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    @SuppressWarnings("unchecked")
    private <R> javafx.util.Callback<TableColumn<R, String>, TableCell<R, String>> createGenericStatusBadgeCell() {
        return column -> (TableCell<R, String>) new StatusBadgeTableCell();
    }

    // Helper class to avoid generic issues
    private static class StatusBadgeTableCell extends TableCell<Object, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                            getClass().getResource("/fxml/shared/components/StatusBadge.fxml"));
                    VBox badgeRoot = loader.load();
                    com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                    StatusBadgeModel badge = switch (item.toLowerCase()) {
                        case "activo", "enviado", "completado", "cerrado", "entregada", "leida" ->
                                StatusBadgeModel.success(item);
                        case "pendiente", "en proceso", "sin respuesta" ->
                                StatusBadgeModel.warning(item);
                        case "cancelado", "rechazado", "bloqueado" ->
                                StatusBadgeModel.danger(item);
                        default -> StatusBadgeModel.neutral(item);
                    };
                    ctrl.setStatus(badge);
                    setGraphic(badgeRoot);
                    setText(null);
                } catch (IOException e) {
                    setText(item);
                    setGraphic(null);
                }
            }
        }
    }

    // ------------------------------------------------------------------ Placeholder actions

    private void onEnviarNotificacion() {}
    private void onNuevaPlantilla() {}
    private void onAtenderAlerta() {}
    private void onReenviar() {}
    private void onMarcarLeida() {}
    private void onVerDetalle() {}
}

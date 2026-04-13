package com.marcosmoreira.opticademo.modules.taller;

import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for the Taller module.
 * Manages 7 sub-views, filter bar, and persistent right panel with technical work summary.
 * Clean separation: no business logic.
 */
public class TallerController {

    // ---- Top bar ----
    @FXML
    private Button nuevoIngresoBtn;

    @FXML
    private Button actualizarTallerBtn;

    @FXML
    private Button exportarTallerBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> tipoCombo;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> tecnicoCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private CheckBox soloPendientesUrgentesCheck;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnBandeja;

    @FXML
    private ToggleButton btnDiagnosticos;

    @FXML
    private ToggleButton btnReparaciones;

    @FXML
    private ToggleButton btnPiezas;

    @FXML
    private ToggleButton btnEnvios;

    @FXML
    private ToggleButton btnEntregas;

    @FXML
    private ToggleButton btnHistorico;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionBandeja;

    @FXML
    private VBox sectionDiagnosticos;

    @FXML
    private VBox sectionReparaciones;

    @FXML
    private VBox sectionPiezas;

    @FXML
    private VBox sectionEnvios;

    @FXML
    private VBox sectionEntregas;

    @FXML
    private VBox sectionHistorico;

    // ---- Sub-view 1: Bandeja de ingresos ----
    @FXML
    private Label lblBandejaCount;

    @FXML
    private TableView<TallerRowModel.IngresoRow> bandejaTable;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBReferencia;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBCliente;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBTipo;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBEstado;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBTecnico;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBFechaPromesa;

    @FXML
    private TableColumn<TallerRowModel.IngresoRow, String> colBSucursal;

    @FXML
    private Label footerBandeja;

    // ---- Sub-view 2: Diagnosticos ----
    @FXML
    private TableView<TallerRowModel.DiagnosticoRow> diagnosticosTable;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDReferencia;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDCliente;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDTipoTrabajo;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDFechaIngreso;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDDanioPrincipal;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDComplejidad;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDRepuesto;

    @FXML
    private TableColumn<TallerRowModel.DiagnosticoRow, String> colDEnvioExterno;

    // ---- Sub-view 3: Reparaciones ----
    @FXML
    private TableView<TallerRowModel.ReparacionRow> reparacionesTable;

    @FXML
    private TableColumn<TallerRowModel.ReparacionRow, String> colRFecha;

    @FXML
    private TableColumn<TallerRowModel.ReparacionRow, String> colRReferencia;

    @FXML
    private TableColumn<TallerRowModel.ReparacionRow, String> colRIntervencion;

    @FXML
    private TableColumn<TallerRowModel.ReparacionRow, String> colRTecnico;

    @FXML
    private TableColumn<TallerRowModel.ReparacionRow, String> colREstado;

    // ---- Sub-view 4: Piezas ----
    @FXML
    private TableView<TallerRowModel.PiezaRow> piezasTable;

    @FXML
    private TableColumn<TallerRowModel.PiezaRow, String> colPRefTrabajo;

    @FXML
    private TableColumn<TallerRowModel.PiezaRow, String> colPPieza;

    @FXML
    private TableColumn<TallerRowModel.PiezaRow, String> colPCantidad;

    @FXML
    private TableColumn<TallerRowModel.PiezaRow, String> colPEstado;

    @FXML
    private TableColumn<TallerRowModel.PiezaRow, String> colPObservacion;

    // ---- Sub-view 5: Envios externos ----
    @FXML
    private TableView<TallerRowModel.EnvioExternoRow> enviosTable;

    @FXML
    private TableColumn<TallerRowModel.EnvioExternoRow, String> colERReferencia;

    @FXML
    private TableColumn<TallerRowModel.EnvioExternoRow, String> colETipoTrabajo;

    @FXML
    private TableColumn<TallerRowModel.EnvioExternoRow, String> colETercero;

    @FXML
    private TableColumn<TallerRowModel.EnvioExternoRow, String> colEFechaEnvio;

    @FXML
    private TableColumn<TallerRowModel.EnvioExternoRow, String> colEEstado;

    @FXML
    private TableColumn<TallerRowModel.EnvioExternoRow, String> colEFechaEstimada;

    // ---- Sub-view 6: Entregas ----
    @FXML
    private TableView<TallerRowModel.EntregaRow> entregasTable;

    @FXML
    private TableColumn<TallerRowModel.EntregaRow, String> colETGReferencia;

    @FXML
    private TableColumn<TallerRowModel.EntregaRow, String> colETGFechaEntrega;

    @FXML
    private TableColumn<TallerRowModel.EntregaRow, String> colETGResponsable;

    @FXML
    private TableColumn<TallerRowModel.EntregaRow, String> colETGEstadoFinal;

    @FXML
    private TableColumn<TallerRowModel.EntregaRow, String> colETGClienteConforme;

    // ---- Sub-view 7: Historico ----
    @FXML
    private TableView<TallerRowModel.HistoricoRow> historicoTable;

    @FXML
    private TableColumn<TallerRowModel.HistoricoRow, String> colHFecha;

    @FXML
    private TableColumn<TallerRowModel.HistoricoRow, String> colHReferencia;

    @FXML
    private TableColumn<TallerRowModel.HistoricoRow, String> colHCliente;

    @FXML
    private TableColumn<TallerRowModel.HistoricoRow, String> colHTipo;

    @FXML
    private TableColumn<TallerRowModel.HistoricoRow, String> colHEstadoFinal;

    @FXML
    private TableColumn<TallerRowModel.HistoricoRow, String> colHObservacion;

    @FXML
    private Label footerHistorico;

    // ---- Right panel: Technical work summary ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipoIntervencion;

    @FXML
    private Label summaryPrioridad;

    @FXML
    private Label summaryCliente;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryFechaIngreso;

    @FXML
    private Label summaryEstadoActual;

    @FXML
    private Label summaryTecnicoResponsable;

    @FXML
    private Label summaryFechaPromesa;

    @FXML
    private Label summaryRepuestoRequerido;

    @FXML
    private Label summaryEnvioExterno;

    @FXML
    private Label summaryObservacionBreve;

    @FXML
    private Button summaryBtnDiagnosticar;

    @FXML
    private Button summaryBtnReparar;

    @FXML
    private Button summaryBtnEntregar;

    @FXML
    private BorderPane rootPane;

    // ---- Facade ----
    private TallerFacade facade;

    private TallerFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    public void initialize() {
        this.facade = new TallerFacade();
        this.currentFilters = new TallerFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Bandeja();
        setupSubView2_Diagnosticos();
        setupSubView3_Reparaciones();
        setupSubView4_Piezas();
        setupSubView5_Envios();
        setupSubView6_Entregas();
        setupSubView7_Historico();
        setupActionButtons();
        setupSummaryPanel();

        // Show first sub-view by default
        showSubView(0);
        if (btnBandeja != null) btnBandeja.setSelected(true);

        // Load initial data
        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (tipoCombo != null) {
            List<String> tipos = facade.getTiposIntervencion();
            String[] items = tipos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Tipo", "Todos", items);
            replaceInParent(tipoCombo, combo);
            tipoCombo = combo;
            tipoCombo.setOnAction(e -> applyFilters());
        }

        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (tecnicoCombo != null) {
            List<String> tecnicos = facade.getTecnicos();
            String[] items = tecnicos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Tecnico", "Todos", items);
            replaceInParent(tecnicoCombo, combo);
            tecnicoCombo = combo;
            tecnicoCombo.setOnAction(e -> applyFilters());
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

        if (soloPendientesUrgentesCheck != null) {
            soloPendientesUrgentesCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
        }

        if (limpiarFiltrosBtn != null) {
            limpiarFiltrosBtn.setOnAction(e -> clearFilters());
        }
    }

    private void replaceInParent(javafx.scene.control.Control oldControl, javafx.scene.control.Control newControl) {
        if (oldControl.getParent() instanceof Pane parent) {
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
        if (btnDiagnosticos != null) {
            btnDiagnosticos.setToggleGroup(group);
            btnDiagnosticos.setOnAction(e -> showSubView(1));
        }
        if (btnReparaciones != null) {
            btnReparaciones.setToggleGroup(group);
            btnReparaciones.setOnAction(e -> showSubView(2));
        }
        if (btnPiezas != null) {
            btnPiezas.setToggleGroup(group);
            btnPiezas.setOnAction(e -> showSubView(3));
        }
        if (btnEnvios != null) {
            btnEnvios.setToggleGroup(group);
            btnEnvios.setOnAction(e -> showSubView(4));
        }
        if (btnEntregas != null) {
            btnEntregas.setToggleGroup(group);
            btnEntregas.setOnAction(e -> showSubView(5));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(group);
            btnHistorico.setOnAction(e -> showSubView(6));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionBandeja,
                sectionDiagnosticos,
                sectionReparaciones,
                sectionPiezas,
                sectionEnvios,
                sectionEntregas,
                sectionHistorico
        };

        for (int i = 0; i < sections.length; i++) {
            if (sections[i] != null) {
                sections[i].setVisible(i == index);
                sections[i].setManaged(i == index);
            }
        }

        // Load data for the selected sub-view
        switch (index) {
            case 0 -> loadSubView1_Bandeja();
            case 1 -> loadSubView2_Diagnosticos();
            case 2 -> loadSubView3_Reparaciones();
            case 3 -> loadSubView4_Piezas();
            case 4 -> loadSubView5_Envios();
            case 5 -> loadSubView6_Entregas();
            case 6 -> loadSubView7_Historico();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Bandeja de ingresos

    private void setupSubView1_Bandeja() {
        if (colBReferencia != null) colBReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colBCliente != null) colBCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colBTipo != null) colBTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colBEstado != null) colBEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colBTecnico != null) colBTecnico.setCellValueFactory(data -> data.getValue().tecnicoProperty());
        if (colBFechaPromesa != null) colBFechaPromesa.setCellValueFactory(data -> data.getValue().fechaPromesaProperty());
        if (colBSucursal != null) colBSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

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

        PageResult<TallerRowModel.IngresoRow> pageResult =
                facade.getIngresos(currentFilters, currentPageRequest);
        ObservableList<TallerRowModel.IngresoRow> data =
                FXCollections.observableArrayList(pageResult.getItems());
        bandejaTable.setItems(data);

        if (lblBandejaCount != null) {
            lblBandejaCount.setText(data.size() + " trabajos visibles");
        }

        if (footerBandeja != null) {
            footerBandeja.setText("Mostrando " + data.size() + " de " + pageResult.getTotalItems() + " trabajos");
        }

        if (!data.isEmpty()) {
            bandejaTable.getSelectionModel().selectFirst();
        }
    }

    private void onBandejaRowSelected(TallerRowModel.IngresoRow row) {
        if (row == null) return;
        TallerSummaryModel summary = facade.buildSummary(row);
        updateSummaryPanel(summary);
    }

    private javafx.util.Callback<TableColumn<TallerRowModel.IngresoRow, String>,
            javafx.scene.control.TableCell<TallerRowModel.IngresoRow, String>>
    createBandejaStatusBadgeCell() {
        return col -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shared/components/StatusBadge.fxml"));
                        VBox badgeRoot = loader.load();
                        StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = resolveBadge(item);
                        ctrl.setStatus(badge);
                        setGraphic(badgeRoot);
                        setText(null);
                    } catch (IOException e) {
                        setText(item);
                        setGraphic(null);
                    }
                }
            }
        };
    }

    // ------------------------------------------------------------------ Sub-view 2: Diagnosticos

    private void setupSubView2_Diagnosticos() {
        if (colDReferencia != null) colDReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colDCliente != null) colDCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colDTipoTrabajo != null) colDTipoTrabajo.setCellValueFactory(data -> data.getValue().tipoTrabajoProperty());
        if (colDFechaIngreso != null) colDFechaIngreso.setCellValueFactory(data -> data.getValue().fechaIngresoProperty());
        if (colDDanioPrincipal != null) colDDanioPrincipal.setCellValueFactory(data -> data.getValue().danioPrincipalProperty());
        if (colDComplejidad != null) colDComplejidad.setCellValueFactory(data -> data.getValue().complejidadProperty());
        if (colDRepuesto != null) colDRepuesto.setCellValueFactory(data -> data.getValue().requiereRepuestoProperty());
        if (colDEnvioExterno != null) colDEnvioExterno.setCellValueFactory(data -> data.getValue().requiereEnvioExternoProperty());

        if (diagnosticosTable != null) {
            diagnosticosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onDiagnosticoSelected(newVal));
        }
    }

    private void loadSubView2_Diagnosticos() {
        if (diagnosticosTable == null) return;
        List<TallerRowModel.DiagnosticoRow> data = facade.getDiagnosticos();
        diagnosticosTable.setItems(FXCollections.observableArrayList(data));
        if (!data.isEmpty()) {
            diagnosticosTable.getSelectionModel().selectFirst();
        }
    }

    private void onDiagnosticoSelected(TallerRowModel.DiagnosticoRow row) {
        if (row == null) return;
        TallerRowModel.IngresoRow ingreso = facade.getIngresosList(new TallerFilters()).stream()
                .filter(i -> i.referencia().equals(row.referencia()))
                .findFirst()
                .orElse(null);
        if (ingreso != null) {
            updateSummaryPanel(facade.buildSummary(ingreso));
        }
    }

    // ------------------------------------------------------------------ Sub-view 3: Reparaciones

    private void setupSubView3_Reparaciones() {
        if (colRFecha != null) colRFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colRReferencia != null) colRReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colRIntervencion != null) colRIntervencion.setCellValueFactory(data -> data.getValue().intervencionProperty());
        if (colRTecnico != null) colRTecnico.setCellValueFactory(data -> data.getValue().tecnicoProperty());
        if (colREstado != null) colREstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colREstado != null) {
            colREstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView3_Reparaciones() {
        if (reparacionesTable == null) return;
        List<TallerRowModel.ReparacionRow> data = facade.getReparaciones();
        reparacionesTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 4: Piezas

    private void setupSubView4_Piezas() {
        if (colPRefTrabajo != null) colPRefTrabajo.setCellValueFactory(data -> data.getValue().refTrabajoProperty());
        if (colPPieza != null) colPPieza.setCellValueFactory(data -> data.getValue().piezaProperty());
        if (colPCantidad != null) colPCantidad.setCellValueFactory(data -> data.getValue().cantidadProperty());
        if (colPEstado != null) colPEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colPObservacion != null) colPObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colPEstado != null) {
            colPEstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView4_Piezas() {
        if (piezasTable == null) return;
        List<TallerRowModel.PiezaRow> data = facade.getPiezas();
        piezasTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 5: Envios externos

    private void setupSubView5_Envios() {
        if (colERReferencia != null) colERReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colETipoTrabajo != null) colETipoTrabajo.setCellValueFactory(data -> data.getValue().tipoTrabajoProperty());
        if (colETercero != null) colETercero.setCellValueFactory(data -> data.getValue().terceroProperty());
        if (colEFechaEnvio != null) colEFechaEnvio.setCellValueFactory(data -> data.getValue().fechaEnvioProperty());
        if (colEEstado != null) colEEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colEFechaEstimada != null) colEFechaEstimada.setCellValueFactory(data -> data.getValue().fechaEstimadaProperty());

        if (colEEstado != null) {
            colEEstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView5_Envios() {
        if (enviosTable == null) return;
        List<TallerRowModel.EnvioExternoRow> data = facade.getEnviosExternos();
        enviosTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 6: Entregas

    private void setupSubView6_Entregas() {
        if (colETGReferencia != null) colETGReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colETGFechaEntrega != null) colETGFechaEntrega.setCellValueFactory(data -> data.getValue().fechaEntregaProperty());
        if (colETGResponsable != null) colETGResponsable.setCellValueFactory(data -> data.getValue().responsableEntregaProperty());
        if (colETGEstadoFinal != null) colETGEstadoFinal.setCellValueFactory(data -> data.getValue().estadoFinalProperty());
        if (colETGClienteConforme != null) colETGClienteConforme.setCellValueFactory(data -> data.getValue().clienteConformeProperty());

        if (colETGEstadoFinal != null) {
            colETGEstadoFinal.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView6_Entregas() {
        if (entregasTable == null) return;
        List<TallerRowModel.EntregaRow> data = facade.getEntregas();
        entregasTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 7: Historico

    private void setupSubView7_Historico() {
        if (colHFecha != null) colHFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHReferencia != null) colHReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHCliente != null) colHCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colHTipo != null) colHTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colHEstadoFinal != null) colHEstadoFinal.setCellValueFactory(data -> data.getValue().estadoFinalProperty());
        if (colHObservacion != null) colHObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colHEstadoFinal != null) {
            colHEstadoFinal.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView7_Historico() {
        if (historicoTable == null) return;
        List<TallerRowModel.HistoricoRow> data = facade.getHistorico(currentFilters);
        historicoTable.setItems(FXCollections.observableArrayList(data));

        if (footerHistorico != null) {
            footerHistorico.setText("Total registros: " + data.size());
        }
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (nuevoIngresoBtn != null) {
            nuevoIngresoBtn.setOnAction(e -> onNuevoIngreso());
        }
        if (actualizarTallerBtn != null) {
            actualizarTallerBtn.setOnAction(e -> onActualizarTaller());
        }
        if (exportarTallerBtn != null) {
            exportarTallerBtn.setOnAction(e -> onExportarTaller());
        }
    }

    private void onNuevoIngreso() {
        if (btnBandeja != null) btnBandeja.setSelected(true);
        showSubView(0);
    }

    private void onActualizarTaller() {
        applyFilters();
        loadSummaryPanel();
    }

    private void onExportarTaller() {
        // Placeholder: export to CSV
    }

    // ------------------------------------------------------------------ Filters apply/clear

    private void applyFilters() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        currentFilters = new TallerFilters(
                searchField != null ? searchField.getText() : "",
                tipoCombo != null && tipoCombo.getValue() != null ? tipoCombo.getValue() : "Todos",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                tecnicoCombo != null && tecnicoCombo.getValue() != null ? tecnicoCombo.getValue() : "Todos",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().format(fmt) : "",
                hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().format(fmt) : "",
                soloPendientesUrgentesCheck != null && soloPendientesUrgentesCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private void clearFilters() {
        currentFilters = new TallerFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (tipoCombo != null) tipoCombo.getSelectionModel().selectFirst();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (tecnicoCombo != null) tecnicoCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloPendientesUrgentesCheck != null) soloPendientesUrgentesCheck.setSelected(false);

        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private int getCurrentSubViewIndex() {
        if (btnBandeja != null && btnBandeja.isSelected()) return 0;
        if (btnDiagnosticos != null && btnDiagnosticos.isSelected()) return 1;
        if (btnReparaciones != null && btnReparaciones.isSelected()) return 2;
        if (btnPiezas != null && btnPiezas.isSelected()) return 3;
        if (btnEnvios != null && btnEnvios.isSelected()) return 4;
        if (btnEntregas != null && btnEntregas.isSelected()) return 5;
        if (btnHistorico != null && btnHistorico.isSelected()) return 6;
        return 0;
    }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnDiagnosticar != null) {
            summaryBtnDiagnosticar.setOnAction(e -> iniciarDiagnostico());
        }
        if (summaryBtnReparar != null) {
            summaryBtnReparar.setOnAction(e -> iniciarReparacion());
        }
        if (summaryBtnEntregar != null) {
            summaryBtnEntregar.setOnAction(e -> entregarTrabajo());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(TallerSummaryModel.demoSeed());
    }

    private void updateSummaryPanel(TallerSummaryModel summary) {
        if (summary == null) return;

        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryTipoIntervencion != null) summaryTipoIntervencion.setText(summary.tipoIntervencion());
        if (summaryPrioridad != null) summaryPrioridad.setText(summary.prioridad());
        if (summaryCliente != null) summaryCliente.setText(summary.cliente());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryFechaIngreso != null) summaryFechaIngreso.setText(summary.fechaIngreso());
        if (summaryEstadoActual != null) summaryEstadoActual.setText(summary.estadoActual());
        if (summaryTecnicoResponsable != null) summaryTecnicoResponsable.setText(summary.tecnicoResponsable());
        if (summaryFechaPromesa != null) summaryFechaPromesa.setText(summary.fechaPromesa());
        if (summaryRepuestoRequerido != null) summaryRepuestoRequerido.setText(summary.repuestoRequerido());
        if (summaryEnvioExterno != null) summaryEnvioExterno.setText(summary.envioExterno());
        if (summaryObservacionBreve != null) summaryObservacionBreve.setText(summary.observacionBreve());
    }

    private void iniciarDiagnostico() { /* placeholder */ }
    private void iniciarReparacion() { /* placeholder */ }
    private void entregarTrabajo() { /* placeholder */ }

    // ------------------------------------------------------------------ Cell factories

    private <T> javafx.util.Callback<TableColumn<T, String>, javafx.scene.control.TableCell<T, String>>
    createGenericStatusBadgeCell() {
        return col -> new javafx.scene.control.TableCell<T, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shared/components/StatusBadge.fxml"));
                        VBox badgeRoot = loader.load();
                        StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = resolveBadge(item);
                        ctrl.setStatus(badge);
                        setGraphic(badgeRoot);
                        setText(null);
                    } catch (IOException e) {
                        setText(item);
                        setGraphic(null);
                    }
                }
            }
        };
    }

    private StatusBadgeModel resolveBadge(String item) {
        if (item == null) return StatusBadgeModel.neutral("-");
        return switch (item.toLowerCase()) {
            case "completado", "entregado", "listo para entrega", "activo", "confirmado", "disponible", "si" ->
                    StatusBadgeModel.success(item);
            case "pendiente", "en proceso", "en diagnostico", "en reparacion", "pendiente repuesto",
                 "en transito", "en pedido" ->
                    StatusBadgeModel.warning(item);
            case "cancelado", "rechazado", "vencido", "no" ->
                    StatusBadgeModel.danger(item);
            default ->
                    StatusBadgeModel.neutral(item);
        };
    }
}

package com.marcosmoreira.opticademo.modules.seguros;

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
 * Controller for the Seguros module.
 * Manages 7 sub-views, filter bar, and persistent right panel with coverage case summary.
 * Clean separation: no business logic.
 */
public class SegurosController {

    // ---- Top bar ----
    @FXML
    private Button nuevaVerificacionBtn;

    @FXML
    private Button actualizarSegurosBtn;

    @FXML
    private Button exportarSegurosBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> planConvenioCombo;

    @FXML
    private ComboBox<String> tipoCasoCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private CheckBox soloCasosPendientesCheck;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnVerificacion;

    @FXML
    private ToggleButton btnPlanes;

    @FXML
    private ToggleButton btnAutorizaciones;

    @FXML
    private ToggleButton btnReclamos;

    @FXML
    private ToggleButton btnRespuestas;

    @FXML
    private ToggleButton btnCoberturaVenta;

    @FXML
    private ToggleButton btnHistorico;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionVerificacion;

    @FXML
    private VBox sectionPlanes;

    @FXML
    private VBox sectionAutorizaciones;

    @FXML
    private VBox sectionReclamos;

    @FXML
    private VBox sectionRespuestas;

    @FXML
    private VBox sectionCoberturaVenta;

    @FXML
    private VBox sectionHistorico;

    // ---- Sub-view 1: Verificacion de cobertura ----
    @FXML
    private Label lblVerificacionCount;

    @FXML
    private TableView<SegurosRowModel.VerificacionRow> verificacionTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVReferencia;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVCliente;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVPlan;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVEstado;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVVigencia;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVMonto;

    @FXML
    private TableColumn<SegurosRowModel.VerificacionRow, String> colVSucursal;

    @FXML
    private Label footerVerificacion;

    // ---- Sub-view 2: Planes ----
    @FXML
    private TableView<SegurosRowModel.PlanRow> planesTable;

    @FXML
    private TableColumn<SegurosRowModel.PlanRow, String> colPNombre;

    @FXML
    private TableColumn<SegurosRowModel.PlanRow, String> colPVigencia;

    @FXML
    private TableColumn<SegurosRowModel.PlanRow, String> colPCobertura;

    @FXML
    private TableColumn<SegurosRowModel.PlanRow, String> colPCopago;

    @FXML
    private TableColumn<SegurosRowModel.PlanRow, String> colPCategorias;

    @FXML
    private TableColumn<SegurosRowModel.PlanRow, String> colPRestricciones;

    // ---- Sub-view 3: Autorizaciones ----
    @FXML
    private TableView<SegurosRowModel.AutorizacionRow> autorizacionesTable;

    @FXML
    private TableColumn<SegurosRowModel.AutorizacionRow, String> colAAutorizacion;

    @FXML
    private TableColumn<SegurosRowModel.AutorizacionRow, String> colACliente;

    @FXML
    private TableColumn<SegurosRowModel.AutorizacionRow, String> colAPlan;

    @FXML
    private TableColumn<SegurosRowModel.AutorizacionRow, String> colAFecha;

    @FXML
    private TableColumn<SegurosRowModel.AutorizacionRow, String> colAEstado;

    @FXML
    private TableColumn<SegurosRowModel.AutorizacionRow, String> colAObservacion;

    // ---- Sub-view 4: Reclamos ----
    @FXML
    private TableView<SegurosRowModel.ReclamoRow> reclamosTable;

    @FXML
    private TableColumn<SegurosRowModel.ReclamoRow, String> colRReclamo;

    @FXML
    private TableColumn<SegurosRowModel.ReclamoRow, String> colRCliente;

    @FXML
    private TableColumn<SegurosRowModel.ReclamoRow, String> colROrden;

    @FXML
    private TableColumn<SegurosRowModel.ReclamoRow, String> colRMonto;

    @FXML
    private TableColumn<SegurosRowModel.ReclamoRow, String> colREstado;

    @FXML
    private TableColumn<SegurosRowModel.ReclamoRow, String> colRFechaEnvio;

    // ---- Sub-view 5: Respuestas ----
    @FXML
    private TableView<SegurosRowModel.RespuestaRow> respuestasTable;

    @FXML
    private TableColumn<SegurosRowModel.RespuestaRow, String> colRSFecha;

    @FXML
    private TableColumn<SegurosRowModel.RespuestaRow, String> colRSReferencia;

    @FXML
    private TableColumn<SegurosRowModel.RespuestaRow, String> colRSTipo;

    @FXML
    private TableColumn<SegurosRowModel.RespuestaRow, String> colRSEstado;

    @FXML
    private TableColumn<SegurosRowModel.RespuestaRow, String> colRSResultado;

    // ---- Sub-view 6: CoberturaVenta ----
    @FXML
    private TableView<SegurosRowModel.CoberturaVentaRow> coberturaVentaTable;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVVenta;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVCliente;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVPlan;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVMonto;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVCopago;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVSaldo;

    @FXML
    private TableColumn<SegurosRowModel.CoberturaVentaRow, String> colCVEstado;

    // ---- Sub-view 7: Historico ----
    @FXML
    private TableView<SegurosRowModel.HistoricoRow> historicoTable;

    @FXML
    private TableColumn<SegurosRowModel.HistoricoRow, String> colHFecha;

    @FXML
    private TableColumn<SegurosRowModel.HistoricoRow, String> colHReferencia;

    @FXML
    private TableColumn<SegurosRowModel.HistoricoRow, String> colHCliente;

    @FXML
    private TableColumn<SegurosRowModel.HistoricoRow, String> colHTipoCaso;

    @FXML
    private TableColumn<SegurosRowModel.HistoricoRow, String> colHEstadoFinal;

    @FXML
    private TableColumn<SegurosRowModel.HistoricoRow, String> colHObservacion;

    @FXML
    private Label footerHistorico;

    // ---- Right panel: Coverage case summary ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipoCaso;

    @FXML
    private Label summaryPlanConvenio;

    @FXML
    private Label summaryCliente;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryVigencia;

    @FXML
    private Label summaryEstadoActual;

    @FXML
    private Label summaryAutorizacion;

    @FXML
    private Label summaryReclamoAsociado;

    @FXML
    private Label summaryMontoCubierto;

    @FXML
    private Label summaryCopago;

    @FXML
    private Label summarySaldoCliente;

    @FXML
    private Button summaryBtnVerificar;

    @FXML
    private Button summaryBtnAutorizar;

    @FXML
    private Button summaryBtnReclamar;

    @FXML
    private BorderPane rootPane;

    // ---- Facade ----
    private SegurosFacade facade;

    private SegurosFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    public void initialize() {
        this.facade = new SegurosFacade();
        this.currentFilters = new SegurosFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Verificacion();
        setupSubView2_Planes();
        setupSubView3_Autorizaciones();
        setupSubView4_Reclamos();
        setupSubView5_Respuestas();
        setupSubView6_CoberturaVenta();
        setupSubView7_Historico();
        setupActionButtons();
        setupSummaryPanel();

        // Show first sub-view by default
        showSubView(0);
        if (btnVerificacion != null) btnVerificacion.setSelected(true);

        // Load initial data
        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (planConvenioCombo != null) {
            List<String> planes = facade.getPlanesConvenio();
            String[] items = planes.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Plan convenio", "Todos", items);
            replaceInParent(planConvenioCombo, combo);
            planConvenioCombo = combo;
            planConvenioCombo.setOnAction(e -> applyFilters());
        }

        if (tipoCasoCombo != null) {
            List<String> tipos = facade.getTiposCaso();
            String[] items = tipos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Tipo caso", "Todos", items);
            replaceInParent(tipoCasoCombo, combo);
            tipoCasoCombo = combo;
            tipoCasoCombo.setOnAction(e -> applyFilters());
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

        if (soloCasosPendientesCheck != null) {
            soloCasosPendientesCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
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
        if (btnVerificacion != null) {
            btnVerificacion.setToggleGroup(group);
            btnVerificacion.setOnAction(e -> showSubView(0));
        }
        if (btnPlanes != null) {
            btnPlanes.setToggleGroup(group);
            btnPlanes.setOnAction(e -> showSubView(1));
        }
        if (btnAutorizaciones != null) {
            btnAutorizaciones.setToggleGroup(group);
            btnAutorizaciones.setOnAction(e -> showSubView(2));
        }
        if (btnReclamos != null) {
            btnReclamos.setToggleGroup(group);
            btnReclamos.setOnAction(e -> showSubView(3));
        }
        if (btnRespuestas != null) {
            btnRespuestas.setToggleGroup(group);
            btnRespuestas.setOnAction(e -> showSubView(4));
        }
        if (btnCoberturaVenta != null) {
            btnCoberturaVenta.setToggleGroup(group);
            btnCoberturaVenta.setOnAction(e -> showSubView(5));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(group);
            btnHistorico.setOnAction(e -> showSubView(6));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionVerificacion,
                sectionPlanes,
                sectionAutorizaciones,
                sectionReclamos,
                sectionRespuestas,
                sectionCoberturaVenta,
                sectionHistorico
        };

        for (int i = 0; i < sections.length; i++) {
            if (sections[i] != null) {
                sections[i].setVisible(i == index);
                sections[i].setManaged(i == index);
            }
        }

        switch (index) {
            case 0 -> loadSubView1_Verificacion();
            case 1 -> loadSubView2_Planes();
            case 2 -> loadSubView3_Autorizaciones();
            case 3 -> loadSubView4_Reclamos();
            case 4 -> loadSubView5_Respuestas();
            case 5 -> loadSubView6_CoberturaVenta();
            case 6 -> loadSubView7_Historico();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Verificacion de cobertura

    private void setupSubView1_Verificacion() {
        if (colVReferencia != null) colVReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colVCliente != null) colVCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colVPlan != null) colVPlan.setCellValueFactory(data -> data.getValue().planConvenioProperty());
        if (colVEstado != null) colVEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colVVigencia != null) colVVigencia.setCellValueFactory(data -> data.getValue().vigenciaProperty());
        if (colVMonto != null) colVMonto.setCellValueFactory(data -> data.getValue().montoDisponibleProperty());
        if (colVSucursal != null) colVSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (colVEstado != null) {
            colVEstado.setCellFactory(createVerificacionStatusBadgeCell());
        }

        if (verificacionTable != null) {
            verificacionTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onVerificacionSelected(newVal));
        }
    }

    private void loadSubView1_Verificacion() {
        if (verificacionTable == null) return;

        PageResult<SegurosRowModel.VerificacionRow> pageResult =
                facade.getVerificaciones(currentFilters, currentPageRequest);
        ObservableList<SegurosRowModel.VerificacionRow> data =
                FXCollections.observableArrayList(pageResult.getItems());
        verificacionTable.setItems(data);

        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages(pageResult.getTotalPages());
            paginationBarController.setTotalItems(pageResult.getTotalItems());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        if (lblVerificacionCount != null) {
            lblVerificacionCount.setText(data.size() + " verificaciones visibles");
        }

        if (footerVerificacion != null) {
            footerVerificacion.setText("Mostrando " + data.size() + " de " + pageResult.getTotalItems() + " verificaciones");
        }

        if (!data.isEmpty()) {
            verificacionTable.getSelectionModel().selectFirst();
        }
    }

    private void onVerificacionSelected(SegurosRowModel.VerificacionRow row) {
        if (row == null) return;
        SegurosSummaryModel summary = facade.buildSummary(row);
        updateSummaryPanel(summary);
    }

    private javafx.util.Callback<TableColumn<SegurosRowModel.VerificacionRow, String>,
            javafx.scene.control.TableCell<SegurosRowModel.VerificacionRow, String>>
    createVerificacionStatusBadgeCell() {
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

    // ------------------------------------------------------------------ Sub-view 2: Planes

    private void setupSubView2_Planes() {
        if (colPNombre != null) colPNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colPVigencia != null) colPVigencia.setCellValueFactory(data -> data.getValue().vigenciaGeneralProperty());
        if (colPCobertura != null) colPCobertura.setCellValueFactory(data -> data.getValue().coberturaMaximaProperty());
        if (colPCopago != null) colPCopago.setCellValueFactory(data -> data.getValue().copagoProperty());
        if (colPCategorias != null) colPCategorias.setCellValueFactory(data -> data.getValue().categoriasCubiertasProperty());
        if (colPRestricciones != null) colPRestricciones.setCellValueFactory(data -> data.getValue().restriccionesProperty());
    }

    private void loadSubView2_Planes() {
        if (planesTable == null) return;
        List<SegurosRowModel.PlanRow> data = facade.getPlanes();
        planesTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 3: Autorizaciones

    private void setupSubView3_Autorizaciones() {
        if (colAAutorizacion != null) colAAutorizacion.setCellValueFactory(data -> data.getValue().autorizacionProperty());
        if (colACliente != null) colACliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colAPlan != null) colAPlan.setCellValueFactory(data -> data.getValue().planProperty());
        if (colAFecha != null) colAFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colAEstado != null) colAEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colAObservacion != null) colAObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colAEstado != null) {
            colAEstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView3_Autorizaciones() {
        if (autorizacionesTable == null) return;
        List<SegurosRowModel.AutorizacionRow> data = facade.getAutorizaciones();
        autorizacionesTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 4: Reclamos

    private void setupSubView4_Reclamos() {
        if (colRReclamo != null) colRReclamo.setCellValueFactory(data -> data.getValue().reclamoProperty());
        if (colRCliente != null) colRCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colROrden != null) colROrden.setCellValueFactory(data -> data.getValue().ordenRelacionadaProperty());
        if (colRMonto != null) colRMonto.setCellValueFactory(data -> data.getValue().montoReclamadoProperty());
        if (colREstado != null) colREstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRFechaEnvio != null) colRFechaEnvio.setCellValueFactory(data -> data.getValue().fechaEnvioProperty());

        if (colREstado != null) {
            colREstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView4_Reclamos() {
        if (reclamosTable == null) return;
        List<SegurosRowModel.ReclamoRow> data = facade.getReclamos();
        reclamosTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 5: Respuestas

    private void setupSubView5_Respuestas() {
        if (colRSFecha != null) colRSFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colRSReferencia != null) colRSReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colRSTipo != null) colRSTipo.setCellValueFactory(data -> data.getValue().tipoRespuestaProperty());
        if (colRSEstado != null) colRSEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRSResultado != null) colRSResultado.setCellValueFactory(data -> data.getValue().resultadoProperty());

        if (colRSEstado != null) {
            colRSEstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView5_Respuestas() {
        if (respuestasTable == null) return;
        List<SegurosRowModel.RespuestaRow> data = facade.getRespuestas();
        respuestasTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 6: CoberturaVenta

    private void setupSubView6_CoberturaVenta() {
        if (colCVVenta != null) colCVVenta.setCellValueFactory(data -> data.getValue().ventaProperty());
        if (colCVCliente != null) colCVCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colCVPlan != null) colCVPlan.setCellValueFactory(data -> data.getValue().planAplicadoProperty());
        if (colCVMonto != null) colCVMonto.setCellValueFactory(data -> data.getValue().montoCubiertoProperty());
        if (colCVCopago != null) colCVCopago.setCellValueFactory(data -> data.getValue().copagoProperty());
        if (colCVSaldo != null) colCVSaldo.setCellValueFactory(data -> data.getValue().saldoClienteProperty());
        if (colCVEstado != null) colCVEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colCVEstado != null) {
            colCVEstado.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView6_CoberturaVenta() {
        if (coberturaVentaTable == null) return;
        List<SegurosRowModel.CoberturaVentaRow> data = facade.getCoberturaVenta();
        coberturaVentaTable.setItems(FXCollections.observableArrayList(data));
    }

    // ------------------------------------------------------------------ Sub-view 7: Historico

    private void setupSubView7_Historico() {
        if (colHFecha != null) colHFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHReferencia != null) colHReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHCliente != null) colHCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colHTipoCaso != null) colHTipoCaso.setCellValueFactory(data -> data.getValue().tipoCasoProperty());
        if (colHEstadoFinal != null) colHEstadoFinal.setCellValueFactory(data -> data.getValue().estadoFinalProperty());
        if (colHObservacion != null) colHObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colHEstadoFinal != null) {
            colHEstadoFinal.setCellFactory(createGenericStatusBadgeCell());
        }
    }

    private void loadSubView7_Historico() {
        if (historicoTable == null) return;
        List<SegurosRowModel.HistoricoRow> data = facade.getHistorico(currentFilters);
        historicoTable.setItems(FXCollections.observableArrayList(data));

        if (footerHistorico != null) {
            footerHistorico.setText("Total registros: " + data.size());
        }
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (nuevaVerificacionBtn != null) {
            nuevaVerificacionBtn.setOnAction(e -> onNuevaVerificacion());
        }
        if (actualizarSegurosBtn != null) {
            actualizarSegurosBtn.setOnAction(e -> onActualizarSeguros());
        }
        if (exportarSegurosBtn != null) {
            exportarSegurosBtn.setOnAction(e -> onExportarSeguros());
        }
    }

    private void onNuevaVerificacion() {
        if (btnVerificacion != null) btnVerificacion.setSelected(true);
        showSubView(0);
    }

    private void onActualizarSeguros() {
        applyFilters();
        loadSummaryPanel();
    }

    private void onExportarSeguros() {
        // Placeholder: export to CSV
    }

    // ------------------------------------------------------------------ Filters apply/clear

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Verificacion();
    }

    private void applyFilters() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        currentFilters = new SegurosFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                planConvenioCombo != null && planConvenioCombo.getValue() != null ? planConvenioCombo.getValue() : "Todos",
                tipoCasoCombo != null && tipoCasoCombo.getValue() != null ? tipoCasoCombo.getValue() : "Todos",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().format(fmt) : "",
                hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().format(fmt) : "",
                soloCasosPendientesCheck != null && soloCasosPendientesCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private void clearFilters() {
        currentFilters = new SegurosFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (planConvenioCombo != null) planConvenioCombo.getSelectionModel().selectFirst();
        if (tipoCasoCombo != null) tipoCasoCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloCasosPendientesCheck != null) soloCasosPendientesCheck.setSelected(false);

        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private int getCurrentSubViewIndex() {
        if (btnVerificacion != null && btnVerificacion.isSelected()) return 0;
        if (btnPlanes != null && btnPlanes.isSelected()) return 1;
        if (btnAutorizaciones != null && btnAutorizaciones.isSelected()) return 2;
        if (btnReclamos != null && btnReclamos.isSelected()) return 3;
        if (btnRespuestas != null && btnRespuestas.isSelected()) return 4;
        if (btnCoberturaVenta != null && btnCoberturaVenta.isSelected()) return 5;
        if (btnHistorico != null && btnHistorico.isSelected()) return 6;
        return 0;
    }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnVerificar != null) {
            summaryBtnVerificar.setOnAction(e -> verificarCobertura());
        }
        if (summaryBtnAutorizar != null) {
            summaryBtnAutorizar.setOnAction(e -> solicitarAutorizacion());
        }
        if (summaryBtnReclamar != null) {
            summaryBtnReclamar.setOnAction(e -> enviarReclamo());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(SegurosSummaryModel.demoSeed());
    }

    private void updateSummaryPanel(SegurosSummaryModel summary) {
        if (summary == null) return;

        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryTipoCaso != null) summaryTipoCaso.setText(summary.tipoCaso());
        if (summaryPlanConvenio != null) summaryPlanConvenio.setText(summary.planConvenio());
        if (summaryCliente != null) summaryCliente.setText(summary.cliente());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryVigencia != null) summaryVigencia.setText(summary.vigencia());
        if (summaryEstadoActual != null) summaryEstadoActual.setText(summary.estadoActual());
        if (summaryAutorizacion != null) summaryAutorizacion.setText(summary.autorizacion());
        if (summaryReclamoAsociado != null) summaryReclamoAsociado.setText(summary.reclamoAsociado());
        if (summaryMontoCubierto != null) summaryMontoCubierto.setText(summary.montoCubierto());
        if (summaryCopago != null) summaryCopago.setText(summary.copago());
        if (summarySaldoCliente != null) summarySaldoCliente.setText(summary.saldoCliente());
    }

    private void verificarCobertura() { /* placeholder */ }
    private void solicitarAutorizacion() { /* placeholder */ }
    private void enviarReclamo() { /* placeholder */ }

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
            case "cobertura activa", "autorizada", "reclamo aceptado", "aceptado", "aplicada",
                 "completado", "activo", "confirmado" ->
                    StatusBadgeModel.success(item);
            case "pendiente", "pendiente validacion", "en proceso", "reclamo enviado",
                 "en espera", "pendiente repuesto" ->
                    StatusBadgeModel.warning(item);
            case "cancelado", "rechazado", "vencido", "cobertura expirada", "no" ->
                    StatusBadgeModel.danger(item);
            default ->
                    StatusBadgeModel.neutral(item);
        };
    }
}

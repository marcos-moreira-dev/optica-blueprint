package com.marcosmoreira.opticademo.modules.reportes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller principal del modulo de Reportes del sistema optico.
 * <p>
 * Gestiona siete sub-vistas analiticas: Resumen ejecutivo, Ventas y desempeno comercial,
 * Inventario y rotacion, Agenda y atencion, Laboratorio y cumplimiento, Cobros y cartera,
 * y Seguimiento y retencion. Cada sub-vista presenta KPIs y tablas especializadas
 * con indicadores de gestion para la toma de decisiones.
 * </p>
 * <p>
 * La arquitectura sigue el patron de tres paneles: filtros superiores, area central
 * con sub-vistas intercambiables, y panel derecho persistente con resumen del KPI
 * o reporte seleccionado. Toda la logica de agregacion y calculo de indicadores
 * esta delegada en {@link ReportesFacade}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ReportesFacade
 * @see ReportesFilters
 * @see ReportesSummaryModel
 */
public class ReportesController {

    // ---- Top bar ----

    /** Boton para generar un resumen ejecutivo de los KPIs principales. */
    @FXML
    private Button generarResumenBtn;

    /** Boton para actualizar todos los datos del modulo de reportes. */
    @FXML
    private Button actualizarReportesBtn;

    /** Boton para exportar el reporte actualmente visible. */
    @FXML
    private Button exportarReporteBtn;

    // ---- Filters ----

    /** ComboBox para filtrar por periodo (semanal, mensual, trimestral, etc.). */
    @FXML
    private ComboBox<String> periodoCombo;

    /** ComboBox para filtrar por sucursal. */
    @FXML
    private ComboBox<String> sucursalCombo;

    /** DatePicker para fecha inicio del rango de reportes. */
    @FXML
    private DatePicker desdePicker;

    /** DatePicker para fecha fin del rango de reportes. */
    @FXML
    private DatePicker hastaPicker;

    /** ComboBox para filtrar por categoria de producto o servicio. */
    @FXML
    private ComboBox<String> categoriaCombo;

    /** CheckBox para mostrar solo datos criticos que requieren atencion. */
    @FXML
    private CheckBox soloDatosCriticosCheck;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----

    /** Toggle button para la sub-vista Resumen ejecutivo. */
    @FXML
    private ToggleButton btnResumen;

    /** Toggle button para la sub-vista Ventas y desempeno comercial. */
    @FXML
    private ToggleButton btnVentas;

    /** Toggle button para la sub-vista Inventario y rotacion. */
    @FXML
    private ToggleButton btnInventario;

    /** Toggle button para la sub-vista Agenda y atencion. */
    @FXML
    private ToggleButton btnAgenda;

    /** Toggle button para la sub-vista Laboratorio y cumplimiento. */
    @FXML
    private ToggleButton btnLaboratorio;

    /** Toggle button para la sub-vista Cobros y cartera. */
    @FXML
    private ToggleButton btnCobros;

    /** Toggle button para la sub-vista Seguimiento y retencion. */
    @FXML
    private ToggleButton btnRetencion;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionResumen;

    @FXML
    private VBox sectionVentas;

    @FXML
    private VBox sectionInventario;

    @FXML
    private VBox sectionAgenda;

    @FXML
    private VBox sectionLaboratorio;

    @FXML
    private VBox sectionCobros;

    @FXML
    private VBox sectionRetencion;

    // ---- Sub-view 1: Resumen ejecutivo ----
    @FXML
    private Label lblVentasPeriodo;

    @FXML
    private Label lblTicketPromedio;

    @FXML
    private Label lblOrdenesActivas;

    @FXML
    private Label lblTrabajosRetrasados;

    @FXML
    private Label lblStockCritico;

    @FXML
    private Label lblRecallsPendientes;

    @FXML
    private Label lblCobrosPendientes;

    @FXML
    private Label lblUtilizacionAgenda;

    @FXML
    private TableView<ReportesRowModel.KpiResumenRow> resumenTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<ReportesRowModel.KpiResumenRow, String> colRArea;

    @FXML
    private TableColumn<ReportesRowModel.KpiResumenRow, String> colRIndicador;

    @FXML
    private TableColumn<ReportesRowModel.KpiResumenRow, String> colRValor;

    @FXML
    private TableColumn<ReportesRowModel.KpiResumenRow, String> colREstado;

    @FXML
    private VBox alertasGerencialesBox;

    // ---- Sub-view 2: Ventas y desempeno comercial ----
    @FXML
    private Label lblVentasTotales;

    @FXML
    private Label lblTicketPromedioVentas;

    @FXML
    private Label lblRevenuePorCliente;

    @FXML
    private Label lblCaptureRate;

    @FXML
    private TableView<ReportesRowModel.ComercialRow> comercialTable;

    @FXML
    private TableColumn<ReportesRowModel.ComercialRow, String> colCCategoria;

    @FXML
    private TableColumn<ReportesRowModel.ComercialRow, String> colCVentas;

    @FXML
    private TableColumn<ReportesRowModel.ComercialRow, String> colCUnidades;

    @FXML
    private TableColumn<ReportesRowModel.ComercialRow, String> colCParticipacion;

    @FXML
    private TableColumn<ReportesRowModel.ComercialRow, String> colCObservacion;

    @FXML
    private Button btnVentasPorAsesor;

    @FXML
    private Button btnExportarVentas;

    @FXML
    private Button btnAbrirCaja;

    // ---- Sub-view 3: Inventario y rotacion ----
    @FXML
    private Label lblBestSellers;

    @FXML
    private Label lblLentos;

    @FXML
    private Label lblEnvejecido;

    @FXML
    private Label lblCritico;

    @FXML
    private TableView<ReportesRowModel.RotacionRow> rotacionTable;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotReferencia;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotNombre;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotCategoria;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotRotacion;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotUltimaSalida;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotEstado;

    @FXML
    private TableColumn<ReportesRowModel.RotacionRow, String> colRotObservacion;

    @FXML
    private Button btnAbrirInventario;

    @FXML
    private Button btnExportarRotacion;

    @FXML
    private Button btnMarcarRevision;

    // ---- Sub-view 4: Agenda y atencion ----
    @FXML
    private Label lblCitasRegistradas;

    @FXML
    private Label lblUtilizacion;

    @FXML
    private Label lblNoShows;

    @FXML
    private Label lblConversion;

    @FXML
    private TableView<ReportesRowModel.AgendaRow> agendaTable;

    @FXML
    private TableColumn<ReportesRowModel.AgendaRow, String> colAIndicador;

    @FXML
    private TableColumn<ReportesRowModel.AgendaRow, String> colAValor;

    @FXML
    private TableColumn<ReportesRowModel.AgendaRow, String> colAMeta;

    @FXML
    private TableColumn<ReportesRowModel.AgendaRow, String> colAEstado;

    @FXML
    private TableColumn<ReportesRowModel.AgendaRow, String> colAObservacion;

    @FXML
    private Button btnAbrirAgenda;

    @FXML
    private Button btnExportarAtencion;

    @FXML
    private Button btnVerRecalls;

    // ---- Sub-view 5: Laboratorio y cumplimiento ----
    @FXML
    private Label lblOrdenesCreadas;

    @FXML
    private Label lblATiempo;

    @FXML
    private Label lblRetrasos;

    @FXML
    private Label lblRetrabajos;

    @FXML
    private TableView<ReportesRowModel.LaboratorioRow> laboratorioTable;

    @FXML
    private TableColumn<ReportesRowModel.LaboratorioRow, String> colLIndicador;

    @FXML
    private TableColumn<ReportesRowModel.LaboratorioRow, String> colLValor;

    @FXML
    private TableColumn<ReportesRowModel.LaboratorioRow, String> colLEstado;

    @FXML
    private TableColumn<ReportesRowModel.LaboratorioRow, String> colLObservacion;

    @FXML
    private Button btnAbrirLaboratorio;

    @FXML
    private Button btnExportarCumplimiento;

    @FXML
    private Button btnVerIncidencias;

    // ---- Sub-view 6: Cobros y cartera ----
    @FXML
    private Label lblCobrado;

    @FXML
    private Label lblPendiente;

    @FXML
    private Label lblAbonoParcial;

    @FXML
    private Label lblVencidos;

    @FXML
    private TableView<ReportesRowModel.CarteraRow> carteraTable;

    @FXML
    private TableColumn<ReportesRowModel.CarteraRow, String> colCPOrden;

    @FXML
    private TableColumn<ReportesRowModel.CarteraRow, String> colCPCliente;

    @FXML
    private TableColumn<ReportesRowModel.CarteraRow, String> colCPSaldo;

    @FXML
    private TableColumn<ReportesRowModel.CarteraRow, String> colCPUltimoPago;

    @FXML
    private TableColumn<ReportesRowModel.CarteraRow, String> colCPEstado;

    @FXML
    private TableColumn<ReportesRowModel.CarteraRow, String> colCPSucursal;

    @FXML
    private Button btnAbrirCajaCobros;

    @FXML
    private Button btnExportarCartera;

    @FXML
    private Button btnVerSeguimiento;

    // ---- Sub-view 7: Seguimiento y retencion ----
    @FXML
    private Label lblRecallsPendientesRet;

    @FXML
    private Label lblRecordatoriosEnviados;

    @FXML
    private Label lblNoRetirados;

    @FXML
    private Label lblResueltos;

    @FXML
    private TableView<ReportesRowModel.RetencionRow> retencionTable;

    @FXML
    private TableColumn<ReportesRowModel.RetencionRow, String> colRetCliente;

    @FXML
    private TableColumn<ReportesRowModel.RetencionRow, String> colRetTipo;

    @FXML
    private TableColumn<ReportesRowModel.RetencionRow, String> colRetEstado;

    @FXML
    private TableColumn<ReportesRowModel.RetencionRow, String> colRetFechaObjetivo;

    @FXML
    private TableColumn<ReportesRowModel.RetencionRow, String> colRetResultado;

    @FXML
    private TableColumn<ReportesRowModel.RetencionRow, String> colRetSucursal;

    @FXML
    private Button btnAbrirSeguimiento;

    @FXML
    private Button btnExportarRetencion;

    @FXML
    private Button btnAbrirCliente;

    // ---- Right panel: KPI summary ----
    @FXML
    private Label summaryIndicador;

    @FXML
    private Label summaryCategoria;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryValorPrincipal;

    @FXML
    private Label summaryVariacionBreve;

    @FXML
    private Label summaryPeriodo;

    @FXML
    private Label summaryUltimaActualizacion;

    @FXML
    private Label summaryObservacionGerencial;

    @FXML
    private Label summaryAccionSugerida;

    @FXML
    private Button summaryBtnGenerar;

    @FXML
    private Button summaryBtnExportar;

    // ---- Facade ----

    /** Fachada que centraliza la logica de agregacion y calculo de indicadores del modulo. */
    private ReportesFacade facade;

    private ReportesFilters currentFilters;
    private com.marcosmoreira.opticademo.shared.query.PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;
    private String currentSubview = "resumen";

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia {@link ReportesFacade}, configura los combos de filtrado,
     * establece el sistema de toggle entre las siete sub-vistas analiticas,
     * configura las columnas de cada tabla, y carga el resumen ejecutivo inicial.
     * </p>
     */
    public void initialize() {
        this.facade = new ReportesFacade();
        this.currentFilters = new ReportesFilters();
        this.currentPageRequest = new com.marcosmoreira.opticademo.shared.query.PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Resumen();
        setupSubView2_Ventas();
        setupSubView3_Inventario();
        setupSubView4_Agenda();
        setupSubView5_Laboratorio();
        setupSubView6_Cobros();
        setupSubView7_Retencion();
        setupActionButtons();
        setupSummaryPanel();

        // Show first sub-view by default
        showSubView("resumen");
        if (btnResumen != null) btnResumen.setSelected(true);

        // Load initial summary
        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (periodoCombo != null) {
            List<String> periodos = facade.getPeriodos();
            periodoCombo.getItems().setAll(periodos);
            periodoCombo.setValue(currentFilters.getPeriodo());
            periodoCombo.setOnAction(e -> {
                currentFilters.setPeriodo(periodoCombo.getValue());
                refreshCurrentView();
            });
        }

        if (sucursalCombo != null) {
            sucursalCombo.getItems().setAll("Todas", "Matriz Centro", "Norte Mall");
            sucursalCombo.setValue(currentFilters.getSucursal());
            sucursalCombo.setOnAction(e -> {
                currentFilters.setSucursal(sucursalCombo.getValue());
                refreshCurrentView();
            });
        }

        if (categoriaCombo != null) {
            List<String> categorias = facade.getCategorias();
            categoriaCombo.getItems().setAll(categorias);
            categoriaCombo.setValue(currentFilters.getCategoria());
            categoriaCombo.setOnAction(e -> {
                currentFilters.setCategoria(categoriaCombo.getValue());
                refreshCurrentView();
            });
        }

        if (desdePicker != null) {
            desdePicker.valueProperty().addListener((obs, old, val) -> {
                currentFilters.setDesde(val != null ? val.toString() : "");
                refreshCurrentView();
            });
        }

        if (hastaPicker != null) {
            hastaPicker.valueProperty().addListener((obs, old, val) -> {
                currentFilters.setHasta(val != null ? val.toString() : "");
                refreshCurrentView();
            });
        }

        if (soloDatosCriticosCheck != null) {
            soloDatosCriticosCheck.selectedProperty().addListener((obs, old, val) -> {
                currentFilters.setSoloDatosCriticos(val);
                refreshCurrentView();
            });
        }

        if (limpiarFiltrosBtn != null) {
            limpiarFiltrosBtn.setOnAction(e -> clearFilters());
        }
    }

    private void clearFilters() {
        currentFilters = new ReportesFilters();
        if (periodoCombo != null) periodoCombo.setValue(currentFilters.getPeriodo());
        if (sucursalCombo != null) sucursalCombo.setValue(currentFilters.getSucursal());
        if (categoriaCombo != null) categoriaCombo.setValue(currentFilters.getCategoria());
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloDatosCriticosCheck != null) soloDatosCriticosCheck.setSelected(false);
        refreshCurrentView();
    }

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new com.marcosmoreira.opticademo.shared.query.PageRequest(currentPageIndex, pageSize);
        refreshCurrentView();
    }

    // ------------------------------------------------------------------ Sub-view toggle

    private void setupSubViewToggle() {
        ToggleGroup group = new ToggleGroup();
        if (btnResumen != null) {
            btnResumen.setToggleGroup(group);
            btnResumen.setOnAction(e -> showSubView("resumen"));
        }
        if (btnVentas != null) {
            btnVentas.setToggleGroup(group);
            btnVentas.setOnAction(e -> showSubView("ventas"));
        }
        if (btnInventario != null) {
            btnInventario.setToggleGroup(group);
            btnInventario.setOnAction(e -> showSubView("inventario"));
        }
        if (btnAgenda != null) {
            btnAgenda.setToggleGroup(group);
            btnAgenda.setOnAction(e -> showSubView("agenda"));
        }
        if (btnLaboratorio != null) {
            btnLaboratorio.setToggleGroup(group);
            btnLaboratorio.setOnAction(e -> showSubView("laboratorio"));
        }
        if (btnCobros != null) {
            btnCobros.setToggleGroup(group);
            btnCobros.setOnAction(e -> showSubView("cobros"));
        }
        if (btnRetencion != null) {
            btnRetencion.setToggleGroup(group);
            btnRetencion.setOnAction(e -> showSubView("retencion"));
        }
    }

    private void showSubView(String subview) {
        this.currentSubview = subview;

        VBox[] sections = {
                sectionResumen,
                sectionVentas,
                sectionInventario,
                sectionAgenda,
                sectionLaboratorio,
                sectionCobros,
                sectionRetencion
        };

        String[] keys = {"resumen", "ventas", "inventario", "agenda", "laboratorio", "cobros", "retencion"};

        for (int i = 0; i < sections.length; i++) {
            if (sections[i] != null) {
                boolean show = keys[i].equals(subview);
                sections[i].setVisible(show);
                sections[i].setManaged(show);
            }
        }

        // Load data for the selected sub-view
        switch (subview) {
            case "resumen" -> loadSubView1_Resumen();
            case "ventas" -> loadSubView2_Ventas();
            case "inventario" -> loadSubView3_Inventario();
            case "agenda" -> loadSubView4_Agenda();
            case "laboratorio" -> loadSubView5_Laboratorio();
            case "cobros" -> loadSubView6_Cobros();
            case "retencion" -> loadSubView7_Retencion();
        }
    }

    private void refreshCurrentView() {
        showSubView(currentSubview);
    }

    // ------------------------------------------------------------------ Sub-view 1: Resumen ejecutivo

    private void setupSubView1_Resumen() {
        if (colRArea != null) colRArea.setCellValueFactory(data -> data.getValue().areaProperty());
        if (colRIndicador != null) colRIndicador.setCellValueFactory(data -> data.getValue().indicadorProperty());
        if (colRValor != null) colRValor.setCellValueFactory(data -> data.getValue().valorProperty());
        if (colREstado != null) colREstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (resumenTable != null) {
            resumenTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onResumenRowSelected(newVal));
        }
    }

    private void loadSubView1_Resumen() {
        List<ReportesRowModel.KpiResumenRow> kpis = facade.getKpisResumen();

        // Update KPI cards
        if (lblVentasPeriodo != null) lblVentasPeriodo.setText("$8,420");
        if (lblTicketPromedio != null) lblTicketPromedio.setText("$96.30");
        if (lblOrdenesActivas != null) lblOrdenesActivas.setText("24");
        if (lblTrabajosRetrasados != null) lblTrabajosRetrasados.setText("4");
        if (lblStockCritico != null) lblStockCritico.setText("11");
        if (lblRecallsPendientes != null) lblRecallsPendientes.setText("18");
        if (lblCobrosPendientes != null) lblCobrosPendientes.setText("$412");
        if (lblUtilizacionAgenda != null) lblUtilizacionAgenda.setText("78%");

        // Update table
        if (resumenTable != null) {
            ObservableList<ReportesRowModel.KpiResumenRow> data = FXCollections.observableArrayList(kpis);
            resumenTable.setItems(data);

            // Update pagination
            if (paginationBarController != null) {
                paginationBarController.setCurrentPage(currentPageIndex + 1);
                paginationBarController.setTotalPages((int) Math.ceil((double) kpis.size() / pageSize));
                paginationBarController.setTotalItems(kpis.size());
                paginationBarController.setPageSize(pageSize);
                paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
                paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
            }

            if (!data.isEmpty()) {
                resumenTable.getSelectionModel().selectFirst();
            }
        }

        // Update alertas gerenciales
        if (alertasGerencialesBox != null) {
            buildAlertasGerenciales(alertasGerencialesBox, kpis);
        }

        // Update summary
        ReportesSummaryModel summary = facade.buildSummary("resumen");
        updateSummaryPanel(summary);
    }

    private void onResumenRowSelected(ReportesRowModel.KpiResumenRow row) {
        if (row == null) return;
        ReportesSummaryModel summary = facade.buildSummary("resumen");
        updateSummaryPanel(summary);
    }

    private void buildAlertasGerenciales(VBox container, List<ReportesRowModel.KpiResumenRow> kpis) {
        container.getChildren().clear();

        Label title = new Label("Alertas gerenciales");
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        container.getChildren().add(title);

        for (ReportesRowModel.KpiResumenRow kpi : kpis) {
            if ("Critico".equals(kpi.estado()) || "Atencion".equals(kpi.estado()) || "Abajo".equals(kpi.estado())) {
                Label alerta = new Label(kpi.indicador() + ": " + kpi.valor() + " (" + kpi.estado() + ")");
                alerta.setStyle("-fx-font-size: 12px; -fx-text-fill: -optica-text-secondary; -fx-padding: 2 0 2 0;");
                container.getChildren().add(alerta);
            }
        }
    }

    // ------------------------------------------------------------------ Sub-view 2: Ventas y desempeno comercial

    private void setupSubView2_Ventas() {
        if (colCCategoria != null) colCCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colCVentas != null) colCVentas.setCellValueFactory(data -> data.getValue().ventasProperty());
        if (colCUnidades != null) colCUnidades.setCellValueFactory(data -> data.getValue().unidadesProperty());
        if (colCParticipacion != null) colCParticipacion.setCellValueFactory(data -> data.getValue().participacionProperty());
        if (colCObservacion != null) colCObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (btnVentasPorAsesor != null) btnVentasPorAsesor.setOnAction(e -> verVentasPorAsesor());
        if (btnExportarVentas != null) btnExportarVentas.setOnAction(e -> exportarVentas());
        if (btnAbrirCaja != null) btnAbrirCaja.setOnAction(e -> abrirCaja());
    }

    private void loadSubView2_Ventas() {
        List<ReportesRowModel.ComercialRow> rows = facade.getComercial();

        if (lblVentasTotales != null) lblVentasTotales.setText("$8,420");
        if (lblTicketPromedioVentas != null) lblTicketPromedioVentas.setText("$96.30");
        if (lblRevenuePorCliente != null) lblRevenuePorCliente.setText("$156.00");
        if (lblCaptureRate != null) lblCaptureRate.setText("62%");

        if (comercialTable != null) {
            ObservableList<ReportesRowModel.ComercialRow> data = FXCollections.observableArrayList(rows);
            comercialTable.setItems(data);
            if (!data.isEmpty()) {
                comercialTable.getSelectionModel().selectFirst();
            }
        }

        ReportesSummaryModel summary = facade.buildSummary("ventas");
        updateSummaryPanel(summary);
    }

    private void verVentasPorAsesor() { /* placeholder */ }
    private void exportarVentas() { /* placeholder */ }
    private void abrirCaja() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 3: Inventario y rotacion

    private void setupSubView3_Inventario() {
        if (colRotReferencia != null) colRotReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colRotNombre != null) colRotNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colRotCategoria != null) colRotCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colRotRotacion != null) colRotRotacion.setCellValueFactory(data -> data.getValue().rotacionProperty());
        if (colRotUltimaSalida != null) colRotUltimaSalida.setCellValueFactory(data -> data.getValue().ultimaSalidaProperty());
        if (colRotEstado != null) colRotEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRotObservacion != null) colRotObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (btnAbrirInventario != null) btnAbrirInventario.setOnAction(e -> abrirInventario());
        if (btnExportarRotacion != null) btnExportarRotacion.setOnAction(e -> exportarRotacion());
        if (btnMarcarRevision != null) btnMarcarRevision.setOnAction(e -> marcarRevision());
    }

    private void loadSubView3_Inventario() {
        List<ReportesRowModel.RotacionRow> rows = facade.getRotacion();

        long bestSellers = rows.stream().filter(r -> r.estado().contains("Alta")).count();
        long lentos = rows.stream().filter(r -> r.estado().equals("Lento")).count();
        long envejecido = rows.stream().filter(r -> r.estado().equals("Envejecido")).count();
        long critico = rows.stream().filter(r -> r.estado().contains("critico") || r.estado().contains("Critico")).count();

        if (lblBestSellers != null) lblBestSellers.setText(String.valueOf(bestSellers));
        if (lblLentos != null) lblLentos.setText(String.valueOf(lentos));
        if (lblEnvejecido != null) lblEnvejecido.setText(String.valueOf(envejecido));
        if (lblCritico != null) lblCritico.setText(String.valueOf(critico));

        if (rotacionTable != null) {
            ObservableList<ReportesRowModel.RotacionRow> data = FXCollections.observableArrayList(rows);
            rotacionTable.setItems(data);
            if (!data.isEmpty()) {
                rotacionTable.getSelectionModel().selectFirst();
            }
        }

        ReportesSummaryModel summary = facade.buildSummary("inventario");
        updateSummaryPanel(summary);
    }

    private void abrirInventario() { /* placeholder */ }
    private void exportarRotacion() { /* placeholder */ }
    private void marcarRevision() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 4: Agenda y atencion

    private void setupSubView4_Agenda() {
        if (colAIndicador != null) colAIndicador.setCellValueFactory(data -> data.getValue().indicadorProperty());
        if (colAValor != null) colAValor.setCellValueFactory(data -> data.getValue().valorProperty());
        if (colAMeta != null) colAMeta.setCellValueFactory(data -> data.getValue().metaProperty());
        if (colAEstado != null) colAEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colAObservacion != null) colAObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (btnAbrirAgenda != null) btnAbrirAgenda.setOnAction(e -> abrirAgenda());
        if (btnExportarAtencion != null) btnExportarAtencion.setOnAction(e -> exportarAtencion());
        if (btnVerRecalls != null) btnVerRecalls.setOnAction(e -> verRecalls());
    }

    private void loadSubView4_Agenda() {
        List<ReportesRowModel.AgendaRow> rows = facade.getAgenda();

        for (ReportesRowModel.AgendaRow row : rows) {
            if (row.indicador().equals("Citas registradas") && lblCitasRegistradas != null) {
                lblCitasRegistradas.setText(row.valor());
            } else if (row.indicador().equals("Utilizacion") && lblUtilizacion != null) {
                lblUtilizacion.setText(row.valor());
            } else if (row.indicador().equals("No-shows") && lblNoShows != null) {
                lblNoShows.setText(row.valor());
            } else if (row.indicador().equals("Conversion cita-venta") && lblConversion != null) {
                lblConversion.setText(row.valor());
            }
        }

        if (agendaTable != null) {
            ObservableList<ReportesRowModel.AgendaRow> data = FXCollections.observableArrayList(rows);
            agendaTable.setItems(data);
            if (!data.isEmpty()) {
                agendaTable.getSelectionModel().selectFirst();
            }
        }

        ReportesSummaryModel summary = facade.buildSummary("agenda");
        updateSummaryPanel(summary);
    }

    private void abrirAgenda() { /* placeholder */ }
    private void exportarAtencion() { /* placeholder */ }
    private void verRecalls() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 5: Laboratorio y cumplimiento

    private void setupSubView5_Laboratorio() {
        if (colLIndicador != null) colLIndicador.setCellValueFactory(data -> data.getValue().indicadorProperty());
        if (colLValor != null) colLValor.setCellValueFactory(data -> data.getValue().valorProperty());
        if (colLEstado != null) colLEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colLObservacion != null) colLObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (btnAbrirLaboratorio != null) btnAbrirLaboratorio.setOnAction(e -> abrirLaboratorio());
        if (btnExportarCumplimiento != null) btnExportarCumplimiento.setOnAction(e -> exportarCumplimiento());
        if (btnVerIncidencias != null) btnVerIncidencias.setOnAction(e -> verIncidencias());
    }

    private void loadSubView5_Laboratorio() {
        List<ReportesRowModel.LaboratorioRow> rows = facade.getLaboratorio();

        for (ReportesRowModel.LaboratorioRow row : rows) {
            if (row.indicador().equals("Ordenes creadas") && lblOrdenesCreadas != null) {
                lblOrdenesCreadas.setText(row.valor());
            } else if (row.indicador().equals("Entregas a tiempo") && lblATiempo != null) {
                lblATiempo.setText(row.valor());
            } else if (row.indicador().equals("Retrasos") && lblRetrasos != null) {
                lblRetrasos.setText(row.valor());
            } else if (row.indicador().equals("Retrabajos") && lblRetrabajos != null) {
                lblRetrabajos.setText(row.valor());
            }
        }

        if (laboratorioTable != null) {
            ObservableList<ReportesRowModel.LaboratorioRow> data = FXCollections.observableArrayList(rows);
            laboratorioTable.setItems(data);
            if (!data.isEmpty()) {
                laboratorioTable.getSelectionModel().selectFirst();
            }
        }

        ReportesSummaryModel summary = facade.buildSummary("laboratorio");
        updateSummaryPanel(summary);
    }

    private void abrirLaboratorio() { /* placeholder */ }
    private void exportarCumplimiento() { /* placeholder */ }
    private void verIncidencias() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 6: Cobros y cartera

    private void setupSubView6_Cobros() {
        if (colCPOrden != null) colCPOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colCPCliente != null) colCPCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colCPSaldo != null) colCPSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colCPUltimoPago != null) colCPUltimoPago.setCellValueFactory(data -> data.getValue().ultimoPagoProperty());
        if (colCPEstado != null) colCPEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colCPSucursal != null) colCPSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (btnAbrirCajaCobros != null) btnAbrirCajaCobros.setOnAction(e -> abrirCajaCobros());
        if (btnExportarCartera != null) btnExportarCartera.setOnAction(e -> exportarCartera());
        if (btnVerSeguimiento != null) btnVerSeguimiento.setOnAction(e -> verSeguimientoCobros());
    }

    private void loadSubView6_Cobros() {
        List<ReportesRowModel.CarteraRow> rows = facade.getCartera();

        double cobrado = 0.0;
        double pendiente = 0.0;
        double abonoParcial = 0.0;
        double vencidos = 0.0;

        for (ReportesRowModel.CarteraRow row : rows) {
            String saldo = row.saldo().replace("$", "").trim();
            double val = 0;
            try { val = Double.parseDouble(saldo); } catch (NumberFormatException ignored) {}

            if (row.estado().equals("Al dia")) {
                cobrado += val;
            } else if (row.estado().equals("Con saldo pendiente")) {
                pendiente += val;
            } else if (row.estado().equals("Abono parcial")) {
                abonoParcial += val;
            } else if (row.estado().equals("Vencido")) {
                vencidos += val;
            }
        }

        if (lblCobrado != null) lblCobrado.setText("$" + String.format("%.2f", cobrado));
        if (lblPendiente != null) lblPendiente.setText("$" + String.format("%.2f", pendiente));
        if (lblAbonoParcial != null) lblAbonoParcial.setText("$" + String.format("%.2f", abonoParcial));
        if (lblVencidos != null) lblVencidos.setText("$" + String.format("%.2f", vencidos));

        if (carteraTable != null) {
            ObservableList<ReportesRowModel.CarteraRow> data = FXCollections.observableArrayList(rows);
            carteraTable.setItems(data);
            if (!data.isEmpty()) {
                carteraTable.getSelectionModel().selectFirst();
            }
        }

        ReportesSummaryModel summary = facade.buildSummary("cobros");
        updateSummaryPanel(summary);
    }

    private void abrirCajaCobros() { /* placeholder */ }
    private void exportarCartera() { /* placeholder */ }
    private void verSeguimientoCobros() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 7: Seguimiento y retencion

    private void setupSubView7_Retencion() {
        if (colRetCliente != null) colRetCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colRetTipo != null) colRetTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colRetEstado != null) colRetEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRetFechaObjetivo != null) colRetFechaObjetivo.setCellValueFactory(data -> data.getValue().fechaObjetivoProperty());
        if (colRetResultado != null) colRetResultado.setCellValueFactory(data -> data.getValue().resultadoProperty());
        if (colRetSucursal != null) colRetSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (btnAbrirSeguimiento != null) btnAbrirSeguimiento.setOnAction(e -> abrirSeguimiento());
        if (btnExportarRetencion != null) btnExportarRetencion.setOnAction(e -> exportarRetencion());
        if (btnAbrirCliente != null) btnAbrirCliente.setOnAction(e -> abrirClienteRetencion());
    }

    private void loadSubView7_Retencion() {
        List<ReportesRowModel.RetencionRow> rows = facade.getRetencion();

        long recallsPendientes = rows.stream().filter(r -> r.estado().equals("Pendiente")).count();
        long recordatoriosEnviados = rows.stream().filter(r -> r.estado().contains("Recordatorio") || r.estado().contains("confirmo")).count();
        long noRetirados = rows.stream().filter(r -> r.estado().equals("No retirado")).count();
        long resueltos = rows.stream().filter(r -> r.estado().equals("Resuelto")).count();

        if (lblRecallsPendientesRet != null) lblRecallsPendientesRet.setText(String.valueOf(recallsPendientes));
        if (lblRecordatoriosEnviados != null) lblRecordatoriosEnviados.setText(String.valueOf(recordatoriosEnviados));
        if (lblNoRetirados != null) lblNoRetirados.setText(String.valueOf(noRetirados));
        if (lblResueltos != null) lblResueltos.setText(String.valueOf(resueltos));

        if (retencionTable != null) {
            ObservableList<ReportesRowModel.RetencionRow> data = FXCollections.observableArrayList(rows);
            retencionTable.setItems(data);
            if (!data.isEmpty()) {
                retencionTable.getSelectionModel().selectFirst();
            }
        }

        ReportesSummaryModel summary = facade.buildSummary("retencion");
        updateSummaryPanel(summary);
    }

    private void abrirSeguimiento() { /* placeholder */ }
    private void exportarRetencion() { /* placeholder */ }
    private void abrirClienteRetencion() { /* placeholder */ }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (generarResumenBtn != null) {
            generarResumenBtn.setOnAction(e -> generarResumen());
        }
        if (actualizarReportesBtn != null) {
            actualizarReportesBtn.setOnAction(e -> actualizarReportes());
        }
        if (exportarReporteBtn != null) {
            exportarReporteBtn.setOnAction(e -> exportarReporte());
        }
    }

    private void generarResumen() { /* placeholder */ }
    private void actualizarReportes() { refreshCurrentView(); }
    private void exportarReporte() { /* placeholder */ }

    // ------------------------------------------------------------------ Right panel: KPI summary

    private void setupSummaryPanel() {
        if (summaryBtnGenerar != null) {
            summaryBtnGenerar.setOnAction(e -> generarResumen());
        }
        if (summaryBtnExportar != null) {
            summaryBtnExportar.setOnAction(e -> exportarReporte());
        }
    }

    private void loadSummaryPanel() {
        ReportesSummaryModel summary = facade.buildSummary(currentSubview);
        updateSummaryPanel(summary);
    }

    private void updateSummaryPanel(ReportesSummaryModel summary) {
        if (summary == null) return;
        if (summaryIndicador != null) summaryIndicador.setText(summary.indicador() != null ? summary.indicador() : "-");
        if (summaryCategoria != null) summaryCategoria.setText(summary.categoria() != null ? summary.categoria() : "-");
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal() != null ? summary.sucursal() : "-");
        if (summaryValorPrincipal != null) summaryValorPrincipal.setText(summary.valorPrincipal() != null ? summary.valorPrincipal() : "-");
        if (summaryVariacionBreve != null) summaryVariacionBreve.setText(summary.variacionBreve() != null ? summary.variacionBreve() : "-");
        if (summaryPeriodo != null) summaryPeriodo.setText(summary.periodo() != null ? summary.periodo() : "-");
        if (summaryUltimaActualizacion != null) summaryUltimaActualizacion.setText(summary.ultimaActualizacion() != null ? summary.ultimaActualizacion() : "-");
        if (summaryObservacionGerencial != null) summaryObservacionGerencial.setText(summary.observacionGerencial() != null ? summary.observacionGerencial() : "-");
        if (summaryAccionSugerida != null) summaryAccionSugerida.setText(summary.accionSugerida() != null ? summary.accionSugerida() : "-");
    }
}

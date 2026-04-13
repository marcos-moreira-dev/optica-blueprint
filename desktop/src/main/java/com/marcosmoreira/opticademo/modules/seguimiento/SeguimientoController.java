package com.marcosmoreira.opticademo.modules.seguimiento;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller principal del modulo de Seguimiento del sistema optico.
 * <p>
 * Gestiona seis sub-vistas especializadas: Bandeja de seguimiento, Recall y revisiones,
 * Pedidos listos y no retirados, Cobros pendientes, Mensajes y recordatorios, e Historico
 * de casos. Implementa una arquitectura de tres paneles: barra superior con acciones globales
 * y filtros, area central con sub-vistas intercambiables mediante toggle buttons, y panel
 * derecho persistente con resumen del caso seleccionado.
 * </p>
 * <p>
 * La logica de negocio esta delegada completamente en {@link SeguimientoFacade}, manteniendo
 * una separacion limpia entre la capa de presentacion y la capa de dominio. El controller
 * se encarga exclusivamente de la configuracion de componentes JavaFX, binding de datos a
 * las tablas, gestion de filtros y actualizacion del panel de resumen.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see SeguimientoFacade
 * @see SeguimientoFilters
 * @see SeguimientoSummaryModel
 */
public class SeguimientoController {

    // ---- Top bar ----

    /** Boton para crear un nuevo caso de seguimiento. */
    @FXML
    private Button nuevoSeguimientoBtn;

    /** Boton para actualizar los datos del modulo. */
    @FXML
    private Button actualizarSeguimientoBtn;

    /** Boton para exportar los datos de seguimiento. */
    @FXML
    private Button exportarSeguimientoBtn;

    // ---- Filters ----

    /** Campo de texto para busqueda libre por referencia o cliente. */
    @FXML
    private TextField searchField;

    /** ComboBox para filtrar por tipo de seguimiento (recall, no retirado, cobro, etc.). */
    @FXML
    private ComboBox<String> tipoCombo;

    /** ComboBox para filtrar por estado del caso. */
    @FXML
    private ComboBox<String> estadoCombo;

    /** ComboBox para filtrar por prioridad (alta, media, baja). */
    @FXML
    private ComboBox<String> prioridadCombo;

    /** ComboBox para filtrar por sucursal. */
    @FXML
    private ComboBox<String> sucursalCombo;

    /** ComboBox para filtrar por canal de contacto. */
    @FXML
    private ComboBox<String> canalCombo;

    /** DatePicker para fecha inicio del rango de filtrado. */
    @FXML
    private DatePicker desdePicker;

    /** DatePicker para fecha fin del rango de filtrado. */
    @FXML
    private DatePicker hastaPicker;

    /** CheckBox para mostrar solo casos abiertos. */
    @FXML
    private CheckBox soloCasosAbiertosCheck;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----

    /** Toggle button para la sub-vista Bandeja de seguimiento. */
    @FXML
    private ToggleButton btnBandeja;

    /** Toggle button para la sub-vista Recall y revisiones. */
    @FXML
    private ToggleButton btnRecall;

    /** Toggle button para la sub-vista Pedidos no retirados. */
    @FXML
    private ToggleButton btnNoRetirados;

    /** Toggle button para la sub-vista Cobros pendientes. */
    @FXML
    private ToggleButton btnCobros;

    /** Toggle button para la sub-vista Mensajes y recordatorios. */
    @FXML
    private ToggleButton btnMensajes;

    /** Toggle button para la sub-vista Historico. */
    @FXML
    private ToggleButton btnHistorico;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionBandeja;

    @FXML
    private VBox sectionRecall;

    @FXML
    private VBox sectionNoRetirados;

    @FXML
    private VBox sectionCobros;

    @FXML
    private VBox sectionMensajes;

    @FXML
    private VBox sectionHistorico;

    // ---- Sub-view 1: Bandeja de seguimiento ----
    @FXML
    private Label lblBandejaCount;

    /** Tabla principal que muestra los casos de seguimiento activos. */
    @FXML
    private TableView<SeguimientoRowModel.BandejaRow> bandejaTable;

    /** Componente de paginacion para la bandeja de seguimiento. */
    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBReferencia;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBCliente;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBTipo;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBEstado;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBFechaObjetivo;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBPrioridad;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBSucursal;

    @FXML
    private TableColumn<SeguimientoRowModel.BandejaRow, String> colBResponsable;

    @FXML
    private Label footerBandeja;

    // ---- Sub-view 2: Recall y revisiones ----
    @FXML
    private Label lblRecallPendiente;

    @FXML
    private Label lblRecetasVencidas;

    @FXML
    private Label lblRevisionesProximas;

    @FXML
    private TableView<SeguimientoRowModel.RecallRow> recallTable;

    @FXML
    private TableColumn<SeguimientoRowModel.RecallRow, String> colRCliente;

    @FXML
    private TableColumn<SeguimientoRowModel.RecallRow, String> colRUltimaVisita;

    @FXML
    private TableColumn<SeguimientoRowModel.RecallRow, String> colRMotivo;

    @FXML
    private TableColumn<SeguimientoRowModel.RecallRow, String> colRFechaSugerida;

    @FXML
    private TableColumn<SeguimientoRowModel.RecallRow, String> colREstado;

    @FXML
    private TableColumn<SeguimientoRowModel.RecallRow, String> colRSucursal;

    @FXML
    private Button btnEnviarRecordatorio;

    @FXML
    private Button btnAgendarRevision;

    @FXML
    private Button btnAbrirClienteRecall;

    // ---- Sub-view 3: Pedidos listos y no retirados ----
    @FXML
    private Label lblNoRetiradosTotal;

    @FXML
    private Label lblNoRetiradosNotificados;

    @FXML
    private Label lblNoRetiradosConSaldo;

    @FXML
    private TableView<SeguimientoRowModel.NoRetiradoRow> noRetiradosTable;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNROrden;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNRCliente;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNRDiasEsperando;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNRNotificacion;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNRSaldo;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNREstado;

    @FXML
    private TableColumn<SeguimientoRowModel.NoRetiradoRow, String> colNRSucursal;

    @FXML
    private Button btnNotificarClienteNR;

    @FXML
    private Button btnRegistrarRetiroNR;

    @FXML
    private Button btnMarcarSeguimientoNR;

    // ---- Sub-view 4: Cobros pendientes ----
    @FXML
    private Label lblCobrosCasosConSaldo;

    @FXML
    private Label lblCobrosMontoPendiente;

    @FXML
    private Label lblCobrosVencidos;

    @FXML
    private TableView<SeguimientoRowModel.CobroPendienteRow> cobrosTable;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPOrden;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPCliente;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPSaldo;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPUltimoPago;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPEstado;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPProximaAccion;

    @FXML
    private TableColumn<SeguimientoRowModel.CobroPendienteRow, String> colCPSucursal;

    @FXML
    private Button btnRegistrarContactoCP;

    @FXML
    private Button btnAbrirCajaCP;

    @FXML
    private Button btnMarcarResueltoCP;

    // ---- Sub-view 5: Mensajes y recordatorios ----
    @FXML
    private TableView<SeguimientoRowModel.MensajeRow> mensajesTable;

    @FXML
    private TableColumn<SeguimientoRowModel.MensajeRow, String> colMFecha;

    @FXML
    private TableColumn<SeguimientoRowModel.MensajeRow, String> colMCliente;

    @FXML
    private TableColumn<SeguimientoRowModel.MensajeRow, String> colMTipo;

    @FXML
    private TableColumn<SeguimientoRowModel.MensajeRow, String> colMCanal;

    @FXML
    private TableColumn<SeguimientoRowModel.MensajeRow, String> colMEstado;

    @FXML
    private TableColumn<SeguimientoRowModel.MensajeRow, String> colMResultado;

    @FXML
    private VBox mensajeDetailPanel;

    @FXML
    private Label lblMensajeDetail;

    @FXML
    private Button btnRegistrarMensaje;

    @FXML
    private Button btnMarcarRespondido;

    @FXML
    private Button btnReenviarRecordatorio;

    // ---- Sub-view 6: Historico ----
    @FXML
    private TextField historicoSearchField;

    @FXML
    private ComboBox<String> historicoTipoCombo;

    @FXML
    private ComboBox<String> historicoEstadoCombo;

    @FXML
    private ComboBox<String> historicoSucursalCombo;

    @FXML
    private DatePicker historicoDesdePicker;

    @FXML
    private DatePicker historicoHastaPicker;

    @FXML
    private CheckBox historicoSoloAbiertosCheck;

    @FXML
    private Button btnBuscarHistorico;

    @FXML
    private Button btnLimpiarHistorico;

    @FXML
    private TableView<SeguimientoRowModel.HistoricoRow> historicoTable;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHFecha;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHReferencia;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHCliente;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHTipo;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHEstadoFinal;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHResultado;

    @FXML
    private TableColumn<SeguimientoRowModel.HistoricoRow, String> colHSucursal;

    @FXML
    private Button btnAbrirCasoH;

    @FXML
    private Button btnExportarHistoricoH;

    @FXML
    private Label footerHistorico;

    // ---- Right panel: Case summary ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipoSeguimiento;

    @FXML
    private Label summaryPrioridad;

    @FXML
    private Label summaryCliente;

    @FXML
    private Label summaryCodigoCliente;

    @FXML
    private Label summaryUltimaVisita;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryEstadoActual;

    @FXML
    private Label summaryCanalContacto;

    @FXML
    private Label summaryUltimaInteraccion;

    @FXML
    private Label summaryAccionSugerida;

    @FXML
    private Label summaryFechaObjetivo;

    @FXML
    private Label summaryObservacionClave;

    @FXML
    private VBox summaryFieldsContainer;

    @FXML
    private Button summaryBtnAbrirCaso;

    @FXML
    private Button summaryBtnContactar;

    @FXML
    private Button summaryBtnAgendar;

    @FXML
    private Button summaryBtnExportar;

    /** Fachada que centraliza toda la logica de negocio del modulo de seguimiento. */
    private SeguimientoFacade facade;

    private SeguimientoFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia la fachada {@link SeguimientoFacade} con el {@link DemoStore} global,
     * configura los combos de filtrado, establece el sistema de toggle entre sub-vistas,
     * configura las columnas de cada tabla con sus respectivas cell value factories,
     * registra los listeners de seleccion para actualizar el panel de resumen,
     * y carga los datos iniciales de la bandeja de seguimiento.
     * </p>
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new SeguimientoFacade(store);
        this.currentFilters = new SeguimientoFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Bandeja();
        setupSubView2_Recall();
        setupSubView3_NoRetirados();
        setupSubView4_Cobros();
        setupSubView5_Mensajes();
        setupSubView6_Historico();
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
            List<String> tipos = facade.getTiposSeguimiento();
            String[] items = tipos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Tipo", "Todos", items);
            replaceInParent(tipoCombo, combo);
            tipoCombo = combo;
            tipoCombo.setOnAction(e -> applyFilters());
        }

        if (estadoCombo != null) {
            List<String> estados = facade.getEstadosSeguimiento();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (prioridadCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Prioridad", "Todas", "Alta", "Media", "Baja");
            replaceInParent(prioridadCombo, combo);
            prioridadCombo = combo;
            prioridadCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", "Matriz Centro", "Norte Mall");
            replaceInParent(sucursalCombo, combo);
            sucursalCombo = combo;
            sucursalCombo.setOnAction(e -> applyFilters());
        }

        if (canalCombo != null) {
            List<String> canales = facade.getCanales();
            String[] items = canales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Canal", "Todos", items);
            replaceInParent(canalCombo, combo);
            canalCombo = combo;
            canalCombo.setOnAction(e -> applyFilters());
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

        if (soloCasosAbiertosCheck != null) {
            soloCasosAbiertosCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
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
        if (btnRecall != null) {
            btnRecall.setToggleGroup(group);
            btnRecall.setOnAction(e -> showSubView(1));
        }
        if (btnNoRetirados != null) {
            btnNoRetirados.setToggleGroup(group);
            btnNoRetirados.setOnAction(e -> showSubView(2));
        }
        if (btnCobros != null) {
            btnCobros.setToggleGroup(group);
            btnCobros.setOnAction(e -> showSubView(3));
        }
        if (btnMensajes != null) {
            btnMensajes.setToggleGroup(group);
            btnMensajes.setOnAction(e -> showSubView(4));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(group);
            btnHistorico.setOnAction(e -> showSubView(5));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionBandeja,
                sectionRecall,
                sectionNoRetirados,
                sectionCobros,
                sectionMensajes,
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
            case 1 -> loadSubView2_Recall();
            case 2 -> loadSubView3_NoRetirados();
            case 3 -> loadSubView4_Cobros();
            case 4 -> loadSubView5_Mensajes();
            case 5 -> loadSubView6_Historico();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Bandeja de seguimiento

    private void setupSubView1_Bandeja() {
        if (colBReferencia != null) colBReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colBCliente != null) colBCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colBTipo != null) colBTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colBEstado != null) colBEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colBFechaObjetivo != null) colBFechaObjetivo.setCellValueFactory(data -> data.getValue().fechaObjetivoProperty());
        if (colBPrioridad != null) colBPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colBSucursal != null) colBSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colBResponsable != null) colBResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());

        // Custom cell for estado column with status badge
        if (colBEstado != null) {
            colBEstado.setCellFactory(createBandejaStatusBadgeCell());
        }

        // Selection listener for summary panel
        if (bandejaTable != null) {
            bandejaTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onBandejaRowSelected(newVal));
        }
    }

    private void loadSubView1_Bandeja() {
        if (bandejaTable == null) return;

        PageResult<SeguimientoRowModel.BandejaRow> pageResult =
                facade.getBandeja(currentFilters, currentPageRequest);
        ObservableList<SeguimientoRowModel.BandejaRow> data =
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
            lblBandejaCount.setText(data.size() + " casos visibles");
        }

        if (footerBandeja != null) {
            footerBandeja.setText("Mostrando " + data.size() + " de " + pageResult.getTotalItems() + " casos");
        }

        // Select first row
        if (!data.isEmpty()) {
            bandejaTable.getSelectionModel().selectFirst();
        }
    }

    private void onBandejaRowSelected(SeguimientoRowModel.BandejaRow row) {
        if (row == null) return;
        SeguimientoSummaryModel summary = facade.buildSummary(row);
        updateSummaryPanel(summary);
    }

    private javafx.util.Callback<TableColumn<SeguimientoRowModel.BandejaRow, String>,
            javafx.scene.control.TableCell<SeguimientoRowModel.BandejaRow, String>>
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

    // ------------------------------------------------------------------ Sub-view 2: Recall y revisiones

    private void setupSubView2_Recall() {
        if (colRCliente != null) colRCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colRUltimaVisita != null) colRUltimaVisita.setCellValueFactory(data -> data.getValue().ultimaVisitaProperty());
        if (colRMotivo != null) colRMotivo.setCellValueFactory(data -> data.getValue().motivoProperty());
        if (colRFechaSugerida != null) colRFechaSugerida.setCellValueFactory(data -> data.getValue().fechaSugeridaProperty());
        if (colREstado != null) colREstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRSucursal != null) colRSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell for estado column
        if (colREstado != null) {
            colREstado.setCellFactory(createGenericStatusBadgeCell());
        }

        if (recallTable != null) {
            recallTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onRecallSelected(newVal));
        }

        if (btnEnviarRecordatorio != null) {
            btnEnviarRecordatorio.setOnAction(e -> enviarRecordatorio());
        }
        if (btnAgendarRevision != null) {
            btnAgendarRevision.setOnAction(e -> agendarRevision());
        }
        if (btnAbrirClienteRecall != null) {
            btnAbrirClienteRecall.setOnAction(e -> abrirClienteRecall());
        }
    }

    private void loadSubView2_Recall() {
        if (recallTable == null) return;

        // Load stats
        SeguimientoFacade.StatsRecalls stats = facade.getStatsRecalls();
        if (lblRecallPendiente != null) lblRecallPendiente.setText(String.valueOf(stats.recallPendiente()));
        if (lblRecetasVencidas != null) lblRecetasVencidas.setText(String.valueOf(stats.recetasVencidas()));
        if (lblRevisionesProximas != null) lblRevisionesProximas.setText(String.valueOf(stats.revisionesProximas()));

        List<SeguimientoRowModel.RecallRow> recalls = facade.getRecalls();
        ObservableList<SeguimientoRowModel.RecallRow> data = FXCollections.observableArrayList(recalls);
        recallTable.setItems(data);

        if (!data.isEmpty()) {
            recallTable.getSelectionModel().selectFirst();
        }
    }

    private void onRecallSelected(SeguimientoRowModel.RecallRow row) {
        if (row == null) return;
        SeguimientoSummaryModel summary = facade.buildSummary(null);
        updateSummaryPanel(summary);
    }

    private void enviarRecordatorio() { /* placeholder */ }
    private void agendarRevision() { /* placeholder */ }
    private void abrirClienteRecall() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 3: Pedidos listos y no retirados

    private void setupSubView3_NoRetirados() {
        if (colNROrden != null) colNROrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colNRCliente != null) colNRCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colNRDiasEsperando != null) colNRDiasEsperando.setCellValueFactory(data -> data.getValue().diasEsperandoProperty());
        if (colNRNotificacion != null) colNRNotificacion.setCellValueFactory(data -> data.getValue().notificacionProperty());
        if (colNRSaldo != null) colNRSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colNREstado != null) colNREstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colNRSucursal != null) colNRSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell for estado column
        if (colNREstado != null) {
            colNREstado.setCellFactory(createGenericStatusBadgeCell());
        }

        if (noRetiradosTable != null) {
            noRetiradosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onNoRetiradoSelected(newVal));
        }

        if (btnNotificarClienteNR != null) {
            btnNotificarClienteNR.setOnAction(e -> notificarClienteNR());
        }
        if (btnRegistrarRetiroNR != null) {
            btnRegistrarRetiroNR.setOnAction(e -> registrarRetiroNR());
        }
        if (btnMarcarSeguimientoNR != null) {
            btnMarcarSeguimientoNR.setOnAction(e -> marcarSeguimientoNR());
        }
    }

    private void loadSubView3_NoRetirados() {
        if (noRetiradosTable == null) return;

        // Load stats
        SeguimientoFacade.StatsNoRetirados stats = facade.getStatsNoRetirados();
        if (lblNoRetiradosTotal != null) lblNoRetiradosTotal.setText(String.valueOf(stats.noRetirados()));
        if (lblNoRetiradosNotificados != null) lblNoRetiradosNotificados.setText(String.valueOf(stats.notificados()));
        if (lblNoRetiradosConSaldo != null) lblNoRetiradosConSaldo.setText(String.valueOf(stats.conSaldo()));

        List<SeguimientoRowModel.NoRetiradoRow> noRetirados = facade.getNoRetirados();
        ObservableList<SeguimientoRowModel.NoRetiradoRow> data = FXCollections.observableArrayList(noRetirados);
        noRetiradosTable.setItems(data);

        if (!data.isEmpty()) {
            noRetiradosTable.getSelectionModel().selectFirst();
        }
    }

    private void onNoRetiradoSelected(SeguimientoRowModel.NoRetiradoRow row) {
        if (row == null) return;
        SeguimientoSummaryModel summary = facade.buildSummary(null);
        updateSummaryPanel(summary);
    }

    private void notificarClienteNR() { /* placeholder */ }
    private void registrarRetiroNR() { /* placeholder */ }
    private void marcarSeguimientoNR() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 4: Cobros pendientes

    private void setupSubView4_Cobros() {
        if (colCPOrden != null) colCPOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colCPCliente != null) colCPCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colCPSaldo != null) colCPSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colCPUltimoPago != null) colCPUltimoPago.setCellValueFactory(data -> data.getValue().ultimoPagoProperty());
        if (colCPEstado != null) colCPEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colCPProximaAccion != null) colCPProximaAccion.setCellValueFactory(data -> data.getValue().proximaAccionProperty());
        if (colCPSucursal != null) colCPSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell for estado column
        if (colCPEstado != null) {
            colCPEstado.setCellFactory(createGenericStatusBadgeCell());
        }

        if (cobrosTable != null) {
            cobrosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onCobroSelected(newVal));
        }

        if (btnRegistrarContactoCP != null) {
            btnRegistrarContactoCP.setOnAction(e -> registrarContactoCP());
        }
        if (btnAbrirCajaCP != null) {
            btnAbrirCajaCP.setOnAction(e -> abrirCajaCP());
        }
        if (btnMarcarResueltoCP != null) {
            btnMarcarResueltoCP.setOnAction(e -> marcarResueltoCP());
        }
    }

    private void loadSubView4_Cobros() {
        if (cobrosTable == null) return;

        // Load stats
        SeguimientoFacade.StatsCobros stats = facade.getStatsCobros();
        if (lblCobrosCasosConSaldo != null) lblCobrosCasosConSaldo.setText(String.valueOf(stats.casosConSaldo()));
        if (lblCobrosMontoPendiente != null) lblCobrosMontoPendiente.setText(String.format("$%.2f", stats.montoPendiente()));
        if (lblCobrosVencidos != null) lblCobrosVencidos.setText(String.valueOf(stats.casosVencidos()));

        List<SeguimientoRowModel.CobroPendienteRow> cobros = facade.getCobrosPendientes();
        ObservableList<SeguimientoRowModel.CobroPendienteRow> data = FXCollections.observableArrayList(cobros);
        cobrosTable.setItems(data);

        if (!data.isEmpty()) {
            cobrosTable.getSelectionModel().selectFirst();
        }
    }

    private void onCobroSelected(SeguimientoRowModel.CobroPendienteRow row) {
        if (row == null) return;
        SeguimientoSummaryModel summary = facade.buildSummary(null);
        updateSummaryPanel(summary);
    }

    private void registrarContactoCP() { /* placeholder */ }
    private void abrirCajaCP() { /* placeholder */ }
    private void marcarResueltoCP() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 5: Mensajes y recordatorios

    private void setupSubView5_Mensajes() {
        if (colMFecha != null) colMFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colMCliente != null) colMCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colMTipo != null) colMTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colMCanal != null) colMCanal.setCellValueFactory(data -> data.getValue().canalProperty());
        if (colMEstado != null) colMEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colMResultado != null) colMResultado.setCellValueFactory(data -> data.getValue().resultadoProperty());

        // Custom cell for estado column
        if (colMEstado != null) {
            colMEstado.setCellFactory(createGenericStatusBadgeCell());
        }

        if (mensajesTable != null) {
            mensajesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onMensajeSelected(newVal));
        }

        if (btnRegistrarMensaje != null) {
            btnRegistrarMensaje.setOnAction(e -> registrarMensaje());
        }
        if (btnMarcarRespondido != null) {
            btnMarcarRespondido.setOnAction(e -> marcarRespondido());
        }
        if (btnReenviarRecordatorio != null) {
            btnReenviarRecordatorio.setOnAction(e -> reenviarRecordatorio());
        }
    }

    private void loadSubView5_Mensajes() {
        if (mensajesTable == null) return;

        List<SeguimientoRowModel.MensajeRow> mensajes = facade.getMensajes();
        ObservableList<SeguimientoRowModel.MensajeRow> data = FXCollections.observableArrayList(mensajes);
        mensajesTable.setItems(data);

        if (lblMensajeDetail != null) {
            lblMensajeDetail.setText("Seleccione un mensaje para ver el detalle");
        }

        if (!data.isEmpty()) {
            mensajesTable.getSelectionModel().selectFirst();
        }
    }

    private void onMensajeSelected(SeguimientoRowModel.MensajeRow row) {
        if (row == null) return;
        if (lblMensajeDetail != null) {
            lblMensajeDetail.setText(row.fecha() + " | " + row.cliente() + " | " + row.tipo() + " via " + row.canal());
        }
        SeguimientoSummaryModel summary = facade.buildSummary(null);
        updateSummaryPanel(summary);
    }

    private void registrarMensaje() { /* placeholder */ }
    private void marcarRespondido() { /* placeholder */ }
    private void reenviarRecordatorio() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 6: Historico

    private void setupSubView6_Historico() {
        if (colHFecha != null) colHFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHReferencia != null) colHReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHCliente != null) colHCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colHTipo != null) colHTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colHEstadoFinal != null) colHEstadoFinal.setCellValueFactory(data -> data.getValue().estadoFinalProperty());
        if (colHResultado != null) colHResultado.setCellValueFactory(data -> data.getValue().resultadoProperty());
        if (colHSucursal != null) colHSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell for estadoFinal column
        if (colHEstadoFinal != null) {
            colHEstadoFinal.setCellFactory(createGenericStatusBadgeCell());
        }

        if (historicoSearchField != null) {
            historicoSearchField.textProperty().addListener((obs, old, val) -> applyHistoricoFilters());
        }

        if (historicoTipoCombo != null) {
            List<String> tipos = facade.getTiposSeguimiento();
            String[] items = tipos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Tipo", "Todos", items);
            replaceInParent(historicoTipoCombo, combo);
            historicoTipoCombo = combo;
            historicoTipoCombo.setOnAction(e -> applyHistoricoFilters());
        }

        if (historicoEstadoCombo != null) {
            List<String> estados = facade.getEstadosSeguimiento();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(historicoEstadoCombo, combo);
            historicoEstadoCombo = combo;
            historicoEstadoCombo.setOnAction(e -> applyHistoricoFilters());
        }

        if (historicoSucursalCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", "Matriz Centro", "Norte Mall");
            replaceInParent(historicoSucursalCombo, combo);
            historicoSucursalCombo = combo;
            historicoSucursalCombo.setOnAction(e -> applyHistoricoFilters());
        }

        if (historicoDesdePicker != null) {
            historicoDesdePicker.valueProperty().addListener((obs, old, val) -> applyHistoricoFilters());
        }

        if (historicoHastaPicker != null) {
            historicoHastaPicker.valueProperty().addListener((obs, old, val) -> applyHistoricoFilters());
        }

        if (historicoSoloAbiertosCheck != null) {
            historicoSoloAbiertosCheck.selectedProperty().addListener((obs, old, val) -> applyHistoricoFilters());
        }

        if (btnBuscarHistorico != null) {
            btnBuscarHistorico.setOnAction(e -> applyHistoricoFilters());
        }

        if (btnLimpiarHistorico != null) {
            btnLimpiarHistorico.setOnAction(e -> clearHistoricoFilters());
        }

        if (btnAbrirCasoH != null) {
            btnAbrirCasoH.setOnAction(e -> abrirCasoH());
        }

        if (btnExportarHistoricoH != null) {
            btnExportarHistoricoH.setOnAction(e -> exportarHistoricoH());
        }
    }

    private void loadSubView6_Historico() {
        if (historicoTable == null) return;

        SeguimientoFilters histFilters = buildHistoricoFilters();
        List<SeguimientoRowModel.HistoricoRow> historico = facade.getHistorico(histFilters);
        ObservableList<SeguimientoRowModel.HistoricoRow> data = FXCollections.observableArrayList(historico);
        historicoTable.setItems(data);

        if (footerHistorico != null) {
            footerHistorico.setText("Total registros: " + data.size());
        }
    }

    private void applyHistoricoFilters() {
        loadSubView6_Historico();
    }

    private void clearHistoricoFilters() {
        if (historicoSearchField != null) historicoSearchField.clear();
        if (historicoTipoCombo != null) historicoTipoCombo.getSelectionModel().selectFirst();
        if (historicoEstadoCombo != null) historicoEstadoCombo.getSelectionModel().selectFirst();
        if (historicoSucursalCombo != null) historicoSucursalCombo.getSelectionModel().selectFirst();
        if (historicoDesdePicker != null) historicoDesdePicker.setValue(null);
        if (historicoHastaPicker != null) historicoHastaPicker.setValue(null);
        if (historicoSoloAbiertosCheck != null) historicoSoloAbiertosCheck.setSelected(false);
        loadSubView6_Historico();
    }

    private SeguimientoFilters buildHistoricoFilters() {
        return new SeguimientoFilters(
                historicoSearchField != null ? historicoSearchField.getText() : "",
                historicoTipoCombo != null && historicoTipoCombo.getValue() != null ? historicoTipoCombo.getValue() : "Todos",
                historicoEstadoCombo != null && historicoEstadoCombo.getValue() != null ? historicoEstadoCombo.getValue() : "Todos",
                "Todas",
                historicoSucursalCombo != null && historicoSucursalCombo.getValue() != null ? historicoSucursalCombo.getValue() : "Todas",
                "Todos",
                historicoDesdePicker != null && historicoDesdePicker.getValue() != null ? historicoDesdePicker.getValue().toString() : "",
                historicoHastaPicker != null && historicoHastaPicker.getValue() != null ? historicoHastaPicker.getValue().toString() : "",
                historicoSoloAbiertosCheck != null && historicoSoloAbiertosCheck.isSelected()
        );
    }

    private void abrirCasoH() { /* placeholder */ }
    private void exportarHistoricoH() { /* placeholder */ }

    // ------------------------------------------------------------------ Filters apply/clear

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Bandeja();
    }

    private void applyFilters() {
        currentFilters = new SeguimientoFilters(
                searchField != null ? searchField.getText() : "",
                tipoCombo != null && tipoCombo.getValue() != null ? tipoCombo.getValue() : "Todos",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                prioridadCombo != null && prioridadCombo.getValue() != null ? prioridadCombo.getValue() : "Todas",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                canalCombo != null && canalCombo.getValue() != null ? canalCombo.getValue() : "Todos",
                desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().toString() : "",
                hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().toString() : "",
                soloCasosAbiertosCheck != null && soloCasosAbiertosCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Bandeja();
    }

    private void clearFilters() {
        currentFilters = new SeguimientoFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (tipoCombo != null) tipoCombo.getSelectionModel().selectFirst();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (prioridadCombo != null) prioridadCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (canalCombo != null) canalCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloCasosAbiertosCheck != null) soloCasosAbiertosCheck.setSelected(false);

        loadSubView1_Bandeja();
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (nuevoSeguimientoBtn != null) {
            nuevoSeguimientoBtn.setOnAction(e -> nuevoSeguimiento());
        }
        if (actualizarSeguimientoBtn != null) {
            actualizarSeguimientoBtn.setOnAction(e -> loadCurrentSubView());
        }
        if (exportarSeguimientoBtn != null) {
            exportarSeguimientoBtn.setOnAction(e -> exportarSeguimiento());
        }
    }

    private void loadCurrentSubView() {
        if (btnBandeja != null && btnBandeja.isSelected()) loadSubView1_Bandeja();
        else if (btnRecall != null && btnRecall.isSelected()) loadSubView2_Recall();
        else if (btnNoRetirados != null && btnNoRetirados.isSelected()) loadSubView3_NoRetirados();
        else if (btnCobros != null && btnCobros.isSelected()) loadSubView4_Cobros();
        else if (btnMensajes != null && btnMensajes.isSelected()) loadSubView5_Mensajes();
        else if (btnHistorico != null && btnHistorico.isSelected()) loadSubView6_Historico();
    }

    private void nuevoSeguimiento() { /* placeholder */ }
    private void exportarSeguimiento() { /* placeholder */ }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnAbrirCaso != null) {
            summaryBtnAbrirCaso.setOnAction(e -> abrirCaso());
        }
        if (summaryBtnContactar != null) {
            summaryBtnContactar.setOnAction(e -> contactarCliente());
        }
        if (summaryBtnAgendar != null) {
            summaryBtnAgendar.setOnAction(e -> agendarAccion());
        }
        if (summaryBtnExportar != null) {
            summaryBtnExportar.setOnAction(e -> exportarCaso());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(SeguimientoSummaryModel.demoSeed());
    }

    private void updateSummaryPanel(SeguimientoSummaryModel summary) {
        if (summary == null) return;

        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryTipoSeguimiento != null) summaryTipoSeguimiento.setText(summary.tipoSeguimiento());
        if (summaryPrioridad != null) summaryPrioridad.setText(summary.prioridad());
        if (summaryCliente != null) summaryCliente.setText(summary.cliente());
        if (summaryCodigoCliente != null) summaryCodigoCliente.setText(summary.codigoCliente());
        if (summaryUltimaVisita != null) summaryUltimaVisita.setText(summary.ultimaVisita());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryEstadoActual != null) summaryEstadoActual.setText(summary.estadoActual());
        if (summaryCanalContacto != null) summaryCanalContacto.setText(summary.canalContacto());
        if (summaryUltimaInteraccion != null) summaryUltimaInteraccion.setText(summary.ultimaInteraccion());
        if (summaryAccionSugerida != null) summaryAccionSugerida.setText(summary.accionSugerida());
        if (summaryFechaObjetivo != null) summaryFechaObjetivo.setText(summary.fechaObjetivo());
        if (summaryObservacionClave != null) summaryObservacionClave.setText(summary.observacionClave());
    }

    private void abrirCaso() { /* placeholder */ }
    private void contactarCliente() { /* placeholder */ }
    private void agendarAccion() { /* placeholder */ }
    private void exportarCaso() { /* placeholder */ }

    // ------------------------------------------------------------------ Status badge helpers

    private StatusBadgeModel resolveBadge(String estado) {
        if (estado == null) return StatusBadgeModel.neutral("-");
        String lower = estado.toLowerCase();
        if (lower.contains("cerrado") || lower.contains("completado") || lower.contains("recibido") || lower.contains("leido")) {
            return StatusBadgeModel.success(estado);
        }
        if (lower.contains("pendiente") || lower.contains("vencido") || lower.contains("no retirado")) {
            return StatusBadgeModel.warning(estado);
        }
        if (lower.contains("reprogramado") || lower.contains("recall")) {
            return StatusBadgeModel.info(estado);
        }
        if (lower.contains("alta") || lower.contains("urgente") || lower.contains("vencido")) {
            return StatusBadgeModel.danger(estado);
        }
        return StatusBadgeModel.neutral(estado);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private <T> javafx.util.Callback<TableColumn<T, String>,
            javafx.scene.control.TableCell<T, String>>
    createGenericStatusBadgeCell() {
        return (javafx.util.Callback) createRawStatusBadgeCellFactory();
    }

    private javafx.util.Callback<?, ?> createRawStatusBadgeCellFactory() {
        return (javafx.util.Callback<TableColumn<?, String>, javafx.scene.control.TableCell<?, String>>) column ->
                new javafx.scene.control.TableCell<>() {
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
}

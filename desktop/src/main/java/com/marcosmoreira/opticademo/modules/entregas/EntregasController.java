package com.marcosmoreira.opticademo.modules.entregas;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controlador para el modulo de Entregas del sistema Optica.
 * <p>
 * Gestiona el proceso completo de entrega de trabajos terminados a los clientes.
 * El modulo se organiza en 6 sub-vistas intercambiables: Lista de trabajos listos
 * para entrega, Validacion previa a la entrega, Registro de retiro, Pendientes
 * de retiro con notificaciones, Ajustes y observaciones post-entrega, e Historico
 * de entregas completadas.
 * </p>
 * <p>
 * La lista de trabajos listos presenta una tabla paginada con filtros por estado,
 * sucursal, notificacion y fecha. La validacion previa verifica que el trabajo
 * recibido cumpla con los requisitos antes de la entrega. El registro de retiro
 * documenta quien retira el trabajo y si requiere seguimiento posterior.
 * </p>
 * <p>
 * El panel derecho muestra el resumen de entrega de la orden seleccionada, incluyendo
 * tipo de trabajo, fechas, estado de notificacion, saldo pendiente y estado de cobro.
 * Toda la logica de negocio se delega en {@link EntregasFacade}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see EntregasFacade
 * @see EntregasRowModel
 * @see EntregasFilters
 * @see EntregasSummaryModel
 */
public class EntregasController {

    // ---- Top bar ----
    @FXML
    private Button registrarRetiroRapidoBtn;

    @FXML
    private Button actualizarEntregasBtn;

    @FXML
    private Button exportarEntregasBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private ComboBox<String> notificacionCombo;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private CheckBox soloConSaldoCheck;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnTrabajosListos;

    @FXML
    private ToggleButton btnValidacionPrevia;

    @FXML
    private ToggleButton btnRegistroRetiro;

    @FXML
    private ToggleButton btnPendientesRetiro;

    @FXML
    private ToggleButton btnPostEntrega;

    @FXML
    private ToggleButton btnHistorico;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionTrabajosListos;

    @FXML
    private VBox sectionValidacionPrevia;

    @FXML
    private VBox sectionRegistroRetiro;

    @FXML
    private VBox sectionPendientesRetiro;

    @FXML
    private VBox sectionPostEntrega;

    @FXML
    private VBox sectionHistorico;

    // ---- Sub-view 1: Lista de trabajos listos ----
    @FXML
    private Label lblTrabajosListosCount;

    @FXML
    private TableView<EntregasRowModel.TrabajoListoRow> trabajosListosTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLReferencia;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLCliente;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLTipoTrabajo;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLFechaPromesa;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLEstado;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLSaldo;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLSucursal;

    @FXML
    private TableColumn<EntregasRowModel.TrabajoListoRow, String> colTLNotificacion;

    @FXML
    private Label footerTrabajosListos;

    // ---- Sub-view 2: Validacion previa a entrega ----
    @FXML
    private ComboBox<String> validacionRefCombo;

    @FXML
    private Label lblValidacionInfo;

    @FXML
    private CheckBox chkTrabajoRecibido;

    @FXML
    private CheckBox chkMontajeConforme;

    @FXML
    private CheckBox chkRequiereAjuste;

    @FXML
    private TextArea txtObservacionTecnica;

    @FXML
    private Label lblEstadoPago;

    @FXML
    private Label lblSaldoPendiente;

    @FXML
    private Button btnMarcarValidado;

    @FXML
    private Button btnRegistrarAjuste;

    @FXML
    private Button btnBloquearEntrega;

    // ---- Sub-view 3: Registro de retiro / entrega ----
    @FXML
    private Label lblRetiroReferencia;

    @FXML
    private Label lblRetiroCliente;

    @FXML
    private Label lblRetiroFechaHora;

    @FXML
    private ComboBox<String> retiraCombo;

    @FXML
    private TextField documentoField;

    @FXML
    private CheckBox chkEntregaConforme;

    @FXML
    private CheckBox chkRequiereSeguimiento;

    @FXML
    private CheckBox chkRetiradoCompletamente;

    @FXML
    private TextArea txtObservacionFinal;

    @FXML
    private Button btnConfirmarEntrega;

    @FXML
    private Button btnReprogramarRetiro;

    @FXML
    private Button btnCancelarRegistro;

    // ---- Sub-view 4: Pendientes de retiro ----
    @FXML
    private Label lblPendientesCount;

    @FXML
    private Label lblPendientesConSaldo;

    @FXML
    private Label lblPendientesNotificados;

    @FXML
    private TableView<EntregasRowModel.PendienteRow> pendientesTable;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendReferencia;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendCliente;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendDiasEsperando;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendSaldo;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendEstado;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendNotificacion;

    @FXML
    private TableColumn<EntregasRowModel.PendienteRow, String> colPendSucursal;

    @FXML
    private Button btnNotificarCliente;

    @FXML
    private Button btnRegistrarRetiro;

    @FXML
    private Button btnMarcarSeguimiento;

    // ---- Sub-view 5: Ajustes y observaciones post-entrega ----
    @FXML
    private TableView<EntregasRowModel.PostEntregaRow> postEntregaTable;

    @FXML
    private TableColumn<EntregasRowModel.PostEntregaRow, String> colPEFecha;

    @FXML
    private TableColumn<EntregasRowModel.PostEntregaRow, String> colPEReferencia;

    @FXML
    private TableColumn<EntregasRowModel.PostEntregaRow, String> colPETipo;

    @FXML
    private TableColumn<EntregasRowModel.PostEntregaRow, String> colPEEstado;

    @FXML
    private TableColumn<EntregasRowModel.PostEntregaRow, String> colPEResponsable;

    @FXML
    private VBox postEntregaDetailPanel;

    @FXML
    private Label lblPEDetail;

    @FXML
    private Button btnRegistrarAjustePost;

    @FXML
    private Button btnMarcarResuelto;

    @FXML
    private Button btnAgendarRevision;

    // ---- Sub-view 6: Historico ----
    @FXML
    private TextField historicoSearchField;

    @FXML
    private ComboBox<String> historicoEstadoCombo;

    @FXML
    private ComboBox<String> historicoSucursalCombo;

    @FXML
    private DatePicker historicoDesdePicker;

    @FXML
    private DatePicker historicoHastaPicker;

    @FXML
    private Button btnBuscarHistorico;

    @FXML
    private Button btnLimpiarHistorico;

    @FXML
    private TableView<EntregasRowModel.HistoricoRow> historicoTable;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistFecha;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistReferencia;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistCliente;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistEstadoFinal;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistSaldo;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistSucursal;

    @FXML
    private TableColumn<EntregasRowModel.HistoricoRow, String> colHistObservacion;

    @FXML
    private Button btnAbrirEntrega;

    @FXML
    private Button btnExportarHistorico;

    @FXML
    private Label footerHistorico;

    // ---- Right panel: Delivery summary ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipoTrabajo;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryCliente;

    @FXML
    private Label summaryCodigoCliente;

    @FXML
    private Label summaryFechaPromesa;

    @FXML
    private Label summaryFechaRecepcion;

    @FXML
    private Label summaryEstadoEntrega;

    @FXML
    private Label summaryNotificacion;

    @FXML
    private Label summaryPrioridad;

    @FXML
    private Label summarySaldoPendiente;

    @FXML
    private Label summaryEstadoCobro;

    @FXML
    private VBox summaryFieldsContainer;

    @FXML
    private Button summaryBtnAbrirDetalle;

    @FXML
    private Button summaryBtnValidarEntrega;

    @FXML
    private Button summaryBtnRegistrarRetiro;

    @FXML
    private Button summaryBtnNotificarCliente;

    @FXML
    private Button summaryBtnVerSaldo;

    // ---- Facade ----
    private EntregasFacade facade;

    private EntregasFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    /**
     * Inicializa el controlador y configura las 6 sub-vistas del modulo de entregas.
     * <p>
     * Instancia {@link EntregasFacade} con el {@link DemoStore} global, configura
     * los combos filtrables mediante {@link ComboBoxFactory}, establece el grupo
     * de toggles para las sub-vistas, inicializa todas las tablas con sus
     * {@code cellValueFactory} (incluyendo badges de estado para cada tipo de
     * registro), y configura el panel de resumen de entrega. Por defecto se muestra
     * la sub-vista de Trabajos listos.
     * </p>
     *
     * @see EntregasFacade
     * @see ComboBoxFactory
     * @see App#getDemoStore()
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new EntregasFacade(store);
        this.currentFilters = new EntregasFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_TrabajosListos();
        setupSubView2_ValidacionPrevia();
        setupSubView3_RegistroRetiro();
        setupSubView4_PendientesRetiro();
        setupSubView5_PostEntrega();
        setupSubView6_Historico();
        setupActionButtons();
        setupSummaryPanel();

        // Show first sub-view by default
        showSubView(0);
        if (btnTrabajosListos != null) btnTrabajosListos.setSelected(true);

        // Load initial data
        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstadosEntrega();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", "Matriz Centro", "Norte Mall");
            replaceInParent(sucursalCombo, combo);
            sucursalCombo = combo;
            sucursalCombo.setOnAction(e -> applyFilters());
        }

        if (notificacionCombo != null) {
            List<String> notificaciones = facade.getEstadosNotificacion();
            String[] items = notificaciones.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Notificacion", "Todas", items);
            replaceInParent(notificacionCombo, combo);
            notificacionCombo = combo;
            notificacionCombo.setOnAction(e -> applyFilters());
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

        if (soloConSaldoCheck != null) {
            soloConSaldoCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
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
        if (btnTrabajosListos != null) {
            btnTrabajosListos.setToggleGroup(group);
            btnTrabajosListos.setOnAction(e -> showSubView(0));
        }
        if (btnValidacionPrevia != null) {
            btnValidacionPrevia.setToggleGroup(group);
            btnValidacionPrevia.setOnAction(e -> showSubView(1));
        }
        if (btnRegistroRetiro != null) {
            btnRegistroRetiro.setToggleGroup(group);
            btnRegistroRetiro.setOnAction(e -> showSubView(2));
        }
        if (btnPendientesRetiro != null) {
            btnPendientesRetiro.setToggleGroup(group);
            btnPendientesRetiro.setOnAction(e -> showSubView(3));
        }
        if (btnPostEntrega != null) {
            btnPostEntrega.setToggleGroup(group);
            btnPostEntrega.setOnAction(e -> showSubView(4));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(group);
            btnHistorico.setOnAction(e -> showSubView(5));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionTrabajosListos,
                sectionValidacionPrevia,
                sectionRegistroRetiro,
                sectionPendientesRetiro,
                sectionPostEntrega,
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
            case 0 -> loadSubView1_TrabajosListos();
            case 1 -> loadSubView2_ValidacionPrevia();
            case 2 -> loadSubView3_RegistroRetiro();
            case 3 -> loadSubView4_PendientesRetiro();
            case 4 -> loadSubView5_PostEntrega();
            case 5 -> loadSubView6_Historico();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Lista de trabajos listos

    private void setupSubView1_TrabajosListos() {
        if (colTLReferencia != null) colTLReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colTLCliente != null) colTLCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colTLTipoTrabajo != null) colTLTipoTrabajo.setCellValueFactory(data -> data.getValue().tipoTrabajoProperty());
        if (colTLFechaPromesa != null) colTLFechaPromesa.setCellValueFactory(data -> data.getValue().fechaPromesaProperty());
        if (colTLEstado != null) colTLEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colTLSaldo != null) colTLSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colTLSucursal != null) colTLSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colTLNotificacion != null) colTLNotificacion.setCellValueFactory(data -> data.getValue().notificacionProperty());

        // Custom cell for estado column with status badge
        if (colTLEstado != null) {
            colTLEstado.setCellFactory(createTrabajosListosStatusBadgeCell());
        }

        // Selection listener for summary panel
        if (trabajosListosTable != null) {
            trabajosListosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onTrabajoListoSelected(newVal));
        }
    }

    private void loadSubView1_TrabajosListos() {
        if (trabajosListosTable == null) return;

        PageResult<EntregasRowModel.TrabajoListoRow> pageResult =
                facade.getTrabajosListos(currentFilters, currentPageRequest);
        ObservableList<EntregasRowModel.TrabajoListoRow> data =
                FXCollections.observableArrayList(pageResult.getItems());
        trabajosListosTable.setItems(data);

        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages(pageResult.getTotalPages());
            paginationBarController.setTotalItems(pageResult.getTotalItems());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        if (lblTrabajosListosCount != null) {
            lblTrabajosListosCount.setText(data.size() + " trabajos visibles");
        }

        if (footerTrabajosListos != null) {
            footerTrabajosListos.setText("Mostrando " + data.size() + " de " + pageResult.getTotalItems() + " trabajos");
        }

        // Select first row
        if (!data.isEmpty()) {
            trabajosListosTable.getSelectionModel().selectFirst();
        }
    }

    private void onTrabajoListoSelected(EntregasRowModel.TrabajoListoRow row) {
        if (row == null) return;

        DemoStore store = App.getDemoStore();
        VentaOptica venta = store.ventas.stream()
                .filter(v -> row.referencia().equals(v.getReferencia()))
                .findFirst()
                .orElse(null);

        EntregasSummaryModel summary = facade.buildSummary(venta);
        updateSummaryPanel(summary);
    }

    // ------------------------------------------------------------------ Sub-view 2: Validacion previa a entrega

    private void setupSubView2_ValidacionPrevia() {
        if (validacionRefCombo != null) {
            List<EntregasRowModel.ValidacionRow> validaciones = facade.getValidaciones();
            for (EntregasRowModel.ValidacionRow v : validaciones) {
                validacionRefCombo.getItems().add(v.referencia() + " - " + v.cliente());
            }
            if (!validaciones.isEmpty()) {
                validacionRefCombo.setValue(validaciones.get(0).referencia() + " - " + validaciones.get(0).cliente());
            }
            validacionRefCombo.setOnAction(e -> loadValidacionDetail());
        }

        if (txtObservacionTecnica != null) {
            txtObservacionTecnica.setPromptText("Observacion tecnica breve...");
        }

        if (btnMarcarValidado != null) {
            btnMarcarValidado.setOnAction(e -> marcarValidado());
        }
        if (btnRegistrarAjuste != null) {
            btnRegistrarAjuste.setOnAction(e -> registrarAjuste());
        }
        if (btnBloquearEntrega != null) {
            btnBloquearEntrega.setOnAction(e -> bloquearEntrega());
        }
    }

    private void loadSubView2_ValidacionPrevia() {
        if (lblValidacionInfo != null) {
            lblValidacionInfo.setText("Seleccione un trabajo para validar su entrega");
        }
        if (chkTrabajoRecibido != null) chkTrabajoRecibido.setSelected(false);
        if (chkMontajeConforme != null) chkMontajeConforme.setSelected(false);
        if (chkRequiereAjuste != null) chkRequiereAjuste.setSelected(false);
        if (txtObservacionTecnica != null) txtObservacionTecnica.clear();
        if (lblEstadoPago != null) lblEstadoPago.setText("-");
        if (lblSaldoPendiente != null) lblSaldoPendiente.setText("-");

        loadValidacionDetail();
    }

    private void loadValidacionDetail() {
        if (validacionRefCombo == null || validacionRefCombo.getValue() == null) return;

        String selected = validacionRefCombo.getValue();
        String ref = selected.split(" - ")[0];

        List<EntregasRowModel.ValidacionRow> validaciones = facade.getValidaciones();
        EntregasRowModel.ValidacionRow row = validaciones.stream()
                .filter(v -> v.referencia().equals(ref))
                .findFirst()
                .orElse(null);

        if (row != null) {
            if (lblValidacionInfo != null) {
                lblValidacionInfo.setText(ref + " | " + row.cliente() + " | " + row.tipoTrabajo());
            }
            if (chkTrabajoRecibido != null) chkTrabajoRecibido.setSelected("Si".equals(row.trabajoRecibido()));
            if (chkMontajeConforme != null) chkMontajeConforme.setSelected("Si".equals(row.montajeConforme()));
            if (chkRequiereAjuste != null) chkRequiereAjuste.setSelected("Si".equals(row.requiereAjuste()));
            if (lblEstadoPago != null) lblEstadoPago.setText("Pagado");
            if (lblSaldoPendiente != null) lblSaldoPendiente.setText(row.saldoPendiente());
        }
    }

    private void marcarValidado() {
        // Placeholder: mark as validated
    }

    private void registrarAjuste() {
        // Placeholder: register adjustment
    }

    private void bloquearEntrega() {
        // Placeholder: block delivery
    }

    // ------------------------------------------------------------------ Sub-view 3: Registro de retiro / entrega

    private void setupSubView3_RegistroRetiro() {
        if (retiraCombo != null) {
            retiraCombo.getItems().addAll("Titular", "Tercero");
            retiraCombo.setValue("Titular");
        }

        if (documentoField != null) {
            documentoField.setPromptText("Documento de referencia...");
        }

        if (txtObservacionFinal != null) {
            txtObservacionFinal.setPromptText("Observacion final sobre la entrega...");
        }

        if (btnConfirmarEntrega != null) {
            btnConfirmarEntrega.setOnAction(e -> confirmarEntrega());
        }
        if (btnReprogramarRetiro != null) {
            btnReprogramarRetiro.setOnAction(e -> reprogramarRetiro());
        }
        if (btnCancelarRegistro != null) {
            btnCancelarRegistro.setOnAction(e -> cancelarRegistro());
        }
    }

    private void loadSubView3_RegistroRetiro() {
        if (lblRetiroReferencia != null) lblRetiroReferencia.setText("-");
        if (lblRetiroCliente != null) lblRetiroCliente.setText("-");
        if (lblRetiroFechaHora != null) lblRetiroFechaHora.setText("-");
        if (retiraCombo != null) retiraCombo.setValue("Titular");
        if (documentoField != null) documentoField.clear();
        if (chkEntregaConforme != null) chkEntregaConforme.setSelected(false);
        if (chkRequiereSeguimiento != null) chkRequiereSeguimiento.setSelected(false);
        if (chkRetiradoCompletamente != null) chkRetiradoCompletamente.setSelected(false);
        if (txtObservacionFinal != null) txtObservacionFinal.clear();

        // Pre-fill with demo seed data
        if (lblRetiroReferencia != null) lblRetiroReferencia.setText("ET-041");
        if (lblRetiroCliente != null) lblRetiroCliente.setText("Sofia Ramirez");
        if (lblRetiroFechaHora != null) lblRetiroFechaHora.setText("16/04/2026 16:30");
    }

    private void confirmarEntrega() {
        // Placeholder: confirm delivery
    }

    private void reprogramarRetiro() {
        // Placeholder: reschedule pickup
    }

    private void cancelarRegistro() {
        // Placeholder: cancel registration
    }

    // ------------------------------------------------------------------ Sub-view 4: Pendientes de retiro

    private void setupSubView4_PendientesRetiro() {
        if (colPendReferencia != null) colPendReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colPendCliente != null) colPendCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colPendDiasEsperando != null) colPendDiasEsperando.setCellValueFactory(data -> data.getValue().diasEsperandoProperty());
        if (colPendSaldo != null) colPendSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colPendEstado != null) colPendEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colPendNotificacion != null) colPendNotificacion.setCellValueFactory(data -> data.getValue().notificacionProperty());
        if (colPendSucursal != null) colPendSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell for estado column
        if (colPendEstado != null) {
            colPendEstado.setCellFactory(createPendientesStatusBadgeCell());
        }

        // Selection listener for summary panel
        if (pendientesTable != null) {
            pendientesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onPendienteSelected(newVal));
        }

        if (btnNotificarCliente != null) {
            btnNotificarCliente.setOnAction(e -> notificarCliente());
        }
        if (btnRegistrarRetiro != null) {
            btnRegistrarRetiro.setOnAction(e -> onRegistrarRetiroPendiente());
        }
        if (btnMarcarSeguimiento != null) {
            btnMarcarSeguimiento.setOnAction(e -> marcarSeguimiento());
        }
    }

    private void loadSubView4_PendientesRetiro() {
        if (pendientesTable == null) return;

        List<EntregasRowModel.PendienteRow> pendientes = facade.getPendientesRetiro();
        ObservableList<EntregasRowModel.PendienteRow> data = FXCollections.observableArrayList(pendientes);
        pendientesTable.setItems(data);

        // Update stats
        EntregasFacade.StatsPendientes stats = facade.getStatsPendientes();
        if (lblPendientesCount != null) lblPendientesCount.setText(String.valueOf(stats.pendientes()));
        if (lblPendientesConSaldo != null) lblPendientesConSaldo.setText(String.valueOf(stats.conSaldo()));
        if (lblPendientesNotificados != null) lblPendientesNotificados.setText(String.valueOf(stats.notificados()));

        // Select first row
        if (!pendientes.isEmpty()) {
            pendientesTable.getSelectionModel().selectFirst();
        }
    }

    private void onPendienteSelected(EntregasRowModel.PendienteRow row) {
        if (row == null) return;

        DemoStore store = App.getDemoStore();
        VentaOptica venta = store.ventas.stream()
                .filter(v -> row.referencia().equals(v.getReferencia()))
                .findFirst()
                .orElse(null);

        EntregasSummaryModel summary = facade.buildSummary(venta);
        updateSummaryPanel(summary);
    }

    private void notificarCliente() {
        // Placeholder
    }

    private void onRegistrarRetiroPendiente() {
        // Switch to sub-view 3
        if (btnRegistroRetiro != null) btnRegistroRetiro.setSelected(true);
        showSubView(2);
    }

    private void marcarSeguimiento() {
        // Placeholder
    }

    // ------------------------------------------------------------------ Sub-view 5: Ajustes y observaciones post-entrega

    private void setupSubView5_PostEntrega() {
        if (colPEFecha != null) colPEFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colPEReferencia != null) colPEReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colPETipo != null) colPETipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colPEEstado != null) colPEEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colPEResponsable != null) colPEResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());

        // Custom cell for estado column
        if (colPEEstado != null) {
            colPEEstado.setCellFactory(createPostEntregaStatusBadgeCell());
        }

        // Selection listener for detail panel
        if (postEntregaTable != null) {
            postEntregaTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onPostEntregaSelected(newVal));
        }

        if (btnRegistrarAjustePost != null) {
            btnRegistrarAjustePost.setOnAction(e -> registrarAjustePostEntrega());
        }
        if (btnMarcarResuelto != null) {
            btnMarcarResuelto.setOnAction(e -> marcarResuelto());
        }
        if (btnAgendarRevision != null) {
            btnAgendarRevision.setOnAction(e -> agendarRevision());
        }
    }

    private void loadSubView5_PostEntrega() {
        if (postEntregaTable == null) return;

        List<EntregasRowModel.PostEntregaRow> postEntrega = facade.getPostEntrega();
        ObservableList<EntregasRowModel.PostEntregaRow> data =
                FXCollections.observableArrayList(postEntrega);
        postEntregaTable.setItems(data);

        if (lblPEDetail != null) {
            lblPEDetail.setText("Seleccione un registro para ver el detalle");
        }

        // Select first row
        if (!postEntrega.isEmpty()) {
            postEntregaTable.getSelectionModel().selectFirst();
        }
    }

    private void onPostEntregaSelected(EntregasRowModel.PostEntregaRow row) {
        if (row == null || lblPEDetail == null) return;

        lblPEDetail.setText(row.fecha() + " | " + row.referencia() + " | " +
                row.tipo() + " | " + row.estado() + " | " + row.responsable());
    }

    private void registrarAjustePostEntrega() {
        // Placeholder
    }

    private void marcarResuelto() {
        // Placeholder
    }

    private void agendarRevision() {
        // Placeholder
    }

    // ------------------------------------------------------------------ Sub-view 6: Historico

    private void setupSubView6_Historico() {
        if (colHistFecha != null) colHistFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHistReferencia != null) colHistReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHistCliente != null) colHistCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colHistEstadoFinal != null) colHistEstadoFinal.setCellValueFactory(data -> data.getValue().estadoFinalProperty());
        if (colHistSaldo != null) colHistSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colHistSucursal != null) colHistSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colHistObservacion != null) colHistObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        // Custom cell for estado column
        if (colHistEstadoFinal != null) {
            colHistEstadoFinal.setCellFactory(createHistoricoStatusBadgeCell());
        }

        if (historicoSearchField != null) {
            historicoSearchField.setPromptText("Cliente, orden o referencia");
        }

        if (historicoEstadoCombo != null) {
            List<String> estados = facade.getEstadosEntrega();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(historicoEstadoCombo, combo);
            historicoEstadoCombo = combo;
        }

        if (historicoSucursalCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", "Matriz Centro", "Norte Mall");
            replaceInParent(historicoSucursalCombo, combo);
            historicoSucursalCombo = combo;
        }

        if (btnBuscarHistorico != null) {
            btnBuscarHistorico.setOnAction(e -> buscarHistorico());
        }
        if (btnLimpiarHistorico != null) {
            btnLimpiarHistorico.setOnAction(e -> limpiarHistorico());
        }
        if (btnAbrirEntrega != null) {
            btnAbrirEntrega.setOnAction(e -> abrirEntrega());
        }
        if (btnExportarHistorico != null) {
            btnExportarHistorico.setOnAction(e -> exportarHistorico());
        }
    }

    private void loadSubView6_Historico() {
        if (historicoTable == null) return;

        EntregasFilters histFilters = buildHistoricoFilters();
        List<EntregasRowModel.HistoricoRow> historico = facade.getHistorico(histFilters);
        ObservableList<EntregasRowModel.HistoricoRow> data = FXCollections.observableArrayList(historico);
        historicoTable.setItems(data);

        if (footerHistorico != null) {
            footerHistorico.setText("Total registros: " + historico.size());
        }
    }

    private EntregasFilters buildHistoricoFilters() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new EntregasFilters(
                historicoSearchField != null ? historicoSearchField.getText() : "",
                historicoEstadoCombo != null && historicoEstadoCombo.getValue() != null ? historicoEstadoCombo.getValue() : "Todos",
                historicoSucursalCombo != null && historicoSucursalCombo.getValue() != null ? historicoSucursalCombo.getValue() : "Todas",
                "Todas",
                historicoDesdePicker != null && historicoDesdePicker.getValue() != null ? historicoDesdePicker.getValue().format(fmt) : "",
                historicoHastaPicker != null && historicoHastaPicker.getValue() != null ? historicoHastaPicker.getValue().format(fmt) : "",
                false
        );
    }

    private void buscarHistorico() {
        loadSubView6_Historico();
    }

    private void limpiarHistorico() {
        if (historicoSearchField != null) historicoSearchField.clear();
        if (historicoEstadoCombo != null) historicoEstadoCombo.getSelectionModel().selectFirst();
        if (historicoSucursalCombo != null) historicoSucursalCombo.getSelectionModel().selectFirst();
        if (historicoDesdePicker != null) historicoDesdePicker.setValue(null);
        if (historicoHastaPicker != null) historicoHastaPicker.setValue(null);
        loadSubView6_Historico();
    }

    private void abrirEntrega() {
        // Placeholder
    }

    private void exportarHistorico() {
        // Placeholder: export to CSV
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (registrarRetiroRapidoBtn != null) {
            registrarRetiroRapidoBtn.setOnAction(e -> onRegistrarRetiroRapido());
        }
        if (actualizarEntregasBtn != null) {
            actualizarEntregasBtn.setOnAction(e -> onActualizarEntregas());
        }
        if (exportarEntregasBtn != null) {
            exportarEntregasBtn.setOnAction(e -> onExportarEntregas());
        }
    }

    private void onRegistrarRetiroRapido() {
        if (btnRegistroRetiro != null) btnRegistroRetiro.setSelected(true);
        showSubView(2);
    }

    /**
     * Maneja la accion de actualizar todos los datos del modulo de entregas.
     * Reaplica los filtros actuales y recarga el panel de resumen.
     */
    private void onActualizarEntregas() {
        applyFilters();
        loadSummaryPanel();
    }

    /** Maneja la exportacion de entregas a CSV. Metodo placeholder. */
    private void onExportarEntregas() {
        // Placeholder: export to CSV
    }

    // ------------------------------------------------------------------ Filters apply/clear

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private void applyFilters() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        currentFilters = new EntregasFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                notificacionCombo != null && notificacionCombo.getValue() != null ? notificacionCombo.getValue() : "Todas",
                desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().format(fmt) : "",
                hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().format(fmt) : "",
                soloConSaldoCheck != null && soloConSaldoCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        // Reload current sub-view data
        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private void clearFilters() {
        currentFilters = new EntregasFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (notificacionCombo != null) notificacionCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloConSaldoCheck != null) soloConSaldoCheck.setSelected(false);

        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private int getCurrentSubViewIndex() {
        if (btnTrabajosListos != null && btnTrabajosListos.isSelected()) return 0;
        if (btnValidacionPrevia != null && btnValidacionPrevia.isSelected()) return 1;
        if (btnRegistroRetiro != null && btnRegistroRetiro.isSelected()) return 2;
        if (btnPendientesRetiro != null && btnPendientesRetiro.isSelected()) return 3;
        if (btnPostEntrega != null && btnPostEntrega.isSelected()) return 4;
        if (btnHistorico != null && btnHistorico.isSelected()) return 5;
        return 0;
    }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnAbrirDetalle != null) {
            summaryBtnAbrirDetalle.setOnAction(e -> abrirDetalle());
        }
        if (summaryBtnValidarEntrega != null) {
            summaryBtnValidarEntrega.setOnAction(e -> validarEntregaRapida());
        }
        if (summaryBtnRegistrarRetiro != null) {
            summaryBtnRegistrarRetiro.setOnAction(e -> onRegistrarRetiroRapido());
        }
        if (summaryBtnNotificarCliente != null) {
            summaryBtnNotificarCliente.setOnAction(e -> notificarCliente());
        }
        if (summaryBtnVerSaldo != null) {
            summaryBtnVerSaldo.setOnAction(e -> verSaldo());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(EntregasSummaryModel.demoSeed());
    }

    private void updateSummaryPanel(EntregasSummaryModel summary) {
        if (summary == null) return;

        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryTipoTrabajo != null) summaryTipoTrabajo.setText(summary.tipoTrabajo());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryCliente != null) summaryCliente.setText(summary.cliente());
        if (summaryCodigoCliente != null) summaryCodigoCliente.setText(summary.codigoCliente());
        if (summaryFechaPromesa != null) summaryFechaPromesa.setText(summary.fechaPromesa());
        if (summaryFechaRecepcion != null) summaryFechaRecepcion.setText(summary.fechaRecepcion());
        if (summaryEstadoEntrega != null) summaryEstadoEntrega.setText(summary.estadoEntrega());
        if (summaryNotificacion != null) summaryNotificacion.setText(summary.notificacion());
        if (summaryPrioridad != null) summaryPrioridad.setText(summary.prioridad());
        if (summarySaldoPendiente != null) summarySaldoPendiente.setText(summary.saldoPendiente());
        if (summaryEstadoCobro != null) summaryEstadoCobro.setText(summary.estadoCobro());

        // Dynamic fields container
        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();

            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Referencia", summary.referencia()),
                    new SummaryFieldModel("Tipo de trabajo", summary.tipoTrabajo()),
                    new SummaryFieldModel("Sucursal", summary.sucursal()),
                    new SummaryFieldModel("Cliente", summary.cliente()),
                    new SummaryFieldModel("Cod. Cliente", summary.codigoCliente()),
                    new SummaryFieldModel("Fecha promesa", summary.fechaPromesa()),
                    new SummaryFieldModel("Fecha recepcion", summary.fechaRecepcion()),
                    new SummaryFieldModel("Estado entrega", summary.estadoEntrega()),
                    new SummaryFieldModel("Notificacion", summary.notificacion()),
                    new SummaryFieldModel("Prioridad", summary.prioridad()),
                    new SummaryFieldModel("Saldo pendiente", summary.saldoPendiente(), true),
                    new SummaryFieldModel("Estado de cobro", summary.estadoCobro())
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

    private void abrirDetalle() {
        // Placeholder
    }

    private void validarEntregaRapida() {
        if (btnValidacionPrevia != null) btnValidacionPrevia.setSelected(true);
        showSubView(1);
    }

    private void verSaldo() {
        // Placeholder: show balance detail
    }

    // ------------------------------------------------------------------ Cell factories

    private javafx.util.Callback<TableColumn<EntregasRowModel.TrabajoListoRow, String>,
            javafx.scene.control.TableCell<EntregasRowModel.TrabajoListoRow, String>>
    createTrabajosListosStatusBadgeCell() {
        return tc -> new javafx.scene.control.TableCell<>() {
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
                        javafx.scene.layout.VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "lista para entrega", "entregada", "notificado" -> StatusBadgeModel.success(item);
                            case "pendiente de validacion", "pendiente de retiro" -> StatusBadgeModel.warning(item);
                            case "con saldo pendiente", "con ajuste pendiente", "no retirada" -> StatusBadgeModel.danger(item);
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
        };
    }

    private javafx.util.Callback<TableColumn<EntregasRowModel.PendienteRow, String>,
            javafx.scene.control.TableCell<EntregasRowModel.PendienteRow, String>>
    createPendientesStatusBadgeCell() {
        return tc -> new javafx.scene.control.TableCell<>() {
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
                        javafx.scene.layout.VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "pendiente de retiro" -> StatusBadgeModel.warning(item);
                            case "con saldo pendiente" -> StatusBadgeModel.danger(item);
                            case "no retirada" -> StatusBadgeModel.danger(item);
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
        };
    }

    private javafx.util.Callback<TableColumn<EntregasRowModel.PostEntregaRow, String>,
            javafx.scene.control.TableCell<EntregasRowModel.PostEntregaRow, String>>
    createPostEntregaStatusBadgeCell() {
        return tc -> new javafx.scene.control.TableCell<>() {
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
                        javafx.scene.layout.VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "cerrado", "resuelto" -> StatusBadgeModel.success(item);
                            case "pendiente", "en proceso" -> StatusBadgeModel.warning(item);
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
        };
    }

    private javafx.util.Callback<TableColumn<EntregasRowModel.HistoricoRow, String>,
            javafx.scene.control.TableCell<EntregasRowModel.HistoricoRow, String>>
    createHistoricoStatusBadgeCell() {
        return tc -> new javafx.scene.control.TableCell<>() {
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
                        javafx.scene.layout.VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "entregada", "cerrado" -> StatusBadgeModel.success(item);
                            case "reprogramada", "pendiente" -> StatusBadgeModel.warning(item);
                            case "no retirada" -> StatusBadgeModel.danger(item);
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
        };
    }
}

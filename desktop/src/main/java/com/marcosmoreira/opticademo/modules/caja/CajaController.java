package com.marcosmoreira.opticademo.modules.caja;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.common.EstadoGeneral;
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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Controlador para el modulo de Caja del sistema Optica.
 * <p>
 * Gestiona todas las operaciones de cobro, pagos y cierre de caja del sistema.
 * El modulo se organiza en 6 sub-vistas intercambiables: Cobro de orden,
 * Abonos y saldos pendientes, Comprobantes de pago, Cierre de caja diario,
 * Pagos pendientes con recordatorios e Historico de transacciones.
 * </p>
 * <p>
 * La sub-vista de Cobro de orden permite buscar una orden por referencia o cliente
 * y registrar el pago completo. Abonos y saldos muestra una tabla paginada de
 * ordenes con saldo pendiente y permite registrar pagos parciales. El cierre de
 * caja presenta un resumen diario agrupado por metodo de pago.
 * </p>
 * <p>
 * El panel derecho muestra el resumen de pago de la orden seleccionada, incluyendo
 * subtotal, descuento, abono acumulado, saldo y estado de cobro. Toda la logica
 * de negocio se delega en {@link CajaFacade}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see CajaFacade
 * @see CajaRowModel
 * @see CajaFilters
 * @see CajaSummaryModel
 */
public class CajaController {

    // ---- Top bar ----
    @FXML
    private Button nuevoCobroBtn;

    @FXML
    private Button actualizarCajaBtn;

    @FXML
    private Button exportarCobrosBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> metodoPagoCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private CheckBox soloPendientesCheck;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnCobroOrden;

    @FXML
    private ToggleButton btnAbonosSaldos;

    @FXML
    private ToggleButton btnComprobantes;

    @FXML
    private ToggleButton btnCierreCaja;

    @FXML
    private ToggleButton btnPagosPendientes;

    @FXML
    private ToggleButton btnHistorico;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionCobroOrden;

    @FXML
    private VBox sectionAbonosSaldos;

    @FXML
    private VBox sectionComprobantes;

    @FXML
    private VBox sectionCierreCaja;

    @FXML
    private VBox sectionPagosPendientes;

    @FXML
    private VBox sectionHistorico;

    // ---- Sub-view 1: Cobro de orden ----
    @FXML
    private TextField ordenSearchField;

    @FXML
    private Label lblOrdenInfo;

    @FXML
    private VBox bloqueconomico;

    @FXML
    private ComboBox<String> metodoPagoCobroCombo;

    @FXML
    private Button registrarCobroBtn;

    // ---- Sub-view 2: Abonos y saldos ----
    @FXML
    private TableView<CajaRowModel.SaldoRow> saldosTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoOrden;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoCliente;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoTotal;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoAbonado;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoSaldo;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoUltimoPago;

    @FXML
    private TableColumn<CajaRowModel.SaldoRow, String> colSaldoEstado;

    @FXML
    private VBox nuevoAbonoForm;

    @FXML
    private TextField abonoOrdenField;

    @FXML
    private TextField abonoMontoField;

    @FXML
    private ComboBox<String> abonoMetodoCombo;

    @FXML
    private Button registrarAbonoBtn;

    // ---- Sub-view 3: Comprobantes ----
    @FXML
    private TableView<CajaRowModel.ComprobanteRow> comprobantesTable;

    @FXML
    private TableColumn<CajaRowModel.ComprobanteRow, String> colCompNumero;

    @FXML
    private TableColumn<CajaRowModel.ComprobanteRow, String> colCompFecha;

    @FXML
    private TableColumn<CajaRowModel.ComprobanteRow, String> colCompCliente;

    @FXML
    private TableColumn<CajaRowModel.ComprobanteRow, String> colCompOrden;

    @FXML
    private TableColumn<CajaRowModel.ComprobanteRow, String> colCompTotal;

    @FXML
    private TableColumn<CajaRowModel.ComprobanteRow, String> colCompEstado;

    @FXML
    private VBox compDetailPanel;

    @FXML
    private Label lblCompDetail;

    // ---- Sub-view 4: Cierre de caja ----
    @FXML
    private VBox cierreStatsContainer;

    @FXML
    private Label lblCierreFecha;

    @FXML
    private Label lblCierreSucursal;

    @FXML
    private Label lblCierreTotalCobros;

    @FXML
    private Label lblCierreTotalDia;

    @FXML
    private VBox cierrePorMetodoContainer;

    @FXML
    private Button confirmarCierreBtn;

    // ---- Sub-view 5: Pagos pendientes ----
    @FXML
    private TableView<CajaRowModel.PendienteRow> pendientesTable;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendOrden;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendCliente;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendSaldo;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendUltimoPago;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendEstado;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendSucursal;

    @FXML
    private TableColumn<CajaRowModel.PendienteRow, String> colPendPrioridad;

    @FXML
    private Button contactarClienteBtn;

    @FXML
    private Button enviarRecordatorioBtn;

    // ---- Sub-view 6: Historico ----
    @FXML
    private TableView<CajaRowModel.CobroRow> historicoTable;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistFecha;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistOrden;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistCliente;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistMonto;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistMetodo;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistEstado;

    @FXML
    private TableColumn<CajaRowModel.CobroRow, String> colHistComprobante;

    @FXML
    private Label footerLabel;

    // ---- Right panel: Payment summary ----
    @FXML
    private Label summaryOrden;

    @FXML
    private Label summaryCliente;

    @FXML
    private Label summaryCodigoCliente;

    @FXML
    private Label summaryFechaPromesa;

    @FXML
    private VBox summaryFieldsContainer;

    @FXML
    private Button summaryBtnCobrar;

    @FXML
    private Button summaryBtnImprimir;

    @FXML
    private Button summaryBtnEnviar;

    // ---- Facade ----
    private CajaFacade facade;

    private CajaFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    /**
     * Inicializa el controlador y configura las 6 sub-vistas del modulo de caja.
     * <p>
     * Instancia {@link CajaFacade} con el {@link DemoStore} global, configura
     * los combos filtrables mediante {@link ComboBoxFactory}, establece el grupo
     * de toggles para las sub-vistas, inicializa todas las tablas con sus
     * {@code cellValueFactory} (incluyendo badges de estado para cada tipo de
     * registro), y configura el panel de resumen de pago. Por defecto se muestra
     * la sub-vista de Cobro de orden.
     * </p>
     *
     * @see CajaFacade
     * @see ComboBoxFactory
     * @see App#getDemoStore()
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new CajaFacade(store);
        this.currentFilters = new CajaFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_CobroOrden();
        setupSubView2_AbonosSaldos();
        setupSubView3_Comprobantes();
        setupSubView4_CierreCaja();
        setupSubView5_PagosPendientes();
        setupSubView6_Historico();
        setupActionButtons();
        setupSummaryPanel();

        // Show first sub-view by default
        showSubView(0);
        if (btnCobroOrden != null) btnCobroOrden.setSelected(true);

        // Load initial data
        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstadosCobro();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (metodoPagoCombo != null) {
            List<String> metodos = facade.getMetodosPago();
            String[] items = metodos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Metodo de pago", "Todos", items);
            replaceInParent(metodoPagoCombo, combo);
            metodoPagoCombo = combo;
            metodoPagoCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", "Matriz Centro", "Norte Mall");
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

        if (soloPendientesCheck != null) {
            soloPendientesCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
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
        if (btnCobroOrden != null) {
            btnCobroOrden.setToggleGroup(group);
            btnCobroOrden.setOnAction(e -> showSubView(0));
        }
        if (btnAbonosSaldos != null) {
            btnAbonosSaldos.setToggleGroup(group);
            btnAbonosSaldos.setOnAction(e -> showSubView(1));
        }
        if (btnComprobantes != null) {
            btnComprobantes.setToggleGroup(group);
            btnComprobantes.setOnAction(e -> showSubView(2));
        }
        if (btnCierreCaja != null) {
            btnCierreCaja.setToggleGroup(group);
            btnCierreCaja.setOnAction(e -> showSubView(3));
        }
        if (btnPagosPendientes != null) {
            btnPagosPendientes.setToggleGroup(group);
            btnPagosPendientes.setOnAction(e -> showSubView(4));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(group);
            btnHistorico.setOnAction(e -> showSubView(5));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionCobroOrden,
                sectionAbonosSaldos,
                sectionComprobantes,
                sectionCierreCaja,
                sectionPagosPendientes,
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
            case 0 -> loadSubView1_CobroOrden();
            case 1 -> loadSubView2_AbonosSaldos();
            case 2 -> loadSubView3_Comprobantes();
            case 3 -> loadSubView4_CierreCaja();
            case 4 -> loadSubView5_PagosPendientes();
            case 5 -> loadSubView6_Historico();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Cobro de orden

    private void setupSubView1_CobroOrden() {
        if (metodoPagoCobroCombo != null) {
            metodoPagoCobroCombo.getItems().addAll("EFECTIVO", "TARJETA_CREDITO", "TARJETA_DEBITO", "TRANSFERENCIA", "CHEQUE");
            metodoPagoCobroCombo.setValue("EFECTIVO");
        }

        if (ordenSearchField != null) {
            ordenSearchField.setPromptText("Buscar orden por referencia o cliente...");
            ordenSearchField.setOnAction(e -> buscarOrden());
        }

        if (registrarCobroBtn != null) {
            registrarCobroBtn.setOnAction(e -> registrarCobro());
        }
    }

    private void loadSubView1_CobroOrden() {
        // Reset search field
        if (ordenSearchField != null) {
            ordenSearchField.clear();
        }
        if (lblOrdenInfo != null) {
            lblOrdenInfo.setText("Busque una orden para cobrar");
        }
    }

    private void buscarOrden() {
        if (ordenSearchField == null) return;
        String query = ordenSearchField.getText();
        if (query == null || query.isBlank()) return;

        VentaOptica venta = facade.findVentaByReferencia(query);
        if (venta == null) {
            venta = facade.findVentaByCliente(query);
        }

        if (venta != null) {
            if (lblOrdenInfo != null) {
                lblOrdenInfo.setText("Orden: " + venta.getReferencia() + " - " + venta.getClienteNombre());
            }
            CajaSummaryModel summary = facade.buildSummary(venta);
            updateSummaryPanel(summary);
        } else {
            if (lblOrdenInfo != null) {
                lblOrdenInfo.setText("No se encontro la orden: " + query);
            }
        }
    }

    private void registrarCobro() {
        // Placeholder: register payment
    }

    // ------------------------------------------------------------------ Sub-view 2: Abonos y saldos

    private void setupSubView2_AbonosSaldos() {
        if (colSaldoOrden != null) colSaldoOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colSaldoCliente != null) colSaldoCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colSaldoTotal != null) colSaldoTotal.setCellValueFactory(data -> data.getValue().totalProperty());
        if (colSaldoAbonado != null) colSaldoAbonado.setCellValueFactory(data -> data.getValue().abonadoProperty());
        if (colSaldoSaldo != null) colSaldoSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colSaldoUltimoPago != null) colSaldoUltimoPago.setCellValueFactory(data -> data.getValue().ultimoPagoProperty());
        if (colSaldoEstado != null) colSaldoEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        // Custom cell for estado column
        if (colSaldoEstado != null) {
            colSaldoEstado.setCellFactory(createSaldoStatusBadgeCell());
        }

        // Selection listener for summary panel
        if (saldosTable != null) {
            saldosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onSaldoSelected(newVal));
        }

        if (abonoMetodoCombo != null) {
            abonoMetodoCombo.getItems().addAll("EFECTIVO", "TARJETA_CREDITO", "TARJETA_DEBITO", "TRANSFERENCIA", "CHEQUE");
            abonoMetodoCombo.setValue("EFECTIVO");
        }

        if (registrarAbonoBtn != null) {
            registrarAbonoBtn.setOnAction(e -> registrarAbono());
        }
    }

    private void loadSubView2_AbonosSaldos() {
        if (saldosTable == null) return;
        List<CajaRowModel.SaldoRow> saldos = facade.getSaldoPendiente();
        ObservableList<CajaRowModel.SaldoRow> data = FXCollections.observableArrayList(saldos);
        saldosTable.setItems(data);

        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages((int) Math.ceil((double) saldos.size() / pageSize));
            paginationBarController.setTotalItems(saldos.size());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        // Select first row
        if (!saldos.isEmpty()) {
            saldosTable.getSelectionModel().selectFirst();
        }
    }

    private void onSaldoSelected(CajaRowModel.SaldoRow row) {
        if (row == null) return;

        VentaOptica venta = App.getDemoStore().ventas.stream()
                .filter(v -> row.orden().equals(v.getReferencia()))
                .findFirst()
                .orElse(null);

        if (venta != null) {
            CajaSummaryModel summary = facade.buildSummary(venta);
            updateSummaryPanel(summary);
        }
    }

    private void registrarAbono() {
        // Placeholder: register partial payment
    }

    // ------------------------------------------------------------------ Sub-view 3: Comprobantes

    private void setupSubView3_Comprobantes() {
        if (colCompNumero != null) colCompNumero.setCellValueFactory(data -> data.getValue().comprobanteProperty());
        if (colCompFecha != null) colCompFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colCompCliente != null) colCompCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colCompOrden != null) colCompOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colCompTotal != null) colCompTotal.setCellValueFactory(data -> data.getValue().totalProperty());
        if (colCompEstado != null) colCompEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colCompEstado != null) {
            colCompEstado.setCellFactory(createCompStatusBadgeCell());
        }

        if (comprobantesTable != null) {
            comprobantesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onComprobanteSelected(newVal));
        }
    }

    private void loadSubView3_Comprobantes() {
        if (comprobantesTable == null) return;
        List<CajaRowModel.ComprobanteRow> comps = facade.getComprobantes();
        ObservableList<CajaRowModel.ComprobanteRow> data = FXCollections.observableArrayList(comps);
        comprobantesTable.setItems(data);

        if (!comps.isEmpty()) {
            comprobantesTable.getSelectionModel().selectFirst();
        }
    }

    private void onComprobanteSelected(CajaRowModel.ComprobanteRow row) {
        if (lblCompDetail == null) return;
        if (row == null) {
            lblCompDetail.setText("Sin comprobante seleccionado");
            return;
        }
        lblCompDetail.setText(row.comprobante() + " | " + row.fecha() + " | " + row.cliente() +
                " | Orden: " + row.orden() + " | Total: " + row.total() + " | " + row.estado());
    }

    // ------------------------------------------------------------------ Sub-view 4: Cierre de caja

    private void setupSubView4_CierreCaja() {
        if (confirmarCierreBtn != null) {
            confirmarCierreBtn.setOnAction(e -> confirmarCierre());
        }
    }

    private void loadSubView4_CierreCaja() {
        if (cierreStatsContainer == null) return;

        CajaFacade.CierreDiaSummary summary = facade.getCierreDia();

        if (lblCierreFecha != null) lblCierreFecha.setText(summary.fecha());
        if (lblCierreSucursal != null) lblCierreSucursal.setText(summary.sucursal());
        if (lblCierreTotalCobros != null) lblCierreTotalCobros.setText(String.valueOf(summary.totalCobros()));
        if (lblCierreTotalDia != null) lblCierreTotalDia.setText(String.format("$%.2f", summary.totalDia()));

        // Build por-metodo breakdown
        if (cierrePorMetodoContainer != null) {
            cierrePorMetodoContainer.getChildren().clear();
            for (Map.Entry<String, Double> entry : summary.porMetodo().entrySet()) {
                HBox row = new HBox(8);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(6, 0, 6, 0));

                Label metodoLabel = new Label(entry.getKey());
                metodoLabel.getStyleClass().add("summary-field-label");
                metodoLabel.setMinWidth(140);

                Label montoLabel = new Label(String.format("$%.2f", entry.getValue()));
                montoLabel.getStyleClass().add("summary-field-value");

                row.getChildren().addAll(metodoLabel, montoLabel);
                cierrePorMetodoContainer.getChildren().add(row);
            }
        }
    }

    private void confirmarCierre() {
        // Placeholder: confirm day close
    }

    // ------------------------------------------------------------------ Sub-view 5: Pagos pendientes

    private void setupSubView5_PagosPendientes() {
        if (colPendOrden != null) colPendOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colPendCliente != null) colPendCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colPendSaldo != null) colPendSaldo.setCellValueFactory(data -> data.getValue().saldoProperty());
        if (colPendUltimoPago != null) colPendUltimoPago.setCellValueFactory(data -> data.getValue().ultimoPagoProperty());
        if (colPendEstado != null) colPendEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colPendSucursal != null) colPendSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colPendPrioridad != null) colPendPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());

        if (colPendEstado != null) {
            colPendEstado.setCellFactory(createPendStatusBadgeCell());
        }

        if (colPendPrioridad != null) {
            colPendPrioridad.setCellFactory(createPendPrioridadCell());
        }

        if (contactarClienteBtn != null) {
            contactarClienteBtn.setOnAction(e -> contactarCliente());
        }

        if (enviarRecordatorioBtn != null) {
            enviarRecordatorioBtn.setOnAction(e -> enviarRecordatorio());
        }
    }

    private void loadSubView5_PagosPendientes() {
        if (pendientesTable == null) return;
        List<CajaRowModel.PendienteRow> pendientes = facade.getPagosPendientes();
        ObservableList<CajaRowModel.PendienteRow> data = FXCollections.observableArrayList(pendientes);
        pendientesTable.setItems(data);
    }

    private void contactarCliente() {
        // Placeholder
    }

    private void enviarRecordatorio() {
        // Placeholder
    }

    // ------------------------------------------------------------------ Sub-view 6: Historico

    private void setupSubView6_Historico() {
        if (colHistFecha != null) colHistFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHistOrden != null) colHistOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colHistCliente != null) colHistCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colHistMonto != null) colHistMonto.setCellValueFactory(data -> data.getValue().montoProperty());
        if (colHistMetodo != null) colHistMetodo.setCellValueFactory(data -> data.getValue().metodoProperty());
        if (colHistEstado != null) colHistEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colHistComprobante != null) colHistComprobante.setCellValueFactory(data -> data.getValue().comprobanteProperty());

        if (colHistEstado != null) {
            colHistEstado.setCellFactory(createHistStatusBadgeCell());
        }
    }

    private void loadSubView6_Historico() {
        if (historicoTable == null) return;
        List<CajaRowModel.CobroRow> historico = facade.getHistorico(currentFilters);
        ObservableList<CajaRowModel.CobroRow> data = FXCollections.observableArrayList(historico);
        historicoTable.setItems(data);

        if (footerLabel != null) {
            footerLabel.setText("Total registros: " + historico.size());
        }
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (nuevoCobroBtn != null) {
            nuevoCobroBtn.setOnAction(e -> onNuevoCobro());
        }
        if (actualizarCajaBtn != null) {
            actualizarCajaBtn.setOnAction(e -> onActualizarCaja());
        }
        if (exportarCobrosBtn != null) {
            exportarCobrosBtn.setOnAction(e -> onExportarCobros());
        }
    }

    private void onNuevoCobro() {
        if (btnCobroOrden != null) btnCobroOrden.setSelected(true);
        showSubView(0);
    }

    /**
     * Maneja la accion de actualizar todos los datos del modulo de caja.
     * Reaplica los filtros actuales y recarga el panel de resumen.
     */
    private void onActualizarCaja() {
        // Reload all data
        applyFilters();
        loadSummaryPanel();
    }

    /** Maneja la exportacion de cobros a CSV. Metodo placeholder. */
    private void onExportarCobros() {
        // Placeholder: export cobros to CSV
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
        currentFilters = new CajaFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                metodoPagoCombo != null && metodoPagoCombo.getValue() != null ? metodoPagoCombo.getValue() : "Todos",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().format(fmt) : "",
                hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().format(fmt) : "",
                soloPendientesCheck != null && soloPendientesCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        // Reload current sub-view data
        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private void clearFilters() {
        currentFilters = new CajaFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (metodoPagoCombo != null) metodoPagoCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloPendientesCheck != null) soloPendientesCheck.setSelected(false);

        int currentIdx = getCurrentSubViewIndex();
        showSubView(currentIdx);
    }

    private int getCurrentSubViewIndex() {
        if (btnCobroOrden != null && btnCobroOrden.isSelected()) return 0;
        if (btnAbonosSaldos != null && btnAbonosSaldos.isSelected()) return 1;
        if (btnComprobantes != null && btnComprobantes.isSelected()) return 2;
        if (btnCierreCaja != null && btnCierreCaja.isSelected()) return 3;
        if (btnPagosPendientes != null && btnPagosPendientes.isSelected()) return 4;
        if (btnHistorico != null && btnHistorico.isSelected()) return 5;
        return 0;
    }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnCobrar != null) {
            summaryBtnCobrar.setOnAction(e -> registrarCobro());
        }
        if (summaryBtnImprimir != null) {
            summaryBtnImprimir.setOnAction(e -> imprimirComprobante());
        }
        if (summaryBtnEnviar != null) {
            summaryBtnEnviar.setOnAction(e -> enviarComprobante());
        }
    }

    private void loadSummaryPanel() {
        // Load demo seed data
        updateSummaryPanel(CajaSummaryModel.demoSeed());
    }

    private void updateSummaryPanel(CajaSummaryModel summary) {
        if (summary == null) return;

        if (summaryOrden != null) summaryOrden.setText(summary.referenciaOrden());
        if (summaryCliente != null) summaryCliente.setText(summary.cliente());
        if (summaryCodigoCliente != null) summaryCodigoCliente.setText(summary.codigoCliente());
        if (summaryFechaPromesa != null) summaryFechaPromesa.setText(summary.fechaPromesa());

        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();

            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Subtotal", summary.subtotal()),
                    new SummaryFieldModel("Descuento", summary.descuento()),
                    new SummaryFieldModel("Abono acumulado", summary.abonoAcumulado()),
                    new SummaryFieldModel("Saldo", summary.saldo(), true),
                    new SummaryFieldModel("Estado de cobro", summary.estadoCobro()),
                    new SummaryFieldModel("Metodo principal", summary.metodoPrincipal()),
                    new SummaryFieldModel("Comprobante", summary.comprobante())
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

    private void imprimirComprobante() {
        // Placeholder
    }

    private void enviarComprobante() {
        // Placeholder
    }

    // ------------------------------------------------------------------ Cell factories

    private javafx.util.Callback<TableColumn<CajaRowModel.CobroRow, String>, javafx.scene.control.TableCell<CajaRowModel.CobroRow, String>> createHistStatusBadgeCell() {
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
                        VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "pagado", "activo", "confirmado", "completado" -> StatusBadgeModel.success(item);
                            case "pendiente", "en_proceso", "por_cobrar", "en_espera" -> StatusBadgeModel.warning(item);
                            case "cancelado", "rechazado", "vencido" -> StatusBadgeModel.danger(item);
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

    private javafx.util.Callback<TableColumn<CajaRowModel.SaldoRow, String>, javafx.scene.control.TableCell<CajaRowModel.SaldoRow, String>> createSaldoStatusBadgeCell() {
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
                        VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "pagado", "activo", "confirmado", "completado" -> StatusBadgeModel.success(item);
                            case "pendiente", "en_proceso", "por_cobrar", "en_espera" -> StatusBadgeModel.warning(item);
                            case "cancelado", "rechazado", "vencido" -> StatusBadgeModel.danger(item);
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

    private javafx.util.Callback<TableColumn<CajaRowModel.ComprobanteRow, String>, javafx.scene.control.TableCell<CajaRowModel.ComprobanteRow, String>> createCompStatusBadgeCell() {
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
                        VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "pagado", "activo", "confirmado", "completado" -> StatusBadgeModel.success(item);
                            case "pendiente", "en_proceso", "por_cobrar", "en_espera" -> StatusBadgeModel.warning(item);
                            case "cancelado", "rechazado", "vencido" -> StatusBadgeModel.danger(item);
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

    private javafx.util.Callback<TableColumn<CajaRowModel.PendienteRow, String>, javafx.scene.control.TableCell<CajaRowModel.PendienteRow, String>> createPendStatusBadgeCell() {
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
                        VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "pagado", "activo", "confirmado", "completado" -> StatusBadgeModel.success(item);
                            case "pendiente", "en_proceso", "por_cobrar", "en_espera" -> StatusBadgeModel.warning(item);
                            case "cancelado", "rechazado", "vencido" -> StatusBadgeModel.danger(item);
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

    private javafx.util.Callback<TableColumn<CajaRowModel.PendienteRow, String>, javafx.scene.control.TableCell<CajaRowModel.PendienteRow, String>> createPendPrioridadCell() {
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
                        VBox badgeRoot = loader.load();
                        com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toUpperCase()) {
                            case "ALTA" -> StatusBadgeModel.danger(item);
                            case "MEDIA" -> StatusBadgeModel.warning(item);
                            default -> StatusBadgeModel.success(item);
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

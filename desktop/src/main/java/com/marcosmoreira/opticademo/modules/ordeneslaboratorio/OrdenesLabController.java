package com.marcosmoreira.opticademo.modules.ordeneslaboratorio;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.model.SummaryFieldModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Ordenes de Laboratorio module.
 * Manages 6 sub-views with a persistent right panel showing order summary.
 * Clean separation: no business logic.
 */
public class OrdenesLabController {

    // ---- Top bar ----
    @FXML
    private Button nuevaOrdenBtn;

    @FXML
    private Button actualizarModuloBtn;

    @FXML
    private Button exportarOrdenesBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> laboratorioCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private ComboBox<String> prioridadCombo;

    @FXML
    private DatePicker desdePicker;

    @FXML
    private DatePicker hastaPicker;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnColaOrdenes;

    @FXML
    private ToggleButton btnDetalle;

    @FXML
    private ToggleButton btnSeguimiento;

    @FXML
    private ToggleButton btnEnvioRecepcion;

    @FXML
    private ToggleButton btnIncidencias;

    @FXML
    private ToggleButton btnHistorico;

    // ---- Sub-view containers ----
    @FXML
    private VBox colaOrdenesView;

    @FXML
    private VBox detalleView;

    @FXML
    private VBox seguimientoView;

    @FXML
    private VBox envioRecepcionView;

    @FXML
    private VBox incidenciasView;

    @FXML
    private VBox historicoView;

    // ---- Cola de ordenes ----
    @FXML
    private TableView<OrdenesLabRowModel.ColaRow> colaTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colRef;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colCliente;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colTipoTrabajo;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colIngreso;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colFechaPromesa;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colEstado;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colLaboratorio;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colPrioridad;

    @FXML
    private TableColumn<OrdenesLabRowModel.ColaRow, String> colSucursal;

    @FXML
    private Label colaFooterLabel;

    // ---- Detalle ----
    @FXML
    private Label detReferencia;

    @FXML
    private Label detCliente;

    @FXML
    private Label detTipo;

    @FXML
    private Label detEstado;

    @FXML
    private Label detPrioridad;

    @FXML
    private Label detFechaPromesa;

    @FXML
    private Label detReceta;

    @FXML
    private Label detMontura;

    @FXML
    private Label detLente;

    @FXML
    private Label detTratamientos;

    @FXML
    private Label detPD;

    @FXML
    private Label detObservacion;

    @FXML
    private Label detLaboratorio;

    @FXML
    private Label detFechas;

    @FXML
    private Label detGuia;

    @FXML
    private Label detRecepcionEsperada;

    @FXML
    private Label detResponsable;

    @FXML
    private Button detEditarOrdenBtn;

    @FXML
    private Button detActualizarEstadoBtn;

    @FXML
    private Button detRegistrarEnvioBtn;

    @FXML
    private Button detRegistrarRecepcionBtn;

    @FXML
    private Button detImprimirHojaBtn;

    // ---- Seguimiento por etapas ----
    @FXML
    private Label etapaRecibida;

    @FXML
    private Label etapaValidada;

    @FXML
    private Label etapaEnProduccion;

    @FXML
    private Label etapaEnControl;

    @FXML
    private Label etapaLista;

    @FXML
    private Label etapaEnviada;

    @FXML
    private Label etapaRecibidaSucursal;

    @FXML
    private Label etapaEntregada;

    @FXML
    private TableView<OrdenesLabRowModel.EtapaRow> etapasTable;

    @FXML
    private TableColumn<OrdenesLabRowModel.EtapaRow, String> colEtapaFecha;

    @FXML
    private TableColumn<OrdenesLabRowModel.EtapaRow, String> colEtapaEvento;

    @FXML
    private TableColumn<OrdenesLabRowModel.EtapaRow, String> colEtapaResponsable;

    @FXML
    private TableColumn<OrdenesLabRowModel.EtapaRow, String> colEtapaObservacion;

    @FXML
    private Button segActualizarEtapaBtn;

    @FXML
    private Button segAgregarCheckpointBtn;

    @FXML
    private Button segMarcarListaBtn;

    // ---- Envio y recepcion ----
    @FXML
    private Label envioLaboratorio;

    @FXML
    private Label envioFechaEnvio;

    @FXML
    private Label envioGuia;

    @FXML
    private Label envioTransportista;

    @FXML
    private Label envioFechaEstimada;

    @FXML
    private Label recepFechaRecepcion;

    @FXML
    private Label recepRecibidoPor;

    @FXML
    private Label recepSucursal;

    @FXML
    private Label recepEstado;

    @FXML
    private Button envRegistrarEnvioBtn;

    @FXML
    private Button envRegistrarRecepcionBtn;

    @FXML
    private Button envActualizarGuiaBtn;

    // ---- Incidencias ----
    @FXML
    private TableView<OrdenesLabRowModel.IncidenciaRow> incidenciasTable;

    @FXML
    private TableColumn<OrdenesLabRowModel.IncidenciaRow, String> colIncFecha;

    @FXML
    private TableColumn<OrdenesLabRowModel.IncidenciaRow, String> colIncTipo;

    @FXML
    private TableColumn<OrdenesLabRowModel.IncidenciaRow, String> colIncEstado;

    @FXML
    private TableColumn<OrdenesLabRowModel.IncidenciaRow, String> colIncResponsable;

    @FXML
    private TableColumn<OrdenesLabRowModel.IncidenciaRow, String> colIncResolucion;

    @FXML
    private Label incDetalleFecha;

    @FXML
    private Label incDetalleTipo;

    @FXML
    private Label incDetalleEstado;

    @FXML
    private Label incDetalleResponsable;

    @FXML
    private Label incDetalleResolucion;

    @FXML
    private Button incRegistrarBtn;

    @FXML
    private Button incMarcarResueltaBtn;

    @FXML
    private Button incGenerarRetrabajoBtn;

    @FXML
    private Button incNotificarClienteBtn;

    // ---- Historico ----
    @FXML
    private TextField histSearchField;

    @FXML
    private ComboBox<String> histEstadoCombo;

    @FXML
    private ComboBox<String> histLaboratorioCombo;

    @FXML
    private ComboBox<String> histSucursalCombo;

    @FXML
    private DatePicker histDesdePicker;

    @FXML
    private DatePicker histHastaPicker;

    @FXML
    private TableView<OrdenesLabRowModel.HistoricoRow> historicoTable;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistRef;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistCliente;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistIngreso;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistFechaPromesa;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistEstadoFinal;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistLaboratorio;

    @FXML
    private TableColumn<OrdenesLabRowModel.HistoricoRow, String> colHistSucursal;

    @FXML
    private Button histAbrirOrdenBtn;

    @FXML
    private Button histExportarBtn;

    // ---- Summary panel ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipoTrabajo;

    @FXML
    private Label summaryPrioridad;

    @FXML
    private VBox summaryFieldsContainer;

    @FXML
    private Button summaryEditarBtn;

    @FXML
    private Button summaryActualizarEstadoBtn;

    @FXML
    private Button summaryRegistrarEnvioBtn;

    @FXML
    private Button summaryRegistrarRecepcionBtn;

    @FXML
    private Button summaryImprimirBtn;

    // ---- Facade and state ----
    private OrdenesLabFacade facade;

    private OrdenesLabFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 10;

    private ToggleGroup subViewToggleGroup;

    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new OrdenesLabFacade(store);
        this.currentFilters = new OrdenesLabFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupToggleGroup();
        setupColaTableColumns();
        setupSeguimientoTableColumns();
        setupIncidenciasTableColumns();
        setupHistoricoTableColumns();
        setupActionButtons();
        loadColaData();
    }

    // ---- Filter combos ----
    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadColaData();
    }

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (laboratorioCombo != null) {
            List<String> labs = facade.getLaboratorios();
            String[] items = labs.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Laboratorio", "Todos", items);
            replaceInParent(laboratorioCombo, combo);
            laboratorioCombo = combo;
            laboratorioCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            List<String> sucursales = facade.getSucursales();
            String[] items = sucursales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todos", items);
            replaceInParent(sucursalCombo, combo);
            sucursalCombo = combo;
            sucursalCombo.setOnAction(e -> applyFilters());
        }

        if (prioridadCombo != null) {
            List<String> prioridades = facade.getPrioridades();
            String[] items = prioridades.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Prioridad", "Todas", items);
            replaceInParent(prioridadCombo, combo);
            prioridadCombo = combo;
            prioridadCombo.setOnAction(e -> applyFilters());
        }

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
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

    // ---- Toggle group for sub-views ----
    private void setupToggleGroup() {
        subViewToggleGroup = new ToggleGroup();

        if (btnColaOrdenes != null) {
            btnColaOrdenes.setToggleGroup(subViewToggleGroup);
            btnColaOrdenes.setSelected(true);
            btnColaOrdenes.setOnAction(e -> showSubView("cola"));
        }
        if (btnDetalle != null) {
            btnDetalle.setToggleGroup(subViewToggleGroup);
            btnDetalle.setOnAction(e -> showSubView("detalle"));
        }
        if (btnSeguimiento != null) {
            btnSeguimiento.setToggleGroup(subViewToggleGroup);
            btnSeguimiento.setOnAction(e -> showSubView("seguimiento"));
        }
        if (btnEnvioRecepcion != null) {
            btnEnvioRecepcion.setToggleGroup(subViewToggleGroup);
            btnEnvioRecepcion.setOnAction(e -> showSubView("envioRecepcion"));
        }
        if (btnIncidencias != null) {
            btnIncidencias.setToggleGroup(subViewToggleGroup);
            btnIncidencias.setOnAction(e -> showSubView("incidencias"));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(subViewToggleGroup);
            btnHistorico.setOnAction(e -> showSubView("historico"));
        }

        // Ensure first view is visible
        showSubView("cola");
    }

    private void showSubView(String view) {
        boolean showCola = "cola".equals(view);
        boolean showDetalle = "detalle".equals(view);
        boolean showSeguimiento = "seguimiento".equals(view);
        boolean showEnvioRecepcion = "envioRecepcion".equals(view);
        boolean showIncidencias = "incidencias".equals(view);
        boolean showHistorico = "historico".equals(view);

        if (colaOrdenesView != null) colaOrdenesView.setVisible(showCola);
        if (colaOrdenesView != null) colaOrdenesView.setManaged(showCola);

        if (detalleView != null) detalleView.setVisible(showDetalle);
        if (detalleView != null) detalleView.setManaged(showDetalle);

        if (seguimientoView != null) seguimientoView.setVisible(showSeguimiento);
        if (seguimientoView != null) seguimientoView.setManaged(showSeguimiento);

        if (envioRecepcionView != null) envioRecepcionView.setVisible(showEnvioRecepcion);
        if (envioRecepcionView != null) envioRecepcionView.setManaged(showEnvioRecepcion);

        if (incidenciasView != null) incidenciasView.setVisible(showIncidencias);
        if (incidenciasView != null) incidenciasView.setManaged(showIncidencias);

        if (historicoView != null) historicoView.setVisible(showHistorico);
        if (historicoView != null) historicoView.setManaged(showHistorico);
    }

    // ---- Cola de ordenes table ----
    private void setupColaTableColumns() {
        if (colRef != null) colRef.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colCliente != null) colCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colTipoTrabajo != null) colTipoTrabajo.setCellValueFactory(data -> data.getValue().tipoTrabajoProperty());
        if (colIngreso != null) colIngreso.setCellValueFactory(data -> data.getValue().ingresoProperty());
        if (colFechaPromesa != null) colFechaPromesa.setCellValueFactory(data -> data.getValue().fechaPromesaProperty());
        if (colEstado != null) colEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colLaboratorio != null) colLaboratorio.setCellValueFactory(data -> data.getValue().laboratorioProperty());
        if (colPrioridad != null) colPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colSucursal != null) colSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell factory for estado column with StatusBadge
        if (colEstado != null) {
            colEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController ctrl = loader.getController();
                            StatusBadgeModel badge = getStatusBadgeForEstado(item);
                            ctrl.setStatus(badge);
                            setGraphic(badgeRoot);
                            setText(null);
                        } catch (IOException e) {
                            setText(item);
                            setGraphic(null);
                        }
                    }
                }
            });
        }

        if (colaTable != null) {
            colaTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> onColaRowSelected(newVal));
        }
    }

    private StatusBadgeModel getStatusBadgeForEstado(String estado) {
        if (estado == null) return StatusBadgeModel.neutral("Desconocido");
        return switch (estado.toLowerCase()) {
            case "lista", "entregada", "recibida en sucursal" -> StatusBadgeModel.success(estado);
            case "en produccion", "validada", "recibida" -> StatusBadgeModel.info(estado);
            case "con incidencia", "retrasada" -> StatusBadgeModel.danger(estado);
            case "enviada" -> StatusBadgeModel.warning(estado);
            default -> StatusBadgeModel.neutral(estado);
        };
    }

    private void loadColaData() {
        PageResult<OrdenesLabRowModel.ColaRow> pageResult = facade.getColaOrdenes(currentFilters, currentPageRequest);
        ObservableList<OrdenesLabRowModel.ColaRow> data = FXCollections.observableArrayList(pageResult.getItems());
        if (colaTable != null) {
            colaTable.setItems(data);
        }

        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages(pageResult.getTotalPages());
            paginationBarController.setTotalItems(pageResult.getTotalItems());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        if (colaFooterLabel != null) {
            int shown = pageResult.getItems().size();
            int total = pageResult.getTotalItems();
            colaFooterLabel.setText(String.format("Mostrando %d de %d ordenes", shown, total));
        }

        this.currentPageIndex = pageResult.getPageIndex();
        this.pageSize = pageResult.getPageSize();

        if (!pageResult.isEmpty() && colaTable != null) {
            colaTable.getSelectionModel().selectFirst();
        }
    }

    private void applyFilters() {
        currentFilters = new OrdenesLabFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                laboratorioCombo != null && laboratorioCombo.getValue() != null ? laboratorioCombo.getValue() : "Todos",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todos",
                prioridadCombo != null && prioridadCombo.getValue() != null ? prioridadCombo.getValue() : "Todas",
                desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().toString() : "",
                hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().toString() : ""
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadColaData();
    }

    private void clearFilters() {
        currentFilters = new OrdenesLabFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (laboratorioCombo != null) laboratorioCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (prioridadCombo != null) prioridadCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);

        loadColaData();
    }

    private void onColaRowSelected(OrdenesLabRowModel.ColaRow row) {
        if (row == null) {
            clearSummaryPanel();
            return;
        }
        OrdenesLabSummaryModel summary = facade.buildSummaryFromRow(row);
        updateSummaryPanel(summary);
    }

    // ---- Seguimiento table ----
    private void setupSeguimientoTableColumns() {
        if (colEtapaFecha != null) colEtapaFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colEtapaEvento != null) colEtapaEvento.setCellValueFactory(data -> data.getValue().eventoProperty());
        if (colEtapaResponsable != null) colEtapaResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());
        if (colEtapaObservacion != null) colEtapaObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        // Load seed data for etapas
        if (etapasTable != null) {
            List<OrdenesLabRowModel.EtapaRow> etapas = facade.getEtapas(null);
            etapasTable.setItems(FXCollections.observableArrayList(etapas));
        }
    }

    // ---- Incidencias table ----
    private void setupIncidenciasTableColumns() {
        if (colIncFecha != null) colIncFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colIncTipo != null) colIncTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colIncEstado != null) colIncEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colIncResponsable != null) colIncResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());
        if (colIncResolucion != null) colIncResolucion.setCellValueFactory(data -> data.getValue().resolucionProperty());

        if (incidenciasTable != null) {
            List<OrdenesLabRowModel.IncidenciaRow> incidencias = facade.getAllIncidencias();
            incidenciasTable.setItems(FXCollections.observableArrayList(incidencias));
            incidenciasTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> updateIncidenciaDetail(newVal));
        }
    }

    // ---- Historico table ----
    private void setupHistoricoTableColumns() {
        if (colHistRef != null) colHistRef.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHistCliente != null) colHistCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colHistIngreso != null) colHistIngreso.setCellValueFactory(data -> data.getValue().ingresoProperty());
        if (colHistFechaPromesa != null) colHistFechaPromesa.setCellValueFactory(data -> data.getValue().fechaPromesaProperty());
        if (colHistEstadoFinal != null) colHistEstadoFinal.setCellValueFactory(data -> data.getValue().estadoFinalProperty());
        if (colHistLaboratorio != null) colHistLaboratorio.setCellValueFactory(data -> data.getValue().laboratorioProperty());
        if (colHistSucursal != null) colHistSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (historicoTable != null) {
            OrdenesLabFilters histFilters = new OrdenesLabFilters();
            List<OrdenesLabRowModel.HistoricoRow> historico = facade.getHistorico(histFilters);
            historicoTable.setItems(FXCollections.observableArrayList(historico));
        }
    }

    // ---- Action buttons ----
    private void setupActionButtons() {
        if (nuevaOrdenBtn != null) nuevaOrdenBtn.setOnAction(e -> onNuevaOrden());
        if (actualizarModuloBtn != null) actualizarModuloBtn.setOnAction(e -> loadColaData());
        if (exportarOrdenesBtn != null) exportarOrdenesBtn.setOnAction(e -> onExportarOrdenes());

        // Detalle buttons
        if (detEditarOrdenBtn != null) detEditarOrdenBtn.setOnAction(e -> onEditarOrden());
        if (detActualizarEstadoBtn != null) detActualizarEstadoBtn.setOnAction(e -> onActualizarEstado());
        if (detRegistrarEnvioBtn != null) detRegistrarEnvioBtn.setOnAction(e -> onRegistrarEnvio());
        if (detRegistrarRecepcionBtn != null) detRegistrarRecepcionBtn.setOnAction(e -> onRegistrarRecepcion());
        if (detImprimirHojaBtn != null) detImprimirHojaBtn.setOnAction(e -> onImprimirHoja());

        // Seguimiento buttons
        if (segActualizarEtapaBtn != null) segActualizarEtapaBtn.setOnAction(e -> onActualizarEtapa());
        if (segAgregarCheckpointBtn != null) segAgregarCheckpointBtn.setOnAction(e -> onAgregarCheckpoint());
        if (segMarcarListaBtn != null) segMarcarListaBtn.setOnAction(e -> onMarcarLista());

        // Envio/Recepcion buttons
        if (envRegistrarEnvioBtn != null) envRegistrarEnvioBtn.setOnAction(e -> onRegistrarEnvio());
        if (envRegistrarRecepcionBtn != null) envRegistrarRecepcionBtn.setOnAction(e -> onRegistrarRecepcion());
        if (envActualizarGuiaBtn != null) envActualizarGuiaBtn.setOnAction(e -> onActualizarGuia());

        // Incidencias buttons
        if (incRegistrarBtn != null) incRegistrarBtn.setOnAction(e -> onRegistrarIncidencia());
        if (incMarcarResueltaBtn != null) incMarcarResueltaBtn.setOnAction(e -> onMarcarResuelta());
        if (incGenerarRetrabajoBtn != null) incGenerarRetrabajoBtn.setOnAction(e -> onGenerarRetrabajo());
        if (incNotificarClienteBtn != null) incNotificarClienteBtn.setOnAction(e -> onNotificarCliente());

        // Historico buttons
        if (histAbrirOrdenBtn != null) histAbrirOrdenBtn.setOnAction(e -> onAbrirOrden());
        if (histExportarBtn != null) histExportarBtn.setOnAction(e -> onExportarHistorico());

        // Summary panel buttons
        if (summaryEditarBtn != null) summaryEditarBtn.setOnAction(e -> onEditarOrden());
        if (summaryActualizarEstadoBtn != null) summaryActualizarEstadoBtn.setOnAction(e -> onActualizarEstado());
        if (summaryRegistrarEnvioBtn != null) summaryRegistrarEnvioBtn.setOnAction(e -> onRegistrarEnvio());
        if (summaryRegistrarRecepcionBtn != null) summaryRegistrarRecepcionBtn.setOnAction(e -> onRegistrarRecepcion());
        if (summaryImprimirBtn != null) summaryImprimirBtn.setOnAction(e -> onImprimirHoja());
    }

    // ---- Summary panel ----
    private void updateSummaryPanel(OrdenesLabSummaryModel summary) {
        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryTipoTrabajo != null) summaryTipoTrabajo.setText(summary.tipoTrabajo());
        if (summaryPrioridad != null) summaryPrioridad.setText(summary.prioridad());

        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Cliente", summary.cliente()),
                    new SummaryFieldModel("Codigo cliente", summary.codigoCliente()),
                    new SummaryFieldModel("Sucursal", summary.sucursal()),
                    new SummaryFieldModel("Fecha promesa", summary.fechaPromesa()),
                    new SummaryFieldModel("Montura", summary.montura()),
                    new SummaryFieldModel("Lente", summary.lente()),
                    new SummaryFieldModel("Tratamientos", summary.tratamientos()),
                    new SummaryFieldModel("PD", summary.pd()),
                    new SummaryFieldModel("Estado actual", summary.estadoActual()),
                    new SummaryFieldModel("Laboratorio", summary.laboratorio()),
                    new SummaryFieldModel("Guia", summary.guia()),
                    new SummaryFieldModel("Recepcion esperada", summary.recepcionEsperada())
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

    private void clearSummaryPanel() {
        if (summaryReferencia != null) summaryReferencia.setText("Sin seleccion");
        if (summaryTipoTrabajo != null) summaryTipoTrabajo.setText("");
        if (summaryPrioridad != null) summaryPrioridad.setText("");
        if (summaryFieldsContainer != null) summaryFieldsContainer.getChildren().clear();
    }

    // ---- Incidencia detail panel ----
    private void updateIncidenciaDetail(OrdenesLabRowModel.IncidenciaRow row) {
        if (row == null) {
            if (incDetalleFecha != null) incDetalleFecha.setText("-");
            if (incDetalleTipo != null) incDetalleTipo.setText("-");
            if (incDetalleEstado != null) incDetalleEstado.setText("-");
            if (incDetalleResponsable != null) incDetalleResponsable.setText("-");
            if (incDetalleResolucion != null) incDetalleResolucion.setText("-");
            return;
        }
        if (incDetalleFecha != null) incDetalleFecha.setText(row.fecha() != null ? row.fecha() : "-");
        if (incDetalleTipo != null) incDetalleTipo.setText(row.tipo() != null ? row.tipo() : "-");
        if (incDetalleEstado != null) incDetalleEstado.setText(row.estado() != null ? row.estado() : "-");
        if (incDetalleResponsable != null) incDetalleResponsable.setText(row.responsable() != null ? row.responsable() : "-");
        if (incDetalleResolucion != null) incDetalleResolucion.setText(row.resolucion() != null ? row.resolucion() : "-");
    }

    // ---- Placeholder actions ----
    private void onNuevaOrden() {
        // Placeholder: open new lab order form
    }

    private void onExportarOrdenes() {
        // Placeholder: export orders to CSV/PDF
    }

    private void onEditarOrden() {
        // Placeholder: open edit order form
    }

    private void onActualizarEstado() {
        // Placeholder: update order status
    }

    private void onRegistrarEnvio() {
        // Placeholder: register shipment
    }

    private void onRegistrarRecepcion() {
        // Placeholder: register reception
    }

    private void onImprimirHoja() {
        // Placeholder: print work sheet
    }

    private void onActualizarEtapa() {
        // Placeholder: update stage
    }

    private void onAgregarCheckpoint() {
        // Placeholder: add checkpoint
    }

    private void onMarcarLista() {
        // Placeholder: mark as ready
    }

    private void onActualizarGuia() {
        // Placeholder: update tracking number
    }

    private void onRegistrarIncidencia() {
        // Placeholder: register incident
    }

    private void onMarcarResuelta() {
        // Placeholder: mark incident as resolved
    }

    private void onGenerarRetrabajo() {
        // Placeholder: generate rework
    }

    private void onNotificarCliente() {
        // Placeholder: notify client
    }

    private void onAbrirOrden() {
        // Placeholder: open order
    }

    private void onExportarHistorico() {
        // Placeholder: export history
    }
}

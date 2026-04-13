package com.marcosmoreira.opticademo.modules.compras;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller del modulo de Compras del sistema optico.
 * <p>
 * Gestiona siete sub-vistas para la gestion integral del proceso de abastecimiento:
 * Solicitudes de compra, Ordenes de compra, Compras por proveedor, Back-orders,
 * Recepcion vinculada, Compras por sucursal, e Historico de compras.
 * </p>
 * <p>
 * Cada sub-vista presenta datos tabulares con badges de estado coloreados. La seleccion
 * de una solicitud u orden actualiza el panel de resumen derecho con los detalles
 * del registro seleccionado. La fachada {@link ComprasFacade} centraliza el acceso
 * a los datos de compras almacenados en el {@link DemoStore}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ComprasFacade
 * @see ComprasFilters
 * @see ComprasSummaryModel
 */
public class ComprasController {

    // ---- Top bar ----

    /** Boton para crear una nueva solicitud de compra. */
    @FXML
    private Button nuevaSolicitudBtn;

    /** Boton para crear una nueva orden de compra. */
    @FXML
    private Button nuevaOrdenBtn;

    /** Boton para exportar los datos de compras. */
    @FXML
    private Button exportarComprasBtn;

    // ---- Filters ----

    /** Campo de busqueda por referencia de solicitud u orden. */
    @FXML
    private TextField searchField;

    /** ComboBox para filtrar por estado de la compra. */
    @FXML
    private ComboBox<String> estadoCombo;

    /** ComboBox para filtrar por proveedor. */
    @FXML
    private ComboBox<String> proveedorCombo;

    /** ComboBox para filtrar por categoria de productos. */
    @FXML
    private ComboBox<String> categoriaCombo;

    /** ComboBox para filtrar por sucursal destino. */
    @FXML
    private ComboBox<String> sucursalCombo;

    /** DatePicker para fecha inicio del rango de filtrado. */
    @FXML
    private DatePicker desdePicker;

    /** DatePicker para fecha fin del rango de filtrado. */
    @FXML
    private DatePicker hastaPicker;

    /** CheckBox para mostrar solo pedidos pendientes criticos. */
    @FXML
    private CheckBox soloPendientesCriticosCheck;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----

    /** Toggle button para la sub-vista Solicitudes de compra. */
    @FXML
    private ToggleButton subViewSolicitudesBtn;

    /** Toggle button para la sub-vista Ordenes de compra. */
    @FXML
    private ToggleButton subViewOrdenesBtn;

    /** Toggle button para la sub-vista Compras por proveedor. */
    @FXML
    private ToggleButton subViewProveedorBtn;

    /** Toggle button para la sub-vista Back-orders. */
    @FXML
    private ToggleButton subViewBackOrdersBtn;

    /** Toggle button para la sub-vista Recepcion vinculada. */
    @FXML
    private ToggleButton subViewRecepcionBtn;

    /** Toggle button para la sub-vista Compras por sucursal. */
    @FXML
    private ToggleButton subViewSucursalBtn;

    /** Toggle button para la sub-vista Historico. */
    @FXML
    private ToggleButton subViewHistoricoBtn;

    // ---- Sub-view containers ----
    @FXML
    private VBox solicitudesSection;

    @FXML
    private VBox ordenesSection;

    @FXML
    private VBox proveedorSection;

    @FXML
    private VBox backOrdersSection;

    @FXML
    private VBox recepcionSection;

    @FXML
    private VBox sucursalSection;

    @FXML
    private VBox historicoSection;

    // ---- Solicitudes table ----
    @FXML
    private TableView<ComprasRowModel.SolicitudRow> solicitudesTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolSolicitud;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolMotivo;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolCategoria;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolSucursalDestino;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolPrioridad;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolEstado;

    @FXML
    private TableColumn<ComprasRowModel.SolicitudRow, String> colSolProveedorSugerido;

    // ---- Ordenes table ----
    @FXML
    private TableView<ComprasRowModel.OrdenCompraRow> ordenesTable;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdOrden;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdProveedor;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdFecha;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdSucursalDestino;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdEstado;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdItems;

    @FXML
    private TableColumn<ComprasRowModel.OrdenCompraRow, String> colOrdTotalEstimado;

    // ---- Proveedor table ----
    @FXML
    private TableView<ComprasRowModel.ProveedorCompraRow> proveedorTable;

    @FXML
    private TableColumn<ComprasRowModel.ProveedorCompraRow, String> colProvProveedor;

    @FXML
    private TableColumn<ComprasRowModel.ProveedorCompraRow, String> colProvOrdenesAbiertas;

    @FXML
    private TableColumn<ComprasRowModel.ProveedorCompraRow, String> colProvUltimaCompra;

    @FXML
    private TableColumn<ComprasRowModel.ProveedorCompraRow, String> colProvCategoriaPrincipal;

    @FXML
    private TableColumn<ComprasRowModel.ProveedorCompraRow, String> colProvEstado;

    // ---- Back-orders table ----
    @FXML
    private TableView<ComprasRowModel.BackOrderRow> backOrdersTable;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOReferencia;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOOrden;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOProveedor;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOItemPendiente;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOCantidad;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOFechaEsperada;

    @FXML
    private TableColumn<ComprasRowModel.BackOrderRow, String> colBOEstado;

    // ---- Recepcion table ----
    @FXML
    private TableView<ComprasRowModel.RecepcionCompraRow> recepcionTable;

    @FXML
    private TableColumn<ComprasRowModel.RecepcionCompraRow, String> colRecRecepcion;

    @FXML
    private TableColumn<ComprasRowModel.RecepcionCompraRow, String> colRecOrden;

    @FXML
    private TableColumn<ComprasRowModel.RecepcionCompraRow, String> colRecFecha;

    @FXML
    private TableColumn<ComprasRowModel.RecepcionCompraRow, String> colRecEstado;

    @FXML
    private TableColumn<ComprasRowModel.RecepcionCompraRow, String> colRecDiferencias;

    @FXML
    private TableColumn<ComprasRowModel.RecepcionCompraRow, String> colRecResponsable;

    // ---- Sucursal table ----
    @FXML
    private TableView<ComprasRowModel.SucursalCompraRow> sucursalTable;

    @FXML
    private TableColumn<ComprasRowModel.SucursalCompraRow, String> colSucSucursal;

    @FXML
    private TableColumn<ComprasRowModel.SucursalCompraRow, String> colSucSolicitudes;

    @FXML
    private TableColumn<ComprasRowModel.SucursalCompraRow, String> colSucOrdenesAbiertas;

    @FXML
    private TableColumn<ComprasRowModel.SucursalCompraRow, String> colSucPendientes;

    @FXML
    private TableColumn<ComprasRowModel.SucursalCompraRow, String> colSucTotalEstimado;

    @FXML
    private TableColumn<ComprasRowModel.SucursalCompraRow, String> colSucEstadoGeneral;

    // ---- Historico table ----
    @FXML
    private TableView<ComprasRowModel.HistoricoCompraRow> historicoTable;

    @FXML
    private TableColumn<ComprasRowModel.HistoricoCompraRow, String> colHisFecha;

    @FXML
    private TableColumn<ComprasRowModel.HistoricoCompraRow, String> colHisReferencia;

    @FXML
    private TableColumn<ComprasRowModel.HistoricoCompraRow, String> colHisTipoRegistro;

    @FXML
    private TableColumn<ComprasRowModel.HistoricoCompraRow, String> colHisProveedor;

    @FXML
    private TableColumn<ComprasRowModel.HistoricoCompraRow, String> colHisEstado;

    @FXML
    private TableColumn<ComprasRowModel.HistoricoCompraRow, String> colHisObservacion;

    // ---- Summary panel ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryTipoRegistro;

    @FXML
    private Label summaryEstado;

    @FXML
    private Label summaryProveedor;

    @FXML
    private Label summaryCategoriaPrincipal;

    @FXML
    private Label summaryTiempoEstimado;

    @FXML
    private Label summarySucursalDestino;

    @FXML
    private Label summaryTotalItems;

    @FXML
    private Label summaryTotalEstimado;

    @FXML
    private Label summaryRecepcionVinculada;

    @FXML
    private Label summaryDiferencias;

    @FXML
    private Label summaryObservacionClave;

    @FXML
    private VBox summaryFieldsContainer;

    // ---- Facade ----
    /** Fachada que centraliza la logica de gestion de compras y abastecimiento. */
    private ComprasFacade facade;
    private ComprasFilters currentFilters;
    private com.marcosmoreira.opticademo.shared.query.PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;
    /** Resumen actual del registro de compra seleccionado. */
    private ComprasSummaryModel currentSummary;

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia {@link ComprasFacade} con el {@link DemoStore} global, configura los combos
     * de filtrado, el sistema de toggle entre sub-vistas, las columnas de cada tabla
     * con sus respectivas cell factories y status badges, y carga los datos de solicitudes.
     * </p>
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new ComprasFacade(store);
        this.currentFilters = new ComprasFilters();
        this.currentPageRequest = new com.marcosmoreira.opticademo.shared.query.PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupActionButtons();
        setupSolicitudesTable();
        setupOrdenesTable();
        setupProveedorTable();
        setupBackOrdersTable();
        setupRecepcionTable();
        setupSucursalTable();
        setupHistoricoTable();

        // Show solicitudes by default
        showSubView("solicitudes");
        loadSolicitudes();
        loadOrdenes();
        loadProveedor();
        loadBackOrders();
        loadRecepcion();
        loadSucursal();
        loadHistorico();
    }

    // ==================== Filter combos ====================

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (proveedorCombo != null) {
            List<String> proveedores = facade.getProveedores();
            String[] items = proveedores.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Proveedor", "Todos", items);
            replaceInParent(proveedorCombo, combo);
            proveedorCombo = combo;
            proveedorCombo.setOnAction(e -> applyFilters());
        }

        if (categoriaCombo != null) {
            List<String> categorias = facade.getCategorias();
            String[] items = categorias.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Categoria", "Todas", items);
            replaceInParent(categoriaCombo, combo);
            categoriaCombo = combo;
            categoriaCombo.setOnAction(e -> applyFilters());
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

    // ==================== Sub-view toggle ====================

    private void setupSubViewToggle() {
        ToggleGroup group = new ToggleGroup();
        if (subViewSolicitudesBtn != null) {
            subViewSolicitudesBtn.setToggleGroup(group);
            subViewSolicitudesBtn.setOnAction(e -> showSubView("solicitudes"));
        }
        if (subViewOrdenesBtn != null) {
            subViewOrdenesBtn.setToggleGroup(group);
            subViewOrdenesBtn.setOnAction(e -> showSubView("ordenes"));
        }
        if (subViewProveedorBtn != null) {
            subViewProveedorBtn.setToggleGroup(group);
            subViewProveedorBtn.setOnAction(e -> showSubView("proveedor"));
        }
        if (subViewBackOrdersBtn != null) {
            subViewBackOrdersBtn.setToggleGroup(group);
            subViewBackOrdersBtn.setOnAction(e -> showSubView("backorders"));
        }
        if (subViewRecepcionBtn != null) {
            subViewRecepcionBtn.setToggleGroup(group);
            subViewRecepcionBtn.setOnAction(e -> showSubView("recepcion"));
        }
        if (subViewSucursalBtn != null) {
            subViewSucursalBtn.setToggleGroup(group);
            subViewSucursalBtn.setOnAction(e -> showSubView("sucursal"));
        }
        if (subViewHistoricoBtn != null) {
            subViewHistoricoBtn.setToggleGroup(group);
            subViewHistoricoBtn.setOnAction(e -> showSubView("historico"));
        }
    }

    private void showSubView(String view) {
        boolean showSolicitudes = "solicitudes".equals(view);
        boolean showOrdenes = "ordenes".equals(view);
        boolean showProveedor = "proveedor".equals(view);
        boolean showBackOrders = "backorders".equals(view);
        boolean showRecepcion = "recepcion".equals(view);
        boolean showSucursal = "sucursal".equals(view);
        boolean showHistorico = "historico".equals(view);

        if (solicitudesSection != null) solicitudesSection.setVisible(showSolicitudes);
        if (solicitudesSection != null) solicitudesSection.setManaged(showSolicitudes);
        if (ordenesSection != null) ordenesSection.setVisible(showOrdenes);
        if (ordenesSection != null) ordenesSection.setManaged(showOrdenes);
        if (proveedorSection != null) proveedorSection.setVisible(showProveedor);
        if (proveedorSection != null) proveedorSection.setManaged(showProveedor);
        if (backOrdersSection != null) backOrdersSection.setVisible(showBackOrders);
        if (backOrdersSection != null) backOrdersSection.setManaged(showBackOrders);
        if (recepcionSection != null) recepcionSection.setVisible(showRecepcion);
        if (recepcionSection != null) recepcionSection.setManaged(showRecepcion);
        if (sucursalSection != null) sucursalSection.setVisible(showSucursal);
        if (sucursalSection != null) sucursalSection.setManaged(showSucursal);
        if (historicoSection != null) historicoSection.setVisible(showHistorico);
        if (historicoSection != null) historicoSection.setManaged(showHistorico);
    }

    // ==================== Action buttons ====================

    private void setupActionButtons() {
        if (nuevaSolicitudBtn != null) {
            nuevaSolicitudBtn.setOnAction(e -> onNuevaSolicitud());
        }
        if (nuevaOrdenBtn != null) {
            nuevaOrdenBtn.setOnAction(e -> onNuevaOrden());
        }
        if (exportarComprasBtn != null) {
            exportarComprasBtn.setOnAction(e -> onExportarCompras());
        }
    }

    // ==================== Apply filters ====================

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new com.marcosmoreira.opticademo.shared.query.PageRequest(currentPageIndex, pageSize);
        loadSolicitudes();
    }

    private void applyFilters() {
        currentFilters.setSearchText(searchField != null ? searchField.getText() : "");
        currentFilters.setEstado(estadoCombo != null ? (String) estadoCombo.getValue() : "Todos");
        currentFilters.setProveedor(proveedorCombo != null ? (String) proveedorCombo.getValue() : "Todos");
        currentFilters.setCategoria(categoriaCombo != null ? (String) categoriaCombo.getValue() : "Todas");
        currentFilters.setSucursal(sucursalCombo != null ? (String) sucursalCombo.getValue() : "Todas");
        currentFilters.setSoloPendientesCriticos(soloPendientesCriticosCheck != null && soloPendientesCriticosCheck.isSelected());

        loadSolicitudes();
        loadOrdenes();
        loadHistorico();
    }

    private void clearFilters() {
        currentFilters = new ComprasFilters();
        if (searchField != null) searchField.setText("");
        if (estadoCombo != null) estadoCombo.setValue("Todos");
        if (proveedorCombo != null) proveedorCombo.setValue("Todos");
        if (categoriaCombo != null) categoriaCombo.setValue("Todas");
        if (sucursalCombo != null) sucursalCombo.setValue("Todas");
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloPendientesCriticosCheck != null) soloPendientesCriticosCheck.setSelected(false);

        loadSolicitudes();
        loadOrdenes();
        loadHistorico();
    }

    // ==================== Solicitudes ====================

    private void setupSolicitudesTable() {
        if (colSolSolicitud != null) colSolSolicitud.setCellValueFactory(data -> data.getValue().solicitudProperty());
        if (colSolMotivo != null) colSolMotivo.setCellValueFactory(data -> data.getValue().motivoProperty());
        if (colSolCategoria != null) colSolCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colSolSucursalDestino != null) colSolSucursalDestino.setCellValueFactory(data -> data.getValue().sucursalDestinoProperty());
        if (colSolPrioridad != null) colSolPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colSolEstado != null) colSolEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colSolProveedorSugerido != null) colSolProveedorSugerido.setCellValueFactory(data -> data.getValue().proveedorSugeridoProperty());

        if (colSolEstado != null) {
            colSolEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "aprobada" -> StatusBadgeModel.success(item);
                                case "completada" -> StatusBadgeModel.success(item);
                                case "pendiente" -> StatusBadgeModel.warning(item);
                                case "en proceso" -> StatusBadgeModel.neutral(item);
                                case "cancelada" -> StatusBadgeModel.danger(item);
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
            });
        }

        if (colSolPrioridad != null) {
            colSolPrioridad.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "alta" -> StatusBadgeModel.danger(item);
                                case "media" -> StatusBadgeModel.warning(item);
                                case "baja" -> StatusBadgeModel.success(item);
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
            });
        }

        if (solicitudesTable != null) {
            solicitudesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> updateSummaryPanelFromSolicitud(newVal));
        }
    }

    private void loadSolicitudes() {
        List<ComprasRowModel.SolicitudRow> rows = facade.getSolicitudes(currentFilters);
        ObservableList<ComprasRowModel.SolicitudRow> data = FXCollections.observableArrayList(rows);
        if (solicitudesTable != null) {
            solicitudesTable.setItems(data);
        }
        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages((int) Math.ceil((double) rows.size() / pageSize));
            paginationBarController.setTotalItems(rows.size());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }
        if (!rows.isEmpty() && solicitudesTable != null) {
            solicitudesTable.getSelectionModel().selectFirst();
        }
    }

    private void updateSummaryPanelFromSolicitud(ComprasRowModel.SolicitudRow row) {
        if (row == null) {
            clearSummary();
            return;
        }
        currentSummary = facade.buildSummary(row);
        updateSummaryPanel(currentSummary);
    }

    // ==================== Ordenes ====================

    private void setupOrdenesTable() {
        if (colOrdOrden != null) colOrdOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colOrdProveedor != null) colOrdProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colOrdFecha != null) colOrdFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colOrdSucursalDestino != null) colOrdSucursalDestino.setCellValueFactory(data -> data.getValue().sucursalDestinoProperty());
        if (colOrdEstado != null) colOrdEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colOrdItems != null) colOrdItems.setCellValueFactory(data -> data.getValue().itemsProperty());
        if (colOrdTotalEstimado != null) colOrdTotalEstimado.setCellValueFactory(data -> data.getValue().totalEstimadoProperty());

        if (colOrdEstado != null) {
            colOrdEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "completada" -> StatusBadgeModel.success(item);
                                case "enviada" -> StatusBadgeModel.warning(item);
                                case "pendiente" -> StatusBadgeModel.neutral(item);
                                case "cancelada" -> StatusBadgeModel.danger(item);
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
            });
        }

        if (ordenesTable != null) {
            ordenesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> updateSummaryPanelFromOrden(newVal));
        }
    }

    private void loadOrdenes() {
        List<ComprasRowModel.OrdenCompraRow> rows = facade.getOrdenesCompra(currentFilters);
        ObservableList<ComprasRowModel.OrdenCompraRow> data = FXCollections.observableArrayList(rows);
        if (ordenesTable != null) {
            ordenesTable.setItems(data);
        }
        if (!rows.isEmpty() && ordenesTable != null) {
            ordenesTable.getSelectionModel().selectFirst();
        }
    }

    private void updateSummaryPanelFromOrden(ComprasRowModel.OrdenCompraRow row) {
        if (row == null) {
            clearSummary();
            return;
        }
        currentSummary = facade.buildSummary(row);
        updateSummaryPanel(currentSummary);
    }

    // ==================== Compras por proveedor ====================

    private void setupProveedorTable() {
        if (colProvProveedor != null) colProvProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colProvOrdenesAbiertas != null) colProvOrdenesAbiertas.setCellValueFactory(data -> data.getValue().ordenesAbiertasProperty());
        if (colProvUltimaCompra != null) colProvUltimaCompra.setCellValueFactory(data -> data.getValue().ultimaCompraProperty());
        if (colProvCategoriaPrincipal != null) colProvCategoriaPrincipal.setCellValueFactory(data -> data.getValue().categoriaPrincipalProperty());
        if (colProvEstado != null) colProvEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colProvEstado != null) {
            colProvEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "activo" -> StatusBadgeModel.success(item);
                                case "bajo observacion" -> StatusBadgeModel.warning(item);
                                case "inactivo" -> StatusBadgeModel.danger(item);
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
            });
        }
    }

    private void loadProveedor() {
        List<ComprasRowModel.ProveedorCompraRow> rows = facade.getComprasPorProveedor();
        ObservableList<ComprasRowModel.ProveedorCompraRow> data = FXCollections.observableArrayList(rows);
        if (proveedorTable != null) {
            proveedorTable.setItems(data);
        }
    }

    // ==================== Back-orders ====================

    private void setupBackOrdersTable() {
        if (colBOReferencia != null) colBOReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colBOOrden != null) colBOOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colBOProveedor != null) colBOProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colBOItemPendiente != null) colBOItemPendiente.setCellValueFactory(data -> data.getValue().itemPendienteProperty());
        if (colBOCantidad != null) colBOCantidad.setCellValueFactory(data -> data.getValue().cantidadProperty());
        if (colBOFechaEsperada != null) colBOFechaEsperada.setCellValueFactory(data -> data.getValue().fechaEsperadaProperty());
        if (colBOEstado != null) colBOEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colBOEstado != null) {
            colBOEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "en transito" -> StatusBadgeModel.warning(item);
                                case "back-order" -> StatusBadgeModel.danger(item);
                                case "pendiente" -> StatusBadgeModel.neutral(item);
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
            });
        }
    }

    private void loadBackOrders() {
        List<ComprasRowModel.BackOrderRow> rows = facade.getBackOrders();
        ObservableList<ComprasRowModel.BackOrderRow> data = FXCollections.observableArrayList(rows);
        if (backOrdersTable != null) {
            backOrdersTable.setItems(data);
        }
    }

    // ==================== Recepcion vinculada ====================

    private void setupRecepcionTable() {
        if (colRecRecepcion != null) colRecRecepcion.setCellValueFactory(data -> data.getValue().recepcionProperty());
        if (colRecOrden != null) colRecOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colRecFecha != null) colRecFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colRecEstado != null) colRecEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRecDiferencias != null) colRecDiferencias.setCellValueFactory(data -> data.getValue().diferenciasProperty());
        if (colRecResponsable != null) colRecResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());

        if (colRecEstado != null) {
            colRecEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "completada" -> StatusBadgeModel.success(item);
                                case "parcial" -> StatusBadgeModel.warning(item);
                                case "con diferencias" -> StatusBadgeModel.danger(item);
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
            });
        }
    }

    private void loadRecepcion() {
        List<ComprasRowModel.RecepcionCompraRow> rows = facade.getRecepcionesVinculadas();
        ObservableList<ComprasRowModel.RecepcionCompraRow> data = FXCollections.observableArrayList(rows);
        if (recepcionTable != null) {
            recepcionTable.setItems(data);
        }
    }

    // ==================== Compras por sucursal ====================

    private void setupSucursalTable() {
        if (colSucSucursal != null) colSucSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colSucSolicitudes != null) colSucSolicitudes.setCellValueFactory(data -> data.getValue().solicitudesProperty());
        if (colSucOrdenesAbiertas != null) colSucOrdenesAbiertas.setCellValueFactory(data -> data.getValue().ordenesAbiertasProperty());
        if (colSucPendientes != null) colSucPendientes.setCellValueFactory(data -> data.getValue().pendientesProperty());
        if (colSucTotalEstimado != null) colSucTotalEstimado.setCellValueFactory(data -> data.getValue().totalEstimadoProperty());
        if (colSucEstadoGeneral != null) colSucEstadoGeneral.setCellValueFactory(data -> data.getValue().estadoGeneralProperty());

        if (colSucEstadoGeneral != null) {
            colSucEstadoGeneral.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "normal" -> StatusBadgeModel.success(item);
                                case "sin actividad" -> StatusBadgeModel.neutral(item);
                                case "critico" -> StatusBadgeModel.danger(item);
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
            });
        }
    }

    private void loadSucursal() {
        List<ComprasRowModel.SucursalCompraRow> rows = facade.getComprasPorSucursal();
        ObservableList<ComprasRowModel.SucursalCompraRow> data = FXCollections.observableArrayList(rows);
        if (sucursalTable != null) {
            sucursalTable.setItems(data);
        }
    }

    // ==================== Historico ====================

    private void setupHistoricoTable() {
        if (colHisFecha != null) colHisFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHisReferencia != null) colHisReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colHisTipoRegistro != null) colHisTipoRegistro.setCellValueFactory(data -> data.getValue().tipoRegistroProperty());
        if (colHisProveedor != null) colHisProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colHisEstado != null) colHisEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colHisObservacion != null) colHisObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colHisEstado != null) {
            colHisEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                            StatusBadgeModel badge = switch (item.toLowerCase()) {
                                case "completada" -> StatusBadgeModel.success(item);
                                case "enviada" -> StatusBadgeModel.warning(item);
                                case "en proceso" -> StatusBadgeModel.neutral(item);
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
            });
        }
    }

    private void loadHistorico() {
        List<ComprasRowModel.HistoricoCompraRow> rows = facade.getHistorico(currentFilters);
        ObservableList<ComprasRowModel.HistoricoCompraRow> data = FXCollections.observableArrayList(rows);
        if (historicoTable != null) {
            historicoTable.setItems(data);
        }
    }

    // ==================== Summary panel ====================

    private void updateSummaryPanel(ComprasSummaryModel summary) {
        if (summary == null) {
            clearSummary();
            return;
        }
        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryTipoRegistro != null) summaryTipoRegistro.setText(summary.tipoRegistro());
        if (summaryEstado != null) summaryEstado.setText(summary.estado());
        if (summaryProveedor != null) summaryProveedor.setText(summary.proveedor());
        if (summaryCategoriaPrincipal != null) summaryCategoriaPrincipal.setText(summary.categoriaPrincipal());
        if (summaryTiempoEstimado != null) summaryTiempoEstimado.setText(summary.tiempoEstimado());
        if (summarySucursalDestino != null) summarySucursalDestino.setText(summary.sucursalDestino());
        if (summaryTotalItems != null) summaryTotalItems.setText(summary.totalItems());
        if (summaryTotalEstimado != null) summaryTotalEstimado.setText(summary.totalEstimado());
        if (summaryRecepcionVinculada != null) summaryRecepcionVinculada.setText(summary.recepcionVinculada());
        if (summaryDiferencias != null) summaryDiferencias.setText(summary.diferencias());
        if (summaryObservacionClave != null) summaryObservacionClave.setText(summary.observacionClave());
    }

    private void clearSummary() {
        ComprasSummaryModel empty = ComprasSummaryModel.empty();
        updateSummaryPanel(empty);
    }

    // ==================== Action handlers ====================

    private void onNuevaSolicitud() {
        // Placeholder action
    }

    private void onNuevaOrden() {
        // Placeholder action
    }

    private void onExportarCompras() {
        // Placeholder action
    }
}

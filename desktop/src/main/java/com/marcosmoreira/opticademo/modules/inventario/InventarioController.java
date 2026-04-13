package com.marcosmoreira.opticademo.modules.inventario;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.producto.Producto;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.model.SummaryFieldModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Inventario module.
 * Manages 7 sub-views with a persistent right panel for product summary.
 */
public class InventarioController {

    // ---- Top bar ----
    @FXML
    private Button nuevoProductoBtn;

    @FXML
    private Button actualizarInventarioBtn;

    @FXML
    private Button exportarInventarioBtn;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> categoriaCombo;

    @FXML
    private ComboBox<String> stockCombo;

    @FXML
    private ComboBox<String> marcaCombo;

    @FXML
    private ComboBox<String> sucursalCombo;

    @FXML
    private ComboBox<String> proveedorCombo;

    @FXML
    private CheckBox soloStockCriticoCheck;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton subViewCatalogoBtn;

    @FXML
    private ToggleButton subViewMonturasBtn;

    @FXML
    private ToggleButton subViewLentesBtn;

    @FXML
    private ToggleButton subViewMovimientosBtn;

    @FXML
    private ToggleButton subViewReposicionBtn;

    @FXML
    private ToggleButton subViewRecepcionBtn;

    @FXML
    private ToggleButton subViewAnalisisBtn;

    // ---- Sub-view containers ----
    @FXML
    private VBox catalogoSection;

    @FXML
    private VBox monturasSection;

    @FXML
    private VBox lentesSection;

    @FXML
    private VBox movimientosSection;

    @FXML
    private VBox reposicionSection;

    @FXML
    private VBox recepcionSection;

    @FXML
    private VBox analisisSection;

    // ---- Catalogo table ----
    @FXML
    private TableView<InventarioRowModel.CatalogoRow> catalogoTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatReferencia;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatNombre;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatCategoria;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatMarca;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatSucursal;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatStock;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatPrecio;

    @FXML
    private TableColumn<InventarioRowModel.CatalogoRow, String> colCatEstado;

    @FXML
    private Label catalogoFooterLabel;

    // ---- Monturas table ----
    @FXML
    private TableView<InventarioRowModel.MonturaRow> monturasTable;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonReferencia;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonNombre;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonMarca;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonMaterial;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonColor;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonPrecio;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonStock;

    @FXML
    private TableColumn<InventarioRowModel.MonturaRow, String> colMonSucursal;

    @FXML
    private Button verMonturaBtn;

    @FXML
    private Button ajustarStockMonturaBtn;

    @FXML
    private Button verSucursalesBtn;

    // ---- Lentes table ----
    @FXML
    private TableView<InventarioRowModel.LenteRow> lentesTable;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenReferencia;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenNombre;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenTipo;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenIndice;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenTratamiento;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenStock;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenPrecio;

    @FXML
    private TableColumn<InventarioRowModel.LenteRow, String> colLenEstado;

    @FXML
    private Button verLenteBtn;

    @FXML
    private Button ajustarStockLenteBtn;

    // ---- Movimientos table ----
    @FXML
    private TableView<InventarioRowModel.MovimientoRow> movimientosTable;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovFecha;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovTipo;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovReferencia;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovProducto;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovCantidad;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovSucursal;

    @FXML
    private TableColumn<InventarioRowModel.MovimientoRow, String> colMovResponsable;

    @FXML
    private Button registrarAjusteBtn;

    @FXML
    private Button registrarTransferenciaBtn;

    @FXML
    private Button exportarMovimientosBtn;

    // ---- Reposicion KPIs ----
    @FXML
    private Label agotadosCountLabel;

    @FXML
    private Label bajoStockCountLabel;

    @FXML
    private Label enReposicionCountLabel;

    // ---- Reposicion table ----
    @FXML
    private TableView<InventarioRowModel.CriticoRow> reposicionTable;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepReferencia;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepNombre;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepCategoria;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepStockActual;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepStockMinimo;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepEstado;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepProveedor;

    @FXML
    private TableColumn<InventarioRowModel.CriticoRow, String> colRepSucursal;

    @FXML
    private Button generarReposicionBtn;

    @FXML
    private Button verProveedorBtn;

    @FXML
    private Button marcarPedidoBtn;

    // ---- Recepcion table ----
    @FXML
    private TableView<InventarioRowModel.RecepcionRow> recepcionTable;

    @FXML
    private TableColumn<InventarioRowModel.RecepcionRow, String> colRecReferencia;

    @FXML
    private TableColumn<InventarioRowModel.RecepcionRow, String> colRecFecha;

    @FXML
    private TableColumn<InventarioRowModel.RecepcionRow, String> colRecProveedor;

    @FXML
    private TableColumn<InventarioRowModel.RecepcionRow, String> colRecSucursal;

    @FXML
    private TableColumn<InventarioRowModel.RecepcionRow, String> colRecEstado;

    @FXML
    private TableColumn<InventarioRowModel.RecepcionRow, String> colRecResponsable;

    @FXML
    private Button registrarRecepcionBtn;

    @FXML
    private Button marcarCompletaBtn;

    @FXML
    private Button registrarDiferenciaBtn;

    // ---- Analisis KPIs ----
    @FXML
    private Label totalProductosLabel;

    @FXML
    private Label rotacionMediaLabel;

    @FXML
    private Label productosBajoStockLabel;

    // ---- Analisis table ----
    @FXML
    private TableView<InventarioRowModel.AnalisisRow> analisisTable;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaReferencia;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaNombre;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaCategoria;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaRotacion;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaUltimaSalida;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaEstado;

    @FXML
    private TableColumn<InventarioRowModel.AnalisisRow, String> colAnaObservacion;

    @FXML
    private Button exportarAnalisisBtn;

    @FXML
    private Button verProductoBtn;

    @FXML
    private Button marcarRevisionBtn;

    // ---- Summary panel ----
    @FXML
    private Label summaryReferencia;

    @FXML
    private Label summaryNombre;

    @FXML
    private Label summaryCategoria;

    @FXML
    private Label summaryMarca;

    @FXML
    private Label summaryEstadoStock;

    @FXML
    private Label summaryStockActual;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryUbicacion;

    @FXML
    private Label summaryPrecioVenta;

    @FXML
    private Label summaryUltimaSalida;

    @FXML
    private Label summaryProveedor;

    @FXML
    private Label summaryUltimaRecepcion;

    @FXML
    private Label summaryEstadoReposicion;

    @FXML
    private VBox summaryFieldsContainer;

    // ---- Facade ----
    private InventarioFacade facade;
    private InventarioFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new InventarioFacade(store);
        this.currentFilters = new InventarioFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupActionButtons();
        setupCatalogoTable();
        setupMonturasTable();
        setupLentesTable();
        setupMovimientosTable();
        setupReposicionTable();
        setupRecepcionTable();
        setupAnalisisTable();

        // Show catalogo by default
        showSubView("catalogo");
        loadCatalogo();
        loadMonturas();
        loadLentes();
        loadMovimientos();
        loadReposicion();
        loadRecepcion();
        loadAnalisis();
    }

    // ==================== Filter combos ====================

    private void setupFilterCombos() {
        if (categoriaCombo != null) {
            List<String> categorias = facade.getCategorias();
            String[] items = categorias.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Categoria", "Todas", items);
            replaceInParent(categoriaCombo, combo);
            categoriaCombo = combo;
            categoriaCombo.setOnAction(e -> applyFilters());
        }

        if (stockCombo != null) {
            List<String> estados = facade.getEstadosStock();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado stock", "Todos", items);
            replaceInParent(stockCombo, combo);
            stockCombo = combo;
            stockCombo.setOnAction(e -> applyFilters());
        }

        if (marcaCombo != null) {
            List<String> marcas = facade.getMarcas();
            String[] items = marcas.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Marca", "Todas", items);
            replaceInParent(marcaCombo, combo);
            marcaCombo = combo;
            marcaCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            List<String> sucursales = facade.getSucursales();
            String[] items = sucursales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", items);
            replaceInParent(sucursalCombo, combo);
            sucursalCombo = combo;
            sucursalCombo.setOnAction(e -> applyFilters());
        }

        if (proveedorCombo != null) {
            List<String> proveedores = facade.getProveedores();
            String[] items = proveedores.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Proveedor", "Todos", items);
            replaceInParent(proveedorCombo, combo);
            proveedorCombo = combo;
            proveedorCombo.setOnAction(e -> applyFilters());
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
        if (subViewCatalogoBtn != null) {
            subViewCatalogoBtn.setToggleGroup(group);
            subViewCatalogoBtn.setOnAction(e -> showSubView("catalogo"));
        }
        if (subViewMonturasBtn != null) {
            subViewMonturasBtn.setToggleGroup(group);
            subViewMonturasBtn.setOnAction(e -> showSubView("monturas"));
        }
        if (subViewLentesBtn != null) {
            subViewLentesBtn.setToggleGroup(group);
            subViewLentesBtn.setOnAction(e -> showSubView("lentes"));
        }
        if (subViewMovimientosBtn != null) {
            subViewMovimientosBtn.setToggleGroup(group);
            subViewMovimientosBtn.setOnAction(e -> showSubView("movimientos"));
        }
        if (subViewReposicionBtn != null) {
            subViewReposicionBtn.setToggleGroup(group);
            subViewReposicionBtn.setOnAction(e -> showSubView("reposicion"));
        }
        if (subViewRecepcionBtn != null) {
            subViewRecepcionBtn.setToggleGroup(group);
            subViewRecepcionBtn.setOnAction(e -> showSubView("recepcion"));
        }
        if (subViewAnalisisBtn != null) {
            subViewAnalisisBtn.setToggleGroup(group);
            subViewAnalisisBtn.setOnAction(e -> showSubView("analisis"));
        }
    }

    private void showSubView(String view) {
        boolean showCatalogo = "catalogo".equals(view);
        boolean showMonturas = "monturas".equals(view);
        boolean showLentes = "lentes".equals(view);
        boolean showMovimientos = "movimientos".equals(view);
        boolean showReposicion = "reposicion".equals(view);
        boolean showRecepcion = "recepcion".equals(view);
        boolean showAnalisis = "analisis".equals(view);

        if (catalogoSection != null) catalogoSection.setVisible(showCatalogo);
        if (catalogoSection != null) catalogoSection.setManaged(showCatalogo);
        if (monturasSection != null) monturasSection.setVisible(showMonturas);
        if (monturasSection != null) monturasSection.setManaged(showMonturas);
        if (lentesSection != null) lentesSection.setVisible(showLentes);
        if (lentesSection != null) lentesSection.setManaged(showLentes);
        if (movimientosSection != null) movimientosSection.setVisible(showMovimientos);
        if (movimientosSection != null) movimientosSection.setManaged(showMovimientos);
        if (reposicionSection != null) reposicionSection.setVisible(showReposicion);
        if (reposicionSection != null) reposicionSection.setManaged(showReposicion);
        if (recepcionSection != null) recepcionSection.setVisible(showRecepcion);
        if (recepcionSection != null) recepcionSection.setManaged(showRecepcion);
        if (analisisSection != null) analisisSection.setVisible(showAnalisis);
        if (analisisSection != null) analisisSection.setManaged(showAnalisis);
    }

    // ==================== Action buttons ====================

    private void setupActionButtons() {
        if (nuevoProductoBtn != null) {
            nuevoProductoBtn.setOnAction(e -> onNuevoProducto());
        }
        if (actualizarInventarioBtn != null) {
            actualizarInventarioBtn.setOnAction(e -> refreshAll());
        }
        if (exportarInventarioBtn != null) {
            exportarInventarioBtn.setOnAction(e -> onExportarInventario());
        }

        // Monturas
        if (verMonturaBtn != null) verMonturaBtn.setOnAction(e -> onVerMontura());
        if (ajustarStockMonturaBtn != null) ajustarStockMonturaBtn.setOnAction(e -> onAjustarStock());
        if (verSucursalesBtn != null) verSucursalesBtn.setOnAction(e -> onVerSucursales());

        // Lentes
        if (verLenteBtn != null) verLenteBtn.setOnAction(e -> onVerLente());
        if (ajustarStockLenteBtn != null) ajustarStockLenteBtn.setOnAction(e -> onAjustarStock());

        // Movimientos
        if (registrarAjusteBtn != null) registrarAjusteBtn.setOnAction(e -> onRegistrarAjuste());
        if (registrarTransferenciaBtn != null) registrarTransferenciaBtn.setOnAction(e -> onRegistrarTransferencia());
        if (exportarMovimientosBtn != null) exportarMovimientosBtn.setOnAction(e -> onExportarMovimientos());

        // Reposicion
        if (generarReposicionBtn != null) generarReposicionBtn.setOnAction(e -> onGenerarReposicion());
        if (verProveedorBtn != null) verProveedorBtn.setOnAction(e -> onVerProveedor());
        if (marcarPedidoBtn != null) marcarPedidoBtn.setOnAction(e -> onMarcarPedido());

        // Recepcion
        if (registrarRecepcionBtn != null) registrarRecepcionBtn.setOnAction(e -> onRegistrarRecepcion());
        if (marcarCompletaBtn != null) marcarCompletaBtn.setOnAction(e -> onMarcarCompleta());
        if (registrarDiferenciaBtn != null) registrarDiferenciaBtn.setOnAction(e -> onRegistrarDiferencia());

        // Analisis
        if (exportarAnalisisBtn != null) exportarAnalisisBtn.setOnAction(e -> onExportarAnalisis());
        if (verProductoBtn != null) verProductoBtn.setOnAction(e -> onVerProducto());
        if (marcarRevisionBtn != null) marcarRevisionBtn.setOnAction(e -> onMarcarRevision());
    }

    // ==================== Catalogo general ====================

    private void setupCatalogoTable() {
        if (colCatReferencia != null) colCatReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colCatNombre != null) colCatNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colCatCategoria != null) colCatCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colCatMarca != null) colCatMarca.setCellValueFactory(data -> data.getValue().marcaProperty());
        if (colCatSucursal != null) colCatSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colCatStock != null) colCatStock.setCellValueFactory(data -> data.getValue().stockProperty());
        if (colCatPrecio != null) colCatPrecio.setCellValueFactory(data -> data.getValue().precioProperty());
        if (colCatEstado != null) colCatEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        // Status badge for estado column
        if (colCatEstado != null) {
            colCatEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                                case "bajo stock" -> StatusBadgeModel.warning(item);
                                case "agotado" -> StatusBadgeModel.danger(item);
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

        if (catalogoTable != null) {
            catalogoTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> updateSummaryPanelFromCatalogo(newVal));
        }
    }

    private void loadCatalogo() {
        PageResult<InventarioRowModel.CatalogoRow> pageResult = facade.getCatalogo(currentFilters, currentPageRequest);
        ObservableList<InventarioRowModel.CatalogoRow> data = FXCollections.observableArrayList(pageResult.getItems());
        if (catalogoTable != null) {
            catalogoTable.setItems(data);
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
        if (catalogoFooterLabel != null) {
            int shown = pageResult.getItems().size();
            int total = pageResult.getTotalItems();
            catalogoFooterLabel.setText(String.format("Mostrando %d de %d productos", shown, total));
        }
        this.currentPageIndex = pageResult.getPageIndex();
        this.pageSize = pageResult.getPageSize();

        if (!pageResult.isEmpty() && catalogoTable != null) {
            catalogoTable.getSelectionModel().selectFirst();
        }
    }

    private void updateSummaryPanelFromCatalogo(InventarioRowModel.CatalogoRow row) {
        if (row == null) {
            clearSummary();
            return;
        }
        DemoStore store = App.getDemoStore();
        Producto producto = store.productos.stream()
                .filter(p -> p.getReferencia() != null && p.getReferencia().equals(row.referencia()))
                .findFirst()
                .orElse(null);
        if (producto == null) {
            if (summaryNombre != null) summaryNombre.setText(row.nombre());
            if (summaryReferencia != null) summaryReferencia.setText(row.referencia());
            return;
        }
        updateSummaryPanel(producto);
    }

    // ==================== Monturas ====================

    private void setupMonturasTable() {
        if (colMonReferencia != null) colMonReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colMonNombre != null) colMonNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colMonMarca != null) colMonMarca.setCellValueFactory(data -> data.getValue().marcaProperty());
        if (colMonMaterial != null) colMonMaterial.setCellValueFactory(data -> data.getValue().materialProperty());
        if (colMonColor != null) colMonColor.setCellValueFactory(data -> data.getValue().colorProperty());
        if (colMonPrecio != null) colMonPrecio.setCellValueFactory(data -> data.getValue().precioProperty());
        if (colMonStock != null) colMonStock.setCellValueFactory(data -> data.getValue().stockProperty());
        if (colMonSucursal != null) colMonSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (monturasTable != null) {
            monturasTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            DemoStore store = App.getDemoStore();
                            Producto p = store.productos.stream()
                                    .filter(pr -> pr.getReferencia().equals(newVal.referencia()))
                                    .findFirst().orElse(null);
                            if (p != null) updateSummaryPanel(p);
                        }
                    });
        }
    }

    private void loadMonturas() {
        List<InventarioRowModel.MonturaRow> rows = facade.getMonturas(currentFilters);
        if (monturasTable != null) {
            monturasTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    // ==================== Lentes ====================

    private void setupLentesTable() {
        if (colLenReferencia != null) colLenReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colLenNombre != null) colLenNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colLenTipo != null) colLenTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colLenIndice != null) colLenIndice.setCellValueFactory(data -> data.getValue().indiceProperty());
        if (colLenTratamiento != null) colLenTratamiento.setCellValueFactory(data -> data.getValue().tratamientoProperty());
        if (colLenStock != null) colLenStock.setCellValueFactory(data -> data.getValue().stockProperty());
        if (colLenPrecio != null) colLenPrecio.setCellValueFactory(data -> data.getValue().precioBaseProperty());
        if (colLenEstado != null) colLenEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colLenEstado != null) {
            colLenEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                                case "bajo stock" -> StatusBadgeModel.warning(item);
                                case "agotado" -> StatusBadgeModel.danger(item);
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

        if (lentesTable != null) {
            lentesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            DemoStore store = App.getDemoStore();
                            Producto p = store.productos.stream()
                                    .filter(pr -> pr.getReferencia().equals(newVal.referencia()))
                                    .findFirst().orElse(null);
                            if (p != null) updateSummaryPanel(p);
                        }
                    });
        }
    }

    private void loadLentes() {
        List<InventarioRowModel.LenteRow> rows = facade.getLentes(currentFilters);
        if (lentesTable != null) {
            lentesTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    // ==================== Movimientos ====================

    private void setupMovimientosTable() {
        if (colMovFecha != null) colMovFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colMovTipo != null) colMovTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colMovReferencia != null) colMovReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colMovProducto != null) colMovProducto.setCellValueFactory(data -> data.getValue().productoProperty());
        if (colMovCantidad != null) colMovCantidad.setCellValueFactory(data -> data.getValue().cantidadProperty());
        if (colMovSucursal != null) colMovSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colMovResponsable != null) colMovResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());
    }

    private void loadMovimientos() {
        List<InventarioRowModel.MovimientoRow> rows = facade.getMovimientos(currentFilters);
        if (movimientosTable != null) {
            movimientosTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    // ==================== Reposicion y stock critico ====================

    private void setupReposicionTable() {
        if (colRepReferencia != null) colRepReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colRepNombre != null) colRepNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colRepCategoria != null) colRepCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colRepStockActual != null) colRepStockActual.setCellValueFactory(data -> data.getValue().stockActualProperty());
        if (colRepStockMinimo != null) colRepStockMinimo.setCellValueFactory(data -> data.getValue().stockMinimoProperty());
        if (colRepEstado != null) colRepEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colRepProveedor != null) colRepProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colRepSucursal != null) colRepSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (colRepEstado != null) {
            colRepEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                                case "agotado" -> StatusBadgeModel.danger(item);
                                case "bajo stock" -> StatusBadgeModel.warning(item);
                                case "en reposicion" -> StatusBadgeModel.info(item);
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

    private void loadReposicion() {
        InventarioFacade.StatsCritico stats = facade.getStatsCritico();
        if (agotadosCountLabel != null) agotadosCountLabel.setText(String.valueOf(stats.agotados()));
        if (bajoStockCountLabel != null) bajoStockCountLabel.setText(String.valueOf(stats.bajoStock()));
        if (enReposicionCountLabel != null) enReposicionCountLabel.setText(String.valueOf(stats.enReposicion()));

        List<InventarioRowModel.CriticoRow> rows = facade.getStockCritico();
        if (reposicionTable != null) {
            reposicionTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    // ==================== Recepcion y abastecimiento ====================

    private void setupRecepcionTable() {
        if (colRecReferencia != null) colRecReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colRecFecha != null) colRecFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colRecProveedor != null) colRecProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colRecSucursal != null) colRecSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colRecEstado != null) colRecEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
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
                                case "completado" -> StatusBadgeModel.success(item);
                                case "pendiente" -> StatusBadgeModel.warning(item);
                                case "en transito" -> StatusBadgeModel.info(item);
                                case "parcial" -> StatusBadgeModel.warning(item);
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
        List<InventarioRowModel.RecepcionRow> rows = facade.getRecepciones();
        if (recepcionTable != null) {
            recepcionTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    // ==================== Historico y analisis ====================

    private void setupAnalisisTable() {
        if (colAnaReferencia != null) colAnaReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colAnaNombre != null) colAnaNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colAnaCategoria != null) colAnaCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colAnaRotacion != null) colAnaRotacion.setCellValueFactory(data -> data.getValue().rotacionProperty());
        if (colAnaUltimaSalida != null) colAnaUltimaSalida.setCellValueFactory(data -> data.getValue().ultimaSalidaProperty());
        if (colAnaEstado != null) colAnaEstado.setCellValueFactory(data -> data.getValue().estadoActualProperty());
        if (colAnaObservacion != null) colAnaObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());
    }

    private void loadAnalisis() {
        DemoStore store = App.getDemoStore();
        int totalProductos = store.productos.size();
        if (totalProductosLabel != null) totalProductosLabel.setText(String.valueOf(totalProductos));
        if (rotacionMediaLabel != null) rotacionMediaLabel.setText("Media");
        int bajoStock = (int) store.productos.stream().filter(p -> p.getStock() <= p.getStockMinimo()).count();
        if (productosBajoStockLabel != null) productosBajoStockLabel.setText(String.valueOf(bajoStock));

        List<InventarioRowModel.AnalisisRow> rows = facade.getAnalisis();
        if (analisisTable != null) {
            analisisTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    // ==================== Summary panel ====================

    private void updateSummaryPanel(Producto producto) {
        InventarioSummaryModel summary = facade.buildSummary(producto);

        if (summaryReferencia != null) summaryReferencia.setText(summary.referencia());
        if (summaryNombre != null) summaryNombre.setText(summary.nombre());
        if (summaryCategoria != null) summaryCategoria.setText(summary.categoria());
        if (summaryMarca != null) summaryMarca.setText(summary.marca());
        if (summaryEstadoStock != null) summaryEstadoStock.setText(summary.estadoStock());
        if (summaryStockActual != null) summaryStockActual.setText(summary.stockActual());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryUbicacion != null) summaryUbicacion.setText(summary.ubicacion());
        if (summaryPrecioVenta != null) summaryPrecioVenta.setText(summary.precioVenta());
        if (summaryUltimaSalida != null) summaryUltimaSalida.setText(summary.ultimaSalida());
        if (summaryProveedor != null) summaryProveedor.setText(summary.proveedorPrincipal());
        if (summaryUltimaRecepcion != null) summaryUltimaRecepcion.setText(summary.ultimaRecepcion());
        if (summaryEstadoReposicion != null) summaryEstadoReposicion.setText(summary.estadoReposicion());

        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Referencia", summary.referencia()),
                    new SummaryFieldModel("Nombre", summary.nombre()),
                    new SummaryFieldModel("Categoria", summary.categoria()),
                    new SummaryFieldModel("Marca", summary.marca()),
                    new SummaryFieldModel("Estado stock", summary.estadoStock()),
                    new SummaryFieldModel("Stock actual", summary.stockActual()),
                    new SummaryFieldModel("Sucursal", summary.sucursal()),
                    new SummaryFieldModel("Ubicacion", summary.ubicacion()),
                    new SummaryFieldModel("Precio venta", summary.precioVenta(), true),
                    new SummaryFieldModel("Ultima salida", summary.ultimaSalida()),
                    new SummaryFieldModel("Proveedor principal", summary.proveedorPrincipal()),
                    new SummaryFieldModel("Ultima recepcion", summary.ultimaRecepcion()),
                    new SummaryFieldModel("Estado reposicion", summary.estadoReposicion())
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

    private void clearSummary() {
        if (summaryReferencia != null) summaryReferencia.setText("Sin seleccion");
        if (summaryNombre != null) summaryNombre.setText("");
        if (summaryCategoria != null) summaryCategoria.setText("");
        if (summaryMarca != null) summaryMarca.setText("");
        if (summaryEstadoStock != null) summaryEstadoStock.setText("");
        if (summaryStockActual != null) summaryStockActual.setText("");
        if (summarySucursal != null) summarySucursal.setText("");
        if (summaryUbicacion != null) summaryUbicacion.setText("");
        if (summaryPrecioVenta != null) summaryPrecioVenta.setText("");
        if (summaryUltimaSalida != null) summaryUltimaSalida.setText("");
        if (summaryProveedor != null) summaryProveedor.setText("");
        if (summaryUltimaRecepcion != null) summaryUltimaRecepcion.setText("");
        if (summaryEstadoReposicion != null) summaryEstadoReposicion.setText("");
        if (summaryFieldsContainer != null) summaryFieldsContainer.getChildren().clear();
    }

    // ==================== Filter actions ====================

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadCatalogo();
    }

    private void applyFilters() {
        currentFilters = new InventarioFilters(
                searchField != null ? searchField.getText() : "",
                categoriaCombo != null && categoriaCombo.getValue() != null ? categoriaCombo.getValue() : "Todas",
                stockCombo != null && stockCombo.getValue() != null ? stockCombo.getValue() : "Todos",
                marcaCombo != null && marcaCombo.getValue() != null ? marcaCombo.getValue() : "Todas",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                proveedorCombo != null && proveedorCombo.getValue() != null ? proveedorCombo.getValue() : "Todos",
                soloStockCriticoCheck != null && soloStockCriticoCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadCatalogo();
        loadMonturas();
        loadLentes();
        loadMovimientos();
        loadReposicion();
    }

    private void clearFilters() {
        currentFilters = new InventarioFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (categoriaCombo != null) categoriaCombo.getSelectionModel().selectFirst();
        if (stockCombo != null) stockCombo.getSelectionModel().selectFirst();
        if (marcaCombo != null) marcaCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (proveedorCombo != null) proveedorCombo.getSelectionModel().selectFirst();
        if (soloStockCriticoCheck != null) soloStockCriticoCheck.setSelected(false);

        loadCatalogo();
        loadMonturas();
        loadLentes();
        loadMovimientos();
        loadReposicion();
    }

    private void refreshAll() {
        loadCatalogo();
        loadMonturas();
        loadLentes();
        loadMovimientos();
        loadReposicion();
        loadRecepcion();
        loadAnalisis();
    }

    // ==================== Placeholder actions ====================

    private void onNuevoProducto() { }
    private void onExportarInventario() { }
    private void onVerMontura() { }
    private void onAjustarStock() { }
    private void onVerSucursales() { }
    private void onVerLente() { }
    private void onRegistrarAjuste() { }
    private void onRegistrarTransferencia() { }
    private void onExportarMovimientos() { }
    private void onGenerarReposicion() { }
    private void onVerProveedor() { }
    private void onMarcarPedido() { }
    private void onRegistrarRecepcion() { }
    private void onMarcarCompleta() { }
    private void onRegistrarDiferencia() { }
    private void onExportarAnalisis() { }
    private void onVerProducto() { }
    private void onMarcarRevision() { }
}

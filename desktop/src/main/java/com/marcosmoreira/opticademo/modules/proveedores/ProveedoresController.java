package com.marcosmoreira.opticademo.modules.proveedores;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.proveedor.Proveedor;
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
 * Controller del modulo de Proveedores del sistema optico.
 * <p>
 * Gestiona siete sub-vistas para la administracion integral de proveedores:
 * Directorio de proveedores, Perfil comercial, Catalogo vinculado, Ordenes y abastecimiento,
 * Recepciones e incidencias, Desempeno del proveedor, e Historico de operaciones.
 * </p>
 * <p>
 * La seleccion de un proveedor en el directorio actualiza automaticamente todas las demas
 * sub-vistas y el panel de resumen derecho con la informacion comercial detallada.
 * La fachada {@link ProveedoresFacade} proporciona acceso a los datos de proveedores
 * almacenados en el {@link DemoStore}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ProveedoresFacade
 * @see ProveedoresFilters
 * @see ProveedoresSummaryModel
 */
public class ProveedoresController {

    // ---- Top bar ----

    /** Boton para registrar un nuevo proveedor en el sistema. */
    @FXML
    private Button nuevoProveedorBtn;

    /** Boton para actualizar la lista de proveedores. */
    @FXML
    private Button actualizarProveedoresBtn;

    /** Boton para exportar el directorio de proveedores. */
    @FXML
    private Button exportarDirectorioBtn;

    // ---- Filters ----

    /** Campo de busqueda por nombre o razon social del proveedor. */
    @FXML
    private TextField searchField;

    /** ComboBox para filtrar por tipo de proveedor (laboratorio, monturas, lentes, etc.). */
    @FXML
    private ComboBox<String> tipoCombo;

    /** ComboBox para filtrar por estado del proveedor. */
    @FXML
    private ComboBox<String> estadoCombo;

    /** ComboBox para filtrar por categoria de productos abastecidos. */
    @FXML
    private ComboBox<String> categoriaCombo;

    /** ComboBox para filtrar por sucursal atendida. */
    @FXML
    private ComboBox<String> sucursalCombo;

    /** CheckBox para mostrar solo proveedores con incidencias recientes. */
    @FXML
    private CheckBox soloConIncidenciasCheck;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----

    /** Toggle button para la sub-vista Directorio de proveedores. */
    @FXML
    private ToggleButton subViewDirectorioBtn;

    /** Toggle button para la sub-vista Perfil comercial. */
    @FXML
    private ToggleButton subViewPerfilBtn;

    /** Toggle button para la sub-vista Catalogo vinculado. */
    @FXML
    private ToggleButton subViewCatalogoBtn;

    /** Toggle button para la sub-vista Ordenes y abastecimiento. */
    @FXML
    private ToggleButton subViewOrdenesBtn;

    /** Toggle button para la sub-vista Recepciones e incidencias. */
    @FXML
    private ToggleButton subViewRecepcionesBtn;

    /** Toggle button para la sub-vista Desempeno del proveedor. */
    @FXML
    private ToggleButton subViewDesempenoBtn;

    /** Toggle button para la sub-vista Historico. */
    @FXML
    private ToggleButton subViewHistoricoBtn;

    // ---- Sub-view containers ----
    @FXML
    private VBox directorioSection;

    @FXML
    private VBox perfilSection;

    @FXML
    private VBox catalogoSection;

    @FXML
    private VBox ordenesSection;

    @FXML
    private VBox recepcionesSection;

    @FXML
    private VBox desempenoSection;

    @FXML
    private VBox historicoSection;

    // ---- Directorio table ----
    @FXML
    private TableView<ProveedoresRowModel.DirectorioRow> directorioTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<ProveedoresRowModel.DirectorioRow, String> colDirProveedor;

    @FXML
    private TableColumn<ProveedoresRowModel.DirectorioRow, String> colDirTipo;

    @FXML
    private TableColumn<ProveedoresRowModel.DirectorioRow, String> colDirContacto;

    @FXML
    private TableColumn<ProveedoresRowModel.DirectorioRow, String> colDirTiempoEstimado;

    @FXML
    private TableColumn<ProveedoresRowModel.DirectorioRow, String> colDirEstado;

    @FXML
    private TableColumn<ProveedoresRowModel.DirectorioRow, String> colDirCobertura;

    // ---- Perfil table ----
    @FXML
    private TableView<ProveedoresRowModel.PerfilRow> perfilTable;

    @FXML
    private TableColumn<ProveedoresRowModel.PerfilRow, String> colPerfilCampo;

    @FXML
    private TableColumn<ProveedoresRowModel.PerfilRow, String> colPerfilValor;

    // ---- Catalogo table ----
    @FXML
    private TableView<ProveedoresRowModel.CatalogoRow> catalogoTable;

    @FXML
    private TableColumn<ProveedoresRowModel.CatalogoRow, String> colCatReferencia;

    @FXML
    private TableColumn<ProveedoresRowModel.CatalogoRow, String> colCatNombre;

    @FXML
    private TableColumn<ProveedoresRowModel.CatalogoRow, String> colCatCategoria;

    @FXML
    private TableColumn<ProveedoresRowModel.CatalogoRow, String> colCatMarca;

    @FXML
    private TableColumn<ProveedoresRowModel.CatalogoRow, String> colCatEstado;

    // ---- Ordenes table ----
    @FXML
    private TableView<ProveedoresRowModel.OrdenRow> ordenesTable;

    @FXML
    private TableColumn<ProveedoresRowModel.OrdenRow, String> colOrdOrden;

    @FXML
    private TableColumn<ProveedoresRowModel.OrdenRow, String> colOrdFecha;

    @FXML
    private TableColumn<ProveedoresRowModel.OrdenRow, String> colOrdEstado;

    @FXML
    private TableColumn<ProveedoresRowModel.OrdenRow, String> colOrdTotalItems;

    @FXML
    private TableColumn<ProveedoresRowModel.OrdenRow, String> colOrdRecepcion;

    @FXML
    private TableColumn<ProveedoresRowModel.OrdenRow, String> colOrdObservacion;

    // ---- Recepciones table ----
    @FXML
    private TableView<ProveedoresRowModel.RecepcionRow> recepcionesTable;

    @FXML
    private TableColumn<ProveedoresRowModel.RecepcionRow, String> colRecFecha;

    @FXML
    private TableColumn<ProveedoresRowModel.RecepcionRow, String> colRecTipo;

    @FXML
    private TableColumn<ProveedoresRowModel.RecepcionRow, String> colRecOrden;

    @FXML
    private TableColumn<ProveedoresRowModel.RecepcionRow, String> colRecEstado;

    @FXML
    private TableColumn<ProveedoresRowModel.RecepcionRow, String> colRecResponsable;

    // ---- Desempeno table ----
    @FXML
    private TableView<ProveedoresRowModel.DesempenoRow> desempenoTable;

    @FXML
    private TableColumn<ProveedoresRowModel.DesempenoRow, String> colDesIndicador;

    @FXML
    private TableColumn<ProveedoresRowModel.DesempenoRow, String> colDesValor;

    @FXML
    private TableColumn<ProveedoresRowModel.DesempenoRow, String> colDesEstado;

    @FXML
    private TableColumn<ProveedoresRowModel.DesempenoRow, String> colDesObservacion;

    // ---- Historico table ----
    @FXML
    private TableView<ProveedoresRowModel.HistoricoRow> historicoTable;

    @FXML
    private TableColumn<ProveedoresRowModel.HistoricoRow, String> colHisFecha;

    @FXML
    private TableColumn<ProveedoresRowModel.HistoricoRow, String> colHisProveedor;

    @FXML
    private TableColumn<ProveedoresRowModel.HistoricoRow, String> colHisTipoRegistro;

    @FXML
    private TableColumn<ProveedoresRowModel.HistoricoRow, String> colHisReferencia;

    @FXML
    private TableColumn<ProveedoresRowModel.HistoricoRow, String> colHisEstado;

    @FXML
    private TableColumn<ProveedoresRowModel.HistoricoRow, String> colHisObservacion;

    // ---- Summary panel ----
    @FXML
    private Label summaryProveedor;

    @FXML
    private Label summaryTipoPrincipal;

    @FXML
    private Label summaryEstado;

    @FXML
    private Label summaryContacto;

    @FXML
    private Label summaryTelefono;

    @FXML
    private Label summaryCorreo;

    @FXML
    private Label summaryCategoriaAbastecida;

    @FXML
    private Label summarySucursalesAtendidas;

    @FXML
    private Label summaryUltimaRecepcion;

    @FXML
    private Label summaryTiempoEstimado;

    @FXML
    private Label summaryIncidenciasRecientes;

    @FXML
    private Label summaryObservacionClave;

    @FXML
    private VBox summaryFieldsContainer;

    // ---- Facade ----
    /** Fachada que proporciona acceso a los datos de proveedores del sistema. */
    private ProveedoresFacade facade;
    private ProveedoresFilters currentFilters;
    private com.marcosmoreira.opticademo.shared.query.PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;
    /** Referencia al proveedor actualmente seleccionado. */
    private Proveedor selectedProveedor;

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia {@link ProveedoresFacade} con el {@link DemoStore} global, configura
     * los combos de filtrado, el sistema de toggle entre sub-vistas, las columnas
     * de cada tabla con sus cell factories, y carga el directorio de proveedores.
     * </p>
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new ProveedoresFacade(store);
        this.currentFilters = new ProveedoresFilters();
        this.currentPageRequest = new com.marcosmoreira.opticademo.shared.query.PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupActionButtons();
        setupDirectorioTable();
        setupPerfilTable();
        setupCatalogoTable();
        setupOrdenesTable();
        setupRecepcionesTable();
        setupDesempenoTable();
        setupHistoricoTable();

        // Show directorio by default
        showSubView("directorio");
        loadDirectorio();
        loadPerfil(null);
        loadCatalogo(null);
        loadOrdenes(null);
        loadRecepciones(null);
        loadDesempeno(null);
        loadHistorico();
    }

    // ==================== Filter combos ====================

    private void setupFilterCombos() {
        if (tipoCombo != null) {
            List<String> tipos = facade.getTiposProveedor();
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

        if (categoriaCombo != null) {
            List<String> categorias = facade.getCategoriasAbastecidas();
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
        if (subViewDirectorioBtn != null) {
            subViewDirectorioBtn.setToggleGroup(group);
            subViewDirectorioBtn.setOnAction(e -> showSubView("directorio"));
        }
        if (subViewPerfilBtn != null) {
            subViewPerfilBtn.setToggleGroup(group);
            subViewPerfilBtn.setOnAction(e -> showSubView("perfil"));
        }
        if (subViewCatalogoBtn != null) {
            subViewCatalogoBtn.setToggleGroup(group);
            subViewCatalogoBtn.setOnAction(e -> showSubView("catalogo"));
        }
        if (subViewOrdenesBtn != null) {
            subViewOrdenesBtn.setToggleGroup(group);
            subViewOrdenesBtn.setOnAction(e -> showSubView("ordenes"));
        }
        if (subViewRecepcionesBtn != null) {
            subViewRecepcionesBtn.setToggleGroup(group);
            subViewRecepcionesBtn.setOnAction(e -> showSubView("recepciones"));
        }
        if (subViewDesempenoBtn != null) {
            subViewDesempenoBtn.setToggleGroup(group);
            subViewDesempenoBtn.setOnAction(e -> showSubView("desempeno"));
        }
        if (subViewHistoricoBtn != null) {
            subViewHistoricoBtn.setToggleGroup(group);
            subViewHistoricoBtn.setOnAction(e -> showSubView("historico"));
        }
    }

    private void showSubView(String view) {
        boolean showDirectorio = "directorio".equals(view);
        boolean showPerfil = "perfil".equals(view);
        boolean showCatalogo = "catalogo".equals(view);
        boolean showOrdenes = "ordenes".equals(view);
        boolean showRecepciones = "recepciones".equals(view);
        boolean showDesempeno = "desempeno".equals(view);
        boolean showHistorico = "historico".equals(view);

        if (directorioSection != null) directorioSection.setVisible(showDirectorio);
        if (directorioSection != null) directorioSection.setManaged(showDirectorio);
        if (perfilSection != null) perfilSection.setVisible(showPerfil);
        if (perfilSection != null) perfilSection.setManaged(showPerfil);
        if (catalogoSection != null) catalogoSection.setVisible(showCatalogo);
        if (catalogoSection != null) catalogoSection.setManaged(showCatalogo);
        if (ordenesSection != null) ordenesSection.setVisible(showOrdenes);
        if (ordenesSection != null) ordenesSection.setManaged(showOrdenes);
        if (recepcionesSection != null) recepcionesSection.setVisible(showRecepciones);
        if (recepcionesSection != null) recepcionesSection.setManaged(showRecepciones);
        if (desempenoSection != null) desempenoSection.setVisible(showDesempeno);
        if (desempenoSection != null) desempenoSection.setManaged(showDesempeno);
        if (historicoSection != null) historicoSection.setVisible(showHistorico);
        if (historicoSection != null) historicoSection.setManaged(showHistorico);
    }

    // ==================== Action buttons ====================

    private void setupActionButtons() {
        if (nuevoProveedorBtn != null) {
            nuevoProveedorBtn.setOnAction(e -> onNuevoProveedor());
        }
        if (actualizarProveedoresBtn != null) {
            actualizarProveedoresBtn.setOnAction(e -> refreshAll());
        }
        if (exportarDirectorioBtn != null) {
            exportarDirectorioBtn.setOnAction(e -> onExportarDirectorio());
        }
    }

    // ==================== Apply filters ====================

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new com.marcosmoreira.opticademo.shared.query.PageRequest(currentPageIndex, pageSize);
        loadDirectorio();
    }

    private void applyFilters() {
        currentFilters.setSearchText(searchField != null ? searchField.getText() : "");
        currentFilters.setTipo(tipoCombo != null ? (String) tipoCombo.getValue() : "Todos");
        currentFilters.setEstado(estadoCombo != null ? (String) estadoCombo.getValue() : "Todos");
        currentFilters.setCategoria(categoriaCombo != null ? (String) categoriaCombo.getValue() : "Todas");
        currentFilters.setSucursal(sucursalCombo != null ? (String) sucursalCombo.getValue() : "Todas");
        currentFilters.setSoloConIncidencias(soloConIncidenciasCheck != null && soloConIncidenciasCheck.isSelected());

        loadDirectorio();
        loadHistorico();
    }

    private void clearFilters() {
        currentFilters = new ProveedoresFilters();
        if (searchField != null) searchField.setText("");
        if (tipoCombo != null) tipoCombo.setValue("Todos");
        if (estadoCombo != null) estadoCombo.setValue("Todos");
        if (categoriaCombo != null) categoriaCombo.setValue("Todas");
        if (sucursalCombo != null) sucursalCombo.setValue("Todas");
        if (soloConIncidenciasCheck != null) soloConIncidenciasCheck.setSelected(false);

        loadDirectorio();
        loadHistorico();
    }

    private void refreshAll() {
        loadDirectorio();
        loadPerfil(selectedProveedor);
        loadCatalogo(selectedProveedor != null ? selectedProveedor.getId() : null);
        loadOrdenes(selectedProveedor != null ? selectedProveedor.getId() : null);
        loadRecepciones(selectedProveedor != null ? selectedProveedor.getId() : null);
        loadDesempeno(selectedProveedor != null ? selectedProveedor.getId() : null);
        loadHistorico();
    }

    // ==================== Directorio ====================

    private void setupDirectorioTable() {
        if (colDirProveedor != null) colDirProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colDirTipo != null) colDirTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colDirContacto != null) colDirContacto.setCellValueFactory(data -> data.getValue().contactoProperty());
        if (colDirTiempoEstimado != null) colDirTiempoEstimado.setCellValueFactory(data -> data.getValue().tiempoEstimadoProperty());
        if (colDirEstado != null) colDirEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colDirCobertura != null) colDirCobertura.setCellValueFactory(data -> data.getValue().coberturaProperty());

        // Status badge for estado column
        if (colDirEstado != null) {
            colDirEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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

        if (directorioTable != null) {
            directorioTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> updateSummaryPanelFromDirectorio(newVal));
        }
    }

    private void loadDirectorio() {
        List<ProveedoresRowModel.DirectorioRow> rows = facade.getDirectorio(currentFilters);
        ObservableList<ProveedoresRowModel.DirectorioRow> data = FXCollections.observableArrayList(rows);
        if (directorioTable != null) {
            directorioTable.setItems(data);
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
        if (!rows.isEmpty() && directorioTable != null) {
            directorioTable.getSelectionModel().selectFirst();
        }
    }

    private void updateSummaryPanelFromDirectorio(ProveedoresRowModel.DirectorioRow row) {
        if (row == null) {
            clearSummary();
            return;
        }
        DemoStore store = App.getDemoStore();
        Proveedor proveedor = store.proveedores.stream()
                .filter(p -> p.getNombreComercial() != null && p.getNombreComercial().equals(row.proveedor()))
                .findFirst()
                .orElse(null);
        this.selectedProveedor = proveedor;
        if (proveedor == null) {
            if (summaryProveedor != null) summaryProveedor.setText(row.proveedor());
            return;
        }
        updateSummaryPanel(proveedor);
        loadPerfil(proveedor);
        loadCatalogo(proveedor.getId());
        loadOrdenes(proveedor.getId());
        loadRecepciones(proveedor.getId());
        loadDesempeno(proveedor.getId());
    }

    // ==================== Perfil comercial ====================

    private void setupPerfilTable() {
        if (colPerfilCampo != null) colPerfilCampo.setCellValueFactory(data -> data.getValue().campoProperty());
        if (colPerfilValor != null) colPerfilValor.setCellValueFactory(data -> data.getValue().valorProperty());
    }

    private void loadPerfil(Proveedor proveedor) {
        List<ProveedoresRowModel.PerfilRow> rows = facade.getPerfilComercial(proveedor);
        ObservableList<ProveedoresRowModel.PerfilRow> data = FXCollections.observableArrayList(rows);
        if (perfilTable != null) {
            perfilTable.setItems(data);
        }
    }

    // ==================== Catalogo vinculado ====================

    private void setupCatalogoTable() {
        if (colCatReferencia != null) colCatReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
        if (colCatNombre != null) colCatNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colCatCategoria != null) colCatCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        if (colCatMarca != null) colCatMarca.setCellValueFactory(data -> data.getValue().marcaProperty());
        if (colCatEstado != null) colCatEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

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
                                case "bajo pedido" -> StatusBadgeModel.warning(item);
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

    private void loadCatalogo(String proveedorId) {
        List<ProveedoresRowModel.CatalogoRow> rows = proveedorId != null
                ? facade.getCatalogoVinculado(proveedorId)
                : List.of();
        ObservableList<ProveedoresRowModel.CatalogoRow> data = FXCollections.observableArrayList(rows);
        if (catalogoTable != null) {
            catalogoTable.setItems(data);
        }
    }

    // ==================== Ordenes y abastecimiento ====================

    private void setupOrdenesTable() {
        if (colOrdOrden != null) colOrdOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        if (colOrdFecha != null) colOrdFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colOrdEstado != null) colOrdEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colOrdTotalItems != null) colOrdTotalItems.setCellValueFactory(data -> data.getValue().totalItemsProperty());
        if (colOrdRecepcion != null) colOrdRecepcion.setCellValueFactory(data -> data.getValue().recepcionProperty());
        if (colOrdObservacion != null) colOrdObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

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

    private void loadOrdenes(String proveedorId) {
        List<ProveedoresRowModel.OrdenRow> rows = proveedorId != null
                ? facade.getOrdenes(proveedorId)
                : List.of();
        ObservableList<ProveedoresRowModel.OrdenRow> data = FXCollections.observableArrayList(rows);
        if (ordenesTable != null) {
            ordenesTable.setItems(data);
        }
    }

    // ==================== Recepciones e incidencias ====================

    private void setupRecepcionesTable() {
        if (colRecFecha != null) colRecFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colRecTipo != null) colRecTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colRecOrden != null) colRecOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
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
                                case "completada" -> StatusBadgeModel.success(item);
                                case "con diferencia" -> StatusBadgeModel.warning(item);
                                case "resuelta" -> StatusBadgeModel.success(item);
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

    private void loadRecepciones(String proveedorId) {
        List<ProveedoresRowModel.RecepcionRow> rows = proveedorId != null
                ? facade.getRecepciones(proveedorId)
                : List.of();
        ObservableList<ProveedoresRowModel.RecepcionRow> data = FXCollections.observableArrayList(rows);
        if (recepcionesTable != null) {
            recepcionesTable.setItems(data);
        }
    }

    // ==================== Desempeno del proveedor ====================

    private void setupDesempenoTable() {
        if (colDesIndicador != null) colDesIndicador.setCellValueFactory(data -> data.getValue().indicadorProperty());
        if (colDesValor != null) colDesValor.setCellValueFactory(data -> data.getValue().valorProperty());
        if (colDesEstado != null) colDesEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colDesObservacion != null) colDesObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colDesEstado != null) {
            colDesEstado.setCellFactory(tc -> new javafx.scene.control.TableCell<>() {
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
                                case "excelente" -> StatusBadgeModel.success(item);
                                case "bueno" -> StatusBadgeModel.success(item);
                                case "aceptable" -> StatusBadgeModel.warning(item);
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

    private void loadDesempeno(String proveedorId) {
        List<ProveedoresRowModel.DesempenoRow> rows = proveedorId != null
                ? facade.getDesempeno(proveedorId)
                : List.of();
        ObservableList<ProveedoresRowModel.DesempenoRow> data = FXCollections.observableArrayList(rows);
        if (desempenoTable != null) {
            desempenoTable.setItems(data);
        }
    }

    // ==================== Historico ====================

    private void setupHistoricoTable() {
        if (colHisFecha != null) colHisFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHisProveedor != null) colHisProveedor.setCellValueFactory(data -> data.getValue().proveedorProperty());
        if (colHisTipoRegistro != null) colHisTipoRegistro.setCellValueFactory(data -> data.getValue().tipoRegistroProperty());
        if (colHisReferencia != null) colHisReferencia.setCellValueFactory(data -> data.getValue().referenciaProperty());
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
        List<ProveedoresRowModel.HistoricoRow> rows = facade.getHistorico(currentFilters);
        ObservableList<ProveedoresRowModel.HistoricoRow> data = FXCollections.observableArrayList(rows);
        if (historicoTable != null) {
            historicoTable.setItems(data);
        }
    }

    // ==================== Summary panel ====================

    private void updateSummaryPanel(Proveedor proveedor) {
        if (proveedor == null) {
            clearSummary();
            return;
        }
        ProveedoresSummaryModel summary = facade.buildSummary(proveedor);
        if (summaryProveedor != null) summaryProveedor.setText(summary.proveedor());
        if (summaryTipoPrincipal != null) summaryTipoPrincipal.setText(summary.tipoPrincipal());
        if (summaryEstado != null) summaryEstado.setText(summary.estado());
        if (summaryContacto != null) summaryContacto.setText(summary.contacto());
        if (summaryTelefono != null) summaryTelefono.setText(summary.telefono());
        if (summaryCorreo != null) summaryCorreo.setText(summary.correo());
        if (summaryCategoriaAbastecida != null) summaryCategoriaAbastecida.setText(summary.categoriaAbastecida());
        if (summarySucursalesAtendidas != null) summarySucursalesAtendidas.setText(summary.sucursalesAtendidas());
        if (summaryUltimaRecepcion != null) summaryUltimaRecepcion.setText(summary.ultimaRecepcion());
        if (summaryTiempoEstimado != null) summaryTiempoEstimado.setText(summary.tiempoEstimado());
        if (summaryIncidenciasRecientes != null) summaryIncidenciasRecientes.setText(summary.incidenciasRecientes());
        if (summaryObservacionClave != null) summaryObservacionClave.setText(summary.observacionClave());
    }

    private void clearSummary() {
        if (summaryProveedor != null) summaryProveedor.setText("Sin seleccion");
        if (summaryTipoPrincipal != null) summaryTipoPrincipal.setText("-");
        if (summaryEstado != null) summaryEstado.setText("-");
        if (summaryContacto != null) summaryContacto.setText("-");
        if (summaryTelefono != null) summaryTelefono.setText("-");
        if (summaryCorreo != null) summaryCorreo.setText("-");
        if (summaryCategoriaAbastecida != null) summaryCategoriaAbastecida.setText("-");
        if (summarySucursalesAtendidas != null) summarySucursalesAtendidas.setText("-");
        if (summaryUltimaRecepcion != null) summaryUltimaRecepcion.setText("-");
        if (summaryTiempoEstimado != null) summaryTiempoEstimado.setText("-");
        if (summaryIncidenciasRecientes != null) summaryIncidenciasRecientes.setText("-");
        if (summaryObservacionClave != null) summaryObservacionClave.setText("-");
    }

    // ==================== Action handlers ====================

    private void onNuevoProveedor() {
        // Placeholder action
    }

    private void onExportarDirectorio() {
        // Placeholder action
    }
}

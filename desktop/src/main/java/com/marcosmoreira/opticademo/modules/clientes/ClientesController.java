package com.marcosmoreira.opticademo.modules.clientes;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para el modulo de Clientes del sistema Optica.
 * <p>
 * Administra el catalogo completo de clientes del sistema, presentando los datos
 * en una tabla paginada con filtros por estado, ultima visita y estado de receta.
 * Incluye un panel de resumen lateral que muestra informacion detallada del cliente
 * seleccionado, asi como botones de accion rapida para crear recetas, ventas y citas.
 * </p>
 * <p>
 * Delega toda la logica de negocio a {@link ClientesFacade}, quien utiliza el
 * {@link DemoStore} como fuente de datos. La tabla utiliza paginacion mediante
 * {@link PageRequest} y {@link PageResult}, y la columna de estado renderiza
 * badges mediante {@link StatusBadgeController}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ClientesFacade
 * @see ClientesRowModel
 * @see ClientesFilters
 * @see PageRequest
 * @see PageResult
 */
public class ClientesController {

    /**
     * Selector de sucursal para filtrar clientes por ubicacion.
     * Permite seleccionar una sucursal especifica o visualizar todas.
     */
    @FXML
    private ComboBox<String> sucursalCombo;

    /** Campo de texto para busqueda de clientes por nombre o codigo. */
    @FXML
    private TextField searchField;

    /** Combo filtrable para filtrar clientes por estado (activo, inactivo, etc.). */
    @FXML
    private ComboBox<String> estadoCombo;

    /** Combo filtrable para filtrar por fecha de ultima visita. */
    @FXML
    private ComboBox<String> ultimaVisitaCombo;

    /** Combo filtrable para filtrar por estado de receta del cliente. */
    @FXML
    private ComboBox<String> recetaCombo;

    /** Boton para abrir el formulario de nuevo cliente. */
    @FXML
    private Button nuevoClienteBtn;

    /** Boton para refrescar el listado de clientes. */
    @FXML
    private Button actualizarListadoBtn;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    /**
     * Tabla principal que lista los clientes con paginacion.
     * Muestra columnas de nombre, codigo, telefono, ultima visita, receta, estado y sucursal.
     */
    @FXML
    private TableView<ClientesRowModel> clientesTable;

    /** Columna de nombre del cliente. */
    @FXML
    private TableColumn<ClientesRowModel, String> colNombre;

    /** Columna de codigo interno del cliente. */
    @FXML
    private TableColumn<ClientesRowModel, String> colCodigo;

    /** Columna de telefono del cliente. */
    @FXML
    private TableColumn<ClientesRowModel, String> colTelefono;

    /** Columna de fecha de ultima visita. */
    @FXML
    private TableColumn<ClientesRowModel, String> colUltimaVisita;

    /** Columna de estado de receta del cliente. */
    @FXML
    private TableColumn<ClientesRowModel, String> colReceta;

    /** Columna de estado del cliente, renderizada con {@link StatusBadgeController}. */
    @FXML
    private TableColumn<ClientesRowModel, String> colEstado;

    /** Columna de sucursal asignada al cliente. */
    @FXML
    private TableColumn<ClientesRowModel, String> colSucursal;

    /** Etiqueta de pie de tabla que muestra el conteo de registros. */
    @FXML
    private Label footerLabel;

    /**
     * Componente de paginacion reutilizable para navegar entre paginas de resultados.
     */
    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    /** Label del nombre en el panel de resumen lateral. */
    @FXML
    private Label summaryNombre;

    /** Label del codigo en el panel de resumen lateral. */
    @FXML
    private Label summaryCodigo;

    /** Label del estado en el panel de resumen lateral. */
    @FXML
    private Label summaryEstado;

    /** Label de la sucursal en el panel de resumen lateral. */
    @FXML
    private Label summarySucursal;

    /** Contenedor de campos adicionales en el panel de resumen. */
    @FXML
    private VBox summaryFieldsContainer;

    /** Boton para editar el cliente seleccionado. */
    @FXML
    private Button editarBtn;

    /** Boton para crear una nueva receta asociada al cliente. */
    @FXML
    private Button nuevaRecetaBtn;

    /** Boton para crear una nueva venta optica para el cliente. */
    @FXML
    private Button nuevaVentaBtn;

    /** Boton para agendar una cita para el cliente. */
    @FXML
    private Button agendarCitaBtn;

    /**
     * Fachada que centraliza la logica de negocio del modulo Clientes.
     * Proporciona metodos de consulta paginada y construccion de resumenes.
     */
    private ClientesFacade facade;

    /** Filtros actualmente aplicados en la busqueda de clientes. */
    private ClientesFilters currentFilters;

    /** Solicitud de paginacion actual. */
    private PageRequest currentPageRequest;

    /** Indice de la pagina actual (base cero). */
    private int currentPageIndex = 0;

    /** Tamano de pagina para la paginacion de resultados. */
    private int pageSize = 10;

    /**
     * Inicializa el controlador y configura todos los componentes de la interfaz.
     * <p>
     * Instancia {@link ClientesFacade} con el {@link DemoStore} global, configura
     * las columnas de la tabla con sus respectivas {@code cellValueFactory},
     * inicializa los combos filtrables mediante {@link ComboBoxFactory}, establece
     * los listeners de eventos para botones y filtros, y carga la primera pagina
     * de datos de clientes.
     * </p>
     *
     * @see ClientesFacade
     * @see ComboBoxFactory
     * @see App#getDemoStore()
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();

        this.facade = new ClientesFacade(store);
        this.currentFilters = new ClientesFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupSucursalCombo();
        setupTableColumns();
        setupFilterCombos();
        setupActionButtons();
        loadClientData();
    }

    // ---- Sucursal combo ----
    private void setupSucursalCombo() {
        List<String> sucursales = store().sucursales.stream()
                .map(s -> s.getNombre())
                .toList();
        if (sucursalCombo != null) {
            String[] items = sucursales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Seleccionar sucursal", "-- Todas --", items);
            if (sucursalCombo.getParent() instanceof HBox hbox) {
                int idx = hbox.getChildren().indexOf(sucursalCombo);
                hbox.getChildren().set(idx, combo);
            }
            sucursalCombo = combo;
        }
    }

    // ---- Table columns ----
    private void setupTableColumns() {
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());
        colTelefono.setCellValueFactory(data -> data.getValue().telefonoProperty());
        colUltimaVisita.setCellValueFactory(data -> data.getValue().ultimaVisitaProperty());
        colReceta.setCellValueFactory(data -> data.getValue().recetaProperty());
        colEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        colSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        // Custom cell factory for estado column to show StatusBadge
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
                        StatusBadgeController ctrl = loader.getController();
                        StatusBadgeModel badge = switch (item.toLowerCase()) {
                            case "activo" -> StatusBadgeModel.success(item);
                            case "inactivo" -> StatusBadgeModel.danger(item);
                            case "pendiente" -> StatusBadgeModel.warning(item);
                            case "bloqueado" -> StatusBadgeModel.danger(item);
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

        // Selection listener for summary panel
        if (clientesTable != null) {
            clientesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> updateSummaryPanel(newVal));
        }
    }

    // ---- Filter combos ----
    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getAllEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (ultimaVisitaCombo != null) {
            List<String> visitas = facade.getAllUltimasVisitas();
            String[] items = visitas.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Ultima visita", "Todas", items);
            replaceInParent(ultimaVisitaCombo, combo);
            ultimaVisitaCombo = combo;
            ultimaVisitaCombo.setOnAction(e -> applyFilters());
        }

        if (recetaCombo != null) {
            List<String> recetas = facade.getAllRecetaEstados();
            String[] items = recetas.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Receta", "Todas", items);
            replaceInParent(recetaCombo, combo);
            recetaCombo = combo;
            recetaCombo.setOnAction(e -> applyFilters());
        }

        // Search field listener
        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        }

        // Limpiar filtros button
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

    // ---- Action buttons ----
    private void setupActionButtons() {
        if (nuevoClienteBtn != null) {
            nuevoClienteBtn.setOnAction(e -> onNuevoCliente());
        }
        if (actualizarListadoBtn != null) {
            actualizarListadoBtn.setOnAction(e -> loadClientData());
        }
        if (editarBtn != null) {
            editarBtn.setOnAction(e -> onEditar());
        }
        if (nuevaRecetaBtn != null) {
            nuevaRecetaBtn.setOnAction(e -> onNuevaReceta());
        }
        if (nuevaVentaBtn != null) {
            nuevaVentaBtn.setOnAction(e -> onNuevaVenta());
        }
        if (agendarCitaBtn != null) {
            agendarCitaBtn.setOnAction(e -> onAgendarCita());
        }
    }

    // ---- Load data ----
    private void loadClientData() {
        PageResult<ClientesRowModel> pageResult = facade.findPage(currentFilters, currentPageRequest);
        ObservableList<ClientesRowModel> data = FXCollections.observableArrayList(pageResult.getItems());
        if (clientesTable != null) {
            clientesTable.setItems(data);
        }

        // Update pagination bar
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages(pageResult.getTotalPages());
            paginationBarController.setTotalItems(pageResult.getTotalItems());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        // Update footer (legacy)
        if (footerLabel != null) {
            int shown = pageResult.getItems().size();
            int total = pageResult.getTotalItems();
            footerLabel.setText(String.format("Mostrando %d de %d clientes", shown, total));
        }

        // Update current page info
        this.currentPageIndex = pageResult.getPageIndex();
        this.pageSize = pageResult.getPageSize();

        // Select first row if available to show summary
        if (!pageResult.isEmpty() && clientesTable != null) {
            clientesTable.getSelectionModel().selectFirst();
        }
    }

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadClientData();
    }

    // ---- Apply filters ----
    private void applyFilters() {
        currentFilters = new ClientesFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                ultimaVisitaCombo != null && ultimaVisitaCombo.getValue() != null ? ultimaVisitaCombo.getValue() : "Todas",
                recetaCombo != null && recetaCombo.getValue() != null ? recetaCombo.getValue() : "Todas"
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadClientData();
    }

    // ---- Clear filters ----
    private void clearFilters() {
        currentFilters = new ClientesFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (ultimaVisitaCombo != null) ultimaVisitaCombo.getSelectionModel().selectFirst();
        if (recetaCombo != null) recetaCombo.getSelectionModel().selectFirst();

        loadClientData();
    }

    // ---- Update summary panel ----
    private void updateSummaryPanel(ClientesRowModel row) {
        if (row == null) {
            if (summaryNombre != null) summaryNombre.setText("Sin seleccion");
            if (summaryCodigo != null) summaryCodigo.setText("");
            if (summaryEstado != null) summaryEstado.setText("");
            if (summarySucursal != null) summarySucursal.setText("");
            if (summaryFieldsContainer != null) summaryFieldsContainer.getChildren().clear();
            return;
        }

        // Find the full Cliente entity to build summary
        Cliente cliente = store().clientes.stream()
                .filter(c -> c.getCodigoInterno() != null && c.getCodigoInterno().equals(row.codigo()))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            if (summaryNombre != null) summaryNombre.setText(row.nombre());
            if (summaryCodigo != null) summaryCodigo.setText(row.codigo());
            return;
        }

        ClientesSummaryModel summary = facade.buildSummary(cliente);

        if (summaryNombre != null) summaryNombre.setText(summary.nombre());
        if (summaryCodigo != null) summaryCodigo.setText(summary.codigo());
        if (summaryEstado != null) summaryEstado.setText(summary.estado());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());

        // Build summary fields
        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Telefono", summary.telefono()),
                    new SummaryFieldModel("Email", summary.email()),
                    new SummaryFieldModel("Ultima compra", summary.ultimaCompra()),
                    new SummaryFieldModel("Saldo pendiente", summary.saldoPendiente(), true),
                    new SummaryFieldModel("Ordenes activas", summary.ordenesActivas()),
                    new SummaryFieldModel("Entregas pendientes", summary.entregasPendientes())
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

    // ---- Placeholder actions ----

    /**
     * Maneja la accion de crear un nuevo cliente.
     * Metodo placeholder para futura implementacion del formulario de registro.
     */
    private void onNuevoCliente() {
        // Placeholder: open new client form
    }

    /**
     * Maneja la accion de editar el cliente seleccionado.
     * Metodo placeholder para futura implementacion del formulario de edicion.
     */
    private void onEditar() {
        // Placeholder: open edit client form
    }

    /**
     * Maneja la accion de crear una nueva receta para el cliente seleccionado.
     * Metodo placeholder para futura integracion con el modulo de Recetas.
     */
    private void onNuevaReceta() {
        // Placeholder: open new receta form
    }

    /**
     * Maneja la accion de crear una nueva venta optica para el cliente seleccionado.
     * Metodo placeholder para futura integracion con el modulo de Venta Optica.
     */
    private void onNuevaVenta() {
        // Placeholder: open new venta optica form
    }

    /**
     * Maneja la accion de agendar una cita para el cliente seleccionado.
     * Metodo placeholder para futura integracion con el modulo de Agenda.
     */
    private void onAgendarCita() {
        // Placeholder: open agenda cita form
    }

    private DemoStore store() {
        return App.getDemoStore();
    }
}

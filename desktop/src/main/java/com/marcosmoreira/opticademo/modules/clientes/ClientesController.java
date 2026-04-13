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
 * Controller for the Clientes module.
 * Wires FXML elements to the facade and populates the view on initialize.
 */
public class ClientesController {

    // ---- Top bar ----
    @FXML
    private ComboBox<String> sucursalCombo;

    // ---- Filters ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> ultimaVisitaCombo;

    @FXML
    private ComboBox<String> recetaCombo;

    // ---- Buttons ----
    @FXML
    private Button nuevoClienteBtn;

    @FXML
    private Button actualizarListadoBtn;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Table ----
    @FXML
    private TableView<ClientesRowModel> clientesTable;

    @FXML
    private TableColumn<ClientesRowModel, String> colNombre;

    @FXML
    private TableColumn<ClientesRowModel, String> colCodigo;

    @FXML
    private TableColumn<ClientesRowModel, String> colTelefono;

    @FXML
    private TableColumn<ClientesRowModel, String> colUltimaVisita;

    @FXML
    private TableColumn<ClientesRowModel, String> colReceta;

    @FXML
    private TableColumn<ClientesRowModel, String> colEstado;

    @FXML
    private TableColumn<ClientesRowModel, String> colSucursal;

    // ---- Pagination ----
    @FXML
    private Label footerLabel;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    // ---- Summary panel ----
    @FXML
    private Label summaryNombre;

    @FXML
    private Label summaryCodigo;

    @FXML
    private Label summaryEstado;

    @FXML
    private Label summarySucursal;

    @FXML
    private VBox summaryFieldsContainer;

    // ---- Action buttons ----
    @FXML
    private Button editarBtn;

    @FXML
    private Button nuevaRecetaBtn;

    @FXML
    private Button nuevaVentaBtn;

    @FXML
    private Button agendarCitaBtn;

    // ---- Facade ----
    private ClientesFacade facade;

    private ClientesFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 10;

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
    private void onNuevoCliente() {
        // Placeholder: open new client form
    }

    private void onEditar() {
        // Placeholder: open edit client form
    }

    private void onNuevaReceta() {
        // Placeholder: open new receta form
    }

    private void onNuevaVenta() {
        // Placeholder: open new venta optica form
    }

    private void onAgendarCita() {
        // Placeholder: open agenda cita form
    }

    private DemoStore store() {
        return App.getDemoStore();
    }
}

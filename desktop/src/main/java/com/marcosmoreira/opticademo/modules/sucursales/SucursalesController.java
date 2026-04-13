package com.marcosmoreira.opticademo.modules.sucursales;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.sucursal.Sucursal;
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
 * Controller del modulo de Sucursales del sistema optico.
 * <p>
 * Gestiona siete sub-vistas para la administracion y monitoreo de las sucursales
 * de la optica: Directorio de sucursales, Perfil operativo de cada sede, Personal
 * asignado, Inventario local, Agenda de citas local, Indicadores de caja,
 * y Vista comparativa entre sucursales.
 * </p>
 * <p>
 * La seleccion de una sucursal en el directorio actualiza todas las sub-vistas
 * y el panel de resumen con la informacion operativa detallada de esa sede.
 * La fachada {@link SucursalesFacade} proporciona acceso a los datos de sucursales
 * almacenados en el {@link DemoStore}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see SucursalesFacade
 * @see SucursalesFilters
 * @see SucursalesSummaryModel
 */
public class SucursalesController {

    // ---- Top bar ----

    /** ComboBox para seleccionar la sucursal activa en el modulo. */
    @FXML
    private ComboBox<String> sucursalCombo;

    // ---- Filters ----

    /** Campo de busqueda por nombre de sucursal o ciudad. */
    @FXML
    private TextField searchField;

    /** ComboBox para filtrar por estado operativo de la sucursal. */
    @FXML
    private ComboBox<String> estadoCombo;

    /** ComboBox para filtrar por servicios habilitados. */
    @FXML
    private ComboBox<String> servicioCombo;

    /** ComboBox para filtrar por ciudad. */
    @FXML
    private ComboBox<String> ciudadCombo;

    /** CheckBox para mostrar solo sucursales con alertas activas. */
    @FXML
    private CheckBox soloAlertasCheck;

    // ---- Buttons ----

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    /** Boton para actualizar la vista actual. */
    @FXML
    private Button actualizarBtn;

    // ---- Sub-view ToggleButtons ----

    /** Toggle button para la sub-vista Directorio de sucursales. */
    @FXML
    private ToggleButton btnDirectorio;

    /** Toggle button para la sub-vista Perfil operativo. */
    @FXML
    private ToggleButton btnPerfil;

    /** Toggle button para la sub-vista Personal asignado. */
    @FXML
    private ToggleButton btnPersonal;

    /** Toggle button para la sub-vista Inventario local. */
    @FXML
    private ToggleButton btnInventario;

    /** Toggle button para la sub-vista Agenda de citas. */
    @FXML
    private ToggleButton btnAgenda;

    /** Toggle button para la sub-vista Indicadores de caja. */
    @FXML
    private ToggleButton btnCaja;

    /** Toggle button para la sub-vista Comparativo entre sucursales. */
    @FXML
    private ToggleButton btnComparativo;

    // ---- Sub-view 1: Directorio ----
    @FXML
    private VBox sectionDirectorio;

    @FXML
    private TableView<SucursalesRowModel.DirectorioRow> directorioTable;

    @FXML
    private TableColumn<SucursalesRowModel.DirectorioRow, String> colDirSucursal;

    @FXML
    private TableColumn<SucursalesRowModel.DirectorioRow, String> colDirCiudad;

    @FXML
    private TableColumn<SucursalesRowModel.DirectorioRow, String> colDirResponsable;

    @FXML
    private TableColumn<SucursalesRowModel.DirectorioRow, String> colDirHorario;

    @FXML
    private TableColumn<SucursalesRowModel.DirectorioRow, String> colDirEstado;

    @FXML
    private TableColumn<SucursalesRowModel.DirectorioRow, String> colDirServicios;

    // ---- Sub-view 2: Perfil operativo ----
    @FXML
    private VBox sectionPerfil;

    @FXML
    private VBox perfilIdentidadContainer;

    @FXML
    private VBox perfilContactoContainer;

    @FXML
    private VBox perfilHorarioContainer;

    @FXML
    private VBox perfilServiciosContainer;

    @FXML
    private VBox perfilObservacionesContainer;

    // ---- Sub-view 3: Personal ----
    @FXML
    private VBox sectionPersonal;

    @FXML
    private TableView<SucursalesRowModel.PersonalRow> personalTable;

    @FXML
    private TableColumn<SucursalesRowModel.PersonalRow, String> colPerUsuario;

    @FXML
    private TableColumn<SucursalesRowModel.PersonalRow, String> colPerRol;

    @FXML
    private TableColumn<SucursalesRowModel.PersonalRow, String> colPerEstado;

    @FXML
    private TableColumn<SucursalesRowModel.PersonalRow, String> colPerAcceso;

    @FXML
    private VBox personalDetailContainer;

    // ---- Sub-view 4: Inventario ----
    @FXML
    private VBox sectionInventario;

    @FXML
    private VBox inventarioKpiContainer;

    @FXML
    private TableView<SucursalesRowModel.InventarioRow> inventarioTable;

    @FXML
    private TableColumn<SucursalesRowModel.InventarioRow, String> colInvRef;

    @FXML
    private TableColumn<SucursalesRowModel.InventarioRow, String> colInvNombre;

    @FXML
    private TableColumn<SucursalesRowModel.InventarioRow, String> colInvCategoria;

    @FXML
    private TableColumn<SucursalesRowModel.InventarioRow, String> colInvStock;

    @FXML
    private TableColumn<SucursalesRowModel.InventarioRow, String> colInvEstado;

    @FXML
    private TableColumn<SucursalesRowModel.InventarioRow, String> colInvObservacion;

    // ---- Sub-view 5: Agenda ----
    @FXML
    private VBox sectionAgenda;

    @FXML
    private VBox agendaKpiContainer;

    @FXML
    private TableView<SucursalesRowModel.AgendaRow> agendaTable;

    @FXML
    private TableColumn<SucursalesRowModel.AgendaRow, String> colAgeIndicador;

    @FXML
    private TableColumn<SucursalesRowModel.AgendaRow, String> colAgeValor;

    @FXML
    private TableColumn<SucursalesRowModel.AgendaRow, String> colAgeEstado;

    @FXML
    private TableColumn<SucursalesRowModel.AgendaRow, String> colAgeObservacion;

    // ---- Sub-view 6: Caja ----
    @FXML
    private VBox sectionCaja;

    @FXML
    private VBox cajaKpiContainer;

    @FXML
    private TableView<SucursalesRowModel.CajaRow> cajaTable;

    @FXML
    private TableColumn<SucursalesRowModel.CajaRow, String> colCajIndicador;

    @FXML
    private TableColumn<SucursalesRowModel.CajaRow, String> colCajValor;

    @FXML
    private TableColumn<SucursalesRowModel.CajaRow, String> colCajEstado;

    @FXML
    private TableColumn<SucursalesRowModel.CajaRow, String> colCajObservacion;

    // ---- Sub-view 7: Comparativo ----
    @FXML
    private VBox sectionComparativo;

    @FXML
    private TableView<SucursalesRowModel.ComparativoRow> comparativoTable;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompSucursal;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompVentas;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompTicket;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompStock;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompRecalls;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompRetrasos;

    @FXML
    private TableColumn<SucursalesRowModel.ComparativoRow, String> colCompEstado;

    // ---- Summary panel ----
    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryCiudad;

    @FXML
    private Label summaryEstado;

    @FXML
    private VBox summaryFieldsContainer;

    // ---- Facade ----

    /** Fachada que proporciona acceso a los datos operativos de las sucursales. */
    private SucursalesFacade facade;

    private SucursalesFilters currentFilters;
    /** Sucursal actualmente seleccionada por el usuario. */
    private Sucursal selectedSucursal;
    private ToggleGroup subViewToggleGroup;

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia {@link SucursalesFacade} con el {@link DemoStore} global, configura
     * el grupo de toggle buttons para las sub-vistas, las columnas de cada tabla
     * con sus cell factories y status badges, los combos de filtrado, y carga
     * el directorio de sucursales.
     * </p>
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();

        this.facade = new SucursalesFacade(store);
        this.currentFilters = new SucursalesFilters();

        setupToggleGroup();
        setupTableColumns();
        setupFilterCombos();
        setupActionButtons();
        loadDirectorio();
        showSubView("directorio");
    }

    // =========================================================================
    // Toggle group
    // =========================================================================

    private void setupToggleGroup() {
        subViewToggleGroup = new ToggleGroup();
        ToggleButton[] buttons = {btnDirectorio, btnPerfil, btnPersonal, btnInventario, btnAgenda, btnCaja, btnComparativo};
        for (ToggleButton btn : buttons) {
            if (btn != null) {
                btn.setToggleGroup(subViewToggleGroup);
                btn.setOnAction(e -> {
                    if (btn.isSelected()) {
                        String id = getSubViewId(btn);
                        if (id != null) showSubView(id);
                    }
                });
            }
        }
        // Default select first
        if (btnDirectorio != null) btnDirectorio.setSelected(true);
    }

    private String getSubViewId(ToggleButton btn) {
        if (btn == btnDirectorio) return "directorio";
        if (btn == btnPerfil) return "perfil";
        if (btn == btnPersonal) return "personal";
        if (btn == btnInventario) return "inventario";
        if (btn == btnAgenda) return "agenda";
        if (btn == btnCaja) return "caja";
        if (btn == btnComparativo) return "comparativo";
        return null;
    }

    private void showSubView(String id) {
        VBox[] sections = {sectionDirectorio, sectionPerfil, sectionPersonal, sectionInventario, sectionAgenda, sectionCaja, sectionComparativo};
        for (VBox section : sections) {
            if (section != null) {
                section.setVisible(false);
                section.setManaged(false);
            }
        }

        VBox target = switch (id) {
            case "directorio" -> sectionDirectorio;
            case "perfil" -> sectionPerfil;
            case "personal" -> sectionPersonal;
            case "inventario" -> sectionInventario;
            case "agenda" -> sectionAgenda;
            case "caja" -> sectionCaja;
            case "comparativo" -> sectionComparativo;
            default -> sectionDirectorio;
        };

        if (target != null) {
            target.setVisible(true);
            target.setManaged(true);
        }

        // Load data for the visible sub-view
        switch (id) {
            case "directorio" -> loadDirectorio();
            case "perfil" -> loadPerfil();
            case "personal" -> loadPersonal();
            case "inventario" -> loadInventario();
            case "agenda" -> loadAgenda();
            case "caja" -> loadCaja();
            case "comparativo" -> loadComparativo();
        }
    }

    // =========================================================================
    // Table columns
    // =========================================================================

    private void setupTableColumns() {
        // -- Directorio columns --
        if (colDirSucursal != null) colDirSucursal.setCellValueFactory(d -> d.getValue().sucursalProperty());
        if (colDirCiudad != null) colDirCiudad.setCellValueFactory(d -> d.getValue().ciudadProperty());
        if (colDirResponsable != null) colDirResponsable.setCellValueFactory(d -> d.getValue().responsableProperty());
        if (colDirHorario != null) colDirHorario.setCellValueFactory(d -> d.getValue().horarioProperty());
        if (colDirServicios != null) colDirServicios.setCellValueFactory(d -> d.getValue().serviciosProperty());
        if (colDirEstado != null) {
            colDirEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
            colDirEstado.setCellFactory(tc -> new EstadoBadgeCell<>());
        }

        // Directorio selection -> update summary
        if (directorioTable != null) {
            directorioTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            selectedSucursal = facade.findSucursalByName(newVal.sucursal());
                            updateSummaryPanel(selectedSucursal);
                        }
                    });
        }

        // -- Personal columns --
        if (colPerUsuario != null) colPerUsuario.setCellValueFactory(d -> d.getValue().usuarioProperty());
        if (colPerRol != null) colPerRol.setCellValueFactory(d -> d.getValue().rolProperty());
        if (colPerAcceso != null) colPerAcceso.setCellValueFactory(d -> d.getValue().accesoPrincipalProperty());
        if (colPerEstado != null) {
            colPerEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
            colPerEstado.setCellFactory(tc -> new EstadoBadgeCell<>());
        }

        // -- Inventario columns --
        if (colInvRef != null) colInvRef.setCellValueFactory(d -> d.getValue().referenciaProperty());
        if (colInvNombre != null) colInvNombre.setCellValueFactory(d -> d.getValue().nombreProperty());
        if (colInvCategoria != null) colInvCategoria.setCellValueFactory(d -> d.getValue().categoriaProperty());
        if (colInvStock != null) colInvStock.setCellValueFactory(d -> d.getValue().stockProperty());
        if (colInvObservacion != null) colInvObservacion.setCellValueFactory(d -> d.getValue().observacionProperty());
        if (colInvEstado != null) {
            colInvEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
            colInvEstado.setCellFactory(tc -> new EstadoBadgeCell<>());
        }

        // -- Agenda columns --
        if (colAgeIndicador != null) colAgeIndicador.setCellValueFactory(d -> d.getValue().indicadorProperty());
        if (colAgeValor != null) colAgeValor.setCellValueFactory(d -> d.getValue().valorProperty());
        if (colAgeObservacion != null) colAgeObservacion.setCellValueFactory(d -> d.getValue().observacionProperty());
        if (colAgeEstado != null) {
            colAgeEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
            colAgeEstado.setCellFactory(tc -> new EstadoBadgeCell<>());
        }

        // -- Caja columns --
        if (colCajIndicador != null) colCajIndicador.setCellValueFactory(d -> d.getValue().indicadorProperty());
        if (colCajValor != null) colCajValor.setCellValueFactory(d -> d.getValue().valorProperty());
        if (colCajObservacion != null) colCajObservacion.setCellValueFactory(d -> d.getValue().observacionProperty());
        if (colCajEstado != null) {
            colCajEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
            colCajEstado.setCellFactory(tc -> new EstadoBadgeCell<>());
        }

        // -- Comparativo columns --
        if (colCompSucursal != null) colCompSucursal.setCellValueFactory(d -> d.getValue().sucursalProperty());
        if (colCompVentas != null) colCompVentas.setCellValueFactory(d -> d.getValue().ventasProperty());
        if (colCompTicket != null) colCompTicket.setCellValueFactory(d -> d.getValue().ticketPromedioProperty());
        if (colCompStock != null) colCompStock.setCellValueFactory(d -> d.getValue().stockCriticoProperty());
        if (colCompRecalls != null) colCompRecalls.setCellValueFactory(d -> d.getValue().recallsPendientesProperty());
        if (colCompRetrasos != null) colCompRetrasos.setCellValueFactory(d -> d.getValue().retrasosProperty());
        if (colCompEstado != null) {
            colCompEstado.setCellValueFactory(d -> d.getValue().estadoGeneralProperty());
            colCompEstado.setCellFactory(tc -> new ComparativoEstadoCell<>());
        }
    }

    // =========================================================================
    // Filter combos
    // =========================================================================

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (servicioCombo != null) {
            List<String> servicios = facade.getServicios();
            String[] items = servicios.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Servicio", "Todos", items);
            replaceInParent(servicioCombo, combo);
            servicioCombo = combo;
            servicioCombo.setOnAction(e -> applyFilters());
        }

        if (ciudadCombo != null) {
            List<String> ciudades = facade.getCiudades();
            String[] items = ciudades.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Ciudad", "Todas", items);
            replaceInParent(ciudadCombo, combo);
            ciudadCombo = combo;
            ciudadCombo.setOnAction(e -> applyFilters());
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

    // =========================================================================
    // Action buttons
    // =========================================================================

    private void setupActionButtons() {
        if (actualizarBtn != null) {
            actualizarBtn.setOnAction(e -> refreshCurrentView());
        }
    }

    // =========================================================================
    // Data loading
    // =========================================================================

    private void loadDirectorio() {
        List<SucursalesRowModel.DirectorioRow> rows = facade.getDirectorio(currentFilters);
        ObservableList<SucursalesRowModel.DirectorioRow> data = FXCollections.observableArrayList(rows);
        if (directorioTable != null) {
            directorioTable.setItems(data);
        }
        // Select first row by default
        if (!rows.isEmpty() && directorioTable != null) {
            directorioTable.getSelectionModel().select(0);
        }
    }

    private void loadPerfil() {
        if (selectedSucursal == null && directorioTable != null) {
            if (directorioTable.getItems().isEmpty()) return;
            var first = directorioTable.getItems().get(0);
            if (first != null) {
                selectedSucursal = facade.findSucursalByName(first.sucursal());
            }
        }
        if (selectedSucursal == null) return;

        SucursalesSummaryModel summary = facade.buildSummary(selectedSucursal);

        // Identidad
        if (perfilIdentidadContainer != null) {
            perfilIdentidadContainer.getChildren().clear();
            addProfileBlock(perfilIdentidadContainer, "Identidad", List.of(
                    new SummaryFieldModel("Sucursal", summary.sucursal()),
                    new SummaryFieldModel("Ciudad", summary.ciudad()),
                    new SummaryFieldModel("Direccion", summary.direccion())
            ));
        }

        // Contacto
        if (perfilContactoContainer != null) {
            perfilContactoContainer.getChildren().clear();
            addProfileBlock(perfilContactoContainer, "Contacto", List.of(
                    new SummaryFieldModel("Responsable", summary.responsable()),
                    new SummaryFieldModel("Telefono", summary.telefono())
            ));
        }

        // Horario
        if (perfilHorarioContainer != null) {
            perfilHorarioContainer.getChildren().clear();
            addProfileBlock(perfilHorarioContainer, "Horario operativo", List.of(
                    new SummaryFieldModel("Horario", summary.horario())
            ));
        }

        // Servicios
        if (perfilServiciosContainer != null) {
            perfilServiciosContainer.getChildren().clear();
            addProfileBlock(perfilServiciosContainer, "Servicios habilitados", List.of(
                    new SummaryFieldModel("Caja", summary.cajaHabilitada()),
                    new SummaryFieldModel("Inventario propio", summary.inventarioPropio()),
                    new SummaryFieldModel("Entregas", summary.entregaHabilitada()),
                    new SummaryFieldModel("Agenda", summary.agendaHabilitada())
            ));
        }

        // Observaciones
        if (perfilObservacionesContainer != null) {
            perfilObservacionesContainer.getChildren().clear();
            addProfileBlock(perfilObservacionesContainer, "Observaciones operativas", List.of(
                    new SummaryFieldModel("Detalle", summary.observacionOperativa())
            ));
        }
    }

    private void loadPersonal() {
        String sucursalName = selectedSucursal != null ? selectedSucursal.getNombre() : null;
        if (sucursalName == null && directorioTable != null && !directorioTable.getItems().isEmpty()) {
            sucursalName = directorioTable.getItems().get(0).sucursal();
        }
        if (sucursalName == null) return;

        List<SucursalesRowModel.PersonalRow> rows = facade.getPersonal(sucursalName);
        ObservableList<SucursalesRowModel.PersonalRow> data = FXCollections.observableArrayList(rows);
        if (personalTable != null) {
            personalTable.setItems(data);
        }

        // Detail panel
        if (personalDetailContainer != null) {
            personalDetailContainer.getChildren().clear();
            if (!rows.isEmpty()) {
                addProfileBlock(personalDetailContainer, "Permisos de la sede", List.of(
                        new SummaryFieldModel("Total personal", String.valueOf(rows.size())),
                        new SummaryFieldModel("Activos", String.valueOf(rows.stream().filter(r -> "ACTIVO".equals(r.estado())).count())),
                        new SummaryFieldModel("Roles", String.valueOf(rows.stream().map(SucursalesRowModel.PersonalRow::rol).distinct().count()))
                ));
            }
        }
    }

    private void loadInventario() {
        String sucursalName = selectedSucursal != null ? selectedSucursal.getNombre() : null;
        if (sucursalName == null && directorioTable != null && !directorioTable.getItems().isEmpty()) {
            sucursalName = directorioTable.getItems().get(0).sucursal();
        }
        if (sucursalName == null) return;

        List<SucursalesRowModel.InventarioRow> rows = facade.getInventarioLocal(sucursalName);
        ObservableList<SucursalesRowModel.InventarioRow> data = FXCollections.observableArrayList(rows);
        if (inventarioTable != null) {
            inventarioTable.setItems(data);
        }

        // KPIs
        if (inventarioKpiContainer != null) {
            inventarioKpiContainer.getChildren().clear();
            long bajoStock = rows.stream().filter(r -> r.stock() != null && Integer.parseInt(r.stock()) <= 5).count();
            long total = rows.size();
            addProfileBlock(inventarioKpiContainer, "KPIs de inventario", List.of(
                    new SummaryFieldModel("Total productos", String.valueOf(total)),
                    new SummaryFieldModel("Bajo stock", String.valueOf(bajoStock), bajoStock > 0),
                    new SummaryFieldModel("Sede", sucursalName)
            ));
        }
    }

    private void loadAgenda() {
        String sucursalName = selectedSucursal != null ? selectedSucursal.getNombre() : null;
        if (sucursalName == null && directorioTable != null && !directorioTable.getItems().isEmpty()) {
            sucursalName = directorioTable.getItems().get(0).sucursal();
        }
        if (sucursalName == null) return;

        List<SucursalesRowModel.AgendaRow> rows = facade.getAgendaLocal(sucursalName);
        ObservableList<SucursalesRowModel.AgendaRow> data = FXCollections.observableArrayList(rows);
        if (agendaTable != null) {
            agendaTable.setItems(data);
        }

        // KPIs
        if (agendaKpiContainer != null) {
            agendaKpiContainer.getChildren().clear();
            addProfileBlock(agendaKpiContainer, "KPIs de agenda", List.of(
                    new SummaryFieldModel("Sede", sucursalName),
                    new SummaryFieldModel("Indicadores", String.valueOf(rows.size()))
            ));
        }
    }

    private void loadCaja() {
        String sucursalName = selectedSucursal != null ? selectedSucursal.getNombre() : null;
        if (sucursalName == null && directorioTable != null && !directorioTable.getItems().isEmpty()) {
            sucursalName = directorioTable.getItems().get(0).sucursal();
        }
        if (sucursalName == null) return;

        List<SucursalesRowModel.CajaRow> rows = facade.getCajaLocal(sucursalName);
        ObservableList<SucursalesRowModel.CajaRow> data = FXCollections.observableArrayList(rows);
        if (cajaTable != null) {
            cajaTable.setItems(data);
        }

        // KPIs
        if (cajaKpiContainer != null) {
            cajaKpiContainer.getChildren().clear();
            addProfileBlock(cajaKpiContainer, "KPIs economicos", List.of(
                    new SummaryFieldModel("Sede", sucursalName),
                    new SummaryFieldModel("Indicadores", String.valueOf(rows.size()))
            ));
        }
    }

    private void loadComparativo() {
        List<SucursalesRowModel.ComparativoRow> rows = facade.getComparativo();
        ObservableList<SucursalesRowModel.ComparativoRow> data = FXCollections.observableArrayList(rows);
        if (comparativoTable != null) {
            comparativoTable.setItems(data);
        }
    }

    // =========================================================================
    // Filters
    // =========================================================================

    private void applyFilters() {
        currentFilters = new SucursalesFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                servicioCombo != null && servicioCombo.getValue() != null ? servicioCombo.getValue() : "Todos",
                ciudadCombo != null && ciudadCombo.getValue() != null ? ciudadCombo.getValue() : "Todas",
                soloAlertasCheck != null && soloAlertasCheck.isSelected()
        );
        loadDirectorio();
    }

    private void clearFilters() {
        currentFilters = new SucursalesFilters();
        if (searchField != null) searchField.clear();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (servicioCombo != null) servicioCombo.getSelectionModel().selectFirst();
        if (ciudadCombo != null) ciudadCombo.getSelectionModel().selectFirst();
        if (soloAlertasCheck != null) soloAlertasCheck.setSelected(false);
        loadDirectorio();
    }

    private void refreshCurrentView() {
        ToggleButton selected = (ToggleButton) (subViewToggleGroup != null ? subViewToggleGroup.getSelectedToggle() : null);
        String id = selected != null ? getSubViewId(selected) : "directorio";
        showSubView(id != null ? id : "directorio");
    }

    // =========================================================================
    // Summary panel
    // =========================================================================

    private void updateSummaryPanel(Sucursal s) {
        if (s == null) {
            if (summarySucursal != null) summarySucursal.setText("Sin seleccion");
            if (summaryCiudad != null) summaryCiudad.setText("");
            if (summaryEstado != null) summaryEstado.setText("");
            if (summaryFieldsContainer != null) summaryFieldsContainer.getChildren().clear();
            return;
        }

        SucursalesSummaryModel summary = facade.buildSummary(s);

        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryCiudad != null) summaryCiudad.setText(summary.ciudad());
        if (summaryEstado != null) summaryEstado.setText(summary.estadoActual());

        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Direccion", summary.direccion()),
                    new SummaryFieldModel("Responsable", summary.responsable()),
                    new SummaryFieldModel("Telefono", summary.telefono()),
                    new SummaryFieldModel("Horario", summary.horario()),
                    new SummaryFieldModel("Caja", summary.cajaHabilitada()),
                    new SummaryFieldModel("Inventario", summary.inventarioPropio()),
                    new SummaryFieldModel("Entregas", summary.entregaHabilitada()),
                    new SummaryFieldModel("Agenda", summary.agendaHabilitada()),
                    new SummaryFieldModel("Observacion", summary.observacionOperativa())
            );

            for (SummaryFieldModel field : fields) {
                HBox row = new HBox(4);
                row.getStyleClass().add("summary-field-row");

                Label fieldLabel = new Label();
                fieldLabel.getStyleClass().add("summary-field-label");
                fieldLabel.setText(field.label() + ":");
                fieldLabel.setMinWidth(110);

                Label fieldValue = new Label();
                fieldValue.getStyleClass().add("summary-field-value");
                if (field.isHighlighted()) {
                    fieldValue.getStyleClass().add("highlighted");
                }
                fieldValue.setText(field.value() != null ? field.value() : "-");
                fieldValue.setWrapText(true);
                HBox.setHgrow(fieldValue, javafx.scene.layout.Priority.ALWAYS);

                row.getChildren().addAll(fieldLabel, fieldValue);
                summaryFieldsContainer.getChildren().add(row);
            }
        }
    }

    // =========================================================================
    // Profile block helper
    // =========================================================================

    private void addProfileBlock(VBox container, String title, List<SummaryFieldModel> fields) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        titleLabel.getStyleClass().add("profile-block-title");

        VBox block = new VBox(6);
        block.getStyleClass().add("profile-block");
        block.getChildren().add(titleLabel);

        for (SummaryFieldModel field : fields) {
            HBox row = new HBox(4);
            row.getStyleClass().add("summary-field-row");

            Label label = new Label(field.label() + ":");
            label.getStyleClass().add("summary-field-label");
            label.setMinWidth(130);

            Label value = new Label(field.value() != null ? field.value() : "-");
            value.getStyleClass().add("summary-field-value");
            if (field.isHighlighted()) {
                value.getStyleClass().add("highlighted");
            }
            value.setWrapText(true);
            HBox.setHgrow(value, javafx.scene.layout.Priority.ALWAYS);

            row.getChildren().addAll(label, value);
            block.getChildren().add(row);
        }

        container.getChildren().add(block);
    }

    // =========================================================================
    // Cell factories - inner classes for type-safe badge cells
    // =========================================================================

    /** Generic badge cell for estado columns across most sub-views. */
    private static class EstadoBadgeCell<T> extends javafx.scene.control.TableCell<T, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(EstadoBadgeCell.class.getResource("/fxml/shared/components/StatusBadge.fxml"));
                    VBox badgeRoot = loader.load();
                    StatusBadgeController ctrl = loader.getController();
                    StatusBadgeModel badge = mapEstadoToBadge(item);
                    ctrl.setStatus(badge);
                    setGraphic(badgeRoot);
                    setText(null);
                } catch (IOException e) {
                    setText(item);
                    setGraphic(null);
                }
            }
        }

        private static StatusBadgeModel mapEstadoToBadge(String estado) {
            return switch (estado.toLowerCase()) {
                case "activo", "operativa" -> StatusBadgeModel.success(estado);
                case "inactivo" -> StatusBadgeModel.danger(estado);
                case "bajo_observacion", "observado", "requiere_revision" -> StatusBadgeModel.warning(estado);
                case "en_proceso", "pendiente" -> StatusBadgeModel.warning(estado);
                case "bajo_stock" -> StatusBadgeModel.danger(estado);
                case "normal" -> StatusBadgeModel.success(estado);
                case "atencion requerida" -> StatusBadgeModel.warning(estado);
                default -> StatusBadgeModel.neutral(estado);
            };
        }
    }

    /** Badge cell specifically for the comparativo estado column. */
    private static class ComparativoEstadoCell<T> extends javafx.scene.control.TableCell<T, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(ComparativoEstadoCell.class.getResource("/fxml/shared/components/StatusBadge.fxml"));
                    VBox badgeRoot = loader.load();
                    StatusBadgeController ctrl = loader.getController();
                    StatusBadgeModel badge = mapComparativoEstadoToBadge(item);
                    ctrl.setStatus(badge);
                    setGraphic(badgeRoot);
                    setText(null);
                } catch (IOException e) {
                    setText(item);
                    setGraphic(null);
                }
            }
        }

        private static StatusBadgeModel mapComparativoEstadoToBadge(String estado) {
            return switch (estado.toLowerCase()) {
                case "optimo" -> StatusBadgeModel.success(estado);
                case "bueno" -> StatusBadgeModel.info(estado);
                case "bajo observacion" -> StatusBadgeModel.warning(estado);
                case "critico", "deficiente" -> StatusBadgeModel.danger(estado);
                default -> StatusBadgeModel.neutral(estado);
            };
        }
    }
}

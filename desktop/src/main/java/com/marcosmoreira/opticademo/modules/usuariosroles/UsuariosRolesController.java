package com.marcosmoreira.opticademo.modules.usuariosroles;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.usuario.Usuario;
import com.marcosmoreira.opticademo.shared.query.PageRequest;
import com.marcosmoreira.opticademo.shared.query.PageResult;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.model.SummaryFieldModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller del modulo de Usuarios y Roles del sistema optico.
 * <p>
 * Gestiona siete sub-vistas para la administracion de accesos y seguridad del sistema:
 * Directorio de usuarios, Roles del sistema, Permisos por modulo, Usuarios por sucursal,
 * Sesiones y accesos, Auditoria y trazabilidad, e Historico de accesos.
 * </p>
 * <p>
 * Permite la gestion completa de usuarios, asignacion de roles, configuracion granular
 * de permisos por modulo, y consulta de logs de auditoria. La fachada
 * {@link UsuariosRolesFacade} proporciona acceso a los datos de usuarios y roles
 * almacenados en el {@link DemoStore}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see UsuariosRolesFacade
 * @see UsuariosRolesFilters
 * @see UsuariosRolesSummaryModel
 */
public class UsuariosRolesController {

    // ---- Top bar ----

    /** Boton para crear un nuevo usuario en el sistema. */
    @FXML
    private Button nuevoUsuarioBtn;

    /** Boton para actualizar la lista de usuarios. */
    @FXML
    private Button actualizarListadoBtn;

    // ---- Filters ----

    /** Campo de busqueda por nombre de usuario o correo. */
    @FXML
    private TextField searchField;

    /** ComboBox para filtrar por rol del usuario. */
    @FXML
    private ComboBox<String> rolCombo;

    /** ComboBox para filtrar por estado del usuario. */
    @FXML
    private ComboBox<String> estadoCombo;

    /** ComboBox para filtrar por sucursal asignada. */
    @FXML
    private ComboBox<String> sucursalCombo;

    /** ComboBox para filtrar por tipo de actividad. */
    @FXML
    private ComboBox<String> actividadCombo;

    /** DatePicker para fecha inicio del rango de filtrado. */
    @FXML
    private DatePicker desdePicker;

    /** DatePicker para fecha fin del rango de filtrado. */
    @FXML
    private DatePicker hastaPicker;

    /** CheckBox para mostrar solo eventos sensibles de seguridad. */
    @FXML
    private CheckBox soloEventosSensiblesCheck;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----

    /** Toggle button para la sub-vista Directorio de usuarios. */
    @FXML
    private ToggleButton btnDirectorio;

    /** Toggle button para la sub-vista Roles del sistema. */
    @FXML
    private ToggleButton btnRoles;

    /** Toggle button para la sub-vista Permisos por modulo. */
    @FXML
    private ToggleButton btnPermisos;

    /** Toggle button para la sub-vista Usuarios por sucursal. */
    @FXML
    private ToggleButton btnSucursales;

    /** Toggle button para la sub-vista Sesiones y accesos. */
    @FXML
    private ToggleButton btnSesiones;

    /** Toggle button para la sub-vista Auditoria y trazabilidad. */
    @FXML
    private ToggleButton btnAuditoria;

    /** Toggle button para la sub-vista Historico. */
    @FXML
    private ToggleButton btnHistorico;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionDirectorio;

    @FXML
    private VBox sectionRoles;

    @FXML
    private VBox sectionPermisos;

    @FXML
    private VBox sectionSucursales;

    @FXML
    private VBox sectionSesiones;

    @FXML
    private VBox sectionAuditoria;

    @FXML
    private VBox sectionHistorico;

    // ---- Sub-view 1: Directorio de usuarios ----
    @FXML
    private Label lblDirectorioCount;

    @FXML
    private TableView<UsuariosRolesRowModel.UsuarioRow> directorioTable;

    @FXML
    private com.marcosmoreira.opticademo.shared.ui.components.PaginationBarController paginationBarController;

    @FXML
    private TableColumn<UsuariosRolesRowModel.UsuarioRow, String> colDUsuario;

    @FXML
    private TableColumn<UsuariosRolesRowModel.UsuarioRow, String> colDNombre;

    @FXML
    private TableColumn<UsuariosRolesRowModel.UsuarioRow, String> colDRol;

    @FXML
    private TableColumn<UsuariosRolesRowModel.UsuarioRow, String> colDSucursal;

    @FXML
    private TableColumn<UsuariosRolesRowModel.UsuarioRow, String> colDEstado;

    @FXML
    private TableColumn<UsuariosRolesRowModel.UsuarioRow, String> colDUltimoAcceso;

    @FXML
    private Label footerDirectorio;

    // ---- Sub-view 2: Roles del sistema ----
    @FXML
    private TableView<UsuariosRolesRowModel.RolRow> rolesTable;

    @FXML
    private TableColumn<UsuariosRolesRowModel.RolRow, String> colRNombre;

    @FXML
    private TableColumn<UsuariosRolesRowModel.RolRow, String> colRDescripcion;

    @FXML
    private TableColumn<UsuariosRolesRowModel.RolRow, String> colRSedeRecomendada;

    @FXML
    private TableColumn<UsuariosRolesRowModel.RolRow, String> colRNivelAcceso;

    // ---- Sub-view 3: Permisos por modulo ----
    @FXML
    private ComboBox<String> permisosRolCombo;

    @FXML
    private TableView<UsuariosRolesRowModel.PermisoRow> permisosTable;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPModulo;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPVer;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPCrear;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPEditar;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPAprobar;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPExportar;

    @FXML
    private TableColumn<UsuariosRolesRowModel.PermisoRow, String> colPCerrar;

    @FXML
    private Button btnGuardarPermisos;

    // ---- Sub-view 4: Usuarios por sucursal ----
    @FXML
    private TableView<UsuariosRolesRowModel.SucursalUsuarioRow> sucursalesTable;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SucursalUsuarioRow, String> colSucSucursal;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SucursalUsuarioRow, String> colSUsuariosActivos;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SucursalUsuarioRow, String> colSRolesPrincipales;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SucursalUsuarioRow, String> colSucEstado;

    // ---- Sub-view 5: Sesiones y accesos ----
    @FXML
    private TableView<UsuariosRolesRowModel.SesionRow> sesionesTable;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SesionRow, String> colSFechaHora;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SesionRow, String> colSUsuario;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SesionRow, String> colSEvento;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SesionRow, String> colSSucursal;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SesionRow, String> colSEstado;

    @FXML
    private TableColumn<UsuariosRolesRowModel.SesionRow, String> colSObservacion;

    // ---- Sub-view 6: Auditoria y trazabilidad ----
    @FXML
    private TableView<UsuariosRolesRowModel.AuditoriaRow> auditoriaTable;

    @FXML
    private TableColumn<UsuariosRolesRowModel.AuditoriaRow, String> colAFechaHora;

    @FXML
    private TableColumn<UsuariosRolesRowModel.AuditoriaRow, String> colAUsuario;

    @FXML
    private TableColumn<UsuariosRolesRowModel.AuditoriaRow, String> colAModulo;

    @FXML
    private TableColumn<UsuariosRolesRowModel.AuditoriaRow, String> colAAccion;

    @FXML
    private TableColumn<UsuariosRolesRowModel.AuditoriaRow, String> colARegistroAfectado;

    @FXML
    private TableColumn<UsuariosRolesRowModel.AuditoriaRow, String> colAObservacion;

    // ---- Sub-view 7: Historico ----
    @FXML
    private TableView<UsuariosRolesRowModel.HistoricoAccesoRow> historicoTable;

    @FXML
    private TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String> colHFecha;

    @FXML
    private TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String> colHUsuario;

    @FXML
    private TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String> colHTipoEvento;

    @FXML
    private TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String> colHRolOModulo;

    @FXML
    private TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String> colHEstado;

    @FXML
    private TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String> colHObservacion;

    // ---- Right panel: User/Role summary ----
    @FXML
    private Label summaryUsuario;

    @FXML
    private Label summaryRolPrincipal;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryEstadoActual;

    @FXML
    private Label summaryUltimoAcceso;

    @FXML
    private VBox summaryFieldsContainer;

    @FXML
    private Button summaryBtnEditar;

    @FXML
    private Button summaryBtnCambiarRol;

    @FXML
    private Button summaryBtnVerAuditoria;

    @FXML
    private Button summaryBtnDesactivar;

    // ---- Facade ----

    /** Fachada que centraliza la logica de gestion de usuarios, roles y permisos. */
    private UsuariosRolesFacade facade;

    private UsuariosRolesFilters currentFilters;
    private PageRequest currentPageRequest;
    private int currentPageIndex = 0;
    private int pageSize = 20;

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia {@link UsuariosRolesFacade} con el {@link DemoStore} global, configura
     * los combos de filtrado, el sistema de toggle entre sub-vistas, las columnas
     * de cada tabla con status badges, y carga el directorio de usuarios.
     * </p>
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new UsuariosRolesFacade(store);
        this.currentFilters = new UsuariosRolesFilters();
        this.currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Directorio();
        setupSubView2_Roles();
        setupSubView3_Permisos();
        setupSubView4_Sucursales();
        setupSubView5_Sesiones();
        setupSubView6_Auditoria();
        setupSubView7_Historico();
        setupActionButtons();
        setupSummaryPanel();

        showSubView(0);
        if (btnDirectorio != null) btnDirectorio.setSelected(true);

        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (rolCombo != null) {
            List<String> roles = facade.getRolesList();
            String[] items = roles.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Rol", "Todos", items);
            replaceInParent(rolCombo, combo);
            rolCombo = combo;
            rolCombo.setOnAction(e -> applyFilters());
        }

        if (estadoCombo != null) {
            List<String> estados = facade.getEstados();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (sucursalCombo != null) {
            List<String> sucursales = facade.getSucursales();
            String[] items = sucursales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Sucursal", "Todas", items);
            replaceInParent(sucursalCombo, combo);
            sucursalCombo = combo;
            sucursalCombo.setOnAction(e -> applyFilters());
        }

        if (actividadCombo != null) {
            List<String> actividades = facade.getActividades();
            String[] items = actividades.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Actividad", "Todas", items);
            replaceInParent(actividadCombo, combo);
            actividadCombo = combo;
            actividadCombo.setOnAction(e -> applyFilters());
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

        if (soloEventosSensiblesCheck != null) {
            soloEventosSensiblesCheck.selectedProperty().addListener((obs, old, val) -> applyFilters());
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
        if (btnDirectorio != null) {
            btnDirectorio.setToggleGroup(group);
            btnDirectorio.setOnAction(e -> showSubView(0));
        }
        if (btnRoles != null) {
            btnRoles.setToggleGroup(group);
            btnRoles.setOnAction(e -> showSubView(1));
        }
        if (btnPermisos != null) {
            btnPermisos.setToggleGroup(group);
            btnPermisos.setOnAction(e -> showSubView(2));
        }
        if (btnSucursales != null) {
            btnSucursales.setToggleGroup(group);
            btnSucursales.setOnAction(e -> showSubView(3));
        }
        if (btnSesiones != null) {
            btnSesiones.setToggleGroup(group);
            btnSesiones.setOnAction(e -> showSubView(4));
        }
        if (btnAuditoria != null) {
            btnAuditoria.setToggleGroup(group);
            btnAuditoria.setOnAction(e -> showSubView(5));
        }
        if (btnHistorico != null) {
            btnHistorico.setToggleGroup(group);
            btnHistorico.setOnAction(e -> showSubView(6));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionDirectorio,
                sectionRoles,
                sectionPermisos,
                sectionSucursales,
                sectionSesiones,
                sectionAuditoria,
                sectionHistorico
        };

        for (int i = 0; i < sections.length; i++) {
            if (sections[i] != null) {
                sections[i].setVisible(i == index);
                sections[i].setManaged(i == index);
            }
        }

        switch (index) {
            case 0 -> loadSubView1_Directorio();
            case 1 -> loadSubView2_Roles();
            case 2 -> loadSubView3_Permisos();
            case 3 -> loadSubView4_Sucursales();
            case 4 -> loadSubView5_Sesiones();
            case 5 -> loadSubView6_Auditoria();
            case 6 -> loadSubView7_Historico();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Directorio de usuarios

    private void setupSubView1_Directorio() {
        if (colDUsuario != null) colDUsuario.setCellValueFactory(data -> data.getValue().usuarioProperty());
        if (colDNombre != null) colDNombre.setCellValueFactory(data -> data.getValue().nombreVisibleProperty());
        if (colDRol != null) colDRol.setCellValueFactory(data -> data.getValue().rolProperty());
        if (colDSucursal != null) colDSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colDEstado != null) colDEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colDUltimoAcceso != null) colDUltimoAcceso.setCellValueFactory(data -> data.getValue().ultimoAccesoProperty());

        if (colDEstado != null) {
            colDEstado.setCellFactory(createUsuarioStatusBadgeCell());
        }

        if (directorioTable != null) {
            directorioTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onUsuarioRowSelected(newVal));
        }
    }

    private void loadSubView1_Directorio() {
        if (directorioTable == null) return;

        PageResult<UsuariosRolesRowModel.UsuarioRow> pageResult =
                facade.getDirectorio(currentFilters, currentPageRequest);
        ObservableList<UsuariosRolesRowModel.UsuarioRow> data =
                FXCollections.observableArrayList(pageResult.getItems());
        directorioTable.setItems(data);

        // Update pagination
        if (paginationBarController != null) {
            paginationBarController.setCurrentPage(currentPageIndex + 1);
            paginationBarController.setTotalPages(pageResult.getTotalPages());
            paginationBarController.setTotalItems(pageResult.getTotalItems());
            paginationBarController.setPageSize(pageSize);
            paginationBarController.setOnPrev(() -> goToPage(currentPageIndex - 1));
            paginationBarController.setOnNext(() -> goToPage(currentPageIndex + 1));
        }

        if (lblDirectorioCount != null) {
            lblDirectorioCount.setText(data.size() + " usuarios visibles");
        }

        if (footerDirectorio != null) {
            footerDirectorio.setText("Mostrando " + data.size() + " de " + pageResult.getTotalItems() + " usuarios");
        }

        if (!data.isEmpty()) {
            directorioTable.getSelectionModel().selectFirst();
        }
    }

    private void onUsuarioRowSelected(UsuariosRolesRowModel.UsuarioRow row) {
        if (row == null) return;

        DemoStore store = App.getDemoStore();
        Usuario usuario = store.usuarios.stream()
                .filter(u -> row.usuario().equals(u.getCorreo()))
                .findFirst()
                .orElse(null);

        UsuariosRolesSummaryModel summary = facade.buildSummary(usuario);
        updateSummaryPanel(summary);
    }

    // ------------------------------------------------------------------ Sub-view 2: Roles del sistema

    private void setupSubView2_Roles() {
        if (colRNombre != null) colRNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        if (colRDescripcion != null) colRDescripcion.setCellValueFactory(data -> data.getValue().descripcionProperty());
        if (colRSedeRecomendada != null) colRSedeRecomendada.setCellValueFactory(data -> data.getValue().sedeRecomendadaProperty());
        if (colRNivelAcceso != null) colRNivelAcceso.setCellValueFactory(data -> data.getValue().nivelAccesoProperty());
    }

    private void loadSubView2_Roles() {
        if (rolesTable == null) return;
        List<UsuariosRolesRowModel.RolRow> rows = facade.getRoles();
        rolesTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 3: Permisos por modulo

    private void setupSubView3_Permisos() {
        if (colPModulo != null) colPModulo.setCellValueFactory(data -> data.getValue().moduloProperty());
        if (colPVer != null) colPVer.setCellValueFactory(data -> data.getValue().verProperty());
        if (colPCrear != null) colPCrear.setCellValueFactory(data -> data.getValue().crearProperty());
        if (colPEditar != null) colPEditar.setCellValueFactory(data -> data.getValue().editarProperty());
        if (colPAprobar != null) colPAprobar.setCellValueFactory(data -> data.getValue().aprobarProperty());
        if (colPExportar != null) colPExportar.setCellValueFactory(data -> data.getValue().exportarProperty());
        if (colPCerrar != null) colPCerrar.setCellValueFactory(data -> data.getValue().cerrarProperty());

        if (permisosRolCombo != null) {
            List<UsuariosRolesRowModel.RolRow> roles = facade.getRoles();
            for (UsuariosRolesRowModel.RolRow r : roles) {
                permisosRolCombo.getItems().add(r.nombre());
            }
            if (!roles.isEmpty()) {
                permisosRolCombo.setValue(roles.get(0).nombre());
            }
            permisosRolCombo.setOnAction(e -> loadSubView3_Permisos());
        }

        if (btnGuardarPermisos != null) {
            btnGuardarPermisos.setOnAction(e -> onGuardarPermisos());
        }
    }

    private void loadSubView3_Permisos() {
        if (permisosTable == null) return;
        String rol = permisosRolCombo != null && permisosRolCombo.getValue() != null ? permisosRolCombo.getValue() : "";
        List<UsuariosRolesRowModel.PermisoRow> rows = facade.getPermisosPorRol(rol);
        permisosTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 4: Usuarios por sucursal

    private void setupSubView4_Sucursales() {
        if (colSucSucursal != null) colSucSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colSUsuariosActivos != null) colSUsuariosActivos.setCellValueFactory(data -> data.getValue().usuariosActivosProperty());
        if (colSRolesPrincipales != null) colSRolesPrincipales.setCellValueFactory(data -> data.getValue().rolesPrincipalesProperty());
        if (colSucEstado != null) colSucEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        if (colSucEstado != null) {
            colSucEstado.setCellFactory(createSucursalStatusBadgeCell());
        }
    }

    private void loadSubView4_Sucursales() {
        if (sucursalesTable == null) return;
        List<UsuariosRolesRowModel.SucursalUsuarioRow> rows = facade.getUsuariosPorSucursal();
        sucursalesTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 5: Sesiones y accesos

    private void setupSubView5_Sesiones() {
        if (colSFechaHora != null) colSFechaHora.setCellValueFactory(data -> data.getValue().fechaHoraProperty());
        if (colSUsuario != null) colSUsuario.setCellValueFactory(data -> data.getValue().usuarioProperty());
        if (colSEvento != null) colSEvento.setCellValueFactory(data -> data.getValue().eventoProperty());
        if (colSSucursal != null) colSSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colSEstado != null) colSEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colSObservacion != null) colSObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colSEstado != null) {
            colSEstado.setCellFactory(createSesionStatusBadgeCell());
        }
    }

    private void loadSubView5_Sesiones() {
        if (sesionesTable == null) return;
        List<UsuariosRolesRowModel.SesionRow> rows = facade.getSesiones();
        sesionesTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 6: Auditoria y trazabilidad

    private void setupSubView6_Auditoria() {
        if (colAFechaHora != null) colAFechaHora.setCellValueFactory(data -> data.getValue().fechaHoraProperty());
        if (colAUsuario != null) colAUsuario.setCellValueFactory(data -> data.getValue().usuarioProperty());
        if (colAModulo != null) colAModulo.setCellValueFactory(data -> data.getValue().moduloProperty());
        if (colAAccion != null) colAAccion.setCellValueFactory(data -> data.getValue().accionProperty());
        if (colARegistroAfectado != null) colARegistroAfectado.setCellValueFactory(data -> data.getValue().registroAfectadoProperty());
        if (colAObservacion != null) colAObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());
    }

    private void loadSubView6_Auditoria() {
        if (auditoriaTable == null) return;
        List<UsuariosRolesRowModel.AuditoriaRow> rows = facade.getAuditoria();
        auditoriaTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Sub-view 7: Historico

    private void setupSubView7_Historico() {
        if (colHFecha != null) colHFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHUsuario != null) colHUsuario.setCellValueFactory(data -> data.getValue().usuarioProperty());
        if (colHTipoEvento != null) colHTipoEvento.setCellValueFactory(data -> data.getValue().tipoEventoProperty());
        if (colHRolOModulo != null) colHRolOModulo.setCellValueFactory(data -> data.getValue().rolOModuloProperty());
        if (colHEstado != null) colHEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colHObservacion != null) colHObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (colHEstado != null) {
            colHEstado.setCellFactory(createHistoricoStatusBadgeCell());
        }
    }

    private void loadSubView7_Historico() {
        if (historicoTable == null) return;
        List<UsuariosRolesRowModel.HistoricoAccesoRow> rows = facade.getHistorico(currentFilters);
        historicoTable.setItems(FXCollections.observableArrayList(rows));
    }

    // ------------------------------------------------------------------ Action buttons

    private void setupActionButtons() {
        if (nuevoUsuarioBtn != null) {
            nuevoUsuarioBtn.setOnAction(e -> onNuevoUsuario());
        }
        if (actualizarListadoBtn != null) {
            actualizarListadoBtn.setOnAction(e -> {
                showSubView(getCurrentSubViewIndex());
                loadSummaryPanel();
            });
        }
    }

    private int getCurrentSubViewIndex() {
        if (btnDirectorio != null && btnDirectorio.isSelected()) return 0;
        if (btnRoles != null && btnRoles.isSelected()) return 1;
        if (btnPermisos != null && btnPermisos.isSelected()) return 2;
        if (btnSucursales != null && btnSucursales.isSelected()) return 3;
        if (btnSesiones != null && btnSesiones.isSelected()) return 4;
        if (btnAuditoria != null && btnAuditoria.isSelected()) return 5;
        if (btnHistorico != null && btnHistorico.isSelected()) return 6;
        return 0;
    }

    private void goToPage(int page) {
        if (page < 0) return;
        currentPageIndex = page;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Directorio();
    }

    private void applyFilters() {
        String desde = desdePicker != null && desdePicker.getValue() != null ? desdePicker.getValue().toString() : "";
        String hasta = hastaPicker != null && hastaPicker.getValue() != null ? hastaPicker.getValue().toString() : "";

        currentFilters = new UsuariosRolesFilters(
                searchField != null ? searchField.getText() : "",
                rolCombo != null && rolCombo.getValue() != null ? rolCombo.getValue() : "Todos",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "Todos",
                sucursalCombo != null && sucursalCombo.getValue() != null ? sucursalCombo.getValue() : "Todas",
                actividadCombo != null && actividadCombo.getValue() != null ? actividadCombo.getValue() : "Todas",
                desde,
                hasta,
                soloEventosSensiblesCheck != null && soloEventosSensiblesCheck.isSelected()
        );
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);
        loadSubView1_Directorio();
        loadSubView7_Historico();
    }

    private void clearFilters() {
        currentFilters = new UsuariosRolesFilters();
        currentPageIndex = 0;
        currentPageRequest = new PageRequest(currentPageIndex, pageSize);

        if (searchField != null) searchField.clear();
        if (rolCombo != null) rolCombo.getSelectionModel().selectFirst();
        if (estadoCombo != null) estadoCombo.getSelectionModel().selectFirst();
        if (sucursalCombo != null) sucursalCombo.getSelectionModel().selectFirst();
        if (actividadCombo != null) actividadCombo.getSelectionModel().selectFirst();
        if (desdePicker != null) desdePicker.setValue(null);
        if (hastaPicker != null) hastaPicker.setValue(null);
        if (soloEventosSensiblesCheck != null) soloEventosSensiblesCheck.setSelected(false);

        loadSubView1_Directorio();
        loadSubView7_Historico();
    }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnEditar != null) {
            summaryBtnEditar.setOnAction(e -> onEditarUsuario());
        }
        if (summaryBtnCambiarRol != null) {
            summaryBtnCambiarRol.setOnAction(e -> onCambiarRol());
        }
        if (summaryBtnVerAuditoria != null) {
            summaryBtnVerAuditoria.setOnAction(e -> onVerAuditoria());
        }
        if (summaryBtnDesactivar != null) {
            summaryBtnDesactivar.setOnAction(e -> onDesactivar());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(UsuariosRolesSummaryModel.empty());
    }

    private void updateSummaryPanel(UsuariosRolesSummaryModel summary) {
        if (summary == null) return;

        if (summaryUsuario != null) summaryUsuario.setText(summary.usuario() != null ? summary.usuario() : "Sin seleccion");
        if (summaryRolPrincipal != null) summaryRolPrincipal.setText(summary.rolPrincipal() != null ? summary.rolPrincipal() : "");
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal() != null ? summary.sucursal() : "");
        if (summaryEstadoActual != null) summaryEstadoActual.setText(summary.estadoActual() != null ? summary.estadoActual() : "");
        if (summaryUltimoAcceso != null) summaryUltimoAcceso.setText(summary.ultimoAcceso() != null ? summary.ultimoAcceso() : "");

        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Acceso a caja", summary.accesoCaja()),
                    new SummaryFieldModel("Acceso a inventario", summary.accesoInventario()),
                    new SummaryFieldModel("Acceso a reportes", summary.accesoReportes()),
                    new SummaryFieldModel("Nivel de revision", summary.nivelRevision()),
                    new SummaryFieldModel("Ultima accion", summary.ultimaAccion()),
                    new SummaryFieldModel("Observacion", summary.observacionBreve())
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

    // ------------------------------------------------------------------ Status badge cell factories

    private javafx.util.Callback<TableColumn<UsuariosRolesRowModel.UsuarioRow, String>, TableCell<UsuariosRolesRowModel.UsuarioRow, String>> createUsuarioStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<UsuariosRolesRowModel.SucursalUsuarioRow, String>, TableCell<UsuariosRolesRowModel.SucursalUsuarioRow, String>> createSucursalStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<UsuariosRolesRowModel.SesionRow, String>, TableCell<UsuariosRolesRowModel.SesionRow, String>> createSesionStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    private javafx.util.Callback<TableColumn<UsuariosRolesRowModel.HistoricoAccesoRow, String>, TableCell<UsuariosRolesRowModel.HistoricoAccesoRow, String>> createHistoricoStatusBadgeCell() {
        return createGenericStatusBadgeCell();
    }

    @SuppressWarnings("unchecked")
    private <R> javafx.util.Callback<TableColumn<R, String>, TableCell<R, String>> createGenericStatusBadgeCell() {
        return column -> (TableCell<R, String>) new URStatusBadgeTableCell();
    }

    // Helper class to avoid generic issues
    private static class URStatusBadgeTableCell extends TableCell<Object, String> {
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
                        case "activo", "exitoso", "completado", "operativa", "cerrado" ->
                                StatusBadgeModel.success(item);
                        case "pendiente", "en proceso", "temporal", "modificado", "acceso restringido" ->
                                StatusBadgeModel.warning(item);
                        case "inactivo", "bloqueado", "fallido", "rechazado" ->
                                StatusBadgeModel.danger(item);
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
    }

    // ------------------------------------------------------------------ Placeholder actions

    private void onNuevoUsuario() {}
    private void onGuardarPermisos() {}
    private void onEditarUsuario() {}
    private void onCambiarRol() {}
    private void onVerAuditoria() {}
    private void onDesactivar() {}
}

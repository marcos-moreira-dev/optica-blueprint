package com.marcosmoreira.opticademo.modules.configuracion;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controller del modulo de Configuracion del sistema optico.
 * <p>
 * Proporciona una interfaz de tres paneles para la administracion de todos los parametros
 * del sistema: navegacion de categorias en el panel izquierdo, formulario de configuracion
 * en el panel central, y panel derecho con resumen del impacto operativo y recomendaciones.
 * </p>
 * <p>
 * Gestiona nueve categorias de configuracion: General del negocio, Sucursales y operacion,
 * Usuarios roles y permisos, Catalogos maestros, Inventario y abastecimiento,
 * Venta caja y comprobantes, Agenda seguimiento y comunicacion, Laboratorio y entregas,
 * y Apariencia y experiencia de uso. Cada categoria presenta un formulario especializado
 * con campos relevantes y botones de accion propios.
 * </p>
 * <p>
 * La fachada {@link ConfiguracionFacade} proporciona acceso a los datos de configuracion
 * y genera resumenes del impacto operativo de cada cambio propuesto.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ConfiguracionFacade
 * @see ConfiguracionFilters
 * @see ConfiguracionSummaryModel
 */
public class ConfiguracionController {

    // ===== TOP BAR =====
    /** Boton para guardar los cambios realizados en la categoria actual. */
    @FXML private Button guardarCambiosBtn;
    /** Boton para restablecer los valores originales de la vista. */
    @FXML private Button restablecerVistaBtn;
    /** Boton para exportar los parametros de configuracion a un archivo. */
    @FXML private Button exportarParametrosBtn;
    /** Campo de busqueda para filtrar categorias de configuracion. */
    @FXML private TextField searchField;
    /** Boton para limpiar el campo de busqueda. */
    @FXML private Button limpiarBusquedaBtn;

    // ===== LEFT PANEL =====
    /** Lista de categorias de configuracion en el panel izquierdo. */
    @FXML private ListView<String> categoriasListView;

    // ===== CENTER PANEL - host =====
    @FXML private StackPane configuracionContentHostPane;

    // ===== CENTER PANEL - category sections =====
    @FXML private VBox categoriaGeneralSection;
    @FXML private VBox categoriaSucursalesSection;
    @FXML private VBox categoriaUsuariosSection;
    @FXML private VBox categoriaCatalogosSection;
    @FXML private VBox categoriaInventarioSection;
    @FXML private VBox categoriaVentaSection;
    @FXML private VBox categoriaAgendaSection;
    @FXML private VBox categoriaLaboratorioSection;
    @FXML private VBox categoriaAparienciaSection;

    // ===== Category 1: General del negocio =====
    @FXML private TextField generalNombreComercial;
    @FXML private TextField generalRazonSocial;
    @FXML private TextField generalRuc;
    @FXML private TextField generalTelefono;
    @FXML private TextField generalCorreo;
    @FXML private TextField generalDireccion;
    @FXML private TextField generalCiudad;
    @FXML private ComboBox<String> generalSucursalPredeterminada;
    @FXML private ComboBox<String> generalMoneda;
    @FXML private ComboBox<String> generalZonaHoraria;
    @FXML private Button generalGuardarBtn;
    @FXML private Button generalRestablecerBtn;

    // ===== Category 2: Sucursales =====
    @FXML private TableView<ConfiguracionRowModel.SucursalRow> sucursalesTable;
    @FXML private TableColumn<ConfiguracionRowModel.SucursalRow, String> colSucursalNombre;
    @FXML private TableColumn<ConfiguracionRowModel.SucursalRow, String> colSucursalDireccion;
    @FXML private TableColumn<ConfiguracionRowModel.SucursalRow, String> colSucursalHorario;
    @FXML private TableColumn<ConfiguracionRowModel.SucursalRow, String> colSucursalResponsable;
    @FXML private TableColumn<ConfiguracionRowModel.SucursalRow, String> colSucursalEstado;
    @FXML private TextField sucursalDetailNombre;
    @FXML private TextField sucursalDetailTelefono;
    @FXML private TextField sucursalDetailHorario;
    @FXML private TextField sucursalDetailResponsable;
    @FXML private CheckBox sucursalDetailInventarioPropio;
    @FXML private CheckBox sucursalDetailRetiroTrabajos;
    @FXML private CheckBox sucursalDetailCajaHabilitada;
    @FXML private Button sucursalGuardarBtn;
    @FXML private Button sucursalNuevaBtn;
    @FXML private Button sucursalDesactivarBtn;

    // ===== Category 3: Usuarios =====
    @FXML private TableView<ConfiguracionRowModel.UsuarioRow> usuariosTable;
    @FXML private TableColumn<ConfiguracionRowModel.UsuarioRow, String> colUsuarioNombre;
    @FXML private TableColumn<ConfiguracionRowModel.UsuarioRow, String> colUsuarioRol;
    @FXML private TableColumn<ConfiguracionRowModel.UsuarioRow, String> colUsuarioSucursal;
    @FXML private TableColumn<ConfiguracionRowModel.UsuarioRow, String> colUsuarioEstado;
    @FXML private TextField usuarioDetailNombre;
    @FXML private ComboBox<String> usuarioDetailRol;
    @FXML private ComboBox<String> usuarioDetailSucursal;
    @FXML private CheckBox usuarioDetailAccesoCaja;
    @FXML private CheckBox usuarioDetailAccesoInventario;
    @FXML private CheckBox usuarioDetailAccesoReportes;
    @FXML private CheckBox usuarioDetailAccesoConfiguracion;
    @FXML private Button usuarioGuardarBtn;
    @FXML private Button usuarioNuevoBtn;
    @FXML private Button usuarioDesactivarBtn;

    // ===== Category 4: Catalogos =====
    @FXML private ListView<String> catalogosListView;
    @FXML private TableView<ConfiguracionRowModel.CatalogoRow> catalogoValoresTable;
    @FXML private TableColumn<ConfiguracionRowModel.CatalogoRow, String> colCatalogoValor;
    @FXML private TableColumn<ConfiguracionRowModel.CatalogoRow, String> colCatalogoEstado;
    @FXML private TableColumn<ConfiguracionRowModel.CatalogoRow, String> colCatalogoCategoria;
    @FXML private Button catalogoGuardarBtn;
    @FXML private Button catalogoNuevoValorBtn;
    @FXML private Button catalogoDesactivarBtn;

    // ===== Category 5: Inventario =====
    @FXML private TextField inventarioStockMinimo;
    @FXML private TextField inventarioUbicacionDefault;
    @FXML private CheckBox inventarioAlertasCriticas;
    @FXML private CheckBox inventarioGeneracionAviso;
    @FXML private CheckBox inventarioPermitirPedido;
    @FXML private ComboBox<String> inventarioProveedorPreferido;
    @FXML private CheckBox inventarioRecepcionParcial;
    @FXML private CheckBox inventarioRequiereResponsable;
    @FXML private CheckBox inventarioActualizaInventario;
    @FXML private Button inventarioGuardarBtn;
    @FXML private Button inventarioRestablecerBtn;

    // ===== Category 6: Venta =====
    @FXML private TextField ventaPrefijoOrden;
    @FXML private TextField ventaPrefijoComprobante;
    @FXML private TextField ventaSecuenciaInicial;
    @FXML private CheckBox ventaPermiteAbonos;
    @FXML private TextField ventaDescuentoMaximo;
    @FXML private CheckBox ventaPermiteSaldoPendiente;
    @FXML private CheckBox ventaMostrarDireccion;
    @FXML private CheckBox ventaMostrarMensaje;
    @FXML private TextField ventaTextoFinal;
    @FXML private Button ventaGuardarBtn;
    @FXML private Button vistaPreviaBtn;

    // ===== Category 7: Agenda =====
    @FXML private ComboBox<String> agendaDuracionCita;
    @FXML private CheckBox agendaReprogramacionRapida;
    @FXML private CheckBox agendaRecallActivo;
    @FXML private TextField agendaDiasAnticipacion;
    @FXML private CheckBox agendaSeguimientoAutomatico;
    @FXML private ComboBox<String> agendaCanalPreferido;
    @FXML private TextField agendaPlantillaTrabajoListo;
    @FXML private TextField agendaPlantillaSaldoPendiente;
    @FXML private Button agendaGuardarBtn;
    @FXML private Button agendaProbarPlantillaBtn;

    // ===== Category 8: Laboratorio =====
    @FXML private TextField laboratorioHabilitados;
    @FXML private TextField laboratorioTiempoPromesa;
    @FXML private CheckBox laboratorioExterno;
    @FXML private CheckBox laboratorioRequiereValidacion;
    @FXML private CheckBox laboratorioBloqueaConSaldo;
    @FXML private CheckBox laboratorioRecordarNoRetirados;
    @FXML private Button laboratorioGuardarBtn;
    @FXML private Button laboratorioRestablecerBtn;

    // ===== Category 9: Apariencia =====
    @FXML private ComboBox<String> aparienciaModo;
    @FXML private CheckBox aparienciaSeguirSistema;
    @FXML private ComboBox<String> aparienciaDensidad;
    @FXML private CheckBox aparienciaMostrarIconos;
    @FXML private CheckBox aparienciaMostrarTooltips;
    @FXML private CheckBox aparienciaConfirmarAcciones;
    @FXML private Button aparienciaGuardarBtn;
    @FXML private Button aparienciaRestablecerBtn;
    @FXML private Button aparienciaVistaPreviaBtn;

    // ===== RIGHT PANEL: Summary =====
    @FXML private Label summaryCategoria;
    @FXML private Label summaryEstadoCambios;
    @FXML private Label summaryAlcanceLabel;
    @FXML private Label summaryImpactoLabel;
    @FXML private Label summaryRecomendacionLabel;
    @FXML private Button summaryGuardarBtn;
    @FXML private Button summaryRestablecerBtn;
    @FXML private Button summaryIrModuloBtn;

    // ===== Facade =====
    /** Fachada que proporciona acceso a los datos de configuracion y resumenes de impacto. */
    private ConfiguracionFacade facade;
    private ConfiguracionFilters currentFilters;

    /** Nombres de las nueve categorias de configuracion disponibles. */
    private static final String[] CATEGORIAS = {
            "General del negocio",
            "Sucursales y operacion",
            "Usuarios, roles y permisos",
            "Catalogos maestros",
            "Inventario y abastecimiento",
            "Venta, caja y comprobantes",
            "Agenda, seguimiento y comunicacion",
            "Laboratorio y entregas",
            "Apariencia y experiencia de uso"
    };

    private final VBox[] categorySections = new VBox[9];

    /**
     * Metodo de inicializacion invocado por JavaFX al cargar el FXML.
     * <p>
     * Instancia {@link ConfiguracionFacade}, configura la lista de categorias del panel izquierdo,
     * los botones de la barra superior, los formularios de cada categoria, y selecciona
     * la primera categoria por defecto cargando sus datos.
     * </p>
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new ConfiguracionFacade(store);
        this.currentFilters = new ConfiguracionFilters();

        setupCategoriesList();
        setupTopBarButtons();
        setupCategoryForms();
        selectCategory(0);
    }

    // ===== Categories list =====
    private void setupCategoriesList() {
        ObservableList<String> items = FXCollections.observableArrayList(CATEGORIAS);
        categoriasListView.setItems(items);
        categoriasListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setTooltip(new Tooltip(getTooltipForCategory(item)));
                }
            }
        });
        categoriasListView.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null && newVal.intValue() >= 0) {
                        selectCategory(newVal.intValue());
                    }
                }
        );
    }

    private String getTooltipForCategory(String categoria) {
        return switch (categoria) {
            case "General del negocio" -> "Configurar datos institucionales base de la optica";
            case "Sucursales y operacion" -> "Configurar sedes, horarios y reglas operativas por sucursal";
            case "Usuarios, roles y permisos" -> "Administrar perfiles de acceso y control del sistema";
            case "Catalogos maestros" -> "Administrar listas base que usa toda la aplicacion";
            case "Inventario y abastecimiento" -> "Definir reglas globales de stock, reposicion y recepcion";
            case "Venta, caja y comprobantes" -> "Definir numeraciones, pagos, abonos y salida documental";
            case "Agenda, seguimiento y comunicacion" -> "Definir reglas de cita, recall y contacto con clientes";
            case "Laboratorio y entregas" -> "Configurar tiempos promesa, estados y validacion de trabajos";
            case "Apariencia y experiencia de uso" -> "Definir apariencia visual y comportamiento general de la interfaz";
            default -> "";
        };
    }

    // ===== Top bar =====
    private void setupTopBarButtons() {
        if (guardarCambiosBtn != null) {
            guardarCambiosBtn.setOnAction(e -> onSaveChanges());
        }
        if (restablecerVistaBtn != null) {
            restablecerVistaBtn.setOnAction(e -> onRestablecerVista());
        }
        if (exportarParametrosBtn != null) {
            exportarParametrosBtn.setOnAction(e -> onExportarParametros());
        }
        if (limpiarBusquedaBtn != null) {
            limpiarBusquedaBtn.setOnAction(e -> {
                if (searchField != null) searchField.clear();
                currentFilters = new ConfiguracionFilters();
                categoriasListView.getSelectionModel().clearSelection();
            });
        }
        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> {
                currentFilters = new ConfiguracionFilters(newVal);
                filterCategories(newVal);
            });
        }
    }

    private void filterCategories(String searchText) {
        ConfiguracionFilters filters = new ConfiguracionFilters(searchText);
        ObservableList<String> filtered = FXCollections.observableArrayList();
        for (String cat : CATEGORIAS) {
            if (filters.matches(cat)) {
                filtered.add(cat);
            }
        }
        categoriasListView.setItems(filtered.isEmpty() ? FXCollections.observableArrayList(CATEGORIAS) : filtered);
    }

    // ===== Category selection =====
    private void selectCategory(int index) {
        if (index < 0 || index >= categorySections.length) return;

        // Hide all sections and remove from layout
        for (VBox section : categorySections) {
            if (section != null) {
                section.setVisible(false);
                section.setManaged(false);
            }
        }

        // Show selected and add to layout
        VBox selected = categorySections[index];
        if (selected != null) {
            selected.setVisible(true);
            selected.setManaged(true);
        }

        // Update summary panel
        updateSummaryPanel(CATEGORIAS[index]);

        // Load category data
        loadCategoryData(index);
    }

    private void updateSummaryPanel(String categoria) {
        ConfiguracionSummaryModel summary = facade.buildSummary(categoria);
        if (summaryCategoria != null) summaryCategoria.setText(categoria);
        if (summaryEstadoCambios != null) summaryEstadoCambios.setText(summary.estadoCambios());
        if (summaryAlcanceLabel != null) summaryAlcanceLabel.setText(summary.alcance());
        if (summaryImpactoLabel != null) summaryImpactoLabel.setText(summary.impactoOperativo());
        if (summaryRecomendacionLabel != null) summaryRecomendacionLabel.setText(summary.recomendacion());
    }

    // ===== Load category data =====
    private void loadCategoryData(int index) {
        switch (index) {
            case 0 -> loadGeneralNegocio();
            case 1 -> loadSucursales();
            case 2 -> loadUsuarios();
            case 3 -> loadCatalogos();
            case 4 -> loadInventario();
            case 5 -> loadVenta();
            case 6 -> loadAgenda();
            case 7 -> loadLaboratorio();
            case 8 -> loadApariencia();
        }
    }

    // ── Category 1 ──
    private void loadGeneralNegocio() {
        ConfiguracionFacade.GeneralNegocio g = facade.getGeneralNegocio();
        if (generalNombreComercial != null) generalNombreComercial.setText(g.nombreComercial());
        if (generalRazonSocial != null) generalRazonSocial.setText(g.razonSocial());
        if (generalRuc != null) generalRuc.setText(g.ruc());
        if (generalTelefono != null) generalTelefono.setText(g.telefono());
        if (generalCorreo != null) generalCorreo.setText(g.correo());
        if (generalDireccion != null) generalDireccion.setText(g.direccion());
        if (generalCiudad != null) generalCiudad.setText(g.ciudad());
        if (generalSucursalPredeterminada != null) {
            generalSucursalPredeterminada.getItems().setAll("Matriz Centro", "Norte Mall");
            generalSucursalPredeterminada.setValue(g.sucursalPredeterminada());
        }
        if (generalMoneda != null) {
            generalMoneda.getItems().setAll("USD", "EUR");
            generalMoneda.setValue(g.moneda());
        }
        if (generalZonaHoraria != null) {
            generalZonaHoraria.getItems().setAll("America/Guayaquil", "America/Bogota", "America/Lima");
            generalZonaHoraria.setValue(g.zonaHoraria());
        }
    }

    // ── Category 2 ──
    private void loadSucursales() {
        if (colSucursalNombre != null) colSucursalNombre.setCellValueFactory(d -> d.getValue().nombreProperty());
        if (colSucursalDireccion != null) colSucursalDireccion.setCellValueFactory(d -> d.getValue().direccionProperty());
        if (colSucursalHorario != null) colSucursalHorario.setCellValueFactory(d -> d.getValue().horarioProperty());
        if (colSucursalResponsable != null) colSucursalResponsable.setCellValueFactory(d -> d.getValue().responsableProperty());
        if (colSucursalEstado != null) colSucursalEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        ObservableList<ConfiguracionRowModel.SucursalRow> sucursalData = FXCollections.observableArrayList(facade.getSucursales());
        if (sucursalesTable != null) {
            sucursalesTable.setItems(sucursalData);
        }

        if (sucursalDetailNombre != null && sucursalDetailTelefono != null) {
            sucursalesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            sucursalDetailNombre.setText(newVal.nombre());
                            sucursalDetailTelefono.setText("04 600 0010");
                            sucursalDetailHorario.setText(newVal.horario());
                            sucursalDetailResponsable.setText(newVal.responsable());
                            sucursalDetailInventarioPropio.setSelected(true);
                            sucursalDetailRetiroTrabajos.setSelected(true);
                            sucursalDetailCajaHabilitada.setSelected(true);
                        }
                    }
            );
            if (!sucursalData.isEmpty()) sucursalesTable.getSelectionModel().selectFirst();
        }
    }

    // ── Category 3 ──
    private void loadUsuarios() {
        if (colUsuarioNombre != null) colUsuarioNombre.setCellValueFactory(d -> d.getValue().usuarioProperty());
        if (colUsuarioRol != null) colUsuarioRol.setCellValueFactory(d -> d.getValue().rolProperty());
        if (colUsuarioSucursal != null) colUsuarioSucursal.setCellValueFactory(d -> d.getValue().sucursalProperty());
        if (colUsuarioEstado != null) colUsuarioEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        ObservableList<ConfiguracionRowModel.UsuarioRow> usuarioData = FXCollections.observableArrayList(facade.getUsuarios());
        if (usuariosTable != null) {
            usuariosTable.setItems(usuarioData);
        }

        if (usuarioDetailNombre != null) {
            usuariosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            usuarioDetailNombre.setText(newVal.usuario());
                            if (usuarioDetailRol != null) {
                                usuarioDetailRol.getItems().setAll("Administrador general", "Recepcion", "Asesor de ventas", "Tecnico optico");
                                usuarioDetailRol.setValue(newVal.rol());
                            }
                            if (usuarioDetailSucursal != null) {
                                usuarioDetailSucursal.getItems().setAll("Matriz Centro", "Norte Mall");
                                usuarioDetailSucursal.setValue(newVal.sucursal());
                            }
                            usuarioDetailAccesoCaja.setSelected(true);
                            usuarioDetailAccesoInventario.setSelected(true);
                            usuarioDetailAccesoReportes.setSelected(true);
                            usuarioDetailAccesoConfiguracion.setSelected(true);
                        }
                    }
            );
            if (!usuarioData.isEmpty()) usuariosTable.getSelectionModel().selectFirst();
        }
    }

    // ── Category 4 ──
    private void loadCatalogos() {
        if (catalogosListView != null) {
            catalogosListView.setItems(FXCollections.observableArrayList(facade.getCatalogoTypes()));
            catalogosListView.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldVal, newVal) -> {
                        if (newVal != null) loadCatalogoValues(newVal);
                    }
            );
            catalogosListView.getSelectionModel().selectFirst();
        }
    }

    private void loadCatalogoValues(String tipo) {
        if (colCatalogoValor != null) colCatalogoValor.setCellValueFactory(d -> d.getValue().valorProperty());
        if (colCatalogoEstado != null) colCatalogoEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
        if (colCatalogoCategoria != null) colCatalogoCategoria.setCellValueFactory(d -> d.getValue().categoriaProperty());

        if (catalogoValoresTable != null) {
            ObservableList<ConfiguracionRowModel.CatalogoRow> data = FXCollections.observableArrayList(
                    facade.getCatalogoValues(tipo)
            );
            catalogoValoresTable.setItems(data);
        }
    }

    // ── Category 5 ──
    private void loadInventario() {
        ConfiguracionFacade.InventarioConfig cfg = facade.getInventarioConfig();
        if (inventarioStockMinimo != null) inventarioStockMinimo.setText(cfg.stockMinimo());
        if (inventarioUbicacionDefault != null) inventarioUbicacionDefault.setText(cfg.ubicacionDefault());
        if (inventarioAlertasCriticas != null) inventarioAlertasCriticas.setSelected(cfg.alertasCriticas());
        if (inventarioGeneracionAviso != null) inventarioGeneracionAviso.setSelected(cfg.generacionAviso());
        if (inventarioPermitirPedido != null) inventarioPermitirPedido.setSelected(true);
        if (inventarioProveedorPreferido != null) {
            inventarioProveedorPreferido.getItems().setAll("Configurable", "Proveedor A", "Proveedor B");
            inventarioProveedorPreferido.setValue("Configurable");
        }
        if (inventarioRecepcionParcial != null) inventarioRecepcionParcial.setSelected(cfg.recepcionParcial());
        if (inventarioRequiereResponsable != null) inventarioRequiereResponsable.setSelected(true);
        if (inventarioActualizaInventario != null) inventarioActualizaInventario.setSelected(true);
    }

    // ── Category 6 ──
    private void loadVenta() {
        ConfiguracionFacade.VentaConfig cfg = facade.getVentaConfig();
        if (ventaPrefijoOrden != null) ventaPrefijoOrden.setText(cfg.prefijoOrden());
        if (ventaPrefijoComprobante != null) ventaPrefijoComprobante.setText(cfg.prefijoComprobante());
        if (ventaSecuenciaInicial != null) ventaSecuenciaInicial.setText(cfg.secuenciaInicial());
        if (ventaPermiteAbonos != null) ventaPermiteAbonos.setSelected(cfg.permiteAbonos());
        if (ventaDescuentoMaximo != null) ventaDescuentoMaximo.setText(cfg.descuentoMaximo());
        if (ventaPermiteSaldoPendiente != null) ventaPermiteSaldoPendiente.setSelected(cfg.permiteSaldoPendiente());
        if (ventaMostrarDireccion != null) ventaMostrarDireccion.setSelected(true);
        if (ventaMostrarMensaje != null) ventaMostrarMensaje.setSelected(true);
        if (ventaTextoFinal != null) ventaTextoFinal.setText("Gracias por confiar en nuestra optica");
    }

    // ── Category 7 ──
    private void loadAgenda() {
        ConfiguracionFacade.AgendaConfig cfg = facade.getAgendaConfig();
        if (agendaDuracionCita != null) {
            agendaDuracionCita.getItems().setAll("15 minutos", "30 minutos", "45 minutos", "60 minutos");
            agendaDuracionCita.setValue(cfg.duracionCita());
        }
        if (agendaReprogramacionRapida != null) agendaReprogramacionRapida.setSelected(true);
        if (agendaRecallActivo != null) agendaRecallActivo.setSelected(cfg.recallActivo());
        if (agendaDiasAnticipacion != null) agendaDiasAnticipacion.setText(cfg.diasAnticipacion());
        if (agendaSeguimientoAutomatico != null) agendaSeguimientoAutomatico.setSelected(true);
        if (agendaCanalPreferido != null) {
            agendaCanalPreferido.getItems().setAll("SMS", "WhatsApp", "Llamada", "Correo");
            agendaCanalPreferido.setValue(cfg.canalPreferido());
        }
        if (agendaPlantillaTrabajoListo != null) agendaPlantillaTrabajoListo.setText("Su trabajo esta listo para retirar");
        if (agendaPlantillaSaldoPendiente != null) agendaPlantillaSaldoPendiente.setText("Su orden mantiene un saldo pendiente");
    }

    // ── Category 8 ──
    private void loadLaboratorio() {
        ConfiguracionFacade.LaboratorioConfig cfg = facade.getLaboratorioConfig();
        if (laboratorioHabilitados != null) laboratorioHabilitados.setText(cfg.laboratoriosHabilitados());
        if (laboratorioTiempoPromesa != null) laboratorioTiempoPromesa.setText(cfg.tiempoPromesa());
        if (laboratorioExterno != null) laboratorioExterno.setSelected(true);
        if (laboratorioRequiereValidacion != null) laboratorioRequiereValidacion.setSelected(cfg.requiereValidacion());
        if (laboratorioBloqueaConSaldo != null) laboratorioBloqueaConSaldo.setSelected(cfg.bloqueaConSaldo());
        if (laboratorioRecordarNoRetirados != null) laboratorioRecordarNoRetirados.setSelected(true);
    }

    // ── Category 9 ──
    private void loadApariencia() {
        ConfiguracionFacade.AparienciaConfig cfg = facade.getAparienciaConfig();
        if (aparienciaModo != null) {
            aparienciaModo.getItems().setAll("Claro", "Oscuro", "Seguir sistema");
            aparienciaModo.setValue(cfg.modoApariencia());
        }
        if (aparienciaSeguirSistema != null) aparienciaSeguirSistema.setSelected(false);
        if (aparienciaDensidad != null) {
            aparienciaDensidad.getItems().setAll("Compacta", "Normal", "Comoda");
            aparienciaDensidad.setValue(cfg.densidadVisual());
        }
        if (aparienciaMostrarIconos != null) aparienciaMostrarIconos.setSelected(cfg.mostrarIconos());
        if (aparienciaMostrarTooltips != null) aparienciaMostrarTooltips.setSelected(cfg.mostrarTooltips());
        if (aparienciaConfirmarAcciones != null) aparienciaConfirmarAcciones.setSelected(cfg.confirmarAccionesCriticas());
    }

    // ===== Setup category sections =====
    private void setupCategoryForms() {
        categorySections[0] = categoriaGeneralSection;
        categorySections[1] = categoriaSucursalesSection;
        categorySections[2] = categoriaUsuariosSection;
        categorySections[3] = categoriaCatalogosSection;
        categorySections[4] = categoriaInventarioSection;
        categorySections[5] = categoriaVentaSection;
        categorySections[6] = categoriaAgendaSection;
        categorySections[7] = categoriaLaboratorioSection;
        categorySections[8] = categoriaAparienciaSection;

        // Enforce proper GridPane spacing across all category sections
        enforceGridSpacing();

        // Category button actions
        if (generalGuardarBtn != null) generalGuardarBtn.setOnAction(e -> onSaveChanges());
        if (generalRestablecerBtn != null) generalRestablecerBtn.setOnAction(e -> loadGeneralNegocio());
        if (sucursalGuardarBtn != null) sucursalGuardarBtn.setOnAction(e -> onSaveChanges());
        if (usuarioGuardarBtn != null) usuarioGuardarBtn.setOnAction(e -> onSaveChanges());
        if (catalogoGuardarBtn != null) catalogoGuardarBtn.setOnAction(e -> onSaveChanges());
        if (inventarioGuardarBtn != null) inventarioGuardarBtn.setOnAction(e -> onSaveChanges());
        if (inventarioRestablecerBtn != null) inventarioRestablecerBtn.setOnAction(e -> loadInventario());
        if (ventaGuardarBtn != null) ventaGuardarBtn.setOnAction(e -> onSaveChanges());
        if (agendaGuardarBtn != null) agendaGuardarBtn.setOnAction(e -> onSaveChanges());
        if (laboratorioGuardarBtn != null) laboratorioGuardarBtn.setOnAction(e -> onSaveChanges());
        if (laboratorioRestablecerBtn != null) laboratorioRestablecerBtn.setOnAction(e -> loadLaboratorio());
        if (aparienciaGuardarBtn != null) aparienciaGuardarBtn.setOnAction(e -> onSaveChanges());
        if (aparienciaRestablecerBtn != null) aparienciaRestablecerBtn.setOnAction(e -> loadApariencia());

        // Summary panel buttons
        if (summaryGuardarBtn != null) summaryGuardarBtn.setOnAction(e -> onSaveChanges());
        if (summaryRestablecerBtn != null) summaryRestablecerBtn.setOnAction(e -> onRestablecerVista());
    }

    /**
     * Ensure all GridPanes inside category sections have proper spacing (8px hgap/vgap)
     * to prevent content truncation and improve readability.
     * Also enables wrapText on all TextFields to prevent truncation with "...".
     */
    private void enforceGridSpacing() {
        // Run after layout pass so nodes are in the scene graph
        Platform.runLater(() -> {
            for (VBox section : categorySections) {
                if (section == null) continue;
                applyGridSpacingRecursive(section, 8, 8);
                enableTextWrapRecursive(section);
            }
        });
    }

    private void applyGridSpacingRecursive(Node node, double hgap, double vgap) {
        if (node instanceof GridPane) {
            GridPane grid = (GridPane) node;
            grid.setHgap(hgap);
            grid.setVgap(vgap);
        }
        if (node instanceof Parent) {
            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
                applyGridSpacingRecursive(child, hgap, vgap);
            }
        }
    }

    private void enableTextWrapRecursive(Node node) {
        if (node instanceof TextField) {
            // TextField doesn't support wrapText; ensure promptText is short
            // No action needed since FXML handles promptText
        }
        if (node instanceof Label) {
            Label label = (Label) node;
            if (label.getText() != null && label.getText().length() > 40) {
                label.setWrapText(true);
            }
        }
        if (node instanceof Parent) {
            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
                enableTextWrapRecursive(child);
            }
        }
    }

    // ===== Action handlers =====
    private void onSaveChanges() {
        int idx = categoriasListView.getSelectionModel().getSelectedIndex();
        if (idx >= 0 && idx < CATEGORIAS.length) {
            String categoria = CATEGORIAS[idx];
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Configuracion");
            alert.setHeaderText("Cambios guardados");
            alert.setContentText("La configuracion de '" + categoria + "' ha sido guardada correctamente.");
            alert.showAndWait();
        }
    }

    private void onRestablecerVista() {
        int idx = categoriasListView.getSelectionModel().getSelectedIndex();
        loadCategoryData(idx);
    }

    private void onExportarParametros() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Configuracion");
        alert.setHeaderText("Exportar parametros");
        alert.setContentText("La configuracion actual se exportaria a un archivo de texto o JSON.");
        alert.showAndWait();
    }
}

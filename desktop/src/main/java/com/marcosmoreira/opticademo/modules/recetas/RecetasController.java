package com.marcosmoreira.opticademo.modules.recetas;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import com.marcosmoreira.opticademo.shared.ui.model.SummaryFieldModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controlador para el modulo de Recetas del sistema Optica.
 * <p>
 * Gestiona la visualizacion y administracion de recetas oftalmologicas asociadas a clientes.
 * El modulo se organiza en 5 sub-vistas intercambiables: Receta Actual, Historial de recetas,
 * Medidas opticas, Recomendaciones y Vinculacion con ordenes de laboratorio. Cada sub-vista
 * se activa mediante botones de tipo toggle que garantizan una unica vista visible a la vez.
 * </p>
 * <p>
 * El panel derecho muestra el contexto del cliente seleccionado, incluyendo informacion
 * personal, estado de la receta y datos de ordenes activas. Toda la logica de negocio
 * se delega en {@link RecetasFacade}, manteniendo una separacion limpia entre
 * la capa de presentacion y el dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see RecetasFacade
 * @see RecetasRowModel
 * @see RecetasFilters
 * @see RecetasSummaryModel
 */
public class RecetasController {

    // ---- Top bar ----

    /** Boton para crear una nueva receta. */
    @FXML
    private Button nuevaRecetaBtn;

    /** Boton para refrescar todos los datos del modulo. */
    @FXML
    private Button actualizarModuloBtn;

    /** Boton para imprimir la receta actualmente seleccionada. */
    @FXML
    private Button imprimirRecetaBtn;

    /** Label de contexto: nombre del cliente asociado a la receta. */
    @FXML
    private Label ctxCliente;

    /** Label de contexto: codigo interno del cliente. */
    @FXML
    private Label ctxCodigo;

    /** Label de contexto: fecha de la receta activa. */
    @FXML
    private Label ctxRecetaActiva;

    /** Label de contexto: estado de la receta actual. */
    @FXML
    private Label ctxEstado;

    /** Label de contexto: profesional que emitio la receta. */
    @FXML
    private Label ctxProfesional;

    /** Label de contexto: sucursal donde se registro la receta. */
    @FXML
    private Label ctxSucursal;

    /** Campo de texto para busqueda en filtros. */
    @FXML
    private TextField searchField;

    /** Combo filtrable para filtrar recetas por estado. */
    @FXML
    private ComboBox<String> estadoCombo;

    /** Combo filtrable para filtrar recetas por profesional. */
    @FXML
    private ComboBox<String> profesionalCombo;

    /** Combo filtrable para filtrar por fecha de inicio del periodo. */
    @FXML
    private ComboBox<String> desdeCombo;

    /** Combo filtrable para filtrar por fecha de fin del periodo. */
    @FXML
    private ComboBox<String> hastaCombo;

    /** Boton para limpiar todos los filtros aplicados. */
    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----

    /** Boton toggle para la sub-vista de Receta Actual. */
    @FXML
    private ToggleButton subViewRecetaActualBtn;

    /** Boton toggle para la sub-vista de Historial de recetas. */
    @FXML
    private ToggleButton subViewHistorialBtn;

    /** Boton toggle para la sub-vista de Medidas opticas. */
    @FXML
    private ToggleButton subViewMedidasBtn;

    /** Boton toggle para la sub-vista de Recomendaciones. */
    @FXML
    private ToggleButton subViewRecomendacionesBtn;

    /** Boton toggle para la sub-vista de Vinculacion con ordenes. */
    @FXML
    private ToggleButton subViewVinculacionBtn;

    // ---- Sub-view containers ----

    /** Contenedor de la sub-vista de Receta Actual. */
    @FXML
    private VBox recetaActualSection;

    /** Contenedor de la sub-vista de Historial de recetas. */
    @FXML
    private VBox historialSection;

    /** Contenedor de la sub-vista de Medidas opticas. */
    @FXML
    private VBox medidasSection;

    /** Contenedor de la sub-vista de Recomendaciones. */
    @FXML
    private VBox recomendacionesSection;

    /** Contenedor de la sub-vista de Vinculacion con ordenes. */
    @FXML
    private VBox vinculacionSection;

    // ---- Receta Actual fields ----

    /** Label: valor de esfera (SPH) para ojo derecho (OD). */
    @FXML
    private Label raOdSph;

    /** Label: valor de cilindro (CYL) para ojo derecho (OD). */
    @FXML
    private Label raOdCyl;

    /** Label: valor de eje (AXIS) para ojo derecho (OD). */
    @FXML
    private Label raOdAxis;

    /** Label: valor de prisma para ojo derecho (OD). */
    @FXML
    private Label raOdPrism;

    /** Label: valor de base para ojo derecho (OD). */
    @FXML
    private Label raOdBase;

    /** Label: valor de adicion (ADD) para ojo derecho (OD). */
    @FXML
    private Label raOdAdd;

    /** Label: valor de esfera (SPH) para ojo izquierdo (OI). */
    @FXML
    private Label raOiSph;

    /** Label: valor de cilindro (CYL) para ojo izquierdo (OI). */
    @FXML
    private Label raOiCyl;

    /** Label: valor de eje (AXIS) para ojo izquierdo (OI). */
    @FXML
    private Label raOiAxis;

    /** Label: valor de prisma para ojo izquierdo (OI). */
    @FXML
    private Label raOiPrism;

    /** Label: valor de base para ojo izquierdo (OI). */
    @FXML
    private Label raOiBase;

    /** Label: valor de adicion (ADD) para ojo izquierdo (OI). */
    @FXML
    private Label raOiAdd;

    /** Label: distancia pupilar (PD) en milimetros. */
    @FXML
    private Label raPd;

    /** Label: uso principal asignado a la receta. */
    @FXML
    private Label raUsoPrincipal;

    /** Label: fecha de vigencia de la receta. */
    @FXML
    private Label raVigencia;

    /** Label: tratamiento sugerido para la receta. */
    @FXML
    private Label raTratamiento;

    /** Label: observaciones especiales de la receta. */
    @FXML
    private Label raObservacion;

    /** Label: recomendacion asociada a la receta. */
    @FXML
    private Label raRecomendacion;

    /** Boton para editar la receta actual. */
    @FXML
    private Button editarRecetaBtn;

    /** Boton para guardar los cambios realizados en la receta. */
    @FXML
    private Button guardarCambiosBtn;

    /** Boton para duplicar la receta actual. */
    @FXML
    private Button duplicarRecetaBtn;

    /** Boton para pasar la receta a una venta optica. */
    @FXML
    private Button pasarAVentaBtn;

    // ---- Historial table ----

    /** Tabla que lista el historial de recetas del cliente. */
    @FXML
    private TableView<RecetasRowModel.HistorialRow> historialTable;

    /** Columna de fecha de la receta en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistFecha;

    /** Columna de profesional en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistProfesional;

    /** Columna de estado de la receta en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistEstado;

    /** Columna de resumen de ojo derecho (OD) en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistOdResumen;

    /** Columna de resumen de ojo izquierdo (OI) en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistOiResumen;

    /** Columna de distancia pupilar (PD) en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistPd;

    /** Columna de observacion breve en el historial. */
    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistObservacion;

    /** Boton para visualizar una receta del historial. */
    @FXML
    private Button verRecetaBtn;

    /** Boton para duplicar una receta desde el historial. */
    @FXML
    private Button duplicarDesdeHistorialBtn;

    /** Boton para comparar una receta del historial con la actual. */
    @FXML
    private Button compararConActualBtn;

    /** Boton para imprimir una receta del historial. */
    @FXML
    private Button imprimirRecetaHistBtn;

    // ---- Medidas table ----

    /** Tabla que lista las medidas opticas del cliente. */
    @FXML
    private TableView<RecetasRowModel.MedicionRow> medidasTable;

    /** Columna de parametro medido (PD, altura, etc.). */
    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedParametro;

    /** Columna de valor de la medicion. */
    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedValor;

    /** Columna de unidad de medida. */
    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedUnidad;

    /** Columna de observacion de la medicion. */
    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedObservacion;

    /** Boton para editar las medidas opticas. */
    @FXML
    private Button editarMedidasBtn;

    /** Boton para guardar los parametros de medicion. */
    @FXML
    private Button guardarParametrosBtn;

    /** Boton para copiar medidas desde una receta existente. */
    @FXML
    private Button copiarDesdeRecetaBtn;

    /** Boton para pasar las medidas a una venta optica. */
    @FXML
    private Button pasarAVentaMedBtn;

    // ---- Recomendaciones ----

    /** Label: recomendacion principal del profesional. */
    @FXML
    private Label recRecomendacionPrincipal;

    /** Label: observacion tecnica asociada a la receta. */
    @FXML
    private Label recObservacionTecnica;

    /** Label: notas internas del equipo. */
    @FXML
    private Label recNotasInternas;

    /** Boton para editar las recomendaciones. */
    @FXML
    private Button editarRecomendacionesBtn;

    /** Boton para guardar las observaciones. */
    @FXML
    private Button guardarObservacionesBtn;

    /** Boton para usar una plantilla de recomendaciones. */
    @FXML
    private Button usarPlantillaBtn;

    /** Boton para enviar recomendaciones a una orden de laboratorio. */
    @FXML
    private Button enviarAOrdenBtn;

    // ---- Vinculacion ----

    /** Label: estado de la vinculacion con ordenes. */
    @FXML
    private Label vincEstadoVinculacion;

    /** Label: referencia de la orden vinculada. */
    @FXML
    private Label vincRefOrden;

    /** Label: estado de la orden vinculada. */
    @FXML
    private Label vincEstadoOrden;

    /** Label: saldo pendiente de la orden. */
    @FXML
    private Label vincSaldo;

    /** Label: estado de la entrega asociada. */
    @FXML
    private Label vincEntrega;

    /** Boton para ver la orden vinculada. */
    @FXML
    private Button verOrdenBtn;

    /** Boton para actualizar el estado de la vinculacion. */
    @FXML
    private Button actualizarEstadoBtn;

    // ---- Right panel: Client context ----

    /** Label del nombre del cliente en el panel de resumen. */
    @FXML
    private Label summaryNombre;

    /** Label del codigo del cliente en el panel de resumen. */
    @FXML
    private Label summaryCodigo;

    /** Label del telefono en el panel de resumen. */
    @FXML
    private Label summaryTelefono;

    /** Label de la ultima visita en el panel de resumen. */
    @FXML
    private Label summaryUltimaVisita;

    /** Label de la sucursal en el panel de resumen. */
    @FXML
    private Label summarySucursal;

    /** Label del estado de la receta en el panel de resumen. */
    @FXML
    private Label summaryEstadoReceta;

    /** Label de la distancia pupilar (PD) en el panel de resumen. */
    @FXML
    private Label summaryPd;

    /** Label del profesional en el panel de resumen. */
    @FXML
    private Label summaryProfesional;

    /** Label de ordenes activas en el panel de resumen. */
    @FXML
    private Label summaryOrdenesActivas;

    /** Label de entregas pendientes en el panel de resumen. */
    @FXML
    private Label summaryEntregasPendientes;

    /** Label de saldo pendiente en el panel de resumen. */
    @FXML
    private Label summarySaldoPendiente;

    /** Contenedor de campos adicionales en el panel de resumen. */
    @FXML
    private VBox summaryFieldsContainer;

    /**
     * Fachada que centraliza la logica de negocio del modulo Recetas.
     * Proporciona metodos para consultar recetas, historial, medidas y vinculaciones.
     */
    private RecetasFacade facade;

    /** Filtros actualmente aplicados en la busqueda de recetas. */
    private RecetasFilters currentFilters;

    /** Identificador del cliente actualmente seleccionado. */
    private String currentClienteId;

    /**
     * Inicializa el controlador y configura todos los componentes del modulo de Recetas.
     * <p>
     * Instancia {@link RecetasFacade} con el {@link DemoStore} global, configura los combos
     * filtrables mediante {@link ComboBoxFactory}, establece el grupo de toggles para las
     * 5 sub-vistas, inicializa las tablas de historial y medidas con sus
     * {@code cellValueFactory}, y carga los datos de la receta del cliente por defecto
     * (Sofia Ramirez, CLI-001).
     * </p>
     *
     * @see RecetasFacade
     * @see ComboBoxFactory
     * @see App#getDemoStore()
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new RecetasFacade(store);
        this.currentFilters = new RecetasFilters();

        // Default to Sofia Ramirez
        this.currentClienteId = "CLI-001";

        setupFilterCombos();
        setupSubViewToggle();
        setupActionButtons();
        setupHistorialTable();
        setupMedidasTable();

        // Load data
        loadCurrentRecipe();
        loadHistorial();
        loadMedidas();
        loadVinculacion();
        loadRecomendaciones();

        // Show receta actual by default
        showSubView("recetaActual");
        updateClientContext();
    }

    // ==================== Filter combos ====================

    private void setupFilterCombos() {
        if (estadoCombo != null) {
            List<String> estados = facade.getEstadosReceta();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (profesionalCombo != null) {
            List<String> profesionales = facade.getProfesionales();
            String[] items = profesionales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Profesional", "Todos", items);
            replaceInParent(profesionalCombo, combo);
            profesionalCombo = combo;
            profesionalCombo.setOnAction(e -> applyFilters());
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
        if (subViewRecetaActualBtn != null) {
            subViewRecetaActualBtn.setToggleGroup(group);
            subViewRecetaActualBtn.setOnAction(e -> showSubView("recetaActual"));
        }
        if (subViewHistorialBtn != null) {
            subViewHistorialBtn.setToggleGroup(group);
            subViewHistorialBtn.setOnAction(e -> showSubView("historial"));
        }
        if (subViewMedidasBtn != null) {
            subViewMedidasBtn.setToggleGroup(group);
            subViewMedidasBtn.setOnAction(e -> showSubView("medidas"));
        }
        if (subViewRecomendacionesBtn != null) {
            subViewRecomendacionesBtn.setToggleGroup(group);
            subViewRecomendacionesBtn.setOnAction(e -> showSubView("recomendaciones"));
        }
        if (subViewVinculacionBtn != null) {
            subViewVinculacionBtn.setToggleGroup(group);
            subViewVinculacionBtn.setOnAction(e -> showSubView("vinculacion"));
        }
    }

    private void showSubView(String view) {
        boolean showRecetaActual = "recetaActual".equals(view);
        boolean showHistorial = "historial".equals(view);
        boolean showMedidas = "medidas".equals(view);
        boolean showRecomendaciones = "recomendaciones".equals(view);
        boolean showVinculacion = "vinculacion".equals(view);

        if (recetaActualSection != null) recetaActualSection.setVisible(showRecetaActual);
        if (recetaActualSection != null) recetaActualSection.setManaged(showRecetaActual);
        if (historialSection != null) historialSection.setVisible(showHistorial);
        if (historialSection != null) historialSection.setManaged(showHistorial);
        if (medidasSection != null) medidasSection.setVisible(showMedidas);
        if (medidasSection != null) medidasSection.setManaged(showMedidas);
        if (recomendacionesSection != null) recomendacionesSection.setVisible(showRecomendaciones);
        if (recomendacionesSection != null) recomendacionesSection.setManaged(showRecomendaciones);
        if (vinculacionSection != null) vinculacionSection.setVisible(showVinculacion);
        if (vinculacionSection != null) vinculacionSection.setManaged(showVinculacion);
    }

    // ==================== Action buttons ====================

    private void setupActionButtons() {
        if (nuevaRecetaBtn != null) nuevaRecetaBtn.setOnAction(e -> onNuevaReceta());
        if (actualizarModuloBtn != null) actualizarModuloBtn.setOnAction(e -> refreshAll());
        if (imprimirRecetaBtn != null) imprimirRecetaBtn.setOnAction(e -> onImprimirReceta());

        // Receta Actual
        if (editarRecetaBtn != null) editarRecetaBtn.setOnAction(e -> onEditarReceta());
        if (guardarCambiosBtn != null) guardarCambiosBtn.setOnAction(e -> onGuardarCambios());
        if (duplicarRecetaBtn != null) duplicarRecetaBtn.setOnAction(e -> onDuplicarReceta());
        if (pasarAVentaBtn != null) pasarAVentaBtn.setOnAction(e -> onPasarAVenta());

        // Historial
        if (verRecetaBtn != null) verRecetaBtn.setOnAction(e -> onVerReceta());
        if (duplicarDesdeHistorialBtn != null) duplicarDesdeHistorialBtn.setOnAction(e -> onDuplicarDesdeHistorial());
        if (compararConActualBtn != null) compararConActualBtn.setOnAction(e -> onCompararConActual());
        if (imprimirRecetaHistBtn != null) imprimirRecetaHistBtn.setOnAction(e -> onImprimirReceta());

        // Medidas
        if (editarMedidasBtn != null) editarMedidasBtn.setOnAction(e -> onEditarMedidas());
        if (guardarParametrosBtn != null) guardarParametrosBtn.setOnAction(e -> onGuardarParametros());
        if (copiarDesdeRecetaBtn != null) copiarDesdeRecetaBtn.setOnAction(e -> onCopiarDesdeReceta());
        if (pasarAVentaMedBtn != null) pasarAVentaMedBtn.setOnAction(e -> onPasarAVenta());

        // Recomendaciones
        if (editarRecomendacionesBtn != null) editarRecomendacionesBtn.setOnAction(e -> onEditarRecomendaciones());
        if (guardarObservacionesBtn != null) guardarObservacionesBtn.setOnAction(e -> onGuardarObservaciones());
        if (usarPlantillaBtn != null) usarPlantillaBtn.setOnAction(e -> onUsarPlantilla());
        if (enviarAOrdenBtn != null) enviarAOrdenBtn.setOnAction(e -> onEnviarAOrden());

        // Vinculacion
        if (verOrdenBtn != null) verOrdenBtn.setOnAction(e -> onVerOrden());
        if (actualizarEstadoBtn != null) actualizarEstadoBtn.setOnAction(e -> onActualizarEstado());
    }

    // ==================== Load data ====================

    private void loadCurrentRecipe() {
        RecetasSummaryModel recipe = facade.getCurrentRecipe(currentClienteId);
        if (recipe == null) return;

        if (raOdSph != null) raOdSph.setText(recipe.odSph());
        if (raOdCyl != null) raOdCyl.setText(recipe.odCyl());
        if (raOdAxis != null) raOdAxis.setText(recipe.odAxis());
        if (raOdPrism != null) raOdPrism.setText("-");
        if (raOdBase != null) raOdBase.setText("-");
        if (raOdAdd != null) raOdAdd.setText(recipe.add());
        if (raOiSph != null) raOiSph.setText(recipe.oiSph());
        if (raOiCyl != null) raOiCyl.setText(recipe.oiCyl());
        if (raOiAxis != null) raOiAxis.setText(recipe.oiAxis());
        if (raOiPrism != null) raOiPrism.setText("-");
        if (raOiBase != null) raOiBase.setText("-");
        if (raOiAdd != null) raOiAdd.setText(recipe.add());
        if (raPd != null) raPd.setText(recipe.pd());
        if (raUsoPrincipal != null) raUsoPrincipal.setText(recipe.usoPrincipal());
        if (raVigencia != null) raVigencia.setText(recipe.fechaReceta());
        if (raTratamiento != null) raTratamiento.setText(recipe.tratamientoSugerido());
        if (raObservacion != null) raObservacion.setText("Sin observaciones especiales");
        if (raRecomendacion != null) raRecomendacion.setText(recipe.tratamientoSugerido() + " recomendado por " + recipe.profesional());

        // Update quick context
        if (ctxCliente != null) ctxCliente.setText(recipe.cliente());
        if (ctxCodigo != null) ctxCodigo.setText(recipe.codigo());
        if (ctxRecetaActiva != null) ctxRecetaActiva.setText(recipe.fechaReceta());
        if (ctxEstado != null) ctxEstado.setText(recipe.estado());
        if (ctxProfesional != null) ctxProfesional.setText(recipe.profesional());
        if (ctxSucursal != null) ctxSucursal.setText("Matriz Centro");
    }

    private void loadHistorial() {
        List<RecetasRowModel.HistorialRow> rows = facade.getHistorial(currentClienteId);
        ObservableList<RecetasRowModel.HistorialRow> data = FXCollections.observableArrayList(rows);
        if (historialTable != null) {
            historialTable.setItems(data);
        }
    }

    private void loadMedidas() {
        List<RecetasRowModel.MedicionRow> rows = facade.getMedidas(currentClienteId);
        ObservableList<RecetasRowModel.MedicionRow> data = FXCollections.observableArrayList(rows);
        if (medidasTable != null) {
            medidasTable.setItems(data);
        }
    }

    private void loadVinculacion() {
        List<RecetasRowModel.VinculacionRow> rows = facade.getVinculaciones(currentClienteId);
        if (rows.isEmpty()) {
            if (vincEstadoVinculacion != null) vincEstadoVinculacion.setText("Sin vinculaciones activas");
            if (vincRefOrden != null) vincRefOrden.setText("-");
            if (vincEstadoOrden != null) vincEstadoOrden.setText("-");
            if (vincSaldo != null) vincSaldo.setText("-");
            if (vincEntrega != null) vincEntrega.setText("-");
            return;
        }

        // Show first vinculacion
        RecetasRowModel.VinculacionRow first = rows.get(0);
        if (vincEstadoVinculacion != null) vincEstadoVinculacion.setText(first.estadoOrden());
        if (vincRefOrden != null) vincRefOrden.setText(first.referenciaOrden());
        if (vincEstadoOrden != null) vincEstadoOrden.setText(first.estadoOrden());
        if (vincSaldo != null) vincSaldo.setText(first.saldo());
        if (vincEntrega != null) vincEntrega.setText(first.entrega());
    }

    private void loadRecomendaciones() {
        List<String> recs = facade.getRecomendaciones(currentClienteId);
        if (recs.isEmpty()) {
            if (recRecomendacionPrincipal != null) recRecomendacionPrincipal.setText("Sin recomendaciones");
            if (recObservacionTecnica != null) recObservacionTecnica.setText("-");
            if (recNotasInternas != null) recNotasInternas.setText("-");
            return;
        }

        StringBuilder principal = new StringBuilder();
        StringBuilder tecnica = new StringBuilder();
        StringBuilder notas = new StringBuilder();

        for (int i = 0; i < recs.size(); i++) {
            if (i == 0) {
                principal.append(recs.get(i));
            } else if (i == 1) {
                tecnica.append(recs.get(i));
            } else {
                if (notas.length() > 0) notas.append("\n");
                notas.append(recs.get(i));
            }
        }

        if (recRecomendacionPrincipal != null) recRecomendacionPrincipal.setText(principal.toString());
        if (recObservacionTecnica != null) recObservacionTecnica.setText(tecnica.length() > 0 ? tecnica.toString() : "-");
        if (recNotasInternas != null) recNotasInternas.setText(notas.length() > 0 ? notas.toString() : "-");
    }

    // ==================== Historial table ====================

    private void setupHistorialTable() {
        if (colHistFecha != null) colHistFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colHistProfesional != null) colHistProfesional.setCellValueFactory(data -> data.getValue().profesionalProperty());
        if (colHistEstado != null) colHistEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colHistOdResumen != null) colHistOdResumen.setCellValueFactory(data -> data.getValue().odResumenProperty());
        if (colHistOiResumen != null) colHistOiResumen.setCellValueFactory(data -> data.getValue().oiResumenProperty());
        if (colHistPd != null) colHistPd.setCellValueFactory(data -> data.getValue().pdProperty());
        if (colHistObservacion != null) colHistObservacion.setCellValueFactory(data -> data.getValue().observacionBreveProperty());
    }

    // ==================== Medidas table ====================

    private void setupMedidasTable() {
        if (colMedParametro != null) colMedParametro.setCellValueFactory(data -> data.getValue().parametroProperty());
        if (colMedValor != null) colMedValor.setCellValueFactory(data -> data.getValue().valorProperty());
        if (colMedUnidad != null) colMedUnidad.setCellValueFactory(data -> data.getValue().unidadProperty());
        if (colMedObservacion != null) colMedObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());
    }

    // ==================== Client context (right panel) ====================

    private void updateClientContext() {
        DemoStore store = App.getDemoStore();
        Cliente cliente = store.clientes.stream()
                .filter(c -> c.getId().equals(currentClienteId))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            clearSummary();
            return;
        }

        RecetasFacade.ClientContextModel ctx = facade.buildClientContext(cliente);
        if (ctx == null) {
            clearSummary();
            return;
        }

        if (summaryNombre != null) summaryNombre.setText(ctx.nombre());
        if (summaryCodigo != null) summaryCodigo.setText(ctx.codigo());
        if (summaryTelefono != null) summaryTelefono.setText(ctx.telefono());
        if (summaryUltimaVisita != null) summaryUltimaVisita.setText(ctx.ultimaVisita());
        if (summarySucursal != null) summarySucursal.setText(ctx.sucursal());
        if (summaryEstadoReceta != null) summaryEstadoReceta.setText(ctx.estadoReceta());
        if (summaryPd != null) summaryPd.setText(ctx.pd());
        if (summaryProfesional != null) summaryProfesional.setText(ctx.profesional());
        if (summaryOrdenesActivas != null) summaryOrdenesActivas.setText(ctx.ordenesActivas());
        if (summaryEntregasPendientes != null) summaryEntregasPendientes.setText(ctx.entregasPendientes());
        if (summarySaldoPendiente != null) summarySaldoPendiente.setText(ctx.saldoPendiente());

        // Build additional fields
        if (summaryFieldsContainer != null) {
            summaryFieldsContainer.getChildren().clear();
            List<SummaryFieldModel> fields = List.of(
                    new SummaryFieldModel("Telefono", ctx.telefono()),
                    new SummaryFieldModel("Ultima visita", ctx.ultimaVisita()),
                    new SummaryFieldModel("Sucursal", ctx.sucursal()),
                    new SummaryFieldModel("Estado receta", ctx.estadoReceta()),
                    new SummaryFieldModel("PD", ctx.pd()),
                    new SummaryFieldModel("Profesional", ctx.profesional()),
                    new SummaryFieldModel("Ordenes activas", ctx.ordenesActivas()),
                    new SummaryFieldModel("Entregas pendientes", ctx.entregasPendientes()),
                    new SummaryFieldModel("Saldo pendiente", ctx.saldoPendiente(), true)
            );

            for (SummaryFieldModel field : fields) {
                Label fieldLabel = new Label();
                fieldLabel.getStyleClass().add("summary-field-label");
                fieldLabel.setText(field.label() + ":");

                Label fieldValue = new Label();
                fieldValue.getStyleClass().add("summary-field-value");
                if (field.isHighlighted()) {
                    fieldValue.getStyleClass().add("summary-field-highlighted");
                }
                fieldValue.setText(field.value() != null ? field.value() : "-");

                VBox fieldBox = new VBox(2, fieldLabel, fieldValue);
                fieldBox.getStyleClass().add("summary-field-row");
                summaryFieldsContainer.getChildren().add(fieldBox);
            }
        }
    }

    private void clearSummary() {
        if (summaryNombre != null) summaryNombre.setText("Sin seleccion");
        if (summaryCodigo != null) summaryCodigo.setText("");
        if (summaryTelefono != null) summaryTelefono.setText("");
        if (summaryUltimaVisita != null) summaryUltimaVisita.setText("");
        if (summarySucursal != null) summarySucursal.setText("");
        if (summaryEstadoReceta != null) summaryEstadoReceta.setText("");
        if (summaryPd != null) summaryPd.setText("");
        if (summaryProfesional != null) summaryProfesional.setText("");
        if (summaryOrdenesActivas != null) summaryOrdenesActivas.setText("");
        if (summaryEntregasPendientes != null) summaryEntregasPendientes.setText("");
        if (summarySaldoPendiente != null) summarySaldoPendiente.setText("");
        if (summaryFieldsContainer != null) summaryFieldsContainer.getChildren().clear();
    }

    // ==================== Filters ====================

    private void applyFilters() {
        currentFilters = new RecetasFilters(
                searchField != null ? searchField.getText() : "",
                estadoCombo != null && estadoCombo.getValue() != null ? estadoCombo.getValue() : "",
                profesionalCombo != null && profesionalCombo.getValue() != null ? profesionalCombo.getValue() : "",
                desdeCombo != null && desdeCombo.getValue() != null ? desdeCombo.getValue() : "",
                hastaCombo != null && hastaCombo.getValue() != null ? hastaCombo.getValue() : ""
        );
        refreshAll();
    }

    private void clearFilters() {
        currentFilters = new RecetasFilters();
        if (searchField != null) searchField.clear();
        if (estadoCombo != null && estadoCombo.getSelectionModel().getSelectedIndex() >= 0) {
            estadoCombo.getSelectionModel().selectFirst();
        }
        if (profesionalCombo != null && profesionalCombo.getSelectionModel().getSelectedIndex() >= 0) {
            profesionalCombo.getSelectionModel().selectFirst();
        }
        refreshAll();
    }

    // ==================== Refresh ====================

    private void refreshAll() {
        loadCurrentRecipe();
        loadHistorial();
        loadMedidas();
        loadVinculacion();
        loadRecomendaciones();
        updateClientContext();
    }

    // ==================== Placeholder actions ====================

    /** Maneja la creacion de una nueva receta. Metodo placeholder. */
    private void onNuevaReceta() { }
    /** Maneja la impresion de la receta actual. Metodo placeholder. */
    private void onImprimirReceta() { }
    /** Maneja la edicion de la receta actual. Metodo placeholder. */
    private void onEditarReceta() { }
    /** Maneja el guardado de cambios en la receta. Metodo placeholder. */
    private void onGuardarCambios() { }
    /** Maneja la duplicacion de la receta actual. Metodo placeholder. */
    private void onDuplicarReceta() { }
    /** Maneja la conversion de receta a venta optica. Metodo placeholder. */
    private void onPasarAVenta() { }
    /** Maneja la visualizacion de una receta del historial. Metodo placeholder. */
    private void onVerReceta() { }
    /** Maneja la duplicacion desde el historial. Metodo placeholder. */
    private void onDuplicarDesdeHistorial() { }
    /** Maneja la comparacion con la receta actual. Metodo placeholder. */
    private void onCompararConActual() { }
    /** Maneja la edicion de medidas opticas. Metodo placeholder. */
    private void onEditarMedidas() { }
    /** Maneja el guardado de parametros de medicion. Metodo placeholder. */
    private void onGuardarParametros() { }
    /** Maneja la copia de medidas desde una receta. Metodo placeholder. */
    private void onCopiarDesdeReceta() { }
    /** Maneja la edicion de recomendaciones. Metodo placeholder. */
    private void onEditarRecomendaciones() { }
    /** Maneja el guardado de observaciones. Metodo placeholder. */
    private void onGuardarObservaciones() { }
    /** Maneja el uso de plantillas de recomendaciones. Metodo placeholder. */
    private void onUsarPlantilla() { }
    /** Maneja el envio de recomendaciones a una orden. Metodo placeholder. */
    private void onEnviarAOrden() { }
    /** Maneja la visualizacion de la orden vinculada. Metodo placeholder. */
    private void onVerOrden() { }
    /** Maneja la actualizacion del estado de vinculacion. Metodo placeholder. */
    private void onActualizarEstado() { }
}

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
 * Controller for the Recetas module.
 * Manages 5 sub-views with a persistent right panel for client context.
 * Clean separation: no business logic, only UI wiring and facade delegation.
 */
public class RecetasController {

    // ---- Top bar ----
    @FXML
    private Button nuevaRecetaBtn;

    @FXML
    private Button actualizarModuloBtn;

    @FXML
    private Button imprimirRecetaBtn;

    // ---- Quick context labels ----
    @FXML
    private Label ctxCliente;

    @FXML
    private Label ctxCodigo;

    @FXML
    private Label ctxRecetaActiva;

    @FXML
    private Label ctxEstado;

    @FXML
    private Label ctxProfesional;

    @FXML
    private Label ctxSucursal;

    // ---- Filter bar ----
    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> profesionalCombo;

    @FXML
    private ComboBox<String> desdeCombo;

    @FXML
    private ComboBox<String> hastaCombo;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton subViewRecetaActualBtn;

    @FXML
    private ToggleButton subViewHistorialBtn;

    @FXML
    private ToggleButton subViewMedidasBtn;

    @FXML
    private ToggleButton subViewRecomendacionesBtn;

    @FXML
    private ToggleButton subViewVinculacionBtn;

    // ---- Sub-view containers ----
    @FXML
    private VBox recetaActualSection;

    @FXML
    private VBox historialSection;

    @FXML
    private VBox medidasSection;

    @FXML
    private VBox recomendacionesSection;

    @FXML
    private VBox vinculacionSection;

    // ---- Receta Actual fields ----
    @FXML
    private Label raOdSph;

    @FXML
    private Label raOdCyl;

    @FXML
    private Label raOdAxis;

    @FXML
    private Label raOdPrism;

    @FXML
    private Label raOdBase;

    @FXML
    private Label raOdAdd;

    @FXML
    private Label raOiSph;

    @FXML
    private Label raOiCyl;

    @FXML
    private Label raOiAxis;

    @FXML
    private Label raOiPrism;

    @FXML
    private Label raOiBase;

    @FXML
    private Label raOiAdd;

    @FXML
    private Label raPd;

    @FXML
    private Label raUsoPrincipal;

    @FXML
    private Label raVigencia;

    @FXML
    private Label raTratamiento;

    @FXML
    private Label raObservacion;

    @FXML
    private Label raRecomendacion;

    @FXML
    private Button editarRecetaBtn;

    @FXML
    private Button guardarCambiosBtn;

    @FXML
    private Button duplicarRecetaBtn;

    @FXML
    private Button pasarAVentaBtn;

    // ---- Historial table ----
    @FXML
    private TableView<RecetasRowModel.HistorialRow> historialTable;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistFecha;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistProfesional;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistEstado;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistOdResumen;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistOiResumen;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistPd;

    @FXML
    private TableColumn<RecetasRowModel.HistorialRow, String> colHistObservacion;

    @FXML
    private Button verRecetaBtn;

    @FXML
    private Button duplicarDesdeHistorialBtn;

    @FXML
    private Button compararConActualBtn;

    @FXML
    private Button imprimirRecetaHistBtn;

    // ---- Medidas table ----
    @FXML
    private TableView<RecetasRowModel.MedicionRow> medidasTable;

    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedParametro;

    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedValor;

    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedUnidad;

    @FXML
    private TableColumn<RecetasRowModel.MedicionRow, String> colMedObservacion;

    @FXML
    private Button editarMedidasBtn;

    @FXML
    private Button guardarParametrosBtn;

    @FXML
    private Button copiarDesdeRecetaBtn;

    @FXML
    private Button pasarAVentaMedBtn;

    // ---- Recomendaciones ----
    @FXML
    private Label recRecomendacionPrincipal;

    @FXML
    private Label recObservacionTecnica;

    @FXML
    private Label recNotasInternas;

    @FXML
    private Button editarRecomendacionesBtn;

    @FXML
    private Button guardarObservacionesBtn;

    @FXML
    private Button usarPlantillaBtn;

    @FXML
    private Button enviarAOrdenBtn;

    // ---- Vinculacion ----
    @FXML
    private Label vincEstadoVinculacion;

    @FXML
    private Label vincRefOrden;

    @FXML
    private Label vincEstadoOrden;

    @FXML
    private Label vincSaldo;

    @FXML
    private Label vincEntrega;

    @FXML
    private Button verOrdenBtn;

    @FXML
    private Button actualizarEstadoBtn;

    // ---- Right panel: Client context ----
    @FXML
    private Label summaryNombre;

    @FXML
    private Label summaryCodigo;

    @FXML
    private Label summaryTelefono;

    @FXML
    private Label summaryUltimaVisita;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryEstadoReceta;

    @FXML
    private Label summaryPd;

    @FXML
    private Label summaryProfesional;

    @FXML
    private Label summaryOrdenesActivas;

    @FXML
    private Label summaryEntregasPendientes;

    @FXML
    private Label summarySaldoPendiente;

    @FXML
    private VBox summaryFieldsContainer;

    // ---- Facade ----
    private RecetasFacade facade;
    private RecetasFilters currentFilters;
    private String currentClienteId;

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

    private void onNuevaReceta() { }
    private void onImprimirReceta() { }
    private void onEditarReceta() { }
    private void onGuardarCambios() { }
    private void onDuplicarReceta() { }
    private void onPasarAVenta() { }
    private void onVerReceta() { }
    private void onDuplicarDesdeHistorial() { }
    private void onCompararConActual() { }
    private void onEditarMedidas() { }
    private void onGuardarParametros() { }
    private void onCopiarDesdeReceta() { }
    private void onEditarRecomendaciones() { }
    private void onGuardarObservaciones() { }
    private void onUsarPlantilla() { }
    private void onEnviarAOrden() { }
    private void onVerOrden() { }
    private void onActualizarEstado() { }
}

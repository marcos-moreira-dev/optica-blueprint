package com.marcosmoreira.opticademo.modules.ventaoptica;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.demo.seed.SharedSeedSupport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller for the Venta Optica 7-stage wizard.
 * Clean separation: no business logic, only UI wiring and facade delegation.
 */
public class VentaOpticaController {

    // ---- Top bar ----
    @FXML
    private Label topbarTitle;

    @FXML
    private Label topbarSubtitle;

    @FXML
    private Button nuevaOrdenBtn;

    @FXML
    private Button guardarBorradorBtn;

    @FXML
    private Button cancelarOrdenBtn;

    // ---- Order context bar ----
    @FXML
    private Label ctxCliente;

    @FXML
    private Label ctxCodigoOrden;

    @FXML
    private Label ctxSucursal;

    @FXML
    private Label ctxEstado;

    @FXML
    private Label ctxReceta;

    @FXML
    private Label ctxResponsable;

    // ---- Stage toggle buttons ----
    @FXML
    private ToggleButton stageClienteBtn;

    @FXML
    private ToggleButton stageRecetaBtn;

    @FXML
    private ToggleButton stageMonturaBtn;

    @FXML
    private ToggleButton stageLentesBtn;

    @FXML
    private ToggleButton stageMedidasBtn;

    @FXML
    private ToggleButton stageCobroBtn;

    @FXML
    private ToggleButton stageConfirmacionBtn;

    // ---- Content host ----
    @FXML
    private VBox ventaContentHostPane;

    // ---- Stage 1: Client search ----
    @FXML
    private VBox stage1Content;

    @FXML
    private TextField clienteSearchField;

    @FXML
    private TableView<VentaOpticaRowModel.ClientSearchRow> clientesTable;

    @FXML
    private TableColumn<VentaOpticaRowModel.ClientSearchRow, String> colClienteNombre;

    @FXML
    private TableColumn<VentaOpticaRowModel.ClientSearchRow, String> colClienteCodigo;

    @FXML
    private TableColumn<VentaOpticaRowModel.ClientSearchRow, String> colClienteTelefono;

    @FXML
    private TableColumn<VentaOpticaRowModel.ClientSearchRow, String> colClienteUltimaVisita;

    @FXML
    private TableColumn<VentaOpticaRowModel.ClientSearchRow, String> colClienteEstado;

    @FXML
    private TableColumn<VentaOpticaRowModel.ClientSearchRow, String> colClienteSucursal;

    @FXML
    private Button btnSeleccionarCliente;

    // ---- Stage 2: Recipe ----
    @FXML
    private VBox stage2Content;

    @FXML
    private TableView<VentaOpticaRowModel.RecipeRow> recetasTable;

    @FXML
    private TableColumn<VentaOpticaRowModel.RecipeRow, String> colRecetaFecha;

    @FXML
    private TableColumn<VentaOpticaRowModel.RecipeRow, String> colRecetaEstado;

    @FXML
    private TableColumn<VentaOpticaRowModel.RecipeRow, String> colRecetaProfesional;

    @FXML
    private TableColumn<VentaOpticaRowModel.RecipeRow, String> colRecetaOD;

    @FXML
    private TableColumn<VentaOpticaRowModel.RecipeRow, String> colRecetaOI;

    @FXML
    private TableColumn<VentaOpticaRowModel.RecipeRow, String> colRecetaPD;

    @FXML
    private Button btnSeleccionarReceta;

    // ---- Stage 3: Montura ----
    @FXML
    private VBox stage3Content;

    @FXML
    private ComboBox<String> monturaMarcaCombo;

    @FXML
    private ComboBox<String> monturaMaterialCombo;

    @FXML
    private TableView<VentaOpticaRowModel.MonturaRow> monturasTable;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaRef;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaNombre;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaMarca;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaColor;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaPrecio;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaStock;

    @FXML
    private TableColumn<VentaOpticaRowModel.MonturaRow, String> colMonturaSucursal;

    @FXML
    private Button btnSeleccionarMontura;

    // ---- Stage 4: Lentes ----
    @FXML
    private VBox stage4Content;

    @FXML
    private ComboBox<String> lenteTipoCombo;

    @FXML
    private ComboBox<String> lenteIndiceCombo;

    @FXML
    private CheckBox chkAntirreflejo;

    @FXML
    private CheckBox chkFotocromatico;

    @FXML
    private CheckBox chkBlueCut;

    @FXML
    private CheckBox chkPolarizado;

    @FXML
    private Button btnConfirmarLente;

    // ---- Stage 5: Medidas ----
    @FXML
    private VBox stage5Content;

    @FXML
    private TextField pdField;

    @FXML
    private TextField alturaMontajeField;

    @FXML
    private TextField distanciaVertexField;

    @FXML
    private TextField anguloPantoscopicoField;

    @FXML
    private Button btnConfirmarMedidas;

    // ---- Stage 6: Cobro ----
    @FXML
    private VBox stage6Content;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblDescuento;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField abonoField;

    @FXML
    private Label lblSaldo;

    @FXML
    private ComboBox<String> metodoPagoCombo;

    @FXML
    private Button btnConfirmarCobro;

    // ---- Stage 7: Confirmacion ----
    @FXML
    private VBox stage7Content;

    @FXML
    private Label confCliente;

    @FXML
    private Label confCodigo;

    @FXML
    private Label confReceta;

    @FXML
    private Label confMontura;

    @FXML
    private Label confLente;

    @FXML
    private Label confPD;

    @FXML
    private Label confSubtotal;

    @FXML
    private Label confDescuento;

    @FXML
    private Label confAbono;

    @FXML
    private Label confSaldo;

    @FXML
    private Label confEntrega;

    @FXML
    private Label confLaboratorio;

    @FXML
    private TableView<VentaOpticaRowModel.HistoricRow> historialTable;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistFecha;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistOrden;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistCliente;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistMonto;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistMetodo;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistEstado;

    @FXML
    private TableColumn<VentaOpticaRowModel.HistoricRow, String> colHistComprobante;

    @FXML
    private Button btnConfirmarOrden;

    // ---- Summary panel (right side) ----
    @FXML
    private Label sumCliente;

    @FXML
    private Label sumCodigo;

    @FXML
    private Label sumReceta;

    @FXML
    private Label sumMontura;

    @FXML
    private Label sumLente;

    @FXML
    private Label sumPD;

    @FXML
    private Label sumSubtotal;

    @FXML
    private Label sumDescuento;

    @FXML
    private Label sumAbono;

    @FXML
    private Label sumSaldo;

    @FXML
    private Label sumEstado;

    @FXML
    private Label sumEntrega;

    @FXML
    private Label sumLaboratorio;

    // ---- Facade and state ----
    private VentaOpticaFacade facade;
    private VentaOpticaFilters currentFilters;

    // Wizard state
    private int currentStage = 0;
    private static final int STAGE_CLIENTE = 0;
    private static final int STAGE_RECETA = 1;
    private static final int STAGE_MONTURA = 2;
    private static final int STAGE_LENTES = 3;
    private static final int STAGE_MEDIDAS = 4;
    private static final int STAGE_COBRO = 5;
    private static final int STAGE_CONFIRMACION = 6;

    // Selected data
    private String selectedClienteId;
    private String selectedClienteNombre;
    private String selectedReceta;
    private String selectedMonturaRef;
    private String selectedMonturaNombre;
    private String selectedLenteTipo;
    private String selectedPD;
    private double currentSubtotal = 110.0;
    private double currentDescuento = 5.0;
    private double currentAbono = 50.0;

    private ToggleGroup stageToggleGroup;
    private VBox[] stageContents;

    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new VentaOpticaFacade(store);
        this.currentFilters = new VentaOpticaFilters();
        this.stageContents = new VBox[]{
                stage1Content, stage2Content, stage3Content,
                stage4Content, stage5Content, stage6Content, stage7Content
        };

        setupTopBar();
        setupStageToggleGroup();
        setupStage1();
        setupStage2();
        setupStage3();
        setupStage4();
        setupStage5();
        setupStage6();
        setupStage7();
        setupSummaryPanel();
        showStage(STAGE_CLIENTE);
        updateSummaryPanel();
    }

    // ---- Top bar ----
    private void setupTopBar() {
        if (topbarSubtitle != null) {
            topbarSubtitle.setText("Wizard de 7 etapas: Cliente, Receta, Montura, Lentes, Medidas, Cobro y Confirmacion");
        }
        if (nuevaOrdenBtn != null) {
            nuevaOrdenBtn.setOnAction(e -> onNuevaOrden());
        }
        if (guardarBorradorBtn != null) {
            guardarBorradorBtn.setOnAction(e -> onGuardarBorrador());
        }
        if (cancelarOrdenBtn != null) {
            cancelarOrdenBtn.setOnAction(e -> onCancelarOrden());
        }
    }

    // ---- Stage toggle group ----
    private void setupStageToggleGroup() {
        stageToggleGroup = new ToggleGroup();
        ToggleButton[] buttons = {
                stageClienteBtn, stageRecetaBtn, stageMonturaBtn,
                stageLentesBtn, stageMedidasBtn, stageCobroBtn, stageConfirmacionBtn
        };
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] != null) {
                final int stageIndex = i;
                buttons[i].setToggleGroup(stageToggleGroup);
                buttons[i].setUserData(stageIndex);
                buttons[i].setOnAction(e -> navigateToStage(stageIndex));
            }
        }
    }

    private void navigateToStage(int targetStage) {
        // Validate: cannot skip ahead past incomplete required stages
        if (targetStage > currentStage) {
            if (!canAdvanceTo(targetStage)) {
                // Revert toggle selection back to current stage
                if (stageToggleGroup != null) {
                    ToggleButton currentBtn = getToggleButtonForStage(currentStage);
                    if (currentBtn != null) {
                        stageToggleGroup.selectToggle(currentBtn);
                    }
                }
                return;
            }
        }
        showStage(targetStage);
    }

    private boolean canAdvanceTo(int targetStage) {
        // Stage 1 (cliente) must be selected to go to stage 2
        if (targetStage >= STAGE_RECETA && selectedClienteId == null) return false;
        // Stage 2 (receta) must be selected to go to stage 3
        if (targetStage >= STAGE_MONTURA && selectedReceta == null) return false;
        // Stage 3 (montura) must be selected to go to stage 4
        if (targetStage >= STAGE_LENTES && selectedMonturaRef == null) return false;
        // Stage 4 (lente) must be selected to go to stage 5
        if (targetStage >= STAGE_MEDIDAS && selectedLenteTipo == null) return false;
        // Stage 5 (medidas) must have PD to go to stage 6
        if (targetStage >= STAGE_COBRO && (selectedPD == null || selectedPD.isBlank())) return false;
        // Stage 6 (cobro) must have payment to go to stage 7
        if (targetStage >= STAGE_CONFIRMACION) {
            // Allow if at least abono is set (can be 0)
        }
        return true;
    }

    private ToggleButton getToggleButtonForStage(int stage) {
        ToggleButton[] buttons = {
                stageClienteBtn, stageRecetaBtn, stageMonturaBtn,
                stageLentesBtn, stageMedidasBtn, stageCobroBtn, stageConfirmacionBtn
        };
        return (stage >= 0 && stage < buttons.length) ? buttons[stage] : null;
    }

    private void showStage(int stage) {
        this.currentStage = stage;
        for (int i = 0; i < stageContents.length; i++) {
            if (stageContents[i] != null) {
                stageContents[i].setVisible(i == stage);
                stageContents[i].setManaged(i == stage);
            }
        }
        updateOrderContext();
        updateSummaryPanel();
    }

    // ---- Stage 1: Client search ----
    private void setupStage1() {
        if (colClienteNombre != null) {
            colClienteNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        }
        if (colClienteCodigo != null) {
            colClienteCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());
        }
        if (colClienteTelefono != null) {
            colClienteTelefono.setCellValueFactory(data -> data.getValue().telefonoProperty());
        }
        if (colClienteUltimaVisita != null) {
            colClienteUltimaVisita.setCellValueFactory(data -> data.getValue().ultimaVisitaProperty());
        }
        if (colClienteEstado != null) {
            colClienteEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        }
        if (colClienteSucursal != null) {
            colClienteSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        }

        if (clienteSearchField != null) {
            clienteSearchField.textProperty().addListener((obs, old, newVal) -> loadClientSearch());
        }

        if (btnSeleccionarCliente != null) {
            btnSeleccionarCliente.setOnAction(e -> onSeleccionarCliente());
        }

        loadClientSearch();
    }

    private void loadClientSearch() {
        String text = clienteSearchField != null ? clienteSearchField.getText() : "";
        List<VentaOpticaRowModel.ClientSearchRow> rows = facade.searchClientes(text);
        if (clientesTable != null) {
            clientesTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    private void onSeleccionarCliente() {
        if (clientesTable == null) return;
        VentaOpticaRowModel.ClientSearchRow row = clientesTable.getSelectionModel().getSelectedItem();
        if (row == null) {
            showAlert("Seleccion de cliente", "Debe seleccionar un cliente de la lista.");
            return;
        }
        this.selectedClienteNombre = row.nombre();
        this.selectedClienteId = row.codigo();

        // Find the full cliente to get ID
        com.marcosmoreira.opticademo.shared.domain.cliente.Cliente cliente = App.getDemoStore().clientes.stream()
                .filter(c -> c.getCodigoInterno().equals(row.codigo()))
                .findFirst()
                .orElse(null);
        if (cliente != null) {
            this.selectedClienteId = cliente.getId();
        }

        // Pre-load recipes for this client
        loadRecipesForSelectedClient();
        showStage(STAGE_RECETA);
    }

    // ---- Stage 2: Recipe ----
    private void setupStage2() {
        if (colRecetaFecha != null) {
            colRecetaFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        }
        if (colRecetaEstado != null) {
            colRecetaEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        }
        if (colRecetaProfesional != null) {
            colRecetaProfesional.setCellValueFactory(data -> data.getValue().profesionalProperty());
        }
        if (colRecetaOD != null) {
            colRecetaOD.setCellValueFactory(data -> data.getValue().odResumenProperty());
        }
        if (colRecetaOI != null) {
            colRecetaOI.setCellValueFactory(data -> data.getValue().oiResumenProperty());
        }
        if (colRecetaPD != null) {
            colRecetaPD.setCellValueFactory(data -> data.getValue().pdProperty());
        }

        if (btnSeleccionarReceta != null) {
            btnSeleccionarReceta.setOnAction(e -> onSeleccionarReceta());
        }
    }

    private void loadRecipesForSelectedClient() {
        if (selectedClienteId == null) return;
        List<VentaOpticaRowModel.RecipeRow> recetas = facade.getRecetasForCliente(selectedClienteId);
        if (recetasTable != null) {
            recetasTable.setItems(FXCollections.observableArrayList(recetas));
            if (!recetas.isEmpty()) {
                recetasTable.getSelectionModel().selectFirst();
            }
        }
    }

    private void onSeleccionarReceta() {
        if (recetasTable == null) return;
        VentaOpticaRowModel.RecipeRow row = recetasTable.getSelectionModel().getSelectedItem();
        if (row == null) {
            showAlert("Seleccion de receta", "Debe seleccionar una receta de la lista.");
            return;
        }
        this.selectedReceta = row.fecha() + " - " + row.profesional();
        this.selectedPD = row.pd();

        // Pre-load monturas
        loadMonturas();
        showStage(STAGE_MONTURA);
    }

    // ---- Stage 3: Montura ----
    private void setupStage3() {
        if (colMonturaRef != null) {
            colMonturaRef.setCellValueFactory(data -> data.getValue().referenciaProperty());
        }
        if (colMonturaNombre != null) {
            colMonturaNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        }
        if (colMonturaMarca != null) {
            colMonturaMarca.setCellValueFactory(data -> data.getValue().marcaProperty());
        }
        if (colMonturaColor != null) {
            colMonturaColor.setCellValueFactory(data -> data.getValue().colorProperty());
        }
        if (colMonturaPrecio != null) {
            colMonturaPrecio.setCellValueFactory(data -> data.getValue().precioProperty());
        }
        if (colMonturaStock != null) {
            colMonturaStock.setCellValueFactory(data -> data.getValue().stockProperty());
        }
        if (colMonturaSucursal != null) {
            colMonturaSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        }

        // Setup filter combos
        if (monturaMarcaCombo != null) {
            List<String> marcas = facade.getMarcasMontura();
            monturaMarcaCombo.setItems(FXCollections.observableArrayList(marcas));
            monturaMarcaCombo.setValue("Todas");
            monturaMarcaCombo.setOnAction(e -> loadMonturas());
        }

        if (monturaMaterialCombo != null) {
            monturaMaterialCombo.setItems(FXCollections.observableArrayList(
                    List.of("Todos", "Acetato", "Metal", "Policarbonato")));
            monturaMaterialCombo.setValue("Todos");
            monturaMaterialCombo.setOnAction(e -> loadMonturas());
        }

        if (btnSeleccionarMontura != null) {
            btnSeleccionarMontura.setOnAction(e -> onSeleccionarMontura());
        }

        loadMonturas();
    }

    private void loadMonturas() {
        currentFilters.setMonturaMarca(monturaMarcaCombo != null ? monturaMarcaCombo.getValue() : "Todas");
        currentFilters.setMonturaMaterial(monturaMaterialCombo != null ? monturaMaterialCombo.getValue() : "Todos");
        List<VentaOpticaRowModel.MonturaRow> rows = facade.getMonturas(currentFilters);
        if (monturasTable != null) {
            monturasTable.setItems(FXCollections.observableArrayList(rows));
        }
    }

    private void onSeleccionarMontura() {
        if (monturasTable == null) return;
        VentaOpticaRowModel.MonturaRow row = monturasTable.getSelectionModel().getSelectedItem();
        if (row == null) {
            showAlert("Seleccion de montura", "Debe seleccionar una montura del catalogo.");
            return;
        }
        this.selectedMonturaRef = row.referencia();
        this.selectedMonturaNombre = row.nombre();
        this.currentSubtotal = 110.0; // Seed: MZ-201 base price
        showStage(STAGE_LENTES);
    }

    // ---- Stage 4: Lentes ----
    private void setupStage4() {
        if (lenteTipoCombo != null) {
            List<String> tipos = facade.getLentesDisponibles();
            lenteTipoCombo.setItems(FXCollections.observableArrayList(tipos));
        }
        if (lenteIndiceCombo != null) {
            lenteIndiceCombo.setItems(FXCollections.observableArrayList(
                    List.of("1.50", "1.56", "1.60", "1.67", "1.74")));
            lenteIndiceCombo.setValue("1.56");
        }
        if (chkAntirreflejo != null) chkAntirreflejo.setSelected(true);
        if (chkFotocromatico != null) chkFotocromatico.setSelected(false);
        if (chkBlueCut != null) chkBlueCut.setSelected(false);
        if (chkPolarizado != null) chkPolarizado.setSelected(false);

        if (btnConfirmarLente != null) {
            btnConfirmarLente.setOnAction(e -> onConfirmarLente());
        }
    }

    private void onConfirmarLente() {
        if (lenteTipoCombo != null && lenteTipoCombo.getValue() != null) {
            this.selectedLenteTipo = lenteTipoCombo.getValue() + " " + lenteIndiceCombo.getValue();
        } else {
            showAlert("Configuracion de lente", "Debe seleccionar un tipo de lente.");
            return;
        }
        showStage(STAGE_MEDIDAS);
    }

    // ---- Stage 5: Medidas ----
    private void setupStage5() {
        // Pre-fill PD from recipe
        if (pdField != null && selectedPD != null) {
            pdField.setText(selectedPD);
        }
        if (alturaMontajeField != null) alturaMontajeField.setText("30");
        if (distanciaVertexField != null) distanciaVertexField.setText("12");
        if (anguloPantoscopicoField != null) anguloPantoscopicoField.setText("8");

        if (btnConfirmarMedidas != null) {
            btnConfirmarMedidas.setOnAction(e -> onConfirmarMedidas());
        }
    }

    private void onConfirmarMedidas() {
        if (pdField != null) {
            this.selectedPD = pdField.getText();
        }
        if (selectedPD == null || selectedPD.isBlank()) {
            showAlert("Medidas", "Debe ingresar la distancia pupilar (PD).");
            return;
        }
        showStage(STAGE_COBRO);
    }

    // ---- Stage 6: Cobro ----
    private void setupStage6() {
        updateCobroDisplay();

        if (metodoPagoCombo != null) {
            metodoPagoCombo.setItems(FXCollections.observableArrayList(
                    SharedSeedSupport.PAYMENT_METHODS));
        }

        if (abonoField != null) {
            abonoField.setText(String.format("%.2f", currentAbono));
            abonoField.textProperty().addListener((obs, old, newVal) -> {
                try {
                    currentAbono = Double.parseDouble(newVal);
                } catch (NumberFormatException ex) {
                    currentAbono = 0;
                }
                updateCobroDisplay();
                updateSummaryPanel();
            });
        }

        if (btnConfirmarCobro != null) {
            btnConfirmarCobro.setOnAction(e -> onConfirmarCobro());
        }
    }

    private void updateCobroDisplay() {
        if (lblSubtotal != null) lblSubtotal.setText(String.format("$%.2f", currentSubtotal));
        if (lblDescuento != null) lblDescuento.setText(String.format("$%.2f", currentDescuento));
        if (lblTotal != null) lblTotal.setText(String.format("$%.2f", currentSubtotal - currentDescuento));
        if (lblSaldo != null) lblSaldo.setText(String.format("$%.2f", Math.max(0, currentSubtotal - currentDescuento - currentAbono)));
    }

    private void onConfirmarCobro() {
        showStage(STAGE_CONFIRMACION);
    }

    // ---- Stage 7: Confirmacion ----
    private void setupStage7() {
        if (colHistFecha != null) {
            colHistFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        }
        if (colHistOrden != null) {
            colHistOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        }
        if (colHistCliente != null) {
            colHistCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        }
        if (colHistMonto != null) {
            colHistMonto.setCellValueFactory(data -> data.getValue().montoProperty());
        }
        if (colHistMetodo != null) {
            colHistMetodo.setCellValueFactory(data -> data.getValue().metodoProperty());
        }
        if (colHistEstado != null) {
            colHistEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        }
        if (colHistComprobante != null) {
            colHistComprobante.setCellValueFactory(data -> data.getValue().comprobanteProperty());
        }

        // Load historical data
        if (historialTable != null) {
            List<VentaOpticaRowModel.HistoricRow> historial = facade.getVentasHistoricas();
            historialTable.setItems(FXCollections.observableArrayList(historial));
        }

        updateConfirmationDisplay();

        if (btnConfirmarOrden != null) {
            btnConfirmarOrden.setOnAction(e -> onConfirmarOrden());
        }
    }

    private void updateConfirmationDisplay() {
        if (confCliente != null) confCliente.setText(selectedClienteNombre != null ? selectedClienteNombre : "Sin seleccionar");
        if (confCodigo != null) confCodigo.setText("OV-" + System.currentTimeMillis() / 1000);
        if (confReceta != null) confReceta.setText(selectedReceta != null ? selectedReceta : "Sin seleccionar");
        if (confMontura != null) confMontura.setText(selectedMonturaNombre != null ? selectedMonturaNombre : "Sin seleccionar");
        if (confLente != null) confLente.setText(selectedLenteTipo != null ? selectedLenteTipo : "Sin seleccionar");
        if (confPD != null) confPD.setText(selectedPD != null ? selectedPD + "mm" : "-");
        if (confSubtotal != null) confSubtotal.setText(String.format("$%.2f", currentSubtotal));
        if (confDescuento != null) confDescuento.setText(String.format("$%.2f", currentDescuento));
        if (confAbono != null) confAbono.setText(String.format("$%.2f", currentAbono));
        if (confSaldo != null) confSaldo.setText(String.format("$%.2f", Math.max(0, currentSubtotal - currentDescuento - currentAbono)));
        if (confEntrega != null) confEntrega.setText("2026-04-25");
        if (confLaboratorio != null) confLaboratorio.setText("Lab. Central");
    }

    private void onConfirmarOrden() {
        showAlert("Orden confirmada", "La orden de venta ha sido registrada exitosamente.\nCodigo: " +
                (confCodigo != null ? confCodigo.getText() : "N/A"));
        onNuevaOrden();
    }

    // ---- Order context bar ----
    private void updateOrderContext() {
        if (ctxCliente != null) ctxCliente.setText(selectedClienteNombre != null ? selectedClienteNombre : "-");
        if (ctxCodigoOrden != null) ctxCodigoOrden.setText("OV-" + (System.currentTimeMillis() / 1000 % 100000));
        if (ctxSucursal != null) ctxSucursal.setText("Matriz Centro");
        if (ctxEstado != null) ctxEstado.setText(currentStage >= STAGE_CONFIRMACION ? "CONFIRMADO" : "BORRADOR");
        if (ctxReceta != null) ctxReceta.setText(selectedReceta != null ? selectedReceta : "-");
        if (ctxResponsable != null) ctxResponsable.setText("Carlos Zambrano");
    }

    // ---- Summary panel ----
    private void setupSummaryPanel() {
    }

    private void updateSummaryPanel() {
        VentaOpticaSummaryModel summary = facade.buildOrderSummary(
                selectedClienteNombre != null ? selectedClienteNombre : "Sin seleccionar",
                "OV-" + (System.currentTimeMillis() / 1000 % 100000),
                selectedReceta != null ? selectedReceta : "Sin seleccionar",
                selectedMonturaNombre != null ? selectedMonturaNombre : "Sin seleccionar",
                selectedLenteTipo != null ? selectedLenteTipo : "Sin seleccionar",
                selectedPD != null ? selectedPD : "",
                currentSubtotal, currentDescuento, currentAbono,
                "2026-04-25", "Lab. Central"
        );

        if (sumCliente != null) sumCliente.setText(summary.cliente());
        if (sumCodigo != null) sumCodigo.setText(summary.codigo());
        if (sumReceta != null) sumReceta.setText(summary.receta());
        if (sumMontura != null) sumMontura.setText(summary.montura());
        if (sumLente != null) sumLente.setText(summary.lente());
        if (sumPD != null) sumPD.setText(summary.pd() + "mm");
        if (sumSubtotal != null) sumSubtotal.setText(summary.subtotal());
        if (sumDescuento != null) sumDescuento.setText(summary.descuento());
        if (sumAbono != null) sumAbono.setText(summary.abono());
        if (sumSaldo != null) sumSaldo.setText(summary.saldo());
        if (sumEstado != null) sumEstado.setText(summary.estado());
        if (sumEntrega != null) sumEntrega.setText(summary.entregaEstimada());
        if (sumLaboratorio != null) sumLaboratorio.setText(summary.laboratorio());
    }

    // ---- Action handlers ----
    private void onNuevaOrden() {
        selectedClienteId = null;
        selectedClienteNombre = null;
        selectedReceta = null;
        selectedMonturaRef = null;
        selectedMonturaNombre = null;
        selectedLenteTipo = null;
        selectedPD = null;
        currentSubtotal = 110.0;
        currentDescuento = 5.0;
        currentAbono = 50.0;
        if (clienteSearchField != null) clienteSearchField.clear();
        if (clientesTable != null) clientesTable.getSelectionModel().clearSelection();
        if (recetasTable != null) recetasTable.getItems().clear();
        if (monturasTable != null) monturasTable.getSelectionModel().clearSelection();
        showStage(STAGE_CLIENTE);
        updateSummaryPanel();
    }

    private void onGuardarBorrador() {
        showAlert("Borrador guardado", "El borrador de la orden ha sido guardado.");
    }

    private void onCancelarOrden() {
        onNuevaOrden();
    }

    // ---- Utility ----
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

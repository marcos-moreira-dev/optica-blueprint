package com.marcosmoreira.opticademo.modules.agenda;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for the Agenda module.
 * Manages 6 sub-views, filter bar, and persistent right panel with appointment detail.
 * Clean separation: no business logic.
 */
public class AgendaController {

    // ---- Top bar ----
    @FXML
    private Button nuevaCitaBtn;

    @FXML
    private Button actualizarAgendaBtn;

    @FXML
    private ComboBox<String> sucursalCombo;

    // ---- Filters ----
    @FXML
    private DatePicker fechaPicker;

    @FXML
    private ComboBox<String> profesionalCombo;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<String> atencionCombo;

    @FXML
    private TextField searchField;

    @FXML
    private Button limpiarFiltrosBtn;

    // ---- Sub-view toggle buttons ----
    @FXML
    private ToggleButton btnDia;

    @FXML
    private ToggleButton btnSemana;

    @FXML
    private ToggleButton btnListaDia;

    @FXML
    private ToggleButton btnListaEspera;

    @FXML
    private ToggleButton btnConfirmaciones;

    @FXML
    private ToggleButton btnHorarios;

    // ---- Center sections (one per sub-view) ----
    @FXML
    private VBox sectionDia;

    @FXML
    private VBox sectionSemana;

    @FXML
    private VBox sectionListaDia;

    @FXML
    private VBox sectionListaEspera;

    @FXML
    private VBox sectionConfirmaciones;

    @FXML
    private VBox sectionHorarios;

    // ---- Sub-view 1: Dia ----
    @FXML
    private VBox diaHourColumn;

    @FXML
    private VBox diaAppointmentsArea;

    @FXML
    private Button btnNuevaCitaDia;

    @FXML
    private Button btnActualizarAgendaDia;

    // ---- Sub-view 2: Semana ----
    @FXML
    private GridPane semanaGrid;

    @FXML
    private Button btnIrAlDia;

    // ---- Sub-view 3: Lista del dia ----
    @FXML
    private Label lblListaDiaCount;

    @FXML
    private TableView<AgendaRowModel.ListaDiaRow> listaDiaTable;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDHora;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDCliente;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDAtencion;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDEstado;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDProfesional;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDSucursal;

    @FXML
    private TableColumn<AgendaRowModel.ListaDiaRow, String> colLDObservacion;

    @FXML
    private Button btnVerDia;

    @FXML
    private Button btnNuevaCitaLista;

    @FXML
    private Button btnExportarAgenda;

    // ---- Sub-view 4: Lista de espera ----
    @FXML
    private Label lblEsperaCount;

    @FXML
    private TableView<AgendaRowModel.EsperaRow> esperaTable;

    @FXML
    private TableColumn<AgendaRowModel.EsperaRow, String> colECliente;

    @FXML
    private TableColumn<AgendaRowModel.EsperaRow, String> colEAtencion;

    @FXML
    private TableColumn<AgendaRowModel.EsperaRow, String> colEFranja;

    @FXML
    private TableColumn<AgendaRowModel.EsperaRow, String> colESucursal;

    @FXML
    private TableColumn<AgendaRowModel.EsperaRow, String> colEPrioridad;

    @FXML
    private TableColumn<AgendaRowModel.EsperaRow, String> colEEstadoContacto;

    @FXML
    private Button btnAsignarCupo;

    @FXML
    private Button btnContactarCliente;

    @FXML
    private Button btnEliminarEspera;

    // ---- Sub-view 5: Confirmaciones ----
    @FXML
    private Label lblConfirmacionesCount;

    @FXML
    private TableView<AgendaRowModel.ConfirmacionRow> confirmacionesTable;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCFecha;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCHora;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCCliente;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCAtencion;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCEstado;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCRecordatorio;

    @FXML
    private TableColumn<AgendaRowModel.ConfirmacionRow, String> colCSucursal;

    @FXML
    private Button btnMarcarConfirmada;

    @FXML
    private Button btnEnviarRecordatorio;

    @FXML
    private Button btnReprogramarCita;

    // ---- Sub-view 6: Horarios y bloqueos ----
    @FXML
    private VBox horarioGeneralContainer;

    @FXML
    private TableView<AgendaRowModel.HorarioRow> bloqueosTable;

    @FXML
    private TableColumn<AgendaRowModel.HorarioRow, String> colBFranja;

    @FXML
    private TableColumn<AgendaRowModel.HorarioRow, String> colBTipo;

    @FXML
    private TableColumn<AgendaRowModel.HorarioRow, String> colBEstado;

    @FXML
    private TableColumn<AgendaRowModel.HorarioRow, String> colBResponsable;

    @FXML
    private Button btnRegistrarBloqueo;

    @FXML
    private Button btnEditarHorario;

    @FXML
    private Button btnQuitarBloqueo;

    // ---- Right panel: Appointment detail ----
    @FXML
    private Label summaryCliente;

    @FXML
    private Label summaryHora;

    @FXML
    private Label summaryAtencion;

    @FXML
    private Label summaryEstado;

    @FXML
    private Label summaryProfesional;

    @FXML
    private Label summarySucursal;

    @FXML
    private Label summaryContacto;

    @FXML
    private Label summaryObservaciones;

    @FXML
    private Label summaryFechaCita;

    @FXML
    private Button summaryBtnEditar;

    @FXML
    private Button summaryBtnConfirmar;

    @FXML
    private Button summaryBtnReprogramar;

    @FXML
    private Button summaryBtnCancelar;

    @FXML
    private Button summaryBtnAtendida;

    @FXML
    private Button summaryBtnAbrirCliente;

    // ---- Facade ----
    private AgendaFacade facade;

    private AgendaFilters currentFilters;

    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new AgendaFacade(store);
        this.currentFilters = new AgendaFilters();

        setupTopBar();
        setupFilterCombos();
        setupSubViewToggle();
        setupSubView1_Dia();
        setupSubView2_Semana();
        setupSubView3_ListaDia();
        setupSubView4_ListaEspera();
        setupSubView5_Confirmaciones();
        setupSubView6_Horarios();
        setupSummaryPanel();

        // Show first sub-view by default
        showSubView(0);
        if (btnDia != null) btnDia.setSelected(true);

        // Load initial data
        loadSummaryPanel();
    }

    // ------------------------------------------------------------------ Top bar

    private void setupTopBar() {
        if (sucursalCombo != null) {
            sucursalCombo.getItems().addAll("Todas", "Matriz Centro", "Norte Mall");
            sucursalCombo.setValue("Todas");
        }

        if (nuevaCitaBtn != null) {
            nuevaCitaBtn.setOnAction(e -> nuevaCita());
        }

        if (actualizarAgendaBtn != null) {
            actualizarAgendaBtn.setOnAction(e -> actualizarAgenda());
        }
    }

    // ------------------------------------------------------------------ Filter combos

    private void setupFilterCombos() {
        if (fechaPicker != null) {
            fechaPicker.setValue(LocalDate.now());
            fechaPicker.valueProperty().addListener((obs, old, val) -> applyFilters());
        }

        if (profesionalCombo != null) {
            List<String> profesionales = facade.getProfesionales();
            String[] items = profesionales.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Profesional", "Todos", items);
            replaceInParent(profesionalCombo, combo);
            profesionalCombo = combo;
            profesionalCombo.setOnAction(e -> applyFilters());
        }

        if (estadoCombo != null) {
            List<String> estados = facade.getEstadosCita();
            String[] items = estados.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Estado", "Todos", items);
            replaceInParent(estadoCombo, combo);
            estadoCombo = combo;
            estadoCombo.setOnAction(e -> applyFilters());
        }

        if (atencionCombo != null) {
            List<String> tipos = facade.getTiposAtencion();
            String[] items = tipos.toArray(new String[0]);
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Atencion", "Todos", items);
            replaceInParent(atencionCombo, combo);
            atencionCombo = combo;
            atencionCombo.setOnAction(e -> applyFilters());
        }

        if (searchField != null) {
            searchField.textProperty().addListener((obs, old, val) -> applyFilters());
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
        if (btnDia != null) {
            btnDia.setToggleGroup(group);
            btnDia.setOnAction(e -> showSubView(0));
        }
        if (btnSemana != null) {
            btnSemana.setToggleGroup(group);
            btnSemana.setOnAction(e -> showSubView(1));
        }
        if (btnListaDia != null) {
            btnListaDia.setToggleGroup(group);
            btnListaDia.setOnAction(e -> showSubView(2));
        }
        if (btnListaEspera != null) {
            btnListaEspera.setToggleGroup(group);
            btnListaEspera.setOnAction(e -> showSubView(3));
        }
        if (btnConfirmaciones != null) {
            btnConfirmaciones.setToggleGroup(group);
            btnConfirmaciones.setOnAction(e -> showSubView(4));
        }
        if (btnHorarios != null) {
            btnHorarios.setToggleGroup(group);
            btnHorarios.setOnAction(e -> showSubView(5));
        }
    }

    private void showSubView(int index) {
        VBox[] sections = {
                sectionDia,
                sectionSemana,
                sectionListaDia,
                sectionListaEspera,
                sectionConfirmaciones,
                sectionHorarios
        };

        for (int i = 0; i < sections.length; i++) {
            if (sections[i] != null) {
                sections[i].setVisible(i == index);
                sections[i].setManaged(i == index);
            }
        }

        switch (index) {
            case 0 -> loadSubView1_Dia();
            case 1 -> loadSubView2_Semana();
            case 2 -> loadSubView3_ListaDia();
            case 3 -> loadSubView4_ListaEspera();
            case 4 -> loadSubView5_Confirmaciones();
            case 5 -> loadSubView6_Horarios();
        }
    }

    // ------------------------------------------------------------------ Sub-view 1: Dia

    private void setupSubView1_Dia() {
        if (btnNuevaCitaDia != null) {
            btnNuevaCitaDia.setOnAction(e -> nuevaCita());
        }
        if (btnActualizarAgendaDia != null) {
            btnActualizarAgendaDia.setOnAction(e -> actualizarAgenda());
        }
    }

    private void loadSubView1_Dia() {
        if (diaHourColumn == null || diaAppointmentsArea == null) return;

        String fecha = fechaPicker.getValue() != null
                ? fechaPicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<AgendaRowModel.CitaDiaRow> citas = facade.getCitasDia(fecha, currentFilters);

        diaHourColumn.getChildren().clear();
        diaAppointmentsArea.getChildren().clear();

        for (int hour = 8; hour <= 18; hour++) {
            String horaStr = String.format("%02d:00", hour);
            Label timeLabel = new Label(horaStr);
            timeLabel.getStyleClass().add("agenda-time-label");
            timeLabel.setPadding(new Insets(6));
            timeLabel.setMinHeight(60);
            timeLabel.setAlignment(Pos.TOP_CENTER);
            diaHourColumn.getChildren().add(timeLabel);

            String horaStrHalf = String.format("%02d:30", hour);
            Label timeLabelHalf = new Label(horaStrHalf);
            timeLabelHalf.getStyleClass().add("agenda-time-label");
            timeLabelHalf.setPadding(new Insets(6));
            timeLabelHalf.setMinHeight(60);
            timeLabelHalf.setAlignment(Pos.TOP_CENTER);
            diaHourColumn.getChildren().add(timeLabelHalf);
        }

        // Place appointment blocks in the appointments area
        for (AgendaRowModel.CitaDiaRow cita : citas) {
            VBox citaBlock = createCitaBlock(cita);
            diaAppointmentsArea.getChildren().add(citaBlock);
        }
    }

    private VBox createCitaBlock(AgendaRowModel.CitaDiaRow cita) {
        VBox block = new VBox(4);
        block.getStyleClass().add("agenda-cita-block");
        block.setPadding(new Insets(8));
        block.setOnMouseClicked(e -> onCitaSelected(cita));

        String estadoClass = getEstadoStyleClass(cita.estado());
        block.getStyleClass().add(estadoClass);

        // Top row: Cliente + Estado
        HBox topRow = new HBox(12);
        topRow.setAlignment(Pos.CENTER_LEFT);

        Label clientePrefix = new Label("Cliente: ");
        clientePrefix.getStyleClass().add("agenda-cita-label");
        Label clienteValue = new Label(cita.cliente());
        clienteValue.getStyleClass().add("agenda-cita-value");

        Label estadoPrefix = new Label("Estado: ");
        estadoPrefix.getStyleClass().add("agenda-cita-label");
        Label estadoBadge = new Label(cita.estado());
        estadoBadge.getStyleClass().addAll("agenda-cita-estado-badge", estadoClass);

        topRow.getChildren().addAll(clientePrefix, clienteValue, estadoPrefix, estadoBadge);

        // Middle row: Tipo + Profesional
        HBox middleRow = new HBox(12);
        middleRow.setAlignment(Pos.CENTER_LEFT);

        Label tipoPrefix = new Label("Tipo: ");
        tipoPrefix.getStyleClass().add("agenda-cita-label");
        Label tipoValue = new Label(cita.tipoAtencion());
        tipoValue.getStyleClass().add("agenda-cita-value");

        Label profPrefix = new Label("Profesional: ");
        profPrefix.getStyleClass().add("agenda-cita-label");
        Label profValue = new Label(cita.profesional());
        profValue.getStyleClass().add("agenda-cita-value");

        middleRow.getChildren().addAll(tipoPrefix, tipoValue, profPrefix, profValue);

        // Bottom row: Hora
        HBox bottomRow = new HBox(8);
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        Label horaPrefix = new Label("Hora: ");
        horaPrefix.getStyleClass().add("agenda-cita-label");
        Label horaValue = new Label(cita.hora());
        horaValue.getStyleClass().add("agenda-cita-value");

        bottomRow.getChildren().addAll(horaPrefix, horaValue);

        block.getChildren().addAll(topRow, middleRow, bottomRow);
        Tooltip.install(block, new Tooltip(cita.tooltip()));

        return block;
    }

    private String getEstadoStyleClass(String estado) {
        if (estado == null) return "agenda-estado-neutral";
        String lower = estado.toLowerCase();
        if (lower.contains("confirm")) return "agenda-estado-confirmada";
        if (lower.contains("pendiente")) return "agenda-estado-pendiente";
        if (lower.contains("reprogram")) return "agenda-estado-reprogramada";
        if (lower.contains("cancel")) return "agenda-estado-cancelada";
        if (lower.contains("revision")) return "agenda-estado-revision";
        if (lower.contains("bloq") || lower.contains("pausa")) return "agenda-estado-bloqueo";
        return "agenda-estado-neutral";
    }

    // ------------------------------------------------------------------ Sub-view 2: Semana

    private void setupSubView2_Semana() {
        if (btnIrAlDia != null) {
            btnIrAlDia.setOnAction(e -> irAlDiaSeleccionado());
        }
    }

    private void loadSubView2_Semana() {
        if (semanaGrid == null) return;

        String fecha = fechaPicker.getValue() != null
                ? fechaPicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<AgendaRowModel.SemanaRow> semana = facade.getSemana(fecha);

        semanaGrid.getChildren().clear();
        semanaGrid.getColumnConstraints().clear();

        // Create 6 columns (Mon-Sat)
        for (int i = 0; i < 6; i++) {
            javafx.scene.layout.ColumnConstraints col = new javafx.scene.layout.ColumnConstraints();
            col.setPercentWidth(100.0 / 6);
            semanaGrid.getColumnConstraints().add(col);
        }

        // Header row
        Label headerLabel = new Label("Semana del " + fecha);
        headerLabel.getStyleClass().add("agenda-semana-header");
        headerLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        semanaGrid.add(headerLabel, 0, 0);
        GridPane.setColumnSpan(headerLabel, 6);

        // Day columns
        for (int i = 0; i < semana.size() && i < 6; i++) {
            AgendaRowModel.SemanaRow row = semana.get(i);
            VBox dayColumn = buildWeekDayColumn(row);
            semanaGrid.add(dayColumn, i, 1);
        }
    }

    private VBox buildWeekDayColumn(AgendaRowModel.SemanaRow row) {
        VBox column = new VBox(6);
        column.setPadding(new Insets(8));
        column.getStyleClass().add("agenda-weekday-column");

        Label diaLabel = new Label(row.dia());
        diaLabel.getStyleClass().add("agenda-weekday-name");

        Label totalLabel = new Label(row.totalCitas() + " citas");
        totalLabel.getStyleClass().add("agenda-weekday-total");

        VBox summaryCard = new VBox(4);
        summaryCard.setPadding(new Insets(6));
        summaryCard.getStyleClass().add("agenda-summary-card");

        Label confirmadasLabel = new Label("Confirmadas: " + row.confirmadas());
        confirmadasLabel.getStyleClass().add("agenda-summary-item");

        Label pendientesLabel = new Label("Pendientes: " + row.pendientes());
        pendientesLabel.getStyleClass().add("agenda-summary-item");

        Label canceladasLabel = new Label("Canceladas: " + row.canceladas());
        canceladasLabel.getStyleClass().add("agenda-summary-item");

        summaryCard.getChildren().addAll(confirmadasLabel, pendientesLabel, canceladasLabel);
        column.getChildren().addAll(diaLabel, totalLabel, summaryCard);

        return column;
    }

    private void irAlDiaSeleccionado() {
        showSubView(0);
        if (btnDia != null) btnDia.setSelected(true);
    }

    // ------------------------------------------------------------------ Sub-view 3: Lista del dia

    private void setupSubView3_ListaDia() {
        if (colLDHora != null) colLDHora.setCellValueFactory(data -> data.getValue().horaProperty());
        if (colLDCliente != null) colLDCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colLDAtencion != null) colLDAtencion.setCellValueFactory(data -> data.getValue().atencionProperty());
        if (colLDEstado != null) colLDEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colLDProfesional != null) colLDProfesional.setCellValueFactory(data -> data.getValue().profesionalProperty());
        if (colLDSucursal != null) colLDSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colLDObservacion != null) colLDObservacion.setCellValueFactory(data -> data.getValue().observacionProperty());

        if (listaDiaTable != null) {
            listaDiaTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onListaDiaRowSelected(newVal));
        }

        if (btnVerDia != null) {
            btnVerDia.setOnAction(e -> irAlDiaSeleccionado());
        }
        if (btnNuevaCitaLista != null) {
            btnNuevaCitaLista.setOnAction(e -> nuevaCita());
        }
        if (btnExportarAgenda != null) {
            btnExportarAgenda.setOnAction(e -> exportarAgenda());
        }
    }

    private void loadSubView3_ListaDia() {
        if (listaDiaTable == null) return;

        String fecha = fechaPicker.getValue() != null
                ? fechaPicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<AgendaRowModel.ListaDiaRow> lista = facade.getListaDia(fecha);
        ObservableList<AgendaRowModel.ListaDiaRow> data = FXCollections.observableArrayList(lista);
        listaDiaTable.setItems(data);

        if (lblListaDiaCount != null) {
            lblListaDiaCount.setText(data.size() + " citas para el dia");
        }

        if (!data.isEmpty()) {
            listaDiaTable.getSelectionModel().selectFirst();
        }
    }

    private void onListaDiaRowSelected(AgendaRowModel.ListaDiaRow row) {
        if (row == null) return;
        AgendaSummaryModel summary = facade.buildSummary(row);
        updateSummaryPanel(summary);
    }

    // ------------------------------------------------------------------ Sub-view 4: Lista de espera

    private void setupSubView4_ListaEspera() {
        if (colECliente != null) colECliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colEAtencion != null) colEAtencion.setCellValueFactory(data -> data.getValue().atencionSolicitadaProperty());
        if (colEFranja != null) colEFranja.setCellValueFactory(data -> data.getValue().franjaPreferidaProperty());
        if (colESucursal != null) colESucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());
        if (colEPrioridad != null) colEPrioridad.setCellValueFactory(data -> data.getValue().prioridadProperty());
        if (colEEstadoContacto != null) colEEstadoContacto.setCellValueFactory(data -> data.getValue().estadoContactoProperty());

        if (esperaTable != null) {
            esperaTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onEsperaRowSelected(newVal));
        }

        if (btnAsignarCupo != null) {
            btnAsignarCupo.setOnAction(e -> asignarCupo());
        }
        if (btnContactarCliente != null) {
            btnContactarCliente.setOnAction(e -> contactarClienteEspera());
        }
        if (btnEliminarEspera != null) {
            btnEliminarEspera.setOnAction(e -> eliminarDeEspera());
        }
    }

    private void loadSubView4_ListaEspera() {
        if (esperaTable == null) return;

        List<AgendaRowModel.EsperaRow> espera = facade.getListaEspera();
        ObservableList<AgendaRowModel.EsperaRow> data = FXCollections.observableArrayList(espera);
        esperaTable.setItems(data);

        if (lblEsperaCount != null) {
            lblEsperaCount.setText(data.size() + " en lista de espera");
        }

        if (!data.isEmpty()) {
            esperaTable.getSelectionModel().selectFirst();
        }
    }

    private void onEsperaRowSelected(AgendaRowModel.EsperaRow row) {
        if (row == null) return;
        AgendaSummaryModel summary = new AgendaSummaryModel(
                row.cliente(),
                row.franjaPreferida(),
                row.atencionSolicitada(),
                "En espera",
                "-",
                row.sucursal(),
                "-",
                "Prioridad: " + row.prioridad(),
                "-"
        );
        updateSummaryPanel(summary);
    }

    private void asignarCupo() { /* placeholder */ }
    private void contactarClienteEspera() { /* placeholder */ }
    private void eliminarDeEspera() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 5: Confirmaciones

    private void setupSubView5_Confirmaciones() {
        if (colCFecha != null) colCFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        if (colCHora != null) colCHora.setCellValueFactory(data -> data.getValue().horaProperty());
        if (colCCliente != null) colCCliente.setCellValueFactory(data -> data.getValue().clienteProperty());
        if (colCAtencion != null) colCAtencion.setCellValueFactory(data -> data.getValue().atencionProperty());
        if (colCEstado != null) colCEstado.setCellValueFactory(data -> data.getValue().estadoConfirmacionProperty());
        if (colCRecordatorio != null) colCRecordatorio.setCellValueFactory(data -> data.getValue().ultimoRecordatorioProperty());
        if (colCSucursal != null) colCSucursal.setCellValueFactory(data -> data.getValue().sucursalProperty());

        if (confirmacionesTable != null) {
            confirmacionesTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onConfirmacionRowSelected(newVal));
        }

        if (btnMarcarConfirmada != null) {
            btnMarcarConfirmada.setOnAction(e -> marcarConfirmada());
        }
        if (btnEnviarRecordatorio != null) {
            btnEnviarRecordatorio.setOnAction(e -> enviarRecordatorioConfirmacion());
        }
        if (btnReprogramarCita != null) {
            btnReprogramarCita.setOnAction(e -> reprogramarCita());
        }
    }

    private void loadSubView5_Confirmaciones() {
        if (confirmacionesTable == null) return;

        List<AgendaRowModel.ConfirmacionRow> confirmaciones = facade.getConfirmaciones();
        ObservableList<AgendaRowModel.ConfirmacionRow> data = FXCollections.observableArrayList(confirmaciones);
        confirmacionesTable.setItems(data);

        if (lblConfirmacionesCount != null) {
            lblConfirmacionesCount.setText(data.size() + " citas pendientes de confirmacion");
        }

        if (!data.isEmpty()) {
            confirmacionesTable.getSelectionModel().selectFirst();
        }
    }

    private void onConfirmacionRowSelected(AgendaRowModel.ConfirmacionRow row) {
        if (row == null) return;
        AgendaSummaryModel summary = new AgendaSummaryModel(
                row.cliente(),
                row.hora(),
                row.atencion(),
                row.estadoConfirmacion(),
                "-",
                row.sucursal(),
                "-",
                "Ultimo recordatorio: " + row.ultimoRecordatorio(),
                row.fecha()
        );
        updateSummaryPanel(summary);
    }

    private void marcarConfirmada() { /* placeholder */ }
    private void enviarRecordatorioConfirmacion() { /* placeholder */ }

    // ------------------------------------------------------------------ Sub-view 6: Horarios y bloqueos

    private void setupSubView6_Horarios() {
        if (colBFranja != null) colBFranja.setCellValueFactory(data -> data.getValue().franjaProperty());
        if (colBTipo != null) colBTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        if (colBEstado != null) colBEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        if (colBResponsable != null) colBResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());

        if (bloqueosTable != null) {
            bloqueosTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, old, newVal) -> onBloqueoRowSelected(newVal));
        }

        if (btnRegistrarBloqueo != null) {
            btnRegistrarBloqueo.setOnAction(e -> registrarBloqueo());
        }
        if (btnEditarHorario != null) {
            btnEditarHorario.setOnAction(e -> editarHorario());
        }
        if (btnQuitarBloqueo != null) {
            btnQuitarBloqueo.setOnAction(e -> quitarBloqueo());
        }
    }

    private void loadSubView6_Horarios() {
        if (bloqueosTable == null) return;

        String fecha = fechaPicker.getValue() != null
                ? fechaPicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<AgendaRowModel.HorarioRow> horarios = facade.getHorarios(fecha);
        ObservableList<AgendaRowModel.HorarioRow> data = FXCollections.observableArrayList(horarios);
        bloqueosTable.setItems(data);

        // Build horario general block
        buildHorarioGeneralBlock(horarios);

        if (!data.isEmpty()) {
            bloqueosTable.getSelectionModel().selectFirst();
        }
    }

    private void buildHorarioGeneralBlock(List<AgendaRowModel.HorarioRow> horarios) {
        if (horarioGeneralContainer == null) return;

        horarioGeneralContainer.getChildren().clear();

        Label title = new Label("Horario general");
        title.getStyleClass().add("agenda-horario-general-title");

        VBox scheduleBox = new VBox(4);
        scheduleBox.setPadding(new Insets(8));
        scheduleBox.getStyleClass().add("agenda-horario-general");

        for (AgendaRowModel.HorarioRow row : horarios) {
            HBox rowBox = new HBox(12);
            rowBox.setPadding(new Insets(4, 8, 4, 8));
            rowBox.setAlignment(Pos.CENTER_LEFT);

            if (row.tipo().toLowerCase().contains("bloq")) {
                rowBox.getStyleClass().add("agenda-bloqueo-row");
            } else {
                rowBox.getStyleClass().add("agenda-horario-row");
            }

            Label franja = new Label(row.franja());
            franja.getStyleClass().add("agenda-horario-franja");
            Label tipo = new Label(row.tipo());
            tipo.getStyleClass().add("agenda-horario-tipo");
            Label estado = new Label(row.estado());
            estado.getStyleClass().add("agenda-horario-estado");

            rowBox.getChildren().addAll(franja, tipo, estado);
            scheduleBox.getChildren().add(rowBox);
        }

        horarioGeneralContainer.getChildren().addAll(title, scheduleBox);
    }

    private void onBloqueoRowSelected(AgendaRowModel.HorarioRow row) {
        if (row == null) return;
        AgendaSummaryModel summary = new AgendaSummaryModel(
                row.responsable(),
                row.franja(),
                row.tipo(),
                row.estado(),
                row.responsable(),
                "-",
                "-",
                "Horario/Bloqueo: " + row.tipo(),
                "-"
        );
        updateSummaryPanel(summary);
    }

    private void registrarBloqueo() { /* placeholder */ }
    private void editarHorario() { /* placeholder */ }
    private void quitarBloqueo() { /* placeholder */ }

    // ------------------------------------------------------------------ Summary panel

    private void setupSummaryPanel() {
        if (summaryBtnEditar != null) {
            summaryBtnEditar.setOnAction(e -> editarCita());
        }
        if (summaryBtnConfirmar != null) {
            summaryBtnConfirmar.setOnAction(e -> confirmarCita());
        }
        if (summaryBtnReprogramar != null) {
            summaryBtnReprogramar.setOnAction(e -> reprogramarCita());
        }
        if (summaryBtnCancelar != null) {
            summaryBtnCancelar.setOnAction(e -> cancelarCita());
        }
        if (summaryBtnAtendida != null) {
            summaryBtnAtendida.setOnAction(e -> marcarAtendida());
        }
        if (summaryBtnAbrirCliente != null) {
            summaryBtnAbrirCliente.setOnAction(e -> abrirCliente());
        }
    }

    private void loadSummaryPanel() {
        updateSummaryPanel(AgendaSummaryModel.demoSeed());
    }

    private void updateSummaryPanel(AgendaSummaryModel summary) {
        if (summaryCliente != null) summaryCliente.setText(summary.cliente());
        if (summaryHora != null) summaryHora.setText(summary.hora());
        if (summaryAtencion != null) summaryAtencion.setText(summary.atencion());
        if (summaryEstado != null) summaryEstado.setText(summary.estado());
        if (summaryProfesional != null) summaryProfesional.setText(summary.profesional());
        if (summarySucursal != null) summarySucursal.setText(summary.sucursal());
        if (summaryContacto != null) summaryContacto.setText(summary.contacto());
        if (summaryObservaciones != null) summaryObservaciones.setText(summary.observaciones());
        if (summaryFechaCita != null) summaryFechaCita.setText(summary.fechaCita());
    }

    private void onCitaSelected(AgendaRowModel.CitaDiaRow row) {
        if (row == null) return;
        AgendaSummaryModel summary = facade.buildSummary(row);
        updateSummaryPanel(summary);
    }

    // ------------------------------------------------------------------ Actions

    private void applyFilters() {
        currentFilters.setFecha(
                fechaPicker.getValue() != null
                        ? fechaPicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : ""
        );
        currentFilters.setProfesional(profesionalCombo != null ? profesionalCombo.getValue() : "Todos");
        currentFilters.setEstado(estadoCombo != null ? estadoCombo.getValue() : "Todos");
        currentFilters.setTipoAtencion(atencionCombo != null ? atencionCombo.getValue() : "Todos");
        currentFilters.setSearchText(searchField != null ? searchField.getText() : "");

        // Reload current active sub-view
        ToggleButton selected = null;
        if (btnDia != null && btnDia.isSelected()) selected = btnDia;
        else if (btnSemana != null && btnSemana.isSelected()) selected = btnSemana;
        else if (btnListaDia != null && btnListaDia.isSelected()) selected = btnListaDia;
        else if (btnListaEspera != null && btnListaEspera.isSelected()) selected = btnListaEspera;
        else if (btnConfirmaciones != null && btnConfirmaciones.isSelected()) selected = btnConfirmaciones;
        else if (btnHorarios != null && btnHorarios.isSelected()) selected = btnHorarios;

        if (selected == btnDia) loadSubView1_Dia();
        else if (selected == btnListaDia) loadSubView3_ListaDia();
        else if (selected == btnHorarios) loadSubView6_Horarios();
    }

    private void clearFilters() {
        if (fechaPicker != null) fechaPicker.setValue(LocalDate.now());
        if (profesionalCombo != null) profesionalCombo.setValue("Todos");
        if (estadoCombo != null) estadoCombo.setValue("Todos");
        if (atencionCombo != null) atencionCombo.setValue("Todos");
        if (searchField != null) searchField.clear();

        currentFilters = new AgendaFilters();
        applyFilters();
    }

    private void actualizarAgenda() {
        applyFilters();
    }

    private void nuevaCita() { /* placeholder */ }
    private void exportarAgenda() { /* placeholder */ }
    private void editarCita() { /* placeholder */ }
    private void confirmarCita() { /* placeholder */ }
    private void reprogramarCita() { /* placeholder */ }
    private void cancelarCita() { /* placeholder */ }
    private void marcarAtendida() { /* placeholder */ }
    private void abrirCliente() { /* placeholder */ }
}

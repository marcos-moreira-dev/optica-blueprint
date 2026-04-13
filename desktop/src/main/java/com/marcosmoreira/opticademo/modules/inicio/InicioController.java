package com.marcosmoreira.opticademo.modules.inicio;

import com.marcosmoreira.opticademo.app.App;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.domain.sucursal.Sucursal;
import com.marcosmoreira.opticademo.shared.ui.components.KpiCardController;
import com.marcosmoreira.opticademo.shared.ui.components.StatusBadgeController;
import com.marcosmoreira.opticademo.shared.ui.model.KpiCardModel;
import com.marcosmoreira.opticademo.shared.ui.model.StatusBadgeModel;
import com.marcosmoreira.opticademo.shared.ui.util.ComboBoxFactory;
import com.marcosmoreira.opticademo.shared.ui.util.TableViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para el modulo de Inicio (Dashboard) del sistema Optica.
 * <p>
 * Este modulo presenta una vista general del estado del negocio mediante tarjetas KPI,
 * una tabla de proximas citas con badges de estado, alertas operativas, accesos rapidos
 * a los modulos principales y un registro de actividad reciente. Delega toda la logica
 * de negocio a {@link InicioFacade}, quien consulta el {@link DemoStore} para obtener
 * los datos de demostracion.
 * </p>
 * <p>
 * El controlador gestiona un selector de sucursal que filtra la informacion mostrada
 * y utiliza componentes reutilizables como {@link KpiCardController} y
 * {@link StatusBadgeController} para mantener consistencia visual en la interfaz.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see InicioFacade
 * @see InicioRowModel
 * @see KpiCardController
 * @see StatusBadgeController
 */
public class InicioController {

    /**
     * Selector de sucursal para filtrar los datos del dashboard.
     * Permite seleccionar una sucursal especifica o visualizar todas.
     */
    @FXML
    private ComboBox<String> sucursalCombo;

    /**
     * Contenedor de tipo {@link GridPane} para las tarjetas KPI.
     * Alberga las tarjetas con indicadores clave como citas del dia, ventas, etc.
     */
    @FXML
    private GridPane kpiGrid;

    /**
     * Tabla que muestra las proximas citas programadas.
     * Incluye columnas de hora, cliente, tipo de atencion, estado y profesional.
     */
    @FXML
    private TableView<InicioRowModel.CitaRow> citasTable;

    /** Columna de hora en la tabla de citas. */
    @FXML
    private TableColumn<InicioRowModel.CitaRow, String> colHora;

    /** Columna de nombre del cliente en la tabla de citas. */
    @FXML
    private TableColumn<InicioRowModel.CitaRow, String> colCliente;

    /** Columna de tipo de atencion en la tabla de citas. */
    @FXML
    private TableColumn<InicioRowModel.CitaRow, String> colAtencion;

    /** Columna de estado de la cita, renderizada con {@link StatusBadgeController}. */
    @FXML
    private TableColumn<InicioRowModel.CitaRow, String> colEstado;

    /** Columna de profesional asignado en la tabla de citas. */
    @FXML
    private TableColumn<InicioRowModel.CitaRow, String> colProfesional;

    /**
     * Contenedor vertical para las alertas operativas del negocio.
     * Cada alerta se representa como un {@link HBox} con badges de tipo y estado.
     */
    @FXML
    private VBox alertasContainer;

    /**
     * Grid de accesos rapidos a los modulos principales del sistema.
     * Incluye botones para nueva cita, cliente, venta, entrega, inventario y reportes.
     */
    @FXML
    private GridPane accesosGrid;

    /**
     * Contenedor vertical para el registro de actividad reciente.
     * Cada entrada se muestra como un item con formato de lista.
     */
    @FXML
    private VBox actividadContainer;

    /**
     * Fachada que centraliza la logica de negocio del modulo Inicio.
     * Consulta el {@link DemoStore} para obtener datos de demostracion.
     */
    private InicioFacade facade;

    /**
     * Inicializa el controlador y carga todos los componentes del dashboard.
     * <p>
     * Este metodo es invocado automaticamente por el {@link FXMLLoader} tras cargar
     * el FXML. Instancia {@link InicioFacade} con el {@link DemoStore} global y
     * delega la carga de cada seccion: combo de sucursal, tarjetas KPI, tabla de citas,
     * alertas, accesos rapidos y actividad reciente.
     * </p>
     *
     * @see InicioFacade
     * @see App#getDemoStore()
     */
    public void initialize() {
        DemoStore store = App.getDemoStore();
        this.facade = new InicioFacade(store);

        populateSucursalCombo();
        loadKpiCards();
        loadProximasCitas();
        loadAlertas();
        loadActividadReciente();
        buildAccesosRapidos();
    }

    // ---- Sucursal combo ----

    /**
     * Configura el combo de sucursal con los nombres de las sucursales disponibles.
     * <p>
     * Utiliza {@link ComboBoxFactory#createFilterCombo(String, String, String[])}
     * para crear un combo filtrable y reemplaza el componente inyectado por FXML.
     * </p>
     */
    private void populateSucursalCombo() {
        List<Sucursal> sucursales = store().sucursales;
        String[] items = sucursales.stream()
                .map(Sucursal::getNombre)
                .toArray(String[]::new);
        if (sucursalCombo != null) {
            ComboBox<String> combo = ComboBoxFactory.createFilterCombo("Seleccionar sucursal", "-- Todas --", items);
            // Replace the injected combo with our configured one
            if (sucursalCombo.getParent() instanceof HBox hbox) {
                int idx = hbox.getChildren().indexOf(sucursalCombo);
                hbox.getChildren().set(idx, combo);
            }
            sucursalCombo = combo;
        }
    }

    // ---- KPI cards ----
    private void loadKpiCards() {
        if (kpiGrid == null) return;
        kpiGrid.getChildren().clear();

        List<KpiCardModel> kpis = facade.getKpiCards();
        int col = 0;
        int row = 0;
        for (KpiCardModel model : kpis) {
            VBox card = createKpiCardProgrammatically(model);
            kpiGrid.add(card, col, row);
            col++;
            if (col >= 3) {
                col = 0;
                row++;
            }
        }
    }

    private VBox createKpiCardProgrammatically(KpiCardModel model) {
        VBox card = new VBox(4);
        card.getStyleClass().add("kpi-card");

        Label titleLbl = new Label(model.title());
        titleLbl.getStyleClass().add("kpi-title");

        Label valueLbl = new Label(model.value());
        valueLbl.getStyleClass().add("kpi-value");

        card.getChildren().addAll(titleLbl, valueLbl);

        if (model.subtitle() != null && !model.subtitle().isEmpty()) {
            Label subtitleLbl = new Label(model.subtitle());
            subtitleLbl.getStyleClass().add("kpi-subtitle");
            card.getChildren().add(subtitleLbl);
        }

        return card;
    }

    // ---- Proximas citas ----
    private void loadProximasCitas() {
        if (citasTable == null) return;

        TableViewFactory.setupColumn(colHora, "hora");
        TableViewFactory.setupColumn(colCliente, "cliente");
        TableViewFactory.setupColumn(colAtencion, "atencion");
        TableViewFactory.setupColumn(colEstado, "estado");
        TableViewFactory.setupColumn(colProfesional, "profesional");

        // Custom cell factory for estado column to show badge
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
                            case "confirmada" -> StatusBadgeModel.success(item);
                            case "pendiente" -> StatusBadgeModel.warning(item);
                            case "en espera" -> StatusBadgeModel.info(item);
                            case "cancelada" -> StatusBadgeModel.danger(item);
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

        ObservableList<InicioRowModel.CitaRow> data = FXCollections.observableArrayList(facade.getProximasCitas());
        citasTable.setItems(data);
    }

    // ---- Alertas ----
    private void loadAlertas() {
        if (alertasContainer == null) return;
        alertasContainer.getChildren().clear();

        for (InicioRowModel.AlertaRow alerta : facade.getAlertas()) {
            HBox row = createAlertRow(alerta);
            alertasContainer.getChildren().add(row);
        }
    }

    private HBox createAlertRow(InicioRowModel.AlertaRow alerta) {
        HBox row = new HBox(8);
        row.getStyleClass().add("alerta-row");
        row.setPadding(new Insets(8, 10, 8, 10));
        row.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        String badgeStyle = switch (alerta.estado()) {
            case "warning" -> "badge-warning";
            case "danger" -> "badge-danger";
            case "info" -> "badge-info";
            default -> "badge-neutral";
        };

        Label tipoBadge = new Label(alerta.tipo());
        tipoBadge.getStyleClass().addAll("status-badge", badgeStyle);
        tipoBadge.setMinWidth(60);
        tipoBadge.setAlignment(javafx.geometry.Pos.CENTER);

        Label textoLabel = new Label(alerta.texto());
        textoLabel.setWrapText(true);
        textoLabel.getStyleClass().add("alerta-texto");
        HBox.setHgrow(textoLabel, javafx.scene.layout.Priority.ALWAYS);

        Label estadoBadge = new Label(alerta.estado());
        estadoBadge.getStyleClass().addAll("status-badge", badgeStyle);
        estadoBadge.setMinWidth(55);
        estadoBadge.setAlignment(javafx.geometry.Pos.CENTER);

        row.getChildren().addAll(tipoBadge, textoLabel, estadoBadge);
        return row;
    }

    // ---- Accesos rapidos ----
    private void buildAccesosRapidos() {
        if (accesosGrid == null) return;
        accesosGrid.getChildren().clear();
        accesosGrid.setHgap(10);
        accesosGrid.setVgap(8);

        String[][] accesos = {
                {"Nueva cita", "\uD83D\uDCC5"},
                {"Nuevo cliente", "\uD83D\uDC64"},
                {"Nueva venta optica", "\uD83D\uDED2"},
                {"Registrar entrega", "\uD83D\uDCE6"},
                {"Ver inventario", "\uD83D\uDCE3"},
                {"Abrir reportes", "\uD83D\uDCCA"}
        };

        int col = 0;
        int row = 0;
        for (String[] acceso : accesos) {
            Button btn = createQuickAccessButton(acceso[0], acceso[1]);
            GridPane.setHgrow(btn, javafx.scene.layout.Priority.ALWAYS);
            GridPane.setVgrow(btn, javafx.scene.layout.Priority.ALWAYS);
            accesosGrid.add(btn, col, row);
            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
        }
    }

    private Button createQuickAccessButton(String text, String icon) {
        Button btn = new Button(icon + "  " + text);
        btn.getStyleClass().add("btn-acceso-rapido");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(44);
        btn.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        return btn;
    }

    // ---- Actividad reciente ----
    private void loadActividadReciente() {
        if (actividadContainer == null) return;
        actividadContainer.getChildren().clear();

        for (InicioRowModel.ActividadRow act : facade.getActividadReciente()) {
            Label label = new Label("\u2022 " + act.texto());
            label.setWrapText(true);
            label.getStyleClass().add("actividad-item");
            actividadContainer.getChildren().add(label);
        }
    }

    private DemoStore store() {
        return App.getDemoStore();
    }
}

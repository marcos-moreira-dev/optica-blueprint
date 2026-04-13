package com.marcosmoreira.opticademo.app.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Map;

/**
 * Loads module FXML views by convention.
 * Each ModuleId maps to a specific fxml path.
 */
public class ModuleViewLoader {

    /** Explicit mapping from ModuleId to the FXML resource path. */
    private static final Map<ModuleId, String> FXML_PATHS = Map.ofEntries(
            Map.entry(ModuleId.INICIO, "/fxml/modules/inicio/InicioView.fxml"),
            Map.entry(ModuleId.AGENDA, "/fxml/modules/agenda/AgendaView.fxml"),
            Map.entry(ModuleId.CLIENTES, "/fxml/modules/clientes/ClientesView.fxml"),
            Map.entry(ModuleId.RECETAS, "/fxml/modules/recetas/RecetasView.fxml"),
            Map.entry(ModuleId.VENTA_OPTICA, "/fxml/modules/ventaoptica/VentaOpticaView.fxml"),
            Map.entry(ModuleId.ORDENES_LABORATORIO, "/fxml/modules/ordeneslaboratorio/OrdeneslaboratorioView.fxml"),
            Map.entry(ModuleId.INVENTARIO, "/fxml/modules/inventario/InventarioView.fxml"),
            Map.entry(ModuleId.CAJA, "/fxml/modules/caja/CajaView.fxml"),
            Map.entry(ModuleId.ENTREGAS, "/fxml/modules/entregas/EntregasView.fxml"),
            Map.entry(ModuleId.SEGUIMIENTO, "/fxml/modules/seguimiento/SeguimientoView.fxml"),
            Map.entry(ModuleId.REPORTES, "/fxml/modules/reportes/ReportesView.fxml"),
            Map.entry(ModuleId.CONFIGURACION, "/fxml/modules/configuracion/ConfiguracionView.fxml"),
            Map.entry(ModuleId.SEGUROS, "/fxml/modules/seguros/SegurosView.fxml"),
            Map.entry(ModuleId.PROVEEDORES, "/fxml/modules/proveedores/ProveedoresView.fxml"),
            Map.entry(ModuleId.COMPRAS, "/fxml/modules/compras/ComprasView.fxml"),
            Map.entry(ModuleId.USUARIOS_ROLES, "/fxml/modules/usuariosroles/UsuariosrolesView.fxml"),
            Map.entry(ModuleId.TALLER, "/fxml/modules/taller/TallerView.fxml"),
            Map.entry(ModuleId.NOTIFICACIONES, "/fxml/modules/notificaciones/NotificacionesView.fxml"),
            Map.entry(ModuleId.SUCURSALES, "/fxml/modules/sucursales/SucursalesView.fxml")
    );

    public Node loadView(ModuleId moduleId) {
        String fxmlPath = FXML_PATHS.get(moduleId);
        System.out.println("[ModuleViewLoader] Loading module: " + moduleId + " -> path: " + fxmlPath);
        if (fxmlPath == null) {
            System.err.println("[ModuleViewLoader] ERROR: No FXML path mapped for " + moduleId);
            return createPlaceholder(moduleId);
        }

        try {
            var url = getClass().getResource(fxmlPath);
            System.out.println("[ModuleViewLoader] Resource URL: " + url);
            if (url == null) {
                System.err.println("[ModuleViewLoader] ERROR: Resource NOT FOUND at: " + fxmlPath);
                return createPlaceholder(moduleId);
            }
            FXMLLoader loader = new FXMLLoader(url);
            Node result = loader.load();
            System.out.println("[ModuleViewLoader] Loaded successfully: " + result.getClass().getSimpleName());
            return result;
        } catch (IOException e) {
            System.err.println("[ModuleViewLoader] ERROR: IOException loading " + fxmlPath + ": " + e.getMessage());
            e.printStackTrace();
            return createPlaceholder(moduleId);
        }
    }

    private Node createPlaceholder(ModuleId moduleId) {
        StackPane placeholder = new StackPane();
        placeholder.getStyleClass().add("module-placeholder");
        placeholder.setMinSize(400, 300);
        return placeholder;
    }
}

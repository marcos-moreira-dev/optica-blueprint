package com.marcosmoreira.opticademo.app.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Map;

/**
 * Cargador de vistas FXML para los módulos de la aplicación, basado en una convención
 * de mapeo entre {@link ModuleId} y rutas de recursos FXML.
 * <p>
 * La clase mantiene un mapa explícito ({@code FXML_PATHS}) que asocia cada identificador
 * de módulo con su correspondiente archivo FXML ubicado en el classpath bajo
 * {@code /fxml/modules/}. El método {@link #loadView(ModuleId)} intenta cargar la vista
 * y, en caso de error (ruta no mapeada, recurso inexistente o {@link IOException}),
 * retorna un marcador de posición generado por {@link #createPlaceholder(ModuleId)}
 * en lugar de propagar la excepción, garantizando así que la navegación de la
 * aplicación nunca falle catastróficamente.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ModuleId
 * @see FXMLLoader
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

    /**
     * Carga la vista FXML asociada al {@link ModuleId} especificado.
     * <p>
     * El proceso de carga sigue esta estrategia de manejo de errores:
     * </p>
     * <ul>
     *   <li>Si el {@code moduleId} no tiene una entrada en {@code FXML_PATHS},
     *       se registra un error y se devuelve un marcador de posición.</li>
     *   <li>Si el recurso FXML no existe en el classpath (URL nula),
     *       se registra un error y se devuelve un marcador de posición.</li>
     *   <li>Si ocurre una {@link IOException} durante la carga, se captura,
     *       se imprime el stack trace y se devuelve un marcador de posición.</li>
     * </ul>
     *
     * @param moduleId el identificador del módulo cuya vista se desea cargar
     * @return un {@link Node} con la vista cargada o un marcador de posición si falló la carga
     * @see #createPlaceholder(ModuleId)
     */
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

    /**
     * Crea un nodo marcador de posición para cuando una vista no puede ser cargada.
     * El marcador es un {@link StackPane} con la clase CSS {@code "module-placeholder"}
     * y dimensiones mínimas de 400x300 píxeles.
     *
     * @param moduleId el identificador del módulo para el cual se genera el placeholder
     * @return un {@link Node} que actúa como vista temporal del módulo
     */
    private Node createPlaceholder(ModuleId moduleId) {
        StackPane placeholder = new StackPane();
        placeholder.getStyleClass().add("module-placeholder");
        placeholder.setMinSize(400, 300);
        return placeholder;
    }
}

package com.marcosmoreira.opticademo.app;

import com.marcosmoreira.opticademo.demo.DemoDataInitializer;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.app.session.DemoSession;
import com.marcosmoreira.opticademo.app.session.CurrentUserContext;
import com.marcosmoreira.opticademo.app.session.CurrentSucursalContext;
import com.marcosmoreira.opticademo.app.theme.ThemeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicacion JavaFX OpticaDemo.
 * <p>
 * Esta clase extiende {@link Application} y constituye el punto de entrada
 * del ciclo de vida de la aplicacion. Se encarga de:
 * <ul>
 *   <li>Inicializar el almacén de datos en memoria ({@link DemoStore})</li>
 *   <li>Poblar datos de demostración mediante {@link DemoDataInitializer}</li>
 *   <li>Configurar el contexto de sesión (usuario y sucursal actuales)</li>
 *   <li>Cargar la vista de login con las hojas de estilo CSS</li>
 *   <li>Configurar el ícono de la ventana de la aplicación</li>
 * </ul>
 * </p>
 * <p>
 * Arquitectura: La aplicación utiliza un patrón MVC + Facade con un
 * almacén en memoria autocontenido (DemoStore) que simula una base de datos
 * para fines de demostración. Todos los módulos del sistema comparten esta
 * instancia única a través del {@code AppSessionHolder}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see Application
 * @see DemoStore
 * @see DemoDataInitializer
 */
public class App extends Application {

    /** Almacén central de datos en memoria compartido por todos los módulos. */
    private DemoStore demoStore;

    /** Sesión de demostración con valores por defecto de usuario y sucursal. */
    private DemoSession demoSession;

    /**
     * Fase de inicialización de la aplicación.
     * <p>
     * Este método es invocado por el runtime de JavaFX antes de {@link #start(Stage)}.
     * Aquí se crea e inicializa toda la infraestructura de datos de la aplicación:
     * </p>
     * <ol>
     *   <li>Crea una nueva instancia de {@link DemoStore}</li>
     *   <li>Crea una {@link DemoSession} con valores por defecto</li>
     *   <li>Registra el store en el holder estático para acceso global</li>
     *   <li>Ejecuta el inicializador de datos demo</li>
     *   <li>Configura el usuario y sucursal actuales como por defecto</li>
     *   <li>Establece el tema visual en modo claro (LIGHT)</li>
     * </ol>
     *
     * @see DemoStore
     * @see DemoDataInitializer
     * @see DemoSession
     */
    @Override
    public void init() {
        this.demoStore = new DemoStore();
        this.demoSession = new DemoSession();
        AppSessionHolder.store = this.demoStore;

        DemoDataInitializer initializer = new DemoDataInitializer(this.demoStore);
        initializer.initialize();

        CurrentUserContext.setCurrentUser(this.demoSession.getDefaultUser());
        CurrentSucursalContext.setSucursal(this.demoSession.getDefaultSucursal());
        ThemeManager.setThemeMode(com.marcosmoreira.opticademo.app.theme.ThemeMode.LIGHT);
    }

    /**
     * Fase de arranque de la interfaz gráfica.
     * <p>
     * Carga la vista FXML del login ({@code /fxml/login/LoginView.fxml}),
     * aplica las hojas de estilo CSS y configura la ventana principal con:
     * </p>
     * <ul>
     *   <li>Dimensiones iniciales: 900x560 píxeles</li>
     *   <li>Dimensiones mínimas: 800x500 píxeles</li>
     *   <li>Centrada en pantalla</li>
     *   <li>Ícono de la aplicación (logo.png)</li>
     * </ul>
     * <p>
     * Las hojas de estilo se cargan en este orden:
     * {@code tokens.css} → {@code app.css} → {@code custom-controls.css} → {@code login.css}
     * </p>
     *
     * @param primaryStage la ventana principal de la aplicación proporcionada por JavaFX
     * @throws Exception si ocurre un error al cargar el FXML o las hojas de estilo
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/LoginView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 560);
        scene.getStylesheets().addAll(
                getClass().getResource("/css/tokens.css").toExternalForm(),
                getClass().getResource("/css/app.css").toExternalForm(),
                getClass().getResource("/css/custom-controls.css").toExternalForm(),
                getClass().getResource("/css/login.css").toExternalForm()
        );

        primaryStage.setTitle("Optica Manager - Login");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.centerOnScreen();

        // Set application icons in multiple resolutions for Windows taskbar + window chrome
        try {
            // JavaFX picks the best-matching size automatically from the list
            // Small sizes for taskbar (16x16, 32x32)
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-16.png")));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-32.png")));
            // Medium sizes for window title bar and Alt+Tab (48x48, 64x64)
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-48.png")));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-64.png")));
            // Large sizes for high-DPI displays (128x128, 256x256)
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-128.png")));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-256.png")));
        } catch (Exception e) {
            // Fallback: try loading the single logo.png if individual icons not available
            try {
                Image icon = new Image(getClass().getResourceAsStream("/logo.png"));
                primaryStage.getIcons().add(icon);
            } catch (Exception fallbackEx) {
                // Icon not available, continuing without it
            }
        }

        primaryStage.show();
    }

    /**
     * Obtiene la instancia global del almacén de datos en memoria.
     * <p>
     * Este método proporciona acceso al {@link DemoStore} compartido por
     * todos los módulos de la aplicación. Es utilizado por los Facades
     * de cada módulo para consultar y manipular datos.
     * </p>
     *
     * @return la instancia única de {@link DemoStore} para toda la aplicación
     */
    public static DemoStore getDemoStore() {
        return AppSessionHolder.store;
    }

    /**
     * Holder estático para compartir el {@link DemoStore} entre componentes.
     * <p>
     * Esta clase interna permite que {@link App#getDemoStore()} devuelva
     * la instancia del store creada durante la fase {@link #init()}.
     * Se usa un patrón de inner class para evitar problemas de inicialización
     * anticipada.
     * </p>
     */
    private static class AppSessionHolder {
        /** Referencia estática al store de la aplicación. */
        static DemoStore store;
    }
}

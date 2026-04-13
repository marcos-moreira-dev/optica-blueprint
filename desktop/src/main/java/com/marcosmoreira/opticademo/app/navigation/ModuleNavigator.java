package com.marcosmoreira.opticademo.app.navigation;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Controlador central de navegacion entre modulos de la aplicacion.
 * <p>
 * Esta clase gestiona el cambio de vistas (FXML) dentro del {@link StackPane}
 * contenedor principal del layout. Utiliza un {@link ModuleViewLoader} para
 * cargar dinamicamente los archivos FXML correspondientes a cada {@link ModuleId}.
 * </p>
 * <p>
 * Patron de navegacion:
 * <ol>
 *   <li>El usuario hace clic en un boton del sidebar</li>
 *   <li>{@link #navigateTo(ModuleId)} verifica si el modulo ya esta activo (idempotencia)</li>
 *   <li>El loader carga el FXML del modulo destino</li>
 *   <li>El StackPane reemplaza su contenido con la nueva vista via {@code setAll()}</li>
 * </ol>
 * </p>
 * <p>
 * Nota de arquitectura: El uso de {@code StackPane.setAll()} elimina automaticamente
 * el nodo anterior, evitando fugas de memoria y asegurando que solo una vista
 * este activa a la vez.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ModuleId
 * @see ModuleViewLoader
 */
public class ModuleNavigator {

    /** Contenedor StackPane donde se intercambian las vistas de los modulos. */
    private final StackPane contentHost;

    /** Loader encargado de cargar los archivos FXML de cada modulo. */
    private final ModuleViewLoader viewLoader;

    /** Identificador del modulo actualmente visible. */
    private ModuleId currentModule;

    /**
     * Construye un navegador de modulos asociado a un contenedor visual.
     *
     * @param contentHost el {@link StackPane} del layout principal donde se renderizaran las vistas
     */
    public ModuleNavigator(StackPane contentHost) {
        this.contentHost = contentHost;
        this.viewLoader = new ModuleViewLoader();
    }

    /**
     * Navega al modulo especificado cargando su vista FXML.
     * <p>
     * Implementa una verificacion de idempotencia: si el modulo solicitado
     * ya es el actualmente visible, se retorna sin recargar la vista para
     * evitar reinicializaciones innecesarias del controlador.
     * </p>
     *
     * @param moduleId el identificador del modulo al cual navegar
     * @see ModuleViewLoader#loadView(ModuleId)
     */
    public void navigateTo(ModuleId moduleId) {
        if (currentModule == moduleId) {
            return;
        }
        currentModule = moduleId;
        Node view = viewLoader.loadView(moduleId);
        contentHost.getChildren().setAll(view);
    }

    /**
     * Obtiene el identificador del modulo actualmente visible.
     *
     * @return el {@link ModuleId} del modulo actual, o {@code null} si no se ha navegado aun
     */
    public ModuleId getCurrentModule() {
        return currentModule;
    }
}

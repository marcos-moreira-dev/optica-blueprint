package com.marcosmoreira.opticademo.app;

import javafx.application.Application;

/**
 * Punto de entrada principal de la aplicacion OpticaDemo.
 * <p>
 * Esta clase actua como el entry point del JVM y delega en el ciclo de vida
 * de JavaFX llamando a {@link Application#launch(Class, String...)}.
 * Es la clase configurada como {@code Main-Class} en el MANIFEST del JAR
 * y como {@code --main-class} en el empaquetado con jpackage.
 * </p>
 * <p>
 * Flujo de arranque:
 * <ol>
 *   <li>El JVM invoca {@code main(String[])}</li>
 *   <li>{@code launch()} inicia el runtime de JavaFX</li>
 *   <li>JavaFX crea una instancia de {@link App} y llama a {@code init()}</li>
 *   <li>Finalmente se invoca {@code start(Stage)} para mostrar la interfaz</li>
 * </ol>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see App
 * @see Application#launch(Class, String...)
 */
public class AppLauncher {

    /**
     * Metodo principal invocado por el JVM como punto de entrada.
     * <p>
     * Delega en el mecanismo de lanzamiento de JavaFX, que se encarga
     * de inicializar el toolkit y crear el hilo de la interfaz grafica.
     * </p>
     *
     * @param args argumentos de linea de comandos (actualmente no utilizados)
     */
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

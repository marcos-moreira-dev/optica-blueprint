package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.Button;

/**
 * Botón personalizado que encapsula las variantes de estilo del sistema de diseño Optica.
 * <p>
 * Esta clase extiende {@link Button} de JavaFX y centraliza la gestión de clases CSS,
 * eliminando el patrón repetitivo de crear un {@code Button} plano y asignar manualmente
 * las clases de estilo (por ejemplo, {@code styleClass="btn-primary"}) tanto en FXML como
 * en código Java. Al encapsular esta lógica, se garantiza la consistencia visual en toda
 * la aplicación y se reduce la duplicación de código.
 * </p>
 * <h3>Patrones de diseño empleados</h3>
 * <ul>
 *   <li><b>Encapsulamiento de estilo:</b> las clases CSS se aplican internamente mediante
 *       {@link #applyStyle(Style)}, de modo que el consumidor del componente no necesita
 *       conocer los nombres de las clases del sistema de diseño.</li>
 *   <li><b>Constructor en cascada:</b> los constructores se encadenan entre sí, delegando
 *       siempre en la firma más completa {@link #OpticaButton(String, Style)}. Esto evita
 *       la duplicación de lógica de inicialización.</li>
 * </ul>
 * <h3>Integración con el sistema de diseño Optica</h3>
 * <p>
 * El tema "blue-petrol" (azul petróleo sobrio) define una paleta coherente para toda la
 * aplicación de escritorio Optica. Este componente traduce cada variante del enum
 * {@link Style} a las clases CSS correspondientes, asegurando que cualquier botón en la
 * interfaz respete las guías de diseño sin esfuerzo adicional por parte del desarrollador.
 * </p>
 * <h3>Ejemplo de uso</h3>
 * <pre>
 *   var btn = new OpticaButton("Guardar", Style.PRIMARY);
 *   btn.setOnAction(e -> save());
 * </pre>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see Style para conocer las variantes de botón disponibles
 * @see Button clase base de JavaFX que se extiende
 */
public class OpticaButton extends Button {

    /**
     * Enumeración que define las variantes visuales disponibles para {@link OpticaButton}.
     * <p>
     * Cada constante se mapea a una clase CSS específica definida en la hoja de estilos
     * del sistema Optica. La decisión de usar un enum en lugar de strings sueltos obedece
     * a dos razones fundamentales:
     * </p>
     * <ol>
     *   <li><b>Seguridad de tipos:</b> el compilador rechaza valores inválidos, evitando
     *       errores en tiempo de ejecución por nombres de clase mal escritos.</li>
     *   <li><b>Descubribilidad:</b> el autocompletado del IDE muestra todas las variantes
     *       disponibles sin necesidad de consultar documentación externa.</li>
     * </ol>
     * <ul>
     *   <li><b>PRIMARY</b> — Fondo sólido azul petróleo con texto blanco en negrita.
     *       Reservado para la acción principal de cada formulario o diálogo.</li>
     *   <li><b>SECONDARY</b> — Fondo blanco con borde gris. Para acciones alternativas
     *       que no representan el flujo principal del usuario.</li>
     *   <li><b>DANGER</b> — Fondo rojo con texto blanco. Señalización visual inmediata
     *       para acciones destructivas (eliminar, borrar datos).</li>
     *   <li><b>ACTION</b> — Variante compacta similar a SECONDARY, diseñada para botones
     *       de acción dentro de módulos y listados.</li>
     *   <li><b>FILTER_CLEAR</b> — Estilo minimalista sin bordes marcados, pensado para
     *       el botón de limpiar filtros en vistas de búsqueda.</li>
     *   <li><b>ACCESO_RAPIDO</b> — Estilo tipo tarjeta con mayor área de clic, utilizado
     *       en los accesos rápidos de la pantalla de inicio.</li>
     * </ul>
     */
    public enum Style {
        /** Acción principal de formularios y diálogos. */
        PRIMARY,
        /** Acciones secundarias o alternativas. */
        SECONDARY,
        /** Acciones destructivas (eliminar, borrar). */
        DANGER,
        /** Botones compactos dentro de módulos. */
        ACTION,
        /** Botón minimalista para limpiar filtros. */
        FILTER_CLEAR,
        /** Accesos rápidos tipo tarjeta en pantalla de inicio. */
        ACCESO_RAPIDO
    }

    /**
     * Construye un botón vacío con estilo {@link Style#SECONDARY}.
     * <p>
     * Este constructor sin argumentos delega en
     * {@link #OpticaButton(String, Style)} para mantener la consistencia
     * de inicialización. Se elige SECONDARY como valor predeterminado porque
     * representa la opción visualmente menos intrusiva, adecuada cuando el
     * desarrollador no ha especificado una intención de estilo explícita.
     */
    public OpticaButton() {
        this("", Style.SECONDARY);
    }

    /**
     * Construye un botón con el texto indicado y estilo {@link Style#SECONDARY}.
     *
     * @param texto etiqueta visual que se muestra en el botón; puede ser una cadena
     *              vacía si se planea asignar solo un ícono posteriormente
     */
    public OpticaButton(String text) {
        this(text, Style.SECONDARY);
    }

    /**
     * Construye un botón con texto y variante de estilo personalizados.
     * <p>
     * Invoca al constructor de {@link Button} con el texto proporcionado y,
     * a continuación, aplica las clases CSS correspondientes mediante
     * {@link #applyStyle(Style)}.
     *
     * @param texto etiqueta visual del botón
     * @param estilo variante visual que determina la apariencia del botón
     *               según el sistema de diseño Optica
     * @see Style para conocer el significado de cada variante
     */
    public OpticaButton(String text, Style style) {
        super(text);
        applyStyle(style);
    }

    /**
     * Aplica las clases CSS correspondientes a la variante de estilo indicada.
     * <p>
     * Este método primero limpia todas las clases CSS existentes con
     * {@code getStyleClass().clear()} para evitar la acumulación de clases
     * cuando se reutiliza el componente. A continuación, añade la clase base
     * {@code optica-button} (que establece los estilos comunes a todos los
     * botones) y la clase específica de la variante seleccionada.
     * <p>
     * La visibilidad {@code private} es intencionada: las clases CSS son un
     * detalle de implementación que no debe ser manipulable desde fuera del
     * componente, preservando así la integridad del sistema de diseño.
     *
     * @param style variante cuyo mapeo CSS se desea aplicar
     */
    private void applyStyle(Style style) {
        // Clear default classes, then add the specific one
        getStyleClass().clear();
        getStyleClass().add("optica-button");
        switch (style) {
            case PRIMARY -> getStyleClass().add("btn-primary");
            case SECONDARY -> getStyleClass().add("btn-secondary");
            case DANGER -> getStyleClass().add("btn-danger");
            case ACTION -> getStyleClass().add("btn-action");
            case FILTER_CLEAR -> getStyleClass().add("btn-filter-clear");
            case ACCESO_RAPIDO -> getStyleClass().add("btn-acceso-rapido");
        }
    }
}

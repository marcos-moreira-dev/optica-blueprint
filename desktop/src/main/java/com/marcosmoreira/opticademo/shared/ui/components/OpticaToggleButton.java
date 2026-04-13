package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Botón de alternancia personalizado que integra los estilos visuales del sistema de diseño
 * Optica, con diferenciación clara entre los estados seleccionado y no seleccionado.
 * <p>
 * Este componente extiende {@link ToggleButton} de JavaFX y aplica la clase CSS
 * {@code optica-toggle-button} de forma automática. La hoja de estilos del tema Optica
 * define un pseudo-clase {@code :selected} que otorga al estado activo un fondo azul sólido
 * con texto blanco en negrita y un acento de borde izquierdo, proporcionando retroalimentación
 * visual inmediata al usuario. Esta decisión de diseño responde a un problema frecuente en
 * interfaces JavaFX por defecto: los botones de alternancia no ofrecen suficiente contraste
 * visual entre sus estados, lo que genera confusión sobre si una opción está activada o no.
 * <p>
 * El componente puede operar de forma independiente o agruparse en un {@link ToggleGroup}
 * para lograr comportamiento de selección exclusiva (radio-button-like), donde solo un botón
 * del grupo puede estar activo simultáneamente.
 * <p>
 * <b>Patrones de diseño aplicados:</b>
 * <ul>
 *   <li><b>Fluent Interface:</b> los métodos {@link #withPrefWidth(double)} e
 *       {@link #inGroup(ToggleGroup)} devuelven {@code this}, permitiendo encadenar
 *       configuraciones en una expresión fluida y legible.</li>
 *   <li><b>Mediator (parcial):</b> {@link ToggleGroup} actúa como mediador que coordina
 *       la exclusión mutua entre botones, delegando en cada instancia la lógica de
 *       deselección automática.</li>
 * </ul>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *   // Grupo de botones para selección de vista
 *   var grupo = new ToggleGroup();
 *   var vistaTabla   = new OpticaToggleButton("Tabla", grupo);
 *   var vistaTarjeta = new OpticaToggleButton("Tarjeta", grupo);
 *
 *   // Uso fluido con grupo y ancho
 *   var filtroActivo = new OpticaToggleButton("Activos")
 *       .inGroup(grupoFiltros)
 *       .withPrefWidth(120);
 * </pre>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ToggleButton control base de JavaFX que se extiende
 * @see ToggleGroup mecanismo de agrupación para selección exclusiva
 */
public class OpticaToggleButton extends ToggleButton {

    /**
     * Construye un botón de alternancia sin texto.
     * <p>
     * Este constructor es útil cuando el botón mostrará únicamente un icono o cuando el
     * texto se asignará dinámicamente en tiempo de ejecución.
     */
    public OpticaToggleButton() {
        this("");
    }

    /**
     * Construye un botón de alternancia con el texto indicado.
     * <p>
     * El botón se crea de forma independiente, sin pertenecer a ningún grupo de alternancia.
     * Si se desea comportamiento de selección exclusiva, debe asociarse a un
     * {@link ToggleGroup} mediante {@link #inGroup(ToggleGroup)} o
     * {@link #setToggleGroup(ToggleGroup)}.
     *
     * @param text texto visible del botón; si es {@code null}, se asigna una cadena vacía
     */
    public OpticaToggleButton(String text) {
        super(text);
        getStyleClass().add("optica-toggle-button");
    }

    /**
     * Construye un botón de alternancia con texto y lo asocia al grupo indicado.
     * <p>
     * Este constructor de conveniencia permite crear un botón ya vinculado a un grupo
     * de selección exclusiva, ahorrando una llamada adicional. Todos los botones que
     * compartan el mismo {@code ToggleGroup} se comportarán como un conjunto de tipo
     * radio-button, donde la activación de uno desactiva automáticamente los demás.
     *
     * @param text  texto visible del botón
     * @param group grupo de alternancia al que se asociará el botón;
     *              si es {@code null}, el botón queda sin grupo
     */
    public OpticaToggleButton(String text, ToggleGroup group) {
        this(text);
        setToggleGroup(group);
    }

    /**
     * Establece el ancho preferido del botón y devuelve {@code this} para encadenamiento.
     * <p>
     * Implementa el patrón <b>fluent interface</b>, permitiendo configurar el componente
     * en una sola expresión legible.
     *
     * @param width ancho preferido en píxeles; debe ser un valor positivo
     * @return esta misma instancia, permitiendo encadenar más llamadas de configuración
     */
    public OpticaToggleButton withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }

    /**
     * Asocia este botón al grupo de alternancia indicado y devuelve {@code this} para
     * encadenamiento.
     * <p>
     * Este método es la versión fluida de {@link #setToggleGroup(ToggleGroup)}. Cuando
     * varios botones comparten el mismo grupo, la selección de uno deselecciona
     * automáticamente los demás, implementando un comportamiento de selección exclusiva.
     * <p>
     * Si se pasa {@code null}, el botón se libera del grupo actual y puede coexistir
     * en estado seleccionado con otros botones independientes.
     *
     * @param group grupo de alternancia al que se asociará el botón;
     *              si es {@code null}, se libera de cualquier grupo existente
     * @return esta misma instancia, permitiendo encadenar más llamadas de configuración
     * @see ToggleGroup
     */
    public OpticaToggleButton inGroup(ToggleGroup group) {
        setToggleGroup(group);
        return this;
    }
}

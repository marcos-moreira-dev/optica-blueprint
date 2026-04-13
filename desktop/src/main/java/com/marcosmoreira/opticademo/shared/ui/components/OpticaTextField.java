package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.TextField;

/**
 * Campo de texto personalizado que integra los estilos visuales del sistema de diseño Optica.
 * <p>
 * Este componente extiende {@link TextField} de JavaFX y encapsula la asignación de clases
 * CSS, evitando la duplicación de código que surgiría al estilizar manualmente cada instancia
 * de {@code TextField} en la aplicación. La clase CSS {@code optica-text-field} se aplica
 * de forma automática en el constructor, garantizando coherencia visual en todos los campos
 * de entrada de la aplicación.
 * <p>
 * El diseño del componente sigue el principio de mínima sorpresa: el consumidor crea una
 * instancia y obtiene inmediatamente la apariencia del tema sin pasos de configuración
 * adicionales. Si se requiere un ancho preferido, el método {@link #withPrefWidth(double)}
 * ofrece una interfaz fluida (fluent interface) que permite encadenar llamadas.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *   // Campo básico con texto de ayuda
 *   var campoNombre = new OpticaTextField("Ingrese su nombre");
 *
 *   // Campo con ancho predefinido mediante interfaz fluida
 *   var campoBusqueda = new OpticaTextField("Buscar...")
 *       .withPrefWidth(300);
 * </pre>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see TextField control base de JavaFX que se extiende
 */
public class OpticaTextField extends TextField {

    /**
     * Construye un campo de texto sin texto de ayuda.
     * <p>
     * Este constructor es adecuado cuando el campo se etiqueta de forma externa
     * (por ejemplo, con un {@code Label} adyacente) y no requiere un placeholder interno.
     */
    public OpticaTextField() {
        this(null);
    }

    /**
     * Construye un campo de texto con el texto de ayuda indicado.
     * <p>
     * El texto de ayuda ({@code promptText}) se muestra como marca de agua dentro del campo
     * cuando este está vacío y no tiene el foco, guiando al usuario sobre el formato o
     * contenido esperado.
     *
     * @param promptText texto de ayuda que se muestra cuando el campo está vacío;
     *                   si es {@code null}, el campo se crea sin texto de ayuda
     */
    public OpticaTextField(String promptText) {
        if (promptText != null) {
            setPromptText(promptText);
        }
        getStyleClass().add("optica-text-field");
    }

    /**
     * Establece el ancho preferido del campo y devuelve {@code this} para encadenamiento.
     * <p>
     * Este método implementa el patrón <b>fluent interface</b>, permitiendo configurar
     * el componente en una sola expresión. Se prefiere sobre {@link #setPrefWidth(double)}
     * directa cuando se busca legibilidad en la construcción del UI.
     *
     * @param width ancho preferido en píxeles; debe ser un valor positivo
     * @return esta misma instancia, permitiendo encadenar más llamadas de configuración
     */
    public OpticaTextField withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }
}

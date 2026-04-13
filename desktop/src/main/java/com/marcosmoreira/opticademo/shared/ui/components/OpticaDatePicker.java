package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.DatePicker;

/**
 * Selector de fecha personalizado que integra los estilos visuales del sistema de diseño Optica.
 * <p>
 * Este componente extiende {@link DatePicker} de JavaFX y aplica automáticamente la clase
 * CSS {@code optica-date-picker}, garantizando que todos los selectores de fecha de la
 * aplicación compartan una apariencia coherente con el tema azul petróleo sobrio del
 * sistema Optica.
 * <p>
 * Al igual que el resto de componentes de la biblioteca, se emplea el patrón de
 * <b>fluent interface</b> a través de {@link #withPrefWidth(double)} para facilitar la
 * configuración encadenada. Un ancho preferido por defecto de 150 píxeles se establece
 * en el constructor, dimensionado para mostrar una fecha completa en formato localizado
 * sin requerir ajuste manual en la mayoría de los formularios.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *   // Selector básico con texto de ayuda
 *   var fechaInicio = new OpticaDatePicker("Fecha de inicio");
 *   fechaInicio.valueProperty().addListener((obs, antigua, nueva) ->
 *       actualizarRango(nueva));
 *
 *   // Selector con ancho personalizado
 *   var fechaNacimiento = new OpticaDatePicker("Fecha de nacimiento")
 *       .withPrefWidth(200);
 * </pre>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DatePicker control base de JavaFX que se extiende
 */
public class OpticaDatePicker extends DatePicker {

    /**
     * Construye un selector de fecha sin texto de ayuda.
     * <p>
     * Este constructor es apropiado cuando la etiqueta del campo se proporciona de forma
     * externa (por ejemplo, un {@code Label} en la misma fila del formulario) y no se
     * necesita un placeholder interno.
     */
    public OpticaDatePicker() {
        this(null);
    }

    /**
     * Construye un selector de fecha con el texto de ayuda especificado.
     * <p>
     * El texto de ayuda ({@code promptText}) funciona como marca de agua que guía al
     * usuario sobre la fecha esperada. Se establece además un ancho preferido de 150
     * píxeles, suficiente para representar una fecha en formato {@code dd/MM/yyyy}
     * en la tipografía del tema.
     *
     * @param promptText texto de ayuda que se muestra cuando no hay fecha seleccionada;
     *                   si es {@code null}, el componente se crea sin texto de ayuda
     */
    public OpticaDatePicker(String promptText) {
        if (promptText != null) {
            setPromptText(promptText);
        }
        getStyleClass().add("optica-date-picker");
        setPrefWidth(150);
    }

    /**
     * Establece el ancho preferido del selector y devuelve {@code this} para encadenamiento.
     * <p>
     * Implementa el patrón <b>fluent interface</b>, permitiendo configurar el componente
     * en una sola expresión. Sobrescribe el ancho por defecto de 150 píxeles establecido
     * en el constructor.
     *
     * @param width ancho preferido en píxeles; debe ser un valor positivo
     * @return esta misma instancia, permitiendo encadenar más llamadas de configuración
     */
    public OpticaDatePicker withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }
}

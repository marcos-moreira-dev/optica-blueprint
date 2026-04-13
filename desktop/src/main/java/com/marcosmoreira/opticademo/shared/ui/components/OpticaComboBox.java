package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.ComboBox;

/**
 * Caja combinada personalizada que integra los estilos visuales del sistema de diseño Optica
 * y ofrece una API fluida para su configuración.
 * <p>
 * Este componente extiende {@link ComboBox} de JavaFX y centraliza la aplicación de la clase
 * CSS {@code optica-combo-box}, eliminando la necesidad de asignar estilos manualmente en
 * FXML o en código imperativo. Además, proporciona un ancho preferido por defecto y un
 * texto de ayuda ({@code promptText}) genérico que se adapta a la mayoría de los casos de uso.
 * <p>
 * La decisión de tipificar el {@code ComboBox} como {@code ComboBox<String>} responde a la
 * naturaleza del sistema Optica, donde las opciones de selección se representan como cadenas
 * de texto legibles. Si en el futuro se requiriera un modelo de dominio más rico, podría
 * generalizarse a {@code ComboBox<T>} con un {@code StringConverter<T>} apropiado.
 * <p>
 * <b>Patrones de diseño aplicados:</b>
 * <ul>
 *   <li><b>Fluent Interface:</b> los métodos {@link #withPrefWidth(double)} y
 *       {@link #onChange(Runnable)} devuelven {@code this}, permitiendo encadenar
 *       configuraciones en una expresión legible.</li>
 *   <li><b>Observer:</b> el método {@link #onChange(Runnable)} expone un mecanismo de
 *       notificación simplificado para reaccionar a cambios de selección sin exponer
 *       directamente la API de propiedades de JavaFX al consumidor.</li>
 * </ul>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *   // ComboBox con opciones predefinidas
 *   var comboEstado = new OpticaComboBox("Estado:", "Todos", "Activo", "Inactivo");
 *   comboEstado.onChange(() -> filtrarPorEstado(comboEstado.getValue()));
 *
 *   // ComboBox con ancho personalizado
 *   var comboCategoria = new OpticaComboBox("Categoría:", "Ópticas", "Lentes", "Monturas")
 *       .withPrefWidth(250);
 * </pre>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ComboBox control base de JavaFX que se extiende
 */
public class OpticaComboBox extends ComboBox<String> {

    /**
     * Construye una caja combinada con el texto de ayuda por defecto ("Seleccionar...").
     * <p>
     * Este constructor delega en {@link #OpticaComboBox(String)}, proporcionando un valor
     * de placeholder genérico que invita al usuario a realizar una selección.
     */
    public OpticaComboBox() {
        this("Seleccionar...");
    }

    /**
     * Construye una caja combinada con el texto de ayuda especificado.
     * <p>
     * Se establece un ancho preferido de 200 píxeles como valor razonable para la mayoría
     * de los formularios. Si se requiere un ancho distinto, puede ajustarse mediante
     * {@link #withPrefWidth(double)}.
     *
     * @param promptText texto de ayuda que se muestra cuando no hay selección;
     *                   se asigna directamente como placeholder del componente
     */
    public OpticaComboBox(String promptText) {
        super();
        setPromptText(promptText);
        getStyleClass().add("optica-combo-box");
        setPrefWidth(200);
    }

    /**
     * Construye una caja combinada con texto de ayuda y un conjunto de opciones.
     * <p>
     * Las opciones se añaden al modelo del componente y, si la lista no está vacía,
     * se selecciona automáticamente el primer elemento. Esta convención de seleccionar
     * el primer item por defecto reduce la ambigüedad del estado inicial y proporciona
     * un valor válido inmediato para consultas al modelo.
     *
     * @param promptText texto de ayuda que se muestra como placeholder
     * @param items      secuencia de opciones disponibles en la lista desplegable;
     *                   se añaden en el orden proporcionado
     */
    public OpticaComboBox(String promptText, String... items) {
        this(promptText);
        getItems().addAll(items);
        if (items.length > 0) {
            setValue(items[0]);
        }
    }

    /**
     * Establece el ancho preferido de la caja combinada y devuelve {@code this} para
     * encadenamiento.
     * <p>
     * Implementa el patrón <b>fluent interface</b>, permitiendo configurar el componente
     * en una sola expresión. Sobrescribe el ancho por defecto de 200 píxeles establecido
     * en el constructor.
     *
     * @param width ancho preferido en píxeles; debe ser un valor positivo
     * @return esta misma instancia, permitiendo encadenar más llamadas de configuración
     */
    public OpticaComboBox withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }

    /**
     * Registra una acción que se ejecutará cada vez que cambie el valor seleccionado,
     * y devuelve {@code this} para encadenamiento.
     * <p>
     * Este método abstrae la API de propiedades de JavaFX, ofreciendo una interfaz
     * más sencilla para el caso de uso más común: reaccionar a cambios de selección
     * sin necesitar acceso al valor anterior ni a la propiedad observable completa.
     * Si se requiere un control más fino sobre el listener, se puede usar
     * {@link #valueProperty()}.{@code addListener(...)} directamente.
     *
     * @param acción bloque de código que se ejecutará al cambiar la selección;
     *                 no debe ser {@code null}
     * @return esta misma instancia, permitiendo encadenar más llamadas de configuración
     * @see #valueProperty() para acceso directo a la propiedad observable
     */
    public OpticaComboBox onChange(Runnable action) {
        valueProperty().addListener((obs, oldVal, newVal) -> action.run());
        return this;
    }
}

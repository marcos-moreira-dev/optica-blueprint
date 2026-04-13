package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.Button;

/**
 * Custom Button with baked-in Optica styles.
 * <p>
 * Replaces the pattern of creating a plain {@code Button} and manually
 * setting {@code styleClass="btn-primary"} etc. in FXML or code.
 * <p>
 * Usage:
 * <pre>
 *   var btn = new OpticaButton("Guardar", Style.PRIMARY);
 *   btn.setOnAction(e -> save());
 * </pre>
 *
 * @author Optica Demo Desktop
 * @see Style for available button variants
 */
public class OpticaButton extends Button {

    /**
     * Button style variants.
     * <ul>
     *   <li><b>PRIMARY</b> — Solid azul petróleo, texto blanco, negrita. Para acciones principales.</li>
     *   <li><b>SECONDARY</b> — Fondo blanco, borde gris. Para acciones secundarias.</li>
     *   <li><b>DANGER</b> — Fondo rojo, texto blanco. Para acciones destructivas.</li>
     *   <li><b>ACTION</b> — Similar a secondary, más compacto. Para acciones dentro de módulos.</li>
     *   <li><b>FILTER_CLEAR</b> — Minimalista. Para botones de limpiar filtros.</li>
     *   <li><b>ACCESO_RAPIDO</b> — Estilo tipo tarjeta. Para accesos rápidos en Inicio.</li>
     * </ul>
     */
    public enum Style {
        PRIMARY, SECONDARY, DANGER, ACTION, FILTER_CLEAR, ACCESO_RAPIDO
    }

    public OpticaButton() {
        this("", Style.SECONDARY);
    }

    public OpticaButton(String text) {
        this(text, Style.SECONDARY);
    }

    public OpticaButton(String text, Style style) {
        super(text);
        applyStyle(style);
    }

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

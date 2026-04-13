package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Custom ToggleButton with baked-in Optica styles.
 * <p>
 * The {@code :selected} state shows a solid blue background with white bold text
 * and a left border accent — no more "clicked but no visual feedback".
 * <p>
 * Usage:
 * <pre>
 *   var group = new ToggleGroup();
 *   var btn1 = new OpticaToggleButton("Vista 1", group);
 *   var btn2 = new OpticaToggleButton("Vista 2", group);
 * </pre>
 *
 * @author Optica Demo Desktop
 */
public class OpticaToggleButton extends ToggleButton {

    public OpticaToggleButton() {
        this("");
    }

    public OpticaToggleButton(String text) {
        super(text);
        getStyleClass().add("optica-toggle-button");
    }

    public OpticaToggleButton(String text, ToggleGroup group) {
        this(text);
        setToggleGroup(group);
    }

    /**
     * Sets the preferred width and returns {@code this} for chaining.
     */
    public OpticaToggleButton withPrefWidth(double width) {
        setPrefWidth(width);
        return this;
    }

    /**
     * Sets the toggle group and returns {@code this} for chaining.
     */
    public OpticaToggleButton inGroup(ToggleGroup group) {
        setToggleGroup(group);
        return this;
    }
}

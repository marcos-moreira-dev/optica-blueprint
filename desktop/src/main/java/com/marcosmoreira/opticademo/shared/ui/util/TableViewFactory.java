package com.marcosmoreira.opticademo.shared.ui.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Factory utility for creating and configuring JavaFX TableView columns.
 */
public final class TableViewFactory {

    private TableViewFactory() {
        // utility class
    }

    /**
     * Creates a preconfigured TableColumn with the given title and width settings.
     *
     * @param title     the column header text
     * @param minWidth  the minimum width
     * @param prefWidth the preferred width
     * @return a configured TableColumn
     */
    public static <S, T> TableColumn<S, T> createColumn(String title, double minWidth, double prefWidth) {
        TableColumn<S, T> column = new TableColumn<>(title);
        column.setMinWidth(minWidth);
        column.setPrefWidth(prefWidth);
        column.setResizable(true);
        return column;
    }

    /**
     * Creates a preconfigured TableColumn with minWidth and prefWidth set to the same value.
     */
    public static <S, T> TableColumn<S, T> createColumn(String title, double width) {
        return createColumn(title, width, width);
    }

    /**
     * Sets up a TableColumn with a property name for automatic cell value binding.
     *
     * @param column       the TableColumn to configure
     * @param propertyName the JavaFX bean property name (e.g. "nombre", "fecha")
     */
    public static <S, T> void setupColumn(TableColumn<S, T> column, String propertyName) {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    }

    /**
     * Creates and sets up a TableColumn in a single call.
     *
     * @param title        the column header text
     * @param propertyName the JavaFX bean property name
     * @param minWidth     the minimum width
     * @param prefWidth    the preferred width
     * @return a fully configured TableColumn
     */
    public static <S, T> TableColumn<S, T> createAndSetupColumn(
            String title, String propertyName, double minWidth, double prefWidth) {
        TableColumn<S, T> column = createColumn(title, minWidth, prefWidth);
        setupColumn(column, propertyName);
        return column;
    }

    /**
     * Creates and sets up a TableColumn with equal min and preferred width.
     */
    public static <S, T> TableColumn<S, T> createAndSetupColumn(
            String title, String propertyName, double width) {
        return createAndSetupColumn(title, propertyName, width, width);
    }
}

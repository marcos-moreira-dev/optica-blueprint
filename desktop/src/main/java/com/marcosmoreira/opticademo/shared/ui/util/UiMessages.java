package com.marcosmoreira.opticademo.shared.ui.util;

/**
 * Static helper for common UI messages used across JavaFX modules.
 */
public final class UiMessages {

    private UiMessages() {
        // utility class
    }

    /**
     * Returns an empty state message for the given module.
     *
     * @param module the module name (e.g. "Clientes", "Inventario")
     * @return a localized empty state message
     */
    public static String emptyState(String module) {
        return "No hay registros en " + module + ".\nHaga clic en \"Nuevo\" para agregar uno.";
    }

    /**
     * Returns a not found message.
     */
    public static String notFound() {
        return "No se encontraron resultados.";
    }

    /**
     * Returns a not found message with a search term.
     */
    public static String notFound(String searchTerm) {
        return "No se encontraron resultados para \"" + searchTerm + "\".";
    }

    /**
     * Returns a confirmation message for delete actions.
     */
    public static String confirmDelete() {
        return "Confirmar eliminacion";
    }

    /**
     * Returns a confirmation dialog text for deleting an item.
     *
     * @param itemName the name or identifier of the item to delete
     * @return the confirmation message
     */
    public static String confirmDelete(String itemName) {
        return "Esta seguro que desea eliminar \"" + itemName + "\"?\nEsta accion no se puede deshacer.";
    }

    /**
     * Returns a success message for save actions.
     */
    public static String saveSuccess() {
        return "Guardado exitosamente.";
    }

    /**
     * Returns a success message for save actions with an item name.
     *
     * @param itemName the name of the saved item
     * @return the success message
     */
    public static String saveSuccess(String itemName) {
        return "\"" + itemName + "\" guardado exitosamente.";
    }

    /**
     * Returns a generic error title.
     */
    public static String errorTitle() {
        return "Error";
    }

    /**
     * Returns a generic warning title.
     */
    public static String warningTitle() {
        return "Advertencia";
    }

    /**
     * Returns an info title.
     */
    public static String infoTitle() {
        return "Informacion";
    }

    /**
     * Returns a validation error message for required fields.
     *
     * @param fieldName the name of the required field
     * @return the validation message
     */
    public static String fieldRequired(String fieldName) {
        return "El campo \"" + fieldName + "\" es obligatorio.";
    }

    /**
     * Returns a generic unsaved changes warning.
     */
    public static String unsavedChanges() {
        return "Hay cambios sin guardar. Desea salir sin guardar?";
    }

    /**
     * Returns a loading message.
     */
    public static String loading() {
        return "Cargando...";
    }

    /**
     * Returns a confirm-yes button label.
     */
    public static String btnYes() {
        return "Si";
    }

    /**
     * Returns a confirm-no button label.
     */
    public static String btnNo() {
        return "No";
    }

    /**
     * Returns a cancel button label.
     */
    public static String btnCancel() {
        return "Cancelar";
    }
}

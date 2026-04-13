package com.marcosmoreira.opticademo.app.navigation;

/**
 * Registro inmutable que asocia un {@link ModuleId} con una cadena de icono Unicode
 * para su visualización en la barra lateral de navegación de la aplicación de escritorio.
 * Cada instancia representa un elemento del menú lateral, combinando el identificador
 * del módulo con su representación gráfica mediante un carácter Unicode.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ModuleId
 */
public record NavigationItem(ModuleId moduleId, String icon) {

    /**
     * Obtiene el nombre legible del módulo asociado a este elemento de navegación.
     * Este método delega en {@link ModuleId#getDisplayName()} para obtener
     * la representación textual del módulo.
     *
     * @return el nombre visible del módulo correspondiente al {@link #moduleId}
     * @see ModuleId#getDisplayName()
     */
    public String getDisplayName() {
        return moduleId.getDisplayName();
    }
}

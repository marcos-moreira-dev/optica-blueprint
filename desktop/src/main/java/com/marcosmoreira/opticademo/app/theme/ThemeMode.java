package com.marcosmoreira.opticademo.app.theme;

/**
 * Enumeración que representa los modos visuales disponibles para la interfaz
 * de la aplicación de escritorio.
 * <p>
 * Los valores {@code LIGHT} y {@code DARK} determinan la paleta de colores
 * utilizada en la renderización de los componentes de la interfaz. El modo
 * por defecto es {@link #LIGHT}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ThemeManager
 * @see ThemeTokens
 */
public enum ThemeMode {
    /** Tema visual claro con fondos luminosos y texto oscuro. */
    LIGHT,
    /** Tema visual oscuro con fondos oscuros y texto claro. */
    DARK
}

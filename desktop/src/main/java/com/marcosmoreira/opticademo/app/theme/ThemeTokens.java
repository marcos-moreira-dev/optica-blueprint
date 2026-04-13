package com.marcosmoreira.opticademo.app.theme;

/**
 * Clase de constantes que contiene los tokens de diseño CSS (design tokens)
 * empleados en la tematización de la interfaz de la aplicación de escritorio.
 * <p>
 * Los tokens se organizan en tres categorías:
 * </p>
 * <ul>
 *   <li><strong>Colores de acento:</strong> {@code COLOR_PRIMARY}, {@code COLOR_SECONDARY},
 *       {@code COLOR_ALERT}, {@code COLOR_SUCCESS} y {@code COLOR_DANGER}.</li>
 *   <li><strong>Colores de fondo:</strong> {@code BG_GENERAL} (fondo de la ventana),
 *       {@code BG_SURFACE} (superficies de tarjetas y paneles) y {@code BG_SIDEBAR}
 *       (barra lateral de navegación).</li>
 *   <li><strong>Colores de texto:</strong> {@code TEXT_PRIMARY}, {@code TEXT_SECONDARY}
 *       y {@code TEXT_ON_PRIMARY} (texto sobre fondos de acento).</li>
 * </ul>
 * <p>
 * Todas las constantes son {@code public static final} y sus valores están expresados
 * en notación hexadecimal CSS. El constructor privado impide la instanciación
 * accidental de esta clase, ya que su único propósito es actuar como contenedor
 * de constantes.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ThemeManager
 * @see ThemeMode
 */
public class ThemeTokens {

    /** Impide la instanciación de esta clase de constantes. */
    private ThemeTokens() {}

    /** Color primario de la aplicación (azul petróleo). */
    public static final String COLOR_PRIMARY = "#2B5B6E";
    /** Color secundario complementario al primario. */
    public static final String COLOR_SECONDARY = "#5A7D8C";
    /** Color de advertencia para alertas no críticas. */
    public static final String COLOR_ALERT = "#C4882B";
    /** Color indicativo de operaciones exitosas. */
    public static final String COLOR_SUCCESS = "#4A8C5C";
    /** Color indicativo de errores o acciones peligrosas. */
    public static final String COLOR_DANGER = "#A64B4B";

    /** Color de fondo general de la ventana de la aplicación. */
    public static final String BG_GENERAL = "#F4F6F8";
    /** Color de fondo para superficies elevadas (tarjetas, paneles). */
    public static final String BG_SURFACE = "#FFFFFF";
    /** Color de fondo de la barra lateral de navegación. */
    public static final String BG_SIDEBAR = "#1E3A4A";

    /** Color de texto principal para contenido de alta importancia visual. */
    public static final String TEXT_PRIMARY = "#1A202C";
    /** Color de texto secundario para información complementaria. */
    public static final String TEXT_SECONDARY = "#5A6474";
    /** Color de texto para mostrar sobre fondos de color primario. */
    public static final String TEXT_ON_PRIMARY = "#FFFFFF";
}

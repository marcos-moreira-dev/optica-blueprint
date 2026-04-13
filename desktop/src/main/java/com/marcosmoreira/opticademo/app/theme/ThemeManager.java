package com.marcosmoreira.opticademo.app.theme;

/**
 * Gestor de temas de la interfaz, implementado como singleton estático que mantiene
 * el modo visual actual ({@link ThemeMode#LIGHT} o {@link ThemeMode#DARK}) de la
 * aplicación de escritorio.
 * <p>
 * Esta clase proporciona un punto centralizado para consultar y modificar la apariencia
 * visual de la aplicación. Los demás componentes de la interfaz pueden consultar
 * {@link #isDark()} para adaptar dinámicamente sus estilos CSS o comportamientos.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see ThemeMode
 * @see ThemeTokens
 */
public class ThemeManager {

    private static ThemeMode currentTheme = ThemeMode.LIGHT;

    /**
     * Establece el modo de tema visual para la aplicación.
     *
     * @param mode el {@link ThemeMode} a aplicar ({@code LIGHT} o {@code DARK})
     */
    public static void setThemeMode(ThemeMode mode) {
        currentTheme = mode;
    }

    /**
     * Obtiene el modo de tema visual actualmente activo.
     *
     * @return el {@link ThemeMode} actual (por defecto {@link ThemeMode#LIGHT})
     */
    public static ThemeMode getCurrentTheme() {
        return currentTheme;
    }

    /**
     * Comprueba si el tema activo corresponde al modo oscuro.
     *
     * @return {@code true} si el tema actual es {@link ThemeMode#DARK},
     *         {@code false} en caso contrario
     */
    public static boolean isDark() {
        return currentTheme == ThemeMode.DARK;
    }
}

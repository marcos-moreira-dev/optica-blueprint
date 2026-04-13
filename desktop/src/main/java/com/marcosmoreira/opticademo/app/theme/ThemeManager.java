package com.marcosmoreira.opticademo.app.theme;

public class ThemeManager {

    private static ThemeMode currentTheme = ThemeMode.LIGHT;

    public static void setThemeMode(ThemeMode mode) {
        currentTheme = mode;
    }

    public static ThemeMode getCurrentTheme() {
        return currentTheme;
    }

    public static boolean isDark() {
        return currentTheme == ThemeMode.DARK;
    }
}

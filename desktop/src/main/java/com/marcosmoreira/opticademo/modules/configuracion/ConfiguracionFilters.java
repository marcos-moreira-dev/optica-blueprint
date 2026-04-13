package com.marcosmoreira.opticademo.modules.configuracion;

/**
 * Filter state for the Configuracion module search bar.
 */
public class ConfiguracionFilters {

    private final String searchText;

    public ConfiguracionFilters() {
        this("");
    }

    public ConfiguracionFilters(String searchText) {
        this.searchText = searchText != null ? searchText.trim() : "";
    }

    public String getSearchText() {
        return searchText;
    }

    public boolean isEmpty() {
        return searchText.isEmpty();
    }

    public boolean matches(String... candidates) {
        if (isEmpty()) {
            return true;
        }
        String term = searchText.toLowerCase();
        for (String c : candidates) {
            if (c != null && c.toLowerCase().contains(term)) {
                return true;
            }
        }
        return false;
    }
}

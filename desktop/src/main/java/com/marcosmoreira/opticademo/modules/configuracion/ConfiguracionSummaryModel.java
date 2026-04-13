package com.marcosmoreira.opticademo.modules.configuracion;

/**
 * Summary model for the right-hand panel in the Configuracion module.
 * Describes the scope, impact, and recommendation of each configuration category.
 */
public record ConfiguracionSummaryModel(
        String categoria,
        String estadoCambios,
        String alcance,
        String impactoOperativo,
        String recomendacion
) {
    public static ConfiguracionSummaryModel empty(String categoria) {
        return new ConfiguracionSummaryModel(
                categoria,
                "Sin cambios pendientes",
                "",
                "",
                ""
        );
    }
}

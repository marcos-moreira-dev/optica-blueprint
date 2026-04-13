package com.marcosmoreira.opticademo.modules.configuracion;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Configuracion.
 * Describe el alcance, impacto operativo y recomendacion de cada categoria de
 * configuracion del sistema (tarifas, impuestos, parametros de laboratorio, etc.).
 * La fachada (ConfiguracionFacade) actualiza este modelo al navegar entre las
 * distintas categorias de configuracion, mediante el metodo estatico {@code empty}.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record ConfiguracionSummaryModel(
        /** Nombre de la categoria de configuracion. */
        String categoria,
        /** Estado de los cambios pendientes en esta categoria. */
        String estadoCambios,
        /** Alcance del impacto de los cambios (global, por sucursal, etc.). */
        String alcance,
        /** Nivel de impacto operativo que generan los cambios. */
        String impactoOperativo,
        /** Recomendacion sobre la aplicacion de los cambios. */
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

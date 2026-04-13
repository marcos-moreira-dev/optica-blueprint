package com.marcosmoreira.opticademo.modules.recetas;

/**
 * Summary model for the current recipe in the Recetas module.
 */
public record RecetasSummaryModel(
        String cliente,
        String codigo,
        String estado,
        String profesional,
        String fechaReceta,
        String odSph,
        String odCyl,
        String odAxis,
        String oiSph,
        String oiCyl,
        String oiAxis,
        String add,
        String pd,
        String tratamientoSugerido,
        String usoPrincipal
) {
}

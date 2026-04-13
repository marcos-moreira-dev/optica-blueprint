package com.marcosmoreira.opticademo.modules.recetas;

/**
 * Modelo resumen para el panel lateral derecho persistente del modulo Recetas.
 * Contiene los datos optometricos y de tratamiento de la receta seleccionada,
 * incluyendo valores esfericos, cilindricos, ejes, adicion y distancia pupilar.
 * La fachada (RecetasFacade) actualiza este modelo al seleccionar una receta
 * en la tabla principal del modulo.
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public record RecetasSummaryModel(
        /** Nombre del cliente titular de la receta. */
        String cliente,
        /** Codigo identificador de la receta. */
        String codigo,
        /** Estado actual de la receta (vigente, vencida, etc.). */
        String estado,
        /** Profesional que emitio la receta. */
        String profesional,
        /** Fecha de emision de la receta. */
        String fechaReceta,
        /** Esfera del ojo derecho (OD Sph). */
        String odSph,
        /** Cilindro del ojo derecho (OD Cyl). */
        String odCyl,
        /** Eje del ojo derecho (OD Axis). */
        String odAxis,
        /** Esfera del ojo izquierdo (OI Sph). */
        String oiSph,
        /** Cilindro del ojo izquierdo (OI Cyl). */
        String oiAxis,
        /** Eje del ojo izquierdo (OI Axis). */
        String oiAxis,
        /** Adicion para lentes progresivos o bifocales. */
        String add,
        /** Distancia pupilar en milimetros. */
        String pd,
        /** Tratamiento sugerido para los lentes (antirreflejo, fotocromatico, etc.). */
        String tratamientoSugerido,
        /** Uso principal indicado (lejos, cerca, permanente, etc.). */
        String usoPrincipal
) {
}

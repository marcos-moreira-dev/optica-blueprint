package com.marcosmoreira.opticademo.modules.agenda;

import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.shared.query.FilterSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade that queries the DemoStore and returns data for the Agenda module.
 * No business logic -- just view-facing data assembly.
 */
public class AgendaFacade {

    private final DemoStore store;

    public AgendaFacade(DemoStore store) {
        this.store = store;
    }

    // ------------------------------------------------------------------ Sub-view 1: Dia

    public List<AgendaRowModel.CitaDiaRow> getCitasDia(String fecha, AgendaFilters filters) {
        return buildCitasDia(fecha).stream()
                .filter(r -> matchesCitaFilters(r, filters))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ Sub-view 2: Semana

    public List<AgendaRowModel.SemanaRow> getSemana(String fecha) {
        List<AgendaRowModel.SemanaRow> list = new ArrayList<>();

        list.add(AgendaRowModel.SemanaRow.seed(
                "Lunes", "5", "4", "1", "0"
        ));
        list.add(AgendaRowModel.SemanaRow.seed(
                "Martes", "7", "5", "1", "1"
        ));
        list.add(AgendaRowModel.SemanaRow.seed(
                "Miercoles", "6", "4", "2", "0"
        ));
        list.add(AgendaRowModel.SemanaRow.seed(
                "Jueves", "8", "6", "1", "1"
        ));
        list.add(AgendaRowModel.SemanaRow.seed(
                "Viernes", "9", "7", "2", "0"
        ));
        list.add(AgendaRowModel.SemanaRow.seed(
                "Sabado", "4", "3", "1", "0"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Sub-view 3: Lista del dia

    public List<AgendaRowModel.ListaDiaRow> getListaDia(String fecha) {
        List<AgendaRowModel.ListaDiaRow> list = new ArrayList<>();

        list.add(AgendaRowModel.ListaDiaRow.seed(
                "09:00", "Sofia Ramirez", "Examen visual",
                "Confirmada", "Dra. Salazar", "Matriz Centro",
                "Paciente nueva, primera consulta"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "09:30", "Juan Cedeno", "Ajuste",
                "Pendiente", "Tecnico Rivera", "Matriz Centro",
                "Ajuste de montura metalica"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "10:15", "Carmen Lopez", "Entrega",
                "Confirmada", "Asesor Molina", "Matriz Centro",
                "Entrega de lentes progresivos"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "11:00", "Luis Andrade", "Revision",
                "Requiere revision", "Dr. Paredes", "Norte Mall",
                "Control post-operatorio"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "12:00", "Bloqueo interno", "Pausa",
                "Confirmado", "Sistema", "Matriz Centro",
                "Bloqueo por reunion de equipo"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "14:00", "Maria Leon", "Toma medidas",
                "Confirmada", "Asesor Molina", "Matriz Centro",
                "Mediciones para lentes de sol"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "15:30", "Ana Vera", "Control",
                "Reprogramada", "Dra. Salazar", "Norte Mall",
                "Reprogramada por solicitud del cliente"
        ));
        list.add(AgendaRowModel.ListaDiaRow.seed(
                "16:30", "Carlos Mendoza", "Adaptacion LC",
                "Pendiente", "Dr. Paredes", "Matriz Centro",
                "Primera adaptacion de lentes de contacto"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Sub-view 4: Lista de espera

    public List<AgendaRowModel.EsperaRow> getListaEspera() {
        List<AgendaRowModel.EsperaRow> list = new ArrayList<>();

        list.add(AgendaRowModel.EsperaRow.seed(
                "Roberto Guzman", "Examen visual",
                "Manana 09:00-10:00", "Matriz Centro",
                "Alta", "Pendiente contacto"
        ));
        list.add(AgendaRowModel.EsperaRow.seed(
                "Diana Velez", "Control receta",
                "Tarde 14:00-15:00", "Norte Mall",
                "Media", "Contactado - confirma disponibilidad"
        ));
        list.add(AgendaRowModel.EsperaRow.seed(
                "Pedro Rivas", "Entrega de lentes",
                "Manana 10:00-11:00", "Matriz Centro",
                "Alta", "Contactado - espera confirmacion"
        ));
        list.add(AgendaRowModel.EsperaRow.seed(
                "Ines Torres", "Adaptacion LC",
                "Tarde 15:00-16:00", "Norte Mall",
                "Baja", "Sin contactar"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Sub-view 5: Confirmaciones

    public List<AgendaRowModel.ConfirmacionRow> getConfirmaciones() {
        List<AgendaRowModel.ConfirmacionRow> list = new ArrayList<>();

        list.add(AgendaRowModel.ConfirmacionRow.seed(
                "12/04/2026", "09:00", "Sofia Ramirez",
                "Examen visual", "Confirmada",
                "11/04/2026 10:30", "Matriz Centro"
        ));
        list.add(AgendaRowModel.ConfirmacionRow.seed(
                "12/04/2026", "09:30", "Juan Cedeno",
                "Ajuste", "Pendiente",
                "11/04/2026 10:35", "Matriz Centro"
        ));
        list.add(AgendaRowModel.ConfirmacionRow.seed(
                "12/04/2026", "10:15", "Carmen Lopez",
                "Entrega", "Confirmada",
                "11/04/2026 11:00", "Matriz Centro"
        ));
        list.add(AgendaRowModel.ConfirmacionRow.seed(
                "12/04/2026", "14:00", "Maria Leon",
                "Toma medidas", "Confirmada",
                "11/04/2026 14:20", "Matriz Centro"
        ));
        list.add(AgendaRowModel.ConfirmacionRow.seed(
                "12/04/2026", "15:30", "Ana Vera",
                "Control", "Reprogramada",
                "11/04/2026 15:00", "Norte Mall"
        ));
        list.add(AgendaRowModel.ConfirmacionRow.seed(
                "12/04/2026", "16:30", "Carlos Mendoza",
                "Adaptacion LC", "Pendiente",
                "10/04/2026 09:00", "Matriz Centro"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Sub-view 6: Horarios y bloqueos

    public List<AgendaRowModel.HorarioRow> getHorarios(String fecha) {
        List<AgendaRowModel.HorarioRow> list = new ArrayList<>();

        list.add(AgendaRowModel.HorarioRow.seed(
                "08:00 - 09:00", "Horario general", "Activo",
                "Todos los profesionales"
        ));
        list.add(AgendaRowModel.HorarioRow.seed(
                "09:00 - 12:00", "Horario general", "Activo",
                "Todos los profesionales"
        ));
        list.add(AgendaRowModel.HorarioRow.seed(
                "12:00 - 13:00", "Bloqueo", "Confirmado",
                "Sistema - Pausa almuerzo"
        ));
        list.add(AgendaRowModel.HorarioRow.seed(
                "13:00 - 14:00", "Horario general", "Activo",
                "Todos los profesionales"
        ));
        list.add(AgendaRowModel.HorarioRow.seed(
                "14:00 - 17:00", "Horario general", "Activo",
                "Todos los profesionales"
        ));
        list.add(AgendaRowModel.HorarioRow.seed(
                "17:00 - 18:00", "Bloqueo", "Programado",
                "Dra. Salazar - Reunion equipo"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Summary

    public AgendaSummaryModel buildSummary(Object cita) {
        if (cita instanceof AgendaRowModel.CitaDiaRow row) {
            return AgendaSummaryModel.fromCitaDia(row);
        }
        if (cita instanceof AgendaRowModel.ListaDiaRow row) {
            return AgendaSummaryModel.fromListaDia(row);
        }
        return AgendaSummaryModel.demoSeed();
    }

    // ------------------------------------------------------------------ Filter combos

    public List<String> getProfesionales() {
        return List.of(
                "Dra. Salazar",
                "Dr. Paredes",
                "Tecnico Rivera",
                "Asesor Molina",
                "Asesora Lopez"
        );
    }

    public List<String> getEstadosCita() {
        return List.of(
                "Confirmada",
                "Pendiente",
                "Reprogramada",
                "Cancelada",
                "Atendida",
                "Requiere revision",
                "Confirmado"
        );
    }

    public List<String> getTiposAtencion() {
        return List.of(
                "Examen visual",
                "Ajuste",
                "Entrega",
                "Revision",
                "Toma medidas",
                "Control",
                "Adaptacion LC",
                "Pausa"
        );
    }

    // ------------------------------------------------------------------ Internal data builders

    private List<AgendaRowModel.CitaDiaRow> buildCitasDia(String fecha) {
        List<AgendaRowModel.CitaDiaRow> list = new ArrayList<>();

        list.add(AgendaRowModel.CitaDiaRow.seed(
                "09:00", "Sofia Ramirez", "Examen visual",
                "Confirmada", "Dra. Salazar", "Matriz Centro",
                "Examen visual - Sofia Ramirez"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "09:30", "Juan Cedeno", "Ajuste",
                "Pendiente", "Tecnico Rivera", "Matriz Centro",
                "Ajuste de montura - Juan Cedeno"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "10:15", "Carmen Lopez", "Entrega",
                "Confirmada", "Asesor Molina", "Matriz Centro",
                "Entrega de lentes - Carmen Lopez"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "11:00", "Luis Andrade", "Revision",
                "Requiere revision", "Dr. Paredes", "Norte Mall",
                "Revision post-operatoria - Luis Andrade"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "12:00", "Bloqueo interno", "Pausa",
                "Confirmado", "Sistema", "Matriz Centro",
                "Bloqueo por reunion de equipo"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "14:00", "Maria Leon", "Toma medidas",
                "Confirmada", "Asesor Molina", "Matriz Centro",
                "Toma de medidas - Maria Leon"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "15:30", "Ana Vera", "Control",
                "Reprogramada", "Dra. Salazar", "Norte Mall",
                "Control de receta - Ana Vera (reprogramada)"
        ));
        list.add(AgendaRowModel.CitaDiaRow.seed(
                "16:30", "Carlos Mendoza", "Adaptacion LC",
                "Pendiente", "Dr. Paredes", "Matriz Centro",
                "Adaptacion lentes de contacto - Carlos Mendoza"
        ));

        return list;
    }

    // ------------------------------------------------------------------ Filter matching

    private boolean matchesCitaFilters(AgendaRowModel.CitaDiaRow row, AgendaFilters filters) {
        if (!FilterSupport.matchesText(filters.getSearchText(),
                row.cliente(), row.tipoAtencion(), row.profesional())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.profesional(), filters.getProfesional())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.estado(), filters.getEstado())) {
            return false;
        }

        if (!FilterSupport.matchesExact(row.tipoAtencion(), filters.getTipoAtencion())) {
            return false;
        }

        return true;
    }
}

package com.marcosmoreira.opticademo.shared.query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;

/**
 * Utilitario de filtrado de texto para interfaces JavaFX.
 * <p>
 * Esta clase proporciona metodos estaticos para realizar operaciones de
 * filtrado comunes en tablas ({@code TableView}) y listas ({@code ListView})
 * de la aplicacion. Implementa:
 * </p>
 * <ul>
 *   <li>Busqueda insensible a mayusculas/minusculas en multiples campos</li>
 *   <li>Filtrado exacto con soporte para valores comodin ("Todos", "All")</li>
 *   <li>Filtrado por rango de fechas en formato {@code dd/MM/yyyy}</li>
 *   <li>Busqueda en colecciones de strings</li>
 * </ul>
 * <p>
 * <strong>Patron de diseno:</strong> Clase de utilidad con constructor privado
 * para prevenir instanciacion. Todos los metodos son estaticos y sin estado.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public class FilterSupport {

    /** Formato de fecha utilizado para los filtros de rango (dd/MM/yyyy). */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Constructor privado: clase de utilidad, no debe instanciarse. */
    private FilterSupport() {
    }

    /**
     * Verifica si un texto de busqueda coincide con cualquiera de los campos
     * proporcionados (busqueda insensible a mayusculas/minusculas).
     * <p>
     * Si el texto de busqueda es {@code null} o esta en blanco, retorna
     * {@code true} (sin filtro aplicado). Esto permite que los filtros
     * vacios no excluyan ningun registro.
     * </p>
     *
     * @param text   el texto de busqueda ingresado por el usuario
     * @param fields los campos contra los cuales comparar (nombre, telefono, etc.)
     * @return {@code true} si el texto coincide con al menos un campo, o si no hay filtro
     */
    public static boolean matchesText(String text, String... fields) {
        if (text == null || text.isBlank()) {
            return true;
        }
        String normalized = text.trim().toLowerCase();
        for (String field : fields) {
            if (field != null && field.toLowerCase().contains(normalized)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un valor coincide exactamente con un filtro, o si el
     * filtro es un valor comodin ("Todos", "Todas", "All", "Todo").
     * <p>
     * Utilizado principalmente por los {@code ComboBox} de filtrado en
     * las barras de filtros de cada modulo.
     * </p>
     *
     * @param value  el valor a verificar (ej: "ACTIVO", "PENDIENTE")
     * @param filter el filtro seleccionado por el usuario
     * @return {@code true} si el filtro es comodin o coincide exactamente
     */
    public static boolean matchesExact(String value, String filter) {
        if (filter == null || filter.isBlank()) {
            return true;
        }
        String normalized = filter.trim();
        if (normalized.equalsIgnoreCase("todos") ||
                normalized.equalsIgnoreCase("todas") ||
                normalized.equalsIgnoreCase("all") ||
                normalized.equalsIgnoreCase("todo")) {
            return true;
        }
        return normalized.equals(value);
    }

    /**
     * Verifica si una fecha cae dentro del rango definido por {@code from} y {@code to}.
     * <p>
     * Los valores {@code null} o en blanco para {@code from} o {@code to} se
     * tratan como limites no acotados (solo inicio o solo fin). Si la fecha
     * objetivo no se puede parsear, se retorna {@code true} para no excluirla.
     * </p>
     *
     * @param date la fecha a verificar (formato {@code dd/MM/yyyy})
     * @param from fecha inicio del rango (inclusive), o {@code null} para sin limite inferior
     * @param to   fecha fin del rango (inclusive), o {@code null} para sin limite superior
     * @return {@code true} si la fecha esta dentro del rango o no hay filtro aplicado
     */
    public static boolean inRange(String date, String from, String to) {
        if (date == null || date.isBlank()) {
            return true;
        }
        LocalDate target = parseDate(date);
        if (target == null) {
            return true;
        }

        if (from != null && !from.isBlank()) {
            LocalDate fromDate = parseDate(from);
            if (fromDate != null && target.isBefore(fromDate)) {
                return false;
            }
        }

        if (to != null && !to.isBlank()) {
            LocalDate toDate = parseDate(to);
            if (toDate != null && target.isAfter(toDate)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica si algun valor de la coleccion coincide con el texto de filtro.
     * <p>
     * Util para filtrar entidades que tienen multiples valores de texto
     * (ej: categorias, etiquetas).
     * </p>
     *
     * @param values coleccion de valores a examinar
     * @param filter texto de filtro (busqueda parcial insensible a mayusculas)
     * @return {@code true} si algun valor contiene el texto de filtro
     */
    public static boolean anyMatch(Collection<String> values, String filter) {
        if (filter == null || filter.isBlank()) {
            return true;
        }
        if (values == null || values.isEmpty()) {
            return false;
        }
        String normalized = filter.trim().toLowerCase();
        for (String value : values) {
            if (value != null && value.toLowerCase().contains(normalized)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parsea un string en formato {@code dd/MM/yyyy} a un {@link LocalDate}.
     *
     * @param date el string de fecha a parsear
     * @return el {@link LocalDate} correspondiente, o {@code null} si el formato es invalido
     */
    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date.trim(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

package com.marcosmoreira.opticademo.demo.generator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generador de fechas aleatorias formateadas como strings {@code dd/MM/yyyy}.
 * <p>
 * Esta clase de utilidad es utilizada por {@link com.marcosmoreira.opticademo.demo.DemoDataInitializer}
 * para poblar el {@link com.marcosmoreira.opticademo.demo.DemoStore} con fechas realistas:
 * fechas de nacimiento, ultima visita, proxima cita, etc.
 * </p>
 * <p>
 * <strong>Nota tecnica:</strong> Retorna strings en lugar de {@link LocalDate}
 * porque los domain models del sistema almacenan las fechas como strings
 * para simplificar la presentacion en UI (sin necesidad de formateadores
 * en cada celda de tabla).
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public class DateGenerator {

    /** Formateador de fecha patron {@code dd/MM/yyyy} utilizado en toda la aplicacion. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Zona horaria del sistema para calculos de fecha. */
    private static final ZoneId ZONE = ZoneId.systemDefault();

    /**
     * Formatea un {@link LocalDate} al formato estandar {@code dd/MM/yyyy}.
     *
     * @param date la fecha a formatear
     * @return string formateado (ej: "13/04/2026")
     */
    public String format(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Retorna la fecha de hoy formateada como {@code dd/MM/yyyy}.
     *
     * @return la fecha actual formateada
     */
    public String today() {
        return format(LocalDate.now(ZONE));
    }

    /**
     * Genera una fecha aleatoria entre startDate y endDate (ambos inclusive).
     *
     * @param startDate fecha inicio del rango (inclusive)
     * @param endDate   fecha fin del rango (inclusive)
     * @return string de fecha aleatoria en formato {@code dd/MM/yyyy}
     */
    public String randomBetween(LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) return format(startDate);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        return format(startDate.plusDays(randomDays));
    }

    /**
     * Genera una fecha aleatoria en el pasado, hasta {@code maxDaysBack} dias atras.
     *
     * @param maxDaysBack numero maximo de dias hacia el pasado
     * @return string de fecha aleatoria en el pasado
     */
    public String pastDate(int maxDaysBack) {
        LocalDate today = LocalDate.now(ZONE);
        LocalDate start = today.minusDays(maxDaysBack);
        return randomBetween(start, today.minusDays(1));
    }

    /**
     * Genera una fecha aleatoria en el futuro, hasta {@code maxDaysForward} dias adelante.
     *
     * @param maxDaysForward numero maximo de dias hacia el futuro
     * @return string de fecha aleatoria en el futuro
     */
    public String futureDate(int maxDaysForward) {
        LocalDate today = LocalDate.now(ZONE);
        LocalDate end = today.plusDays(maxDaysForward);
        return randomBetween(today.plusDays(1), end);
    }

    /**
     * Retorna una fecha que esta exactamente {@code daysOffset} dias desde hoy.
     * Un valor negativo produce una fecha en el pasado, positivo en el futuro.
     *
     * @param daysOffset desplazamiento en dias desde hoy (negativo = pasado, positivo = futuro)
     * @return string de fecha calculada
     */
    public String offsetDays(int daysOffset) {
        return format(LocalDate.now(ZONE).plusDays(daysOffset));
    }
}

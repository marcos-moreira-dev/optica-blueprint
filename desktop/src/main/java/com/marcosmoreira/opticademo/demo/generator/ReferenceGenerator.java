package com.marcosmoreira.opticademo.demo.generator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generador de referencias secuenciales con prefijo configurable.
 * <p>
 * Produce identificadores unicos con formato {@code PREF-00001},
 * {@code PREF-00002}, etc. Es utilizado para generar codigos internos
 * de entidades como clientes ({@code CLI-00001}), ventas ({@code OV-00001}),
 * ordenes de laboratorio ({@code LAB-00001}), etc.
 * </p>
 * <p>
 * <strong>Thread-safety:</strong> Utiliza {@link AtomicInteger} por cada
 * prefijo y {@link ConcurrentHashMap} para el mapeo de contadores,
 * garantizando atomicidad en entornos concurrentes.
 * </p>
 *
 * <h3>Ejemplo de uso:</h3>
 * <pre>{@code
 * ReferenceGenerator refs = new ReferenceGenerator();
 * refs.next("CLI");  // "CLI-00001"
 * refs.next("CLI");  // "CLI-00002"
 * refs.next("OV");   // "OV-00001"
 * refs.of("LAB", 5); // "LAB-00005" (sin incrementar contador)
 * }</pre>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public class ReferenceGenerator {

    /** Mapa de contadores atomicos por prefijo. */
    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    /** Numero de digitos del padding secuencial (default: 5 → "00001"). */
    private final int padding;

    /**
     * Construye un generador con padding por defecto de 5 digitos.
     * <p>
     * Las referencias generadas tendran el formato {@code PREF-00001}.
     * </p>
     */
    public ReferenceGenerator() {
        this(5);
    }

    /**
     * Construye un generador con padding personalizable.
     *
     * @param padding numero de digitos para la parte secuencial (ej: 5 → "00001", 3 → "001")
     */
    public ReferenceGenerator(int padding) {
        this.padding = padding;
    }

    /**
     * Genera la siguiente referencia para el prefijo dado, incrementando
     * el contador interno automaticamente.
     * <p>
     * Cada prefijo mantiene su propio contador independiente. La primera
     * llamada con un prefijo nuevo genera la secuencia 1.
     * </p>
     *
     * @param prefix el prefijo de la referencia (ej: "CLI", "OV", "LAB")
     * @return la referencia generada (ej: "CLI-00001", luego "CLI-00002", etc.)
     */
    public String next(String prefix) {
        AtomicInteger counter = counters.computeIfAbsent(prefix, k -> new AtomicInteger(0));
        int seq = counter.incrementAndGet();
        return String.format("%s-%0" + padding + "d", prefix, seq);
    }

    /**
     * Genera una referencia con un numero de secuencia especifico, sin
     * incrementar el contador interno.
     * <p>
     * Util para crear referencias con numeros fijos (ej: al poblar datos
     * seed con IDs predefinidos).
     * </p>
     *
     * @param prefix el prefijo de la referencia
     * @param seq    el numero de secuencia deseado
     * @return la referencia formateada con el numero especificado
     */
    public String of(String prefix, int seq) {
        return String.format("%s-%0" + padding + "d", prefix, seq);
    }
}

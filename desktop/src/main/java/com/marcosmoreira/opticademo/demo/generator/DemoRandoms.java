package com.marcosmoreira.opticademo.demo.generator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utilitario de generacion de valores aleatorios para datos demo.
 * <p>
 * Esta clase proporciona metodos estaticos para seleccion aleatoria de
 * elementos de listas, generacion de montos monetarios y decisiones
 * probabilisticas. Es utilizada extensivamente por
 * {@link com.marcosmoreira.opticademo.demo.DemoDataInitializer} para
 * crear datos realistas de demostracion.
 * </p>
 * <p>
 * <strong>Thread-safety:</strong> Utiliza {@link ThreadLocalRandom} para
 * generacion de numeros aleatorios thread-safe sin contencion de locks,
 * apropiado para el entorno multi-hilo de JavaFX.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public class DemoRandoms {

    /** Constructor privado: clase de utilidad, no debe instanciarse. */
    private DemoRandoms() {
        // utility class
    }

    /**
     * Retorna un elemento aleatorio de la lista proporcionada.
     *
     * @param <T>  el tipo de elemento en la lista
     * @param list la lista de donde seleccionar
     * @return un elemento aleatorio, o {@code null} si la lista es vacia o nula
     */
    public static <T> T pick(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    /**
     * Retorna un elemento aleatorio del arreglo de opciones.
     *
     * @param <T>      el tipo de elemento en el arreglo
     * @param options  las opciones entre las cuales elegir
     * @return un elemento aleatorio, o {@code null} si el arreglo es vacio o nulo
     */
    @SafeVarargs
    public static <T> T pick(T... options) {
        if (options == null || options.length == 0) {
            return null;
        }
        return options[ThreadLocalRandom.current().nextInt(options.length)];
    }

    /**
     * Genera un {@code double} aleatorio entre {@code min} (inclusive) y
     * {@code max} (exclusive), redondeado a 2 decimales.
     * <p>
     * Utilizado principalmente para generar montos monetarios (precios,
     * totales de venta, etc.).
     * </p>
     *
     * @param min valor minimo (inclusive)
     * @param max valor maximo (exclusive)
     * @return valor aleatorio redondeado a 2 decimales
     */
    public static double randomAmount(double min, double max) {
        double value = ThreadLocalRandom.current().nextDouble(min, max);
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Genera un {@code int} aleatorio entre {@code min} y {@code max} (ambos inclusive).
     *
     * @param min valor minimo (inclusive)
     * @param max valor maximo (inclusive)
     * @return entero aleatorio en el rango especificado
     */
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Retorna {@code true} con la probabilidad especificada.
     * <p>
     * Ejemplo: {@code chance(0.3)} retorna {@code true} un 30% de las veces.
     * Util para crear datos demo con distribucion realista (ej: 80% activo,
     * 20% inactivo).
     * </p>
     *
     * @param probability probabilidad de retorno {@code true} (0.0 a 1.0)
     * @return {@code true} con la probabilidad dada
     */
    public static boolean chance(double probability) {
        return ThreadLocalRandom.current().nextDouble() < probability;
    }
}

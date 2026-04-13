package com.marcosmoreira.opticademo.app.session;

/**
 * Contexto estático singleton que almacena el identificador del usuario actualmente
 * autenticado en la aplicación de escritorio.
 * <p>
 * Esta implementación utiliza una variable estática simple, lo que significa que
 * <strong>no es thread-safe</strong>. Resulta adecuada para una demostración de
 * escritorio de usuario único, donde no existen accesos concurrentes. En un entorno
 * de producción se debería reemplazar por un mecanismo de gestión de sesión con
 * sincronización adecuada o inyección de dependencias.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see CurrentSucursalContext
 */
public class CurrentUserContext {

    private static String currentUser;

    /**
     * Establece el identificador del usuario actual en el contexto de sesión.
     *
     * @param user el nombre o identificador del usuario que ha iniciado sesión
     */
    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    /**
     * Obtiene el identificador del usuario actualmente almacenado en el contexto.
     *
     * @return el nombre del usuario actual, o {@code null} si no hay sesión activa
     */
    public static String getCurrentUser() {
        return currentUser;
    }
}

package com.marcosmoreira.opticademo.app.session;

/**
 * Contexto estático singleton que almacena la sucursal (rama) actualmente
 * seleccionada por el usuario en la aplicación de escritorio.
 * <p>
 * Al igual que {@link CurrentUserContext}, esta clase emplea una variable estática
 * simple, por lo que <strong>no es thread-safe</strong>. Su propósito es mantener
 * un estado compartido accesible desde cualquier controlador de la interfaz durante
 * la ejecución de la demostración. Para un sistema en producción se recomienda
 * un enfoque con gestión de concurrencia adecuada.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see CurrentUserContext
 */
public class CurrentSucursalContext {

    private static String currentSucursal;

    /**
     * Establece la sucursal actual en el contexto de sesión.
     *
     * @param sucursal el nombre de la sucursal activa seleccionada por el usuario
     */
    public static void setSucursal(String sucursal) {
        currentSucursal = sucursal;
    }

    /**
     * Obtiene la sucursal actualmente almacenada en el contexto.
     *
     * @return el nombre de la sucursal activa, o {@code null} si no se ha establecido
     */
    public static String getCurrentSucursal() {
        return currentSucursal;
    }
}

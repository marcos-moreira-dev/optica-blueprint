package com.marcosmoreira.opticademo.app.session;

/**
 * Proveedor de credenciales predeterminadas exclusivo para la versión de demostración
 * de la aplicación.
 * <p>
 * Esta clase proporciona valores codificados ({@code hardcoded}) para el usuario
 * y la sucursal por defecto, permitiendo que la aplicación funcione sin necesidad
 * de un sistema de autenticación real durante el desarrollo y las presentaciones.
 * </p>
 * <p>
 * <strong>Nota:</strong> en un entorno de producción, esta clase debe ser reemplazada
 * por un sistema de autenticación completo que incluya verificación de credenciales,
 * gestión de tokens y persistencia de sesiones.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public class DemoSession {

    /**
     * Retorna el nombre de usuario predeterminado para la sesión de demostración.
     *
     * @return el correo electrónico del usuario por defecto ({@code "admin\@opticamanager.local"})
     */
    public String getDefaultUser() {
        return "admin@opticamanager.local";
    }

    /**
     * Retorna el nombre de la sucursal predeterminada para la sesión de demostración.
     *
     * @return el nombre de la sucursal por defecto ({@code "Matriz Centro"})
     */
    public String getDefaultSucursal() {
        return "Matriz Centro";
    }
}

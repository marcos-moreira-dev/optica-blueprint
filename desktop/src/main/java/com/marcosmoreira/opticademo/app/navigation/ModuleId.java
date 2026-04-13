package com.marcosmoreira.opticademo.app.navigation;

/**
 * Enumeracion canonica de los 19 modulos del sistema OpticaDemo.
 * <p>
 * Cada constante representa un modulo funcional completo de la aplicacion.
 * Esta enumeracion es utilizada por:
 * </p>
 * <ul>
 *   <li>{@link ModuleNavigator} para realizar la navegacion entre modulos</li>
 *   <li>{@code ModuleViewLoader} para mapear cada modulo a su archivo FXML</li>
 *   <li>{@code MainLayoutController} para construir la barra lateral de navegacion</li>
 *   <li>{@code NavigationItem} para asociar iconos Unicode a cada modulo</li>
 * </ul>
 * <p>
 * Los modulos cubren todas las areas operativas de una optica: desde la
 * agenda de citas y gestion de clientes, hasta ventas, inventario,
 * laboratorio, caja, reportes y configuracion del sistema.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 */
public enum ModuleId {
    /** Dashboard principal con KPIs, alertas y accesos rapidos. */
    INICIO("Inicio"),
    /** Gestion de citas, horarios y lista de espera del consultorio. */
    AGENDA("Agenda"),
    /** Expediente completo de pacientes: datos, historial y recetas. */
    CLIENTES("Clientes"),
    /** Consulta y gestion de recetas oftalmologicas (OD/OI). */
    RECETAS("Recetas"),
    /** Wizard de venta optica: montura, lentes, medidas y pago. */
    VENTA_OPTICA("Venta Optica"),
    /** Cola de ordenes de laboratorio con seguimiento de etapas. */
    ORDENES_LABORATORIO("Ordenes de Laboratorio"),
    /** Catalogo general de productos: monturas, lentes e inventario. */
    INVENTARIO("Inventario"),
    /** Cobros, comprobantes, cierre de caja y pagos pendientes. */
    CAJA("Caja"),
    /** Validacion y registro de entrega de trabajos al cliente. */
    ENTREGAS("Entregas"),
    /** Bandeja de seguimiento: recalls, cobros y mensajes al cliente. */
    SEGUIMIENTO("Seguimiento"),
    /** Reportes ejecutivos: ventas, inventario, agenda y laboratorio. */
    REPORTES("Reportes"),
    /** Configuracion general del negocio, sucursales, usuarios y apariencia. */
    CONFIGURACION("Configuracion"),
    /** Verificacion de seguros, coberturas, autorizaciones y reclamaciones. */
    SEGUROS("Seguros / Cobertura"),
    /** Directorio de proveedores, catalogo y evaluacion de desempeno. */
    PROVEEDORES("Proveedores"),
    /** Gestion de ordenes de compra y abastecimiento de inventario. */
    COMPRAS("Compras"),
    /** Administracion de usuarios, roles, permisos y auditoria del sistema. */
    USUARIOS_ROLES("Usuarios y Roles"),
    /** Taller de reparaciones: diagnostico, ajustes y repuestos. */
    TALLER("Taller / Reparaciones"),
    /** Centro de notificaciones: alertas, recordatorios y campanas. */
    NOTIFICACIONES("Notificaciones"),
    /** Gestion de sucursales: perfil operativo, personal e inventario local. */
    SUCURSALES("Sucursales");

    /** Nombre legible del modulo para mostrar en la interfaz de usuario. */
    private final String displayName;

    /**
     * Construye un identificador de modulo con su nombre para mostrar.
     *
     * @param displayName el texto legible que se muestra en la UI para este modulo
     */
    ModuleId(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Obtiene el nombre legible del modulo para mostrar en la interfaz.
     *
     * @return el nombre display del modulo (ej: "Venta Optica", "Agenda")
     */
    public String getDisplayName() {
        return displayName;
    }
}

package com.marcosmoreira.opticademo.app.navigation;

public enum ModuleId {
    INICIO("Inicio"),
    AGENDA("Agenda"),
    CLIENTES("Clientes"),
    RECETAS("Recetas"),
    VENTA_OPTICA("Venta Optica"),
    ORDENES_LABORATORIO("Ordenes de Laboratorio"),
    INVENTARIO("Inventario"),
    CAJA("Caja"),
    ENTREGAS("Entregas"),
    SEGUIMIENTO("Seguimiento"),
    REPORTES("Reportes"),
    CONFIGURACION("Configuracion"),
    SEGUROS("Seguros / Cobertura"),
    PROVEEDORES("Proveedores"),
    COMPRAS("Compras"),
    USUARIOS_ROLES("Usuarios y Roles"),
    TALLER("Taller / Reparaciones"),
    NOTIFICACIONES("Notificaciones"),
    SUCURSALES("Sucursales");

    private final String displayName;

    ModuleId(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

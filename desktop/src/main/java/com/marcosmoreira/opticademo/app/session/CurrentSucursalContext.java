package com.marcosmoreira.opticademo.app.session;

public class CurrentSucursalContext {

    private static String currentSucursal;

    public static void setSucursal(String sucursal) {
        currentSucursal = sucursal;
    }

    public static String getCurrentSucursal() {
        return currentSucursal;
    }
}

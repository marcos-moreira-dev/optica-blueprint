package com.marcosmoreira.opticademo.demo;

import com.marcosmoreira.opticademo.shared.domain.caja.Cobro;
import com.marcosmoreira.opticademo.shared.domain.cliente.Cliente;
import com.marcosmoreira.opticademo.shared.domain.cobertura.CasoCobertura;
import com.marcosmoreira.opticademo.shared.domain.notificacion.Notificacion;
import com.marcosmoreira.opticademo.shared.domain.producto.Producto;
import com.marcosmoreira.opticademo.shared.domain.proveedor.Proveedor;
import com.marcosmoreira.opticademo.shared.domain.sucursal.Sucursal;
import com.marcosmoreira.opticademo.shared.domain.taller.TrabajoTecnico;
import com.marcosmoreira.opticademo.shared.domain.usuario.Usuario;
import com.marcosmoreira.opticademo.shared.domain.venta.VentaOptica;

import java.util.ArrayList;
import java.util.List;

public class DemoStore {

    public final List<Cliente> clientes = new ArrayList<>();
    public final List<Sucursal> sucursales = new ArrayList<>();
    public final List<Usuario> usuarios = new ArrayList<>();
    public final List<Producto> productos = new ArrayList<>();
    public final List<Proveedor> proveedores = new ArrayList<>();
    public final List<VentaOptica> ventas = new ArrayList<>();
    public final List<Cobro> cobros = new ArrayList<>();
    public final List<Notificacion> notificaciones = new ArrayList<>();
    public final List<CasoCobertura> coberturas = new ArrayList<>();
    public final List<TrabajoTecnico> trabajosTecnicos = new ArrayList<>();

    public void clear() {
        clientes.clear();
        sucursales.clear();
        usuarios.clear();
        productos.clear();
        proveedores.clear();
        ventas.clear();
        cobros.clear();
        notificaciones.clear();
        coberturas.clear();
        trabajosTecnicos.clear();
    }
}

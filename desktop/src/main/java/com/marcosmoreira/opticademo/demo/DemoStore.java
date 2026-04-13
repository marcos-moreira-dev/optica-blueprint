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

/**
 * Almacen de datos en memoria compartido por toda la aplicacion.
 * <p>
 * Esta clase actua como la "base de datos" de la demostración. Contiene
 * listas de todas las entidades del dominio (clientes, productos, ventas,
 * citas, etc.) como {@link ArrayList} simples, accesibles publicamente para
 * que los Facades de cada módulo puedan consultarlas y manipularlas.
 * </p>
 * <p>
 * <strong>Patron de diseño:</strong> En una aplicación de producción, este
 * store se reemplazaría por un repositorio real (JPA, JDBC, etc.). Aqui
 * se usa un enfoque de memoria por simplicidad del demo, aprovechando que
 * {@link java.util.List} implementa {@link java.util.Collection} y puede
 * envolverce en {@link javafx.collections.ObservableList} para binding UI.
 * </p>
 * <p>
 * <strong>Ciclo de vida:</strong>
 * <ol>
 *   <li>Se crea en {@code App.init()}</li>
 *   <li>Se pobla via {@link DemoDataInitializer}</li>
 *   <li>Se accede globalmente via {@code App.getDemoStore()}</li>
 *   <li>Se puede resetear via {@link DemoResetService}</li>
 * </ol>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoDataInitializer
 * @see DemoResetService
 */
public class DemoStore {

    /** Lista de clientes registrados en el sistema. */
    public final List<Cliente> clientes = new ArrayList<>();

    /** Lista de sucursales de la optica. */
    public final List<Sucursal> sucursales = new ArrayList<>();

    /** Lista de usuarios del sistema con sus roles y permisos. */
    public final List<Usuario> usuarios = new ArrayList<>();

    /** Catalogo de productos: monturas, lentes y accesorios. */
    public final List<Producto> productos = new ArrayList<>();

    /** Lista de proveedores de insumos y productos opticos. */
    public final List<Proveedor> proveedores = new ArrayList<>();

    /** Historial de ventas opticas realizadas. */
    public final List<VentaOptica> ventas = new ArrayList<>();

    /** Registro de cobros asociados a las ventas. */
    public final List<Cobro> cobros = new ArrayList<>();

    /** Cola de notificaciones pendientes y enviadas. */
    public final List<Notificacion> notificaciones = new ArrayList<>();

    /** Casos de coberturas de seguros aplicados a ventas. */
    public final List<CasoCobertura> coberturas = new ArrayList<>();

    /** Registro de trabajos técnicos del taller de reparaciones. */
    public final List<TrabajoTecnico> trabajosTecnicos = new ArrayList<>();

    /**
     * Limpia todas las listas del store, dejando las colecciones vacias
     * pero listas para ser repobladas.
     * <p>
     * Este método es utilizado por {@link DemoResetService} antes de
     * re-ejecutar el inicializador de datos.
     * </p>
     */
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

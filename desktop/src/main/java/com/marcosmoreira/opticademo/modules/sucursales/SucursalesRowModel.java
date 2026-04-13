package com.marcosmoreira.opticademo.modules.sucursales;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las vistas del modulo Sucursales (gestion multi-sede).
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: directorio de
 * sucursales, personal y permisos por sede, inventario y stock por sucursal,
 * agenda y flujo local, caja y desempeno por sucursal, y comparativo entre
 * sucursales. La fachada crea estas instancias para la gestion centralizada
 * de todas las sedes de la optica.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.sucursales.SucursalesFacade
 */
public final class SucursalesRowModel {

    private SucursalesRowModel() {
    }

    /**
     * Modelo de fila para el directorio de sucursales.
     * <p>
     * Lista todas las sedes registradas con su informacion basica.
     * El campo {@code servicios} enuma los servicios disponibles en esa sede.
     * </p>
     *
     * @param sucursal    nombre de la sede (columna "Sucursal")
     * @param ciudad      ubicacion (columna "Ciudad")
     * @param responsable encargado de la sede (columna "Responsable")
     * @param horario     horario de atencion (columna "Horario")
     * @param estado      estado: "Activa", "Inactiva" (columna "Estado")
     * @param servicios   servicios disponibles (columna "Servicios")
     */
    public record DirectorioRow(
            String sucursal,
            String ciudad,
            String responsable,
            String horario,
            String estado,
            String servicios
    ) {
        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty ciudadProperty() {
            return new SimpleStringProperty(ciudad);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }

        public StringProperty horarioProperty() {
            return new SimpleStringProperty(horario);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty serviciosProperty() {
            return new SimpleStringProperty(servicios);
        }
    }

    /**
     * Modelo de fila para el personal y permisos por sucursal.
     * <p>
     * Muestra los usuarios asignados a cada sede y sus permisos principales.
     * </p>
     *
     * @param usuario          nombre de usuario (columna "Usuario")
     * @param rol              rol asignado (columna "Rol")
     * @param estado           estado del usuario (columna "Estado")
     * @param accesoPrincipal  modulo de acceso principal (columna "Acceso Principal")
     */
    public record PersonalRow(
            String usuario,
            String rol,
            String estado,
            String accesoPrincipal
    ) {
        public StringProperty usuarioProperty() {
            return new SimpleStringProperty(usuario);
        }

        public StringProperty rolProperty() {
            return new SimpleStringProperty(rol);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty accesoPrincipalProperty() {
            return new SimpleStringProperty(accesoPrincipal);
        }
    }

    /**
     * Modelo de fila para el inventario y stock por sucursal.
     * <p>
     * Muestra el estado del inventario en cada sede.
     * </p>
     *
     * @param referencia  codigo SKU (columna "Referencia")
     * @param nombre      nombre del producto (columna "Nombre")
     * @param categoria   categoria (columna "Categoria")
     * @param stock       cantidad disponible (columna "Stock")
     * @param estado      estado del stock (columna "Estado")
     * @param observacion nota sobre el producto (columna "Observacion")
     */
    public record InventarioRow(
            String referencia,
            String nombre,
            String categoria,
            String stock,
            String estado,
            String observacion
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty nombreProperty() {
            return new SimpleStringProperty(nombre);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty stockProperty() {
            return new SimpleStringProperty(stock);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Modelo de fila para la agenda y flujo local por sucursal.
     * <p>
     * Muestra indicadores operativos de la agenda en cada sede: citas del dia,
     * profesionales activos, etc.
     * </p>
     *
     * @param indicador   nombre del indicador (columna "Indicador")
     * @param valor       valor actual (columna "Valor")
     * @param estado      estado del indicador (columna "Estado")
     * @param observacion nota sobre el indicador (columna "Observacion")
     */
    public record AgendaRow(
            String indicador,
            String valor,
            String estado,
            String observacion
    ) {
        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Modelo de fila para caja y desempeno por sucursal.
     * <p>
     * Indicadores financieros y operativos de cada sede.
     * </p>
     *
     * @param indicador   nombre del indicador (columna "Indicador")
     * @param valor       valor medido (columna "Valor")
     * @param estado      estado del indicador (columna "Estado")
     * @param observacion nota analitica (columna "Observacion")
     */
    public record CajaRow(
            String indicador,
            String valor,
            String estado,
            String observacion
    ) {
        public StringProperty indicadorProperty() {
            return new SimpleStringProperty(indicador);
        }

        public StringProperty valorProperty() {
            return new SimpleStringProperty(valor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }

    /**
     * Modelo de fila para el comparativo entre sucursales.
     * <p>
     * Vista ejecutiva que compara el rendimiento de todas las sedes en metricas
     * clave: ventas, ticket promedio, stock critico, recalls pendientes y retrasos.
     * El campo {@code estadoGeneral} resume la situacion global de cada sede.
     * </p>
     *
     * @param sucursal          nombre de la sede (columna "Sucursal")
     * @param ventas            total de ventas (columna "Ventas")
     * @param ticketPromedio    valor del ticket promedio (columna "Ticket Promedio")
     * @param stockCritico      items en stock critico (columna "Stock Critico")
     * @param recallsPendientes recalls sin atender (columna "Recalls Pendientes")
     * @param retrasos          retrasos acumulados (columna "Retrasos")
     * @param estadoGeneral     estado global de la sede (columna "Estado General")
     */
    public record ComparativoRow(
            String sucursal,
            String ventas,
            String ticketPromedio,
            String stockCritico,
            String recallsPendientes,
            String retrasos,
            String estadoGeneral
    ) {
        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty ventasProperty() {
            return new SimpleStringProperty(ventas);
        }

        public StringProperty ticketPromedioProperty() {
            return new SimpleStringProperty(ticketPromedio);
        }

        public StringProperty stockCriticoProperty() {
            return new SimpleStringProperty(stockCritico);
        }

        public StringProperty recallsPendientesProperty() {
            return new SimpleStringProperty(recallsPendientes);
        }

        public StringProperty retrasosProperty() {
            return new SimpleStringProperty(retrasos);
        }

        public StringProperty estadoGeneralProperty() {
            return new SimpleStringProperty(estadoGeneral);
        }
    }
}

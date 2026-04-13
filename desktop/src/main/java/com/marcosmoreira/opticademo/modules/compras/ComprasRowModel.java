package com.marcosmoreira.opticademo.modules.compras;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelos de fila para las 7 sub-vistas del modulo Compras.
 * <p>
 * Estos registros alimentan los {@code TableView} del modulo: solicitudes de compra,
 * ordenes de compra, compras por proveedor, back-orders pendientes, recepcion
 * vinculada, compras por sucursal e historico. La fachada crea estas instancias
 * a partir de las entidades {@code SolicitudCompra} y {@code OrdenCompra} del dominio.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see com.marcosmoreira.opticademo.modules.compras.ComprasFacade
 */
public final class ComprasRowModel {

    private ComprasRowModel() {
    }

    // ==================== Sub-view 1: Solicitudes de compra ====================

    /**
     * Modelo de fila para las solicitudes de compra internas.
     * <p>
     * Registra las solicitudes generadas automaticamente por stock critico o
     * manualmente por el personal. El campo {@code prioridad} indica la urgencia.
     * </p>
     *
     * @param solicitud          identificador de la solicitud (columna "Solicitud")
     * @param motivo             motivo: "Stock critico", "Manual" (columna "Motivo")
     * @param categoria          categoria de productos solicitados (columna "Categoria")
     * @param sucursalDestino    sede que solicita (columna "Sucursal Destino")
     * @param prioridad          prioridad: "Alta", "Media", "Baja" (columna "Prioridad")
     * @param estado             estado: "Pendiente", "Aprobada", "Rechazada" (columna "Estado")
     * @param proveedorSugerido  proveedor recomendado (columna "Proveedor Sugerido")
     */
    public record SolicitudRow(
            String solicitud,
            String motivo,
            String categoria,
            String sucursalDestino,
            String prioridad,
            String estado,
            String proveedorSugerido
    ) {
        public StringProperty solicitudProperty() {
            return new SimpleStringProperty(solicitud);
        }

        public StringProperty motivoProperty() {
            return new SimpleStringProperty(motivo);
        }

        public StringProperty categoriaProperty() {
            return new SimpleStringProperty(categoria);
        }

        public StringProperty sucursalDestinoProperty() {
            return new SimpleStringProperty(sucursalDestino);
        }

        public StringProperty prioridadProperty() {
            return new SimpleStringProperty(prioridad);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty proveedorSugeridoProperty() {
            return new SimpleStringProperty(proveedorSugerido);
        }
    }

    // ==================== Sub-view 2: Ordenes de compra ====================

    /**
     * Modelo de fila para las ordenes de compra enviadas a proveedores.
     *
     * @param orden          numero de orden (columna "Orden")
     * @param proveedor      proveedor destinatario (columna "Proveedor")
     * @param fecha          fecha de emision (columna "Fecha")
     * @param sucursalDestino sede destino de la mercaderia (columna "Sucursal Destino")
     * @param estado         estado de la orden (columna "Estado")
     * @param items          cantidad de items (columna "Items")
     * @param totalEstimado  costo estimado total (columna "Total Estimado")
     */
    public record OrdenCompraRow(
            String orden,
            String proveedor,
            String fecha,
            String sucursalDestino,
            String estado,
            String items,
            String totalEstimado
    ) {
        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty sucursalDestinoProperty() {
            return new SimpleStringProperty(sucursalDestino);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty itemsProperty() {
            return new SimpleStringProperty(items);
        }

        public StringProperty totalEstimadoProperty() {
            return new SimpleStringProperty(totalEstimado);
        }
    }

    // ==================== Sub-view 3: Compras por proveedor ====================

    /**
     * Modelo de fila para el resumen de compras agrupado por proveedor.
     *
     * @param proveedor          nombre del proveedor (columna "Proveedor")
     * @param ordenesAbiertas    cantidad de ordenes activas (columna "Ordenes Abiertas")
     * @param ultimaCompra       fecha de la ultima compra (columna "Ultima Compra")
     * @param categoriaPrincipal categoria mas comprada (columna "Categoria Principal")
     * @param estado             estado general de la relacion (columna "Estado")
     */
    public record ProveedorCompraRow(
            String proveedor,
            String ordenesAbiertas,
            String ultimaCompra,
            String categoriaPrincipal,
            String estado
    ) {
        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty ordenesAbiertasProperty() {
            return new SimpleStringProperty(ordenesAbiertas);
        }

        public StringProperty ultimaCompraProperty() {
            return new SimpleStringProperty(ultimaCompra);
        }

        public StringProperty categoriaPrincipalProperty() {
            return new SimpleStringProperty(categoriaPrincipal);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 4: Back-orders y pendientes ====================

    /**
     * Modelo de fila para los items pendientes de recepcion (back-orders).
     * <p>
     * Muestra productos que fueron parcialmente recibidos y quedan pendientes
     * de entrega por parte del proveedor.
     * </p>
     *
     * @param referencia    identificador del pendiente (columna "Referencia")
     * @param orden         orden de compra asociada (columna "Orden")
     * @param proveedor     proveedor responsable (columna "Proveedor")
     * @param itemPendiente nombre del item pendiente (columna "Item Pendiente")
     * @param cantidad      cantidad pendiente (columna "Cantidad")
     * @param fechaEsperada fecha estimada de llegada (columna "Fecha Esperada")
     * @param estado        estado del pendiente (columna "Estado")
     */
    public record BackOrderRow(
            String referencia,
            String orden,
            String proveedor,
            String itemPendiente,
            String cantidad,
            String fechaEsperada,
            String estado
    ) {
        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty itemPendienteProperty() {
            return new SimpleStringProperty(itemPendiente);
        }

        public StringProperty cantidadProperty() {
            return new SimpleStringProperty(cantidad);
        }

        public StringProperty fechaEsperadaProperty() {
            return new SimpleStringProperty(fechaEsperada);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }
    }

    // ==================== Sub-view 5: Recepcion vinculada ====================

    /**
     * Modelo de fila para las recepciones vinculadas a ordenes de compra.
     *
     * @param recepcion   identificador de la recepcion (columna "Recepcion")
     * @param orden       orden de compra asociada (columna "Orden")
     * @param fecha       fecha de recepcion (columna "Fecha")
     * @param estado      estado: "Completa", "Parcial", "Con diferencia" (columna "Estado")
     * @param diferencias diferencias encontradas (columna "Diferencias")
     * @param responsable persona que recibio (columna "Responsable")
     */
    public record RecepcionCompraRow(
            String recepcion,
            String orden,
            String fecha,
            String estado,
            String diferencias,
            String responsable
    ) {
        public StringProperty recepcionProperty() {
            return new SimpleStringProperty(recepcion);
        }

        public StringProperty ordenProperty() {
            return new SimpleStringProperty(orden);
        }

        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty diferenciasProperty() {
            return new SimpleStringProperty(diferencias);
        }

        public StringProperty responsableProperty() {
            return new SimpleStringProperty(responsable);
        }
    }

    // ==================== Sub-view 6: Compras por sucursal ====================

    /**
     * Modelo de fila para el resumen de compras agrupado por sucursal.
     *
     * @param sucursal       nombre de la sede (columna "Sucursal")
     * @param solicitudes    solicitudes activas (columna "Solicitudes")
     * @param ordenesAbiertas ordenes en proceso (columna "Ordenes Abiertas")
     * @param pendientes     recepciones pendientes (columna "Pendientes")
     * @param totalEstimado  valor estimado total (columna "Total Estimado")
     * @param estadoGeneral  estado general de compras (columna "Estado General")
     */
    public record SucursalCompraRow(
            String sucursal,
            String solicitudes,
            String ordenesAbiertas,
            String pendientes,
            String totalEstimado,
            String estadoGeneral
    ) {
        public StringProperty sucursalProperty() {
            return new SimpleStringProperty(sucursal);
        }

        public StringProperty solicitudesProperty() {
            return new SimpleStringProperty(solicitudes);
        }

        public StringProperty ordenesAbiertasProperty() {
            return new SimpleStringProperty(ordenesAbiertas);
        }

        public StringProperty pendientesProperty() {
            return new SimpleStringProperty(pendientes);
        }

        public StringProperty totalEstimadoProperty() {
            return new SimpleStringProperty(totalEstimado);
        }

        public StringProperty estadoGeneralProperty() {
            return new SimpleStringProperty(estadoGeneral);
        }
    }

    // ==================== Sub-view 7: Historico ====================

    /**
     * Modelo de fila para el historico de compras.
     *
     * @param fecha        fecha del registro (columna "Fecha")
     * @param referencia   identificador del documento (columna "Referencia")
     * @param tipoRegistro tipo: "Solicitud", "Orden", "Recepcion" (columna "Tipo")
     * @param proveedor    proveedor involucrado (columna "Proveedor")
     * @param estado       estado del registro (columna "Estado")
     * @param observacion  nota sobre el evento (columna "Observacion")
     */
    public record HistoricoCompraRow(
            String fecha,
            String referencia,
            String tipoRegistro,
            String proveedor,
            String estado,
            String observacion
    ) {
        public StringProperty fechaProperty() {
            return new SimpleStringProperty(fecha);
        }

        public StringProperty referenciaProperty() {
            return new SimpleStringProperty(referencia);
        }

        public StringProperty tipoRegistroProperty() {
            return new SimpleStringProperty(tipoRegistro);
        }

        public StringProperty proveedorProperty() {
            return new SimpleStringProperty(proveedor);
        }

        public StringProperty estadoProperty() {
            return new SimpleStringProperty(estado);
        }

        public StringProperty observacionProperty() {
            return new SimpleStringProperty(observacion);
        }
    }
}

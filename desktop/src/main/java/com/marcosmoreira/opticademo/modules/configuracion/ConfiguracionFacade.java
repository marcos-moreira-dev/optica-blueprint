package com.marcosmoreira.opticademo.modules.configuracion;

import com.marcosmoreira.opticademo.demo.DemoStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Facade para el modulo de Configuracion (parametros del sistema).
 * <p>
 * Este facade suministra todos los datos de configuracion para las nueve categorias
 * del modulo de Configuracion. Los datos son seed data estatico que representan
 * la configuracion inicial del sistema de la optica.
 * </p>
 * <p>
 * Categorias que alimenta:
 * <ul>
 *   <li><b>General del Negocio:</b> datos institucionales, RUC, moneda, zona horaria.</li>
 *   <li><b>Sucursales:</b> sedes fisicas con direccion, horario y responsable.</li>
 *   <li><b>Usuarios:</b> cuentas de acceso con rol y sucursal.</li>
 *   <li><b>Catalogos Maestros:</b> marcas, categorias, materiales, tratamientos, etc.</li>
 *   <li><b>Inventario:</b> reglas de stock minimo y alertas.</li>
 *   <li><b>Venta y Caja:</b> prefijos de orden, comprobantes, reglas de pago.</li>
 *   <li><b>Agenda y Seguimiento:</b> duracion de citas, recall, canales.</li>
 *   <li><b>Laboratorio:</b> laboratorios habilitados, tiempos promesa.</li>
 *   <li><b>Apariencia:</b> tema visual, densidad, tooltips.</li>
 * </ul>
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see DemoStore
 */
public class ConfiguracionFacade {

    private final DemoStore store;

    public ConfiguracionFacade(DemoStore store) {
        this.store = store;
    }

    // ──────────────────────────────────────────────
    // Category 1: General del negocio
    // ──────────────────────────────────────────────

    public record GeneralNegocio(
            String nombreComercial,
            String razonSocial,
            String ruc,
            String telefono,
            String correo,
            String direccion,
            String ciudad,
            String sucursalPredeterminada,
            String moneda,
            String zonaHoraria
    ) {}

    public GeneralNegocio getGeneralNegocio() {
        return new GeneralNegocio(
                "Optica Manager Demo",
                "Optica Visual Centro S.A.",
                "0999999999001",
                "04 600 0000",
                "contacto@opticamanager.local",
                "Av. Principal y calle comercial",
                "Guayaquil",
                "Matriz Centro",
                "USD",
                "America/Guayaquil"
        );
    }

    // ──────────────────────────────────────────────
    // Category 2: Sucursales
    // ──────────────────────────────────────────────

    public List<ConfiguracionRowModel.SucursalRow> getSucursales() {
        return List.of(
                new ConfiguracionRowModel.SucursalRow(
                        "Matriz Centro",
                        "Av. Principal y calle comercial",
                        "08:00 - 18:00",
                        "Laura Gomez",
                        "Activa"
                ),
                new ConfiguracionRowModel.SucursalRow(
                        "Norte Mall",
                        "Centro comercial, local 12",
                        "10:00 - 20:00",
                        "Carlos Mendoza",
                        "Activa"
                )
        );
    }

    // ──────────────────────────────────────────────
    // Category 3: Usuarios
    // ──────────────────────────────────────────────

    public List<ConfiguracionRowModel.UsuarioRow> getUsuarios() {
        return List.of(
                new ConfiguracionRowModel.UsuarioRow(
                        "admin@opticamanager.local",
                        "Administrador general",
                        "Matriz Centro",
                        "Activo"
                ),
                new ConfiguracionRowModel.UsuarioRow(
                        "recepcion@opticamanager.local",
                        "Recepcion",
                        "Norte Mall",
                        "Activo"
                ),
                new ConfiguracionRowModel.UsuarioRow(
                        "ventas@opticamanager.local",
                        "Asesor de ventas",
                        "Matriz Centro",
                        "Activo"
                ),
                new ConfiguracionRowModel.UsuarioRow(
                        "tecnico@opticamanager.local",
                        "Tecnico optico",
                        "Matriz Centro",
                        "Activo"
                )
        );
    }

    // ──────────────────────────────────────────────
    // Category 4: Catalogos maestros
    // ──────────────────────────────────────────────

    public List<String> getCatalogoTypes() {
        return List.of(
                "Marcas",
                "Categorias de productos",
                "Materiales de montura",
                "Tipos de lente",
                "Tratamientos",
                "Tipos de cita",
                "Estados del sistema",
                "Prioridades",
                "Tipos de seguimiento"
        );
    }

    public List<ConfiguracionRowModel.CatalogoRow> getCatalogoValues(String tipo) {
        return switch (tipo) {
            case "Marcas" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Ray-Ban", "Activo", "Marcas"),
                    new ConfiguracionRowModel.CatalogoRow("Oakley", "Activo", "Marcas"),
                    new ConfiguracionRowModel.CatalogoRow("Vogue", "Activo", "Marcas"),
                    new ConfiguracionRowModel.CatalogoRow("Gucci", "Activo", "Marcas")
            );
            case "Categorias de productos" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Monturas", "Activo", "Categorias de productos"),
                    new ConfiguracionRowModel.CatalogoRow("Lentes oftalmicos", "Activo", "Categorias de productos"),
                    new ConfiguracionRowModel.CatalogoRow("Lentes de sol", "Activo", "Categorias de productos"),
                    new ConfiguracionRowModel.CatalogoRow("Lentes de contacto", "Activo", "Categorias de productos"),
                    new ConfiguracionRowModel.CatalogoRow("Accesorios", "Activo", "Categorias de productos")
            );
            case "Materiales de montura" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Acero inoxidable", "Activo", "Materiales de montura"),
                    new ConfiguracionRowModel.CatalogoRow("Titanio", "Activo", "Materiales de montura"),
                    new ConfiguracionRowModel.CatalogoRow("Acetato", "Activo", "Materiales de montura"),
                    new ConfiguracionRowModel.CatalogoRow("TR90", "Activo", "Materiales de montura"),
                    new ConfiguracionRowModel.CatalogoRow("Aluminio", "Activo", "Materiales de montura")
            );
            case "Tipos de lente" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Monofocal", "Activo", "Tipos de lente"),
                    new ConfiguracionRowModel.CatalogoRow("Bifocal", "Activo", "Tipos de lente"),
                    new ConfiguracionRowModel.CatalogoRow("Progresivo", "Activo", "Tipos de lente"),
                    new ConfiguracionRowModel.CatalogoRow("Ocupacional", "Activo", "Tipos de lente")
            );
            case "Tratamientos" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Antirreflejo", "Activo", "Tratamientos"),
                    new ConfiguracionRowModel.CatalogoRow("Blue cut", "Activo", "Tratamientos"),
                    new ConfiguracionRowModel.CatalogoRow("Fotocromatico", "Activo", "Tratamientos"),
                    new ConfiguracionRowModel.CatalogoRow("Hidrofonico", "Activo", "Tratamientos")
            );
            case "Tipos de cita" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Examen visual", "Activo", "Tipos de cita"),
                    new ConfiguracionRowModel.CatalogoRow("Entrega de trabajo", "Activo", "Tipos de cita"),
                    new ConfiguracionRowModel.CatalogoRow("Ajuste de montura", "Activo", "Tipos de cita"),
                    new ConfiguracionRowModel.CatalogoRow("Control post-venta", "Activo", "Tipos de cita")
            );
            case "Estados del sistema" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Activo", "Activo", "Estados del sistema"),
                    new ConfiguracionRowModel.CatalogoRow("Inactivo", "Activo", "Estados del sistema"),
                    new ConfiguracionRowModel.CatalogoRow("Pendiente", "Activo", "Estados del sistema"),
                    new ConfiguracionRowModel.CatalogoRow("Bloqueado", "Activo", "Estados del sistema")
            );
            case "Prioridades" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Alta", "Activo", "Prioridades"),
                    new ConfiguracionRowModel.CatalogoRow("Media", "Activo", "Prioridades"),
                    new ConfiguracionRowModel.CatalogoRow("Baja", "Activo", "Prioridades"),
                    new ConfiguracionRowModel.CatalogoRow("Urgente", "Activo", "Prioridades")
            );
            case "Tipos de seguimiento" -> List.of(
                    new ConfiguracionRowModel.CatalogoRow("Post-venta", "Activo", "Tipos de seguimiento"),
                    new ConfiguracionRowModel.CatalogoRow("Recall anual", "Activo", "Tipos de seguimiento"),
                    new ConfiguracionRowModel.CatalogoRow("Trabajo en proceso", "Activo", "Tipos de seguimiento"),
                    new ConfiguracionRowModel.CatalogoRow("Saldo pendiente", "Activo", "Tipos de seguimiento")
            );
            default -> List.of();
        };
    }

    // ──────────────────────────────────────────────
    // Category 5: Inventario y abastecimiento
    // ──────────────────────────────────────────────

    public record InventarioConfig(
            String stockMinimo,
            String ubicacionDefault,
            boolean alertasCriticas,
            boolean generacionAviso,
            boolean recepcionParcial
    ) {}

    public InventarioConfig getInventarioConfig() {
        return new InventarioConfig(
                "5",
                "Bodega general",
                true,
                true,
                true
        );
    }

    // ──────────────────────────────────────────────
    // Category 6: Venta, caja y comprobantes
    // ──────────────────────────────────────────────

    public record VentaConfig(
            String prefijoOrden,
            String prefijoComprobante,
            String secuenciaInicial,
            boolean permiteAbonos,
            String descuentoMaximo,
            boolean permiteSaldoPendiente
    ) {}

    public VentaConfig getVentaConfig() {
        return new VentaConfig(
                "OV",
                "FAC",
                "1000",
                true,
                "15%",
                true
        );
    }

    // ──────────────────────────────────────────────
    // Category 7: Agenda, seguimiento y comunicacion
    // ──────────────────────────────────────────────

    public record AgendaConfig(
            String duracionCita,
            boolean recallActivo,
            String diasAnticipacion,
            String canalPreferido
    ) {}

    public AgendaConfig getAgendaConfig() {
        return new AgendaConfig(
                "30 minutos",
                true,
                "30",
                "WhatsApp"
        );
    }

    // ──────────────────────────────────────────────
    // Category 8: Laboratorio y entregas
    // ──────────────────────────────────────────────

    public record LaboratorioConfig(
            String laboratoriosHabilitados,
            String tiempoPromesa,
            boolean requiereValidacion,
            boolean bloqueaConSaldo
    ) {}

    public LaboratorioConfig getLaboratorioConfig() {
        return new LaboratorioConfig(
                "Optica Interna, LabVision Externo",
                "3 dias",
                true,
                true
        );
    }

    // ──────────────────────────────────────────────
    // Category 9: Apariencia y experiencia de uso
    // ──────────────────────────────────────────────

    public record AparienciaConfig(
            String modoApariencia,
            String densidadVisual,
            boolean mostrarIconos,
            boolean mostrarTooltips,
            boolean confirmarAccionesCriticas
    ) {}

    public AparienciaConfig getAparienciaConfig() {
        return new AparienciaConfig(
                "Claro",
                "Normal",
                true,
                true,
                true
        );
    }

    // ──────────────────────────────────────────────
    // Summary builder
    // ──────────────────────────────────────────────

    private static final Map<String, ConfiguracionSummaryModel> SUMMARIES = Map.ofEntries(
            Map.entry("General del negocio", new ConfiguracionSummaryModel(
                    "General del negocio",
                    "Sin cambios pendientes",
                    "Esta categoria define los datos institucionales que identifican a la optica dentro del sistema, comprobantes y modulos de uso diario.",
                    "Los cambios aqui pueden afectar encabezados, comprobantes, sucursal predeterminada y datos visibles en modulos administrativos.",
                    "Revise cuidadosamente los datos institucionales antes de modificar numeraciones, sucursales o datos de contacto visibles."
            )),
            Map.entry("Sucursales y operacion", new ConfiguracionSummaryModel(
                    "Sucursales y operacion",
                    "Sin cambios pendientes",
                    "Define las sedes fisicas, horarios y reglas operativas de cada punto de atencion de la optica.",
                    "Afecta la asignacion de usuarios, inventario por sede y disponibilidad de caja.",
                    "Verifique que cada sucursal tenga un responsable asignado antes de activar operaciones."
            )),
            Map.entry("Usuarios, roles y permisos", new ConfiguracionSummaryModel(
                    "Usuarios, roles y permisos",
                    "Sin cambios pendientes",
                    "Administra los perfiles de acceso, roles y alcance operativo de cada usuario del sistema.",
                    "Impacta directamente la seguridad y que usuarios pueden ver o modificar en cada modulo.",
                    "Asigne solo los permisos necesarios por rol. Evite cuentas con acceso total innecesario."
            )),
            Map.entry("Catalogos maestros", new ConfiguracionSummaryModel(
                    "Catalogos maestros",
                    "Sin cambios pendientes",
                    "Centraliza listas maestras que alimentan al resto del sistema: marcas, categorias, tratamientos, etc.",
                    "Cambios aqui se reflejan en formularios de productos, ordenes y filtros de toda la aplicacion.",
                    "Mantenga los catalogos actualizados pero sin duplicados. Desactive valores en lugar de eliminarlos."
            )),
            Map.entry("Inventario y abastecimiento", new ConfiguracionSummaryModel(
                    "Inventario y abastecimiento",
                    "Sin cambios pendientes",
                    "Define reglas globales de stock minimo, ubicaciones y recepcion de mercaderia.",
                    "Afecta alertas de inventario, generacion de pedidos y flujo de recepcion de compras.",
                    "Ajuste el stock minimo segun la rotacion real de cada categoria de producto."
            )),
            Map.entry("Venta, caja y comprobantes", new ConfiguracionSummaryModel(
                    "Venta, caja y comprobantes",
                    "Sin cambios pendientes",
                    "Parametriza numeraciones de orden, comprobantes, abonos y reglas de pago.",
                    "Impacta directamente la salida documental y el flujo de caja diario.",
                    "No modifique prefijos ni secuencias una vez en produccion sin un respaldo previo."
            )),
            Map.entry("Agenda, seguimiento y comunicacion", new ConfiguracionSummaryModel(
                    "Agenda, seguimiento y comunicacion",
                    "Sin cambios pendientes",
                    "Configura duracion de citas, recall anual, recordatorios y canales de contacto con clientes.",
                    "Afecta la programacion de citas y la comunicacion automatica con clientes.",
                    "Active el recall anual para fomentar visitas de control y aumentar la fidelizacion."
            )),
            Map.entry("Laboratorio y entregas", new ConfiguracionSummaryModel(
                    "Laboratorio y entregas",
                    "Sin cambios pendientes",
                    "Define tiempos promesa, estados de trabajo y validacion de entregas de laboratorio.",
                    "Impacta los tiempos de entrega al cliente y el flujo de trabajos externos.",
                    "Mantenga tiempos promesa realistas segun la capacidad actual de los laboratorios."
            )),
            Map.entry("Apariencia y experiencia de uso", new ConfiguracionSummaryModel(
                    "Apariencia y experiencia de uso",
                    "Sin cambios pendientes",
                    "Ajusta la apariencia visual, densidad y comportamiento general de la interfaz del sistema.",
                    "Afecta la experiencia diaria de todos los usuarios pero no la logica de negocio.",
                    "Pruebe los cambios de tema con un grupo reducido antes de aplicarlos globalmente."
            ))
    );

    public ConfiguracionSummaryModel buildSummary(String categoria) {
        return SUMMARIES.getOrDefault(categoria, ConfiguracionSummaryModel.empty(categoria));
    }
}

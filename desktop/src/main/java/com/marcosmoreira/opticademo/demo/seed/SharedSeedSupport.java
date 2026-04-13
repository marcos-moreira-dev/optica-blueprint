package com.marcosmoreira.opticademo.demo.seed;

import java.util.List;

/**
 * Common seed data constants used across demo data generation.
 */
public final class SharedSeedSupport {

    private SharedSeedSupport() {
        // utility class
    }

    // --- Client names ---
    public static final List<String> CLIENT_NAMES = List.of(
            "Sofia Ramirez",
            "Juan Cedenio",
            "Carmen Lopez",
            "Luis Andrade",
            "Maria Leon",
            "Ana Vera",
            "Carlos Mendoza",
            "Diana Velez"
    );

    // --- Professional / user names ---
    public static final List<String> PROFESSIONAL_NAMES = List.of(
            "Dr. Andres Villavicencio",
            "Lcda. Patricia Mendoza",
            "Tec. Ricardo Salazar",
            "Ing. Monica Paredes"
    );

    // --- Sucursal names ---
    public static final List<String> SUCURSAL_NAMES = List.of(
            "Matriz Centro",
            "Norte Mall"
    );

    // --- Product references (brands/models) ---
    public static final List<String> PRODUCT_REFERENCES = List.of(
            "RAY-BAN RB5154",
            "OAKLEY OX8091",
            "VOGUE VO2876",
            "LENTES ANTIREFLEJO 1.67",
            "ESTUCHE PREMIUM CUERO",
            "LIMPIADOR OPTICO 120ML",
            "CAREY CLASICO ACETATO",
            "LENTES PROGRESIVOS VARILUX"
    );

    // --- Product names ---
    public static final List<String> PRODUCT_NAMES = List.of(
            "Montura Ray-Ban RB5154 Negro",
            "Montura Oakley OX8091 Mate",
            "Montura Vogue VO2876 Rosa",
            "Lentes Antirreflejo Indice 1.67",
            "Estuche Premium Cuero Negro",
            "Limpiador Optico Profesional 120ml",
            "Montura Carey Clasico Acetato",
            "Lentes Progresivos Varilux Premium"
    );

    // --- Product categories ---
    public static final List<String> PRODUCT_CATEGORIES = List.of(
            "MONTURA",
            "MONTURA",
            "MONTURA",
            "LENTES",
            "ACCESORIO",
            "ACCESORIO",
            "MONTURA",
            "LENTES"
    );

    // --- Product brands ---
    public static final List<String> PRODUCT_BRANDS = List.of(
            "Ray-Ban",
            "Oakley",
            "Vogue",
            "Hoya",
            "Generico",
            "Generico",
            "Arnette",
            "Varilux"
    );

    // --- Provider names ---
    public static final List<String> PROVIDER_NAMES = List.of(
            "Distribuidora Optica Nacional S.A.",
            "LensTech Ecuador Cia. Ltda.",
            "Accesorios Visuales Import"
    );

    // --- Payment methods ---
    public static final List<String> PAYMENT_METHODS = List.of(
            "EFECTIVO",
            "TARJETA_CREDITO",
            "TARJETA_DEBITO",
            "TRANSFERENCIA"
    );

    // --- Notification types ---
    public static final List<String> NOTIFICATION_TYPES = List.of(
            "RECORDATORIO_ENTREGA",
            "ALERTA_COBRO",
            "AVISOS_GARANTIA"
    );

    // --- Notification channels ---
    public static final List<String> NOTIFICATION_CHANNELS = List.of(
            "SMS",
            "EMAIL",
            "WHATSAPP"
    );

    // --- Cobertura types ---
    public static final List<String> COBERTURA_TYPES = List.of(
            "CONVENIO_EMPRESARIAL",
            "SEGURO_VISUAL_PLUS",
            "GARANTIA_EXTENDED_2A"
    );

    // --- Cobertura plans ---
    public static final List<String> COBERTURA_PLANS = List.of(
            "Plan Corporativo Vision",
            "Seguro Visual Plus",
            "Garantia Extendida 2 Anos"
    );

    // --- Trabajo tecnico types ---
    public static final List<String> TRABAJO_TECNICO_TYPES = List.of(
            "AJUSTE_DE_MONTURA",
            "CAMBIO_DE_PLAQUETAS",
            "REEMPLAZO_TORNILLOS_Y_BISAGRAS"
    );

    // --- Technical staff names ---
    public static final List<String> TECHNICIAN_NAMES = List.of(
            "Ricardo Salazar",
            "Miguel Angel Torres",
            "Fernando Cevallos"
    );

    // --- Observaciones for notifications ---
    public static final List<String> NOTIF_OBSERVATIONS = List.of(
            "Cliente notificado sobre fecha de entrega de orden",
            "Recordatorio de pago pendiente enviado",
            "Aviso de vigencia de cobertura proxima a vencer"
    );

    // --- Laboratorio names ---
    public static final List<String> LABORATORIO_NAMES = List.of(
            "Lab. Central",
            "Lab. Externo Premium",
            "Lab. Interno"
    );

    // --- Lente types ---
    public static final List<String> LENTE_TYPES = List.of(
            "Monofocal",
            "Bifocal",
            "Progresivo",
            "Antirreflejo",
            "Fotocromatico"
    );
}

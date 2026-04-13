# Configuración

## ¿Para qué sirve este módulo?

El módulo **Configuración** es el centro de control de todo el sistema. Aquí usted define cómo funciona su óptica: datos del negocio, sucursales, usuarios y permisos, reglas de inventario, condiciones de venta, horarios de atención, reglas de laboratorio y apariencia del sistema.

## ¿Quién usa esta pantalla?

- **Administradores generales:** es su herramienta principal para configurar el sistema.
- **Dueños del negocio:** para definir las reglas operativas y comerciales.

> **Precaución:** Los cambios en Configuración afectan el funcionamiento de toda la aplicación. Realice cambios con cuidado y documente lo que modifica.

---

## Categorías de configuración

El módulo se organiza en 9 categorías, accesibles desde la barra lateral izquierda:

| Categoría | Qué controla |
|-----------|-------------|
| **General del negocio** | Nombre, logo, datos fiscales, moneda. |
| **Sucursales y operaciones** | Sedes, horarios, servicios por sucursal. |
| **Usuarios y roles** | Quién accede al sistema y qué puede hacer. |
| **Catálogos maestros** | Listas de valores: marcas, materiales, tratamientos. |
| **Inventario y abastecimiento** | Stock mínimo, alertas, reglas de reposición. |
| **Venta, caja y comprobantes** | Impuestos, descuentos, formas de pago. |
| **Agenda y seguimiento** | Duración de citas, reglas de recordatorios. |
| **Laboratorio y entregas** | Etapas de fabricación, tiempos estimados. |
| **Apariencia y experiencia** | Tema visual, idioma, formato de fecha. |

---

## General del negocio

### Datos de la empresa

Configure aquí la información general de su óptica:

| Campo | Descripción |
|-------|-------------|
| **Nombre del negocio** | Nombre que aparece en el encabezado y comprobantes |
| **Logo** | Imagen que se muestra en login, encabezado y reportes |
| **RUC/NIT** | Identificación fiscal de la empresa |
| **Dirección fiscal** | Dirección legal del negocio |
| **Teléfono principal** | Número de contacto público |
| **Correo electrónico** | Email oficial del negocio |
| **Sitio web** | Página web (si aplica) |
| **Moneda** | Moneda en la que se maneja el sistema (USD, EUR, etc.) |

---

## Sucursales y operaciones

### Gestión de sedes

Para cada sucursal puede configurar:

| Campo | Descripción |
|-------|-------------|
| **Nombre** | Nombre de la sucursal (ej: "Matriz Centro") |
| **Dirección** | Dirección física |
| **Teléfono** | Número de contacto |
| **Responsable** | Quién está a cargo |
| **Horario operativo** | Horario de atención al público |
| **Servicios habilitados** | Qué módulos usa esta sucursal: |
| | - Caja habilitada |
| | - Inventario propio |
| | - Entregas habilitadas |
| | - Agenda habilitada |
| **Estado** | Activo / Observado / Cerrado |

---

## Usuarios y roles

### Quién accede al sistema

Esta sección se detalla en el módulo **Usuarios y Roles**, pero desde aquí puede:
- Crear nuevos usuarios
- Asignar roles
- Activar o desactivar usuarios
- Ver sesiones activas

---

## Catálogos maestros

### Listas de valores del sistema

Estos catálogos alimentan los desplegables de toda la aplicación:

#### Marcas de monturas
Ray-Ban, Oakley, Vogue, Armani, Gucci, etc.

#### Materiales de monturas
Metal, Acetato, TR-90, Titanio, Mixto, Aluminio

#### Tipos de lentes
Monofocal, Bifocal, Progresivo

#### Materiales de lentes
CR-39 (Orgánico), Policarbonato, High-Index 1.67, High-Index 1.74

#### Tratamientos
Antirreflejo, Filtro UV, Blue-cut, Fotocromático, Endurecido, Espejo

#### Tipos de atención
Examen visual nuevo, Control, Urgencia, Adaptación a progresivos

> **Tip:** Mantenga estos catálogos actualizados para que los asesores de venta tengan opciones consistentes al ofrecer productos.

---

## Inventario y abastecimiento

### Reglas de gestión de stock

| Parámetro | Descripción | Valor sugerido |
|-----------|-------------|----------------|
| **Stock mínimo de monturas** | Nivel bajo el cual se alerta reposición | 5 unidades por modelo |
| **Stock mínimo de lentes** | Nivel bajo de lentes en blanco | 10 pares por tipo |
| **Alerta de stock crítico** | Notificar cuando un producto está por debajo | Sí |
| **Método de reposición** | Automático o manual | Manual |
| **Proveedor predeterminado** | Proveedor principal de cada categoría | Configurar por categoría |

---

## Venta, caja y comprobantes

### Reglas comerciales

| Parámetro | Descripción |
|-----------|-------------|
| **Impuesto aplicado** | IVA/IGV u otro impuesto sobre ventas |
| **Porcentaje de impuesto** | Valor del impuesto (ej: 12%) |
| **Descuento máximo permitido** | Tope de descuento que un asesor puede aplicar sin autorización |
| **Plazo máximo de abonos** | Días que un paciente tiene para cancelar su saldo |
| **Formas de pago habilitadas** | Cuáles acepta: efectivo, tarjeta, transferencia, mixto |
| **Numeración de comprobantes** | Prefijo y secuencia de facturación |
| **Mensaje en comprobante** | Texto que aparece al pie del recibo |

---

## Agenda y seguimiento

### Reglas de citas y seguimiento

| Parámetro | Descripción | Valor sugerido |
|-----------|-------------|----------------|
| **Duración estándar de cita** | Minutos por defecto para un examen visual | 30 minutos |
| **Duración de control** | Minutos para consulta de seguimiento | 15 minutos |
| **Antelación para cancelar** | Horas mínimas antes de la cita para cancelar sin penalización | 24 horas |
| **Recordatorio automático** | Cuántas horas antes enviar recordatorio | 24 horas |
| **Canal de recordatorio** | Llamada, SMS, Email | Llamada + SMS |
| **Plazo de recall** | Meses después de un examen para programar el próximo recall | 6-12 meses |

---

## Laboratorio y entregas

### Reglas de fabricación

| Parámetro | Descripción | Valor sugerido |
|-----------|-------------|----------------|
| **Tiempo estimado de fabricación** | Días que toma fabricar una orden estándar | 3-5 días hábiles |
| **Tiempo para progresivos** | Días adicionales para lentes progresivos | +2 días |
| **Tiempo para alto índice** | Días adicionales para lentes high-index | +2 días |
| **Etapas de fabricación** | Qué etapas tiene el proceso (configurable) | Recepción → Corte → Montaje → QC → Entrega |
| **Notificación al paciente** | Enviar aviso cuando la orden está lista | Sí, por SMS |
| **Plazo de retiro** | Días que el paciente tiene para retirar antes de escalar | 15 días |

---

## Apariencia y experiencia

### Personalización visual

| Opción | Descripción |
|--------|-------------|
| **Tema visual** | Claro (Light) u Oscuro (Dark) |
| **Idioma** | Idioma de la interfaz |
| **Formato de fecha** | DD/MM/AAAA o MM/DD/AAAA |
| **Formato de moneda** | $1,234.56 o 1.234,56 |

---

## Casos de uso comunes

### "Acabo de abrir una nueva sucursal"
1. Vaya a **Sucursales y operaciones**
2. Haga clic en **Nueva sucursal**
3. Complete nombre, dirección, teléfono, responsable, horario
4. Seleccione qué servicios habilita
5. Guarde

### "Quiero cambiar el logo de la empresa"
1. Vaya a **General del negocio**
2. Haga clic en **Cambiar logo**
3. Seleccione la imagen (PNG o JPG, recomendado 512x512px)
4. Guarde

### "Necesito agregar una marca de monturas nueva"
1. Vaya a **Catálogos maestros → Marcas de monturas**
2. Haga clic en **Agregar**
3. Escriba el nombre de la marca
4. Guarde

### "Quiero que el descuento máximo de los asesores sea 15%"
1. Vaya a **Venta, caja y comprobantes**
2. Cambie **Descuento máximo permitido** a 15%
3. Guarde

### "Quiero que los recordatorios se envíen 48 horas antes"
1. Vaya a **Agenda y seguimiento**
2. Cambie **Antelación para recordatorio** a 48 horas
3. Guarde

---

## Notas importantes
- Los cambios en catálogos maestros se reflejan inmediatamente en toda la aplicación.
- Desactivar una sucursal no borra sus datos históricos; solo impide nuevas operaciones.
- Los usuarios desactivados pierden acceso inmediatamente pero su histórico se conserva.
- Siempre documente los cambios importantes de configuración para referencia futura.
- Algunos cambios requieren reiniciar la aplicación para aplicarse completamente.

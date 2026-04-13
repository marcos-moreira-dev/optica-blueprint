# Clientes

## ¿Para qué sirve este módulo?

El módulo **Clientes** es el expediente digital de cada uno de sus pacientes. Aquí usted puede ver sus datos personales, historial de visitas, recetas oftalmológicas vigentes, órdenes de compra y entregas pendientes. Es el lugar donde consulta toda la información de un paciente antes de atenderlo.

## ¿Quién usa esta pantalla?

- **Optometristas:** para revisar el historial del paciente antes del examen visual.
- **Asesores de venta:** para conocer las compras anteriores y ofrecer productos adecuados.
- **Recepcionistas:** para actualizar datos de contacto y agendar citas.
- **Administradores:** para revisar el comportamiento general de la cartera de pacientes.

---

## Vista general del módulo

### Tabla de clientes

La vista principal muestra una tabla con todos los pacientes registrados. Cada fila incluye:
- **Código** del cliente (ej: CLI-00124)
- **Nombre completo**
- **Teléfono**
- **Correo electrónico**
- **Ciudad**
- **Última visita**
- **Estado de receta** (Vigente, Por vencer, Vencida, Sin receta)
- **Estado general** (Activo, Inactivo, Observado)

### Panel de resumen (lado derecho)

Al seleccionar un cliente, el panel derecho muestra un resumen rápido:
- Datos de contacto
- Última receta
- Última visita
- Órdenes pendientes
- Estado actual

---

## Sub-vistas del módulo de Clientes

El módulo tiene 5 sub-vistas que puede cambiar con los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Ficha general** | Datos personales, dirección, teléfono, correo, ciudad, sucursal habitual. |
| **Historial** | Lista cronológica de todas las visitas del paciente al consultorio y a la óptica. |
| **Recetas** | Historial de recetas oftalmológicas (exámenes visuales OD/OI). |
| **Órdenes y entregas** | Compras y pedidos asociados al paciente, con su estado de entrega. |
| **Seguimiento** | Acciones de seguimiento pendientes o programadas para el paciente. |

---

## Ficha general del cliente

Aquí puede ver y editar:

| Campo | Descripción |
|-------|-------------|
| **Nombre completo** | Nombre y apellido del paciente |
| **Documento de identidad** | Cédula o pasaporte |
| **Teléfono** | Número de contacto principal |
| **Teléfono alternativo** | Segundo número de contacto |
| **Correo electrónico** | Email para comunicaciones |
| **Dirección** | Dirección de domicilio |
| **Ciudad** | Ciudad de residencia |
| **Sucursal habitual** | La sucursal donde asiste con más frecuencia |
| **Estado** | Activo / Inactivo / Observado |

> **Tip:** Mantener los datos de contacto actualizados es fundamental para enviar recordatorios de citas y notificaciones de entregas.

---

## Historial del cliente

Esta vista muestra una línea de tiempo con todas las interacciones del paciente:

- **Citas realizadas:** Fecha, profesional atendido, tipo de atención
- **Compras anteriores:** Qué monturas, lentes o productos adquirió
- **Visitas al taller:** Reparaciones o ajustes realizados
- **Seguimientos:** Llamadas o mensajes enviados al paciente

> **Para el optometrista:** Revisar el historial antes de un examen le da contexto sobre la evolución visual del paciente.

---

## Recetas del cliente

Aquí encontrará todas las recetas oftalmológicas (exámenes visuales) del paciente, ordenadas de la más reciente a la más antigua.

### Información que muestra cada receta
- **Fecha del examen**
- **Profesional** que realizó el examen
- **Medición OD (ojo derecho):** Esfera, Cilindro, Eje, Adición, Prismas
- **Medición OI (ojo izquierdo):** Esfera, Cilindro, Eje, Adición, Prismas
- **Distancia pupilar (DP)**
- **Observaciones** del profesional
- **Estado de la receta:** Vigente / Por vencer / Vencida

### Estado de receta
| Estado | Qué significa |
|--------|---------------|
| **Vigente** | La receta está dentro de su período de validez |
| **Por vencer** | La receta está próxima a expirar (próximo a 6 meses) |
| **Vencida** | La receta expiró y el paciente necesita un nuevo examen |
| **Sin receta** | El paciente no tiene ningún examen registrado |

---

## Órdenes y entregas

Muestra todas las compras y pedidos del paciente:

| Columna | Descripción |
|---------|-------------|
| **Número de orden** | Referencia de la orden (ej: OV-00045) |
| **Fecha** | Cuándo se realizó la compra |
| **Descripción** | Montura + lentes + tratamiento |
| **Estado de entrega** | Pendiente / En laboratorio / Lista para entrega / Entregada |
| **Monto total** | Valor de la orden |
| **Estado de pago** | Pagada / Parcialmente pagada / Pendiente |

---

## Búsqueda y filtros

### Buscar un cliente rápidamente
Use el campo de búsqueda en la parte superior. Puede buscar por:
- **Nombre:** Escriba el nombre completo o parcial
- **Código:** Ingrese el código del cliente (ej: CLI-00124)
- **Teléfono:** Ingrese el número de teléfono
- **Correo electrónico:** Ingrese el email

### Filtros avanzados
- **Estado general:** Active, Inactivo, Observado
- **Última visita:** Reciente (< 30 días), Intermedia (30-90 días), Antigua (> 90 días)
- **Estado de receta:** Vigente, Por vencer, Vencida, Sin receta

---

## Casos de uso comunes

### "Un paciente llegó y quiero ver su historia antes de atenderlo"
1. Vaya a **Clientes**
2. Busque al paciente por nombre o código
3. Haga clic en su fila
4. Revise la sub-vista **Historial** y **Recetas** para contexto

### "Necesito actualizar el teléfono de un paciente"
1. Busque al paciente
2. Seleccione la sub-vista **Ficha general**
3. Edite el campo **Teléfono**
4. Guarde los cambios

### "Quiero ver qué pacientes tienen la receta vencida"
1. En la barra de filtros, seleccione **Estado de receta → Vencida**
2. La tabla se filtrará automáticamente para mostrar solo esos pacientes
3. Puede contactar a cada uno para ofrecer un nuevo examen visual

### "Un paciente pregunta por su última compra"
1. Busque al paciente
2. Vaya a la sub-vista **Órdenes y entregas**
3. Verá todas sus compras con fecha, descripción y estado

### "Quiero saber qué pacientes no vienen hace mucho tiempo"
1. Filtre por **Última visita → Antigua (> 90 días)**
2. Esos pacientes son candidatos para una campaña de recordatorio

---

## Notas importantes
- Un paciente puede tener múltiples recetas; siempre consulte la más reciente.
- Si un paciente se muda de ciudad, actualice su dato en la Ficha general para mantener la información limpia.
- El sistema no permite eliminar clientes, solo desactivarlos. Esto preserva el historial clínico.
- Los datos del paciente son compartidos entre todas las sucursales.

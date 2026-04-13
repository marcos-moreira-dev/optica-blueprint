# Entregas

## ¿Para qué sirve este módulo?

El módulo **Entregas** gestiona la entrega de trabajos terminados al paciente. Cuando los lentes están fabricados y montados en la montura, este módulo se encarga de validar que todo esté correcto, registrar la entrega al paciente y dejar constancia del acto de entrega.

## ¿Quién usa esta pantalla?

- **Recepcionistas:** para entregar los trabajos al paciente y registrar la entrega.
- **Asesores de venta:** para validar que el producto entregado coincide con lo vendido.
- **Administradores:** para revisar entregas pendientes, atrasadas y el histórico.

---

## Sub-vistas del módulo

El módulo tiene 6 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Trabajos listos** | Órdenes terminadas esperando ser retiradas por el paciente. |
| **Validación pre-entrega** | Verificación de que todo esté correcto antes de entregar. |
| **Registro de retiro** | Formulario para registrar la entrega al paciente. |
| **Pendientes de retiro** | Órdenes listas que el paciente aún no ha venido a buscar. |
| **Ajustes post-entrega** | Reclamos o ajustes después de la entrega. |
| **Histórico de entregas** | Todas las entregas realizadas, con fecha y datos. |

---

## Trabajos listos

### Órdenes listas para entregar

Cuando el laboratorio termina una orden, esta aparece en la lista de trabajos listos:

| Columna | Descripción |
|---------|-------------|
| **Número de orden** | Referencia de la venta (ej: OV-00045) |
| **Cliente** | Nombre del paciente |
| **Teléfono** | Para contactarlo |
| **Descripción** | Montura + tipo de lentes + tratamientos |
| **Fecha de completado** | Cuándo el laboratorio terminó |
| **Días en espera** | Cuántos días lleva sin retirar |

### Qué hacer cuando el paciente viene a recoger
1. Busque la orden por número, nombre o teléfono
2. Seleccione la orden
3. Vaya a **Validación pre-entrega** para verificar
4. Registre la entrega en **Registro de retiro**

---

## Validación pre-entrega

### Verificación antes de entregar

Antes de entregar los lentes al paciente, verifique que todo esté correcto:

#### Checklist de validación
| Verificación | Qué revisar |
|--------------|-------------|
| **Receta correcta** | Los valores OD y OI coinciden con la receta del paciente |
| **Montura correcta** | Es la misma marca y modelo que el paciente eligió |
| **Lentes sin defectos** | No hay rayones, burbujas o imperfecciones |
| **Centro óptico** | Los lentes están bien centrados en la montura |
| **Ajuste de montura** | La montura está bien ajustada y cómoda |
| **Accesorios incluidos** | Estuche, paño de limpieza, solución si aplica |

### Si algo no pasa la validación
- **No entregue el producto al paciente**
- Regrese la orden al laboratorio con una observación
- El módulo **Órdenes de Laboratorio** registra la incidencia
- Se inicia un proceso de reelaboración si es necesario

---

## Registro de retiro

### Formulario de entrega al paciente

Cuando todo está validado, registre la entrega:

#### Datos de la entrega
| Campo | Descripción |
|-------|-------------|
| **Orden** | Número de referencia |
| **Cliente** | Quién recibe (puede ser el paciente o un tercero autorizado) |
| **Fecha de entrega** | Automática (hoy) |
| **Entregado por** | Usuario del sistema que hace la entrega |
| **Observaciones** | Notas especiales (ej: "Se le indicó cómo limpiar los lentes") |

#### Confirmación de entrega
1. Verifique la identidad del paciente (documento de identidad)
2. Entregue los lentes con estuche y accesorios
3. Explique al paciente:
   - Cómo limpiar los lentes correctamente
   - Cómo ajustar la montura si es necesario
   - Período de adaptación si son progresivos
   - Garantía del producto
4. Registre la entrega en el sistema
5. El estado de la orden cambia a **Entregada**

> **Importante:** Si entrega a un tercero (familiar del paciente), registre el nombre y documento de quien recibe.

---

## Pendientes de retiro

### Órdenes que el paciente no ha venido a buscar

Muestra las órdenes listas que aún no han sido retiradas:

| Columna | Descripción |
|---------|-------------|
| **Orden** | Número de referencia |
| **Cliente** | Nombre y teléfono |
| **Fecha de completado** | Cuándo se terminó |
| **Días pendiente** | Cuántos días lleva sin retirar |
| **Notificación enviada** | Si se le notificó al paciente |

### Cuándo contactar al paciente
- **3 días sin retirar:** Primer recordatorio (llamada o mensaje)
- **7 días sin retirar:** Segundo recordatorio
- **15 días sin retirar:** Notificación de que el pedido está en espera
- **30 días sin retirar:** Escalar al administrador

> **Tip:** Use el módulo **Seguimiento** para programar estos recordatorios automáticamente.

---

## Ajustes post-entrega

### Reclamos y ajustes después de la entrega

Si el paciente regresa después de recibir sus lentes con alguna queja:

#### Tipos de ajustes post-entrega
| Tipo | Descripción |
|------|-------------|
| **Ajuste de montura** | La montura le queda apretada, floja o desnivelada |
| **Molestia visual** | El paciente refiere que ve borroso o con distorsión |
| **Defecto de fabricación** | Rayón, burbuja, descentrado no detectado en la validación |
| **Adaptación a progresivos** | El paciente no se adapta a los lentes progresivos |

### Cómo registrar un ajuste post-entrega
1. Vaya a **Ajustes post-entrega**
2. Busque la orden del paciente
3. Seleccione el tipo de ajuste
4. Describa la situación
5. Indique la acción a tomar:
   - **Ajuste en tienda:** Se ajusta la montura aquí mismo
   - **Reelaboración:** Los lentes deben fabricarse de nuevo
   - **Cambio de receta:** El paciente necesita un nuevo examen
   - **Adaptación:** Se le da tiempo al paciente para adaptarse

---

## Histórico de entregas

### Registro completo de todas las entregas

Muestra todas las entregas realizadas con sus detalles:

| Columna | Descripción |
|---------|-------------|
| **Fecha de entrega** | Cuándo se entregó |
| **Orden** | Número de referencia |
| **Cliente** | Quién recibió |
| **Entregado por** | Usuario que hizo la entrega |
| **Productos** | Qué se entregó |
| **Observaciones** | Notas de la entrega |

### Para qué sirve el histórico
- **Auditoría:** Verificar que cada orden fue entregada correctamente.
- **Garantía:** Si un paciente reclama garantía, verificar cuándo recibió.
- **Estadísticas:** Tiempo promedio entre completado y entrega.

---

## Casos de uso comunes

### "Un paciente viene a recoger sus lentes"
1. Vaya a **Trabajos listos**
2. Busque al paciente por nombre o número de orden
3. Vaya a **Validación pre-entrega** y revise el checklist
4. Si todo está correcto, vaya a **Registro de retiro**
5. Confirme la entrega y entregue los lentes al paciente

### "Los lentes no pasan la validación pre-entrega"
1. Identifique el problema (ej: "Centro óptico descentrado 2mm")
2. No entregue al paciente
3. Regrese la orden al laboratorio con una observación
4. Informe al paciente que habrá un retraso
5. El laboratorio inicia la reelaboración

### "Un paciente no ha venido a buscar sus lentes hace 10 días"
1. Vaya a **Pendientes de retiro**
2. Localice la orden del paciente
3. Llame al paciente para recordarle que sus lentes están listos
4. Registre la llamada en las observaciones

### "El paciente regresó porque la montura le aprieta"
1. Vaya a **Ajustes post-entrega**
2. Busque la orden del paciente
3. Seleccione **Ajuste de montura**
4. Realice el ajuste o envíelo al taller
5. Registre la acción tomada

### "El paciente dice que ve borroso con los lentes nuevos"
1. Vaya a **Ajustes post-entrega**
2. Busque la orden
3. Seleccione **Molestia visual**
4. Verifique la receta contra los lentes fabricados
5. Si hay error de fabricación: **Reelaboración**
6. Si la receta está bien: ofrezca un nuevo examen visual

---

## Estados de entrega

| Estado | Color | Qué significa |
|--------|-------|---------------|
| **Lista para entrega** | 🟢 Verde | El laboratorio terminó, esperando al paciente |
| **Entregada** | ⚫ Gris | Ya fue entregada al paciente |
| **Con ajuste post-entrega** | 🟡 Ámbar | Tiene un reclamo o ajuste pendiente |
| **Reelaboración** | 🔴 Rojo | Debe fabricarse de nuevo |

---

## Notas importantes
- La entrega solo puede registrarse si la orden está en estado **Lista para entrega**.
- Siempre valide los lentes antes de entregarlos; un error detectado antes de la entrega es más barato de corregir.
- El período de adaptación para lentes progresivos es de 7 a 15 días; informe al paciente.
- Las entregas a terceros deben quedar registradas con nombre y documento del receptor.
- El módulo **Notificaciones** envía automáticamente un mensaje al paciente cuando su orden está lista.

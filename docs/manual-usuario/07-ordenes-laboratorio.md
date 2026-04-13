# Órdenes de Laboratorio

## ¿Para qué sirve este módulo?

El módulo **Órdenes de Laboratorio** es donde usted da seguimiento a la fabricación de los lentes. Desde que se genera una orden de venta hasta que los lentes están montados en la montura y listos para entrega, este módulo le permite ver en qué etapa del proceso está cada orden.

## ¿Quién usa esta pantalla?

- **Técnicos de laboratorio:** para ver qué órdenes deben fabricar y en qué etapa están.
- **Administradores:** para monitorear el rendimiento del laboratorio y los tiempos de entrega.
- **Asesores de venta:** para consultar el estado de las órdenes de sus pacientes.
- **Recepcionistas:** para informar al paciente cuándo estarán listos sus lentes.

---

## Sub-vistas del módulo

El módulo tiene 6 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Cola de órdenes** | Todas las órdenes pendientes de fabricación, ordenadas por fecha. |
| **Detalle de orden** | Información completa de una orden específica (receta, montura, lentes). |
| **Seguimiento de etapas** | Visualización de las etapas del proceso de fabricación por orden. |
| **Envío y recepción** | Órdenes enviadas a laboratorio externo y las recibidas. |
| **Incidencias y reelaboraciones** | Problemas detectados durante la fabricación y órdenes que se deben rehacer. |
| **Histórico** | Órdenes ya completadas y entregadas. |

---

## Cola de órdenes

### Vista principal del laboratorio
La cola muestra todas las órdenes que están en proceso de fabricación, ordenadas de la más antigua a la más reciente.

### Columnas de la tabla
| Columna | Descripción |
|---------|-------------|
| **Número de orden** | Referencia (ej: LAB-00023) |
| **Cliente** | Nombre del paciente |
| **Tipo de trabajo** | Montaje de lentes, Solo lentes, Reparación |
| **Prioridad** | Normal, Urgente |
| **Etapa actual** | En qué parte del proceso está |
| **Fecha de ingreso** | Cuándo entró al laboratorio |
| **Fecha estimada** | Cuándo se estima estará lista |
| **Estado** | Pendiente, En proceso, En espera, Completada |

### Qué puede hacer aquí
- **Cambiar la etapa:** Cuando un técnico completa una etapa, la actualiza aquí.
- **Marcar como urgente:** Si un paciente necesita sus lentes con urgencia.
- **Asignar técnico:** Indicar quién se encargará de la orden.

---

## Etapas de fabricación

Cada orden de laboratorio pasa por las siguientes etapas:

| Etapa | Qué se hace | Quién lo hace |
|-------|-------------|---------------|
| **1. Recepción** | La orden ingresa al laboratorio. Se verifica que la receta sea legible y completa. | Recepcionista de laboratorio |
| **2. Edging/Corte** | El lente en blanco se corta al tamaño y forma de la montura seleccionada. | Técnico de laboratorio |
| **3. Montaje** | Los lentes cortados se instalan en la montura del paciente. | Técnico de laboratorio |
| **4. Control de calidad** | Se verifica que los lentes estén bien centrados, sin rayones y con la graduación correcta. | Supervisor de calidad |
| **5. Listo para entrega** | La orden pasa al área de entregas notificando al paciente. | Recepcionista |

> **Tip:** Si una orden se detiene en una etapa por mucho tiempo, puede haber un problema (falta de insumos, reelaboración pendiente, etc.).

---

## Detalle de orden

Al seleccionar una orden de la cola, el panel derecho muestra toda la información:

### Datos de la orden
- **Número de orden** y fecha de ingreso
- **Cliente** asociado
- **Venta** de origen (número de orden de venta)

### Receta aplicada
- Valores OD y OI (Esfera, Cilindro, Eje, Adición)
- Distancia pupilar
- Altura de centro óptico

### Productos
- **Montura:** Marca, modelo, color
- **Lente:** Tipo (monofocal, progresivo), material (CR-39, policarbonato)
- **Tratamientos:** Antirreflejo, filtro UV, blue-cut, etc.

### Seguimiento
- **Etapa actual** y fecha de cambio
- **Técnico** asignado
- **Observaciones** del laboratorio

---

## Envío y recepción

### Órdenes enviadas a laboratorio externo

Si su óptica envía lentes a un laboratorio externo:
1. Seleccione las órdenes que deben enviarse
2. Haga clic en **Enviar a laboratorio**
3. Seleccione el laboratorio externo (proveedor)
4. La orden cambia de estado a "Enviada"

### Recepción de órdenes externas
Cuando el laboratorio externo devuelve los lentes:
1. Vaya a la vista **Envío y recepción**
2. Identifique la orden recibida
3. Haga clic en **Recibir**
4. Verifique que los lentes coincidan con la receta
5. Si hay discrepancias, registre una incidencia

---

## Incidencias y reelaboraciones

### Qué es una incidencia
Un problema detectado durante la fabricación:
- Graduación incorrecta
- Lente rayado o dañado
- Montura dañada durante el montaje
- Centro óptico mal calculado

### Cómo registrar una incidencia
1. Vaya a la vista **Incidencias y reelaboraciones**
2. Haga clic en **Nueva incidencia**
3. Seleccione la orden afectada
4. Describa el problema
5. Indique la acción: **Reelaborar** (fabricar de nuevo) o **Observar** (continuar con nota)

### Reelaboración
Si se decide reelaborar:
1. La orden vuelve a la etapa de **Edging/Corte**
2. Se consume un nuevo lente en blanco (se registra en inventario)
3. Se prioriza para que el paciente no espere más de lo necesario

---

## Histórico

Muestra todas las órdenes de laboratorio ya completadas, con sus datos de fabricación.

### Para qué sirve el histórico
- **Consultar tiempos de producción:** ¿Cuánto tardó cada orden?
- **Identificar problemas recurrentes:** ¿Qué incidencias se repiten?
- **Evaluar rendimiento del laboratorio:** Órdenes completadas vs. incidencias

### Columnas del histórico
| Columna | Descripción |
|---------|-------------|
| **Número de orden** | Referencia de la orden |
| **Cliente** | Nombre del paciente |
| **Fecha de ingreso** | Cuándo entró al laboratorio |
| **Fecha de completado** | Cuándo se terminó |
| **Tiempo total** | Días/horas que tomó fabricar |
| **Incidencias** | Si las hubo |

---

## Casos de uso comunes

### "Quiero ver qué órdenes están pendientes hoy"
1. Vaya a la **Cola de órdenes**
2. La tabla muestra todas las órdenes pendientes
3. Filtre por etapa si quiere ver solo las que están en una etapa específica

### "Un técnico terminó el corte de una orden"
1. Seleccione la orden en la cola
2. Cambie la etapa de **Edging/Corte** a **Montaje**
3. La orden avanza en el proceso

### "Un paciente llama preguntando por sus lentes"
1. Busque al paciente en la cola por nombre o número de orden
2. Consulte la **Etapa actual** para darle una estimación
3. Si está en **Listo para entrega**, indíquele que puede pasar a recoger

### "Detecté que un lente salió con la graduación equivocada"
1. Vaya a **Incidencias y reelaboraciones**
2. Registre la incidencia con la orden afectada
3. Describa el error (ej: "Esfera OD -2.00 en vez de -2.50")
4. Marque como **Reelaborar**
5. La orden vuelve a la etapa de corte con un nuevo lente

### "Necesito enviar 5 órdenes al laboratorio externo"
1. Seleccione las 5 órdenes en la cola (marcando cada una)
2. Haga clic en **Enviar a laboratorio**
3. Seleccione el proveedor de laboratorio externo
4. Confirme el envío

---

## Estados de las órdenes

| Estado | Color | Qué significa |
|--------|-------|---------------|
| **Pendiente** | 🟡 Ámbar | La orden ingresó pero aún no inicia fabricación |
| **En proceso** | 🔵 Azul | Se está fabricando activamente |
| **En espera** | 🟡 Ámbar | Detenida por algún motivo (falta insumo, incidencia) |
| **Enviada** | 🟣 Morado | Enviada a laboratorio externo |
| **Recibida** | 🟢 Verde | Vuelta del laboratorio externo, en verificación |
| **Completada** | 🟢 Verde | Lista para entrega al paciente |

---

## Notas importantes
- Las órdenes se generan automáticamente al confirmar una venta en el módulo **Venta Óptica**.
- El tiempo estándar de fabricación es de 3 a 5 días hábiles (depende del tipo de lente).
- Los lentes progresivos y de alta graduación pueden tomar más tiempo.
- Si una orden tiene incidencia, el tiempo de entrega se extiende automáticamente.
- El módulo **Notificaciones** alerta al paciente cuando su orden pasa a **Listo para entrega**.

# Recetas

## ¿Para qué sirve este módulo?

El módulo **Recetas** es donde usted consulta y gestiona las recetas oftalmológicas (exámenes visuales) de sus pacientes. Aquí puede ver la prescripción actual de lentes, el historial de exámenes anteriores, las medidas oftalmológicas del paciente y las recomendaciones que hizo el profesional.

## ¿Quién usa esta pantalla?

- **Optometristas:** para revisar recetas anteriores antes de un nuevo examen y consultar el historial visual del paciente.
- **Asesores de venta:** para verificar la receta vigente al preparar una venta de lentes.
- **Laboratorio:** para consultar los valores de la receta al fabricar los lentes.

---

## Sub-vistas del módulo de Recetas

El módulo tiene 5 sub-vistas accesibles desde los botones superiores:

| Sub-vista | Qué muestra |
|-----------|-------------|
| **Receta actual** | La prescripción más reciente del paciente seleccionado. |
| **Historial** | Todas las recetas anteriores del paciente, ordenadas por fecha. |
| **Medidas y parámetros** | Datos oftalmológicos adicionales: distancia pupilar, altura de montaje, etc. |
| **Recomendaciones** | Notas y sugerencias del profesional para el paciente. |
| **Vínculo con orden** | Relación entre la receta y la orden óptica de venta/laboratorio. |

---

## Receta actual

### Selección del paciente

Primero debe seleccionar un paciente usando el campo de búsqueda:
1. Escriba el nombre, código o teléfono del paciente
2. Seleccione al paciente de la lista de resultados
3. La receta más reciente se cargará automáticamente

### Información que muestra la receta actual

#### Datos del ojo derecho (OD)

| Campo | Qué significa |
|-------|---------------|
| **Esfera (ESF)** | Poder dióptrico del lente. Valores negativos = miopía. Positivos = hipermetropía. |
| **Cilindro (CIL)** | Corrección del astigmatismo. Si está vacío, no hay astigmatismo. |
| **Eje** | Orientación del cilindro (0° a 180°). Solo aplica si hay cilindro. |
| **Adición (ADD)** | Poder adicional para lentes progresivos/bifocales (presbicia). |
| **Prisma** | Corrección de estrabismo o problemas de alineación ocular. |
| **Dirección del prisma** | Hacia donde apunta el prisma (Base arriba, abajo, dentro, fuera). |

#### Datos del ojo izquierdo (OI)

Los mismos campos que el OD, pero para el ojo izquierdo.

> **Nota para el optometrista:** La presentación es simétrica (OD a la izquierda, OI a la derecha) para facilitar la lectura rápida.

#### Datos adicionales

| Campo | Qué significa |
|-------|---------------|
| **Distancia pupilar (DP)** | Distancia entre centros de pupilas en milímetros. Fundamental para el centrado de lentes. |
| **Altura de montaje** | Altura del centro óptico respecto al borde inferior del marco. |
| **Observaciones** | Notas libres del profesional sobre condiciones especiales. |
| **Fecha del examen** | Cuándo se realizó la receta. |
| **Profesional** | Quién realizó el examen. |

---

## Historial de recetas

Muestra todas las recetas anteriores del paciente en orden cronológico (de la más reciente a la más antigua).

### Para qué sirve el historial
- **Comparar evolución:** Ver cómo ha cambiado la prescripción del paciente con el tiempo.
- **Identificar tendencias:** Detectar si la miopía progresa, si el astigmatismo cambia, etc.
- **Validar la receta actual:** Si un valor parece fuera de lo normal, el historial ayuda a verificar.

### Columnas de la tabla de historial
| Columna | Descripción |
|---------|-------------|
| **Fecha** | Cuándo se realizó el examen |
| **Profesional** | Quién hizo el examen |
| **OD (Esfera)** | Valor de esfera del ojo derecho |
| **OI (Esfera)** | Valor de esfera del ojo izquierdo |
| **DP** | Distancia pupilar |
| **Estado** | Vigente / Por vencer / Vencida |

---

## Medidas y parámetros

Esta vista muestra datos oftalmológicos adicionales que complementan la receta:

| Medida | Descripción | Para qué sirve |
|--------|-------------|----------------|
| **Distancia pupilar lejana** | DP mirando al infinito | Para lentes de lejos |
| **Distancia pupilar cercana** | DP mirando un punto cercano | Para lentes de lectura/progresivos |
| **Altura de centro óptico** | Altura del centro óptico respecto al marco | Para el montaje correcto de lentes |
| **Distancia vértice** | Distancia entre el ojo y la superficie posterior del lente | Para prescripciones altas |
| **Ángulo pantoscópico** | Inclinación del marco respecto al plano vertical | Para cálculos de compensación |

> **Tip:** Estas medidas son fundamentales para que el laboratorio centre correctamente los lentes en la montura elegida.

---

## Recomendaciones

Aquí el profesional puede dejar anotaciones para el paciente o el equipo:

**Ejemplos de recomendaciones:**
- "Paciente refiere molestias con lentes progresivos. Considerar lentes separados para lejos y cerca."
- "Miopía en progresión. Recomendar control en 6 meses."
- "Paciente con sensibilidad a luz azul. Ofrecer filtro blue-cut."
- "Adaptación progresiva requerida para nuevo diseño de progresivo."

### Para qué sirven las recomendaciones
- **Para el optometrista:** Recordar indicaciones especiales para la próxima visita.
- **Para el asesor de venta:** Saber qué tipo de lentes ofrecer al paciente.
- **Para el laboratorio:** Tener en cuenta consideraciones especiales al fabricar.

---

## Vínculo con orden óptica

Esta vista conecta la receta con la orden de venta o laboratorio asociada.

### Qué muestra
- **Número de orden:** La orden de venta generada a partir de esta receta
- **Estado de la orden:** Pendiente / En laboratorio / Lista / Entregada
- **Montura seleccionada:** Modelo de marco elegido por el paciente
- **Tipo de lente:** Monofocal, bifocal, progresivo, etc.
- **Tratamientos:** Antirreflejo, filtro UV, fotocromático, etc.

### Para qué sirve este vínculo
Cuando un paciente va al área de ventas, el asesor puede:
1. Ver la receta vigente del paciente
2. Generar una orden de venta basada en esa receta
3. Dar seguimiento al estado de fabricación desde el módulo de Órdenes de Laboratorio

---

## Casos de uso comunes

### "Voy a atender a un paciente y quiero ver su última receta"
1. Vaya a **Recetas**
2. Busque al paciente por nombre
3. La **Receta actual** se cargará automáticamente
4. Revise los valores OD y OI antes del examen

### "Quiero comparar la receta actual con la anterior"
1. Seleccione al paciente
2. Vaya a la sub-vista **Historial**
3. Compare la receta actual con la entrada anterior

### "El paciente pregunta cuánto ha cambiado su vista"
1. Abra el **Historial** del paciente
2. Compare los valores de esfera entre la receta más reciente y la anterior
3. Puede mostrarle la tendencia visualmente

### "Necesito los datos de la receta para enviar al laboratorio"
1. Abra la **Receta actual** del paciente
2. Copie los valores OD y OI (Esfera, Cilindro, Eje, Adición)
3. Tome nota de la Distancia Pupilar
4. Estos valores van en la orden de laboratorio

### "La receta del paciente está vencida"
1. Si el **Estado** muestra "Vencida", el paciente necesita un nuevo examen visual
2. Ofrezca agendar una cita de examen visual desde el módulo **Agenda**
3. No venda lentes con receta vencida; es un riesgo para la salud visual del paciente

---

## Estados de receta y su significado

| Estado | Color | Cuándo aplica |
|--------|-------|---------------|
| **Vigente** | 🟢 Verde | La receta tiene menos de 6 meses |
| **Por vencer** | 🟡 Ámbar | La receta tiene entre 6 y 12 meses |
| **Vencida** | 🔴 Rojo | La receta tiene más de 12 meses |

> **Recomendación profesional:** Una receta vencida no debe usarse para fabricar lentes nuevos. El paciente necesita un examen visual actualizado.

---

## Notas importantes
- La receta es un documento clínico; su manejo debe ser confidencial.
- Los datos de la receta no pueden ser editados directamente desde este módulo; para correcciones, el profesional debe generar una nueva receta.
- Cada sucursal puede ver las recetas de pacientes de otras sucursales.
- El sistema alerta automáticamente cuando una receta está próxima a vencer.

# Proyecto-IS

---

## Sprints

| Sprint | Link |
|--------|------|
| Sprint 1 | [Ver commit](https://github.com/MagiaGatuna/Proyecto-IS/tree/132924707575e91f80ab7dae1cbdc76427db8eab) |
| Sprint 2 | [Ver commit](https://github.com/MagiaGatuna/Proyecto-IS/tree/b3ef212b842d107bbb3d4c9b8dae2f4bafe9946f) |

---

## Integrantes

|   |
|--------|
| Valentina Almeida |
| Alexandra Amselmi |
| Corina Matheus |
| Andrés Ortiz |

---

## Librerías Adicionales

### `java.time`

**Imports utilizados:**
- `java.time.LocalDate`
- `java.time.LocalTime`
- `java.time.DayOfWeek`

La librería ha sido utilizada en el util llamado calcular_dia.
Principalmente fue añadida al proyecto con la finalidad de 
poder obtener el dia de la semana y hora actual que posee el
dispositivo desde donde se ejecute el programa, de forma que
se pueda tener mayor control de los menús presentados, tanto el
menú diario como el menú de cada uno de los otros días de la 
semana; en cuanto a la hora, nos permite llevar un control más 
preciso al momento de ver los menús que el usuario va a poder
reservar(funcionalidad que será añadida en proximos sprints por
el equipo de desarrollo), ya que en caso de que haya pasado ya el
horario donde se puede reservar, entonces se bloqueara el boton de
reserva debido a la hora.

<details>
<summary>Ver métodos</summary>

| Método | Descripción |
|--------|-------------|
| `getdia()` | Utiliza `LocalDate.getDayOfWeek().toString` para extraer el dia de la semana en ingles, por ejemplo `"MONDAY"`. |
| `gethora()` | Utiliza `getHour()` y `getMinute()`, bajo la formula de `(hora*60)+minutos` con la finalidad de obtener la hora actual de la maquina en minutos, de forma que se tenga un control del tiempo en un formato uniforme y facil de utilizar por los controladores. |
| `getDiaMesNumero()` | Utiliza `LocalDate.getDayOfMonth()` para recibir un entero. |
| `getMes()` | Utiliza `LocalDate.getMoth().ToString()` para obtener el mes actual en ingles y luego se hace uso de esto para traducir la fecha actual al hispanohablente. |
| `getAnio()` | Utiliza `LocalDate.getYear()` para recibir un entero. |

</details>

---

### `org.json.*`

Integrado en VScode en la seccion Java Projects y en el apartado Referenced Libraries. 

**Imports utilizados:**
- `org.json.JSONArray`
- `org.json.JSONException`
- `org.json.JSONObject`

Con la finalidad de preservar la siguiente informacion en archivos `.json`:

<details>
<summary>Ver estructura de archivos JSON</summary>

**`baseDeDatosUCV.json`** : Simular informacion recibida por la UCV para poder registrar como administradores solamente usuarios permitidos. Utiliza la siguiente estructura:
```json
[
    {
        "cedula": "1234",
        "rol": "administrador"
    }
]
```

**`costosFijos.json`** : Guardar los CF agregados por los admininistradores. Utiliza la siguiente estructura:
```json
[
    {
        "costo": 100,
        "nombre": "servicios"
    }
]
```

**`costosVariables.json`** : Guardar los CV agregados por los admininistradores. Organizandolos por cada uno de los menus existentes. Utiliza la siguiente estructura:
```json
[
    {
        "detalles_costos": {"vegetales": 25},
        "dia_turno": "MONDAY_DESAYUNO"
    },
    {
        "detalles_costos": {},
        "dia_turno": "MONDAY_ALMUERZO"
    }
]
```

**`menus.json`** : Guardar informacion de los menus actuales en el sistema. Utiliza la siguiente estructura:
```json
[
    {
        "dia": "MONDAY",
        "turno": "DESAYUNO",
        "comida": "Arepa con huevo frito",
        "descripcion": "Deliciosa arepa dorada rellena con huevo frito recién hecho.",
        "valorNutricional": "349 Kcal",
        "precio": 764,
        "dia_turno": "MONDAY_DESAYUNO",
        "reservas_actual": 54,
        "aforo_max": 500
    }
]
```

**`usuarios.json`** : Guardar informacion de los usuarios registrados en el sistema que permite el funcionamoento correcto del iniciar sesion. Utiliza la siguiente estructura:
```json
[
    {
        "apellidos": "Matheus",
        "cedula": "123",
        "sexo": "femenino",
        "rol": "Estudiante",
        "nombres": "Corina",
        "contraseña": "123",
        "saldo" : 999999.99
    }
]
```
**`reservas.json`** : Guardar todas las reservas actuales del sistema
```json
[
    {
        "cedula": "12345678",
        "dia_turno": "MONDAY_ALMUERZO",
        "fecha_exacta": "2026-03-16"
    },
    {
        "cedula": "123",
        "dia_turno": "FRIDAY_DESAYUNO",
        "fecha_exacta": "2026-03-13"
    }
]
```

**`Merma.json`** : Guardar la merma actual de trodos los menu
```json
[
    {
        "merma": 20,
        "dia_turno": "MONDAY_DESAYUNO"
    },
    {
        "merma": 0,
        "dia_turno": "MONDAY_ALMUERZO"
    },
    {
        "merma": 20,
        "dia_turno": "MONDAY_CENA"
    },
    {
        "merma": 50,
        "dia_turno": "FRIDAY_CENA"
    },
    {
        "merma": 0,
        "dia_turno": "TUESDAY_ALMUERZO"
    },
    {
        "merma": 0,
        "dia_turno": "WEDNESDAY_DESAYUNO"
    },
    {
        "merma": 0,
        "dia_turno": "THURSDAY_DESAYUNO"
    },
    {
        "merma": 0,
        "dia_turno": "THURSDAY_ALMUERZO"
    },
    {
        "merma": 0,
        "dia_turno": "TUESDAY_DESAYUNO"
    },
    {
        "merma": 0,
        "dia_turno": "WEDNESDAY_ALMUERZO"
    },
    {
    "merma": 0,
    "dia_turno": "FRIDAY_DESAYUNO"
    },
    {
        "merma": 0,
        "dia_turno": "FRIDAY_ALMUERZO"
    }
]
```
**`consumos.json`** : Guardar informacion de los consumos realizados por los comensales organizados por fecha y turno
```json

{
  "almuerzo": {
    "2026-03-10": {
      "resumen": {
        "total": 1,
        "empleado": 0,
        "exonerado": 0,
        "becario": 1,
        "profesor": 0,
        "regular": 0
      },
      "asistentes": [{
        "tipo": "becario",
        "cedula": "123",
        "rol": "Estudiante"
      }]
    },
    "2026-03-09": {
      "resumen": {
        "total": 2,
        "empleado": 1,
        "exonerado": 0,
        "becario": 0,
        "profesor": 0,
        "regular": 1
      },
      "asistentes": [
        {
          "tipo": "regular",
          "cedula": "15151515",
          "rol": "estudiante"
        },
        {
          "cedula": "12548736",
          "rol": "empleado"
        }
      ]
    },
    "2026-03-07": {
      "resumen": {
        "total": 2,
        "empleado": 0,
        "exonerado": 0,
        "becario": 0,
        "profesor": 1,
        "regular": 1
      },
      "asistentes": [
        {
          "tipo": "regular",
          "cedula": "123",
          "rol": "estudiante"
        },
        {
          "cedula": "22222222",
          "rol": "profesor"
        }
      ]
    },
    "2026-03-08": {
      "resumen": {
        "total": 4,
        "empleado": 1,
        "exonerado": 1,
        "becario": 1,
        "profesor": 1,
        "regular": 0
      },
      "asistentes": [
        {
          "tipo": "exonerado",
          "cedula": "123",
          "rol": "estudiante"
        },
        {
          "cedula": "22222222",
          "rol": "profesor"
        },
        {
          "tipo": "becario",
          "cedula": "15151515",
          "rol": "estudiante"
        },
        {
          "cedula": "12548736",
          "rol": "empleado"
        }
      ]
    }
  },
  "desayuno": {
    "2026-03-09": {
      "resumen": {
        "total": 3,
        "empleado": 1,
        "exonerado": 0,
        "becario": 0,
        "profesor": 1,
        "regular": 1
      },
      "asistentes": [
        {
          "tipo": "regular",
          "cedula": "123",
          "rol": "estudiante"
        },
        {
          "cedula": "12548736",
          "rol": "empleado"
        },
        {
          "cedula": "22222222",
          "rol": "profesor"
        }
      ]
    },
    "2026-03-07": {
      "resumen": {
        "total": 4,
        "empleado": 1,
        "exonerado": 1,
        "becario": 0,
        "profesor": 1,
        "regular": 1
      },
      "asistentes": [
        {
          "tipo": "exonerado",
          "cedula": "15151515",
          "rol": "estudiante"
        },
        {
          "cedula": "12548736",
          "rol": "empleado"
        },
        {
          "cedula": "22222222",
          "rol": "profesor"
        },
        {
          "tipo": "regular",
          "cedula": "123",
          "rol": "estudiante"
        }
      ]
    },
    "2026-03-08": {
      "resumen": {
        "total": 3,
        "empleado": 0,
        "exonerado": 0,
        "becario": 1,
        "profesor": 1,
        "regular": 1
      },
      "asistentes": [
        {
          "tipo": "becario",
          "cedula": "123",
          "rol": "estudiante"
        },
        {
          "tipo": "regular",
          "cedula": "15151515",
          "rol": "estudiante"
        },
        {
          "cedula": "22222222",
          "rol": "profesor"
        }
      ]
    }
  }
}
```

</details>

---

### `java.nio`

**Imports utilizados:**
- `java.nio.charset.StandardCharsets`
- `java.nio.file.Files`
- `java.nio.file.Path`
- `java.nio.file.Path`

La librería ha sido utilizada en las siguientes clases de modelo:
- `editarCostos.java`
- `Menus_lista.java`
- `validadorInicioS.java`
- `validadorRegistro.java`

con la finalidad de gestionar la lectura y escritura de los archivos JSON

---

### `java.awt.image`

**Imports utilizados:**
- `java.awt.image.BufferedImage`: representa la imagen en cuestion en memoria

La librería ha sido utilizada en las siguientes clases de Util:
- `ReconocimientoFacialUtil.java`

con la finalidad de representar las imagenes correspondientes del ReconocimientoFacial

---

### `javax.imageio`

**Imports utilizados:**
- `javax.imageio.ImageIO`: lectura de imágenes desde archivo (read(File)).

La librería ha sido utilizada en las siguientes clases de Util:
- `ReconocimientoFacialUtil.java`

con la finalidad de leer y cargar las imagenes del ReconocimientoFacial







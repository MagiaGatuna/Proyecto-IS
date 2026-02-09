# Proyecto-IS
## Integrantes: 
-  Valentina Almeida
-  Alexandra Amselmi
-  Corina Matheus
-  Andrés Ortiz

## Librerías Adicionales:
* "java.time" de la cual se importaron los siguientes:
    - java.time.LocalDate
    - java.time.LocalTime
    - java.time.DayOfWeek

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

    Metodos donde se hace uso:  getdia()  } utiliza "LocalDate.getDayOfWeek().toString" 
                                para extraer el dia de la semana en ingles, por ejemplo "MONDAY".
                                gethora() } utiliza getHour() y getMinute(), bajo la formula de
                                (hora*60)+minutos con la finalidad de obtener la hora actual de
                                la maquina en minutos, de forma que se tenga un control del tiempo
                                en un formato uniforme y facil de utilizar por los controladores.
                                getDiaMesNumero() } utiliza LocalDate.getDayOfMonth() para recibir 
                                un entero.
                                getMes() } utiliza LocalDate.getMoth().ToString() para obtener el 
                                mes actual en ingles y luego se hace uso de esto para traducir
                                la fecha actual al hispanohablente.
                                getAnio() } utiliza LocalDate.getYear() para recibir un entero.

* "org.json.*"  integrado en VScode en la seccion Java Projects y en el apartado Referenced Libraries. De esta libreria se importaron los siguiente:
    - org.json.JSONArray
    - org.json.JSONException
    - org.json.JSONObject
    La librería ha sido utilizada en las siguientes clases de modelo:
        - editarCostos.java
        - Menus_lista.java
        - validadorInicioS.java
        - validadorRegistro.java

    Con la finalidad de preservar la siguiente afirmacion en archivos .json:
    - baseDeDatosUCV.json : Simular informacion recibida por la UCV para poder registrar como administradores solamente usuarios permitidos. Utiliza la siguiente estructura:
        ```json
            [
                {
                    "cedula": "1234",
                    "rol": "administrador"
                }
            ]
        ```    

    - costosFijos.json : Guardar los CF agregados por los admininistradores. Utiliza la siguiente estructura:
        ```json
            [
                {
                    "costo": 100,
                    "nombre": "servicios"
                 }
            ] 
        ``` 

    - costosVariables.json : Guardar los CV agregados por los admininistradores. Organizandolos por cada uno de los menus existentes. Utiliza la siguiente estructura:
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

    - menus.json : Guardar informacion de los menus actuales en el sistema. Utiliza la siguiente estructura:
        ```json
        [
            {
                "dia":"MONDAY",
                "turno":"DESAYUNO",
                "comida":"Arepa con huevo frito",
                "valorNutricional":"349 Kcal",
                "precio":764,
                "dia_turno":"MONDAY_DESAYUNO",
                "reservas_actual":54,
                "aforo_max":500
            }
        ]
        ```

    - usuarios.json :  Guardar informacion de los usuarios registrados en el sistema que permite el funcionamoento correcto del iniciar sesion. Utiliza la siguiente estructura:
        ```json
            [
                {
                    "apellidos": "Matheus",
                    "cedula": "123",
                    "sexo": "femenino",
                    "rol": "Estudiante",
                    "nombres": "Corina",
                    "contraseña": "123"
                }
            ]
        ```     

* "java.nio" de la cual se importaron los siguientes:
    - java.nio.charset.StandardCharsets
    - java.nio.file.Files
    - java.nio.file.Path
    - java.nio.file.Path

    La librería ha sido utilizada en las siguientes clases de modelo:
        - editarCostos.java
        - Menus_lista.java
        - validadorInicioS.java
        - validadorRegistro.java
    con la finalidad de gestionar la lectura y escritura de los archivos JSON




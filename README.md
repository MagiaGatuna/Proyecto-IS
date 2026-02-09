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
    - java.time.DayOfWeek;

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

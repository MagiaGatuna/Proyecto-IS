package src.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Calcular_dia {
    
public static String getdia(){
    
    LocalDate dia= LocalDate.now();//pido la fecha exacta del dia de hoy
    DayOfWeek dia_semana= dia.getDayOfWeek();//Obtengo el dia en expresion de día de la semana
    String texto_dia= dia_semana.toString(); //Aquí deberia devolverme el día actual en ingles, como "SUNDAY", ya que como no estaba en una cadena de tipo string, hay que convertirla con la funcion de toString()
    return texto_dia;
}
public static int gethora(){ //Aqui se maneja el calculo de la hora, lo hace en minutos, es decir, si son las 11 am, debe ser 60X11=660.
    LocalTime hora= LocalTime.now(); //esto sirve para obtener todos los datos referentes a la hora actual del dispositivo donde se ejecute el programa
    int hora_actual= hora.getHour();
    int minutos_actual=hora.getMinute();
    //Primero obtengo de toda la información especifica de la hora local, solo la hora actual en formato de 24 horas (valga la redundancia), 
    // pero tambien se tiene que contemplar los minutos para tener una hora correcta, por lo cual a la informacion de la hora local, voy y pido los minutos
    // ya con todo eso, los minutos que tengo los voy a sumar a la hora actual pero en minutos(1 hora son 60 minutos) para mantener coherencia, y eso si es lo que voy a retornar
    int resultado= (hora_actual*60)+minutos_actual;
    return resultado;
}

public static int getDiaMesNumero(){
        LocalDate dia = LocalDate.now();
        return dia.getDayOfMonth();
    }

    public static String getMes(){
        LocalDate dia = LocalDate.now();
        return dia.getMonth().toString();
    }

    public static int getAnio(){
        LocalDate dia = LocalDate.now();
        return dia.getYear();
    }

}

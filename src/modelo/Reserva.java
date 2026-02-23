package src.modelo;

public class Reserva {
    private String cedula;
    private String diaTurno; 

    public Reserva(String cedula, String diaTurno) {
        this.cedula = cedula;
        this.diaTurno = diaTurno;
    }

    public String getCedula() { return cedula; }
    public String getDiaTurno() { return diaTurno; }
}
package src.modelo;

public class Reserva {
    private String cedula;
    private String diaTurno; 
    private String fechaExacta;

    public Reserva(String cedula, String diaTurno, String fechaExacta) {
        this.cedula = cedula;
        this.diaTurno = diaTurno;
        this.fechaExacta= fechaExacta;
    }

    public String getCedula() { return cedula; }
    public String getDiaTurno() { return diaTurno; }
    public String getFechaExacta() { return fechaExacta; }
}
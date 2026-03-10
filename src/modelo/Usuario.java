package src.modelo;

public class Usuario {
    private String nombre;
    private double saldo;
    private String rol;
    private String cedula;
    private String sexo;
    private String condicion;
   
    public Usuario(String nombre,double saldo,String rol,String cedula, String sexo, String condicion) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.rol = rol;
        this.cedula=cedula;
        this.sexo=sexo;
        this.condicion= condicion;
    }

    // Getters
    public String getRol() { return rol; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public String getCedula(){ return cedula;}
    public String getSexo(){ return sexo;}
    public String getCondicion() { return condicion; }
    public void setSaldo(double saldo) { this.saldo = saldo;}
    public void setCondicion(String condicion) { this.condicion = condicion; }
    

}
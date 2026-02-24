package src.modelo;

public class Usuario {
    private String nombre;
    private double saldo;
    private String rol;
    private String cedula;
    private String sexo;
   
    public Usuario(String nombre,double saldo,String rol,String cedula, String sexo) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.rol = rol;
        this.cedula=cedula;
        this.sexo=sexo;
    }

    // Getters
    public String getRol() { return rol; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public String getCedula(){ return cedula;}
    public String getSexo(){ return sexo;}
    public void setSaldo(double saldo) { this.saldo = saldo;}
// falta el set que seria el de recargar...
}
package src.modelo;

public class Usuario {
    private String nombre;
    private double saldo;
    private String rol;
    private String cedula;
   
    public Usuario(String nombre,double saldo,String rol,String cedula) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.rol = rol;
        this.cedula=cedula;
    }

    // Getters
    public String getRol() { return rol; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public String getCedula(){ return cedula;}
    public void setSaldo(double saldo) {
    this.saldo = saldo;
}
// falta el set que seria el de recargar...
}
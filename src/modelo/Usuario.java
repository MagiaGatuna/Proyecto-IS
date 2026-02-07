package src.modelo;

public class Usuario {
    private String nombre;
    private double saldo;
    private String rol;

    public Usuario(String nombre,double saldo,String rol) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.rol = rol;
    }

    // Getters
    public String getRol() { return rol; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
// falta el set que seria el de recargar...
}
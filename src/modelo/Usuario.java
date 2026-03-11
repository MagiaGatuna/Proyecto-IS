package src.modelo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Usuario {
    private String nombre;
    private double saldo;
    private String rol;
    private String cedula;
    private String sexo;
    private String estado;
   
    public Usuario(String nombre,double saldo,String rol,String cedula, String sexo, String estado) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.rol = rol;
        this.cedula=cedula;
        this.sexo=sexo;
        this.estado= estado;
    }

    // Getters
    public String getRol() { return rol; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public String getCedula(){ return cedula;}
    public String getSexo(){ return sexo;}
    public String getCondicion() { return estado; }
    public void setSaldo(double saldo) { this.saldo = saldo;}
    public void setCondicion(String estado) { this.estado = estado; }

    

}
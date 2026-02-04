package src.controlador;

import src.Landingpage;
import src.Registro;
import src.InicioSesion;
import src.util.ValidarUtil;
import src.HomeAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Controlador_inicioS implements ActionListener{
    //getHome y getRegistro
    private Landingpage inicio;
    private Registro ventanaRegistro;
    private InicioSesion inicio_sesion;
    private HomeAdmin admin;

    String Rol="";

    public Controlador_inicioS(Landingpage inicio, Registro ventanaRegistro, InicioSesion inicio_sesion, HomeAdmin admin){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio_sesion=inicio_sesion;
        this.admin=admin;
        this.inicio_sesion.getRegistro().addActionListener(this);
        this.inicio_sesion.getHome().addActionListener(this);
        this.admin.getHome2().addActionListener(this);
        this.inicio_sesion.getAdmin().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == admin.getHome2()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            admin.setVisible(false);

        }
        if(e.getSource() == inicio_sesion.getAdmin()){
            if (validarInicioSesion()){
                /*   
                    if((Rol.equals("Administrador"))){
                        
                    }
                    if((Rol.equals("Trabajador")||Rol.equals("Docente"))){
        
                        
                    }
                    if((Rol.equals("Estudiante"))){
                    

                    }
                 */ 
                admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                admin.setResizable(false);
                admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                admin.setVisible(true);
                inicio_sesion.setVisible(false);
            }
        }
        if(e.getSource()==inicio_sesion.getRegistro()){

            ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventanaRegistro.setResizable(false);
            ventanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaRegistro.setVisible(true);
            inicio_sesion.setVisible(false);
        }
        if(e.getSource()==inicio_sesion.getHome()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            inicio_sesion.setVisible(false);

        }

    }

    private boolean validarInicioSesion(){
        StringBuilder errores = new StringBuilder();

        if(ValidarUtil.campoEstaVacio(inicio_sesion.getCedula_id(), "Cédula de identidad")){
            errores.append("- El campo Cédula es obligatorio\n\n");
        } else if(!ValidarUtil.cedulaEsValida(inicio_sesion.getCedula_id())){
            errores.append("- La cédula debe contener solo números\n");
        }

        if(ValidarUtil.campoEstaVacio(inicio_sesion.getContraseña(), "Contraseña")){
            errores.append("- El campo Contraseña es obligatorio\n\n");
        }
        
        if(errores.length()>0){
            JOptionPane.showMessageDialog(null, "Por favor corrija los siguientes errores:\n\n" + errores.toString(),
                "Errores en el formulario", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            StringBuilder problemas = new StringBuilder();
            try {
                Path rutaRaiz = Paths.get("res/data/usuarios.json").toAbsolutePath();
                if (!Files.exists(rutaRaiz)) {
                    problemas.append("- Archivo no encontrado\n\n");
                }
                    
                
                String contenidoJson = new String(Files.readAllBytes(rutaRaiz), StandardCharsets.UTF_8);
                JSONArray listaUsuarios = new JSONArray(contenidoJson);
                int i=0;
                for(; i<listaUsuarios.length(); i++){
                    JSONObject usuario = listaUsuarios.getJSONObject(i);
                    if(usuario.getString("cedula").trim().equals(inicio_sesion.getCedula_id().getText().trim())){
                        if(usuario.getString("contraseña").equals(String.valueOf(inicio_sesion.getContraseña().getPassword()))){
                            Rol=usuario.getString("rol");
                            return true;
                        } else {
                            problemas.append("- Contraseña incorrecta\n\n");
                            break;
                        }
                    }
                }
                if(i==listaUsuarios.length())
                    problemas.append("- Usuario no registrado\n\n");
            }catch(IOException e){
                problemas.append("- Error al leer la base de datos de usuarios\n\n");
            }

            JOptionPane.showMessageDialog(null, "¡Lo sentimos! no se pudo iniciar sesion:\n\n" + problemas.toString(),
                "Errores en el formulario", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }

}

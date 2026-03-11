package src.controlador;

import src.vista.HomeAdmin;
import src.modelo.ActualizarUsuario;
import src.vista.CambiarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Cambiar_Usuario implements ActionListener{
//new Controlador_MenuSemanal(null, null,admin, menu_s_a);
private HomeAdmin homeAdmin;
private CambiarUsuario usuario;

public Controlador_Cambiar_Usuario(HomeAdmin homeAdmin,CambiarUsuario usuario){

this.homeAdmin=homeAdmin;
this.usuario=usuario;

    if(usuario!=null){
        usuario.getAceptar().addActionListener(this);
        usuario.getCerrar().addActionListener(this);
    }
}
@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    
        if(source==usuario.getAceptar()){
            String seleccion=(String)usuario.getCombo().getSelectedItem();
            String cedula=usuario.getTxtCedula().getText().trim();
            boolean actualizado= ActualizarUsuario.actualizar(seleccion,cedula);
            if(actualizado){
                JOptionPane.showMessageDialog(usuario,"Actualizado con éxito");
                usuario.getTxtCedula().setText("");
            }else{
                JOptionPane.showMessageDialog(usuario,"Cédula invalida, no se puede actualizar");
            }
        }
        if(source==usuario.getCerrar()){
            homeAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
            homeAdmin.setVisible(true);
            usuario.setVisible(false);
        }
    
    }

}


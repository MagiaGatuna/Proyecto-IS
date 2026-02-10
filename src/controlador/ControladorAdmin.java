package src.controlador;

import src.vista.Landingpage;
import src.vista.GestorCFView;
import src.vista.GestorCVView;
import src.vista.HomeAdmin;
import src.vista.InicioSesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.util.LimpiarFormulariosUtil;

public class ControladorAdmin implements ActionListener {
    
    private Landingpage landingpage;  
    private HomeAdmin homeAdmin;  
    private InicioSesion inicio_sesion;    
    
    public ControladorAdmin(Landingpage landingpage, HomeAdmin homeAdmin,InicioSesion inicio_sesion) {
        this.landingpage = landingpage;
        this.homeAdmin = homeAdmin;
        this.inicio_sesion = inicio_sesion;
        
        if (this.homeAdmin != null) {
            this.homeAdmin.getHome2().addActionListener(this);
            this.homeAdmin.getBtnGestorCF().addActionListener(this);
            this.homeAdmin.getBtnGestorCV().addActionListener(this);
            avisoProximamente(this.homeAdmin.getBtnTurnos());
            avisoProximamente(this.homeAdmin.getBtnReporte());
            avisoProximamente(this.homeAdmin.getBtnMenu());
            avisoProximamente(this.homeAdmin.getBtnInventario());
            avisoProximamente(this.homeAdmin.getBtnConsumos());
        }
    }
    
    private void avisoProximamente(JButton boton) {
            if (boton != null) {
                boton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, 
                        "Esta funcionalidad estará disponible en la próxima actualización.", 
                        "En construcción", 
                        JOptionPane.INFORMATION_MESSAGE);
                });
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    
        if (source == homeAdmin.getHome2()) {
            cerrarSesion();
        }

        if (source == homeAdmin.getBtnGestorCF()) {
            abrirGestorCF();
        }

        if (source == homeAdmin.getBtnGestorCV()) {
            abrirGestorCV();
        }
        
    }

    private void abrirGestorCF(){ 
        GestorCFView vistaCF = new GestorCFView();
        
        new Controlador_GestorCF(vistaCF, landingpage, homeAdmin);
        
        vistaCF.setVisible(true);
        homeAdmin.setVisible(false);
    }

    private void abrirGestorCV(){ 
        GestorCVView vistaCV = new GestorCVView();
        
        new Controlador_GestorCV(vistaCV, landingpage, homeAdmin);
        
        vistaCV.setVisible(true);
        homeAdmin.setVisible(false);
    }
    
    private void cerrarSesion() {
        if (inicio_sesion != null) {
            LimpiarFormulariosUtil.limpiarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña());
        }
        homeAdmin.setVisible(false);
        homeAdmin.dispose();  
        
        landingpage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        landingpage.setVisible(true);
    }
}
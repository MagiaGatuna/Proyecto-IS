package src.controlador;

import src.vista.Landingpage;
import src.vista.GestorCFView;
import src.vista.GestorCVView;
import src.vista.GestorCostosView;
import src.vista.MenuSemanal;
import src.vista.ListadoComensales;
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
    private MenuSemanal menu;
    
    public ControladorAdmin(Landingpage landingpage, HomeAdmin homeAdmin,InicioSesion inicio_sesion, MenuSemanal menu) {
        this.landingpage = landingpage;
        this.homeAdmin = homeAdmin;
        this.inicio_sesion = inicio_sesion;
        this.menu=menu;
        
        if (this.homeAdmin != null) {
            this.homeAdmin.getHome2().addActionListener(this);
            this.homeAdmin.getBtnCostos().addActionListener(this);
            this.homeAdmin.getBtnMenu().addActionListener(this);
            avisoProximamente(this.homeAdmin.getBtnTurnos());
            this.homeAdmin.getBtnListadoComensales().addActionListener(this);
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
        if (source == homeAdmin.getBtnCostos()) {
            abrirGestorCostos();
        }
        if (source == homeAdmin.getBtnListadoComensales()) {
            abrirListadoComensales();
        }
        if(source == homeAdmin.getBtnMenu()){
            menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
            menu.setVisible(true);
            homeAdmin.setVisible(false);
        }
        
    }

    private void abrirListadoComensales(){
        ListadoComensales vista = new ListadoComensales();
        new Controlador_ListadoComensales(vista, homeAdmin);
        homeAdmin.setVisible(false);
    }

    
    private void abrirGestorCostos(){ 
        GestorCostosView vistaCostos = new GestorCostosView();
        GestorCFView gestorCFView = new GestorCFView();
        GestorCVView gestorCVView = new GestorCVView();
        
        new Controlador_GestorCostos(vistaCostos, landingpage, homeAdmin, gestorCFView, gestorCVView);
        
        vistaCostos.setVisible(true);
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
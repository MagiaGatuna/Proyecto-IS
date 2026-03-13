package src.controlador;
 
import src.vista.Landingpage;
import src.vista.GestorCFView;
import src.vista.GestorCVView;
import src.vista.GestorCostosView;
import src.vista.MenuSemanal;
import src.vista.ListadoComensales;
import src.vista.HomeAdmin;
import src.vista.InicioSesion;
import src.vista.CambiarUsuario;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.util.LimpiarFormulariosUtil;
import src.util.Conectar_ventanas;
 
public class ControladorAdmin implements ActionListener {
    
    private Landingpage landingpage;  
    private HomeAdmin homeAdmin;  
    private InicioSesion inicio_sesion;   
    private MenuSemanal menu;
    private CambiarUsuario usuario;
    
    public ControladorAdmin(Landingpage landingpage, HomeAdmin homeAdmin,InicioSesion inicio_sesion, MenuSemanal menu,CambiarUsuario usuario) {
        this.landingpage = landingpage;
        this.homeAdmin = homeAdmin;
        this.inicio_sesion = inicio_sesion;
        this.menu=menu;
        this.usuario=usuario;
        
        if (this.homeAdmin != null) {
            this.homeAdmin.getHome2().addActionListener(this);
            this.homeAdmin.getBtnCostos().addActionListener(this);
            this.homeAdmin.getBtnMenu().addActionListener(this);
            this.homeAdmin.getBtnUsuarios().addActionListener(this);
            this.homeAdmin.getBtnListadoComensales().addActionListener(this);
            this.homeAdmin.getBtnTarifas().addActionListener(this);
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
        if(source == homeAdmin.getBtnTarifas()){
            Conectar_ventanas.getInstancia().desplegarTarifas(homeAdmin);
        }
        if(source == homeAdmin.getBtnUsuarios()){
            usuario.setExtendedState(JFrame.MAXIMIZED_BOTH);
            usuario.setVisible(true);
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
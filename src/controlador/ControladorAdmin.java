package src.controlador;

import src.Landingpage;
import src.HomeAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControladorAdmin implements ActionListener {
    
    private Landingpage landingpage;  
    private HomeAdmin homeAdmin;      
    
    public ControladorAdmin(Landingpage landingpage, HomeAdmin homeAdmin) {
        this.landingpage = landingpage;
        this.homeAdmin = homeAdmin;
        
        if (this.homeAdmin != null) {
            this.homeAdmin.getHome2().addActionListener(this);
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    
        if (source == homeAdmin.getHome2()) {
            cerrarSesion();
        }
        
    }
    
    private void cerrarSesion() {
        homeAdmin.setVisible(false);
        homeAdmin.dispose();  
        
        landingpage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        landingpage.setVisible(true);
    }
}
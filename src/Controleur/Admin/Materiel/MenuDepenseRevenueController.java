/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Materiel;

import Controleur.AdminControleur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MenuDepenseRevenueController implements Initializable {
private static AdminControleur mainControler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static void init(AdminControleur controleur){
        mainControler=controleur;
    }
    
    @FXML
    void handleConsulterRevenue(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/ConsulterRevenue.fxml")));
    }
    
    @FXML
    void handleConsulterDepense(ActionEvent event) throws IOException  {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/ConsulterDepense.fxml")));
    
    }
    
}

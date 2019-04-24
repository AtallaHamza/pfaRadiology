/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Medecin;

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
public class MenuMedecinController implements Initializable {
    private static InterfaceMedecinController mainControler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void handleAjouterRendu(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Medecin/AjouterRendu.fxml")));
    }
    
    @FXML
    void handleConsultFiches(ActionEvent event) throws IOException  {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/RDV/Patient/ConsulterPatient.fxml")));
    
    }
    
    
    public static void init(InterfaceMedecinController controleur){
        mainControler=controleur;
    }
}

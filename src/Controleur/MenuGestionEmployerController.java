/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import static java.awt.SystemColor.menu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MenuGestionEmployerController implements Initializable {

     private static AdminControleur mainControler;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
     
    @FXML
    void handelajoutemployer(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/FormulaireAjoutEmployer.fxml")));
    }
    
    /*@FXML
    void handlconsulemployer(ActionEvent event) throws IOException  {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/FormulaireConsulterEmployer.fxml")));
    
    }*/
 
    @FXML
    void handleConsulterSecretaire(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/FormulaireConsulterEmployer.fxml")));

    }
    @FXML
    void handleConsulterMedecin(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/Employer/ConsulterMedecin.fxml")));

    }
    @FXML
    void handleConsulterTechnicien(ActionEvent event)throws IOException  {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/Employer/ConsulterTechnicien.fxml")));

    }

    @FXML
    void handlsuppemployer(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/FormulaireSupprimerEmployer.fxml")));
    }
    
    public static void init(AdminControleur controleur){
        mainControler=controleur;
    }
}

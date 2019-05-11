/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Secretaire.Fiches;

import Controleur.SecretaireControleur;
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
public class MenuGestionFichesController implements Initializable {

    private static SecretaireControleur mainControler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
     
    @FXML
    void handleAjoutFiche(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/Fiches/FormulaireAjoutFiche.fxml")));
    }
    @FXML
    void handleAjoutPtient(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/RDV/Patient/AjouterPatient.fxml")));
    }
    @FXML
    void handleConsultFiches(ActionEvent event) throws IOException  {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/RDV/Patient/ConsulterPatient.fxml")));
    
    }
 
    @FXML
    void handleModifierFiche(ActionEvent event) {

    }

    @FXML
    void handleSupprimerFiche(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaie/Fiches/FormulaireSupprimerFiche.fxml")));
    }
    @FXML
    void handleImprimerFiches(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/Fiches/ImprimerFche.fxml")));
    }
    
    public static void init(SecretaireControleur controleur){
        mainControler=controleur;
    }
    
}

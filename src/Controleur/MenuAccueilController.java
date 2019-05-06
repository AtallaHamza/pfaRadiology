/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Controleur.Admin.Materiel.MenuDepenseRevenueController;
import Controleur.Admin.Salle.MenuCentreController;
import Controleur.Medecin.InterfaceMedecinController;
import Controleur.Medecin.MenuMedecinController;
import Controleur.Secretaire.Fiches.MenuGestionFichesController;
import com.jfoenix.controls.JFXButton;
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
public class MenuAccueilController implements Initializable {

    private static AdminControleur mainControler;
        private static SecretaireControleur SecretaireControler;
        private static InterfaceMedecinController MedecinControler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private JFXButton btnemployer;

    @FXML
    private JFXButton btnExamen;

    @FXML
    private JFXButton btnSalles;

    @FXML
    private JFXButton btneMachine;

    @FXML
    private JFXButton btnemployer4;
    
    @FXML
    private JFXButton btnRDV;
     
    @FXML
    private JFXButton btnDossiers;   
    
    @FXML
    private JFXButton btnFiches;
    
    @FXML
    private JFXButton btnPatient;
    
    @FXML
    private JFXButton btnNewRendu;
    
    @FXML
    private JFXButton btnListRendu;
        
    @FXML
    void handleemployer(ActionEvent event) throws IOException {
       MenuGestionEmployerController.init(mainControler);
       mainControler.getMenu().getChildren().removeAll(mainControler.getMenu().getChildren());
       mainControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/MenuGestionEmployer.fxml")));
    }
    /*
    @FXML
    void handleCentre(ActionEvent event) throws IOException {
       MenuCentreController.init(mainControler);
       mainControler.getMenu().getChildren().removeAll(mainControler.getMenu().getChildren());
       mainControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/MenuCentre.fxml")));
    }*/
    @FXML
    void handleSalles(ActionEvent event) throws IOException {
       MenuSalleController.init(mainControler);
       mainControler.getMenu().getChildren().removeAll(mainControler.getMenu().getChildren());
       mainControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/MenuSalle.fxml")));
    }
    
    @FXML
    void handleExamen(ActionEvent event) throws IOException {
       MenuExamenController.init(mainControler);
       mainControler.getMenu().getChildren().removeAll(mainControler.getMenu().getChildren());
       mainControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/MenuExamen.fxml")));
    }
    
    @FXML
    void handleMachine(ActionEvent event) throws IOException {
       MenuMaterielleController.init(mainControler);
       mainControler.getMenu().getChildren().removeAll(mainControler.getMenu().getChildren());
       mainControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/MenuMaterielle.fxml")));
    }
    
    @FXML
    void handleDepenses(ActionEvent event) throws IOException {
       MenuDepenseRevenueController.init(mainControler);
       mainControler.getMenu().getChildren().removeAll(mainControler.getMenu().getChildren());
       mainControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Admin/MenuDepenseRevenue.fxml")));
    }
    @FXML
    void handleRDV(ActionEvent event) throws IOException {
       MenuGestionRDVController.init(SecretaireControler);
       SecretaireControler.getMenu().getChildren().removeAll(SecretaireControler.getMenu().getChildren());
       SecretaireControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/MenuGestionRDV.fxml")));
    }
    @FXML
    void handleFiches(ActionEvent event) throws IOException {
       MenuGestionFichesController.init(SecretaireControler);
       SecretaireControler.getMenu().getChildren().removeAll(SecretaireControler.getMenu().getChildren());
       SecretaireControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/MenuGestionFiches.fxml")));
    }
    @FXML
    void handleAjouterRendu(ActionEvent event) throws IOException {
        MedecinControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Medecin/AjouterRendu.fxml")));
    }
    
    @FXML
    void handleConsulterRendu(ActionEvent event) throws IOException {
        MedecinControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Medecin/ConsulterRendu.fxml")));
    }
    
    @FXML
    void handlePatient(ActionEvent event) throws IOException {
       MenuGestionFichesController.init(SecretaireControler);
       SecretaireControler.getMenu().getChildren().removeAll(SecretaireControler.getMenu().getChildren());
       SecretaireControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/MenuGestionFiches.fxml")));
    }
    @FXML
    void handleCompteRendu(ActionEvent event) throws IOException {
       MenuMedecinController.init(MedecinControler);
       MedecinControler.getMenu().getChildren().removeAll(MedecinControler.getMenu().getChildren());
       MedecinControler.getMenu().getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Medecin/MenuMedecin.fxml")));
    }
    public static void init(AdminControleur controleur){
        mainControler=controleur;
    }
    public static void initSecretaire(SecretaireControleur controleur){
        SecretaireControler=controleur;
    }
    public static void initMedecin(InterfaceMedecinController controleur){
        MedecinControler=controleur;
    }
}

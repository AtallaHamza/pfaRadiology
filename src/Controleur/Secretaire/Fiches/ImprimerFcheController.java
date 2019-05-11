/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Secretaire.Fiches;

import Controleur.FichePdf;
import Controleur.FormulaireAjoutEmployerController;
import Dao.PatientDao;
import Model.Patient;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ImprimerFcheController implements Initializable {

    @FXML
    private JFXComboBox<Patient> patients;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Patient p : PatientDao.findAllPatient()) {
            this.patients.getItems().add(p);
        }
    }

    @FXML
    public void Imprimer(ActionEvent event) {
        if (patients.getSelectionModel().getSelectedItem() != null) {

            int idPatient = patients.getSelectionModel().getSelectedItem().getIdPatient();
                            try {   
                                            FichePdf f = new FichePdf();

                                System.out.println("**********");

                                f.createSamplePDF(PatientDao.findPatientById(idPatient));
                                System.out.println("--------");
                            } catch (Exception ex) {
                                Logger.getLogger(FormulaireAjoutEmployerController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Formulaire invalide!");
            alert.showAndWait();
        }
    }
}

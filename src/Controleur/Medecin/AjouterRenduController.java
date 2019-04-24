/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Medecin;

import Dao.ExamenDao;
import Dao.PatientDao;
import Dao.RenduDao;
import Model.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterRenduController implements Initializable {

    @FXML
    private NotificationPane Notif;

    @FXML
    private JFXTimePicker hour;

    @FXML
    private JFXComboBox<Patient> patients;

    @FXML
    private JFXComboBox<Examen> examens;

    @FXML
    private JFXTextField observations;

    @FXML
    private JFXTextField resultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hour.setIs24HourView(true);
        for (Patient p : PatientDao.findAllPatient()) {
            this.patients.getItems().add(p);
        }
        for (Examen p : ExamenDao.findAllExamen()) {
            this.examens.getItems().add(p);
        }
    }

    @FXML
    void handleReset(ActionEvent event) {
        patients.setValue(null);
        examens.setValue(null);
        observations.setText(null);
        resultat.setText(null);
    }

    @FXML
    public void addRendu(ActionEvent event) {
        if (observations.getLength()!= 0 && resultat.getLength()!= 0 && hour.getValue() != null && examens.getSelectionModel().getSelectedItem() != null && patients.getSelectionModel().getSelectedItem() != null) {
            String heure = this.hour.getValue().toString();
            int idPatient = patients.getSelectionModel().getSelectedItem().getIdPatient();
            int idExamen = examens.getSelectionModel().getSelectedItem().getIdExamen();
            String commentaire = this.observations.getText();
            String result = this.resultat.getText();
            Rendu r =new Rendu(heure, commentaire, result, idPatient, idExamen);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adding Rendu Confirmation");
            alert.setHeaderText("New Rendu will be added");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> resultClick = alert.showAndWait();

            if (resultClick.get() == ButtonType.OK) {
                RenduDao.AjouterRendu(r);
                Notif.show("New Rendu is added !! ");

            } 
            else {
                handleReset(event);
            }
        }
        else {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Formulaire invalide!");
            alert.showAndWait();
            handleReset(event); }
    }
}

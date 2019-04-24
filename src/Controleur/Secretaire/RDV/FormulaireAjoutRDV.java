/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Secretaire.RDV;

import Dao.PatientDao;
import Dao.RendezVousDao;
import Model.Patient;
import Model.RendezVous;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.NotificationPane;

/**
 *
 * @author asus
 */
public class FormulaireAjoutRDV implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hour.setIs24HourView(true);
        for (Patient p : PatientDao.findAllPatient()) {
            this.patients.getItems().add(p);
        }
        for (RendezVous r: RendezVousDao.findAllRendezVous()) {
                System.out.println("rr " + r.getHeure());
        }
    }
    @FXML
    private NotificationPane Notif;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTimePicker hour;
    @FXML
    private JFXComboBox<Patient> patients;

    @FXML
    void handleReset(ActionEvent event) {
        patients.setValue(null);

        date.setValue(null);
    }

    @FXML
    public void addRDV(ActionEvent event) {
        if (date.getValue() != null && hour.getValue() != null && patients.getSelectionModel().getSelectedItem() != null) {

            int idPatient = patients.getSelectionModel().getSelectedItem().getIdPatient();
            Date d = Date.valueOf(date.getValue());
            String heure = this.hour.getValue().toString();
            RendezVous r = new RendezVous(d,heure ,idPatient);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adding RDV Confirmation");
            alert.setHeaderText("New RDV will be added");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                RendezVousDao.AjouterRendezVous(r);
                Notif.show("New RDV is added !! ");

            } 
            else {
                handleReset(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Formulaire invalide!");
            alert.showAndWait();
            handleReset(event);

        }
    }
}

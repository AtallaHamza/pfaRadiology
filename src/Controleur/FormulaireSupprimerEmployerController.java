/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Model.Admin;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FormulaireSupprimerEmployerController {
   
    @FXML
    private JFXTextField cin;

    @FXML
    void handleReset(ActionEvent event) {
        cin.setText(null);
    }

    @FXML
    void handleSupprimer(ActionEvent event) throws SQLException {
        long id = Long.parseLong(this.cin.getText());
        Alert alert = new Alert(AlertType.NONE);
        alert.setHeaderText(null);
        if(Admin.SupprimerEmployer(id)){
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Employer Supprimer aveec succée!");
        }else{
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Employer ne peut pas etre supprimer!");
        }
        alert.showAndWait();
    }

}
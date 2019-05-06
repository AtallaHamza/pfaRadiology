/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Salle;

import Dao.CentreDao;
import Model.Centre;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierCentreController implements Initializable {

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField nom;

    @FXML
    private NotificationPane Notif;

    @FXML
    private JFXTextField tele;

    @FXML
    private JFXTextField fax;

    
    int centreId;
    @FXML
    void handleReset(ActionEvent event) {
        nom.setText(null);
        adress.setText(null);
        tele.setText(null);
        email.setText(null);
        fax.setText(null);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        tele.setTextFormatter(textFormatter1);
        fax.setTextFormatter(textFormatter);
        List<Centre> lst = CentreDao.findAllCentre();
        for (Centre emp : lst) {
            tele.setText(String.valueOf(emp.getTel()));
            fax.setText(String.valueOf(emp.getFax()));
            nom.setText(emp.getNom());
            email.setText(emp.getEmail());
            adress.setText(emp.getAdress());
            centreId=     emp.getIdCentre();
        }
    }

    private boolean verifAjoutform(String nom, String email, String adress) {

        return (nom == null || email.length() == 0 || adress.length() == 0 ? false : true);
    }

    @FXML
    public void ModifierCentre() {

        if (this.tele.getText().length() == 0 && this.fax.getText().length() == 0) {
            Notif.show("telephone doit etre non null !");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("telephone doit etre non null !");
            alert.showAndWait();
        } else if (!this.tele.getText().equals("") && verifAjoutform(this.nom.getText(), email.getText(), adress.getText())) {

            long tel = Long.parseLong(this.tele.getText());
            long f = Long.parseLong(this.fax.getText());
            String nomForm = this.nom.getText();
            String emailForm = this.email.getText();
            String adressForm = this.adress.getText();
            Centre centre1= new Centre(nomForm, adressForm, emailForm, tel, f);
            CentreDao.updateCentre(centre1,centreId);
           List<Centre> lst = CentreDao.findAllCentre();
        for (Centre emp : lst) {
            tele.setText(String.valueOf(emp.getTel()));
            fax.setText(String.valueOf(emp.getFax()));
            nom.setText(emp.getNom());
            email.setText(emp.getEmail());
            adress.setText(emp.getAdress());
            centreId=     emp.getIdCentre();
        }
        } else {
            Notif.show("Formulaire invalide !! ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Formulaire invalide!");
            alert.showAndWait();
        }
    }

}

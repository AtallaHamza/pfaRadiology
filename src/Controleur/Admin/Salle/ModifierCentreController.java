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
            

        }
}
}
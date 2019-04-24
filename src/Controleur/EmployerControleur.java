/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author asus
 */
public class EmployerControleur implements Initializable{
   @FXML
    private Label greetings;   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Statement stm = Connexion.connect().createStatement();
            ResultSet rs= stm.executeQuery("SELECT NOM,PRENOM FROM users where type = 'A'");
            rs.next();
            String nom =rs.getString("NOM");
            String prenom=rs.getString("PRENOM");
            greetings.setText("Bonjour Mr/Mrs "+nom+" "+prenom);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

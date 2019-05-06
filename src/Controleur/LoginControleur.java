/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.PFA2019;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginControleur implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private NotificationPane Notif;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXPasswordField pass;
    private static Connection connection;

    @FXML
    void handlebtn(ActionEvent event) throws SQLException, IOException {
        ResultSet rs;
        String nomform = this.nom.getText();
        String passform = this.pass.getText();
        if (verifloginform(nomform, passform)) {
            connection = Connexion.getInstance().getConnection();
            Statement statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM compte WHERE LOGIN = '" + nomform + "' AND PASSWORD = '" + passform + "'");
            if (rs.next()) {
                Stage st = PFA2019.getStage();

                if (rs.getString(4).equals("S")) {
                   

                    Scene scene = new Scene((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/InterfaceSecretaire.fxml")));
                    st.setScene(scene);
                    st.setTitle("BIENVENUE SECRÉTAIRE ");
                    st.setResizable(false);
                    st.show();
                } else if (rs.getString(4).equals("A")) {
                    
                    Scene scene = new Scene((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/InterfaceAdmin.fxml")));
                    st.setScene(scene);
                    st.setTitle("BIENVENUE ADMINISTRATEUR ");
                    st.setResizable(false);
                    st.show();
                }else if (rs.getString(4).equals("M")) {
                   
                    Scene scene = new Scene((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/InterfaceMedecin.fxml")));
                    st.setScene(scene);
                    st.setTitle("BIENVENUE DOCTEUR ");
                    st.setResizable(false);
                    st.show();
                }
            } else {
                Notif.show("Authentification echouée");
                rs.close();
                statement.close();
            }
        } else {
            Notif.show("Vérifier que tous les champs sont remplis");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean verifloginform(String n, String p) {
        return (n.length() == 0 || p.length() == 0 ? false : true);
    }

}

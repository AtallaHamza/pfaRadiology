/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Dao.PatientDao;
import Model.Admin;
import Model.Employer;
import Model.Medecin;
import Model.Secretaire;
import Model.Technicien;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FormulaireAjoutEmployerController implements Initializable {

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
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        salaire.setTextFormatter(textFormatter2);
        tele.setTextFormatter(textFormatter1);
        cin.setTextFormatter(textFormatter);

    }

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private JFXTextField tele;

    @FXML
    private JFXTextField salaire;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField passe;
    @FXML
    private NotificationPane Notif;

    @FXML
    void handleAjouter(ActionEvent event) {
        if (VerifyFormulaire()) {
            switch (type.getSelectionModel().getSelectedItem()) {
                case "Secretaire":
                    addSecretaire();
                    break;
                case "Medecin":
                    addMedecin();
                    break;
                case "Technicien":
                    addTechnicien();
                    break;
            }
        }
    }

    @FXML
    void handleReset(ActionEvent event) {
        cin.setText(null);
        nom.setText(null);
        prenom.setText(null);
        adress.setText(null);
        tele.setText(null);
        email.setText(null);
        date.setValue(null);
        type.setValue(null);
        login.setText(null);
        passe.setText(null);
        List<Employer> lst = Admin.findAllSecretaire();
        for (Employer emp : lst) {
            System.out.println("User  " + emp.getNom());
        }
    }

    private boolean VerifyFormulaire() {
        return true;
    }

    private boolean verifAjoutform(String nom, String prenom, String email, Date date, String adress, String login, String pass) {

        return (nom == null || prenom.length() == 0 || email.length() == 0 || date == null || adress.length() == 0 || login.length() == 0 || pass.length() == 0 ? false : true);
    }

    private void addSecretaire()  {

        if (this.cin.getText().length() == 0 || this.cin.getText().length() != 8) {
            Notif.show("CIN doit etre non null et de longueur 8  !");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("CIN doit etre non null et de longueur 8  !");
            alert.showAndWait();
        } else {
            long cinForm = Long.parseLong(this.cin.getText());
            String nomForm = this.nom.getText();
            String prenomForm = this.prenom.getText();
            String emailForm = this.email.getText();
            String adressForm = this.adress.getText();
            String loginForm = this.login.getText();
            String passForm = this.passe.getText();

            if (!Admin.CheckCin(cinForm)) {
                if (date.getValue() != null) {
                    if (!this.tele.getText().equals("") && !this.salaire.getText().equals("") && verifAjoutform(nomForm, prenomForm, emailForm, Date.valueOf(date.getValue()), adressForm, loginForm, passForm)) {

                        int salaireForm = Integer.parseInt(this.salaire.getText());
                        long tel = Long.valueOf(this.tele.getText());
                        Date d = Date.valueOf(date.getValue());
                        if ((LocalDate.now().getYear() - date.getValue().getYear()) <= 60 && (LocalDate.now().getYear() - date.getValue().getYear()) >= 18) {

                            Secretaire s = new Secretaire(cinForm, nomForm, prenomForm, emailForm, adressForm, d, tel, salaireForm, loginForm, passForm);

                            Admin.AjouterEmployer(s);
                            

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Secretaire ajoutée avec succès");
                            alert.showAndWait();
                        } else {

                            Notif.show("DATE DE NAISSANCE doit etre non null et entre 18 et 60  !");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("DATE DE NAISSANCE doit etre non null et entre 18 et 60 !");
                            alert.showAndWait();
                        }

                    } else {
                        Notif.show("Formulaire invalide !! ");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Formulaire invalide!");
                        alert.showAndWait();
                    }
                } else {
                    Notif.show("DATE DE NAISSANCE doit etre non null !");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("DATE DE NAISSANCE doit etre non null!");
                    alert.showAndWait();
                }

            } else {
                Notif.show("CIN déjà existante !");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("CIN déjà existante!");
                alert.showAndWait();
            }
        }

    }

    private void addTechnicien() {
        if (this.cin.getText().length() == 0 || this.cin.getText().length() != 8) {
            Notif.show("CIN doit etre non null et de longueur 8  !");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("CIN doit etre non null et de longueur 8  !");
            alert.showAndWait();
        } else {
            long cinForm = Long.parseLong(this.cin.getText());
            String nomForm = this.nom.getText();
            String prenomForm = this.prenom.getText();
            String emailForm = this.email.getText();
            String adressForm = this.adress.getText();
            String loginForm = this.login.getText();
            String passForm = this.passe.getText();

            if (!Admin.CheckCin(cinForm)) {
                if (date.getValue() != null) {
                    if (!this.tele.getText().equals("") && !this.salaire.getText().equals("") && verifAjoutform(nomForm, prenomForm, emailForm, Date.valueOf(date.getValue()), adressForm, loginForm, passForm)) {

                        int salaireForm = Integer.parseInt(this.salaire.getText());
                        long tel = Long.valueOf(this.tele.getText());
                        Date d = Date.valueOf(date.getValue());

                        if ((LocalDate.now().getYear() - date.getValue().getYear()) <= 60 && (LocalDate.now().getYear() - date.getValue().getYear()) >= 18) {

                            Technicien s = new Technicien(cinForm, nomForm, prenomForm, emailForm, adressForm, d, tel, salaireForm, loginForm, passForm);

                            Admin.AjouterEmployer(s);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Technicien ajouté avec succès");
                            alert.showAndWait();
                        } else {

                            Notif.show("DATE DE NAISSANCE doit etre non null et entre 18 et 60  !");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("DATE DE NAISSANCE doit etre non null et entre 18 et 60 !");
                            alert.showAndWait();
                        }

                    } else {
                        Notif.show("Formulaire invalide !! ");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Formulaire invalide!");
                        alert.showAndWait();
                    }
                } else {
                    Notif.show("DATE DE NAISSANCE doit etre non null !");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("DATE DE NAISSANCE doit etre non null!");
                    alert.showAndWait();
                }

            } else {
                Notif.show("CIN déjà existante !");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("CIN déjà existante!");
                alert.showAndWait();
            }
        }
    }

    private void addMedecin() {
        if (this.cin.getText().length() == 0 || this.cin.getText().length() != 8) {
            Notif.show("CIN doit etre non null et de longueur 8  !");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("CIN doit etre non null et de longueur 8  !");
            alert.showAndWait();
        } else {
            long cinForm = Long.parseLong(this.cin.getText());
            String nomForm = this.nom.getText();
            String prenomForm = this.prenom.getText();
            String emailForm = this.email.getText();
            String adressForm = this.adress.getText();
            String loginForm = this.login.getText();
            String passForm = this.passe.getText();

            if (!Admin.CheckCin(cinForm)) {
                if (date.getValue() != null) {
                    if (!this.tele.getText().equals("") && !this.salaire.getText().equals("") && verifAjoutform(nomForm, prenomForm, emailForm, Date.valueOf(date.getValue()), adressForm, loginForm, passForm)) {

                        int salaireForm = Integer.parseInt(this.salaire.getText());
                        long tel = Long.valueOf(this.tele.getText());
                        Date d = Date.valueOf(date.getValue());
                        if ((LocalDate.now().getYear() - date.getValue().getYear()) <= 60 && (LocalDate.now().getYear() - date.getValue().getYear()) >= 18) {
                            Medecin s = new Medecin(cinForm, nomForm, prenomForm, emailForm, adressForm, d, tel, salaireForm, loginForm, passForm);

                            Admin.AjouterEmployer(s);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Medecin ajouté avec succès");
                            alert.showAndWait();
                        } else {

                            Notif.show("DATE DE NAISSANCE doit etre non null et entre 18 et 60  !");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("DATE DE NAISSANCE doit etre non null et entre 18 et 60 !");
                            alert.showAndWait();
                        }
                    } else {
                        Notif.show("Formulaire invalide !! ");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Formulaire invalide!");
                        alert.showAndWait();
                    }
                } else {
                    Notif.show("DATE DE NAISSANCE doit etre non null et entre 18 et 60  !");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("DATE DE NAISSANCE doit etre non null et entre 18 et 60 !");
                    alert.showAndWait();
                }

            } else {
                Notif.show("CIN déjà existante !");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("CIN déjà existante!");
                alert.showAndWait();
            }
        }
    }
}

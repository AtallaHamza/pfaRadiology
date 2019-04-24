/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Examen;

import Dao.ExamenDao;
import Model.Examen;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterExamenController implements Initializable {

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXTextField resultat;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private NotificationPane Notif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UnaryOperator<Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        prix.setTextFormatter(textFormatter);
    }

    @FXML
    void handleReset(ActionEvent event) {
        nom.setText(null);
        description.setText(null);
        resultat.setText(null);
        type.setValue(null);
        prix.setText(null);
    }

    private boolean verifAjoutform(String TypeExamen, String resultat, String nom, String description) {
        return (TypeExamen == null || description.length() == 0 || nom.length() == 0 || resultat.length() == 0 ? false : true);
    }

    @FXML
    void handleAjouter(ActionEvent event) throws IOException {

        String TypeExamen = type.getSelectionModel().getSelectedItem();
        String nom = this.nom.getText();
        String description = this.description.getText();
        String resultat = this.resultat.getText();
        int prix = Integer.parseInt( this.prix.getText());


        Examen examen = new Examen(nom, description, TypeExamen, resultat, prix);

        if (verifAjoutform(TypeExamen, resultat, nom, description)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adding Exam Confirmation");
            alert.setHeaderText("New exam will be added");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                ExamenDao.AjouterExamen(examen);
                handleReset(event);
            } else {
                handleReset(event);
            }
        } else {
            Notif.show("Formulaire invalide ");

        }

    }
}
/*

   <children>
      <Label layoutX="136.0" layoutY="14.0" text="Ajouter Un Examen" textAlignment="CENTER" textFill="#970d50" wrapText="true">
         <font>
            <Font name="Berlin Sans FB" size="36.0" />
         </font>
      </Label>
      <GridPane layoutX="24.0" layoutY="57.0" prefHeight="524.0" prefWidth="561.0">
        <columnConstraints>
          <ColumnConstraints hgrow="SOMETIMES" maxWidth="275.0" minWidth="10.0" prefWidth="164.0" />
          <ColumnConstraints hgrow="SOMETIMES" maxWidth="399.0" minWidth="10.0" prefWidth="399.0" />
        </columnConstraints>
        <rowConstraints>
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
             <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
            
            
        </rowConstraints>
         <children>
           
            <Label alignment="TOP_CENTER" contentDisplay="TOP" text="NOM :" textAlignment="CENTER" textFill="#063918" wrapText="true" GridPane.rowIndex="1">
               <opaqueInsets>
                  <Insets bottom="10.0" left="20.0" right="20.0" top="10.0" />
               </opaqueInsets>
               <font>
                  <Font name="Berlin Sans FB Demi Bold" size="13.0" />
               </font>
            </Label>
        
            <Label alignment="TOP_CENTER" contentDisplay="TOP" text="Description :" textAlignment="CENTER" textFill="#063918" wrapText="true" GridPane.rowIndex="2">
               <opaqueInsets>
                  <Insets bottom="10.0" left="20.0" right="20.0" top="10.0" />
               </opaqueInsets>
               <font>
                  <Font name="Berlin Sans FB Demi Bold" size="13.0" />
               </font>
            </Label>
            <Label alignment="TOP_CENTER" contentDisplay="TOP" text="Resultat :" textAlignment="CENTER" textFill="#063918" wrapText="true" GridPane.rowIndex="3">
               <opaqueInsets>
                  <Insets bottom="10.0" left="20.0" right="20.0" top="10.0" />
               </opaqueInsets>
               <font>
                  <Font name="Berlin Sans FB Demi Bold" size="13.0" />
               </font>
            </Label>
            <Label alignment="TOP_CENTER" contentDisplay="TOP" text="Type :" textAlignment="CENTER" textFill="#063918" wrapText="true" GridPane.rowIndex="4">
               <opaqueInsets>
                  <Insets bottom="10.0" left="20.0" right="20.0" top="10.0" />
               </opaqueInsets>
               <font>
                  <Font name="Berlin Sans FB Demi Bold" size="13.0" />
               </font>
            </Label>
            
            <JFXTextField fx:id="nom" GridPane.columnIndex="1">
               <font>
                  <Font name="Calibri" size="14.0" />
               </font></JFXTextField>
            <JFXTextField fx:id="description" GridPane.columnIndex="1" GridPane.rowIndex="2">
               <font>
                  <Font name="Calibri" size="14.0" />
               </font></JFXTextField>
           <JFXTextField fx:id="resultat" GridPane.columnIndex="1" GridPane.rowIndex="3">
               <font>
                  <Font name="Calibri" size="14.0" />
               </font></JFXTextField>
            
            <JFXComboBox fx:id="type" promptText="CHOISIR UN TYPE" visibleRowCount="4" GridPane.columnIndex="1" GridPane.rowIndex="4">
                <items>
                    <FXCollections fx:factory="observableArrayList">
                        <String fx:value="Secretaire" />
                        <String fx:value="Medecin" />
                        <String fx:value="Technicien" />
                    </FXCollections>
                </items>
</JFXComboBox>
            
         </children>
      </GridPane>
      <ButtonBar layoutX="226.0" layoutY="390.0" prefHeight="57.0" prefWidth="265.0">
         <buttons>
            <JFXButton contentDisplay="CENTER" defaultButton="true" onAction="#handleAjouter" prefHeight="40.0" style="-fx-background-color: lightgreen;" text="Ajouter" textAlignment="CENTER">
               <font>
                  <Font name="System Bold" size="15.0" />
               </font>
            </JFXButton>
            <JFXButton cancelButton="true" contentDisplay="CENTER" onAction="#handleReset" prefHeight="40.0" style="-fx-background-color: lightgreen;" text="Annuler" textAlignment="CENTER">
               <font>
                  <Font name="System Bold" size="15.0" />
               </font>
            </JFXButton>
         </buttons>
         <padding>
            <Insets left="40.0" right="40.0" />
         </padding>
      </ButtonBar>
   </children>
</AnchorPane>
 */

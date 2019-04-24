/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Medecin;

import Dao.PatientDao;
import Dao.RendezVousDao;
import Dao.RenduDao;
import Model.Patient;
import Model.RendezVous;
import Model.Rendu;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConsulterRenduController implements Initializable {

    @FXML
    private NotificationPane Notif;
    @FXML
    private JFXComboBox<Patient> patients;
    
    @FXML
    private TableView<Rendu> tableRendu;
    
     @FXML
    private TableColumn<Rendu, Integer> tv_ListExamen_cl_ID;

    @FXML
    private TableColumn<Rendu, Date> tv_ListExamen_cl_Date;

    @FXML
    private TableColumn<Rendu, String> tv_ListExamen_cl_Heure;
    
    @FXML
    private TableColumn<Rendu, String> tv_ListExamen_cl_Obser;
    
    @FXML
    private TableColumn<Rendu, String> tv_ListExamen_cl_Result;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Patient p : PatientDao.findAllPatient()) {
            this.patients.getItems().add(p);
        }                             
        tableRendu.setVisible(false);    }    
     public void listRendezVous(List<Rendu> examens) {
        ObservableList<Rendu> examen = FXCollections.observableArrayList(examens);
        this.tableRendu.setItems(examen);
    }
    
    @FXML
    public void listerRendu(ActionEvent event) {
        System.out.println("rd"+ patients.getSelectionModel().getSelectedItem().getIdPatient());
        listRendezVous(RenduDao.findAllRenduByPatientId(
                patients.getSelectionModel().getSelectedItem().getIdPatient()));
                Tableau();
        tableRendu.setVisible(true);

    }
     public void Tableau() {
        this.tableRendu.setEditable(true);

        //this.tv_ListExamen_cl_ID.setCellValueFactory(new PropertyValueFactory("idRendezVous"));
        //tv_ListExamen_cl_ID.setEditable(false);      
        
        this.tv_ListExamen_cl_Result.setCellValueFactory(new PropertyValueFactory("resultat"));
        this.tv_ListExamen_cl_Result.setCellFactory(TextFieldTableCell.<Rendu>forTableColumn());
        tv_ListExamen_cl_Result.setOnEditCommit(
                (TableColumn.CellEditEvent<Rendu, String> t) -> {
                    ((Rendu) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setResultat(t.getNewValue());
                    RenduDao.updateRendu(t.getRowValue());
                });
        
        this.tv_ListExamen_cl_Obser.setCellValueFactory(new PropertyValueFactory("commentaire"));
        this.tv_ListExamen_cl_Obser.setCellFactory(TextFieldTableCell.<Rendu>forTableColumn());
        tv_ListExamen_cl_Obser.setOnEditCommit(
                (TableColumn.CellEditEvent<Rendu, String> t) -> {
                    ((Rendu) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setCommentaire(t.getNewValue());
                    RenduDao.updateRendu(t.getRowValue());
                });
        
        this.tv_ListExamen_cl_Heure.setCellValueFactory(new PropertyValueFactory("heure"));
        this.tv_ListExamen_cl_Heure.setCellFactory(TextFieldTableCell.<Rendu>forTableColumn());
        tv_ListExamen_cl_Heure.setOnEditCommit(
                (TableColumn.CellEditEvent<Rendu, String> t) -> {
                    ((Rendu) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setHeure(t.getNewValue());
                    RenduDao.updateRendu(t.getRowValue());
                });
        this.tv_ListExamen_cl_Date.setCellValueFactory(new PropertyValueFactory("dateRendu"));
        tv_ListExamen_cl_Date.setEditable(false);
       
    }
}

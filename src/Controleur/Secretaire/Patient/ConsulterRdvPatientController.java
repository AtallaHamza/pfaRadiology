/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Secretaire.Patient;

import Dao.PatientDao;
import Dao.RendezVousDao;
import Model.Patient;
import Model.RendezVous;
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
public class ConsulterRdvPatientController implements Initializable {

    @FXML
    private NotificationPane Notif;
    @FXML
    private JFXComboBox<Patient> patients;
    @FXML
    private TableView<RendezVous> tableRendezVous;

    @FXML
    private TableColumn<RendezVous, Integer> tv_ListExamen_cl_ID;

    @FXML
    private TableColumn<RendezVous, Date> tv_ListExamen_cl_Date;

    @FXML
    private TableColumn<RendezVous, String> tv_ListExamen_cl_Heure;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Patient p : PatientDao.findAllPatient()) {
            this.patients.getItems().add(p);
        }                             
        tableRendezVous.setVisible(false);

    }
     public void listRendezVous(List<RendezVous> examens) {
        ObservableList<RendezVous> examen = FXCollections.observableArrayList(examens);
        this.tableRendezVous.setItems(examen);
    }
    
    @FXML
    public void listerRDV(ActionEvent event) {
        System.out.println("rd"+ patients.getSelectionModel().getSelectedItem().getIdPatient());
        listRendezVous(RendezVousDao.findAllRendezVousByPtientId(
                patients.getSelectionModel().getSelectedItem().getIdPatient()));
                Tableau();
        tableRendezVous.setVisible(true);

    }
    
     public void Tableau() {
        this.tableRendezVous.setEditable(true);

        //this.tv_ListExamen_cl_ID.setCellValueFactory(new PropertyValueFactory("idRendezVous"));
        //tv_ListExamen_cl_ID.setEditable(false);      
        this.tv_ListExamen_cl_Heure.setCellValueFactory(new PropertyValueFactory("heure"));
        this.tv_ListExamen_cl_Heure.setCellFactory(TextFieldTableCell.<RendezVous>forTableColumn());
        tv_ListExamen_cl_Heure.setOnEditCommit(
                (TableColumn.CellEditEvent<RendezVous, String> t) -> {
                    ((RendezVous) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setHeure(t.getNewValue());
                    RendezVousDao.updateRendezVous(t.getRowValue());
                });
        this.tv_ListExamen_cl_Date.setCellValueFactory(new PropertyValueFactory("dateRendezVous"));
        //this.tv_ListExamen_cl_Date.setCellFactory(TextFieldTableCell.<RendezVous>forTableColumn());
        tv_ListExamen_cl_Date.setOnEditCommit(
                (TableColumn.CellEditEvent<RendezVous, Date> t) -> {
                    ((RendezVous) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDateRendezVous(t.getNewValue());

                });
        /*dateColumn.setEditable(true);
        dateColumn.setCellValueFactory(new PropertyValueFactory<RendezVous, Date>("dateRendezVous"));
        dateColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                        ObservableList<RendezVous> examen = FXCollections.observableArrayList(RendezVousDao.findAllRendezVous());

                DatePickerCell datePick = new DatePickerCell(examen);
                return datePick;
            }
        });*/
    }
}

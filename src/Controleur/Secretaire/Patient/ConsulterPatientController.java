/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Secretaire.Patient;

import Dao.PatientDao;
import Model.Patient;
import com.sun.javafx.scene.control.skin.TableCellSkin;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConsulterPatientController implements Initializable {
                
    Patient p = new Patient();

    
    @FXML
    private TableView<Patient> tablePatient;

    @FXML
    private TableColumn<Patient, Integer> tv_ListExamen_cl_ID;

    @FXML
    private TableColumn<Patient, String> tv_ListExamen_cl_Nom;

    @FXML
    private TableColumn<Patient, String> tv_ListExamen_cl_Prenom;

    @FXML
    private TableColumn<Patient, String> tv_ListExamen_cl_GenrePatient;

    @FXML
    private TableColumn<Patient, Long> tv_ListExamen_cl_Tel;
    
    @FXML
    private TableColumn<Patient, Date> tv_ListExamen_cl_Date;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listPatient(PatientDao.findAllPatient());
        Tableau();
    }

    public void listPatient(List<Patient> patients) {
        ObservableList<Patient> patient = FXCollections.observableArrayList(patients);
        this.tablePatient.setItems(patient);
    }

    public void Tableau() {
        this.tablePatient.setEditable(true);
        this.tv_ListExamen_cl_ID.setCellValueFactory(new PropertyValueFactory("idPatient"));
        tv_ListExamen_cl_ID.setEditable(false);

        this.tv_ListExamen_cl_Nom.setCellValueFactory(new PropertyValueFactory("nom"));
        this.tv_ListExamen_cl_Nom.setCellFactory(TextFieldTableCell.<Patient>forTableColumn());
        tv_ListExamen_cl_Nom.setOnEditCommit(
                (TableColumn.CellEditEvent<Patient, String> t) -> {
                    ((Patient) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNom(t.getNewValue());
                    PatientDao.updatePatient(t.getRowValue());

                });

        this.tv_ListExamen_cl_Prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        this.tv_ListExamen_cl_Prenom.setCellFactory(TextFieldTableCell.<Patient>forTableColumn());
        tv_ListExamen_cl_Prenom.setOnEditCommit(
                (TableColumn.CellEditEvent<Patient, String> t) -> {
                    ((Patient) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrenom(t.getNewValue());
                    PatientDao.updatePatient(t.getRowValue());
                });
        
        this.tv_ListExamen_cl_GenrePatient.setCellValueFactory(new PropertyValueFactory("genre"));
        this.tv_ListExamen_cl_GenrePatient.setCellFactory(ComboBoxTableCell.<Patient, String>forTableColumn("Homme", "Femme"));

        tv_ListExamen_cl_GenrePatient.setOnEditCommit(
                (TableColumn.CellEditEvent<Patient, String> t) -> {
                    ((Patient) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setGenre(t.getNewValue());
                    PatientDao.updatePatient(t.getRowValue());
                });
        
         this.tv_ListExamen_cl_Tel.setCellValueFactory(new PropertyValueFactory("tel"));
        this.tv_ListExamen_cl_Tel.setCellFactory(TextFieldTableCell.<Patient,Long>forTableColumn(new LongStringConverter()));
        tv_ListExamen_cl_Tel.setOnEditCommit(
                (TableColumn.CellEditEvent<Patient, Long> t) -> {
                    ((Patient) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTel(t.getNewValue());
                    PatientDao.updatePatient(t.getRowValue());
                });
        
        this.tv_ListExamen_cl_Date.setCellValueFactory(new PropertyValueFactory("date"));

    }
    
    
     
    public int GetSelecTedRaw(Object O) {

        if (O instanceof Patient) {

            TablePosition pos = tablePatient.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Patient item = tablePatient.getItems().get(row);
            TableColumn col = pos.getTableColumn();
            return item.getIdPatient();
        }
        return 0;

    }
    @FXML
    public void btnPatient(ActionEvent event) {
        
                                tablePatient.setVisible(false);
                            
    }
    @FXML
    public void SupprimerPatient() {
        int O = GetSelecTedRaw(p);
        System.out.println(O);

        DeleteRaw(O);

        System.out.println("user Deleted Succesfuly");
        listPatient(PatientDao.findAllPatient());
        System.out.println("List Updated");
    }
     public void DeleteRaw(int ID)  {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Selected Row  will be deleted permenatly");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            PatientDao.SupprimerPatient(ID);
            System.out.println("PatientDao tfassa5 Succesfuly");

        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
     
}

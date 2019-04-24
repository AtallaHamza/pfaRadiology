/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Model.Admin;
import Model.Employer;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

/**
 *
 * @author asus
 */
public class ConsulterTechnicienController implements Initializable {

    @FXML
    private TableView<Employer> tableusers;

    @FXML
    private TableColumn<Employer, Long> tv_ListUsers_cl_ID;

    @FXML
    private TableColumn<Employer, String> tv_ListUsers_cl_Nom;

    @FXML
    private TableColumn<Employer, String> tv_ListUsers_cl_Prenom;

    @FXML
    private TableColumn<Employer, Long> tv_ListUsers_cl_Tel;

    @FXML
    private TableColumn<Employer, String> tv_ListUsers_cl_Email;

    @FXML
    private TableColumn<Employer, String> tv_ListUsers_cl_Adress;
    
    @FXML
    private TableColumn<Employer, Integer> tv_ListUsers_cl_Salaire;
    
    Employer emp = new Employer();

    public void Tableau() {
        this.tableusers.setEditable(true);
        //this.tableTechnicien.setEditable(true);

        this.tv_ListUsers_cl_ID.setCellValueFactory(new PropertyValueFactory("cin"));
        tv_ListUsers_cl_ID.setEditable(false);

        this.tv_ListUsers_cl_Nom.setCellValueFactory(new PropertyValueFactory("nom"));
        this.tv_ListUsers_cl_Nom.setCellFactory(TextFieldTableCell.<Employer>forTableColumn());
        tv_ListUsers_cl_Nom.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, String> t) -> {
                    ((Employer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrenom(t.getNewValue());
                    Admin.updateEmployer(t.getRowValue());

                });

        this.tv_ListUsers_cl_Prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        this.tv_ListUsers_cl_Prenom.setCellFactory(TextFieldTableCell.<Employer>forTableColumn());
        tv_ListUsers_cl_Prenom.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, String> t) -> {
                    ((Employer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrenom(t.getNewValue());
                    Admin.updateEmployer(t.getRowValue());
                });
        this.tv_ListUsers_cl_Email.setCellValueFactory(new PropertyValueFactory("email"));
        this.tv_ListUsers_cl_Email.setCellFactory(TextFieldTableCell.<Employer>forTableColumn());
        tv_ListUsers_cl_Email.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, String> t) -> {
                    ((Employer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    Admin.updateEmployer(t.getRowValue());
                });

        this.tv_ListUsers_cl_Adress.setCellValueFactory(new PropertyValueFactory("adress"));
        this.tv_ListUsers_cl_Adress.setCellFactory(TextFieldTableCell.<Employer>forTableColumn());
        tv_ListUsers_cl_Adress.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, String> t) -> {
                    ((Employer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAdress(t.getNewValue());
                    Admin.updateEmployer(t.getRowValue());
                });

        this.tv_ListUsers_cl_Tel.setCellValueFactory(new PropertyValueFactory("tel"));
        this.tv_ListUsers_cl_Tel.setCellFactory(TextFieldTableCell.<Employer, Long>forTableColumn(new LongStringConverter()));
        tv_ListUsers_cl_Tel.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, Long> t) -> {
                    ((Employer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTel(t.getNewValue());
                    Admin.updateEmployer(t.getRowValue());
                });
        this.tv_ListUsers_cl_Salaire.setCellValueFactory(new PropertyValueFactory("salaire"));
        this.tv_ListUsers_cl_Salaire.setCellFactory(TextFieldTableCell.<Employer, Integer>forTableColumn(new IntegerStringConverter()));
        tv_ListUsers_cl_Salaire.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer,Integer > t) -> {
                    ((Employer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSalaire(t.getNewValue());
                    Admin.updateEmployer(t.getRowValue());
                });

    }

    @FXML
    public void SupprimerSecretaire() {
        long O = GetSelecTedRaw(emp);
        System.out.println(O);

        DeleteRaw(O);

        //ExamenDao.SupprimerExamen(O);
        System.out.println("user Deleted Succesfuly");
        listTechnicien(Admin.findAllTechnicien());
        System.out.println("List Updated");
    }

    public long GetSelecTedRaw(Object O) {

        if (O instanceof Employer) {

            TablePosition pos = tableusers.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Employer item = tableusers.getItems().get(row);
            TableColumn col = pos.getTableColumn();

            return item.getCin();

        }

        return 0;

    }

    public void DeleteRaw(long ID) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Selected Row  will be deleted permenatly");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Admin.SupprimerEmployer(ID);
            System.out.println("user deleted Succesfuly");
            listTechnicien(Admin.findAllTechnicien());
            Tableau();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void listTechnicien(List<Employer> users) {
        ObservableList<Employer> medecin = FXCollections.observableArrayList(users);
        this.tableusers.setItems(medecin);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listTechnicien(Admin.findAllTechnicien());

        Tableau();

    }

}

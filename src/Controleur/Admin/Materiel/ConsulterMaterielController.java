/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Materiel;

import Dao.MaterielDao;
import Model.Materiel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConsulterMaterielController implements Initializable {

    @FXML
    private TableView<Materiel> tableMateriel;

    @FXML
    private TableColumn<Materiel, Integer> tv_ListExamen_cl_ID;

    @FXML
    private TableColumn<Materiel, String> tv_ListExamen_cl_Nom;

    @FXML
    private TableColumn<Materiel, String> tv_ListExamen_cl_Description;

    @FXML
    private TableColumn<Materiel, String> tv_ListExamen_cl_Type;

    @FXML
    private TableColumn<Materiel, Integer> tv_ListExamen_cl_Prix;

    @FXML
    private TableColumn<Materiel, String> tv_ListExamen_cl_Statut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listMateriels(MaterielDao.findAllMateriel());
        Tableau();
    }

    public void listMateriels(List<Materiel> materiels) {
        ObservableList<Materiel> materiel = FXCollections.observableArrayList(materiels);
        this.tableMateriel.setItems(materiel);
    }

    public void Tableau() {
        this.tableMateriel.setEditable(true);
        this.tv_ListExamen_cl_ID.setCellValueFactory(new PropertyValueFactory("idMateriel"));
        tv_ListExamen_cl_ID.setEditable(false);

        this.tv_ListExamen_cl_Nom.setCellValueFactory(new PropertyValueFactory("nom"));
        this.tv_ListExamen_cl_Nom.setCellFactory(TextFieldTableCell.<Materiel>forTableColumn());
        tv_ListExamen_cl_Nom.setOnEditCommit(
                (TableColumn.CellEditEvent<Materiel, String> t) -> {
                    ((Materiel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNom(t.getNewValue());
                    MaterielDao.updateMateriel(t.getRowValue());

                });

        this.tv_ListExamen_cl_Description.setCellValueFactory(new PropertyValueFactory("description"));
        this.tv_ListExamen_cl_Description.setCellFactory(TextFieldTableCell.<Materiel>forTableColumn());
        tv_ListExamen_cl_Description.setOnEditCommit(
                (TableColumn.CellEditEvent<Materiel, String> t) -> {
                    ((Materiel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDescription(t.getNewValue());
                    MaterielDao.updateMateriel(t.getRowValue());
                });
        
        
        this.tv_ListExamen_cl_Type.setCellValueFactory(new PropertyValueFactory("Type"));
        this.tv_ListExamen_cl_Type.setCellFactory(ComboBoxTableCell.<Materiel, String>forTableColumn("Analyse", "Test", "Informaique"));

        tv_ListExamen_cl_Type.setOnEditCommit(
                (TableColumn.CellEditEvent<Materiel, String> t) -> {
                    ((Materiel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setType(t.getNewValue());
                    MaterielDao.updateMateriel(t.getRowValue());
                });

        this.tv_ListExamen_cl_Statut.setCellValueFactory(new PropertyValueFactory("Statut"));
        this.tv_ListExamen_cl_Statut.setCellFactory(ComboBoxTableCell.<Materiel, String>forTableColumn("Endommagé", "En Panne", "Nouveau"));
        tv_ListExamen_cl_Statut.setOnEditCommit(
                (TableColumn.CellEditEvent<Materiel, String> t) -> {
                    ((Materiel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setStatut(t.getNewValue());
                    MaterielDao.updateMateriel(t.getRowValue());
                });

        this.tv_ListExamen_cl_Prix.setCellValueFactory(new PropertyValueFactory("Prix"));
        this.tv_ListExamen_cl_Prix.setCellFactory(TextFieldTableCell.<Materiel, Integer>forTableColumn(new IntegerStringConverter()));
        tv_ListExamen_cl_Prix.setOnEditCommit(
                (TableColumn.CellEditEvent<Materiel, Integer> t) -> {
                    ((Materiel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrix(t.getNewValue());
                    MaterielDao.updateMateriel(t.getRowValue());
                });

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Salle;

import Dao.SalleDao;
import Model.Salle;
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

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierSalleController implements Initializable {
@FXML
    private TableView<Salle> tableSalle;

    @FXML
    private TableColumn<Salle, Integer> tv_ListSalle_cl_ID;

    @FXML
    private TableColumn<Salle, String> tv_ListSalle_cl_Nom;

    @FXML
    private TableColumn<Salle, String> tv_ListSalle_cl_Description;

    @FXML
    private TableColumn<Salle, String> tv_ListSalle_cl_TypeSalle;
    
    @FXML
    private TableColumn<Salle, String> tv_ListSalle_cl_Statut;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         listSalles(SalleDao.findAllSalles());
        Tableau();
    }

    public void listSalles(List<Salle> salles) {
        ObservableList<Salle> salle = FXCollections.observableArrayList(salles);
        this.tableSalle.setItems(salle);
    }
  public void Tableau() {
        this.tableSalle.setEditable(true);
        this.tv_ListSalle_cl_ID.setCellValueFactory(new PropertyValueFactory("idSalle"));
        tv_ListSalle_cl_ID.setEditable(false);

        this.tv_ListSalle_cl_Nom.setCellValueFactory(new PropertyValueFactory("nom"));
        this.tv_ListSalle_cl_Nom.setCellFactory(TextFieldTableCell.<Salle>forTableColumn());
        tv_ListSalle_cl_Nom.setOnEditCommit(
                (TableColumn.CellEditEvent<Salle, String> t) -> {
                    ((Salle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNom(t.getNewValue());
                    SalleDao.updateSalle(t.getRowValue());

                });

        this.tv_ListSalle_cl_Description.setCellValueFactory(new PropertyValueFactory("description"));
        this.tv_ListSalle_cl_Description.setCellFactory(TextFieldTableCell.<Salle>forTableColumn());
        tv_ListSalle_cl_Description.setOnEditCommit(
                (TableColumn.CellEditEvent<Salle, String> t) -> {
                    ((Salle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDescription(t.getNewValue());
                    SalleDao.updateSalle(t.getRowValue());
                });
        this.tv_ListSalle_cl_TypeSalle.setCellValueFactory(new PropertyValueFactory("Type"));
        this.tv_ListSalle_cl_TypeSalle.setCellFactory(ComboBoxTableCell.<Salle, String>forTableColumn("Examen", "Consultation", "Réception", "Attente"));

        tv_ListSalle_cl_TypeSalle.setOnEditCommit(
                (TableColumn.CellEditEvent<Salle, String> t) -> {
                    ((Salle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setType(t.getNewValue());
                    SalleDao.updateSalle(t.getRowValue());
                });

        this.tv_ListSalle_cl_Statut.setCellValueFactory(new PropertyValueFactory("Statut"));
        this.tv_ListSalle_cl_Statut.setCellFactory(ComboBoxTableCell.<Salle, String>forTableColumn("Occupé", "Libre", "Travaux"));
        tv_ListSalle_cl_Statut.setOnEditCommit(
                (TableColumn.CellEditEvent<Salle, String> t) -> {
                    ((Salle) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setStatut(t.getNewValue());
                    SalleDao.updateSalle(t.getRowValue());
                });


    }
}

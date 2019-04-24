/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Examen;

import Dao.ExamenDao;
import Model.Examen;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SupprimerExamenController implements Initializable {
@FXML
    private TableView<Examen> tableExamen;

    @FXML
    private TableColumn<Examen, Integer> tv_ListExamen_cl_ID;

    @FXML
    private TableColumn<Examen, String> tv_ListExamen_cl_Nom;

    @FXML
    private TableColumn<Examen, String> tv_ListExamen_cl_Description;

    @FXML
    private TableColumn<Examen, String> tv_ListExamen_cl_TypeExamen;

    @FXML
    private TableColumn<Examen, String> tv_ListExamen_cl_Resultat;

    @FXML
    private TableColumn<Examen, Date> tv_ListExamen_cl_Date;
    
    Examen examen =new Examen();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listExamens(ExamenDao.findAllExamen());
        Tableau();
    }

    public void listExamens(List<Examen> examens) {
        ObservableList<Examen> examen = FXCollections.observableArrayList(examens);
        this.tableExamen.setItems(examen);
    }

    public void Tableau() {
        this.tableExamen.setEditable(true);
        this.tv_ListExamen_cl_ID.setCellValueFactory(new PropertyValueFactory("idExamen"));
        tv_ListExamen_cl_ID.setEditable(false);

        this.tv_ListExamen_cl_Nom.setCellValueFactory(new PropertyValueFactory("nom"));
        this.tv_ListExamen_cl_Nom.setCellFactory(TextFieldTableCell.<Examen>forTableColumn());
        tv_ListExamen_cl_Nom.setOnEditCommit(
                (TableColumn.CellEditEvent<Examen, String> t) -> {
                    ((Examen) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNom(t.getNewValue());
                    ExamenDao.updateExamen(t.getRowValue());

                });

        this.tv_ListExamen_cl_Description.setCellValueFactory(new PropertyValueFactory("description"));
        this.tv_ListExamen_cl_Description.setCellFactory(TextFieldTableCell.<Examen>forTableColumn());
        tv_ListExamen_cl_Description.setOnEditCommit(
                (TableColumn.CellEditEvent<Examen, String> t) -> {
                    ((Examen) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDescription(t.getNewValue());
                    ExamenDao.updateExamen(t.getRowValue());
                });
        this.tv_ListExamen_cl_TypeExamen.setCellValueFactory(new PropertyValueFactory("TypeExamen"));
        //this.tv_ListExamen_cl_TypeExamen.setCellFactory(TextFieldTableCell.<Examen>forTableColumn());
        this.tv_ListExamen_cl_TypeExamen.setCellFactory(ComboBoxTableCell.<Examen, String>forTableColumn("NFS", "BILAN", "CARDIO"));

        tv_ListExamen_cl_TypeExamen.setOnEditCommit(
                (TableColumn.CellEditEvent<Examen, String> t) -> {
                    ((Examen) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTypeExamen(t.getNewValue());
                    ExamenDao.updateExamen(t.getRowValue());
                });

        this.tv_ListExamen_cl_Resultat.setCellValueFactory(new PropertyValueFactory("Resultat"));
        this.tv_ListExamen_cl_Resultat.setCellFactory(TextFieldTableCell.<Examen>forTableColumn());
        tv_ListExamen_cl_Resultat.setOnEditCommit(
                (TableColumn.CellEditEvent<Examen, String> t) -> {
                    ((Examen) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setResultat(t.getNewValue());
                    ExamenDao.updateExamen(t.getRowValue());
                });

        this.tv_ListExamen_cl_Date.setCellValueFactory(new PropertyValueFactory("DateExamen"));

        tv_ListExamen_cl_Date.setCellFactory(column -> {
            TableCell<Examen, Date> cell = new TableCell<Examen, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });

    }
    public int GetSelecTedRaw(Object O) {

        if (O instanceof Examen) {

            TablePosition pos = tableExamen.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Examen item = tableExamen.getItems().get(row);
            TableColumn col = pos.getTableColumn();

            return item.getIdExamen();

        }

        return 0;

    }
    @FXML
    public void btn_deleteExamen() throws SQLException {
        int O = GetSelecTedRaw(examen);
        System.out.println(O);

        DeleteRaw(examen.getIdExamen());
        ExamenDao.SupprimerExamen(O);
        System.out.println("examen Deleted Succesfuly");
        listExamens(ExamenDao.findAllExamen());
        System.out.println("List Updated");

    }
     public void DeleteRaw(int ID)  {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Selected Row  will be deleted permenatly");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ExamenDao.SupprimerExamen(ID);
            System.out.println("Hotel tfassa5 Succesfuly");

        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}
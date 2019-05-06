/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Salle;

import Dao.CalculDao;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConsulerDepenseController implements Initializable {

    
    
    @FXML
    private JFXTextField prixMachine;
    @FXML
    private JFXTextField salaire;
    
      @FXML
    private JFXTextField total;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int y = 0, x = 0;
    for (int i : CalculDao.ListePrixMateriel()) {
            y = y + i;
            prixMachine.setEditable(false);       
            prixMachine.setText(String.valueOf(y)+" DT");
        }
        
        for (int i : CalculDao.ListeSalaire()) {
            x = x + i;
            salaire.setEditable(false);       
            salaire.setText(String.valueOf(x)+" DT");
        }  
        int o=x+y;
    total.setText(String.valueOf(o)+" DT");

    }
}

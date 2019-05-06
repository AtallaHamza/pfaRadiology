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
public class ConsulterRevenueController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField nombre;/*
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int  d = 0;
        for (int i : CalculDao.ListePrixExamen()) {
            d = d + i;
            nom.setEditable(false);       
            nom.setText(String.valueOf(d)+" DT");
            nombre.setEditable(false);       
            nombre.setText(String.valueOf(CalculDao.ListePrixExamen().size())+"");
        }
        
    }

}

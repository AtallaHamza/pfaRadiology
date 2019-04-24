/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Model.Admin;
import Model.Employer;
import static com.sun.deploy.util.ReflectionUtil.getClass;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class PFA2019 extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
       this.stage =primaryStage;
        try {
            Scene scene = new Scene((AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("View/FXML.fxml")));
            stage.setScene(scene);
            stage.setTitle("Authentification");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PFA2019.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Stage getStage()
    {
        return stage;
    }
   /* public static void changeStage(String place) throws IOException{
        this.stage.setScene(new Scene());
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

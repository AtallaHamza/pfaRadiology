/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class Connexion {

    private static Connection conn;
    private Properties properties;
    private static Connexion instance;
    private Connection connection;
    private String url;
    private String login;
    private String password;
    public static Connection connect() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testf", "root", "hamza");
            } catch (ClassNotFoundException e) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, e);
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public Connexion() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("configuration.properties")));
            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;

    }

    public Connection getConnection() {
        return connection;
    }
}

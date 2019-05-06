/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class CalculDao {

    public static List<Integer> ListeSalaire() {

        List<Integer> listeSalaire = new ArrayList<>();

        String requete = "select salaire from users";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                listeSalaire.add(resultat.getInt(1));
            }

            return listeSalaire;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Examens " + ex.getMessage());
            return null;
        }
    }

    public static List<Integer> ListePrixMateriel() {

        List<Integer> listePrixMateriel = new ArrayList<>();

        String requete = "select Prix from Materiel";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                listePrixMateriel.add(resultat.getInt(1));
            }

            return listePrixMateriel;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des  Prix Materiel " + ex.getMessage());
            return null;
        }
    }

    public static List<Integer> ListePrixExamen() {

        List<Integer> listePrixMateriel = new ArrayList<>();

        String requete = "select Prix from Examen";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                listePrixMateriel.add(resultat.getInt(1));
            }

            return listePrixMateriel;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des  Prix Examen " + ex.getMessage());
            return null;
        }
    }
    /*public static int NombreExamen() {

        List<Integer> listePrixMateriel = new ArrayList<>();
              
        String requete = "select count(*) from Examen";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                
                listePrixMateriel.add(resultat.getInt(1));
            }

            return listePrixMateriel;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des  Prix Examen " + ex.getMessage());
            return null;
        }
    }*/

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.Salle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class SalleDao {
    
    
    public static List<Salle> findAllSalles() {

        List<Salle> listeExamens= new ArrayList<>();
        String requete = "select * from Salle ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Salle s = new Salle();
                s.setIdSalle(resultat.getInt(1));
                s.setNom(resultat.getString(2));
                s.setDescription(resultat.getString(3));
                s.setType(resultat.getString(4));
                s.setStatut(resultat.getString(5));
      

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Examens " + ex.getMessage());
            return null;
        }
    }
    
    public static Salle TrouverSalle(int id) {
        String requete = "select * from Salle where id=?";
        Salle s = new Salle();
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setLong(1, id);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                s.setIdSalle(resultat.getInt(1));
                s.setNom(resultat.getString(2));
                s.setDescription(resultat.getString(3));
                s.setType(resultat.getString(4));
                s.setStatut(resultat.getString(5));
      

            }
            return s;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche de la Salle id "+id+ " " + ex.getMessage());
            return null;
        }
    }
    
     public static void updateSalle(Salle st) {
        String requete = "UPDATE `Salle` SET `nom`=?,`Description`=?,`Type`=?,`Statut`=? WHERE idSalle=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, st.getNom());
            ps.setString(2, st.getDescription());
            ps.setString(3, st.getType());
            ps.setString(4, st.getStatut());
            ps.setInt(5, st.getIdSalle());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  Salle" + ex.getMessage());

        }
    }
}

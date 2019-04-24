/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.Examen;
import Model.Rendu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class RenduDao {

    public static boolean AjouterRendu(Rendu rendu) {

        long millis = System.currentTimeMillis();

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().
                    prepareStatement("INSERT INTO Rendu (dateRendu, heure, commentaire, resultat, idPatient, idExamen) VALUES (? ,? ,? ,? ,?,?)");

            pstm.setDate(1, new java.sql.Date(millis));
            pstm.setString(2, rendu.getHeure());
            pstm.setString(3, rendu.getCommentaire());
            pstm.setString(4, rendu.getResultat());
            pstm.setInt(5, rendu.getIdPatient());
            pstm.setInt(6, rendu.getIdExamen());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println("AjouterRendu() " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean SupprimerRendu(int id) {
        boolean r = false;

        String req = "DELETE FROM Rendu WHERE idRendu = ?";

        try {

            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(req);

            pstm.setInt(1, id);

            if (pstm.executeUpdate() == 0) {
                r = false;
            } else {
                r = true;
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du Rendu " + ex.getMessage());
        }
        return r;
    }
    
    
    public static List<Rendu> findAllRendu() {

        List<Rendu> listeExamens= new ArrayList<>();
        String requete = "select * from Rendu ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Rendu s = new Rendu();
                s.setIdRendu(resultat.getInt(1));
                s.setDateRendu(resultat.getDate(2));
                s.setHeure(resultat.getString(3));
                s.setCommentaire(resultat.getString(4));
                s.setResultat(resultat.getString(5));
                s.setIdPatient(resultat.getInt(6));
                s.setIdExamen(resultat.getInt(7));

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Rendu " + ex.getMessage());
            return null;
        }
    }
    
      public static void updateRendu(Rendu st) {
        String requete = "UPDATE `Rendu` SET `heure`=?,`commentaire`=?`Resultat`=? ,`Prix`=? WHERE idRendu=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, st.getHeure());
            ps.setString(2, st.getCommentaire());
            ps.setString(3, st.getResultat());
            ps.setInt(4, st.getIdRendu());


            ps.executeUpdate();
            System.out.println("Mise à jour DU Rendu effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  Rendu" + ex.getMessage());

        }
    }
      
      
      public static List<Rendu> findAllRenduByPatientId(int id) {

        List<Rendu> listeExamens = new ArrayList<>();
        String requete = "select * from Rendu where idPatient=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Rendu s = new Rendu();
                 s.setIdRendu(resultat.getInt(1));
                s.setDateRendu(resultat.getDate(2));
                s.setHeure(resultat.getString(3));
                s.setCommentaire(resultat.getString(4));
                s.setResultat(resultat.getString(5));
                s.setIdPatient(resultat.getInt(6));
                s.setIdExamen(resultat.getInt(7));

                listeExamens.add(s);
            }
                        System.out.println("findAllRenduByPtientId ");

            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Examens " + ex.getMessage());
            return null;
        }
    }
}

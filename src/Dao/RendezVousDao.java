/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.RendezVous;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class RendezVousDao {

    public static boolean AjouterRendezVous(RendezVous R) {
        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement("INSERT INTO RendezVous (dateRendezVous, heure,idPatient) VALUES (?,? ,? )");
            pstm.setDate(1, R.getDateRendezVous());
            pstm.setString(2, R.getHeure());
            pstm.setInt(3, R.getIdPatient());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static List<RendezVous> findAllRendezVous() {

        List<RendezVous> listeExamens = new ArrayList<>();
        String requete = "select * from RendezVous ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                RendezVous s = new RendezVous();
                s.setIdRendezVous(resultat.getInt(1));
                s.setDateRendezVous(resultat.getDate(2));
                s.setHeure(resultat.getString(3));

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des RendezVous " + ex.getMessage());
            return null;
        }
    }

    public static boolean SupprimerRendezVous(int id) {
        boolean r = false;

        String req = "DELETE FROM RendezVous WHERE idRendezVous = ?";

        try {

            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(req);

            pstm.setInt(1, id);

            if (pstm.executeUpdate() == 0) {
                r = false;

            } else {
                r = true;

            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        
        return r;
    }

    public static List<RendezVous> findAllRendezVousByPtientId(int id) {

        List<RendezVous> listeExamens = new ArrayList<>();
        String requete = "select * from RendezVous where idPatient=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                RendezVous s = new RendezVous();
                s.setIdRendezVous(resultat.getInt(1));
                s.setDateRendezVous(resultat.getDate(2));
                s.setHeure(resultat.getString(3));

                listeExamens.add(s);
            }
                        System.out.println("findAllRendezVousByPtientId ");

            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Examens " + ex.getMessage());
            return null;
        }
    }

    public static void updateRendezVous(RendezVous st) {
        String requete = "UPDATE `RendezVous` SET `heure`=? WHERE idRendezVous=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setString(1, st.getHeure());
            ps.setInt(2, st.getIdRendezVous());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  RendezVous" + ex.getMessage());
        }
    }

}

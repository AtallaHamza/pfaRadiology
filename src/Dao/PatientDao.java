/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.Examen;
import Model.FichePatient;
import Model.Patient;
import Model.RendezVous;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class PatientDao {

    public static boolean AjouterPatient(Patient patient) {

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement("INSERT INTO patient (nom, prenom, genre, tel, date) VALUES (? ,? ,? ,? ,?)");
            pstm.setString(1, patient.getNom());
            pstm.setString(2, patient.getPrenom());
            pstm.setString(3, patient.getGenre());
            pstm.setLong(4, patient.getTel());
            pstm.setDate(5, patient.getDate());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean AjouterFichePatient(Patient patient, Examen examen) {
        long millis = System.currentTimeMillis();

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement("INSERT INTO FichePatient (idPatient, idExamen, dateFiche) VALUES (? ,? ,? )");
            pstm.setInt(1, patient.getIdPatient());
            pstm.setInt(2, examen.getIdExamen());
            pstm.setDate(5, new java.sql.Date(millis));
            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println("new FichePatient error" + e.getMessage());
            return false;
        }
        return true;
    }

    public static void updatePatient(Patient st) {
        String requete = "UPDATE `Patient` SET `nom`=?,`prenom`=?,`genre`=?,`tel`=? ,`date`=? WHERE idPatient=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, st.getNom());
            ps.setString(2, st.getPrenom());
            ps.setString(3, st.getGenre());
            ps.setDate(5, st.getDate());
            ps.setLong(4, st.getTel());
            ps.setInt(6, st.getIdPatient());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  Examen" + ex.getMessage());

        }
    }

    public static boolean SupprimerPatient(int id) {
        boolean r = false;

        String req = "DELETE FROM Patient WHERE idPatient = ?";
        String req2 = "DELETE FROM RendezVous WHERE idRendezVous = ?";

        try {

            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(req);
            PreparedStatement pstm2 = Connexion.getInstance().getConnection().prepareStatement(req2);

            pstm.setInt(1, id);
            if (pstm.executeUpdate() == 0) {
                r = false;
                System.out.println("suppression des P FLSR ");

            } else {
                r = true;
                System.out.println("suppression des P TRUE ");

                RendezVousDao.findAllRendezVousByPtientId(id).forEach((rendez) -> {
                    System.out.println("RENDEZ " + rendez.getHeure());
                    try {
                        pstm2.setInt(1, rendez.getIdRendezVous());
                        pstm2.executeUpdate();

                    } catch (SQLException ex) {
                        Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //RendezVousDao.SupprimerRendezVous(rendez.getIdPatient());
                });
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        return r;
    }

    public static Patient findPatientById(int id) {
        Patient examen = new Patient();
        String requete = "select * from Patient where idPatient=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                examen.setIdPatient(resultat.getInt(1));
                examen.setNom(resultat.getString(2));
                examen.setPrenom(resultat.getString(3));

            }
            return examen;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du l'Examen id " + ex.getMessage());
            return null;
        }
    }

    public static List<Patient> findAllPatient() {

        List<Patient> listeExamens = new ArrayList<>();
        String requete = "select * from Patient ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Patient s = new Patient();
                s.setIdPatient(resultat.getInt(1));
                s.setNom(resultat.getString(2));
                s.setPrenom(resultat.getString(3));
                s.setGenre(resultat.getString(4));
                s.setTel(resultat.getLong(5));
                s.setDate(resultat.getDate(6));

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Patients " + ex.getMessage());
            return null;
        }
    }

    public static List<FichePatient> findAllFichePatient(int idP) {

        List<FichePatient> listeExamens = new ArrayList<>();
        String requete = "select * from FichePatient where idPatient =? ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setInt(1, idP);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                FichePatient s = new FichePatient();
                s.setIdPatient(resultat.getInt(1));
                s.setIdExamen(resultat.getInt(2));
                s.setDateFiche(resultat.getDate(3));

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des FichePatient " + ex.getMessage());
            return null;
        }
    }
}

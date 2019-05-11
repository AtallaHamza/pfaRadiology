/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.Examen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class ExamenDao {
static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");  

    public static boolean AjouterExamen(Examen examen ) {
     
        long millis=System.currentTimeMillis();  

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().
                    prepareStatement("INSERT INTO Examen (nom, description, typeExamen, resultat, dateExamen, prix) VALUES (? ,? ,? ,? ,?,?)");
            pstm.setString(1, examen.getNom());
            pstm.setString(2, examen.getDescription());
            pstm.setString(3, examen.getTypeExamen());
            pstm.setString(4, examen.getResultat());
            pstm.setDate(5,new java.sql.Date(millis));
            pstm.setInt(6,examen.getPrix());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean SupprimerExamen(int id){
        boolean r = false;
        
        String req = "DELETE FROM Examen WHERE idExamen = ?";

        try {
        
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(req);

            pstm.setInt(1, id);
        
        if (pstm.executeUpdate() == 0) {
             r=false;
        } else {
            r= true;
        }
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        return r;
    }
    public static List<Examen> findAllExamen() {

        List<Examen> listeExamens= new ArrayList<>();
        String requete = "select * from Examen ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Examen s = new Examen();
                s.setIdExamen(resultat.getInt(1));
                s.setNom(resultat.getString(2));
                s.setDescription(resultat.getString(3));
                s.setTypeExamen(resultat.getString(4));
                s.setResultat(resultat.getString(5));
                s.setDateExamen(resultat.getDate(6));

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Examens " + ex.getMessage());
            return null;
        }
    }
    
      public static void updateExamen(Examen st) {
        String requete = "UPDATE `Examen` SET `nom`=?,`Description`=?,`TypeExamen`=?,`Resultat`=? ,`Prix`=? WHERE idExamen=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, st.getNom());
            ps.setString(2, st.getDescription());
            ps.setString(3, st.getTypeExamen());
            ps.setString(4, st.getResultat());
            ps.setInt(5, st.getPrix());
            ps.setInt(6, st.getIdExamen());


            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  Examen" + ex.getMessage());

        }
    }
      
      public static Examen findExamenById(int id) {
        Examen examen = new Examen();
        String requete = "select * from Examen where idExamen=?";
        try {
            PreparedStatement ps =  Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                examen.setIdExamen(resultat.getInt(1));      
                examen.setNom(resultat.getString(2));       
                examen.setResultat(resultat.getString(5));
                examen.setDateExamen(resultat.getDate(6));


              
            }
            return examen;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du l'Examen id " + ex.getMessage());
            return null;
        }
    }
}

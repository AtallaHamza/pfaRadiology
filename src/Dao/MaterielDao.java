/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.Materiel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class MaterielDao {
    
    
    public static boolean AjouterMateriel(Materiel materiel ) {
     
        long millis=System.currentTimeMillis();  

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().
                    prepareStatement("INSERT INTO Materiel (nom, description, type, Statut, prix) VALUES (? ,? ,? ,? ,?)");
            pstm.setString(1, materiel.getNom());
            pstm.setString(2, materiel.getDescription());
            pstm.setString(3, materiel.getType());
            pstm.setString(4, "Nouveau");
            pstm.setInt(5,materiel.getPrix());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public static List<Materiel> findAllMateriel() {

        List<Materiel> listeMateriel= new ArrayList<>();
        String requete = "select * from Materiel  ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Materiel s = new Materiel();
                s.setIdMateriel(resultat.getInt(1));
                s.setNom(resultat.getString(2));
                s.setDescription(resultat.getString(3));
                s.setType(resultat.getString(4));
                s.setPrix(resultat.getInt(5));
                s.setStatut(resultat.getString(6));

                listeMateriel.add(s);
            }
            return listeMateriel;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des listeMateriel " + ex.getMessage());
            return null;
        }
    }
    
      public static void updateMateriel(Materiel materiel ) {
        String requete = "UPDATE `Materiel` SET `nom`=?,`Description`=?,`Type`=?,`Statut`=? ,`Prix`=? WHERE idMateriel=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, materiel.getNom());
            ps.setString(2, materiel.getDescription());
            ps.setString(3, materiel.getType());
            ps.setString(4, materiel.getStatut());
            ps.setInt(5, materiel.getPrix());
            ps.setInt(6, materiel.getIdMateriel());


            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  Examen" + ex.getMessage());

        }
    }
      
      public static Materiel findMaterielById(int id) {
        Materiel examen = new Materiel();
        String requete = "select * from Materiel where idMateriel=?";
        try {
            PreparedStatement ps =  Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                examen.setIdMateriel(resultat.getInt(1));
              
            }
            return examen;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du l'Materiel id " + ex.getMessage());
            return null;
        }
    }
}

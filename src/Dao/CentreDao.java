/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controleur.Connexion;
import Model.Centre;
import Model.Examen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author asus
 */
public class CentreDao {
    
    public static boolean AjouterCentre(Centre centre ) {
     
        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().
                    prepareStatement("INSERT INTO Centre (nom, adress, email, fax, tel) VALUES (? ,? ,? ,? ,?)");
            pstm.setString(1, centre.getNom());
            pstm.setString(2, centre.getAdress());
            pstm.setString(3, centre.getEmail());
            pstm.setLong(4, centre.getFax());
            pstm.setLong(5,centre.getTel());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void updateCentre(Centre centre, int centreId) {
        String requete = "UPDATE `Centre` SET `nom`=?,`adress`=?,`email`=?,`fax`=? ,`tel`=? WHERE idCentre=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, centre.getNom());
            ps.setString(2, centre.getAdress());
            ps.setString(3, centre.getEmail());
            ps.setLong(4, centre.getFax());
            ps.setLong(5, centre.getTel());
            ps.setInt(6, centreId);


            ps.executeUpdate();
            System.out.println("Mise à jour du centre effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour  centre" + ex.getMessage());

        }
    }
      
    public static List<Centre> findAllCentre() {

        List<Centre> listeExamens= new ArrayList<>();
        String requete = "select * from Centre ";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Centre s = new Centre();
                s.setIdCentre(resultat.getInt(1));
                s.setNom(resultat.getString(2));
                s.setAdress(resultat.getString(3));
                s.setEmail(resultat.getString(4));
                s.setFax(resultat.getLong(5));
                s.setTel(resultat.getLong(6));

                listeExamens.add(s);
            }
            return listeExamens;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement du Centre " + ex.getMessage());
            return null;
        }
    }

}

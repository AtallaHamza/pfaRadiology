/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controleur.Connexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class Admin extends Employer {

    private String login;
    private String pass;

    public Admin() {
    }

    public Admin(long cin, String nom, String prenom, String email, String adress, Date date, long tel, String login, String pass) {
        super(cin, nom, prenom, email, adress, date, tel);
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //private static Connection connection;
    public static Employer TrouverEmployer(long cin) {
        String requete = "select * from users where cin=?";
        Employer emp = new Employer();
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setLong(1, cin);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                emp.setCin(resultat.getLong(1));
                emp.setNom(resultat.getString(2));
                emp.setPrenom(resultat.getString(3));
                emp.setEmail(resultat.getString(5));
                emp.setTel(resultat.getInt(6));
                emp.setDate(resultat.getDate(7));
                emp.setAdress(resultat.getString(9));

            }
            return emp;
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du l'utilisateur id " + ex.getMessage());
            return null;
        }
    }

    public static boolean AjouterEmployer(Employer emp) {
        String t = null;

        switch (emp.getClass().toString()) {
            case "class Model.Secretaire":
                t = "S";
                break;
            case "class Model.Medecin":
                t = "M";
                break;
            case "class Model.Technicien":
                t = "T";
                break;
        }

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement("INSERT INTO users (cin,nom,prenom,t,date,  tel,email,salaire,adress) VALUES (? ,? ,? ,? ,? ,? ,? ,?,?)");
            pstm.setLong(1, emp.getCin());
            pstm.setString(2, emp.getNom());
            pstm.setString(3, emp.getPrenom());
            pstm.setString(4, t);
            pstm.setDate(5, emp.getDate());
            pstm.setLong(6, emp.getTel());
            pstm.setString(7, emp.getEmail());
            pstm.setInt(8, emp.getSalaire());
            pstm.setString(9, emp.getAdress());
            pstm.executeUpdate();

            pstm = Connexion.getInstance().getConnection().prepareStatement("INSERT INTO compte ( cin, login, PASSWORD, role)  VALUES (? , ?, ?, ?)");
            pstm.setLong(1, emp.getCin());
            pstm.setString(2, emp.getLogin());
            pstm.setString(3, emp.getMotdepasse());
            pstm.setString(4, t);

            pstm.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean SupprimerEmployer(long id) {
        boolean r = false;
        String req = "DELETE FROM users WHERE cin = ?";
        String req2 = "DELETE FROM compte WHERE cin = ?";

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(req);

            pstm.setLong(1, id);
            if (pstm.executeUpdate() == 0) {
                r = false;
                System.out.println("erreur de suppression ");

            } else {
                r = true;
                PreparedStatement pstm2 = Connexion.getInstance().getConnection().prepareStatement(req2);
                pstm2.setLong(1, id);
                pstm2.executeUpdate();
                System.out.println(" suppression ");

            }
        } catch (SQLException ex) {
            System.out.println("erreur catched lors de la suppression " + ex.getMessage());
        }
        return r;

    }

    public static boolean CheckCin(long cin) {
        boolean r = false;
        String req = "Select * From users WHERE cin = ?";

        try {
            PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(req);

            pstm.setLong(1, cin);
            ResultSet resultat = pstm.executeQuery();

            if (resultat.next()) {
                r = true;
                System.out.println(" found ");


            } else {
                r = false;
                System.out.println("not found ");

            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche " + ex.getMessage());
        }
        return r;

    }

    public static List<Employer> findAllSecretaire() {

        List<Employer> listeusers = new ArrayList<>();
        String requete = "select * from users where t=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setString(1, "S");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Employer s = new Employer();
                s.setCin(resultat.getLong(1));
                s.setNom(resultat.getString(2));
                s.setPrenom(resultat.getString(3));
                s.setEmail(resultat.getString(5));
                s.setTel(resultat.getLong(6));
                s.setDate(resultat.getDate(7));
                s.setAdress(resultat.getString(9));

                listeusers.add(s);
            }
            return listeusers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des seretqire " + ex.getMessage());
            return null;
        }
    }

    public static List<Employer> findAllDoctors() {

        List<Employer> listeusers = new ArrayList<>();
        String requete = "select * from users where t=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setString(1, "M");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Employer s = new Employer();
                s.setCin(resultat.getLong(1));
                s.setNom(resultat.getString(2));
                s.setPrenom(resultat.getString(3));
                s.setEmail(resultat.getString(5));
                s.setTel(resultat.getLong(6));
                s.setDate(resultat.getDate(7));
                s.setAdress(resultat.getString(9));

                listeusers.add(s);
            }
            return listeusers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des seretqire " + ex.getMessage());
            return null;
        }
    }

    public static List<Employer> findAllTechnicien() {

        List<Employer> listeusers = new ArrayList<>();
        String requete = "select * from users where t=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);
            ps.setString(1, "T");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Employer s = new Employer();
                s.setCin(resultat.getLong(1));
                s.setNom(resultat.getString(2));
                s.setPrenom(resultat.getString(3));
                s.setEmail(resultat.getString(5));
                s.setTel(resultat.getLong(6));
                s.setDate(resultat.getDate(7));
                s.setAdress(resultat.getString(9));

                listeusers.add(s);
            }
            return listeusers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des seretqire " + ex.getMessage());
            return null;
        }
    }

    public static void updateEmployer(Employer st) {
//          LocationDAO ld=new LocationDAO();
        String requete = "UPDATE `users` SET `nom`=?,`prenom`=?,`email`=?,`adress`=?,`tel`=? ,`salaire`=? WHERE cin=?";
        try {
            PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(requete);

            ps.setString(1, st.getNom());
            ps.setString(2, st.getPrenom());
            ps.setString(3, st.getEmail());
            ps.setString(4, st.getAdress());
            ps.setLong(5, st.getTel());
            ps.setInt(6, st.getSalaire());

            ps.setLong(7, st.getCin());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour du user" + ex.getMessage());

        }
    }

    public boolean ModifierEmployer() {
        return false;
    }

    public boolean ConsulterEmployer(long id) throws SQLException {
        return false;

    }
}

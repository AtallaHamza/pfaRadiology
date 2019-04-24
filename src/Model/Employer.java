/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Employer {
    
    private long cin;
    private String nom;
    private String prenom;
    private String email;
    private String adress;
    private Date date;
    private long tel;
    private int salaire;

    private String login;
    private String motdepasse;

    public Employer() {
    }

    public Employer(long cin, String nom, String prenom, String email, String adress, Date date, long tel, int salaire) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adress = adress;
        this.date = date;
        this.tel = tel;
        this.salaire = salaire;
    }
    
    public Employer(long cin, String nom, String prenom, String email,String adress, Date date, long tel) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adress = adress;
        this.date = date;
        this.tel = tel;
    }

    public Employer(long cin, String nom, String prenom, String email, String adress, Date date, long tel, String login, String motdepasse) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adress = adress;
        this.date = date;
        this.tel = tel;
        this.login = login;
        this.motdepasse = motdepasse;
    }

    public Employer(long cin, String nom, String prenom, String email, String adress, Date date, long tel, int salaire, String login, String motdepasse) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adress = adress;
        this.date = date;
        this.tel = tel;
        this.salaire = salaire;
        this.login = login;
        this.motdepasse = motdepasse;
    }
    

    public long getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdress() {
        return adress;
    }

    public Date getDate() {
        return date;
    }

    public long getTel() {
        return tel;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public void setCin(long cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
    
    
    @Override
    public String toString() {
        return "Employer{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adress=" + adress + ", date=" + date + ", tel=" + tel + ", salaire=" + salaire + '}';
    }
    
}

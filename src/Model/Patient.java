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
public class Patient {
    private int idPatient;
    private String nom;
    private String prenom;
    private String genre;
    private long tel;
    private Date date;
    //private int idFcihe;

    public Patient() {
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public Patient(String nom, String prenom, String genre, long tel, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.tel = tel;
        this.date = date;
    }

    
    
    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    /*
    public int getIdFcihe() {
        return idFcihe;
    }

    public void setIdFcihe(int idFcihe) {
        this.idFcihe = idFcihe;
    }*/

    @Override
    public String toString() {
        return  prenom + " "+nom ;
    }

    
    

    

    
    
}

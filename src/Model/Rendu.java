/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class Rendu {
    private int idRendu;
    private Date dateRendu;
    private String heure ;
    private String commentaire ;
    private String resultat ;
    private int idPatient;
    public int idExamen;

    public int getIdRendu() {
        return idRendu;
    }

    public void setIdRendu(int idRendu) {
        this.idRendu = idRendu;
    }

    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public Rendu(String heure, String commentaire, String resultat, int idPatient, int idExamen) {
        this.heure = heure;
        this.commentaire = commentaire;
        this.resultat = resultat;
        this.idPatient = idPatient;
        this.idExamen = idExamen;
    }

    public Rendu() {
    }
    
    

    
}

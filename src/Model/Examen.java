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
public class Examen {
    public int idExamen;
    public String Nom;
    public String Description;
    public String TypeExamen;
    public String Resultat;
    public Date DateExamen;
    public int Prix;

    

    public Examen() {
    }

    
    public Examen(String Nom, String Description, String TypeExamen, String Resultat, int Prix) {
        this.Nom = Nom;
        this.Description = Description;
        this.TypeExamen = TypeExamen;
        this.Resultat = Resultat;
        this.Prix = Prix;

    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTypeExamen() {
        return TypeExamen;
    }

    public void setTypeExamen(String TypeExamen) {
        this.TypeExamen = TypeExamen;
    }

    public String getResultat() {
        return Resultat;
    }

    public void setResultat(String Resultat) {
        this.Resultat = Resultat;
    }

    public Date getDateExamen() {
        return DateExamen;
    }

    public void setDateExamen(Date DateExamen) {
        this.DateExamen = DateExamen;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    @Override
    public String toString() {
        return  Nom + " "+TypeExamen +" "+Resultat ;
    }
    
    
}

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
public class FichePatient {
    private int idFichePatient;
    private int idPatient;
    private int idExamen;
    private Date dateFiche;

    public int getIdFichePatient() {
        return idFichePatient;
    }

    public void setIdFichePatient(int idFichePatient) {
        this.idFichePatient = idFichePatient;
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

    public Date getDateFiche() {
        return dateFiche;
    }

    public void setDateFiche(Date dateFiche) {
        this.dateFiche = dateFiche;
    }
    
    
}

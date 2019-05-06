/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author asus
 */
public class Centre {
    private int idCentre;
    private String nom;
    private String adress;
    private String email;
    private long fax;
    private long tel;

    public int getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getFax() {
        return fax;
    }

    public void setFax(long fax) {
        this.fax = fax;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public Centre() {
    }

    public Centre(String nom, String adress, String email, long fax, long tel) {
        this.nom = nom;
        this.adress = adress;
        this.email = email;
        this.fax = fax;
        this.tel = tel;
    }
    
    
    
}

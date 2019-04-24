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
public class Medecin extends Employer{

    public Medecin(long cin, String nom, String prenom, String email, String adress, Date date, long tel) {
        super(cin, nom, prenom, email, adress, date, tel);
    }

    public Medecin(long cin, String nom, String prenom, String email, String adress, Date date, long tel, int salaire) {
        super(cin, nom, prenom, email, adress, date, tel, salaire);
    }

    public Medecin(long cin, String nom, String prenom, String email, String adress, Date date, long tel, int salaire, String login, String motdepasse) {
        super(cin, nom, prenom, email, adress, date, tel, salaire, login, motdepasse);
    }

    
    
}

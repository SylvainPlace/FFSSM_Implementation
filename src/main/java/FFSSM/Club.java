/**
 * @(#) Club.java
 */
package FFSSM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Club {

    public Moniteur president;

    public String nom;

    public String adresse;

    public String telephone;

    private ArrayList<Plongee> lesPlongees;

    public Club(Moniteur president, String nom, String telephone) {
        this.president = president;
        this.nom = nom;
        this.telephone = telephone;
        lesPlongees = new ArrayList<>();
    }

    /**
     * Calcule l'ensemble des plongées non conformes organisées par ce club. Une
     * plongée est conforme si tous les plongeurs de la palanquée ont une
     * licence valide à la date de la plongée
     *
     * @return l'ensemble des plongées non conformes
     */
    public Set<Plongee> plongeesNonConformes() {
        HashSet<Plongee> plongeesNonConformes = new HashSet<>();
        for (Plongee p : lesPlongees) {
            if (!p.estConforme()) {
                plongeesNonConformes.add(p);
            }
        }
        return plongeesNonConformes;
    }

    /**
     * Enregistre une nouvelle plongée organisée par ce club
     *
     * @param p la nouvelle plongée
     */
    public void organisePlongee(Plongee p) {
        lesPlongees.add(p);
    }

    public Moniteur getPresident() {
        return president;
    }

    public void setPresident(Moniteur president) {
        this.president = president;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }
    
    

}

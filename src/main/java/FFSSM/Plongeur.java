package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne {

    private int niveau;
    private ArrayList<Licence> lesLicences;

    public Plongeur(int niveau, String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        this(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau = niveau;
    }

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        lesLicences = new ArrayList<>();
    }

    public void ajouterLicence(String numero, LocalDate delivrance) {
        lesLicences.add(new Licence(this, numero, delivrance));
    }

    public Licence DerniereLicence() {
        if (lesLicences.isEmpty()) {
            return null;
        }
        return lesLicences.get(lesLicences.size() - 1);
    }
}

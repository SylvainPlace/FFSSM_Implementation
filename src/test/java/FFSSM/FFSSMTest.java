/*
 */
package FFSSM;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SylvainPlc
 */
public class FFSSMTest {

    LocalDate date;
    Moniteur m1;
    Moniteur m2;
    Moniteur m3;
    Plongee p;
    Club c;

    public FFSSMTest() {
    }

    @BeforeEach
    public void setUp() {
        date = LocalDate.of(2020, Month.MARCH, 1);
        m1 = new Moniteur("147E", "Jeanne", "Jean", "8 rue de la ferme", "06 67 89 01 23", date, 1);
        m2 = new Moniteur("147F", "Mark", "Marc", "8 rue de la ferme", "06 67 89 01 24", date, 2);
        m3 = new Moniteur("147G", "Edouardo", "Edouard", "8 rue de la ferme", "06 67 89 01 25", date, 3);
        p = new Plongee(new Site("Plage de l'amour", "Plage très prisée par les amoureux"), m1, date, 50, 4);
        c = new Club(m1, "Les Dents de la mer", "05 67 78 12 34");
        m1.ajouterLicence("47L", date.minusMonths(10),c);
        m3.ajouterLicence("49L", date.minusMonths(10),c);
    }

    /**
     * Test of plongeesNonConformes method, of class Club.
     */
    @Test
    public void testPlongeesNonConformes() {
        HashSet<Plongee> plongees = new HashSet<>();
        p.ajouteParticipant(m2);
        p.ajouteParticipant(m3);
        c.organisePlongee(p);
        plongees.add(p);
        assertEquals(plongees, c.plongeesNonConformes(), "Les plongées non conformes doivent s'afficher");
        m2.ajouterLicence("50L", date.minusMonths(10),c);
        plongees.remove(p);
        assertEquals(plongees, c.plongeesNonConformes(), "Aucune plongee ne doit s'afficher");
    }

    /**
     * Test of estConforme method, of class Plongee.
     */
    @Test
    public void testEstConforme() {
        m2.ajouterLicence("48L", date.minusMonths(14),c);
        p.ajouteParticipant(m2);
        p.ajouteParticipant(m3);
        assertFalse(p.estConforme(), "La plongée doit être conforme");
        m2.ajouterLicence("50L", date.minusMonths(10),c);
        assertTrue(p.estConforme(), "La plongée doit être conforme");

    }

    /**
     * Test of estValide method, of class Licence.
     */
    @Test
    public void testEstValide() {
        Licence l = new Licence(m1, "10E", date, c);
        assertTrue(l.estValide(date.plusDays(300)), "La licence doit encore être valide");
        assertFalse(l.estValide(date.plusDays(400)), "La licence ne doit plus être valide");
    }

    /**
     * Test of employeurActuel method, of class Moniteur.
     */
    @Test
    public void testEmployeurActuel() {
        try {
            m1.terminerEmbauche(LocalDate.now());
            fail();
        } catch (Exception ex) {
            //  doit  renvoyer une erreur
        }
        m1.nouvelleEmbauche(c, date);
        assertEquals(c, m1.employeurActuel().get(), "L'employeur n'est pas bon");
        try {
            m1.terminerEmbauche(LocalDate.now());
        } catch (Exception ex) {
            fail();// ne doit pas renvoyer d'erreur
        }
        assertTrue(m1.employeurActuel().isEmpty(), "L'employeur n'est pas bon");
    }

}

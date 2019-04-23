package deplacement;

import junit.framework.TestCase;

import echiquier.Position;
import java.util.Arrays;

/**
 * Classe qui test les déplacements possibles des pièces d'un échiquier
 *
 * @author Samuel Colassin
 */
public class TestDeplacement extends TestCase {

    /**
     * Teste la méthode equals sur un déplacements
     */
    public void testEquals() {
        Position depart = new Position(0, 0);
        Deplacement deplacementA = new DeplacementReine(depart);
        Deplacement deplacementB = new DeplacementReine(depart);
        Deplacement deplacementC = new DeplacementCavalier(depart);

        assertTrue(deplacementA.equals(deplacementB));
        assertTrue(deplacementB.equals(deplacementA));

        assertTrue(deplacementA.equals(deplacementA));
        assertTrue(deplacementB.equals(deplacementB));

        assertFalse(deplacementA.equals(deplacementC));
        assertFalse(deplacementB.equals(deplacementC));
        assertFalse(deplacementA.equals(null));
        assertFalse(deplacementA.equals("ads"));
    }

    /**
     * Teste la génération de hashcode
     */
    public void testHashCode() {
        Position depart = new Position(0, 0);
        Deplacement deplacementA = new DeplacementReine(depart);
        Deplacement deplacementB = new DeplacementReine(depart);
        Deplacement deplacementC = new DeplacementCavalier(depart);

        assertEquals(deplacementA.hashCode(), deplacementB.hashCode());
        assertEquals(deplacementB.hashCode(), deplacementA.hashCode());

        assertFalse(deplacementA.hashCode() == deplacementC.hashCode());
        assertFalse(deplacementB.hashCode() == deplacementC.hashCode());
    }

    public void testAjouterPositions() {
        Deplacement deplacementA = new DeplacementRoi(new Position(0, 0));
    }

    /**
     * Test de déplacement d'un roi
     */
    public void testDeplacementRoi() {
        Deplacement deplacement = new DeplacementRoi(new Position(4, 4));

        Position[] possibiliteAttendues = new Position[]{
                new Position(4, 5),
                new Position(3, 5),
                new Position(3, 4),
                new Position(3, 3),
                new Position(4, 3),
                new Position(5, 3),
                new Position(5, 4),
                new Position(5, 5)
        };

        Arrays.sort(possibiliteAttendues);
        Arrays.sort(deplacement.getPositionsDisponibles());

        assertTrue(Arrays.equals(possibiliteAttendues, deplacement.getPositionsDisponibles()));
    }

    /**
     * Test de déplacement d'une reine
     */
    public void testDeplacementReine() {
        Deplacement deplacement = new DeplacementReine(new Position(0, 0));

        Position[] possibiliteAttendues = new Position[]{
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4),
                new Position(0, 5),
                new Position(0, 6),
                new Position(0, 7),
                new Position(1, 0),
                new Position(2, 0),
                new Position(3, 0),
                new Position(4, 0),
                new Position(5, 0),
                new Position(6, 0),
                new Position(7, 0),
                new Position(1, 1),
                new Position(2, 2),
                new Position(3, 3),
                new Position(4, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),
        };

        Arrays.sort(possibiliteAttendues);
        Arrays.sort(deplacement.getPositionsDisponibles());

        assertTrue(Arrays.equals(possibiliteAttendues, deplacement.getPositionsDisponibles()));
    }

    /**
     * Test de déplacement d'un fou
     */
    public void testDeplacementFou() {
        Deplacement deplacement = new DeplacementFou(new Position(4, 4));

        Position[] possibiliteAttendues = new Position[]{
                new Position(3, 5),
                new Position(2, 6),
                new Position(1, 7),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),
                new Position(5, 3),
                new Position(6, 2),
                new Position(7, 1),
                new Position(3, 3),
                new Position(2, 2),
                new Position(1, 1),
                new Position(0, 0)
        };
        Arrays.sort(possibiliteAttendues);
        Arrays.sort(deplacement.getPositionsDisponibles());

        assertTrue(Arrays.equals(possibiliteAttendues, deplacement.getPositionsDisponibles()));
    }

    /**
     * Test de déplacement d'un cavalier
     */
    public void testDeplacementCavalier() {
        Deplacement deplacement = new DeplacementCavalier(new Position(4, 4));

        Position[] possibiliteAttendues = new Position[]{
                new Position(5, 6),
                new Position(3, 6),
                new Position(2, 5),
                new Position(2, 3),
                new Position(3, 2),
                new Position(5, 2),
                new Position(6, 5),
                new Position(6, 3),

        };

        Arrays.sort(possibiliteAttendues);
        Arrays.sort(deplacement.getPositionsDisponibles());

        assertTrue(Arrays.equals(possibiliteAttendues, deplacement.getPositionsDisponibles()));
    }

    /**
     * Test de déplacement d'une tour
     */
    public void testDeplacementTour() {
        Deplacement deplacement = new DeplacementTour(new Position(4, 4));

        Position[] possibiliteAttendues = new Position[]{
                new Position(4, 7),
                new Position(4, 6),
                new Position(4, 5),
                new Position(4, 3),
                new Position(4, 2),
                new Position(4, 1),
                new Position(4, 0),
                new Position(0, 4),
                new Position(1, 4),
                new Position(2, 4),
                new Position(3, 4),
                new Position(5, 4),
                new Position(6, 4),
                new Position(7, 4),
        };

        Arrays.sort(possibiliteAttendues);
        Arrays.sort(deplacement.getPositionsDisponibles());

        assertTrue(Arrays.equals(possibiliteAttendues, deplacement.getPositionsDisponibles()));
    }

    /**
     * Test de déplacement d'un pion
     */
    public void testDeplacementPion() {
        Deplacement deplacement = new DeplacementPion(new Position(4, 4));

        Position[] possibiliteAttendues = new Position[]{
                new Position(4, 5)
        };

        Arrays.sort(possibiliteAttendues);
        Arrays.sort(deplacement.getPositionsDisponibles());

        assertTrue(Arrays.equals(possibiliteAttendues, deplacement.getPositionsDisponibles()));
    }
}

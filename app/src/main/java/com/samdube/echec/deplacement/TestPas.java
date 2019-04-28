package com.samdube.echec.deplacement;

import junit.framework.TestCase;

/**
 * Classe testant la classe incrémentation
 * @author Samuel Dube
 */
public class TestPas extends TestCase {
    /**
     * Teste la création d'une incrémentation valide.
     */
    public void testCreer() {
        Pas pas = new Pas(1, 0);

        assertEquals(1, pas.getDeplacementX());
        assertEquals(0, pas.getDeplacementY());

        pas = new Pas(8, 8);

        assertEquals(8, pas.getDeplacementX());
        assertEquals(8, pas.getDeplacementY());

        pas = new Pas(-8, -8);

        assertEquals(-8, pas.getDeplacementX());
        assertEquals(-8, pas.getDeplacementY());

        try {
            new Pas(9, 9);
            fail();
        } catch (IllegalArgumentException e) {
            // Test reussi
        }
    }

    /**
     * Teste l'égalite sémentique entre deux incrémentation possédant les mêmes
     * valeurs.
     */
    public void testEquals() {
        Pas pasA = new Pas(1, 0);
        Pas pasB = new Pas(1, 0);
        Pas pasC = new Pas(-1, 0);

        assertTrue(pasA.equals(pasA));
        assertTrue(pasB.equals(pasB));

        assertTrue(pasA.equals(pasB));
        assertTrue(pasB.equals(pasA));

        assertFalse(pasA.equals("null"));
        assertFalse(pasA.equals(pasC));
        assertFalse(pasB.equals(pasC));
        assertFalse(pasA.equals(null));
    }

    /**
     * Teste la génération de hash code pour une incrémentation.
     */
    public void testHashCode() {
        Pas pasA = new Pas(1, 1);
        Pas pasB = new Pas(1, 1);
        Pas pasC = new Pas(-1, 1);

        assertEquals(pasA.hashCode(), pasA.hashCode());
        assertEquals(pasA.hashCode(), pasB.hashCode());
        assertEquals(pasB.hashCode(), pasA.hashCode());

        assertFalse(pasA.hashCode() == pasC.hashCode());
        assertFalse(pasB.hashCode() == pasC.hashCode());
    }
}

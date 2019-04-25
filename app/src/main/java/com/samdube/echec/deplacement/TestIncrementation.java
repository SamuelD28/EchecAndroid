package com.samdube.echec.deplacement;

import junit.framework.TestCase;

/**
 * Classe testant la classe incrémentation
 * @author Samuel Dube
 */
public class TestIncrementation extends TestCase {
    /**
     * Teste la création d'une incrémentation valide.
     */
    public void testCreer() {
        Incrementation incrementation = new Incrementation(1, 0);

        assertEquals(1, incrementation.getIncrementationX());
        assertEquals(0, incrementation.getIncrementationY());

        incrementation = new Incrementation(8, 8);

        assertEquals(8, incrementation.getIncrementationX());
        assertEquals(8, incrementation.getIncrementationY());

        incrementation = new Incrementation(-8, -8);

        assertEquals(-8, incrementation.getIncrementationX());
        assertEquals(-8, incrementation.getIncrementationY());

        try {
            new Incrementation(9, 9);
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
        Incrementation incrementationA = new Incrementation(1, 0);
        Incrementation incrementationB = new Incrementation(1, 0);
        Incrementation incrementationC = new Incrementation(-1, 0);

        assertTrue(incrementationA.equals(incrementationA));
        assertTrue(incrementationB.equals(incrementationB));

        assertTrue(incrementationA.equals(incrementationB));
        assertTrue(incrementationB.equals(incrementationA));

        assertFalse(incrementationA.equals("null"));
        assertFalse(incrementationA.equals(incrementationC));
        assertFalse(incrementationB.equals(incrementationC));
        assertFalse(incrementationA.equals(null));
    }

    /**
     * Teste la génération de hash code pour une incrémentation.
     */
    public void testHashCode() {
        Incrementation incrementationA = new Incrementation(1, 1);
        Incrementation incrementationB = new Incrementation(1, 1);
        Incrementation incrementationC = new Incrementation(-1, 1);

        assertEquals(incrementationA.hashCode(), incrementationA.hashCode());
        assertEquals(incrementationA.hashCode(), incrementationB.hashCode());
        assertEquals(incrementationB.hashCode(), incrementationA.hashCode());

        assertFalse(incrementationA.hashCode() == incrementationC.hashCode());
        assertFalse(incrementationB.hashCode() == incrementationC.hashCode());
    }
}
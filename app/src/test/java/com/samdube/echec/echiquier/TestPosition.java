package com.samdube.echec.echiquier;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Classe de test pour verifier les comportements
 * de la classe Position
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestPosition extends TestCase {
    /**
     * Methode qui teste la creation de nouvelle
     * position.
     */
    public void testCreer() {
        Position position1 = new Position("A1");
        Position position2 = new Position(7, 3);
        Position position3 = new Position(7, 3);

        assertEquals(0, position1.getX());
        assertEquals(0, position1.getY());

        assertEquals(7, position2.getX());
        assertEquals(3, position2.getY());

        assertEquals(position1, position1);
        assertEquals(position2.hashCode(), position3.hashCode());
        assertFalse(position1.hashCode() == position2.hashCode());
        assertFalse(position1.equals("test"));

        // On verifie que la creation d'une position erronnee lance belle et
        // bien une exception de type position invalide
        try {
            new Position("AAAAA");
            fail();
        } catch (Position.PositionInvalideException e) {
            // Test réussi
        }

        try {
            new Position(19, 22);
            fail();
        } catch (Position.PositionInvalideException e) {
            // Test réussi
        }
    }

    /**
     * Methode qui teste l'assignation d'une position existante
     * a une nouvelle position
     */
    public void testAssignation() {
        Position position1 = new Position("A1");

        // Devrait change la position a 1,1
        position1.assigner("B2");

        assertEquals(1, position1.getX());
        assertEquals(1, position1.getY());

        // Ne devrait pas modifier la position
        assertFalse(position1.assigner("ZZZZ"));
        assertFalse(position1.assigner(20, 20));

        // La position devrait toujours etre 1,1
        assertEquals(1, position1.getX());
        assertEquals(1, position1.getY());

        // Devrait modifier la position a 3,3
        assertTrue(position1.assigner("C3"));
        assertTrue(position1.assigner(3, 3));

        // La position devrait maintenant etre 3,3
        assertEquals(3, position1.getX());
        assertEquals(3, position1.getY());
    }

    /**
     * Methode qui teste qu'une position x,y est bel
     * et bien equivalente a une la meme position x,y
     */
    public void testComparaison() {
        Position position1 = new Position("A1");  // x,y = 0,0
        Position position2 = new Position(0, 0);     // x,y = 0,0
        Position position3 = new Position("C4"); // x,y = 2,3

        assertEquals(position1, position2);

        assertTrue(position3 != position1);
        assertTrue(position3 != position2);

        position1.assigner("C4");
        assertEquals(position1, position3);
        assertTrue(position1 != position2);
    }

    /**
     * Méthode qui test l'obtention d'une position la plus proche
     * à partir d'un point d'origine selon une liste d'autre positions
     */
    public void testPositionLaPlusProche() {
        Position plusProche = new Position(2, 2);
        Position origine = new Position(3, 3);

        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(2, 2));
        positions.add(new Position(5, 5));
        positions.add(new Position(5, 1));
        positions.add(new Position(1, 1));

        Position plusProcheCalcule = Position.ObtenirPositionLaPlusProche(origine, positions);
        assertEquals(plusProche, plusProcheCalcule);
    }

    /**
     * Teste le compare to
     */
    public void testCompareTo() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(1, 1);
        Position position3 = new Position(7, 7);
        Position position4 = new Position(0, 0);

        assertTrue(position1.compareTo(position2) < 0);
        assertTrue(position3.compareTo(position2) > 0);
        assertTrue(position3.compareTo(position1) > 0);
        assertEquals(0, position1.compareTo(position4));
    }

    /**
     * Permet de tester la creation de postion en version textuelle
     */
    public void testPostionEnTexte() {
        assertEquals("A1", Position.parsePositionVersTexte(new Position(0, 0)));
    }
}

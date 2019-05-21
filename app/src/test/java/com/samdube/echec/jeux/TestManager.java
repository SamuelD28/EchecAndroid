package com.samdube.echec.jeux;

import com.samdube.echec.piece.Piece;

import junit.framework.TestCase;

/**
 * Teste la clsse manager
 *
 * @author Samuel Dube
 * @author Samuel COlassin
 */
public class TestManager extends TestCase {

    /**
     * Teste la creation dun manager
     */
    public void testCreation() {
        Manager manager = new Manager();
        manager.setNomsJoueurs("Samuel", "Bob");

        assertEquals(0, manager.getTour());
        assertEquals(Piece.CouleurPiece.BLANC, manager.getCouleurJoueurEnTour());
        assertEquals("Samuel", manager.getNomJoueurEnTour());

        manager.terminerTour();

        assertEquals(1, manager.getTour());
        assertEquals(Piece.CouleurPiece.NOIR, manager.getCouleurJoueurEnTour());
        assertEquals("Bob", manager.getNomJoueurEnTour());

        manager.terminerTour();
        manager.revenirTour(2);

        assertEquals(0, manager.getTour());
        assertEquals(Piece.CouleurPiece.BLANC, manager.getCouleurJoueurEnTour());
        assertEquals("Samuel", manager.getNomJoueurEnTour());

        manager.terminerTour();
        manager.reinitialiserPartie();

        assertEquals(0, manager.getTour());
        assertEquals(Piece.CouleurPiece.BLANC, manager.getCouleurJoueurEnTour());
        assertEquals("Samuel", manager.getNomJoueurEnTour());
    }
}

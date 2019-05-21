package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.piece.Piece;

/**
 * Classe de test pour le com.samdube.echec.deplacement du pion
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementPion extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementPion(Piece.CouleurPiece.BLANC);
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour();
    }

    /**
     * Teste la collision dun pion
     */
    public void testCollisionsPion() {
        Echiquier echiquier = new Echiquier();
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 1)), new Position(1, 3)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 3)), new Position(1, 4)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 4)), new Position(1, 5)));
        echiquier.calculerDeplacements();
        assertFalse(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 5)), new Position(1, 6)));

        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 6)), new Position(6, 4)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 4)), new Position(6, 3)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 3)), new Position(6, 2)));
        echiquier.calculerDeplacements();
        assertFalse(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 2)), new Position(6, 1)));
    }
}

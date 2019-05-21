package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementPion;
import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;


/**
 * Teste la creation dune com.samdube.echec.piece de type pion
 *
 * @author Samuel Dube
 */
public class TestPion extends TestPiece {
    @Override
    public Piece getPieceAttendue() {
        return new Piece(
                new Position(0, 0),
                new DeplacementPion(Piece.getCouleurAvecPositionDepart(new Position(0, 0))),
                'p',
                1) {
        };
    }

    @Override
    public Piece getPieceActuel() {
        return new Pion(new Position(0, 0));
    }

    @Override
    public Piece getPieceDifferente() {
        return new Roi(new Position(1, 1));
    }

    @Override
    public Position[] getDeplacementsPossible() {
        return new Position[]{
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4)
        };
    }

    @Override
    public Position[] getDeplacementsImpossible() {
        return new Position[]{
                new Position(0, 0),
                new Position(0, 2),
                new Position(1, 0),
                new Position(1, 1)
        };
    }

    /**
     * Teste latteinte en promotion
     */
    public void testAtteindrePromotion() {
        // On fait atteindre le pion de l'autre côté (blanc)
        Echiquier echiquier = new Echiquier();
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 1)), new Position(1, 3)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 3)), new Position(1, 4)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 4)), new Position(1, 5)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(1, 5)), new Position(0, 6)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(0, 6)), new Position(1, 7)));
        echiquier.calculerDeplacements();

        // On fait atteindre le pion de l'autre côté (noir)
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 6)), new Position(6, 4)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 4)), new Position(6, 3)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 3)), new Position(6, 2)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 2)), new Position(7, 1)));
        echiquier.calculerDeplacements();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(7, 1)), new Position(6, 0)));
        echiquier.calculerDeplacements();
    }
}

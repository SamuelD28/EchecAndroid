package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementTour;
import com.samdube.echec.echiquier.Position;

/**
 * Teste la creation dune com.samdube.echec.piece de type tour
 *
 * @author Samuel Dube
 */
public class TestTour extends TestPiece {
    @Override
    public Piece getPieceAttendue() {
        return new Piece(
                new Position(0, 0),
                new DeplacementTour(),
                't',
                2
        ) {
        };
    }

    @Override
    public Piece getPieceActuel() {
        return new Tour(new Position(0, 0));
    }

    @Override
    public Piece getPieceDifferente() {
        return new Roi(new Position(1, 1));
    }

    @Override
    public Position[] getDeplacementsPossible() {
        return new Position[]{
                new Position(0, 7),
                new Position(7, 7),
                new Position(7, 0),
                new Position(0, 0)
        };
    }

    @Override
    public Position[] getDeplacementsImpossible() {
        return new Position[]{
                new Position(7, 7),
                new Position(2, 2),
                new Position(4, 1),
                new Position(0, 0)
        };
    }

    /**
     * Teste la construction dune tour en promotion
     */
    public void testConstructeurTourPromotion() {
        Piece piece = new Tour(new Position(0,1), Piece.CouleurPiece.BLANC);
        assertEquals(Piece.CouleurPiece.BLANC, piece.getCouleur());
    }
}

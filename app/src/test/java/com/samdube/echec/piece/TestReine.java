package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementReine;
import com.samdube.echec.echiquier.Position;

/**
 * Teste la creation dune piece de type reine
 *
 * @author Samuel Dube
 */
public class TestReine extends TestPiece {
    @Override
    public Piece getPieceAttendue() {
        return new Piece(
                new Position(0, 0),
                new DeplacementReine(),
                'q',
                3
        ) {
        };
    }

    @Override
    public Piece getPieceActuel() {
        return new Reine(new Position(0, 0));
    }

    @Override
    public Piece getPieceDifferente() {
        return new Roi(new Position(1, 1));
    }

    @Override
    public Position[] getDeplacementsPossible() {
        return new Position[]{
                new Position(7, 7),
                new Position(7, 0),
                new Position(0, 0),
                new Position(0, 7)
        };
    }

    @Override
    public Position[] getDeplacementsImpossible() {
        return new Position[]{
                new Position(1, 2),
                new Position(4, 1),
                new Position(4, 2),
                new Position(0, 0)
        };
    }

    /**
     * Teste la construction dune reine en promotion
     */
    public void testConstructeurReinePromotion() {
        Piece piece = new Reine(new Position(0, 1), Piece.CouleurPiece.BLANC);
        assertEquals(Piece.CouleurPiece.BLANC, piece.getCouleur());
    }
}

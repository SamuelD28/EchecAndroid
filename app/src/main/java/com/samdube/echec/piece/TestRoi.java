package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementRoi;
import com.samdube.echec.echiquier.Position;

/**
 * Teste la creation dune com.samdube.echec.piece de type roi
 *
 * @author Samuel Dube
 */
public class TestRoi extends TestPiece {
    @Override
    public Piece getPieceAttendue() {
        return new Piece(
                new Position(7, 7),
                new DeplacementRoi(new Position(7, 7)),
                'r',
                1
        ) {
        };
    }

    @Override
    public Piece getPieceActuel() {
        return new Roi(new Position(7, 7));
    }

    @Override
    public Piece getPieceDifferente() {
        return new Pion(new Position(1, 1));
    }

    @Override
    public Position[] getDeplacementsPossible() {
        return new Position[]{
                new Position(6, 7),
                new Position(5, 7),
                new Position(5, 6),
                new Position(4, 5)
        };
    }

    @Override
    public Position[] getDeplacementsImpossible() {
        return new Position[]{
                new Position(0, 2),
                new Position(2, 2),
                new Position(1, 2),
                new Position(7, 7)
        };
    }
}

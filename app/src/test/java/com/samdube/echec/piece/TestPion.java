package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementPion;
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
}

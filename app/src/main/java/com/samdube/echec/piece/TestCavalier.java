package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementCavalier;
import com.samdube.echec.echiquier.Position;

/**
 * Teste la creation d'une com.samdube.echec.piece cavalier
 *
 * @author Samuel Dube
 */
public class TestCavalier extends TestPiece {
    @Override
    public Piece getPieceAttendue() {
        return new Piece(
                new Position(0, 0),
                new DeplacementCavalier(),
                'c',
                2
        ) {
        };
    }

    @Override
    public Piece getPieceActuel() {
        return new Cavalier(new Position(0, 0));
    }

    @Override
    public Piece getPieceDifferente() {
        return new Roi(new Position(1, 1));
    }

    @Override
    public Position[] getDeplacementsPossible() {
        return new Position[]{
                new Position(1, 2),
                new Position(2, 4),
                new Position(4, 3),
                new Position(5, 5)
        };
    }

    @Override
    public Position[] getDeplacementsImpossible() {
        return new Position[]{
                new Position(0, 0),
                new Position(2, 2),
                new Position(3, 1),
                new Position(7, 7)
        };
    }
}

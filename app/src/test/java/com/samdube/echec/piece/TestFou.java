package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementFou;
import com.samdube.echec.echiquier.Position;

/**
 * Teste la creation d'une com.samdube.echec.piece fou
 *
 * @author Samuel Dube
 */
public class TestFou extends TestPiece {
    @Override
    public Piece getPieceAttendue() {
        return new Piece(
                new Position(0, 0),
                new DeplacementFou(),
                'f',
                2
        ) {
        };
    }

    @Override
    public Piece getPieceActuel() {
        return new Fou(new Position(0, 0));
    }

    @Override
    public Piece getPieceDifferente() {
        return new Roi(new Position(1, 1));
    }

    @Override
    public Position[] getDeplacementsPossible() {
        return new Position[]{
                new Position(1, 1),
                new Position(4, 4),
                new Position(1, 7),
                new Position(7, 1)
        };
    }

    @Override
    public Position[] getDeplacementsImpossible() {
        return new Position[]{
                new Position(1, 0),
                new Position(0, 1),
                new Position(2, 3),
                new Position(0, 0)
        };
    }

    public void testConstructeurFouPromotion() {
        Piece piece = new Fou(new Position(0,1), Piece.CouleurPiece.BLANC);
        assertEquals(Piece.CouleurPiece.BLANC, piece.getCouleur());
    }
}

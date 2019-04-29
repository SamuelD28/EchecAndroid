package com.samdube.echec.deplacement;

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

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(4, 5)
        };
    }
}

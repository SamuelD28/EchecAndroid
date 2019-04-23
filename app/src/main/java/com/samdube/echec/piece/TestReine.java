package com.samdube.echec.piece;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.deplacement.DeplacementReine;
import com.samdube.echec.echiquier.Position;

import static com.samdube.echec.piece.Piece.CouleurPiece.BLANC;

public class TestReine extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 3;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'q';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementReine(getPiece().getPosition());
    }

    @Override
    public Position getPositionAttendu() {
        return new Position(0,0);
    }

    @Override
    public Piece.CouleurPiece getCouleurAttendu() {
        return BLANC;
    }

    @Override
    public Piece getPiece() {
        return new Reine(new Position(0,0));
    }
}

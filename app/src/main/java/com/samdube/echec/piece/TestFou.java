package com.samdube.echec.piece;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.deplacement.DeplacementFou;
import com.samdube.echec.echiquier.Position;

import static com.samdube.echec.piece.Piece.CouleurPiece.BLANC;

public class TestFou extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 2;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'f';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementFou(getPiece().getPosition());
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
        return new Fou(new Position(0,0));
    }
}

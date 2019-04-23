package com.samdube.echec.piece;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.deplacement.DeplacementCavalier;
import com.samdube.echec.echiquier.Position;

import static com.samdube.echec.piece.Piece.CouleurPiece.*;

public class TestCavalier extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 2;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'c';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementCavalier(getPiece().getPosition());
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
        return new Cavalier(new Position(0,0));
    }
}

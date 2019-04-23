package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementFou;
import com.samdube.echec.echiquier.Position;

public class Fou extends Piece{
    public Fou(Position p_position) {
        super(p_position, new DeplacementFou(p_position));
    }

    @Override
    public char getRepresentation() {
        return 'f';
    }

    @Override
    public int getForce() {
        return 2;
    }
}

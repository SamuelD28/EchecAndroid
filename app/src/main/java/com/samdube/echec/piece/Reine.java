package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementReine;
import com.samdube.echec.echiquier.Position;

public class Reine extends Piece {
    public Reine(Position p_position) {
        super(p_position, new DeplacementReine(p_position));
    }

    @Override
    public char getRepresentation() {
        return 'q';
    }

    @Override
    public int getForce() {
        return 3;
    }
}

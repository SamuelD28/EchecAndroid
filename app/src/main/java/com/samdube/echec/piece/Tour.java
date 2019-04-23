package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementTour;
import com.samdube.echec.echiquier.Position;

public class Tour extends Piece {
    public Tour(Position p_position) {
        super(p_position, new DeplacementTour(p_position));
    }

    @Override
    public char getRepresentation() {
        return 't';
    }

    @Override
    public int getForce() {
        return 2;
    }
}

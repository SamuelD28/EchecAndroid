package piece;

import deplacement.DeplacementReine;
import echiquier.Position;

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

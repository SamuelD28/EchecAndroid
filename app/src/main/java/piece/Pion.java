package piece;

import deplacement.DeplacementPion;
import echiquier.Position;

public class Pion extends Piece {

    public Pion(Position p_position) {
        super(p_position, new DeplacementPion(p_position));
    }

    @Override
    public char getRepresentation() {
        return 'p';
    }

    @Override
    public int getForce() {
        return 1;
    }
}

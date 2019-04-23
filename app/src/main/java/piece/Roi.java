package piece;

import deplacement.DeplacementRoi;
import echiquier.Position;

public class Roi extends Piece{
    public Roi(Position p_position) {
        super(p_position, new DeplacementRoi(p_position));
    }

    @Override
    public char getRepresentation() {
        return 'r';
    }

    @Override
    public int getForce() {
        return 1;
    }
}

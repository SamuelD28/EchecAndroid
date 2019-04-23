package piece;

import deplacement.DeplacementFou;
import echiquier.Position;

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

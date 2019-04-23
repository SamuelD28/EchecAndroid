package piece;

import deplacement.DeplacementCavalier;
import echiquier.Position;

public class Cavalier extends Piece{
    public Cavalier(Position p_position) {
        super(p_position, new DeplacementCavalier(p_position));
    }

    @Override
    public char getRepresentation() {
        return 'c';
    }

    @Override
    public int getForce() {
        return 2;
    }
}

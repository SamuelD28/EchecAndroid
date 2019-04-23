package deplacement;

import echiquier.Position;

/**
 * Classe de déplacement possible par un fou
 *
 * @author Samuel Colassin
 */
public class DeplacementFou extends Deplacement {
    public DeplacementFou(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[] {
                new Incrementation(1,1),
                new Incrementation(-1,1),
                new Incrementation(-1,-1),
                new Incrementation(1,-1)
        };
    }

    @Override
    public int getPasMaximum() {
        return 8;
    }
}

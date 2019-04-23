package deplacement;

import echiquier.Position;

/**
 * Classe de déplacement possible pour une reine
 *
 * @author Samuel Colassin
 */
public class DeplacementReine extends Deplacement{
    public DeplacementReine(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[] {
                new Incrementation(0,1),
                new Incrementation(0,-1),
                new Incrementation(1,0),
                new Incrementation(-1,0),
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

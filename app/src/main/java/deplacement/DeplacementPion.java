package deplacement;

import echiquier.Position;

/**
 * Classe de déplacements possibles pour un pion
 *
 * @author Samuel Colassin
 */
public class DeplacementPion extends Deplacement{
    public DeplacementPion(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[] {
                new Incrementation(0,1)
        };
    }

    @Override
    public int getPasMaximum() {
        return 1;
    }
}

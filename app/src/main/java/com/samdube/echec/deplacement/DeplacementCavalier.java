package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de déplacements possibles d'un cavalier
 *
 * @author Samuel Colassin
 */
public class DeplacementCavalier extends Deplacement {

    public DeplacementCavalier(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[] {
                new Incrementation(1,2),
                new Incrementation(-1,2),
                new Incrementation(-2,1),
                new Incrementation(-2,-1),
                new Incrementation(-1,-2),
                new Incrementation(1,-2),
                new Incrementation(2,1),
                new Incrementation(2,-1)
        };
    }

    @Override
    public int getPasMaximum() {
        return 1;
    }
}

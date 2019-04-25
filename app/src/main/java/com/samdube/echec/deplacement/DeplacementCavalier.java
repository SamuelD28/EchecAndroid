package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de déplacements possibles d'un cavalier
 *
 * @author Samuel Colassin
 */
public class DeplacementCavalier extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour un cavalier
     *
     * @param p_pointOrigine Position du cavalier sur l'com.samdube.echec.echiquier
     */
    public DeplacementCavalier(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[]{
                new Incrementation(1, 2),
                new Incrementation(-1, 2),
                new Incrementation(-2, 1),
                new Incrementation(-2, -1),
                new Incrementation(-1, -2),
                new Incrementation(1, -2),
                new Incrementation(2, 1),
                new Incrementation(2, -1)
        };
    }

    @Override
    public int getPasMaximum() {
        return 1;
    }
}

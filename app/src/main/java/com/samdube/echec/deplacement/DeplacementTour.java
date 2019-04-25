package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de déplacements possibles par une tour
 *
 * @author Samuel Colassin
 */
public class DeplacementTour extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour la tour
     *
     * @param p_pointOrigine Position de la tour sur l'com.samdube.echec.echiquier
     */
    public DeplacementTour(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[]{
                new Incrementation(0, 1),
                new Incrementation(0, -1),
                new Incrementation(1, 0),
                new Incrementation(-1, 0)
        };
    }

    @Override
    public int getPasMaximum() {
        return 8;
    }
}

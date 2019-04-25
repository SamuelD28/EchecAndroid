package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de déplacement possible pour une reine
 *
 * @author Samuel Colassin
 */
public class DeplacementReine extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour la reine
     *
     * @param p_pointOrigine Position de la reine sur l'com.samdube.echec.echiquier
     */
    public DeplacementReine(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[]{
                new Incrementation(0, 1),
                new Incrementation(0, -1),
                new Incrementation(1, 0),
                new Incrementation(-1, 0),
                new Incrementation(1, 1),
                new Incrementation(-1, 1),
                new Incrementation(-1, -1),
                new Incrementation(1, -1)
        };
    }

    @Override
    public int getPasMaximum() {
        return 8;
    }
}

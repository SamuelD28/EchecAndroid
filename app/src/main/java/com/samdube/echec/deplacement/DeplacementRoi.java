package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de com.samdube.echec.deplacement possible pour un roi
 *
 * @author Samuel Colassin
 */
public class DeplacementRoi extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour le roi
     *
     * @param p_pointOrigine La position du roi sur l'com.samdube.echec.echiquier
     */
    public DeplacementRoi(Position p_pointOrigine) {
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
        return 1;
    }
}

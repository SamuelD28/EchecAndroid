package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de déplacements possibles pour un pion
 *
 * @author Samuel Colassin
 */
public class DeplacementPion extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour un pion
     *
     * @param p_pointOrigine Position du pion sur l'com.samdube.echec.echiquier
     */
    public DeplacementPion(Position p_pointOrigine) {
        super(p_pointOrigine);
    }

    @Override
    public Incrementation[] getIncrementations() {
        return new Incrementation[]{
                new Incrementation(0, 1)
        };
    }

    @Override
    public int getPasMaximum() {
        return 1;
    }
}

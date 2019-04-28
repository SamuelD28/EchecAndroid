package com.samdube.echec.deplacement;


/**
 * Classe de déplacements possibles pour un pion
 *
 * @author Samuel Colassin
 */
public class DeplacementPion extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour un pion
     *
     */
    public DeplacementPion() {
        super(new Pas[]{
                new Pas(0, 1)
        }, 1);
    }

}

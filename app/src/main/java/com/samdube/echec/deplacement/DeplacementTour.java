package com.samdube.echec.deplacement;

/**
 * Classe de déplacements possibles par une tour
 *
 * @author Samuel Colassin
 */
public class DeplacementTour extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour la tour
     *
     */
    public DeplacementTour() {
        super(new Pas[]{
                        new Pas(0, 1),
                        new Pas(0, -1),
                        new Pas(1, 0),
                        new Pas(-1, 0)
                },
                8);
    }
}

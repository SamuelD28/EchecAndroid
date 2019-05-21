package com.samdube.echec.deplacement;

/**
 * Classe de déplacement possible pour une reine
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class DeplacementReine extends Deplacement {
    /**
     * Constructeur initiant un deplacement pour la reine
     */
    public DeplacementReine() {
        super(new Pas[]{
                        new Pas(0, 1),
                        new Pas(0, -1),
                        new Pas(1, 0),
                        new Pas(-1, 0),
                        new Pas(1, 1),
                        new Pas(-1, 1),
                        new Pas(-1, -1),
                        new Pas(1, -1)
                },
                8);
    }
}

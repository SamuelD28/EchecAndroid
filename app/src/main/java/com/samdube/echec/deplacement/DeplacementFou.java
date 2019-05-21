package com.samdube.echec.deplacement;

/**
 * Classe de déplacement possible par un fou
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class DeplacementFou extends Deplacement {
    /**
     * Constructeur initiant un deplacement pour un fou
     */
    public DeplacementFou() {
        super(new Pas[]{
                        new Pas(1, 1),
                        new Pas(-1, 1),
                        new Pas(-1, -1),
                        new Pas(1, -1)
                },
                8);
    }
}

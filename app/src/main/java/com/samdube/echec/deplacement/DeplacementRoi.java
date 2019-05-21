package com.samdube.echec.deplacement;

/**
 * Classe de com.samdube.echec.deplacement possible pour un roi
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class DeplacementRoi extends Deplacement {
    /**
     * Constructeur initiant un deplacement pour le roi
     */
    public DeplacementRoi() {
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
                1);
    }
}

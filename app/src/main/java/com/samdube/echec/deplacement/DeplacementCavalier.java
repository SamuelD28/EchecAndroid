package com.samdube.echec.deplacement;

/**
 * Classe de déplacements possibles d'un cavalier
 *
 * @author Samuel Colassin
 */
public class DeplacementCavalier extends Deplacement {
    /**
     * Constructeur initiant un com.samdube.echec.deplacement pour un cavalier
     */
    public DeplacementCavalier() {
        super(new Pas[]{
                        new Pas(1, 2),
                        new Pas(-1, 2),
                        new Pas(-2, 1),
                        new Pas(-2, -1),
                        new Pas(-1, -2),
                        new Pas(1, -2),
                        new Pas(2, 1),
                        new Pas(2, -1)
                },
                1);
    }
}

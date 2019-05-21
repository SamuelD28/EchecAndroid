package com.samdube.echec.deplacement;


/**
 * Classe de test pour le deplacement du cavalier
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementCavalier extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementCavalier();
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour();
    }
}

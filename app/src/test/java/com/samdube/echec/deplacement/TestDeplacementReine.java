package com.samdube.echec.deplacement;

/**
 * Classe de test pour le com.samdube.echec.deplacement de la reine
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementReine extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementReine();
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour();
    }
}

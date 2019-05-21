package com.samdube.echec.deplacement;

/**
 * Classe de test pour le com.samdube.echec.deplacement du roi
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementRoi extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementRoi();
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour();
    }
}

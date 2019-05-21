package com.samdube.echec.deplacement;

/**
 * Classe de test pour le com.samdube.echec.deplacement de la tour
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementTour extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementTour();
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementRoi();
    }
}

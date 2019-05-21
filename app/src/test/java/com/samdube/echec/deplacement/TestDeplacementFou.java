package com.samdube.echec.deplacement;

/**
 * Classe de test pour le com.samdube.echec.deplacement du fou
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementFou extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementFou();
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour();
    }
}

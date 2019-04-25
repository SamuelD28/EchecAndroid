package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de test pour le com.samdube.echec.deplacement du pion
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementPion extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementPion(new Position(4, 4));
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour(new Position(4, 4));
    }

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(4, 5)
        };
    }
}

package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

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

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(4, 5),
                new Position(3, 5),
                new Position(3, 4),
                new Position(3, 3),
                new Position(4, 3),
                new Position(5, 3),
                new Position(5, 4),
                new Position(5, 5)
        };
    }
}

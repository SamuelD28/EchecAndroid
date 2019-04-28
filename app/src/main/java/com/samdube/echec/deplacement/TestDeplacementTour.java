package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

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

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(4, 7),
                new Position(4, 6),
                new Position(4, 5),
                new Position(4, 3),
                new Position(4, 2),
                new Position(4, 1),
                new Position(4, 0),
                new Position(0, 4),
                new Position(1, 4),
                new Position(2, 4),
                new Position(3, 4),
                new Position(5, 4),
                new Position(6, 4),
                new Position(7, 4),
        };
    }
}

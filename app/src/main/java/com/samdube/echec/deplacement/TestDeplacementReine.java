package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de test pour le com.samdube.echec.deplacement de la reine
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementReine extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementReine(new Position(0, 0));
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour(new Position(4, 4));
    }

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4),
                new Position(0, 5),
                new Position(0, 6),
                new Position(0, 7),
                new Position(1, 0),
                new Position(2, 0),
                new Position(3, 0),
                new Position(4, 0),
                new Position(5, 0),
                new Position(6, 0),
                new Position(7, 0),
                new Position(1, 1),
                new Position(2, 2),
                new Position(3, 3),
                new Position(4, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),
        };
    }
}

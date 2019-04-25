package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de test pour le com.samdube.echec.deplacement du fou
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementFou extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementFou(new Position(4, 4));
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour(new Position(4, 4));
    }

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(3, 5),
                new Position(2, 6),
                new Position(1, 7),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),
                new Position(5, 3),
                new Position(6, 2),
                new Position(7, 1),
                new Position(3, 3),
                new Position(2, 2),
                new Position(1, 1),
                new Position(0, 0)
        };
    }
}

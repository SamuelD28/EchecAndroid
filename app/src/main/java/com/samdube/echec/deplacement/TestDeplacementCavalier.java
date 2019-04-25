package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

/**
 * Classe de test pour le com.samdube.echec.deplacement du cavalier
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class TestDeplacementCavalier extends TestDeplacement {
    @Override
    public Deplacement getDeplacement() {
        return new DeplacementCavalier(new Position(4, 4));
    }

    @Override
    protected Deplacement getDeplacementDifferent() {
        return new DeplacementTour(new Position(4, 4));
    }

    @Override
    public Position[] getPossibilitesAttendues() {
        return new Position[]{
                new Position(5, 6),
                new Position(3, 6),
                new Position(2, 5),
                new Position(2, 3),
                new Position(3, 2),
                new Position(5, 2),
                new Position(6, 5),
                new Position(6, 3),

        };
    }
}

package com.samdube.echec.legacy;

import com.samdube.echec.echiquier.Position;
import junit.framework.TestCase;
import java.util.Arrays;

/**
 * Classe qui permet de tester les collisions qui peuvent survenir dans un échiquier
 *
 * @author Samuel Colassin
 */
public class TestCollision extends TestCase {
    /**
     * Test la création d'un échiquier
     */
    public void testCreer() {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(1, 2);
        Position p3 = new Position(1, 4);
        Collision c1 = new Collision(p1, p2);
        Collision c2 = new Collision(p1, p2);
        Collision c3 = new Collision(p1, p3);

        assertEquals(c1, c2);
        assertFalse(c1.equals(c3));

        assertEquals(c1.hashCode(), c2.hashCode());
        assertFalse(c1.hashCode() == c3.hashCode());
    }

    /**
     * Permet de tester le calcul des collisions à conserver
     */
    public void testCalculerCollision() {
        Position origine = new Position(3, 3);
        Collision[] collisionsEsperer = new Collision[]{
                new Collision(origine, new Position(1, 5)),
                new Collision(origine, new Position(7, 7)),
                new Collision(origine, new Position(4, 2)),
                new Collision(origine, new Position(3, 0)),
                new Collision(origine, new Position(1, 3))
        };

        Position[] positionsPossible = new Position[]{
                new Position(2, 4),
                new Position(1, 5),
                new Position(0, 6),

                new Position(4, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),

                new Position(4, 2),
                new Position(5, 1),
                new Position(6, 0),

                new Position(2, 2),
                new Position(1, 1),
                new Position(0, 0),

                new Position(3, 0),
                new Position(1, 3)
        };

        Position[] possibleCollisions = new Position[]{
                new Position(1, 5),
                new Position(7, 7),
                new Position(4, 2),
                new Position(0, 5),
                new Position(3, 4),
                new Position(6, 2),

                new Position(3, 0),
                new Position(1, 3)
        };

        Collision[] collisions = Collision.calculerCollisions(
                positionsPossible, possibleCollisions, origine, 6);

        // On sort parce qu'on veut seulement savoir si les éléménts de chaque liste se retrouve dans l'autre
        Arrays.sort(collisions);
        Arrays.sort(collisionsEsperer);

        assertTrue(Arrays.equals(collisionsEsperer, collisions));
    }
}
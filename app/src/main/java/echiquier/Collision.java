package echiquier;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe qui permet de gérer les collisions dans un échiquier
 *
 * @author Samuel Colassin
 */
public class Collision implements Comparable<Collision> {
    private Position m_pointOrigine;

    private Position m_pointContact;

    /**
     * Constructeur de collision
     *
     * @param p_pointOrigine La position de départ de la pièce en déplacement
     * @param p_pointContact La position qui cause une collision avec le pièce en mouvement
     */
    public Collision(Position p_pointOrigine, Position p_pointContact) {
        m_pointOrigine = p_pointOrigine;
        m_pointContact = p_pointContact;
    }

    /**
     * Méthode qui calcul les collisions qu'on doit conserver pour les déplacements disponible
     *
     * @param p_positions          Positions que que la pièce pourrait aller
     * @param p_possibleCollisions Position où il y a des pièce présente sur l'échiquer, donc une possible collision
     * @param p_origine            Position de la pièce en déplacement
     * @param p_collisionsMax      Le nombre de collision max que pourrait produire la pièce,
     *                             soit son nombre de direction différente
     * @return La liste des collision à conserver selon les déplacements possible de la pièce
     */
    public static Collision[] calculerCollisionsAConserver(Position[] p_positions, Position[] p_possibleCollisions,
                                                           Position p_origine, int p_collisionsMax) {
        ArrayList<Collision> collisions = new ArrayList<>();
        ArrayList<Position> positions = new ArrayList<>();

        for (Position possibleCollision : p_possibleCollisions) {
            for (Position position : p_positions) {
                if (possibleCollision.equals(position)) {
                    positions.add(possibleCollision);
                }
            }
        }

        if (positions.size() > 0) {
            for (int i = 0; i < p_collisionsMax; i++) {
                Position aConserver = Position.ObtenirPositionLaPlusProche(p_origine, positions);
                if (aConserver != null) {
                    collisions.add(new Collision(p_origine, aConserver));
                }
                positions.remove(aConserver);
            }
        }

        return collisions.toArray(new Collision[0]);
    }

    @Override
    public int compareTo(Collision o) {
        int result = Double.compare(m_pointContact.getX(), o.m_pointContact.getX());
        if (result == 0) {
            result = Double.compare(m_pointContact.getY(), o.m_pointContact.getY());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collision collision = (Collision) o;
        return Objects.equals(m_pointOrigine, collision.m_pointOrigine) &&
                Objects.equals(m_pointContact, collision.m_pointContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_pointOrigine, m_pointContact);
    }
}

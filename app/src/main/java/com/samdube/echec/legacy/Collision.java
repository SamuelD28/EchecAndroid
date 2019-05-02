package com.samdube.echec.legacy;

import com.samdube.echec.echiquier.Position;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe qui permet de gérer les collisions dans un échiquier
 *
 * @author Samuel Colassin
 */
class Collision implements Comparable<Collision> {
    private final Position m_pointOrigine;

    private final Position m_pointContact;

    /**
     * Constructeur de collision
     *
     * @param p_pointOrigine La position de départ de la pièce en déplacement
     * @param p_pointContact La position qui cause une collision avec le pièce en mouvement
     */
    Collision(Position p_pointOrigine, Position p_pointContact) {
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
    static Collision[] calculerCollisions(Position[] p_positions, Position[] p_possibleCollisions,
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

    public Position getPointContact() {
        return m_pointContact;
    }

    /**
     * Permet de déterminer si une collision est plus grande ou plus petite
     * qu'une autre
     *
     * @param o La collision à comparer
     * @return Entier plus petit que 0 si plus petit, 0 si égal et en entier plus grand que 0
     * si plus grand
     */
    @Override
    public int compareTo(Collision o) {
        int result = Double.compare(m_pointContact.getX(), o.m_pointContact.getX());
        if (result == 0) {
            result = Double.compare(m_pointContact.getY(), o.m_pointContact.getY());
        }
        return result;
    }

    /**
     * Methode permettant de comparer deux objects
     * de type collision pour savoir si les deux Collisions
     * sont equivalente
     *
     * @param o Position a comparer la courante instance
     * @return Vrai si les deux Collision sont equivalentes sinon faux.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collision collision = (Collision) o;
        return Objects.equals(m_pointOrigine, collision.m_pointOrigine) &&
                Objects.equals(m_pointContact, collision.m_pointContact);
    }

    /**
     * Methode generant le hash code pour l'instance.
     * Utiliser pour permettant la comparaison entre
     * deux objects de type Collision
     *
     * @return Le hash code generer pour l'instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(m_pointOrigine, m_pointContact);
    }
}

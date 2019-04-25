package com.samdube.echec.deplacement;

import java.util.Objects;

/**
 * Classe permettant de créer de nouvelle incrémentation
 * qui seront utiliser conjointement avec la classe déplacement.
 *
 * @author Samuel Dubé
 */
public class Incrementation {
    private int m_incrementationY;

    private int m_incrementationX;

    /**
     * Constructeur prennant en paramètre le nombre de position
     * que l'on peut se doit sur les X et Y d'un coup.
     *
     * @param p_incrementationX Nombre de position sur l'axe des x que l'on peut se déplacer d'un coup
     * @param p_incrementationY Nombre de position sur l'axe des y que l'on peut se déplacer d'un coup
     */
    Incrementation(int p_incrementationX, int p_incrementationY) {
        if (Math.abs(p_incrementationX) > 8 ||
                Math.abs(p_incrementationY) > 8 ||
                p_incrementationX == 0 && p_incrementationY == 0
        ) {
            throw new IllegalArgumentException();
        }

        this.m_incrementationX = p_incrementationX;
        this.m_incrementationY = p_incrementationY;
    }

    /**
     * Getter pour obtenir l'incrémentation Y.
     *
     * @return l'incrémentation Y.
     */
    int getIncrementationY() {
        return m_incrementationY;
    }

    /**
     * Getter pour obtenir l'incrémentation X.
     *
     * @return l'incrémentation X.
     */
    int getIncrementationX() {
        return m_incrementationX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Incrementation)) return false;
        Incrementation that = (Incrementation) o;
        return m_incrementationY == that.m_incrementationY &&
                m_incrementationX == that.m_incrementationX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_incrementationY, m_incrementationX);
    }
}

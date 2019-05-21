package com.samdube.echec.deplacement;

import java.util.Objects;

/**
 * Classe permettant de créer de nouveaux pas
 * pour le déplacement.
 *
 * @author Samuel Dubé
 * @author Samuel Colassin
 */
public class Pas {
    private int m_deplacementY;

    private int m_deplacementX;

    /**
     * Constructeur prennant en paramètre le nombre de position
     * que l'on peut se déplacer sur les X et Y d'un coup.
     *
     * @param p_incrementationX Nombre de position sur l'axe des x que l'on peut se pour un pas
     * @param p_incrementationY Nombre de position sur l'axe des y que l'on peut se pour un pas
     */
    Pas(int p_incrementationX, int p_incrementationY) {
        if (Math.abs(p_incrementationX) > 8 ||
                Math.abs(p_incrementationY) > 8 ||
                p_incrementationX == 0 && p_incrementationY == 0
                ) {
            throw new IllegalArgumentException();
        }

        this.m_deplacementX = p_incrementationX;
        this.m_deplacementY = p_incrementationY;
    }

    /**
     * Getter pour obtenir le déplacement sur Y
     *
     * @return Déplacement Y.
     */
    int getDeplacementY() {
        return m_deplacementY;
    }

    /**
     * Getter pour obtenir le déplacement sur X.
     *
     * @return le déplacement X
     */
    int getDeplacementX() {
        return m_deplacementX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pas)) return false;
        Pas that = (Pas) o;
        return m_deplacementY == that.m_deplacementY &&
                m_deplacementX == that.m_deplacementX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_deplacementY, m_deplacementX);
    }
}

package com.samdube.echec.deplacement;

import java.util.Objects;

/**
 * Classe permettant de créer de nouvelle incrémentation
 * qui seront utiliser conjointement avec la classe déplacement.
 *
 * @author Samuel Dubé
 */
public class Incrementation {
    // Todo nomenclature
    private static int INCREMENTATION_X_LIMITE = 8;

    private static int INCREMENTATION_Y_LIMITE = 8;

    private int m_incrementationY;

    private int m_incrementationX;

    /**
     * Constructeur prennant en paramètre le nombre de position
     * que l'on peut se doit sur les X et Y d'un coup.
     *
     * @param p_incrementationX Nombre de position sur l'axe des x que l'on peut se déplacer d'un coup
     * @param p_incrementationY Nombre de position sur l'axe des y que l'on peut se déplacer d'un coup
     */
    public Incrementation(int p_incrementationX, int p_incrementationY) {
        if (Math.abs(p_incrementationX) > INCREMENTATION_X_LIMITE ||
                Math.abs(p_incrementationY) > INCREMENTATION_Y_LIMITE ||
                p_incrementationX == 0 && p_incrementationY == 0
        ) {
            throw new IllegalArgumentException();
        }

        this.m_incrementationX = p_incrementationX;
        this.m_incrementationY = p_incrementationY;
    }

    /**
     * Setter pour changer la limite X de déplacemenet.
     *
     * @param p_incrementationXLimite Limite X de déplacement pour une incrémentation.
     */
    public static void setIncrementationXLimite(int p_incrementationXLimite) {
        INCREMENTATION_X_LIMITE = Math.abs(p_incrementationXLimite);
    }

    /**
     * Setter pour changer la limite Y de déplacemenet.
     *
     * @param p_incrementationYLimite Limite Y de déplacement pour une incrémentation
     */
    public static void setIncrementationYLimite(int p_incrementationYLimite) {
        INCREMENTATION_Y_LIMITE = Math.abs(p_incrementationYLimite);
    }

    /**
     * Getter pour obtenir la limite X de déplacement
     *
     * @return La limite X de déplacement
     */
    public static int getIncrementationXLimite() {
        return INCREMENTATION_X_LIMITE;
    }

    /**
     * Getter pour obtenir la limite Y de déplacement.
     *
     * @return La limite Y de déplacement
     */
    public static int getIncrementationYLimite() {
        return INCREMENTATION_Y_LIMITE;
    }

    /**
     * Getter pour obtenir l'incrémentation Y.
     *
     * @return l'incrémentation Y.
     */
    public int getIncrementationY() {
        return m_incrementationY;
    }

    /**
     * Getter pour obtenir l'incrémentation X.
     *
     * @return l'incrémentation X.
     */
    public int getIncrementationX() {
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

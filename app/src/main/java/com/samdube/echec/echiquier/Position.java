package com.samdube.echec.echiquier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Classe permettant de gérer la position d'une pièce
 * sur l'échiquier.
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class Position implements Comparable<Position> {
    /**
     * Constante utiliser pour mapper une lettre a une position dans un com.samdube.echec.echiquier
     */
    private final static List<Character> ECHELLE_POSITIONX = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');

    private int m_X;

    private int m_Y;

    /**
     * Permet d'obtenir la position la plus proche parmi une liste de position
     * selon un point d'origine
     *
     * @param p_origine   La position d'origine
     * @param p_positions La liste de position parmi lequel trouver la plus proche
     * @return La position la plus proche du point d'origine
     */
    static Position ObtenirPositionLaPlusProche(Position p_origine, List<Position> p_positions) {
        int differencePlusProche = 10000;
        Position plusProche = null;
        for (Position position : p_positions) {
            int differenceX = Math.abs(p_origine.getX() - position.getX());
            int differenceY = Math.abs(p_origine.getY() - position.getY());
            int difference = differenceX + differenceY;

            if (difference < differencePlusProche) {
                differencePlusProche = difference;
                plusProche = position;
            }
        }
        return plusProche;
    }

    /**
     * Classe contenant une excpetion qui est lancer
     * lorsque la position passee au constructeur est
     * invalide.
     *
     * @author Samuel Dube
     * @author Samuel Colassin
     */
    class PositionInvalideException extends RuntimeException {
        /**
         * Message d'erreur pour une position invalide
         */
        static final String ERR_POSITION_INVALIDE = "La position specifier est invalide";

        /**
         * Constructeur de base pour une exception
         * de type position invalide.
         */
        PositionInvalideException() {
            super(ERR_POSITION_INVALIDE);
        }
    }

    /**
     * Constructeur permettant d'initialiser une
     * nouvelle position sur l'com.samdube.echec.echiquier avec une coordonnee
     * textuelle.
     *
     * @param p_positionTextuelle Position sur l'com.samdube.echec.echiquier represente de facon textuelle.
     */
    public Position(String p_positionTextuelle) {
        int[] positionXY = parseTextuelleVersPositionXY(p_positionTextuelle);

        if (estDansLesLimites(positionXY)) {
            m_X = positionXY[0];
            m_Y = positionXY[1];
        } else {
            throw new PositionInvalideException();
        }
    }

    /**
     * Constructeur permettant d"initialiser une
     * nouvelle position sur l"com.samdube.echec.echiquier avec une coordonnee
     * chiffrer.
     *
     * @param p_positionXY Position sur l'com.samdube.echec.echiquier. [0] = x, [1] = 1
     */
    public Position(int... p_positionXY) {
        if (estDansLesLimites(p_positionXY)) {
            m_X = p_positionXY[0];
            m_Y = p_positionXY[1];
        } else {
            throw new PositionInvalideException();
        }
    }

    /**
     * Methode statique permettant de convertir une position
     * textuelle en position xy sur l'com.samdube.echec.echiquier sous forme
     * de array.
     *
     * @param p_positionTextuelle Position textuelle a convertir
     * @return La position converti sous formes de array. [0] = x, [1] = y
     */
    private static int[] parseTextuelleVersPositionXY(String p_positionTextuelle) {
        if (p_positionTextuelle.length() == 2) {

            int positionX = ECHELLE_POSITIONX.indexOf(Character.toUpperCase(p_positionTextuelle.charAt(0)));
            // On retire 1 parce l'com.samdube.echec.echiquier est de 0 a 7
            int positionY = Character.getNumericValue(p_positionTextuelle.charAt(1)) - 1;

            return new int[]{positionX, positionY};
        } else {
            return null;
        }
    }

    /**
     * Methode statique permettant de savoir si une position
     * est dans les limites de la grille de l'com.samdube.echec.echiquier.
     *
     * @param p_positionXY Position a verifier. [0] = x, [1] = y
     * @return Vrai si la position est conforme sinon faux.
     */
    public static boolean estDansLesLimites(int... p_positionXY) {
        if (p_positionXY == null || p_positionXY.length < 2) {
            return false;
        }

        return (p_positionXY[0] >= 0 && p_positionXY[0] < Echiquier.TAILLE_ECHIQUIER)
                &&
                (p_positionXY[1] >= 0 && p_positionXY[1] < Echiquier.TAILLE_ECHIQUIER);
    }

    /**
     * Methode permettant d'assigner la position
     * de linstance a une nouvelle position representer
     * sous forme textuelle
     *
     * @param p_positionTextuelle Position textuelle a assigner l'instance
     * @return Vrai si l'assignation a reussi sinon retourne faux.
     */
    boolean assigner(String p_positionTextuelle) {
        int[] positionXY = parseTextuelleVersPositionXY(p_positionTextuelle);

        if (estDansLesLimites(positionXY)) {
            m_X = positionXY[0];
            m_Y = positionXY[1];
            return true;
        } else {
            return false;
        }
    }

    /**
     * Methode permettant d'assigner la position
     * de l'instance a une nouvelle position representer
     * sous forme de coordonnees.
     *
     * @param p_positionXY Position coordonnee a assigner l'instance. [0] = x, [1] = y
     * @return Vrai si l'assignation a reussi sinon retourne faux.
     */
    boolean assigner(int... p_positionXY) {
        if (estDansLesLimites(p_positionXY)) {
            m_X = p_positionXY[0];
            m_Y = p_positionXY[1];
            return true;
        } else {
            return false;
        }
    }

    public static Position[] trimmer(Position p_pointOrigine, Position[] p_pointsDeCalcul, Position[] p_positions) {
        ArrayList<Position> positionsTrimmer = new ArrayList<>(Arrays.asList(p_positions));

        for (Position pointDeCalcul : p_pointsDeCalcul) {
            int differenceOrigineX = Math.abs(p_pointOrigine.getX() - pointDeCalcul.getX());
            int differenceOrigineY = Math.abs(p_pointOrigine.getY() - pointDeCalcul.getY());

            for(Position position : p_positions){
                int differenceX = Math.abs(p_pointOrigine.getX() - position.getX());
                int differenceY = Math.abs(p_pointOrigine.getY() - position.getY());
                if(differenceX >= differenceOrigineX && differenceY >= differenceOrigineY){
                    positionsTrimmer.remove(position);
                }
            }
        }

        return positionsTrimmer.toArray(new Position[0]);
    }

    /**
     * Methode qui permet d"obtenir la
     * position x de l'instance courante.
     *
     * @return La position x
     */
    public int getX() {
        return m_X;
    }

    /**
     * Methode qui permet d"obtenir la
     * position y de l'instance courante.
     *
     * @return La position ys
     */
    public int getY() {
        return m_Y;
    }

    /**
     * Methode generant le hash code pour l'instance.
     * Utiliser pour permettant la comparaison entre
     * deux objects de type Position
     *
     * @return Le hash code generer pour l'instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(m_X, m_Y);
    }

    /**
     * Methode permettant de comparer deux objects
     * de type position pour savoir si les deux Position
     * sont equivalente
     *
     * @param obj Position a comparer la courante instance
     * @return Vrai si les deux Position sont equivalentes sinon faux.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Position)) {
            return false;
        }

        Position position = (Position) obj;
        return this.m_X == position.getX() &&
                this.m_Y == position.getY();
    }

    /**
     * Permet de déterminer si une position est plus grande ou plus petite
     * qu'une autre
     *
     * @param o La position à comparer
     * @return Entier plus petit que 0 si plus petit, 0 si égal et en entier plus grand que 0
     * si plus grand
     */
    @Override
    public int compareTo(Position o) {
        int result = Double.compare(getX(), o.getX());
        if (result == 0) {
            result = Double.compare(getY(), o.getY());
        }
        return result;
    }
}

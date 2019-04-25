package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe permettant de créer une logique de déplacement
 * sur un échelle de positions.
 *
 * @author Samuel Dubé
 */
public abstract class Deplacement {
    private ArrayList<Position> m_possibilites;

    /**
     * Constructeur initialisant un nouveau com.samdube.echec.deplacement pour
     * une com.samdube.echec.piece.
     *
     * @param p_pointOrigine Position de la com.samdube.echec.piece sur l'com.samdube.echec.echiquier
     */
    Deplacement(Position p_pointOrigine) {
        m_possibilites = new ArrayList<>();
        calculerPossibilites(p_pointOrigine);
    }

    /**
     * Getter pour obtenir la liste d'incrémentations du déplacement
     *
     * @return La liste d'incrémentations
     */
    protected abstract Incrementation[] getIncrementations();

    /**
     * Getter pour obtenir le nombre de pas maximum du déplacement
     *
     * @return Nombre de pas maximum
     */
    protected abstract int getPasMaximum();

    /**
     * Getter pour obtenir les positions disponibles pour deplacements
     *
     * @return Liste de positions disponibles
     */
    public ArrayList<Position> getPossibilites() {
        return m_possibilites;
    }

    /**
     * Methode pour retirer des possibiles de position disponible
     * pour deplacements
     *
     * @param p_positions Possibilites a retirer
     */
    void retirerPossibilites(Position... p_positions) {
        for (Position position : p_positions) {
            m_possibilites.remove(position);
        }
    }

    /**
     * Methode pour ajouter des possibilites de positions disponible
     * pour com.samdube.echec.deplacement.
     *
     * @param p_positions Possibilite a ajouter
     */
    void ajouterPossibilites(Position... p_positions) {
        for (Position position : p_positions) {
            if (!m_possibilites.contains(position)) {
                m_possibilites.add(position);
            }
        }
    }

    /**
     * Méthode qui calcule toutes les possibilités de déplacements
     * pour chaques pas.
     *
     * @param p_pointOrigine Position le point d'origine des possibilités
     */
    public void calculerPossibilites(Position p_pointOrigine) {
        ArrayList<Position> possibilites = new ArrayList<>();

        for (int sequence = 1; sequence <= getPasMaximum(); sequence++) {
            for (Incrementation incrementation : getIncrementations()) {

                int coordonneX = p_pointOrigine.getX() + incrementation.getIncrementationX() * sequence;
                int coordonneY = p_pointOrigine.getY() + incrementation.getIncrementationY() * sequence;

                if (Position.estDansLesLimites(coordonneX, coordonneY)) {
                    possibilites.add(new Position(coordonneX, coordonneY));
                }
            }
        }

        m_possibilites = possibilites;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPasMaximum(), Arrays.hashCode(getIncrementations()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Deplacement)) {
            return false;
        }

        Deplacement o = (Deplacement) obj;
        return o.getPasMaximum() == getPasMaximum() &&
                Arrays.equals(o.getIncrementations(), getIncrementations());
    }
}

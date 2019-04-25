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
    private ArrayList<Position> m_positionsDisponibles;

    /**
     * Constructeur initialisant un nouveau com.samdube.echec.deplacement pour
     * une com.samdube.echec.piece.
     *
     * @param p_pointOrigine Position de la com.samdube.echec.piece sur l'com.samdube.echec.echiquier
     */
    Deplacement(Position p_pointOrigine) {
        m_positionsDisponibles = new ArrayList<>();
        calculerPositionsDisponibles(p_pointOrigine);
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
    public ArrayList<Position> getPositionsDisponible() {
        return m_positionsDisponibles;
    }

    /**
     * Methode pour retirer des possibiles de position disponible
     * pour deplacements
     *
     */
    public void retirerPositionsDisponible(Position p_pointOrigine, Position[] p_pointsContact) {
        ArrayList<Position> positionsInvalides = new ArrayList<>();

        for (Position pointContact : p_pointsContact) {
            int differenceOrigineX = Math.abs(p_pointOrigine.getX() - pointContact.getX());
            int differenceOrigineY = Math.abs(p_pointOrigine.getY() - pointContact.getY());

            for(Position position : m_positionsDisponibles){
                int differenceX = Math.abs(p_pointOrigine.getX() - position.getX());
                int differenceY = Math.abs(p_pointOrigine.getY() - position.getY());
                if(differenceX >= differenceOrigineX && differenceY >= differenceOrigineY){
                    positionsInvalides.add(position);
                }
            }
        }

        for(Position positionInvalide : positionsInvalides){
            m_positionsDisponibles.remove(positionInvalide);
        }
    }

    /**
     * Methode qui retourne le nombre d'incrementation contenu dans
     * la liste incrementation
     *
     * @return Nombre d'incrementation contenue
     */
    public int getNombreIncrementations(){
        return getIncrementations().length;
    }

    /**
     * Methode pour ajouter des possibilites de positions disponible
     * pour com.samdube.echec.deplacement.
     *
     * @param p_positions Possibilite a ajouter
     */
    public void ajouterPositionsDisponible(Position... p_positions) {
        for (Position position : p_positions) {
            if (!m_positionsDisponibles.contains(position)) {
                m_positionsDisponibles.add(position);
            }
        }
    }

    /**
     * Méthode qui calcule toutes les possibilités de déplacements
     * pour chaques pas.
     *
     * @param p_pointOrigine Position le point d'origine des possibilités
     */
    public void calculerPositionsDisponibles(Position p_pointOrigine) {
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

        m_positionsDisponibles = possibilites;
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

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
    private Position[] m_deplacementDisponibles;

    private Pas[] m_pas;

    private int m_pasMaximum;

    /**
     * Constructeur initialisant un nouveau com.samdube.echec.deplacement pour
     * une piece
     */
    Deplacement(Pas[] p_pas, int p_pasMaximum) {
        m_pas = p_pas;
        m_pasMaximum = p_pasMaximum;
    }

    /**
     * Getter pour obtenir les positions disponibles pour deplacements
     *
     * @return Liste de positions disponibles
     */
    public Position[] getDisponibles() {
        return m_deplacementDisponibles;
    }

    public int getNombrePas() {
        return m_pas.length;
    }

    /**
     * Methode pour ajouter des possibilites de positions disponible
     * pour deplacement
     *
     * @param p_positions Possibilite a ajouter
     */
    public void ajouterDeplacementPossibles(Position... p_positions) {
        for (Position position : p_positions) {
            if (!Arrays.asList(m_deplacementDisponibles).contains(position)) {
                Arrays.asList(m_deplacementDisponibles).add(position);
            }
        }
    }

    /**
     * Méthode qui calcule toutes les possibilités de déplacements
     * pour chaques pas.
     *
     * @param p_pointOrigine Position le point d'origine des possibilités
     */
    public void calculerDeplacementPossibles(Position p_pointOrigine, Position[] p_positionsIndisponibles) {
        ArrayList<Position> possibilites = new ArrayList<>();

        for (Pas pas : m_pas) {
            for (int sequence = 1; sequence <= m_pasMaximum; sequence++) {

                int coordonneX = p_pointOrigine.getX() + pas.getDeplacementX() * sequence;
                int coordonneY = p_pointOrigine.getY() + pas.getDeplacementY() * sequence;

                if (Position.estDansLesLimites(coordonneX, coordonneY)) {
                    Position position = new Position(coordonneX, coordonneY);

                    if (Arrays.asList(p_positionsIndisponibles).contains(position)) {
                        break;
                    } else {
                        possibilites.add(new Position(coordonneX, coordonneY));
                    }
                }
            }
        }

        m_deplacementDisponibles = possibilites.toArray(new Position[0]);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_pasMaximum, Arrays.hashCode(m_pas));
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
        return o.m_pasMaximum == m_pasMaximum &&
                Arrays.equals(o.m_pas, m_pas);
    }
}

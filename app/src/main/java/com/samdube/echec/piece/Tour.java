package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementTour;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer une com.samdube.echec.piece de type tour
 *
 * @author Samuel Dube
 */
public class Tour extends Piece {
    private boolean m_peutRoquer = true;

    /**
     * Constructeur initiant une com.samdube.echec.piece tour
     *
     * @param p_position Position de la tour sur l'com.samdube.echec.echiquier
     */
    public Tour(Position p_position) {
        super(p_position, new DeplacementTour(), 't', 2);
    }

    /**
     * Constructeur de base pour une tour
     *
     * @param p_position Position de la tour sur lechiquier
     * @param p_couleur Couleur de la tour
     */
    Tour(Position p_position, CouleurPiece p_couleur) {
        super(p_position, p_couleur, new DeplacementTour(), 't', 2);
    }

    /**
     * Getter pour savoir si la tour peut roquer
     *
     * @return Vrai si la tour peut roquer
     */
    public boolean peutRoquer() {
        return m_peutRoquer;
    }

    @Override
    public boolean deplacer(Position p_position) {
        m_peutRoquer = false;
        return super.deplacer(p_position);
    }
}

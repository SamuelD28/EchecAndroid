package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementPion;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un pion
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class Pion extends Piece {
    private boolean m_peutPromotion = false;

    /**
     * Constructeur initiant un nouveau pion
     *
     * @param p_position Position du pion sur l'com.samdube.echec.echiquier
     */
    public Pion(Position p_position) {
        super(p_position, new DeplacementPion(getCouleurAvecPositionDepart(p_position)), 'p', 1);
    }

    /**
     * Constructeur dun pion
     *
     * @param p_position Position du pion
     * @param p_couleur  Couleur du pion
     */
    public Pion(Position p_position, CouleurPiece p_couleur) {
        super(p_position, p_couleur, new DeplacementPion(p_couleur), 'p', 1);
    }

    @Override
    public boolean deplacer(Position p_position) {

        if (peutDeplacer(p_position)) {
            m_position = p_position;
            if (this.getCouleur() == CouleurPiece.BLANC) {
                if (p_position.getY() == 7) {
                    m_peutPromotion = true;
                }
            } else {
                if (p_position.getY() == 0) {
                    m_peutPromotion = true;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter pour savoir si un pion peut etre promu
     *
     * @return Vrai si on peut le promouvoir
     */
    public boolean getPeutPromotion() {
        return m_peutPromotion;
    }


}

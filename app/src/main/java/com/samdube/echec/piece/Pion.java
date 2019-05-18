package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementPion;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un pion
 *
 * @author Samuel Dube
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

    @Override
    public boolean deplacer(Position p_position) {

        if (peutDeplacer(p_position)) {
            m_position = p_position;
            Notify();

            if (this.getCouleur() == CouleurPiece.BLANC) {
                if(p_position.getY() == 7) {
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

    public boolean getPeutPromotion() {
        return m_peutPromotion;
    }


}

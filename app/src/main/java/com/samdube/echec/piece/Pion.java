package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementPion;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un pion
 *
 * @author Samuel Dube
 */
public class Pion extends Piece {
    /**
     * Constructeur initiant un nouveau pion
     *
     * @param p_position Position du pion sur l'com.samdube.echec.echiquier
     */
    public Pion(Position p_position) {
        super(p_position, new DeplacementPion(getCouleurAvecPositionDepart(p_position)), 'p', 1);
    }
}

package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementRoi;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un roi
 *
 * @author Samuel Dube
 */
public class Roi extends Piece {
    /**
     * Constructeur initiant un nouveau roi
     *
     * @param p_position Position du roi sur l'com.samdube.echec.echiquier
     */
    public Roi(Position p_position) {
        super(p_position, new DeplacementRoi(), 'r', 1);
    }
}

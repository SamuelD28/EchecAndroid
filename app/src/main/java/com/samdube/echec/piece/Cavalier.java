package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementCavalier;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un cavalier
 *
 * @author Samuel Dube
 */
public class Cavalier extends Piece{
    /**
     * Constructeur initiant un nouveau cavalier
     *
     * @param p_position Position du cavalier sur l'com.samdube.echec.echiquier
     */
    public Cavalier(Position p_position) {
        super(p_position, new DeplacementCavalier(p_position), 'c', 2);
    }
}
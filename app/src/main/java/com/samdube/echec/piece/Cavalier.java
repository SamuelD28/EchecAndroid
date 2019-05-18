package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementCavalier;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un cavalier
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class Cavalier extends Piece {
    /**
     * Constructeur initiant un nouveau cavalier
     *
     * @param p_position Position du cavalier sur l'com.samdube.echec.echiquier
     */
    public Cavalier(Position p_position) {
        super(p_position, new DeplacementCavalier(), 'c', 2);
    }

    /**
     * Constructeur utiliser pour la promotion d'un Pion
     *
     * @param p_position La position du Cavalier
     * @param p_couleur  La couleur du Cavalier
     */
    public Cavalier(Position p_position, CouleurPiece p_couleur) {
        super(p_position, p_couleur, new DeplacementCavalier(), 'c', 2);
    }
}

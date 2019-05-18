package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementFou;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un fou
 *
 * @author Samuel Dube
 */
public class Fou extends Piece{
    /**
     * Classe initiant un nouveau fou
     *
     * @param p_position Position du fou sur l'com.samdube.echec.echiquier
     */
    public Fou(Position p_position) {
        super(p_position, new DeplacementFou(), 'f', 2);
    }

    public Fou(Position p_position, CouleurPiece p_couleur) {
        super(p_position, p_couleur, new DeplacementFou(), 'f', 2);
    }
}

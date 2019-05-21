package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementFou;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un fou
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public class Fou extends Piece {
    /**
     * Classe initiant un nouveau fou
     *
     * @param p_position Position du fou sur l'echiquier
     */
    public Fou(Position p_position) {
        super(p_position, new DeplacementFou(), 'f', 2);
    }

    /**
     * Constructeur dune piece avec position et couleur
     *
     * @param p_position Position de la piece
     * @param p_couleur  Couleur de la piece
     */
    public Fou(Position p_position, CouleurPiece p_couleur) {
        super(p_position, p_couleur, new DeplacementFou(), 'f', 2);
    }
}

package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementReine;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer une reine
 *
 * @author Samuel Dube
 */
public class Reine extends Piece {
    /**
     * Constructeur initiant une nouvelle reine
     *
     * @param p_position Position de la reine sur l'com.samdube.echec.echiquier
     */
    public Reine(Position p_position) {
        super(p_position, new DeplacementReine(p_position), 'q', 3);
    }
}

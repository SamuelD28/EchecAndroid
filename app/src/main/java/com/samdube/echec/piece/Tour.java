package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementTour;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer une com.samdube.echec.piece de type tour
 *
 * @author Samuel Dube
 */
public class Tour extends Piece {
    /**
     * Constructeur initiant une com.samdube.echec.piece tour
     *
     * @param p_position Position de la tour sur l'com.samdube.echec.echiquier
     */
    public Tour(Position p_position) {
        super(p_position, new DeplacementTour(), 't', 2);
    }

    public Tour(Position p_position, CouleurPiece p_couleur) {
        super(p_position, p_couleur, new DeplacementTour(), 't', 2);
    }
}

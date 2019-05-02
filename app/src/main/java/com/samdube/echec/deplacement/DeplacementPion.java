package com.samdube.echec.deplacement;

import static com.samdube.echec.piece.Piece.*;

/**
 * Classe de déplacements possibles pour un pion
 *
 * @author Samuel Colassin
 */
public class DeplacementPion extends Deplacement {
    /**
     * Constructeur initiant un deplacement pour un pion.
     * Inverse la direction du pas si la couleur du pion n'est
     * pas blanc.
     *
     * @param p_couleur Couleur de la pièce associé au déplacement
     */
    public DeplacementPion(CouleurPiece p_couleur) {
        super((p_couleur == CouleurPiece.BLANC) ?
                new Pas[]{new Pas(0, 1)} :
                new Pas[]{new Pas(0, -1)}, 1);
    }
}

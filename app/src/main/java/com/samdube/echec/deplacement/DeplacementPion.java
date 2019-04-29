package com.samdube.echec.deplacement;

import static com.samdube.echec.piece.Piece.*;

/**
 * Classe de déplacements possibles pour un pion
 *
 * @author Samuel Colassin
 */
public class DeplacementPion extends Deplacement {
    /**
     * Constructeur initiant un deplacement pour un pion
     */
    public DeplacementPion(CouleurPiece p_couleur) {
        super((p_couleur == CouleurPiece.BLANC)?
                new Pas[]{new Pas(0, 1)}:
                new Pas[] {new Pas(0,-1)},1);
    }
}

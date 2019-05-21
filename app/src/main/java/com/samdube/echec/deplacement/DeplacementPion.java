package com.samdube.echec.deplacement;

import com.samdube.echec.echiquier.Position;

import java.util.Arrays;

import static com.samdube.echec.piece.Piece.*;

/**
 * Classe de déplacements possibles pour un pion
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class DeplacementPion extends Deplacement {
    private final CouleurPiece m_couleur;

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
        m_couleur = p_couleur;
    }

    @Override
    public void calculerDeplacementPossibles(Position p_pointOrigine, Position[] p_collisionsInclusives, Position[] p_collisionsExclusives) {
        super.calculerDeplacementPossibles(p_pointOrigine, p_collisionsInclusives, p_collisionsExclusives);

        if (p_pointOrigine.getY() == 6 && m_couleur == CouleurPiece.NOIR &&
                !Arrays.asList(p_collisionsInclusives).contains(new Position(p_pointOrigine.getX(), p_pointOrigine.getY() - 1)) &&
                !Arrays.asList(p_collisionsInclusives).contains(new Position(p_pointOrigine.getX(), p_pointOrigine.getY() - 2))) {
            this.ajouterDeplacementPossibles(new Position(p_pointOrigine.getX(), p_pointOrigine.getY() - 2));
        } else if (p_pointOrigine.getY() == 1 && m_couleur == CouleurPiece.BLANC &&
                !Arrays.asList(p_collisionsInclusives).contains(new Position(p_pointOrigine.getX(), p_pointOrigine.getY() + 1)) &&
                !Arrays.asList(p_collisionsInclusives).contains(new Position(p_pointOrigine.getX(), p_pointOrigine.getY() + 2))) {
            this.ajouterDeplacementPossibles(new Position(p_pointOrigine.getX(), p_pointOrigine.getY() + 2));
        }

        for (Position p : p_collisionsInclusives) {
            if (m_couleur == CouleurPiece.BLANC) {
                if (pieceAManger(p, p_pointOrigine.getX() - 1, p_pointOrigine.getY() + 1)) {
                    this.ajouterDeplacementPossibles(p);
                }

                if (pieceAManger(p, p_pointOrigine.getX() + 1, p_pointOrigine.getY() + 1)) {
                    this.ajouterDeplacementPossibles(p);
                }

                if (pieceAManger(p, p_pointOrigine.getX(), p_pointOrigine.getY() + 1)) {
                    this.retirerDeplacementPossibles(p);
                }

            } else {
                if (pieceAManger(p, p_pointOrigine.getX() - 1, p_pointOrigine.getY() - 1)) {
                    this.ajouterDeplacementPossibles(p);
                }

                if (pieceAManger(p, p_pointOrigine.getX() + 1, p_pointOrigine.getY() - 1)) {
                    this.ajouterDeplacementPossibles(p);
                }

                if (pieceAManger(p, p_pointOrigine.getX(), p_pointOrigine.getY() - 1)) {
                    this.retirerDeplacementPossibles(p);
                }
            }
        }
    }

    /**
     * Permet de savoir s'il y a une pièce à manger à la position calculée
     *
     * @param p_position Position qui est potentiellement posible de manger
     * @param p_x        Coordonnée x de la position à vérifier
     * @param p_y        Coordonnée y de la position à vérifier
     * @return Vrai si il y a une position à manger sinon faux
     */
    private boolean pieceAManger(Position p_position, int p_x, int p_y) {
        Position position = null;
        if (Position.estDansLesLimites(p_x, p_y)) {
            position = new Position(p_x, p_y);
        }
        return p_position.equals(position);
    }
}

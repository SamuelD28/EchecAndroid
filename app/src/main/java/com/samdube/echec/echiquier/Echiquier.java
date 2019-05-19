package com.samdube.echec.echiquier;

import com.samdube.echec.piece.Cavalier;
import com.samdube.echec.piece.Fou;
import com.samdube.echec.piece.Piece;
import com.samdube.echec.piece.Piece.CouleurPiece;
import com.samdube.echec.piece.Pion;
import com.samdube.echec.piece.Reine;
import com.samdube.echec.piece.Roi;
import com.samdube.echec.piece.Tour;

import java.util.ArrayList;

import static com.samdube.echec.piece.Piece.CouleurPiece.*;

/**
 * Echiquier de base permettant l'ajout de pion
 *
 * @author Samuel Colassin
 * @author Samuel Dube
 */
public class Echiquier {
    /**
     * Constante contenant la taille largeur/longeur de l'échiquier
     */
    public final static int TAILLE_ECHIQUIER = 8;

    private final Position[] m_cases = new Position[TAILLE_ECHIQUIER * TAILLE_ECHIQUIER];

    private final ArrayList<Piece> m_pieces = new ArrayList<>();

    private ArrayList<Position> m_positionsPiecesBlanches = new ArrayList<>();

    private ArrayList<Position> m_positionsPiecesNoir = new ArrayList<>();

    /**
     * Piece qui est présentement en cours de changement de type de pièce
     */
    private Piece m_pionEnPromotion = null;

    /**
     * Indique s'il y une pièce qui est en cours de promotion dans l'échiquier
     */
    private boolean m_pionEnCoursDePromotion = false;

    private boolean m_roiNoirEnEchec = false;

    private boolean m_roiBlancEnEchec = false;

    /**
     * Constructeur qui initialise l'échiquier
     */
    public Echiquier() {
        initialiser();
        //calculerCollisionsPieces();
        diviserPiecesDeChaqueCouleur();
    }

    /**
     * Permet de savoir si la partie est échec et mat dans l'échiquier
     *
     * @return Vrai si un roi est manquant dans l'échiquier
     */
    public boolean estEchecEtMat() {
        int cpt = 0;
        for (Piece p : m_pieces) {
            if (p instanceof Roi) {
                cpt++;
            }
        }
        return cpt < 2;
    }

    /**
     * Methode qui initialise l'echiquier avec des m_cases vides
     * representer avec des X.
     */
    private void initialiser() {
        int i = 0;
        for (int y = 0; y < TAILLE_ECHIQUIER; y++) {
            for (int x = 0; x < TAILLE_ECHIQUIER; x++) {
                Position position = new Position(x, y);
                Piece piece = obtenirPiecePositionDepart(position);
                m_cases[i] = position;
                if (piece != null) {
                    m_pieces.add(piece);
                }
                i++;
            }
        }
    }

    /**
     * Permet d'actualiser la position d'une piece apres un déplacement de celle-ci
     *
     * @param p_piece    La piece qui change de position
     * @param p_position La nouvelle position de la pièce
     */
    private void actualiserPositionsPieces(Piece p_piece, Position p_position) {
        if (p_piece.getCouleur() == BLANC) {
            m_positionsPiecesBlanches.remove(p_piece.getPosition());
            m_positionsPiecesBlanches.add(p_position);
        } else {
            m_positionsPiecesNoir.remove(p_piece.getPosition());
            m_positionsPiecesNoir.add(p_position);
        }
    }

    /**
     * Permet d'avoir toute les pièces de l'échiquier blanches et noirs dans
     * deux listes distinctes pour les manipulations.
     */
    private void diviserPiecesDeChaqueCouleur() {
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == BLANC) {
                m_positionsPiecesBlanches.add(piece.getPosition());
            } else {
                m_positionsPiecesNoir.add(piece.getPosition());
            }
        }
    }

    /**
     * Permet d'obtenir la liste des positions de toute les pièce d'une certaine couleur
     *
     * @param p_couleur la couleur des pièces qu'on désire les positions
     * @return Une array de toute les positions des pièces de la couleur désirée
     */
    private Position[] getPositionsPieces(CouleurPiece p_couleur) {
        if (p_couleur == BLANC) {
            return m_positionsPiecesBlanches.toArray(new Position[0]);
        } else {
            return m_positionsPiecesNoir.toArray(new Position[0]);
        }
    }

    /**
     * Permet de calculer toutes les possibilités de collisions pour toutes les
     * pièces de l'échiquier
     */
    public void calculerCollisionsPieces() {
        Position[] positionsPiecesBlanches = getPositionsPieces(BLANC);
        Position[] positionsPiecesNoires = getPositionsPieces(NOIR);

        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == BLANC) {
                piece.calculerDeplacementPossibles(positionsPiecesBlanches, positionsPiecesNoires);
            } else {
                piece.calculerDeplacementPossibles(positionsPiecesNoires, positionsPiecesBlanches);
            }
        }
    }

    /**
     * Permet d'obtenir la pièce à mettre sur l'échiquier
     * selon la position dans celui-ci.
     *
     * @return Le type de pièce à mettre dans l'échiquier
     */
    private Piece obtenirPiecePositionDepart(Position p_position) {
        Piece piece = null;

        int x = p_position.getX();
        int y = p_position.getY();

        if (y == 1 || y == 6) {
            return new Pion(new Position(x, y));
        } else if (y == 0 || y == 7) {
            if (x == 0 || x == 7) {
                return new Tour(new Position(x, y));
            } else if (x == 1 || x == 6) {
                return new Cavalier(new Position(x, y));
            } else if (x == 2 || x == 5) {
                return new Fou(new Position(x, y));
            } else if (x == 3) {
                return new Reine(new Position(x, y));
            } else {
                return new Roi(new Position(x, y));
            }
        }

        return piece;
    }

    /**
     * Obtient le nombre de pièce dans l'échiquier
     *
     * @return Le nombre de pièce dans l'échiquier
     */
    int getNombrePieces() {
        return m_pieces.size();
    }

    /**
     * Obtient le nombre de pièce dans l'échiquier d'une certaine couleur
     *
     * @param p_couleur La couleur dont on veut le nombre de pièce
     * @return Le nombre de pièce dans l'échiquier d'une certaine couleur
     */
    int getNombrePieces(CouleurPiece p_couleur) {
        int nombrePiece = 0;
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur) {
                nombrePiece++;
            }
        }
        return nombrePiece;
    }

    /**
     * Permet d'avoir le nombre d'occurence d'une pièce dans l'échiquier courant
     *
     * @param p_couleur la couleur de la pièce désirée
     * @return le nombre d'occurence de la pièce dans le jeu
     */
    int getNombrePieces(CouleurPiece p_couleur, Class<? extends Piece> p_typePiece) {
        int nombrePiece = 0;
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur && piece.getClass() == p_typePiece) {
                nombrePiece++;
            }
        }
        return nombrePiece;
    }

    /**
     * Permet d'obtenir la référence d'un pion
     * à une position donnée.
     *
     * @param p_position La position du pion désiré
     * @return Le pion à la position donnée
     */
    public Piece getPiece(Position p_position) {
        Piece pieceTrouve = null;
        for (Piece piece : m_pieces) {
            if (piece.getPosition().equals(p_position)) {
                pieceTrouve = piece;
                pieceTrouve.calculerDeplacementPossibles(getPositionsPieces(BLANC), getPositionsPieces(NOIR));
                break;
            }
        }
        return pieceTrouve;
    }

    /**
     * Permet de déplacer une pièce dans l'échiquier
     *
     * @param p_piece    La pièce qu'on veut déplacer
     * @param p_nouvelle La position qu'on désire pour la pièce
     * @return Vrai si le déplacement à fonctionné sinon faux
     */
    public boolean deplacerPiece(Piece p_piece, Position p_nouvelle) {
        if (p_piece.peutDeplacer(p_nouvelle)) {
            Piece piecePourPrise = getPiece(p_nouvelle);

            if (piecePourPrise != null) {
                m_pieces.remove(piecePourPrise);
                if (piecePourPrise.getCouleur() == BLANC) {
                    m_positionsPiecesBlanches.remove(piecePourPrise.getPosition());
                } else {
                    m_positionsPiecesNoir.remove(piecePourPrise.getPosition());
                }
            }

            actualiserPositionsPieces(p_piece, p_nouvelle);
            p_piece.deplacer(p_nouvelle);

            if (p_piece instanceof Pion && ((Pion) p_piece).getPeutPromotion()) {
                m_pionEnCoursDePromotion = true;
                m_pionEnPromotion = p_piece;
            }

            p_piece.calculerDeplacementPossibles(getPositionsPieces(BLANC), getPositionsPieces(NOIR));
            for (Position p: p_piece.getDeplacementsPossibles()) {
                if (p_piece.getCouleur() == BLANC && m_positionsPiecesNoir.contains(p)) {
                    for (Piece piece: m_pieces) {
                        if (piece.getPosition() == p && piece instanceof Roi) {
                            // Est en echec Roi Noir
                            m_roiNoirEnEchec = true;
                        }
                    }
                }
                else if (p_piece.getCouleur() == NOIR && m_positionsPiecesBlanches.contains(p)) {
                    for (Piece piece: m_pieces) {
                        if (piece.getPosition() == p && piece instanceof Roi) {
                            // Est en echec Roi blanc
                            m_roiBlancEnEchec = true;
                        }
                    }
                }
            }


            return true;
        } else {
            return false;
        }
    }

    /**
     * Permet de promouvoir un pion
     */
    public void promouvoirPion(/*char p_representation*/) {
        Position p = m_pionEnPromotion.getPosition();
        CouleurPiece c = m_pionEnPromotion.getCouleur();

//        switch (p_representation) {
//            case 'r' : m_pieces.add(new Reine(p, c)); break;
//            case 'f' : m_pieces.add(new Fou(p, c)); break;
//            case 'c' : m_pieces.add(new Cavalier(p, c)); break;
//            case 't' : m_pieces.add(new Tour(p, c)); break;
//            default: m_pieces.add(new Reine(p, c)); break;
//        }

        m_pieces.remove(m_pionEnPromotion);
        m_pieces.add(new Reine(p, c));
        m_pionEnPromotion = null;
        m_pionEnCoursDePromotion = false;
    }

    /**
     * Permet de savoir s'il y a un pion en cours de promotion dans l'échiquier
     *
     * @return Vrai si un pion est en cours de promotion
     */
    public boolean getPionEnCourDePromotion() {
        return m_pionEnCoursDePromotion;
    }

    /**
     * Obtient toute les positions de l'échiquier
     *
     * @return Les position dans l'échiquier
     */
    public Position[] getCases() {
        return m_cases;
    }
}

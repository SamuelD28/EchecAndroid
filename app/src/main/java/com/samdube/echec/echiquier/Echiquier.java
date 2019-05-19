package com.samdube.echec.echiquier;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.piece.Cavalier;
import com.samdube.echec.piece.Fou;
import com.samdube.echec.piece.Piece;
import com.samdube.echec.piece.Piece.CouleurPiece;
import com.samdube.echec.piece.Pion;
import com.samdube.echec.piece.Reine;
import com.samdube.echec.piece.Roi;
import com.samdube.echec.piece.Tour;

import java.util.ArrayList;
import java.util.Arrays;

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

    /**
     * Piece qui est présentement en cours de changement de type de pièce
     */
    private Piece m_pionEnPromotion = null;

    /**
     * Indique s'il y une pièce qui est en cours de promotion dans l'échiquier
     */
    private boolean m_pionEnCoursDePromotion = false;

    /**
     * Constructeur qui initialise l'échiquier
     */
    public Echiquier() {
        initialiser();
        calculerTousDeplacements();
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

    private Position[] obtenirPositionsPieces(CouleurPiece p_couleur)
    {
        ArrayList<Position> positions = new ArrayList<>();
        for (Piece piece : m_pieces){
            if(piece.getCouleur() == p_couleur){
                positions.add(piece.getPosition());
            }
        }
        return positions.toArray(new Position[0]);
    }

    /**
     * Permet de calculer toutes les possibilités de collisions pour toutes les
     * pièces de l'échiquier
     */
    public void calculerTousDeplacements() {
        Position[] positionsPiecesBlanches = obtenirPositionsPieces(BLANC);
        Position[] positionsPiecesNoires = obtenirPositionsPieces(NOIR);
        for (Piece piece : m_pieces) {
            piece.calculerDeplacementPossibles(positionsPiecesBlanches, positionsPiecesNoires);
        }
        ajusterDeplacementRoi(BLANC);
        ajusterDeplacementRoi(NOIR);
    }


    /**
     * Methode pour obtenir un roi d'une couleur
     * donne.
     *
     * @param p_couleur Couleur du roi a trouver
     * @return Roi trouve
     */
    private Roi getRoi(CouleurPiece p_couleur) {
        Roi roi = null;
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur && piece instanceof Roi){
                roi = (Roi)piece;
            }
        }
        return roi;
    }

    public void ajusterDeplacementRoi(CouleurPiece p_couleur){
        CouleurPiece couleurInverse = (p_couleur == BLANC)? NOIR : BLANC;
        Position[] champsAction = getChampsDactions(couleurInverse);
        Roi roi = getRoi(p_couleur);
        Deplacement deplacementRoi = roi.getDeplacement();

        if(Arrays.asList(champsAction).contains(roi.getPosition())){
            roi.setEstEchec(true);
        }else{
            roi.setEstEchec(false);
        }
        deplacementRoi.retirerDeplacementPossibles(champsAction);

        if(deplacementRoi.getDisponibles().length == 0 && roi.estEchec()){
            roi.setEchecEtMath(true);
        }
    }

    public boolean estEchec(CouleurPiece p_couleur){
        return getRoi(p_couleur).estEchec();
    }

    public boolean estEchecEtMath(CouleurPiece p_couleur){
        return getRoi(p_couleur).estEchecEtMath();
    }

    /**
     * Permet d'obtenir la pièce à mettre sur l'échiquier
     * selon la position dans celui-ci.
     *
     * @param p_position Position de depart de la piece
     * @return Le type de pièce à mettre dans l'échiquier
     */
    private Piece obtenirPiecePositionDepart(Position p_position) {
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

        return null;
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
     * @param p_couleur   la couleur de la pièce désirée
     * @param p_typePiece type de la piece a aller chercher
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


    public Position[] getChampsDactions(CouleurPiece p_couleur) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur) {
                pieces.add(piece);
            }
        }

        ArrayList<Position> positions = new ArrayList<>();
        for (Piece piece : pieces) {
            positions.addAll(Arrays.asList(piece.getDeplacementsPossibles()));
        }

        return positions.toArray(new Position[0]);
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
                calculerTousDeplacements();
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
            }

            p_piece.deplacer(p_nouvelle);
            calculerTousDeplacements();

            return true;
        } else {
            return false;
        }
    }

    /**
     * Permet de promouvoir un pion
     */
    public void promouvoirPion() {
        Position p = m_pionEnPromotion.getPosition();
        CouleurPiece c = m_pionEnPromotion.getCouleur();
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

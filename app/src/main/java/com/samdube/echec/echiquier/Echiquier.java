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
import java.util.Arrays;

import static com.samdube.echec.piece.Piece.CouleurPiece.*;

/**
 * Echiquier de base permettant l'ajout de pion
 *
 * @author Samuel Colassin
 * @author Samuel Dube
 */
public class Echiquier {
    public final static int TAILLE_ECHIQUIER = 8;

    private final Position[] m_cases = new Position[TAILLE_ECHIQUIER * TAILLE_ECHIQUIER];

    private final ArrayList<Piece> m_pieces = new ArrayList<>();

    private ArrayList<Position> m_positionsPiecesBlanches = new ArrayList<>();

    private ArrayList<Position> m_positionsPiecesNoir = new ArrayList<>();

    /**
     * Constructeur qui initialise l'échiquier
     */
    public Echiquier() {
        initialiser();
        calculerCollisionsPieces();
        actualiserPositionsPieces();
    }

    /**
     * Methode qui initialise l'com.samdube.echec.echiquier avec des m_cases vides
     * representer ave des X.
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

    private void actualiserPositionsPieces(Piece p_piece, Position p_position){
        if(p_piece.getCouleur() == BLANC){
            m_positionsPiecesBlanches.remove(p_piece.getPosition());
            m_positionsPiecesBlanches.add(p_position);
        }
        else
        {
            m_positionsPiecesNoir.remove(p_piece.getPosition());
            m_positionsPiecesNoir.add(p_position);
        }
    }

    private void actualiserPositionsPieces(){
        for(Piece piece : m_pieces){
            if(piece.getCouleur() == BLANC){
                m_positionsPiecesBlanches.add(piece.getPosition());
            }
            else{
                m_positionsPiecesNoir.add(piece.getPosition());
            }
        }
    }

    private Position[] getPositionsPieces(CouleurPiece p_couleur){
        if(p_couleur == BLANC){
            return m_positionsPiecesBlanches.toArray(new Position[0]);
        }else{
            return m_positionsPiecesNoir.toArray(new Position[0]);
        }
    }

    private void calculerCollisionsPieces() {
        Position[] positionsPiecesBlanches = getPositionsPieces(BLANC);
        Position[] positionsPiecesNoires = getPositionsPieces(NOIR);

        for (Piece piece : m_pieces) {
            if(piece.getCouleur() == BLANC){
                piece.calculerDeplacementPossibles(positionsPiecesBlanches, positionsPiecesNoires);
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
            return new Pion(new Position(x,y));
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
     * @param p_couleur   la couleur de la pièce désirée
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
     * Methode permettant d'obtenir la référence d'un pion
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

    public boolean deplacerPiece(Piece p_piece, Position p_nouvelle) {
        if (p_piece.peutDeplacer(p_nouvelle)) {

            Piece piecePourPrise = getPiece(p_nouvelle);

            if(piecePourPrise != null){
                m_pieces.remove(piecePourPrise);
                if(piecePourPrise.getCouleur() == BLANC){
                    m_positionsPiecesBlanches.remove(piecePourPrise.getPosition());
                }else{
                    m_positionsPiecesNoir.remove(piecePourPrise.getPosition());
                }
            }

            actualiserPositionsPieces(p_piece, p_nouvelle);
            p_piece.deplacer(p_nouvelle);

            return true;
        } else {
            return false;
        }
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

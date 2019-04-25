package com.samdube.echec.echiquier;

import com.samdube.echec.piece.*;
import com.samdube.echec.piece.Piece.CouleurPiece;

import java.util.ArrayList;

/**
 * Echiquier de base permettant l'ajout de pion
 *
 * @author Samuel Colassin
 * @author Samuel Dube
 */
class Echiquier {
    final static int TAILLE_ECHIQUIER = 8;

    private final Position[] m_echiquier = new Position[TAILLE_ECHIQUIER * TAILLE_ECHIQUIER];

    private final ArrayList<Piece> m_pieces = new ArrayList<>();

    /**
     * Constructeur qui initialise l'échiquier
     */
    Echiquier() {
        initialiser();
    }

    /**
     * Methode qui initialise l'com.samdube.echec.echiquier avec des m_echiquier vides
     * representer ave des X.
     */
    private void initialiser() {
        for (int y = 0; y < TAILLE_ECHIQUIER; y++) {
            for (int x = 0; x < TAILLE_ECHIQUIER; x++) {
                m_echiquier[x + y] = new Position(x, y);
                switch (y) {
                    case 1:
                    case 6:
                        m_pieces.add(new Pion(new Position(x, y)));
                        break;
                    case 0:
                    case 7:
                        m_pieces.add(obtenirPiecePositionDepart(x, y));
                        break;
                }
            }
        }
    }

    /**
     * Permet d'obtenir la pièce à mettre sur l'échiquier
     * selon la position dans celui-ci.
     *
     * @param x L'axe des x dans l'échiquier
     * @param y L'axe des y dans l'échiquier
     * @return Le type de pièce à mettre dans l'échiquier
     */
    private Piece obtenirPiecePositionDepart(int x, int y) {
        switch (x) {
            case 0:
            case 7:
                return new Tour(new Position(x, y));
            case 1:
            case 6:
                return new Cavalier(new Position(x, y));
            case 2:
            case 5:
                return new Fou(new Position(x, y));
            case 3:
                return new Reine(new Position(x, y));
            default:
                return new Roi(new Position(x, y));
        }
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
        return (int) m_pieces.stream().filter(p -> p.getCouleur() == p_couleur).count();
    }

    /**
     * Permet d'avoir le nombre d'occurence d'une pièce dans l'échiquier courant
     *
     * @param p_couleur   la couleur de la pièce désirée
     * @param p_typePiece TODO
     * @return le nombre d'occurence de la pièce dans le jeu
     */
    int getNombrePieces(CouleurPiece p_couleur, Class<? extends Piece> p_typePiece) {
        return (int) m_pieces.stream()
                .filter(p -> p.getCouleur() == p_couleur && p.getClass() == p_typePiece)
                .count();
    }

    /**
     * Methode permettant d'obtenir la référence d'un pion
     * à une position donnée.
     *
     * @param p_position La position du pion désiré
     * @return Le pion à la position donnée
     */
    Piece getPiece(Position p_position) {
        return m_pieces.stream()
                .filter(p -> p.getPosition().equals(p_position))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtient toute les positions de l'échiquier
     *
     * @return Les position dans l'échiquier
     */
    public Position[] getEchiquier() {
        return m_echiquier;
    }
}

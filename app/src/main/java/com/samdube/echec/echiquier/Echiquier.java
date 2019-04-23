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
public class Echiquier {
    final static int TAILLE_ECHIQUIER = 8;

    private final Position[] m_echiquier = new Position[TAILLE_ECHIQUIER * TAILLE_ECHIQUIER];

    private final ArrayList<Piece> m_pieces = new ArrayList<>();


    Echiquier() {
        initialiser();
    }

    /**
     * Methode qui initialise l'com.samdube.echec.echiquier avec des m_echiquier vides
     * representer ave des X.
     */
    private void initialiser() {

        //m_pieces.stream().filter(p -> p.getPosition()).toArray(new Position[0]);

        for (int y = 0; y < TAILLE_ECHIQUIER; y++) {
            for (int x = 0; x < TAILLE_ECHIQUIER; x++) {
                m_echiquier[x + y] = new Position(x, y);
                switch(y){
                    case 1 :
                    case 6 : m_pieces.add(new Pion(new Position(x,y))); break;
                    case 0 :
                    case 7: m_pieces.add(obtenirPiecePositionDepart(x,y)); break;
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
            case 4:
                return new Roi(new Position(x, y));
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getNombrePieces(){
        return m_pieces.size();
    }

    public int getNombrePieces(CouleurPiece p_couleur) {
        return 0;
    }

    /**
     * Permet d'avoir le nombre d'occurence d'une pièce dans l'échiquier courant
     *
     * @param p_couleur la couleur de la pièce désirée
     * @return le nombre d'occurence de la pièce dans le jeu
     */
    public int getNombrePieces(CouleurPiece p_couleur, Class<? extends Piece> p_typePiece) {
        return 2;
    }

    /**
     * Methode permettant d'obtenir la référence d'un pion
     * à une position donnée.
     *
     * @param p_position La position du pion désiré
     * @return Le pion à la position donnée
     */
    public Piece getPiece(Position p_position) {
        return m_pieces.stream()
                .filter(p -> p.getPosition().equals(p_position))
                .findFirst()
                .orElse(null);
    }

    public Position[] getEchiquier() {
        return m_echiquier;
    }

    public int getTaille() {
        return TAILLE_ECHIQUIER;
    }
}

package com.samdube.echec.piece;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.echiquier.Position;

import java.util.Arrays;
import java.util.Objects;

/**
 * Classe abstraite permettant de creer de nouveau
 * type de com.samdube.echec.piece a utiliser pour le jeu d'echec.
 *
 * @author Samuel Dube
 */
public abstract class Piece {
    private final char m_representation;

    private final int m_force;

    private boolean m_jamaisJouer = true;

    Position m_position;

    private final Deplacement m_deplacement;

    private final CouleurPiece m_couleur;

    /**
     * Constructeur initiant une nouvelle piece. Accessible
     * seulement par une classe qui implemente la classe abstraite
     *
     * @param p_position       Position de la com.samdube.echec.piece sur l'com.samdube.echec.echiquier
     * @param p_deplacement    Deplacement associer a la com.samdube.echec.piece
     * @param p_representation Representation textuelle de la com.samdube.echec.piece
     * @param p_force          Force de la com.samdube.echec.piece
     */
    Piece(Position p_position, Deplacement p_deplacement, char p_representation, int p_force) {
        m_couleur = getCouleurAvecPositionDepart(p_position);
        m_representation = p_representation;
        m_force = p_force;
        m_position = p_position;
        m_deplacement = p_deplacement;
    }

    /**
     * Constructeur de la piece avec tous les paramètres, principalement utilisé
     * pour la promotion
     *
     * @param p_position       Position de la piece
     * @param p_couleur        Couleur de la piece
     * @param p_deplacement    Deplacement de la piece
     * @param p_representation Representation de la piece
     * @param p_force          Force de la piece
     */
    Piece(Position p_position, CouleurPiece p_couleur, Deplacement p_deplacement, char p_representation, int p_force) {
        m_couleur = p_couleur;
        m_representation = p_representation;
        m_force = p_force;
        m_position = p_position;
        m_deplacement = p_deplacement;
    }

    /**
     * Methode qui retourne la couleur de la piece
     * en fonction de sa position de depart
     *
     * @param p_position Position de depart de la piece
     * @return Couleur de la piece
     */
    static CouleurPiece getCouleurAvecPositionDepart(Position p_position) {
        return (p_position.getY() > 3) ? CouleurPiece.NOIR : CouleurPiece.BLANC;
    }

    /**
     * Methode permettant de changer la position de la piece
     *
     * @param p_position Position ou la piece doit etre deplacer
     * @return Vrai si la piece a ete deplacer a la position
     */
    public boolean deplacer(Position p_position) {
        if (peutDeplacer(p_position)) {
            m_position = p_position;
            m_jamaisJouer = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Methode qui indique si le deplacement desirer est realisable
     *
     * @param p_position Position ou deplacer la piece
     * @return Vrai si la piece peut se deplaceer
     */
    public boolean peutDeplacer(Position p_position) {
        return !m_position.equals(p_position) && Arrays.asList(m_deplacement.getDisponibles()).contains(p_position);
    }

    /**
     * Methode qui calcule et assigne tous les deplacements possibles a la piece
     *
     * @param p_positionsPiecesBlanches Positions des pieces blanches
     * @param p_positionsPiecesNoires   Position des pieces noires
     */
    public void calculerDeplacementPossibles(Position[] p_positionsPiecesBlanches, Position[] p_positionsPiecesNoires) {
        if (m_couleur == CouleurPiece.BLANC) {
            m_deplacement.calculerDeplacementPossibles(m_position, p_positionsPiecesNoires, p_positionsPiecesBlanches);
        } else {
            m_deplacement.calculerDeplacementPossibles(m_position, p_positionsPiecesBlanches, p_positionsPiecesNoires);
        }
    }

    /**
     * Getter qui retourne tous les deplacements possibles de la piece
     *
     * @return Liste de tous les deplacemnt possibles
     */
    public Position[] getDeplacementsPossibles() {
        return m_deplacement.getDisponibles();
    }

    /**
     * Getter qui retourne le deplacement de la piece
     *
     * @return Deplacement de la piece
     */
    public Deplacement getDeplacement() {
        return m_deplacement;
    }

    /**
     * Getter pour obtenir la representation textuelle
     *
     * @return Representation textuelle
     */
    public char getRepresentation() {
        return m_representation;
    }

    /**
     * Getter pour savoir si la piece na jamais jouer encore
     *
     * @return Vrai si la piece na jamais jouer
     */
    public boolean jamaisJouer() {
        return !m_jamaisJouer;
    }

    /**
     * Getter pour obtenir la force de la com.samdube.echec.piece
     *
     * @return Force de la com.samdube.echec.piece
     */
    public int getForce() {
        return m_force;
    }

    /**
     * Getter pour obtenir la couleur de la com.samdube.echec.piece
     *
     * @return COuleur de la com.samdube.echec.piece
     */
    public CouleurPiece getCouleur() {
        return m_couleur;
    }

    /**
     * Getter pour obtenir la position de la com.samdube.echec.piece
     *
     * @return Position de la com.samdube.echec.piece
     */
    public Position getPosition() {
        return m_position;
    }


    /**
     * Enum pour les couleurs possibles pour les pieces
     */
    public enum CouleurPiece {
        /**
         * Couleur blanche
         */
        BLANC,
        /**
         * Couleur noir
         */
        NOIR
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return piece.m_position.equals(m_position) &&
                piece.m_couleur == m_couleur &&
                piece.m_deplacement.equals(m_deplacement) &&
                piece.m_force == m_force &&
                piece.m_representation == m_representation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_position, m_couleur);
    }

    /**
     * Permet de créer un copie d'une pièce
     *
     * @param p_piece La pièce à copier
     * @return La copie de la pièce
     */
    public static Piece creerCopie(Piece p_piece) {
        switch (p_piece.getRepresentation()) {
            case 'r':
                return new Roi(new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY()), p_piece.getCouleur());
            case 'q':
                return new Reine(new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY()), p_piece.getCouleur());
            case 'f':
                return new Fou(new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY()), p_piece.getCouleur());
            case 'c':
                return new Cavalier(new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY()), p_piece.getCouleur());
            case 't':
                return new Tour(new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY()), p_piece.getCouleur());
            case 'p':
                return new Pion(new Position(p_piece.getPosition().getX(), p_piece.getPosition().getY()), p_piece.getCouleur());
        }
        return null;
    }
}

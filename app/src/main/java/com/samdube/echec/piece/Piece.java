package com.samdube.echec.piece;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.utils.IObservable;
import com.samdube.echec.utils.IObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe abstraite permettant de creer de nouveau
 * type de com.samdube.echec.piece a utiliser pour le jeu d'echec.
 *
 * @author Samuel Dube
 */
public abstract class Piece implements IObservable {
    private final ArrayList<IObserver> m_observateurs;

    private final char m_representation;

    private final int m_force;

    private Position m_position;

    private final Deplacement m_deplacement;

    private final CouleurPiece m_couleur;

    /**
     * Constructeur initiant une nouvelle com.samdube.echec.piece. Accessible
     * seuleument par une classe qui implemente la classe abstraite
     *
     * @param p_position       Position de la com.samdube.echec.piece sur l'com.samdube.echec.echiquier
     * @param p_deplacement    Deplacement associer a la com.samdube.echec.piece
     * @param p_representation Representation textuelle de la com.samdube.echec.piece
     * @param p_force          Force de la com.samdube.echec.piece
     */
    Piece(Position p_position, Deplacement p_deplacement, char p_representation, int p_force) {
        m_couleur = getCouleurAvecPositionDepart(p_position);
        m_observateurs = new ArrayList<>();
        m_representation = p_representation;
        m_force = p_force;
        m_position = p_position;
        m_deplacement = p_deplacement;
    }

    public static CouleurPiece getCouleurAvecPositionDepart(Position p_position){
        return (p_position.getY() > 3)? CouleurPiece.NOIR : CouleurPiece.BLANC;
    }

    /**
     * Methode permettant de changer la position de la com.samdube.echec.piece
     *
     * @param p_position Position ou la com.samdube.echec.piece doit etre deplacer
     * @return Vrai si la com.samdube.echec.piece a ete deplacer a la position
     */
    public boolean deplacer(Position p_position) {
        if (peutDeplacer(p_position)) {
            m_position = p_position;
            Notify();
            return true;
        }else{
            return false;
        }
    }

    public boolean peutDeplacer(Position p_position){
        return !m_position.equals(p_position) && Arrays.asList(m_deplacement.getDisponibles()).contains(p_position);
    }

    public void calculerDeplacementPossibles(Position[] p_positionsPiecesBlanches, Position[] p_positionsPiecesNoires){
        if(m_couleur == CouleurPiece.BLANC){
            m_deplacement.calculerDeplacementPossibles(m_position, p_positionsPiecesNoires, p_positionsPiecesBlanches);
        }else{
            m_deplacement.calculerDeplacementPossibles(m_position, p_positionsPiecesBlanches, p_positionsPiecesNoires);
        }
    }

    public Position[] getDeplacementsPossibles(){
        return m_deplacement.getDisponibles();
    }

    public Deplacement getDeplacement(){
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
    public void Subscribe(IObserver p_observateur) {
        m_observateurs.add(p_observateur);
    }

    @Override
    public void Notify() {
        for (IObserver obersvateur : m_observateurs) {
            obersvateur.update();
        }
    }

    @Override
    public void Unsubscribe(IObserver p_observateur) {
        m_observateurs.remove(p_observateur);
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
}

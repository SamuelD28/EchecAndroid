package com.samdube.echec.jeux;

import com.samdube.echec.piece.Piece;

/**
 * Classe permettant de créer les joueurs du jeux d'échec
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
class Joueur {
    private final Piece.CouleurPiece m_couleur;

    private String m_nom;

    /**
     * Constructeur permttant de creer un nouveau joueur
     *
     * @param p_couleur Couleur du joueur
     */
    Joueur(Piece.CouleurPiece p_couleur) {
        m_couleur = p_couleur;
    }

    /**
     * Getter pour obtenir la couleur du joueurs
     *
     * @return Couleur du joueur
     */
    Piece.CouleurPiece getCouleurJoueur() {
        return m_couleur;
    }

    /**
     * Getter pour obtenir le nom du joueur
     *
     * @return Nom du joueur
     */
    String getNom() {
        return m_nom;
    }

    /**
     * Setter pour changer le nom du joueur
     *
     * @param p_nom Nom a assigner au joueur
     */
    void setNom(String p_nom) {
        m_nom = p_nom;
    }
}

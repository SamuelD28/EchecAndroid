package com.samdube.echec.jeux;

import com.samdube.echec.piece.Piece;

/**
 * Classe qui permet de gerer une partie d'échec
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class Manager {
    private final Joueur m_joueurBlanc = new Joueur(Piece.CouleurPiece.BLANC);

    private final Joueur m_joueurNoir = new Joueur(Piece.CouleurPiece.NOIR);

    private int m_tour = 0;

    /**
     *  Methode qui reinitialise la partie
     */
    public void reinitialiserPartie() {
        m_tour = 0;
    }

    /**
     * Permet de revenir a un tour antérieur
     * @param p_nombre Nombre de tour a revenir
     */
    public void revenirTour(int p_nombre) {
        if (m_tour > 0) {
            m_tour -= p_nombre;
        }
    }

    /**
     * Methode qui retourne la couleur
     * du joeur a qui cest le tour
     *
     * @return Couleur du joueur en cours
     */
    public Piece.CouleurPiece getCouleurJoueurEnTour() {
        if (m_tour % 2 == 0) {
            return m_joueurBlanc.getCouleurJoueur();
        } else {
            return m_joueurNoir.getCouleurJoueur();
        }
    }

    /**
     * Methode qui retourne le nom du joueur en cours
     *
     * @return Nom du joueur
     */
    public String getNomJoueurEnTour() {
        if (m_tour % 2 == 0) {
            return m_joueurBlanc.getNom();
        } else {
            return m_joueurNoir.getNom();
        }
    }

    /**
     * Methode qui termine le tour du joueur en cours
     */
    public void terminerTour() {
        m_tour++;
    }

    /**
     * Methode qui assigne le nom des joueurs du jeux
     * dechec
     *
     * @param nomBlanc Nom du joueur blanc
     * @param nomNoir Nom du joueur noir
     */
    public void setNomsJoueurs(String nomBlanc, String nomNoir) {
        m_joueurBlanc.setNom(nomBlanc);
        m_joueurNoir.setNom(nomNoir);
    }
}

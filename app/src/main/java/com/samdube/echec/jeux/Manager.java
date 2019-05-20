package com.samdube.echec.jeux;

import android.support.design.internal.BottomNavigationItemView;

import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.piece.Piece;

/**
 * Classe qui permet de gerer une partie d'échec
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class Manager {
    private Joueur m_joueurBlanc = new Joueur(Piece.CouleurPiece.BLANC);

    private Joueur m_joueurNoir = new Joueur(Piece.CouleurPiece.NOIR);

    private int m_tour = 0;

    private Echiquier m_echiquer;

    public Manager(Echiquier p_echiquier) {
        m_echiquer = p_echiquier;
    }

    public void reinitialiserPartie() {
        m_tour = 0;
    }

    /**
     * Permet de revenir a un tour antérieur
     */
    public void revenirTour() {
        if (m_tour > 0) {
            m_tour--;
        }
    }

    public Piece.CouleurPiece getCouleurJoueurEnTour() {
        if (m_tour % 2 == 0) {
            return m_joueurBlanc.getCouleurJoueur();
        } else {
            return m_joueurNoir.getCouleurJoueur();
        }
    }

    public String getNomJoueurEnTour() {
        if (m_tour % 2 == 0) {
            return m_joueurBlanc.getNom();
        } else {
            return m_joueurNoir.getNom();
        }
    }

    public void terminerTour() {
        m_tour++;
    }

    public void setNomsJoueurs(String nomBlanc, String nomNoir) {
        m_joueurBlanc.setNom(nomBlanc);
        m_joueurNoir.setNom(nomNoir);
    }
}

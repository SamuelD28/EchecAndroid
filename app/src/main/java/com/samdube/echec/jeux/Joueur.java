package com.samdube.echec.jeux;

/**
 * Classe permettant de créer les joueurs du com.samdube.echec.jeux d'échec
 *
 * @author Samuel Colassin
 */
class Joueur {
    private static Joueur m_joueurBlanc;

    private static Joueur m_joueurNoir;

    private final CouleurJoueur m_couleur;

    private String m_nom;

    /**
     * Constructeur permttant de creer un nouveau joueur
     *
     * @param p_couleur Couleur du joueur
     */
    private Joueur(CouleurJoueur p_couleur) {
        m_couleur = p_couleur;
    }

    /**
     * Permet d'avoit uniquement une seule instance de joueur blanc
     *
     * @return L'unique joueur blanc
     */
    static Joueur ObtenirJoueurBlanc() {
        if (m_joueurBlanc == null) {
            m_joueurBlanc = new Joueur(CouleurJoueur.BLANC);
        }
        return m_joueurBlanc;
    }

    /**
     * Permet d'avoit uniquement une seule instance de joueur noir
     *
     * @return L'unique joueur noir
     */
    static Joueur ObtenirJoueurNoir() {
        if (m_joueurNoir == null) {
            m_joueurNoir = new Joueur(CouleurJoueur.NOIR);
        }
        return m_joueurNoir;
    }

    /**
     * Getter pour obtenir la couleur du joueurs
     *
     * @return Couleur du joueur
     */
    CouleurJoueur getCouleurJoueur() {
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

    /**
     * Couleur que peut prendre un joueur
     */
    public enum CouleurJoueur {BLANC, NOIR}
}

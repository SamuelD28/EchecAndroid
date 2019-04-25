package com.samdube.echec.jeux;

/**
 * Classe permettant de créer les joueurs du com.samdube.echec.jeux d'échec
 *
 * @author Samuel Colassin
 */
class Joueur {
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

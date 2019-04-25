package com.samdube.echec.jeux;

import junit.framework.TestCase;

/**
 * Teste la class joueur
 *
 * @author Samuel Colassin
 */
public class TestJoueur extends TestCase {
    /**
     * Teste la ccreation d'une nouvelle instance de joueur
     */
    public void testCreer() {
        String nom1 = "Samuel";
        String nom2 = "Bobby";

        Joueur joueurNoir = Joueur.ObtenirJoueurNoir();
        Joueur joueurNoir2 = Joueur.ObtenirJoueurNoir();
        Joueur joueurBlanc = Joueur.ObtenirJoueurBlanc();
        Joueur joueurBlanc2 = Joueur.ObtenirJoueurBlanc();

        assertEquals(Joueur.ObtenirJoueurNoir(), joueurNoir);
        assertEquals(Joueur.CouleurJoueur.NOIR, joueurNoir.getCouleurJoueur());
        joueurNoir.setNom(nom1);
        assertEquals(nom1, joueurNoir.getNom());
        assertEquals(joueurNoir, joueurNoir2);


        assertEquals(Joueur.ObtenirJoueurBlanc(), joueurBlanc);
        assertEquals(Joueur.CouleurJoueur.BLANC, joueurBlanc.getCouleurJoueur());
        joueurBlanc.setNom(nom2);
        assertEquals(nom2, joueurBlanc.getNom());
        assertEquals(joueurBlanc, joueurBlanc2);
    }
}

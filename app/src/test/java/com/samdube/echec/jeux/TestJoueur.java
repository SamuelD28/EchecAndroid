package com.samdube.echec.jeux;

import com.samdube.echec.piece.Piece;

import junit.framework.TestCase;

/**
 * Teste la classe joueur
 *
 * @author Samuel DubE
 * @author  Samuel Colassin
 */
public class TestJoueur extends TestCase {

    /**
     * Teste la creation dun joueur
     */
    public void testCreation(){
        Joueur joueur = new Joueur(Piece.CouleurPiece.BLANC);
        joueur.setNom("Samuel");

        assertEquals("Samuel", joueur.getNom());
        assertEquals(Piece.CouleurPiece.BLANC, joueur.getCouleurJoueur());

        joueur.setNom("Bob");
        assertEquals("Bob", joueur.getNom());
        assertEquals(Piece.CouleurPiece.BLANC, joueur.getCouleurJoueur());
    }
}

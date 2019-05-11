package com.samdube.echec.echiquier;

import junit.framework.TestCase;
import com.samdube.echec.piece.*;

import static com.samdube.echec.piece.Piece.CouleurPiece.BLANC;
import static com.samdube.echec.piece.Piece.CouleurPiece.NOIR;

/**
 * Tests de la classe Echiquier
 *
 * @author Samuel Dubé
 * @author Samuel Colassin
 */
public class TestEchiquier extends TestCase {
    private Echiquier m_echiquier;

    /**
     * Réinitialise l'échiquier à chaque exécution des tests
     */
    public void setUp() {
        m_echiquier = new Echiquier();
    }

    /**
     * Methode testant l'initialisation d'un com.samdube.echec.echiquier. Vérifie
     * que les pièces sont bel et bien au bon endroit et que le
     * nombre de pièces correspond également.
     */
    public void testCreer() {
        assertEquals(64, m_echiquier.getCases().length);
    }

    /**
     * Permet de tester si l'échiquier retourne le bon nombre de pièce désiré
     * sur l'échiquier selon la couleur et le type de la pièce.
     */
    public void testGetNombrePiece() {
        assertEquals(32, m_echiquier.getNombrePieces());
        assertEquals(16, m_echiquier.getNombrePieces(NOIR));
        assertEquals(16, m_echiquier.getNombrePieces(BLANC));
        assertEquals(2, m_echiquier.getNombrePieces(BLANC, Tour.class));
        assertEquals(2, m_echiquier.getNombrePieces(BLANC, Fou.class));
        assertEquals(2, m_echiquier.getNombrePieces(BLANC, Cavalier.class));
        assertEquals(8, m_echiquier.getNombrePieces(BLANC, Pion.class));
        assertEquals(1, m_echiquier.getNombrePieces(BLANC, Roi.class));
        assertEquals(1, m_echiquier.getNombrePieces(BLANC, Reine.class));
    }

    /**
     * Permet de tester si l'échiquier retourne la bonne pièce selon une position
     * dans l'échiquier.
     */
    public void testGetPiece() {
        Piece piece = m_echiquier.getPiece(new Position(0,1));

        assertTrue(piece instanceof Pion);
        assertEquals(BLANC, piece.getCouleur());
        assertEquals('p', piece.getRepresentation());
        assertEquals(1, piece.getForce());

        piece = m_echiquier.getPiece(new Position(7,7));

        assertTrue(piece instanceof Tour);
        assertEquals(NOIR, piece.getCouleur());
        assertEquals('t', piece.getRepresentation());
        assertEquals(2, piece.getForce());

        piece = m_echiquier.getPiece(new Position(0,3));

        assertNull(piece);
    }

    /**
     * Permet de tester l'actualisation des positions des pièces dans l'échiquier
     */
//    public void testActualiserPositionsPieces() {
//        Echiquier echiquier = new Echiquier();
//
//        //echiquier.
//    }


    public void testDeplacerPiece() {
        // Test case d'arrivé vide
        Echiquier echiquier = new Echiquier();
//        Piece pion = echiquier.getPiece(new Position(0,1));
//        Position positionPion = new Position(0, 2);
//        echiquier.deplacerPiece(pion, positionPion);
//        assertEquals(pion, echiquier.getPiece(positionPion));

        // Test avec pièce à la position d'arriver
        Piece cavalier = echiquier.getPiece(new Position(1, 0));
        echiquier.deplacerPiece(cavalier, new Position(2, 2));
        echiquier.deplacerPiece(cavalier, new Position(1, 4));
        Position positionCavalier = new Position(2, 6);
        echiquier.deplacerPiece(cavalier, new Position(2, 6));

        assertEquals(cavalier, echiquier.getPiece(positionCavalier));
    }
}

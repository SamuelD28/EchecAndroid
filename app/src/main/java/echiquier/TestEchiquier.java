package echiquier;

import junit.framework.TestCase;

import piece.Cavalier;
import piece.Fou;
import piece.Pion;
import piece.Reine;
import piece.Roi;
import piece.Tour;

import static piece.Piece.CouleurPiece.*;

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
     * Methode testant l'initialisation d'un echiquier. Vérifie
     * que les pièces sont bel et bien au bon endroit et que le
     * nombre de pièces correspond également.
     */
    public void testCreer() {
        assertEquals(64, m_echiquier.getEchiquier().length);
        assertEquals(8, m_echiquier.getTaille());
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
    }

    /**
     * Permet de tester si l'emplacement d'une pièce est bonne après
     * l'avoir mis à une place désirée.
     */
    public void testPlacerPiece() {
    }

}

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

    public void testDeplacerPiece() {
        Echiquier echiquier = new Echiquier();

        // Test case d'arrivé vide
        Piece pionNoir = echiquier.getPiece(new Position(0,1));
        Position positionPion = new Position(0, 2);
        echiquier.deplacerPiece(pionNoir, positionPion);
        assertEquals(pionNoir, echiquier.getPiece(positionPion));

        // Test avec pièce à la position d'arriver -- Cavalier blanc mange un pion noir
        Piece cavalierBlanc = echiquier.getPiece(new Position(1, 0));
        echiquier.calculerTousDeplacements();
        echiquier.deplacerPiece(cavalierBlanc, new Position(2, 2));
        echiquier.calculerTousDeplacements();
        echiquier.deplacerPiece(cavalierBlanc, new Position(1, 4));
        echiquier.calculerTousDeplacements();

        Position positionArrive1 = new Position(2, 6);
        echiquier.deplacerPiece(cavalierBlanc, positionArrive1);
        assertEquals(cavalierBlanc, echiquier.getPiece(positionArrive1));

        //echiquier.calculerCollisionsPieces();
        assertFalse(echiquier.deplacerPiece(cavalierBlanc, new Position(7, 7)));

        //Position p = new Position(1, 7);

        Piece cavalierNoir = echiquier.getPiece(new Position(1, 7));

        //assertEquals(cavalierNoir, echiquier.getPiece(p));

        echiquier.calculerTousDeplacements();


        echiquier.deplacerPiece(cavalierNoir, new Position(2, 5));


        echiquier.calculerTousDeplacements();

        assertEquals(cavalierNoir, echiquier.getPiece(new Position(2, 5)));


        echiquier.deplacerPiece(cavalierNoir, new Position(1, 3));


        echiquier.calculerTousDeplacements();


        echiquier.getPiece(new Position(1, 3));

        Position positionArrive2 = new Position(2, 1);
        echiquier.deplacerPiece(cavalierNoir, positionArrive2);
        assertEquals(cavalierNoir, echiquier.getPiece(positionArrive2));
    }

    public void testEchecEtMat() {
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 0)), new Position(2, 2));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(2, 2)), new Position(3, 4));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(3, 4)), new Position(5, 5));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(5, 5)), new Position(4, 7));

        assertTrue(m_echiquier.estEchecEtMath(NOIR));
    }

    public void testEnCoursDePromotion() {
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 1)), new Position(1, 3));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 3)), new Position(1, 4));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 4)), new Position(1, 5));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 5)), new Position(0, 6));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(0, 6)), new Position(1, 7));

        assertTrue(m_echiquier.getPionEnCourDePromotion());
    }

    public void testPromouvoirPion() {
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 1)), new Position(1, 3));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 3)), new Position(1, 4));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 4)), new Position(1, 5));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 5)), new Position(0, 6));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(0, 6)), new Position(1, 7));

        m_echiquier.promouvoirPion();
        assertEquals(Reine.class, m_echiquier.getPiece(new Position(1, 7)).getClass());
    }
}

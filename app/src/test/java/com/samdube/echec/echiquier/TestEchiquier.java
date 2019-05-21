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
        Piece piece = m_echiquier.getPiece(new Position(0, 1));

        assertTrue(piece instanceof Pion);
        assertEquals(BLANC, piece.getCouleur());
        assertEquals('p', piece.getRepresentation());
        assertEquals(1, piece.getForce());

        piece = m_echiquier.getPiece(new Position(7, 7));

        assertTrue(piece instanceof Tour);
        assertEquals(NOIR, piece.getCouleur());
        assertEquals('t', piece.getRepresentation());
        assertEquals(2, piece.getForce());

        piece = m_echiquier.getPiece(new Position(0, 3));

        assertNull(piece);
    }

    /**
     * Teste le deplacement de piece
     */
    public void testDeplacerPiece() {
        Echiquier echiquier = new Echiquier();

        // Test case d'arrivé vide
        Piece pionNoir = echiquier.getPiece(new Position(0, 1));
        Position positionPion = new Position(0, 2);
        echiquier.deplacerPiece(pionNoir, positionPion);
        assertEquals(pionNoir, echiquier.getPiece(positionPion));

        // Test avec pièce à la position d'arriver -- Cavalier blanc mange un pion noir
        Piece cavalierBlanc = echiquier.getPiece(new Position(1, 0));
        echiquier.calculerDeplacements();
        echiquier.deplacerPiece(cavalierBlanc, new Position(2, 2));
        echiquier.calculerDeplacements();
        echiquier.deplacerPiece(cavalierBlanc, new Position(1, 4));
        echiquier.calculerDeplacements();

        Position positionArrive1 = new Position(2, 6);
        echiquier.deplacerPiece(cavalierBlanc, positionArrive1);
        assertEquals(cavalierBlanc, echiquier.getPiece(positionArrive1));

        assertFalse(echiquier.deplacerPiece(cavalierBlanc, new Position(7, 7)));
        Piece cavalierNoir = echiquier.getPiece(new Position(1, 7));

        echiquier.calculerDeplacements();
        echiquier.deplacerPiece(cavalierNoir, new Position(2, 5));
        echiquier.calculerDeplacements();
    }

    /**
     * Teste le echec et math pour lechiquier
     */
    public void testEchecEtMat() {
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 0)), new Position(2, 2));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(2, 2)), new Position(3, 4));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(3, 4)), new Position(5, 5));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(5, 5)), new Position(4, 7));

        assertTrue(m_echiquier.estEchecEtMath());
    }

    /**
     * Teste la promotion dun pion pour lechiquier
     */
    public void testEnCoursDePromotion() {
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 1)), new Position(1, 3));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 3)), new Position(1, 4));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 4)), new Position(1, 5));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(1, 5)), new Position(0, 6));
        m_echiquier.deplacerPiece(m_echiquier.getPiece(new Position(0, 6)), new Position(1, 7));

        assertTrue(m_echiquier.getEnCourDePromotion());
    }

    /**
     * Teste la selection de piece pour lechiquier
     */
    public void testSelectionnerPiece() {
        Piece pion = new Pion(new Position(0, 0));

        m_echiquier.setPieceSelectionner(pion);
        Piece selection = m_echiquier.getPieceSelectionner();
        assertEquals(selection, pion);

        m_echiquier.setPieceSelectionner(null);
        selection = m_echiquier.getPieceSelectionner();
        assertNull(selection);
    }

    /**
     * Teste un scenario valide de petit roque
     */
    public void testEffectuerPetitRoque() {
        Piece roiNoir = new Roi(new Position(4, 7));
        Piece tourNoir = new Tour(new Position(7, 7));

        Piece roiBlanc = new Roi(new Position(4, 0));
        Piece tourBlanche = new Tour(new Position(7, 0));

        m_echiquier = new Echiquier(roiBlanc, tourBlanche, roiNoir, tourNoir);

        assertTrue(m_echiquier.deplacerPiece(roiBlanc, tourBlanche.getPosition()));
        assertEquals(new Position(6, 0), roiBlanc.getPosition());
        assertEquals(new Position(5, 0), tourBlanche.getPosition());

        assertTrue(m_echiquier.deplacerPiece(roiNoir, tourNoir.getPosition()));
        assertEquals(new Position(6, 7), roiNoir.getPosition());
        assertEquals(new Position(5, 7), tourNoir.getPosition());
    }

    /**
     * Teste un scenario invalide de petit roque
     */
    public void testPeutPasEffectuerPetitRoque() {
        Piece roiNoir = new Roi(new Position(4, 7));
        Piece tourNoir = new Tour(new Position(7, 7));

        Piece roiBlanc = new Roi(new Position(4, 0));
        Piece tourBlanche = new Tour(new Position(7, 0));

        Piece pionBlanc = new Pion(new Position(6, 0));
        Piece pionNoir = new Pion(new Position(6, 7));

        m_echiquier = new Echiquier(roiBlanc, tourBlanche, roiNoir, tourNoir, pionBlanc, pionNoir);

        assertFalse(m_echiquier.deplacerPiece(roiBlanc, tourBlanche.getPosition()));
        assertEquals(new Position(4, 0), roiBlanc.getPosition());
        assertEquals(new Position(7, 0), tourBlanche.getPosition());

        assertFalse(m_echiquier.deplacerPiece(roiNoir, tourNoir.getPosition()));
        assertEquals(new Position(4, 7), roiNoir.getPosition());
        assertEquals(new Position(7, 7), tourNoir.getPosition());
    }

    /**
     * Teste un scenario valide de petit roque
     */
    public void testEffectuerGrandRoque() {
        Piece roiNoir = new Roi(new Position(4, 7));
        Piece tourNoir = new Tour(new Position(0, 7));

        Piece roiBlanc = new Roi(new Position(4, 0));
        Piece tourBlanche = new Tour(new Position(0, 0));

        m_echiquier = new Echiquier(roiBlanc, tourBlanche, roiNoir, tourNoir);

        assertTrue(m_echiquier.deplacerPiece(roiBlanc, tourBlanche.getPosition()));
        assertEquals(new Position(2, 0), roiBlanc.getPosition());
        assertEquals(new Position(3, 0), tourBlanche.getPosition());

        assertTrue(m_echiquier.deplacerPiece(tourNoir, roiNoir.getPosition()));
        assertEquals(new Position(2, 7), roiNoir.getPosition());
        assertEquals(new Position(3, 7), tourNoir.getPosition());
    }

    /**
     * Teste un scenario invalide de petit roque
     */
    public void testPeutPasEffectuerGrandRoque() {
        Piece roiNoir = new Roi(new Position(4, 7));
        Piece tourNoir = new Tour(new Position(0, 7));

        Piece roiBlanc = new Roi(new Position(4, 0));
        Piece tourBlanche = new Tour(new Position(0, 0));

        Piece pionBlanc = new Pion(new Position(2, 0));
        Piece pionNoir = new Pion(new Position(2, 7));

        m_echiquier = new Echiquier(roiBlanc, tourBlanche, roiNoir, tourNoir, pionBlanc, pionNoir);

        assertFalse(m_echiquier.deplacerPiece(roiBlanc, tourBlanche.getPosition()));
        assertEquals(new Position(4, 0), roiBlanc.getPosition());
        assertEquals(new Position(0, 0), tourBlanche.getPosition());

        assertFalse(m_echiquier.deplacerPiece(roiNoir, tourNoir.getPosition()));
        assertEquals(new Position(4, 7), roiNoir.getPosition());
        assertEquals(new Position(0, 7), tourNoir.getPosition());
    }

    /**
     * Teste les deplacements pouvant sauver un roi en echec
     */
    public void testDeplacementSauverRoi() {
        Piece roiNoir = new Roi(new Position(4, 7));
        Piece roiBlanc = new Roi(new Position(0, 0));
        Piece reineNoir = new Reine(new Position(0, 7));
        Piece tourBlanche = new Tour(new Position(7, 1));

        m_echiquier = new Echiquier(roiBlanc, reineNoir, tourBlanche, roiNoir);

        assertTrue(m_echiquier.estEchec(BLANC));

        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(6, 1)));
        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(3, 1)));
        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(1, 1)));

        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(1, 1)));
        assertTrue(m_echiquier.deplacerPiece(tourBlanche, new Position(0, 1)));

        assertFalse(m_echiquier.estEchec(BLANC));
    }

    /**
     * Teste les deplacements dune piece qui mettrais en danger le roi
     */
    public void testDeplacementDangerRoi() {
        Piece roiNoir = new Roi(new Position(4, 7));
        Piece roiBlanc = new Roi(new Position(0, 0));
        Piece reineNoir = new Reine(new Position(0, 7));
        Piece tourBlanche = new Tour(new Position(0, 1));

        m_echiquier = new Echiquier(roiBlanc, reineNoir, tourBlanche, roiNoir);

        assertFalse(m_echiquier.estEchec(BLANC));

        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(6, 1)));
        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(3, 1)));
        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(1, 1)));

        assertFalse(m_echiquier.deplacerPiece(tourBlanche, new Position(1, 3)));
        assertTrue(m_echiquier.deplacerPiece(tourBlanche, new Position(0, 3)));

        assertFalse(m_echiquier.estEchec(BLANC));
    }

    /**
     * Teste la promotion dun pion sur lechiquier
     */
    public void testPromotionPion() {
        Piece roiBLanc = new Roi(new Position(0, 0));
        Piece roiNoir = new Roi(new Position(7, 7));

        Piece pionUn = new Pion(new Position(0, 6), BLANC);
        Piece pionDeux = new Pion(new Position(1, 6), BLANC);
        Piece pionTrois = new Pion(new Position(2, 6), BLANC);
        Piece pionQuatre = new Pion(new Position(3, 6), BLANC);
        Piece pionCinq = new Pion(new Position(4, 6), BLANC);
        Piece pionSix = new Pion(new Position(5, 6), BLANC);

        m_echiquier = new Echiquier(roiBLanc, roiNoir, pionUn, pionDeux, pionTrois, pionQuatre, pionCinq, pionSix);

        assertTrue(m_echiquier.deplacerPiece(pionUn, new Position(0, 7)));
        m_echiquier.promouvoirPion('r');
        assertEquals(m_echiquier.getPiece(pionUn.getPosition()), new Reine(pionUn.getPosition(), pionUn.getCouleur()));


        assertTrue(m_echiquier.deplacerPiece(pionDeux, new Position(1, 7)));
        m_echiquier.promouvoirPion('f');
        assertEquals(m_echiquier.getPiece(pionDeux.getPosition()), new Fou(pionDeux.getPosition(), pionDeux.getCouleur()));

        assertTrue(m_echiquier.deplacerPiece(pionTrois, new Position(2, 7)));
        m_echiquier.promouvoirPion('c');
        assertEquals(m_echiquier.getPiece(pionTrois.getPosition()), new Cavalier(pionTrois.getPosition(), pionTrois.getCouleur()));

        assertTrue(m_echiquier.deplacerPiece(pionQuatre, new Position(3, 7)));
        m_echiquier.promouvoirPion('t');
        assertEquals(m_echiquier.getPiece(pionQuatre.getPosition()), new Tour(pionQuatre.getPosition(), pionQuatre.getCouleur()));
    }
}

package com.samdube.echec.piece;

import com.samdube.echec.echiquier.Position;

import junit.framework.TestCase;

import com.samdube.echec.utils.IObserver;

/**
 * Teste la classe pièce et ses méthodes
 *
 * @author Samuel Dubé
 * @author Samuel Colassin
 */
public abstract class TestPiece extends TestCase {
    /**
     * Getter pour obtenir la com.samdube.echec.piece qui
     * serait attendue.
     *
     * @return Piece attendue
     */
    protected abstract Piece getPieceAttendue();

    /**
     * Getter pour obtenir la com.samdube.echec.piece actuellement
     * creer
     *
     * @return Piece actuel creer
     */
    protected abstract Piece getPieceActuel();

    /**
     * Getter pour obtenir une com.samdube.echec.piece differente
     * de celle creer
     *
     * @return Piece differente
     */
    protected abstract Piece getPieceDifferente();

    /**
     * Getter pour obtenir une suite de com.samdube.echec.deplacement
     * possible que la com.samdube.echec.piece doit effectuer
     *
     * @return Suite de deplacements possibles
     */
    protected abstract Position[] getDeplacementsPossible();

    /**
     * Getter pour obtenir une liste de com.samdube.echec.deplacement impossible
     * que la com.samdube.echec.piece ne peut effectuer
     *
     * @return Suite de com.samdube.echec.deplacement impossible
     */
    protected abstract Position[] getDeplacementsImpossible();

    /**
     * Teste la creation d"une nouvelle com.samdube.echec.piece et
     * verifie ses proprietes
     */
    public void testCreer() {
        assertEquals(getPieceAttendue().getRepresentation(), getPieceActuel().getRepresentation());
        assertEquals(getPieceAttendue().getForce(), getPieceActuel().getForce());
        assertEquals(getPieceAttendue().getPosition(), getPieceActuel().getPosition());
        assertEquals(getPieceAttendue().getCouleur(), getPieceActuel().getCouleur());
        assertEquals(getPieceAttendue().getDeplacement().getClass(), getPieceActuel().getDeplacement().getClass());
    }

    /**
     * Teste le com.samdube.echec.deplacement de com.samdube.echec.piece par la methode
     * set position.
     */
    public void testSetPosition() {
        Piece piece = getPieceActuel();
        for (Position deplacementPossible : getDeplacementsPossible()) {
            piece.calculerDeplacementPossibles(new Position[0], new Position[0]);
            assertTrue(piece.deplacer(deplacementPossible));
        }

        piece = getPieceActuel();
        for (Position deplacementImpossible : getDeplacementsImpossible()) {
            assertFalse(piece.deplacer(deplacementImpossible));
        }
    }

    /**
     * Test le getter de déplacement disponible
     */
    public void testGetDeplacementPossible() {
        // TODO Peut-être pas la meilleur facon de vérfier

        Piece pion = new Pion(new Position(0, 1));
        pion.calculerDeplacementPossibles(new Position[0], new Position[0]);
        assertEquals(1, pion.getDeplacementsPossibles().length);
        Piece reine = new Reine(new Position(0, 7));
        reine.calculerDeplacementPossibles(new Position[0], new Position[0]);
        assertEquals(21, reine.getDeplacementsPossibles().length);
    }

    /**
     * Teste que les pieces sont observables
     */
    public void testObservable() {
        Piece piece = getPieceActuel();
        IObserver observateurA = () -> {
            // mise a jour
        };
        IObserver observateurB = () -> {
            // mise a jour
        };

        piece.Subscribe(observateurA);
        piece.Subscribe(observateurB);

        piece.Notify();

        piece.Unsubscribe(observateurA);
        piece.Unsubscribe(observateurB);
    }

    /**
     * Teste la méthode equals sur une com.samdube.echec.piece
     */
    public void testEquals() {
        Piece pieceA = getPieceActuel();
        Piece pieceB = getPieceActuel();
        Piece pieceC = getPieceDifferente();

        assertTrue(pieceA.equals(pieceB));
        assertTrue(pieceB.equals(pieceA));

        assertTrue(pieceA.equals(pieceA));
        assertTrue(pieceB.equals(pieceB));

        assertFalse(pieceA.equals(pieceC));
        assertFalse(pieceB.equals(pieceC));
        assertFalse(pieceA.equals(null));
        assertFalse(pieceA.equals("ads"));
    }

    /**
     * Teste la génération de hashcode
     */
    public void testHashCode() {
        Piece pieceA = getPieceActuel();
        Piece pieceB = getPieceActuel();
        Piece pieceC = getPieceDifferente();

        assertEquals(pieceA.hashCode(), pieceB.hashCode());
        assertEquals(pieceB.hashCode(), pieceA.hashCode());

        assertFalse(pieceA.hashCode() == pieceC.hashCode());
        assertFalse(pieceB.hashCode() == pieceC.hashCode());
    }
}

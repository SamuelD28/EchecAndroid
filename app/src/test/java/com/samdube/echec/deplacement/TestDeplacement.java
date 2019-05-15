package com.samdube.echec.deplacement;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.piece.Cavalier;
import com.samdube.echec.piece.Piece;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe de test pour le com.samdube.echec.deplacement du cavalier
 *
 * @author Samuel Dube
 * @author Samuel Colassin
 */
public abstract class TestDeplacement extends TestCase {

    /**
     * Methode pour obtenir le type de com.samdube.echec.deplacement
     * generer par le test enfant
     *
     * @return Le Deplacement generer
     */
    protected abstract Deplacement getDeplacement();

    /**
     * Methode pour obtenir un com.samdube.echec.deplacement different de celui
     * implemente dans la classe enfant afin de tester la methode equals
     *
     * @return Le com.samdube.echec.deplacement different
     */
    protected abstract Deplacement getDeplacementDifferent();

    /**
     * Methode pour obtenir les possibilites attendues
     * pour le com.samdube.echec.deplacement generer
     *
     * @return Les possibilites attendues
     */
    protected abstract Position[] getPossibilitesAttendues();

    /**
     * Test de déplacement d'un cavalier
     */
    public void testCalculerPossibilites() {

        Echiquier echiquier = new Echiquier();

        echiquier.calculerCollisionsPieces();
        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(0, 1)), new Position(0, 2)));
        echiquier.calculerCollisionsPieces();
        assertFalse(echiquier.deplacerPiece(echiquier.getPiece(new Position(2, 0)), new Position(1, 1)));

        echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 0)), new Position(7, 2));
        echiquier.calculerCollisionsPieces();

        echiquier.deplacerPiece(echiquier.getPiece(new Position(7, 2)), new Position(6, 4));
        echiquier.calculerCollisionsPieces();

        assertTrue(echiquier.deplacerPiece(echiquier.getPiece(new Position(6, 4)), new Position(7, 6)));

//        Position[] possibilitesActuels = getDeplacement().getDisponibles();
//        Position[] possibilitesAttendues = getPossibilitesAttendues();
//
//        Arrays.sort(possibilitesAttendues);
//        Arrays.sort(possibilitesActuels);
//
//        assertTrue(Arrays.equals(possibilitesAttendues, possibilitesActuels));
    }

    /**
     * Teste la méthode equals sur un déplacements
     */
    public void testEquals() {
        Deplacement deplacementA = getDeplacement();
        Deplacement deplacementB = getDeplacement();
        Deplacement deplacementC = getDeplacementDifferent();

        assertTrue(deplacementA.equals(deplacementB));
        assertTrue(deplacementB.equals(deplacementA));

        assertTrue(deplacementA.equals(deplacementA));
        assertTrue(deplacementB.equals(deplacementB));

        assertFalse(deplacementA.equals(deplacementC));
        assertFalse(deplacementB.equals(deplacementC));
        assertFalse(deplacementA.equals(null));
        assertFalse(deplacementA.equals("ads"));
    }

    /**
     * Teste la génération de hashcode
     */
    public void testHashCode() {
        Deplacement deplacementA = getDeplacement();
        Deplacement deplacementB = getDeplacement();
        Deplacement deplacementC = getDeplacementDifferent();

        assertEquals(deplacementA.hashCode(), deplacementB.hashCode());
        assertEquals(deplacementB.hashCode(), deplacementA.hashCode());

        assertFalse(deplacementA.hashCode() == deplacementC.hashCode());
        assertFalse(deplacementB.hashCode() == deplacementC.hashCode());
    }

    /**
     * Teste l'ajout de possiblites dans le com.samdube.echec.deplacement
     */
    public void testAjouterDeplacement() {
        Deplacement deplacement = getDeplacement();

        deplacement.ajouterDeplacementPossibles(
                new Position(6, 7),
                new Position(5, 7),
                new Position(6, 0)
        );

        assertTrue(Arrays.asList(deplacement.getDisponibles()).contains(new Position(6, 7)));
        assertTrue(Arrays.asList(deplacement.getDisponibles()).contains(new Position(5, 7)));
        assertTrue(Arrays.asList(deplacement.getDisponibles()).contains(new Position(6, 0)));
    }

    /**
     * Teste le retrait de possibblites dans le com.samdube.echec.deplacement
     */
    public void testRetirerDeplacement() {
        //On commence par ajouter les possiblites avant de les retirer
        Deplacement deplacement = getDeplacement();

        deplacement.ajouterDeplacementPossibles(
                new Position(5, 7),
                new Position(6, 0),
                new Position(5, 0)
        );

        assertTrue(Arrays.asList(deplacement.getDisponibles()).contains(new Position(5, 7)));
        assertTrue(Arrays.asList(deplacement.getDisponibles()).contains(new Position(5, 0)));
        assertTrue(Arrays.asList(deplacement.getDisponibles()).contains(new Position(6, 0)));

        //On retire les possibilites ajouter
        deplacement.retirerDeplacementPossibles(
                new Position(5, 7),
                new Position(6, 0),
                new Position(5, 0)
        );

        assertFalse(Arrays.asList(deplacement.getDisponibles()).contains(new Position(5, 7)));
        assertFalse(Arrays.asList(deplacement.getDisponibles()).contains(new Position(5, 0)));
        assertFalse(Arrays.asList(deplacement.getDisponibles()).contains(new Position(6, 0)));
    }

    public void testGetNombrePas() {
        Deplacement deplacement = new DeplacementPion(Piece.CouleurPiece.BLANC);
        assertEquals(1, deplacement.getNombrePas());
    }
}

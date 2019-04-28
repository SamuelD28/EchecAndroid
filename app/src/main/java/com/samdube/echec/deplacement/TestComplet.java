package com.samdube.echec.deplacement;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Regroupe tout les test du package déplacement
 * pour les executer les uns à la suite des autres.
 *
 * @author Samuel Dubé
 * @author Samuel Colassin
 */
public class TestComplet extends TestCase {
    /**
     * Regroupe tout les tests du package déplacement
     *
     * @return Une suite de test représentant tout les test du package déplacement
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestDeplacementTour.class);
        suite.addTestSuite(TestDeplacementRoi.class);
        suite.addTestSuite(TestDeplacementReine.class);
        suite.addTestSuite(TestDeplacementPion.class);
        suite.addTestSuite(TestDeplacementFou.class);
        suite.addTestSuite(TestDeplacementCavalier.class);
        suite.addTestSuite(TestPas.class);
        return suite;
    }
}

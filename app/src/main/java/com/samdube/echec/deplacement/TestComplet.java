package com.samdube.echec.deplacement;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Regroupe tout les test du package déplacement
 * pour les executer les uns à la suite des autres.
 *
 * @author Samuel Dubé
 */
public class TestComplet extends TestCase {
    /**
     * Regroupe tout les tests du package déplacement
     *
     * @return Une suite de test représentant tout les test du package déplacement
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestDeplacement.class);
        suite.addTestSuite(TestIncrementation.class);
        return suite;
    }
}

package com.samdube.echec.echiquier;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Regroupe tous les tests du package echec
 *
 * @author Samuel Dubé
 * @author Samuel Colassin
 */
public class TestComplet extends TestCase {
    /**
     *
     * @return la suite avec les tests à effectuer
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestPosition.class);
        suite.addTestSuite(TestEchiquier.class);
        suite.addTestSuite(TestCollision.class);
        return suite;
    }
}

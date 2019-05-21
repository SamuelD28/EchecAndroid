package com.samdube.echec.echiquier;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Regroupe tous les tests du package echec
 *
 * @author Samuel Dubé
 * @author Samuel Colassin
 */
public class TestCompletEchiquier extends TestCase {
    /**
     * @return la suite avec les tests à effectuer
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestPosition.class);
        suite.addTestSuite(TestEchiquier.class);
        return suite;
    }
}

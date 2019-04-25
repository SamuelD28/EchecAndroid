package com.samdube.echec.piece;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe qui regroupe tout les tests du package com.samdube.echec.piece
 *
 * @author Samuel Dube
 */
public class TestComplet extends TestCase {
    /**
     *
     * @return la suite avec les tests à effectuer
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestPion.class);
        suite.addTestSuite(TestFou.class);
        suite.addTestSuite(TestRoi.class);
        suite.addTestSuite(TestReine.class);
        suite.addTestSuite(TestCavalier.class);
        suite.addTestSuite(TestTour.class);
        return suite;
    }
}

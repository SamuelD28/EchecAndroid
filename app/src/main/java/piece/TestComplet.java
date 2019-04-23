package piece;

import junit.framework.TestCase;
import junit.framework.TestSuite;

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

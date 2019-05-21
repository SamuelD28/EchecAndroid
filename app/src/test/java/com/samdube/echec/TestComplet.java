package com.samdube.echec;

import com.samdube.echec.deplacement.TestDeplacementCavalier;
import com.samdube.echec.deplacement.TestDeplacementFou;
import com.samdube.echec.deplacement.TestDeplacementPion;
import com.samdube.echec.deplacement.TestDeplacementReine;
import com.samdube.echec.deplacement.TestDeplacementRoi;
import com.samdube.echec.deplacement.TestDeplacementTour;
import com.samdube.echec.deplacement.TestPas;
import com.samdube.echec.echiquier.TestEchiquier;
import com.samdube.echec.echiquier.TestPosition;
import com.samdube.echec.piece.TestCavalier;
import com.samdube.echec.piece.TestFou;
import com.samdube.echec.piece.TestPion;
import com.samdube.echec.piece.TestReine;
import com.samdube.echec.piece.TestRoi;
import com.samdube.echec.piece.TestTour;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestComplet extends TestCase {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestDeplacementTour.class);
        suite.addTestSuite(TestDeplacementRoi.class);
        suite.addTestSuite(TestDeplacementReine.class);
        suite.addTestSuite(TestDeplacementPion.class);
        suite.addTestSuite(TestDeplacementFou.class);
        suite.addTestSuite(TestDeplacementCavalier.class);
        suite.addTestSuite(TestPas.class);

        suite.addTestSuite(TestPosition.class);
        suite.addTestSuite(TestEchiquier.class);

        suite.addTestSuite(TestPion.class);
        suite.addTestSuite(TestFou.class);
        suite.addTestSuite(TestRoi.class);
        suite.addTestSuite(TestReine.class);
        suite.addTestSuite(TestCavalier.class);
        suite.addTestSuite(TestTour.class);

        return suite;
    }
}

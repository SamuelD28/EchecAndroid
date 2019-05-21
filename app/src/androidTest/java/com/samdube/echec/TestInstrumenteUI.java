package com.samdube.echec;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Permet de faire des tests instrumentés
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class TestInstrumenteUI {
    /**
     * Permet de specifier que la main activity doit être lancer avant d'exécuter les tests
     */
    @Rule
    public final ActivityTestRule<MainActivity> m_activityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Permet de vérifier si un dialogue s'affiche au lancement de l'application
     */
    @Test
    public void testValidationNom() {
        onView(withText(R.string.dialogue_nom_titre)).inRoot(isDialog()).check(matches(isDisplayed()));
    }
}

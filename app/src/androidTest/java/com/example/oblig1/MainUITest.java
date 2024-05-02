package com.example.oblig1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Denne test-klassen sjekker at riktig sub-activity blir launchet ved klikk på "Enter Game".
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainUITest {

    // Forteller systemet at gitt aktivitet skal kjøres før hver test
    @Rule public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Before
    public void setupIntents() {
        // Initialiserer intents, og starter registrering av intents (for verifisering)
        // I dette tilfellet benyttes kun sistnevnte
        // For at init() skal initialisere en intent, må gitt intent først defineres separat (stub)
        init();
    }


    @After
    public void teardownIntents() {
        // Klarerer bort intent state (etter hver test)
        release();
    }


    /** Sjekker at riktig sub-activity (selve quizen) blir launchet ved klikk på "Enter Game" */
    @Test
    public void verifyCorrectSubActivity() {

        // Bruker activityRule til å aksessere aktivitet og klikke på "Enter Game"-knappen
        activityRule.getScenario().onActivity( mainActivity -> {
            mainActivity.getQuizBtn().performClick();
        });

        // En enklere måte å utføre samme handling som det over
        //onView(withId(R.id.button_enterGame)).perform(click());

        // Espresso provides the ability to intercept outgoing intents based on matching criteria.
        // init() records all intents that attempt to launch activities from the app under test.
        // Using the intended() method you can assert that a given intent has been seen.
        intended(hasComponent(GameActivity.class.getName()));
        //intended(hasComponent("com.example.oblig1.GameActivity"));  // Ekvivalent
    }
}

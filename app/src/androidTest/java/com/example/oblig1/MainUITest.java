package com.example.oblig1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;


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


    @Test
    public void verifyCorrectSubActivity() {

        // Bruker activityRule til å aksessere aktivitet og klikke på "Enter Game"-knappen
        activityRule.getScenario().onActivity( mainActivity -> {
            mainActivity.getQuizBtn().performClick();
        });

        // Alternativ måte å utføre samme handlingen på



        //intended(hasComponent("com.example.oblig1.GameActivity"));
        intended(hasComponent(GameActivity.class.getName()));
    }



    /*

    EN MER RIKTIG MÅTE Å GJØRE DETTE PÅ

    @Test
    public void validateIntentSentToPackage() {
        // User action that results in an external "phone" activity being launched.
        user.clickOnView(system.getView(R.id.callButton));

        // Using a canned RecordedIntentMatcher to validate that an intent resolving
        // to the "phone" activity has been sent.
        intended(toPackage("com.android.phone"));
    }

    */

}



// koble sammen aktivitets-klassen/objekt og test-cases,
// slik at testcasen kan finne en riktig/en feil knapp,
// og deretter sammenligne melding på skjerm med forventet resultat.



// init()
//    initializes intents and begins recording intents (for verification)
//    triggering any action that call an intent that we want to verify with validation/stubbing




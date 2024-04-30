package com.example.oblig1;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

// Volker
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.nullValue;

import com.example.oblig1.MainActivity;
import com.example.oblig1.R;
import com.example.oblig1.GameActivity;



import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;

import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.ext.junit.rules.ActivityScenarioRule.*;
import androidx.test.espresso.Espresso;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainUITest {


    // tells the system to launch this activity before any of the tests
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Before
    public void setupIntents() {
        // initializes intents and begins recording intents
        // in order to verify the intent, it must first be recorded
        // triggering any action that call an intent that we want to verify with validation/stubbing
        init();
    }


    @After
    public void teardownIntents() {
        // Clears intent state, must be called after each test case
        release();
    }


    @Test
    public void correctSubActivity_nte() {

        activityRule.getScenario().onActivity( mainActivity -> {

            mainActivity.getQuizBtn().performClick();

        });

        //intended(hasComponent("com.example.oblig1.GameActivity"));
        intended(hasComponent(GameActivity.class.getName()));
    }




/*





// ROT

    @Test
    public void correctSubActivity() {
        onView(withId(R.id.button_enterGame))
                .perform(click());

        intended(hasComponent("GameActivity"));

        //intended(hasComponent("GameActivity"));
    }



    @Test
    public void correctSubActivity_ny() {

        ActivityScenario<MainActivity> scenario = activityRule.getScenario();

        // your test code goes here


        activityRule.getScenario().onActivity( activity -> {
            // Perform actions or setup conditions on the activity
            // For example, interact with UI elements using Espresso:
            Espresso.onView(ViewMatchers.withId(R.id.button_enterGame))
                    .perform(ViewActions.click());
        });

    }



    @Test
    public void correctSubActivity_nyeste() {


        activityRule.getScenario().onActivity( mainActivity -> {

            mainActivity.getQuizBtn().performClick();


            GameActivity subActivity = (GameActivity) mainActivity.getCurrentActivity();



            // finne knappen som leder inn i GameActivity
            // trykke på denne knappen
            // sjekke at jeg nå befinner meg i riktig activity (GameActivity)


            // finne en riktig/en feil knapp, og deretter
            // sammenligne melding på skjerm med forventet resultat



            // Perform actions or setup conditions on the activity
            // For example, interact with UI elements using Espresso:
            Espresso.onView(ViewMatchers.withId(R.id.button_enterGame))
                    .perform(ViewActions.click());
        });



    }









*/

}









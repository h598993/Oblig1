package com.example.oblig1;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Denne test-klassen sjekker korrekt oppdatering av fremvist score i quiz-appen.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizUITest {


    // Forteller systemet at gitt aktivitet skal kjøres før hver test
    @Rule
    public ActivityScenarioRule<GameActivity> activityRule =
            new ActivityScenarioRule<>(GameActivity.class);


    /** Sjekker riktig oppdatering av score første runde (etter ett riktig svar) */
    @Test
    public void scoreUpdateFirstRoundCorrAns() {
        AtomicReference<Integer> riktigSvarIndexRef = new AtomicReference<>(null);
        activityRule.getScenario().onActivity( gameActivity -> {
            int riktigSvarIndex = gameActivity.getGameViewModel().getCorrectTextIndex();
            riktigSvarIndexRef.set(riktigSvarIndex);
        });
        switch(riktigSvarIndexRef.get()) {
            case 0:     onView(withId(R.id.option_one)).perform(click());   break;
            case 1:     onView(withId(R.id.option_two)).perform(click());   break;
            default:    onView(withId(R.id.option_three)).perform(click()); break;
        }
        // Ettersom riktig svar ble valgt, skal det vises "Correct!" på skjermen
        onView(withId(R.id.answer_response)).check(matches(withText("Correct!")));
        // Sammenligner score på skjermen med forventet resultat
        onView(withId(R.id.text_score)).check(matches(withText("1")));
    }


    /** Sjekker forventet score etter fem runder med feil svar */
    @Test
    public void scoreUpdateFiveRoundsWrong() {
        AtomicReference<Integer> riktigSvarIndexRef = new AtomicReference<>(null);
        for (int i = 1; i<=5; i++) {
            // Bruker activity-klassen til å lokalisere riktig svar
            activityRule.getScenario().onActivity(gameActivity -> {
                int riktigSvarIndex = gameActivity.getGameViewModel().getCorrectTextIndex();
                // Lagrer indeks/lokasjon i AtomicReference
                riktigSvarIndexRef.set(riktigSvarIndex);
            });
            // Velger feil svar hver gang
            switch (riktigSvarIndexRef.get()) {
                case 0: onView(withId(R.id.option_three)).perform(click()); break;
                case 1: onView(withId(R.id.option_one)).perform(click()); break;
                default: onView(withId(R.id.option_two)).perform(click()); break;
            }
            // Skal da vise "Wrong Answer" på skjermen
            onView(withId(R.id.answer_response)).check(matches(withText("Wrong Answer")));
        }
    }


    /** Sjekker riktig oppdatering av score gjennom 5 runder (det gis feil svar i 3. runde) */
    @Test
    public void scoreUpdateManyRoundsMixAns() {

        AtomicReference<Integer> riktigSvarIndexRef = new AtomicReference<>(null);

        int runde = 1;
        while(runde <= 5) {

            // Bruker activity-klassen til å lokalisere riktig svar
            activityRule.getScenario().onActivity(gameActivity -> {
                int riktigSvarIndex = gameActivity.getGameViewModel().getCorrectTextIndex();
                // Lagrer indeks/lokasjon i AtomicReference
                riktigSvarIndexRef.set(riktigSvarIndex);
            });

            if (runde == 3) {
                // Velger feil svar i 3. runde
                switch (riktigSvarIndexRef.get()) {
                    case 0: onView(withId(R.id.option_three)).perform(click()); break;
                    case 1: onView(withId(R.id.option_one)).perform(click()); break;
                    default: onView(withId(R.id.option_two)).perform(click()); break;
                }
                // Skal da vise "Wrong Answer" på skjermen
                onView(withId(R.id.answer_response)).check(matches(withText("Wrong Answer")));
                // Sjekker at skjermen viser forventet score etter 3. runde
                onView(withId(R.id.text_score)).check(matches(withText("2")));

            } else {
                // Velger riktig svar ellers
                switch (riktigSvarIndexRef.get()) {
                    case 0: onView(withId(R.id.option_one)).perform(click()); break;
                    case 1: onView(withId(R.id.option_two)).perform(click()); break;
                    default: onView(withId(R.id.option_three)).perform(click()); break;
                }
                // Skal da vise "Correct!" på skjermen
                onView(withId(R.id.answer_response)).check(matches(withText("Correct!")));
                // Sammenligner score på skjermen med forventet resultat
                switch (runde) {
                    case 1: onView(withId(R.id.text_score)).check(matches(withText("1"))); break;
                    case 2: onView(withId(R.id.text_score)).check(matches(withText("2"))); break;
                    case 4: onView(withId(R.id.text_score)).check(matches(withText("3"))); break;
                    case 5: onView(withId(R.id.text_score)).check(matches(withText("4"))); break;
                }
            }
            runde++;
        }
    }























// Is the score updated correctly in the quiz?
// (submit right/wrong answer and check if the score is correct afterwards)

// koble sammen aktivitets-klassen/objekt og test-cases,
// slik at testcasen kan finne en riktig/en feil knapp,
// og deretter sammenligne melding på skjerm med forventet resultat.




    // tells the system to launch this activity before any of the tests


    //AtomicReference<Integer> scoreRef = new AtomicReference<>(null);
    //int score = gameActivity.getGameViewModel().getCounter();
    //scoreRef.set(score + 1);    // skal økes med én i switch-setningen under
    //onView(withId(R.id.text_score)).check(matches(withText(scoreRef.get().toString())));





    // Sjekker at View viser forventet score etter 3. runde
    //onView(withId(R.id.text_score)).check(matches(withText("2")));


    // runde 1        1
    // runde 2        2
    // runde 3        2
    // runde 4        3
    // runde 5        4








    // Sjekker at View viser forventet score etter 3. runde
    //onView(withId(R.id.text_score)).check(matches(withText("2")));













    // AtomicReference<Integer> scoreRef = new AtomicReference<>(null);
    // int score = gameActivity.getGameViewModel().getCounter();
    //                 scoreRef.set(score);




    /* Score i View */                      /* Score fra GameViewModel */
    //onView(withId(R.id.text_score)).check(matches(withText(scoreRef.get().toString())));



    // Lage en som kun trykker på knapper (klassisk onView-test)
    //                 // Sjekker at View viser samme score som tilstanden i aktivit-etskallsen
    //                onView(withId(R.id.text_score)).check(matches(withText(scoreRef.get().toString())));


    // Lage en til test som tester oppdatering av score etter f.eks. 2 riktige og 1 feil svar






    // "Correct!"
    // "Wrong Answer"




    // 1. runde
    // 2. / 3. runde
    // riktig svar
    // feil svar



























/*


    @Before
    public void setupIntents() {
        // initializes intents and begins recording intents
        init();
    }


    @After
    public void teardownIntents() {
        // Clears intent state, must be called after each test case
        release();
    }





    //intended(hasComponent("com.example.oblig1.GameActivity"));
    intended(hasComponent(GameActivity.class.getName()));




            gameActivity.getQuizBtn().performClick();

    // har gameActivity-objektet:
    // hent ut score (id = "textView4", TextView counterView;)          Trenger getter
    // velg ett av de tre alternativiene (options)
    // finn ut om valgt alternativ er riktig


            switch(expression) {
        case x:
            // code block
            break;
        case y:
            // code block
            break;
        default:
            // code block
    }




    // counterView.setText(String.valueOf(gameViewModel.getCounter()));

    // riktigValgIndex;


    //gameActivity.getCounterView().getText().toString();





    // hvert bilde har et korrekt navn assosiert
    // må vite hva som er riktig svar blant tre alternativer
    // velge riktig/feil alternativ
    // sjekke at score er korrekt





    // godt mulig jeg må legge til en getGameViewModel() i GameActivity-klassen
    // (trenger tilgang til info. som ligger i GameViewModel fra gameActivity-objekt)
    //  - currentItem   (Item)
    //  - correctAnswer (String)











*/


}


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
    @Rule public ActivityScenarioRule<GameActivity> activityRule =
            new ActivityScenarioRule<>(GameActivity.class);


    /** Sjekker riktig oppdatering av score første runde (etter ett riktig svar) */
    @Test
    public void scoreUpdateFirstRoundCorrAns() {
        AtomicReference<Integer> riktigSvarIndexRef = new AtomicReference<>(null);
        // Bruker activityRule til å hente ut gameActivity-objekt
        activityRule.getScenario().onActivity( gameActivity -> {
            // Bruker activity-objekt til å lokalisere riktig svar
            int riktigSvarIndex = gameActivity.getGameViewModel().getCorrectTextIndex();
            // // Lagrer lokasjon/indeks i en AtomicReference (til bruk utenfor onActivity)
            riktigSvarIndexRef.set(riktigSvarIndex);
        });
        // Henter ut, og bruker indeksen til å trykke på riktig knapp (korrekt svaralternativ)
        switch(riktigSvarIndexRef.get()) {
            case 0:     onView(withId(R.id.option_one)).perform(click());   break;
            case 1:     onView(withId(R.id.option_two)).perform(click());   break;
            default:    onView(withId(R.id.option_three)).perform(click()); break;
        }
        // Ettersom riktig svar ble valgt, skal det vises "Correct!" på skjermen (verifiseres)
        onView(withId(R.id.answer_response)).check(matches(withText("Correct!")));
        // Sammenligner score på skjermen med forventet resultat
        onView(withId(R.id.text_score)).check(matches(withText("1")));
    }


    /** Sjekker forventet score etter fem runder med feil svar */
    @Test
    public void scoreUpdateFiveRoundsWrong() {
        AtomicReference<Integer> riktigSvarIndexRef = new AtomicReference<>(null);
        for (int i = 1; i<=5; i++) {
            // Bruker activity-objekt til å lokalisere riktig svar
            activityRule.getScenario().onActivity(gameActivity -> {
                int riktigSvarIndex = gameActivity.getGameViewModel().getCorrectTextIndex();
                // Lagrer lokasjon/indeks i en AtomicReference (til bruk utenfor onActivity)
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
        onView(withId(R.id.text_score)).check(matches(withText("0")));
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
                // Sammenligner score på skjermen med forventet resultat (for gitt runde)
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

}


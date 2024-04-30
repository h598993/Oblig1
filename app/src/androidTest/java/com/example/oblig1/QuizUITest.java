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


@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizUITest {

    // tells the system to launch this activity before any of the tests
    @Rule
    public ActivityScenarioRule<GameActivity> activityRule =
            new ActivityScenarioRule<>(GameActivity.class);


    // Is the score updated correctly in the quiz?
    // (submit right/wrong answer and check if the score is correct afterwards)
    
    @Test
    public void scoreUpdateCorrectAnswer() {

        AtomicReference<Integer> riktigSvarIndexRef = new AtomicReference<>(null);
        AtomicReference<Integer> scoreRef = new AtomicReference<>(null);

        activityRule.getScenario().onActivity( gameActivity -> {

            int riktigSvarIndex = gameActivity.getGameViewModel().getCorrectTextIndex();
            int score = gameActivity.getGameViewModel().getCounter();

            riktigSvarIndexRef.set(riktigSvarIndex);
            scoreRef.set(score + 1);
        });

        switch(riktigSvarIndexRef.get()) {
            case 0:     onView(withId(R.id.option_one)).perform(click());   break;
            case 1:     onView(withId(R.id.option_two)).perform(click());   break;
            default:    onView(withId(R.id.option_three)).perform(click()); break;
        }

        onView(withId(R.id.text_score)).check(matches(withText(scoreRef.get().toString())));
    }




    // Lage en til test som tester oppdatering av score etter f.eks. 2 riktige og 1 feil svar


































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


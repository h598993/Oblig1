package com.example.oblig1;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(AndroidJUnit4.class)
@LargeTest
public class GalleryUITest {
/*

    // a test that checks that the number of registered pictures
    // is correct after adding/deleting an entry.

    // For adding, use Intent Stubbing to return some image data
    // (e.g. from the resource-folder) without any user interaction.



    // Forteller systemet at gitt aktivitet skal kjøres før hver test
    @Rule
    public ActivityScenarioRule<Gallery> activityRule =
            new ActivityScenarioRule<>(Gallery.class);


    // Initializes intents and begins recording intents
    @Before
    public void setupIntents() {

        init();
    }


    // Clears intent state, must be called after each test case
    @After
    public void teardownIntents() {
        release();
    }



    // a test that checks that the number of registered pictures
    // is correct after adding/deleting an entry.

    // For adding, use Intent Stubbing to return some image data
    // (e.g. from the resource-folder) without any user interaction.

    @Test
    public void testAddDog() {


        onView(withId(R.id.button_addItem)).perform(click());





        activityRule.getScenario().onActivity( mainActivity -> {

            mainActivity.getQuizBtn().performClick();

        });

        //intended(hasComponent("com.example.oblig1.GameActivity"));
        intended(hasComponent(GameActivity.class.getName()));





        onView(withId(R.id.option_one)).perform(click());


        onView(withId(R.id.text_score)).check(matches(withText("1")));

















    }




    @Test
    public void testDeleteDog() {



        onView(withId(R.id.button_addItem)).perform(click());





    }







/*


Intents is a datastructure that holds a discription of an action to be performed
Used to pass data between activities alot

Intent stubbing mimics



"Intending" is used to validate THAT an activity has launched







*/






}


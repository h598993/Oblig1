package com.example.oblig1;


import static android.app.Activity.RESULT_OK;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class GalleryUITest {


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



    // 1. Build the result to return when a particular activity is launched.
    //    (når button_addItem klikkes, blir AddItem-activityen launchet. Definer hva AddItem skal returnere/gjøre)


    //public xxx activityResult_



    // a test that checks that the number of registered pictures
    // is correct after adding/deleting an entry.

    // For adding, use Intent Stubbing to return some image data
    // (e.g. from the resource-folder) without any user interaction.

    @Test
    public void activityResult_DispCorrNumDogs_afterAddingDog() throws InterruptedException {
//
//        intending(hasComponent(hasShortClassName(".AddItem")))
//                .respondWith(new Instrumentation.ActivityResult(
//                        Activity.RESULT_OK,
//                        ContactsActivity.createResultData(VALID_PHONE_NUMBER)));
//
//
//
//        // 1. Build the result to return when the AddItem activity is launched
//        Intent resultData = new Intent();
//
//        String race = ""
//
//
//
//
//        // Stub all Intents to ContactsActivity to return VALID_PHONE_NUMBER. Note that the Activity
//        // is never launched and result is stubbed.
//        intending(hasComponent(hasShortClassName(".ContactsActivity")))
//                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,
//                        ContactsActivity.createResultData(VALID_PHONE_NUMBER)));
//
//
//
//        String bilde = "123-345-6789";
//        resultData.putExtra("phone", phoneNumber);
//        Instrumentation.ActivityResult result =
//                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
//
//        // Set up result stubbing when an intent sent to "contacts" is seen.
//        intending(toPackage("com.android.contacts")).respondWith(result);
//
//        // User action that results in "contacts" activity being launched.
//        // Launching activity expects phoneNumber to be returned and displayed.
//        onView(withId(R.id.pickButton)).perform(click());
//
//        // Assert that the data we set up above is shown.
//        onView(withId(R.id.phoneNumber)).check(matches(withText(phoneNumber)));
//

        Intent i = new Intent();
        i.setType("image/png");
        i.setData(Uri.parse("android.resource://com.example.oblig1/drawable/bulldog"));
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(RESULT_OK, i);

        intending(hasAction(Intent.ACTION_OPEN_DOCUMENT)).respondWith(result);

        AtomicReference<Integer> refAntallHunderDB = new AtomicReference<>(null);

        // Henter ut antall hunder (Items) i DB før sletting
        activityRule.getScenario().onActivity( galleryActivity -> {
            int antallHunderDB = galleryActivity.getItemViewModel().getNumItems();
            // Lagrer antall i AtomicReference
            refAntallHunderDB.set(antallHunderDB);
        });
        int dogsBefore = refAntallHunderDB.get();

        onView(withId(R.id.button_addItem)).perform(click());

        intended(hasComponent(AddItem.class.getName()));


        onView(withId(R.id.button_get_image)).perform(click());
        onView(withId(R.id.input_dog_race_edit)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.button_add_dog)).perform(click());

        Thread.sleep(1000);

        // Henter ut antall hunder (Items) i DB før sletting
        activityRule.getScenario().onActivity( galleryActivity -> {
            int antallHunderDB = galleryActivity.getItemViewModel().getNumItems();
            // Lagrer antall i AtomicReference
            refAntallHunderDB.set(antallHunderDB);
            assertEquals(dogsBefore+1, refAntallHunderDB.get().intValue());
        });

    }


     static class MyViewAction {

        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }

    }

    @Test
    public void testDeleteDog() throws InterruptedException {

        AtomicReference<Integer> refAntallHunderDB = new AtomicReference<>(null);

        // Henter ut antall hunder (Items) i DB før sletting
        activityRule.getScenario().onActivity( galleryActivity -> {
            int antallHunderDB = galleryActivity.getItemViewModel().getNumItems();
            // Lagrer antall i AtomicReference
            refAntallHunderDB.set(antallHunderDB);
        });

        // For å unngå NullPointerException
        if (refAntallHunderDB.get() == -1) {
            onView(withId(R.id.answer_response)).check(matches(withText("Hello!")));

        // Dersom databasen er tom
        } else if (refAntallHunderDB.get() == 0) {

            // må da legge til et item først...
            onView(withId(R.id.answer_response)).check(matches(withText("Hello!")));
        } else {

            // Sletter entry

            int dogsBefore = refAntallHunderDB.get();
            onView(withId(R.id.mRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1,
                    MyViewAction.clickChildViewWithId(R.id.button_image_delete)));

            // sjekke at antall er én mindre i sted (med mindre det er null bilder der)
            /* Need to wait for DB to write change -- alternatively, getAllItems().observe() BEFORE we click the button,
               and look for the delete action? */
            Thread.sleep(1000);
            // Henter ut antall Items i DB på ny
            activityRule.getScenario().onActivity( galleryActivity -> {

                int antallHunderDB = galleryActivity.getItemViewModel().getNumItems();
                refAntallHunderDB.set(antallHunderDB);
            });
            assertEquals( dogsBefore-1, refAntallHunderDB.get().intValue());

            // activity_gallery har en mRecyclerView  -->  Går det ann å hente ut ant. Items via denne?
        }

    }

/*


Intents is a datastructure that holds a discription of an action to be performed
Used to pass data between activities alot

Intent stubbing mimics



"Intending" is used to validate THAT an activity has launched







*/






}


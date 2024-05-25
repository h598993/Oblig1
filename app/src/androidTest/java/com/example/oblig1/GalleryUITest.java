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

/**
 * Denne test-klassen sjekker at antall registerte hunder/Items
 * er korrekt før/etter en hund er lagt til/slettet.
 * */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GalleryUITest {


    // Forteller systemet at gitt aktivitet skal kjøres før hver test
    @Rule public ActivityScenarioRule<Gallery> activityRule =
            new ActivityScenarioRule<>(Gallery.class);


    @Before
    public void setupIntents() {
        // Initializes intents and begins recording intents
        init();
    }


    @After
    public void teardownIntents() {
        // Clears intent state, must be called after each test case
        release();
    }


    /** Sjekker at antall hunder/Items i DB er korrekt etter en ny registering */
    @Test
    public void activityResult_DispCorrNumDogs_afterAddingDog() throws InterruptedException {

        // Oppretter Intent og angir type (String) og data (Uri)
        // Intents er en datastruktur som inneholder en beskrivelse av en action som skal utføres
        // (inneholder også data og info. om hvilken type data)
        Intent i = new Intent();
        i.setType("image/png");
        i.setData(Uri.parse("android.resource://com.example.oblig1/drawable/bulldog"));

        // Oppretter ActivityResult (gir inn "activity result"-kode sammen med Intent)
        // result som opprettes her utgjør "the stub" (Intent Stubbing)
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(RESULT_OK, i);

        // Definerer at dersom det sendes ut en Intent som har en ACTION_OPEN_DOCUMENT-action,
        // så skal det responderes med "result" (en ActivityResult vi har konstruert/stubbet).
        // I stedenfor at gitt Intent starter en activity som etter brukerinteraksjon sender
        // en ActivityResult tilbake, så blir Intenten "fanget opp" (og blokkert) av init()
        // og den definerte stubben sendes automatisk som respons uten brukerinteraksjon.
        // MERK! intending = fremtid (hvis... så STUB)
        intending(hasAction(Intent.ACTION_OPEN_DOCUMENT)).respondWith(result);

        AtomicReference<Integer> refAntallHunderDB = new AtomicReference<>(null);

        // Henter ut antall hunder/Items i DB FØR registrering (via activity-objektet)
        activityRule.getScenario().onActivity( galleryActivity -> {
            int antallHunderDB = galleryActivity.getItemViewModel().getNumItems();
            // Lagrer antall i AtomicReference (for å bruke verdien videre utenfor onActivity)
            refAntallHunderDB.set(antallHunderDB);
        });
        int antallHunderFor = refAntallHunderDB.get();

        // "befinner oss" i Gallery: Finner og klikker på "Add new item"-knappen
        onView(withId(R.id.button_addItem)).perform(click());

        // Verifiserer at det ble sendt ut en intent for AddItem-aktiviteten
        // Bruker hasComponent(...) for identifisering
        // MERK! intended = fortid (validere om noe HAR skjedd)
        intended(hasComponent(AddItem.class.getName()));

        // Skjermen viser nå AddItem-aktiviteten: Utfører handlinger for å legge til Item/hund
        // Den første linjen klikker på knapp hvor stubben gis som respons (bilde velges automatisk)
        onView(withId(R.id.button_get_image)).perform(click());
        onView(withId(R.id.input_dog_race_edit)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(R.id.button_add_dog)).perform(click());

        // Gir systemet litt tid til å oppdatere databasen...
        Thread.sleep(1000);

        // Henter ut antall hunder/Items i DB ETTER registrering
        activityRule.getScenario().onActivity( galleryActivity -> {
            int antallHunderEtter = galleryActivity.getItemViewModel().getNumItems();

            // Sjekker at antall hunder/Items registrert i DB er riktig etter at stub ble lagt til
            assertEquals(antallHunderFor+1, antallHunderEtter);
        });
    }


    /** Sjekker at antall hunder/Items i DB er korrekt etter sletting */
    @Test
    public void testSlettHund() throws InterruptedException {

        AtomicReference<Integer> refAntallHunderDB = new AtomicReference<>(null);

        // Henter ut antall hunder/Items i DB FØR sletting
        activityRule.getScenario().onActivity( galleryActivity -> {
            // Tilfelle problemer med LiveData<List<Item>> i ItemViewModel,
            // så vil getNumItems() returnere -1 heller enn å kaste NullPointerException
            int antallHunderDB = galleryActivity.getItemViewModel().getNumItems();
            // Lagrer antall i AtomicReference (for å bruke verdien videre utenfor onActivity)
            refAntallHunderDB.set(antallHunderDB);
        });

        int antallHunderFor = refAntallHunderDB.get();

        // Dersom getNumItems() returnerte -1 skal testen feile
        if (antallHunderFor == -1) {
            onView(withId(R.id.answer_response)).check(matches(withText("Hello!")));

        // Dersom databasen er tom
        } else if (antallHunderFor == 0) {
            // Må da legge til et item først...
            // Lar heller testen feile for nå
            onView(withId(R.id.answer_response)).check(matches(withText("Hello!")));

        } else {
            // Sletter entry
            onView(withId(R.id.mRecyclerView))
                    .perform( RecyclerViewActions.actionOnItemAtPosition(1,
                    MyViewAction.clickChildViewWithId(R.id.button_image_delete)));

            // sjekke at antall er én mindre enn i sted (med mindre det er null bilder der)

            // Må vente for å gi DB tid til å skrive endring...
            Thread.sleep(1000);

            // Henter ut antall Items i DB på ny
            activityRule.getScenario().onActivity( galleryActivity -> {
                int antallHunderEtter = galleryActivity.getItemViewModel().getNumItems();

                // Sjekker at antall registrerte hunder/Items i DB er én mindre enn før sletting
                assertEquals(antallHunderFor-1, antallHunderEtter);
            });
        }
    }


    /** Hjelpe-klasse til bruk i testSlettHund-testen */
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

}


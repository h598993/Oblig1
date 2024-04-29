package com.example.oblig1;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

import java.util.Collections;


public class GameViewModel extends ViewModel {
    boolean firstSetup = true;
    private int counter = 0;
    private int correctTextIndex;
    private Random random = new Random();
    private ArrayList<String> options = new ArrayList<>();
    private String correctAnswer;
    Item currentItem;

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public int getCorrectTextIndex() {
        return correctTextIndex;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    //setter opp quiz første gang
    public void prepareQuiz(ArrayList<Item> items, String[] dogNames) {
        if (firstSetup) {
            int itemIndex = random.nextInt(items.size());
            currentItem = items.get(itemIndex);
            correctAnswer = currentItem.getName();

            // Reset options list and fill with dog names, ensuring correct answer is included
            options.clear();
            Collections.addAll(options, dogNames);

            // Ensure correct answer is not duplicated if it's already a dog name
            options.remove(correctAnswer);
            Collections.shuffle(options, random);
            correctTextIndex = random.nextInt(3);
            options.add(correctTextIndex, correctAnswer);  // Insert correct answer at random position

            firstSetup = false;
        }
    }

    // kaller denne for hver gang bruker trykker på en av knappene. Stter opp nytt spørsmål
    public void prepareNewQuiz(ArrayList<Item> items, String[] dogNames) {

        // Hener ut en tilfledig hund fra listen og setter navnent til riktig hundenavn
        int itemIndex = random.nextInt(items.size());
        currentItem = items.get(itemIndex);
        correctAnswer = currentItem.getName();

        // legger til tilfledige hundenavn som skal vises på skjermen.
        options.clear();
        Collections.addAll(options, dogNames);

        //fjerner det riktige navnet og velger ut to ulike hundenavn som skal fylle de to andre valgene
        options.remove(correctAnswer);
        Collections.shuffle(options, random);
        correctTextIndex = random.nextInt(3);
        options.add(correctTextIndex, correctAnswer);

    }


}

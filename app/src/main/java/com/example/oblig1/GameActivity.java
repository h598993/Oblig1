package com.example.oblig1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;

    ImageView image;
    TextView optionOne;
    TextView optionTwo;
    TextView optionThree;
    TextView counterView;
    TextView answerResponse;

    MyApplication globalState;
    //tilfeldige navn
    String[] randomDogNames = {"Puddel", "Dalmantiner", "Sjefer", "Fuglehund", "Rottweiler"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //oppretter en kobling til viewmodel
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        globalState = (MyApplication) getApplicationContext();

        // Binder UI komponenenter
        image = findViewById(R.id.image_game);
        optionOne = findViewById(R.id.option_one);
        optionTwo = findViewById(R.id.option_two);
        optionThree = findViewById(R.id.option_three);
        counterView = findViewById(R.id.text_score);
        answerResponse = findViewById(R.id.answer_response);
        counterView.setText(String.valueOf(gameViewModel.getCounter()));

        //setter opp quizen og lyttere på knappene første gang
        setupQuiz();
        setupClickListeners();
    }

    //setter opp quizen ved første innlasting
    private void setupQuiz() {

        ArrayList<Item> items = globalState.getItems();
        gameViewModel.prepareQuiz(items, randomDogNames);

        image.setImageURI(gameViewModel.getCurrentItem().getImageUri());

        optionOne.setText(gameViewModel.getOptions().get(0));
        optionTwo.setText(gameViewModel.getOptions().get(1));
        optionThree.setText(gameViewModel.getOptions().get(2));
    }

    //Setter opp ny quiz når bruker trykker på et svar
    private void setupNewQuiz() {
        ArrayList<Item> items = globalState.getItems();
        gameViewModel.prepareNewQuiz(items, randomDogNames);

        image.setImageURI(gameViewModel.getCurrentItem().getImageUri());

        optionOne.setText(gameViewModel.getOptions().get(0));
        optionTwo.setText(gameViewModel.getOptions().get(1));
        optionThree.setText(gameViewModel.getOptions().get(2));
    }

    // setter opp lytter på de tre ulike valgene
    private void setupClickListeners() {
        View.OnClickListener checkAnswerListener = v -> {
            int selectedOptionIndex = v.equals(optionOne) ? 0 : v.equals(optionTwo) ? 1 : 2;
            checkAnswer(selectedOptionIndex);
        };

        optionOne.setOnClickListener(checkAnswerListener);
        optionTwo.setOnClickListener(checkAnswerListener);
        optionThree.setOnClickListener(checkAnswerListener);
    }

    // sjekker om det valgte svaret er riktig
    private void checkAnswer(int selectedOptionIndex) {
        if (gameViewModel.getCorrectTextIndex() == selectedOptionIndex) {
            gameViewModel.incrementCounter();
            counterView.setText(String.valueOf(gameViewModel.getCounter()));
            answerResponse.setText("Correct!");
        } else {
            answerResponse.setText("Wrong Answer");
        }
        setupNewQuiz();
    }

    // Lagt til for å kunne hente ut intern tilstand ifm. testing
    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }


}

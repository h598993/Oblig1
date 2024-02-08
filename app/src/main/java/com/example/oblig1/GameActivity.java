package com.example.oblig1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

Random randomObjectToShow;
Random randomPosition;
int counter;
int correctTextIndex;

    ImageView image;
    TextView optionOne;
    TextView optionTwo;
    TextView optionThree;
    TextView counterView;

    TextView answerResponse;

    MyApplication globalState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        answerResponse = findViewById(R.id.answer_response);

        //Score counter
        counter = 0;

       setupQuiz();


        // setting up click events

        optionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctTextIndex == 0){
                    counter++;
                    counterView.setText(String.valueOf(counter));
                    answerResponse.setText("Correct!");
                    setupQuiz();
                }else{

                    answerResponse.setText("Wrong Ansver");
                    setupQuiz();
                }



            }
        });




        optionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctTextIndex == 1){
                    counter++;
                    counterView.setText(String.valueOf(counter));
                    answerResponse.setText("Correct!");
                    setupQuiz();
                }else{

                    answerResponse.setText("Wrong Ansver");
                    setupQuiz();
                }



            }
        });




        optionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctTextIndex == 2){
                    counter++;
                    counterView.setText(String.valueOf(counter));
                    answerResponse.setText("Correct!");
                    setupQuiz();
                }else{

                    answerResponse.setText("Wrong Ansver");
                    setupQuiz();
                }



            }
        });


        Button backButton = findViewById(R.id.button_back_game);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(GameActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        });




    }


    private void setupQuiz (){

        globalState = (MyApplication) getApplicationContext();


        // Getting references
        image = findViewById(R.id.image_game);
        optionOne = findViewById(R.id.option_one);
        optionTwo = findViewById(R.id.option_two);
        optionThree = findViewById(R.id.option_three);
        counterView = findViewById(R.id.text_score);




        // Storing list of strings to choose from in the quiz
        String[] randomDogNames = {"Puddel","Dalmantiner","Sjefer","Fuglehund","Rottveiler"};

        //setting up the list to choose from
        ArrayList<Item> items = globalState.getItems();
        int nrOfItems = items.size();




        //Setting up first picture
        Random r1 = new Random();
        int randomObjectIndex = r1.nextInt(nrOfItems);

        Item firstItem =  items.get(randomObjectIndex);
        image.setImageURI(firstItem.getImageUri());

        //setting up first text
        Random r2 = new Random();
        String correctText = firstItem.getName();
        correctTextIndex = r2.nextInt(3);



    // really bad way of making sure to not pick the same name twice
        int firstPick = r2.nextInt(randomDogNames.length);
        int secondPick = r2.nextInt(randomDogNames.length);
        if(firstPick == secondPick){
            while (true){
                secondPick = r2.nextInt(randomDogNames.length);
                if(firstPick != secondPick) break;
            }
        }


        //Binding the correct text to one of the three options then filling in the remaing with random names from the list
        switch (correctTextIndex){

            case 0:
                optionOne.setText(correctText);

                optionTwo.setText(randomDogNames[firstPick]);
                optionThree.setText(randomDogNames[secondPick]);

                break;

            case 1:
                optionTwo.setText(correctText);

                optionOne.setText(randomDogNames[firstPick]);
                optionThree.setText(randomDogNames[secondPick]);


                break;

            case 2:
                optionThree.setText(correctText);

                optionOne.setText(randomDogNames[firstPick]);
                optionTwo.setText(randomDogNames[secondPick]);


                break;

        }


    }





}

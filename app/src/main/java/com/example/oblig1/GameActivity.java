package com.example.oblig1;

import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MyApplication globalState = (MyApplication) getApplicationContext();
        // Getting references
        ImageView image = findViewById(R.id.image_game);
        TextView optionOne = findViewById(R.id.option_one);
        TextView optionTwo = findViewById(R.id.option_two);
        TextView optionThree = findViewById(R.id.option_three);
        TextView counterView = findViewById(R.id.text_score);

        //Score counter
        counter = 0;

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
        int correctTextIndex = r2.nextInt(3);




        int firstPick = r2.nextInt(randomDogNames.length);
        int secondPick = r2.nextInt(randomDogNames.length);
        if(firstPick == secondPick){
            while (true){
                secondPick = r2.nextInt(randomDogNames.length);
                if(firstPick != secondPick) break;
            }
        }


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



        // setting up click events

        optionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctTextIndex == 0){
                    counter++;
                    counterView.setText(String.valueOf(counter));
                }else{
                    counter--;
                    counterView.setText(String.valueOf(counter));
                }



            }
        });




        optionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctTextIndex == 1){
                    counter++;
                    counterView.setText(String.valueOf(counter));
                }else{
                    counter--;
                    counterView.setText(String.valueOf(counter));
                }



            }
        });




        optionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctTextIndex == 2){
                    counter++;
                    counterView.setText(String.valueOf(counter));
                }else{
                    counter--;
                    counterView.setText(String.valueOf(counter));
                }



            }
        });




    }


    private void setupQuiz (){
        


    }





}

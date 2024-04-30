package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button galleryBtn;
    Button quizBtn;

    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        galleryBtn = findViewById(R.id.button_gallery);
        quizBtn = findViewById(R.id.button_enterGame);





        //Button logic to go to Gallery
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGallery = new Intent(MainActivity.this, Gallery.class);
                startActivity(goToGallery);
            }
        });



// Button for changing to game menu
        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoGameMenu = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gotoGameMenu);
            }
        });



    }

    public View getQuizBtn() {
        return this.quizBtn;
    }


}
package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button galleryBtn;
    Button quizBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        galleryBtn = findViewById(R.id.button_gallery);
        quizBtn = findViewById(R.id.button_enterGame);


        //Knapp for å gå til galleriet
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGallery = new Intent(MainActivity.this, Gallery.class);
                startActivity(goToGallery);
            }
        });


        // Knapp for å gå til nytt spill
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
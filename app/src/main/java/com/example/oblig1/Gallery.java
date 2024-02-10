package com.example.oblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // referencing global state
        MyApplication globalState = (MyApplication) getApplicationContext();
        setContentView(R.layout.activity_gallery);
        //backButton
        Button backBtn = findViewById(R.id.button_back_gallery);
        //Button for switching to add item activity
        Button addItemBtn = findViewById(R.id.button_addItem);
        Button sortAsc = findViewById(R.id.button_sort_asc);
        Button sortDec = findViewById(R.id.button_sort_dec);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNewItemActivity = new Intent(Gallery.this, AddItem.class );
                startActivity(goToNewItemActivity);

            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(Gallery.this, MainActivity.class);
                startActivity(goBack);
            }
        });






        // Setting up list
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        RecyclerViewAdapter adapter =  new RecyclerViewAdapter(this,globalState.getItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Sort Logic

        sortAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalState.getItems().sort(Item.compareByNameAZ());
                adapter. notifyDataSetChanged();
            }
        });

        sortDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalState.getItems().sort(Item.compareByNameZA());
                adapter.notifyDataSetChanged();
            }
        });



    }


}

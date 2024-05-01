package com.example.oblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class Gallery extends AppCompatActivity implements ItemDeleteListener {
    private ItemViewModel itemViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        // Setup ViewModel
        ItemDao itemDao = AppDatabase.getDatabase(getApplicationContext()).itemDao();
        itemViewModel = new ViewModelProvider(this, new ItemViewModelFactory(itemDao)).get(ItemViewModel.class);




        // referencing global state
        MyApplication globalState = (MyApplication) getApplicationContext();

        //backButton
        Button backBtn = findViewById(R.id.button_back_gallery);
        //Button for switching to add item activity
        Button addItemBtn = findViewById(R.id.button_addItem);
        Button sortAsc = findViewById(R.id.button_sort_asc);
        Button sortDec = findViewById(R.id.button_sort_dec);


        // Setting up list
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        RecyclerViewAdapter adapter =  new RecyclerViewAdapter(this,Collections.emptyList(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Observing data changes
        itemViewModel.getAllItems().observe(this, items -> {
            adapter.updateItems(items);
            adapter.notifyDataSetChanged();
        });


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
                // finish();
            }
        });


        // Sorting logic
        sortAsc.setOnClickListener(v -> {
            adapter.sortItems(true);  // True for ascending
        });

        sortDec.setOnClickListener(v -> {
            adapter.sortItems(false);  // False for descending
        });



    }

    //passing onDeleteItem to the recyclerview to be able to delte from DB
    @Override
    public void onDeleteItem(Item item) {
        itemViewModel.deleteItem(item);
    }


}

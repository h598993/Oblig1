package com.example.oblig1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {


    ArrayList<Item> items = new ArrayList<>();
    int[] dogs = {R.drawable.bulldog,R.drawable.cockerspaniel,R.drawable.labrador};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        setupItemModels();

RecyclerViewAdapter adapter =  new RecyclerViewAdapter(this,items);

recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void setupItemModels(){
String[] dognames = new String[3];
dognames[0] = "Bulldog";
dognames[1] = "Some other dog";
dognames[2] = "Another Dog";

for( int i = 0; i < dognames.length; i++){

    items.add(new Item(dognames[i],dogs[i]));
}

    }
}

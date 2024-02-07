package com.example.oblig1;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {


    ArrayList<Item> items = new ArrayList<>();
    int[] dogs = {R.drawable.bulldog,R.drawable.cockerspaniel,R.drawable.labrador};


    ArrayList<Uri> dogImages = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        setupItemModels();

RecyclerViewAdapter adapter =  new RecyclerViewAdapter(this,items);

recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
MyApplication globalState = (MyApplication) getApplicationContext();
    }


    private void setupItemModels(){
String[] dognames = new String[3];
dognames[0] = "Bulldog";
dognames[1] = "Cockerspaniel";
dognames[2] = "Labrador";
        dogImages.add(Uri.parse("android.resource://com.example.oblig1/drawable/bulldog"));
        dogImages.add(Uri.parse("android.resource://com.example.oblig1/drawable/cockerspaniel"));
        dogImages.add(Uri.parse("android.resource://com.example.oblig1/drawable/labrador"));
for( int i = 0; i < dognames.length; i++){

    items.add(new Item(dognames[i],dogImages.get(i)));
}



    }
}

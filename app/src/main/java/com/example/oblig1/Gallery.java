package com.example.oblig1;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication globalState = (MyApplication) getApplicationContext();
        setContentView(R.layout.activity_gallery);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);


RecyclerViewAdapter adapter =  new RecyclerViewAdapter(this,globalState.getItems());

recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}

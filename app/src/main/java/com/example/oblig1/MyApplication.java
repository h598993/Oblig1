package com.example.oblig1;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.net.Uri;

import java.util.ArrayList;

public class MyApplication extends Application implements ComponentCallbacks2 {
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Uri> dogImages = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        setupItemModels();
    }

    public void addItemToList(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
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

            items.add(new Item(dognames[i],dogImages.get(i).toString()));
        }



    }




}

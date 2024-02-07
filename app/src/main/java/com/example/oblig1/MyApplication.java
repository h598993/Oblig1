package com.example.oblig1;

import android.app.Application;
import android.content.ComponentCallbacks2;

import java.util.ArrayList;

public class MyApplication extends Application implements ComponentCallbacks2 {
    ArrayList<Item> items = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();




    }

    public void addItemToList(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

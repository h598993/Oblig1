package com.example.oblig1;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.net.Uri;

import java.util.ArrayList;

public class MyApplication extends Application implements ComponentCallbacks2 {

    @Override
    public void onCreate() {
        super.onCreate();
        setupItemModels();
    }

    //Legger til elementer til galleriet dersom databasen er tom
    private void setupItemModels() {
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Uri> dogImages = new ArrayList<>();

        ItemDao itemDao = AppDatabase.getDatabase(getApplicationContext()).itemDao();

        String[] dognames = new String[3];
        dognames[0] = "Bulldog";
        dognames[1] = "Cockerspaniel";
        dognames[2] = "Labrador";
        dogImages.add(Uri.parse("android.resource://com.example.oblig1/drawable/bulldog"));
        dogImages.add(Uri.parse("android.resource://com.example.oblig1/drawable/cockerspaniel"));
        dogImages.add(Uri.parse("android.resource://com.example.oblig1/drawable/labrador"));
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (itemDao.getAllasList().size() == 0) {
                    for (int i = 0; i < dognames.length; i++) {
                        itemDao.insertItem(new Item(dognames[i], dogImages.get(i).toString()));
                    }
                }
            }
        }.start();


    }


}

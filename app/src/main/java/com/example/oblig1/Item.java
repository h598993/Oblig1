package com.example.oblig1;

import android.net.Uri;

public class Item {

    String name;
    Uri image;

    public Item(String name, Uri image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Uri getImageUri() {
        return image;
    }
}

package com.example.oblig1;

import android.net.Uri;

import java.util.Comparator;

public class Item {

    String name;
    Uri image;

    public Item(String name, Uri image) {
        this.name = name;
        this.image = image;
    }


    public static Comparator<Item> compareByNameAZ (){
        return new Comparator<Item>() {
            @Override
            public int compare(Item I1, Item I2) {
                return I1.getName().compareTo(I2.getName()) ;
            }
        };
    }
    public static Comparator<Item> compareByNameZA (){
        return new Comparator<Item>() {
            @Override
            public int compare(Item I1, Item I2) {
                return I2.getName().compareTo(I1.getName()) ;
            }
        };
    }

    public String getName() {
        return name;
    }

    public Uri getImageUri() {
        return image;
    }
}

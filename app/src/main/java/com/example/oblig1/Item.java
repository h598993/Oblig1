package com.example.oblig1;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity
public class Item {
    @PrimaryKey(autoGenerate=true)
    private int uid;
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image")
    private Uri image;

    public Item(String name, Uri image) {
        this.name = name;
        this.image = image;
    }

    public Item(){};


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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}

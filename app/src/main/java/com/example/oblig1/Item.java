package com.example.oblig1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "items")
public class Item {
    @PrimaryKey(autoGenerate=true)
    private int uid;
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image")
    private String image;

    public Item(String name, String image) {
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

    public String getImage() {
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

    public void setImage(String image) {
        this.image = image;
    }
}

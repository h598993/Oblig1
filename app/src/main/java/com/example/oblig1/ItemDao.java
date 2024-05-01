package com.example.oblig1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ItemDao {
    @Insert
    void insertItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getAll();


    @Query("DELETE FROM items")
    void deleteAllItems();

}

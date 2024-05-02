package com.example.oblig1;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemViewModel extends ViewModel {
    private final ItemDao itemDao;
    private final LiveData<List<Item>> allItems;


    public ItemViewModel(ItemDao itemDao) {
        this.itemDao = itemDao;
        allItems = itemDao.getAll();
    }

    // Method to get all items
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    // Method to get number of items in DB
    public int getNumItems() {
        if (allItems == null || allItems.getValue() == null) {
            return -1;
        }
        return allItems.getValue().size();
    }

    // Method to insert an item
    public void insert(Item item) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.insertItem(item);
        });
    }

    // Method to delete all items in DB
    public void deleteAllItems() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.deleteAllItems();
        });

    }

    // Method to delete specific item from DB
    public void deleteItem(Item item) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.deleteItem(item);
        });
    }


}

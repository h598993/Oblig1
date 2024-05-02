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

    // Metode for å hente ut alle data
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    // Returnerer antall elementer i databasen
    public int getNumItems() {
        if (allItems == null || allItems.getValue() == null) {
            return -1;
        }
        return allItems.getValue().size();
    }

    // Metode som legger til et element til databasen
    public void insert(Item item) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.insertItem(item);
        });
    }

    // Metode som sletter et element fra databasen
    public void deleteAllItems() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.deleteAllItems();
        });

    }

    // Metode som fjerner et spesifikt element fra databasen
    public void deleteItem(Item item) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.deleteItem(item);
        });
    }


}

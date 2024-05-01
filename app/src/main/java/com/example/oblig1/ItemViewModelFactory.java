package com.example.oblig1;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ItemViewModelFactory implements ViewModelProvider.Factory {
    private final ItemDao itemDao;

    public ItemViewModelFactory(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ItemViewModel.class)) {
            return (T) new ItemViewModel(itemDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}


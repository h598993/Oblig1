package com.example.oblig1;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ItemViewModelFactory implements ViewModelProvider.Factory {
    private final ItemDao itemDao;

    // Denne klassen er nødvenig for å kunnne injisere avhengigheter inn i viewmodel klassen ItemViewModel
    // Brukergrensesnittet ItemDao er en avhengighet til ItemViewModel
    public ItemViewModelFactory(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    //Metode som sjekker om det er riktig klasse(ItemViewModel) og returnerer en ny ItemViewModel instans med ItemDoa lagt til
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ItemViewModel.class)) {
            return (T) new ItemViewModel(itemDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}


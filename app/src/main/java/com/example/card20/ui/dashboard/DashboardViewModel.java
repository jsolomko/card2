package com.example.card20.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.card20.data.Card;
import com.example.card20.data.CardRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    CardRepository cardRepository;
    LiveData<List<Card>> listRepo;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        cardRepository = new CardRepository(application);
        listRepo = cardRepository.getListLiveData();
    }


    public LiveData<List<Card>> getListRepo() {
        return listRepo;
    }

    public void addCard(Card card) {
        cardRepository.addCard(card);
    }
}
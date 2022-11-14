package com.example.card20.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.card20.data.Card;
import com.example.card20.data.CardRepository;

import java.util.List;

public class ListCardViewModel extends AndroidViewModel {

    CardRepository cardRepository;
    LiveData<List<Card>> listRepo;

    public ListCardViewModel(@NonNull Application application) {
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
    public void deleteCard(Card card){
        cardRepository.deleteCard(card);
    }
}
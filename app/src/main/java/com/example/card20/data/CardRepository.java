package com.example.card20.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.card20.db.CardDB;
import com.example.card20.db.CardDao;

import java.util.List;

public class CardRepository {
    CardDao cardDao;
    CardDB cardDB;
    LiveData<List<Card>> listLiveData;

    public CardRepository(Application application) {
        cardDB = CardDB.getINSTANCE(application);
        cardDao = cardDB.cardDao();
        listLiveData = cardDao.getAllCards();
    }

    public LiveData<List<Card>> getListLiveData() {
        return listLiveData;
    }

    public void addCard(Card card) {
        CardDB.executor.execute(new Runnable() {
            @Override
            public void run() {
                cardDao.addNewCard(card);
            }
        });
    }
}

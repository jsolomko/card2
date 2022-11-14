package com.example.card20.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.card20.data.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Query("SELECT * From card_table")
    LiveData<List<Card>> getAllCards();

    @Insert
    void addNewCard(Card card);

    @Delete
    void deleteCard(Card card);
}

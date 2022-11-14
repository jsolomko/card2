package com.example.card20.data;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_table")
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cardTitle;
    private int cardNumber;
    private String card_front;


    public Card(String cardTitle, String card_front) {
        this.cardTitle = cardTitle;
        this.card_front = card_front;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCard_front(String card_front) {
        this.card_front = card_front;
    }

    public int getId() {
        return id;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getCard_front() {
        return card_front;
    }
}

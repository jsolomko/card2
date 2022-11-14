package com.example.card20.data;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_table")
public class Card implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cardTitle;
    private int cardNumber;
    private String card_front;


    public Card(String cardTitle, String card_front) {
        this.cardTitle = cardTitle;
        this.card_front = card_front;
    }

    protected Card(Parcel in) {
        id = in.readInt();
        cardTitle = in.readString();
        cardNumber = in.readInt();
        card_front = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(cardTitle);
        parcel.writeInt(cardNumber);
        parcel.writeString(card_front);
    }
}

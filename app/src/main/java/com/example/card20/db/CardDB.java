package com.example.card20.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.card20.data.Card;
import com.example.card20.utils.ImageBitmapString;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Card.class}, version = 2, exportSchema = false)
@TypeConverters({ImageBitmapString.class})
public abstract class CardDB extends RoomDatabase {
    public abstract CardDao cardDao();

    private static volatile CardDB INSTANCE;
    private static final String DB_NAME = "card_db";
    private static final int THREADS = 4;
    public final static ExecutorService executor = Executors.newFixedThreadPool(THREADS);

    public static CardDB getINSTANCE(Application application) {
        if (INSTANCE == null) {
            synchronized (CardDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(application.getApplicationContext(), CardDB.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

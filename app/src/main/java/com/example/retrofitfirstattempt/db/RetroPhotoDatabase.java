package com.example.retrofitfirstattempt.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.retrofitfirstattempt.model.RetroPhoto;

@Database(entities = {RetroPhoto.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class RetroPhotoDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile RetroPhotoDatabase INSTANCE;

    // --- DAO ---
    public abstract RetroPhotoDao retroPhotoDao();

    public static RetroPhotoDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    RetroPhotoDatabase.class, "MyDatabase.db")
                    .build();
        }
        return INSTANCE;
    }

}

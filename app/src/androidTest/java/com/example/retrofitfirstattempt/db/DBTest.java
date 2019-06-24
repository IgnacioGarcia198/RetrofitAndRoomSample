package com.example.retrofitfirstattempt.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.example.retrofitfirstattempt.db.RetroPhotoDatabase;
import com.example.retrofitfirstattempt.model.RetroPhoto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public abstract class DBTest {
    protected RetroPhotoDatabase db;

    @Before
    public void initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                RetroPhotoDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }


}

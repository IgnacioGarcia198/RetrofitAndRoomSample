package com.example.retrofitfirstattempt.db;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;

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

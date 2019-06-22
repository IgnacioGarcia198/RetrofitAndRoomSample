package com.example.retrofitfirstattempt.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.retrofitfirstattempt.model.RetroPhoto;


import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RetroPhotoDao {

    @Insert(onConflict = REPLACE)
    void save(RetroPhoto photo);

    @Insert(onConflict = REPLACE)
    void saveVarious(RetroPhoto... retroPhotos);

    @Query("SELECT * FROM photoTable")
    LiveData<List<RetroPhoto>> getAllPhotosFromRoom();

    @Query("SELECT * FROM photoTable WHERE title LIKE :title")
    LiveData<List<RetroPhoto>> findPhotosByNameInRoom(String title);

    @Query("SELECT * FROM photoTable WHERE albumId = :albumId AND title LIKE :title")
    LiveData<List<RetroPhoto>> findPhotosByNameAndAlbumInRoom(String title, int albumId);

    @Query("SELECT * FROM photoTable WHERE albumId = :albumId")
    LiveData<List<RetroPhoto>> findPhotosByAlbumIdInRoom(int albumId);

    @Query("SELECT * FROM photoTable LIMIT 1")
    RetroPhoto getFirstPhoto();

    @Query("SELECT lastRefresh FROM photoTable LIMIT 1")
    Date getFirstPhotoLastRefresh();

    @Query("SELECT * FROM photoTable WHERE albumId = :albumId AND lastRefresh > :lastRefreshMax LIMIT 1")
    RetroPhoto hasFreshPhotoInAlbum(int albumId, Date lastRefreshMax);

}

package com.example.retrofitfirstattempt.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.retrofitfirstattempt.data.RetroPhotoRepository;
import com.example.retrofitfirstattempt.model.RetroPhoto;

import java.util.List;

public class RetroPhotoViewModel extends ViewModel {
    private LiveData<List<RetroPhoto>> retroPhotoList;
    private RetroPhotoRepository repository;

    public RetroPhotoViewModel(RetroPhotoRepository repository) {
        this.repository = repository;
    }

    public void init(int albumId) {
        retroPhotoList = repository.getPhotosByAlbumId(albumId);
    }

    public LiveData<List<RetroPhoto>> getRetroPhotoList() {
        return retroPhotoList;
    }

    public LiveData<List<RetroPhoto>> findPhotosByTitleInAlbum(String photoTitle, int albumId) {
        return repository.findPhotosByNameInAlbum(photoTitle,albumId);
    }
}

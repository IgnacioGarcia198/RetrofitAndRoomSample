package com.example.retrofitfirstattempt.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.retrofitfirstattempt.data.RetroPhotoRepository;

import java.lang.reflect.InvocationTargetException;

/**
 * Factory for ViewModels
 *
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private RetroPhotoRepository repository;
    public ViewModelFactory(RetroPhotoRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (RetroPhotoViewModel.class.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(RetroPhotoRepository.class).newInstance(repository);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}

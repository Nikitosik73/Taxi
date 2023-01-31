package com.mirea.taxiapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirea.taxiapp.data.CarNote;
import com.mirea.taxiapp.data.CarNoteDatabase;

import java.util.List;

public class TaxiNoteViewModel extends AndroidViewModel {

    private CarNoteDatabase carNoteDatabase;

    public TaxiNoteViewModel(@NonNull Application application) {
        super(application);
        carNoteDatabase = CarNoteDatabase.getInstance(application);
    }

    public LiveData<List<CarNote>> getCarNotes(){
        return carNoteDatabase.carNoteDao().getCarNotes();
    }

    public void remove(CarNote carNote){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                carNoteDatabase.carNoteDao().remove(carNote.getId());
            }
        });
        thread.start();
    }
}

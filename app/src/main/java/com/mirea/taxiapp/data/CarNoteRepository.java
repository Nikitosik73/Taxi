package com.mirea.taxiapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CarNoteRepository {

    private final MutableLiveData<Integer> insertResult = new MutableLiveData<>();

    private final LiveData<List<CarNote>> allCarsNote;

    private final CarNoteDao carNoteDao;

    public CarNoteRepository(Application application){

        CarNoteDatabase db = CarNoteDatabase.getInstance(application);
        carNoteDao = db.carNoteDao();
        allCarsNote = carNoteDao.getCarNotes();
    }

    public MutableLiveData<Integer> getInsertResult() {
        return insertResult;
    }
}

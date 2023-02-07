package com.mirea.taxiapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirea.taxiapp.data.CarNote;
import com.mirea.taxiapp.data.CarNoteDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaxiNoteViewModel extends AndroidViewModel {

    private CarNoteDatabase carNoteDatabase;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TaxiNoteViewModel(@NonNull Application application) {
        super(application);
        carNoteDatabase = CarNoteDatabase.getInstance(application);
    }

    public LiveData<List<CarNote>> getCarNotes(){
        return carNoteDatabase.carNoteDao().getCarNotes();
    }

    public void remove(CarNote carNote){
        Disposable disposable = carNoteDatabase.carNoteDao().remove(carNote.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("TaxiNoteViewModel", "removed: " + carNote.getId());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("TaxiNoteViewModel", "Error removed");
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

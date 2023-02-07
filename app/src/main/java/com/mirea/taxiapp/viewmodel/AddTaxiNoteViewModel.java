package com.mirea.taxiapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mirea.taxiapp.data.CarNote;
import com.mirea.taxiapp.data.CarNoteDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddTaxiNoteViewModel extends AndroidViewModel {

    private CarNoteDatabase carNoteDatabase;

    private MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AddTaxiNoteViewModel(@NonNull Application application) {
        super(application);
        carNoteDatabase = CarNoteDatabase.getInstance(application);
    }

    public MutableLiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }

    public void add(CarNote carNote){

        Disposable disposable = carNoteDatabase.carNoteDao().add(carNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        shouldCloseScreen.postValue(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("AddTaxiNoteViewModel", "not add new notes");
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

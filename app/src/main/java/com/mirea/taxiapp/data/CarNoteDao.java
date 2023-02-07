package com.mirea.taxiapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CarNoteDao {

    @Query("select * from carNote")
    LiveData<List<CarNote>> getCarNotes();

    @Insert
    Completable add(CarNote carNote);

    @Query("delete from carNote where id = :id")
    Completable remove(int id);
}

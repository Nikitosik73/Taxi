package com.mirea.taxiapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarNoteDao {

    @Query("select * from carNote")
    LiveData<List<CarNote>> getCarNotes();

    @Insert
    void add(CarNote carNote);

    @Query("delete from carNote where id = :id")
    void remove(int id);
}

package com.mirea.taxiapp.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CarNote.class}, version = 1, exportSchema = false)
public abstract class CarNoteDatabase extends RoomDatabase {

    private static CarNoteDatabase instance = null;
    private static final String NAME_DB = "carNote.db";

    public static CarNoteDatabase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(
                    application,
                    CarNoteDatabase.class,
                    NAME_DB
            ).build();
        }
        return instance;
    }

    public abstract CarNoteDao carNoteDao();
}

package com.asg.android.poc.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.asg.android.poc.room.db.dao.PersonDao;
import com.asg.android.poc.room.db.entity.Person;


/**
 * Created by AGularia on 03/01/18.
 */

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "database-name";
    private static AppDatabase sInstance;

    public abstract PersonDao personDao();


    // You should follow the singleton design pattern when instantiating an AppDatabase object,
    // as each RoomDatabase instance is fairly expensive, and you rarely need access to multiple instances.
    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(context,
                        AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            }
        }
        return sInstance;
    }


}

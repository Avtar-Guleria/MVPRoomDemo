package com.asg.android.poc.room.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.asg.android.poc.room.db.AppDatabase;
import com.asg.android.poc.room.db.dao.PersonDao;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by avtar guleria on 12/20/17.
 */

public class AppController extends Application {
    public static AppController INSTANCE;
    private static final String PREFERENCES = "Database.preferences";
    private static final String KEY_FORCE_UPDATE = "Database_force_update";

    private static AppDatabase appDatabase;
    private PersonDao personDao;

    public static AppController get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        // create appDatabase
        INSTANCE = this;

    }

    public static AppDatabase getAppDatabase() {
        if (appDatabase == null) {
            appDatabase = AppDatabase.getInstance(INSTANCE);
        }
        return appDatabase;
    }


    public boolean isForceUpdate() {
        return getAppSharedPreferences().getBoolean(KEY_FORCE_UPDATE, true);
    }

    public void setForceUpdate(boolean force) {
        SharedPreferences.Editor edit = getAppSharedPreferences().edit();
        edit.putBoolean(KEY_FORCE_UPDATE, force);
        edit.apply();
    }

    private SharedPreferences getAppSharedPreferences() {
        return getSharedPreferences(PREFERENCES, MODE_PRIVATE);
    }
}

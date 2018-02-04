package com.asg.android.poc.room.presentation.home;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.asg.android.poc.room.application.AppController;
import com.asg.android.poc.room.db.entity.Person;
import com.asg.android.poc.room.presentation.base.BasePresenter;

import java.util.List;


/**
 * Created by Avtar Gularia on 09/Jan/2018.
 *
 * This MVPView interface is specific to Home Fragment. It defines all the method that
 * need to be handled by HomeFragment.
 */

public class HomePresenter<T extends HomeMvpView> extends BasePresenter<T> implements HomeMvpPresenter<T> {

    private static final String TAG = HomePresenter.class.getSimpleName();
    private HomeMvpView homeView;


    public HomePresenter(@NonNull HomeMvpView homeMvpView) {
        homeView = homeMvpView;
    }

    @Override
    public void onSaveButtonClicked(String firstName, String lastName) {
        if (TextUtils.isEmpty(firstName)) {
            getMvpView().onError("Please provide a firstName.");
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            getMvpView().onError("Please provide a lastName.");
            return;
        }

        // save User in DB.
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        AppController.getAppDatabase().personDao().insertAll(person);
        List<Person> persons = AppController.getAppDatabase().personDao().getAllPersons();
        Log.d("Persons=",persons.toString());
        homeView.showSnackBar("Data saved with "+persons.size()+" records");

        // reset UI
        homeView.resetForm();
    }


}

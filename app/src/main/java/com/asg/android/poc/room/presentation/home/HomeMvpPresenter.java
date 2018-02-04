package com.asg.android.poc.room.presentation.home;


import com.asg.android.poc.room.presentation.base.MvpPresenter;

/**
 * Created by Avtar Gularia on 09/Jan/2018.
 *
 * This MVPPresenter interface is specific to HomePresenter. It defines all the method that
 * need to be handled by HomePresenter.
 *
 * Basically job of any presenter to handle all the BL and pass results to View/Fragment layer.
 */
public interface HomeMvpPresenter<T extends HomeMvpView> extends MvpPresenter<T> {

    void onSaveButtonClicked(String firstName, String lastName);


    void onAttach(T mvpView);
}

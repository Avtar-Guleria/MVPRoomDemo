package com.asg.android.poc.room.presentation.home;


import com.asg.android.poc.room.presentation.base.MvpView;

/**
 * Created by Avtar Gularia on 09/Jan/2018.
 *
 * This MVPView interface is specific to Home Fragment. It defines all the method that
 * need to be handled by HomeFragment.
 */


public interface HomeMvpView extends MvpView {
    void openMainActivity();
    void resetForm();
}

package com.asg.android.poc.room.presentation.base;


public interface MvpPresenter<T extends MvpView> {
    void onAttach(T mvpView);

    void onDetach();
}

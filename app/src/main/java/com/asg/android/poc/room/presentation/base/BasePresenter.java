package com.asg.android.poc.room.presentation.base;


public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T mvpView;

    @Override
    public void onAttach(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public T getMvpView() {
        return mvpView;
    }
}



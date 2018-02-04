package com.asg.android.poc.room.presentation.home;

import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asg.android.poc.room.R;
import com.asg.android.poc.room.presentation.base.BaseFragment;
import com.asg.android.poc.room.presentation.base.MvpPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by AGularia on 08/01/18.
 */

public class HomeFragment extends BaseFragment implements HomeMvpView {

    // UI references.
    @BindView(R.id.firstName)
    TextInputEditText firstNameEditText;

    @BindView(R.id.lastName)
    TextInputEditText lastNameEditText;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected View bindView(LayoutInflater inflater, ViewGroup container) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    protected MvpPresenter bindPresenter() {
        return new HomePresenter(this) ;
    }

    @OnClick(R.id.saveButton)
    void saveButtonClicked() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        getPresenter().onSaveButtonClicked(firstName, lastName);

    }

    @OnClick(R.id.forceCrash)
    void forceCrashClicked(){
        throw new RuntimeException("Test Crash");
    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public void resetForm() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
    }

    private HomeMvpPresenter getPresenter() {
        return ((HomeMvpPresenter)presenter);
    }
}

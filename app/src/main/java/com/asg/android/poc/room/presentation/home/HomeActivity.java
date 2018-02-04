package com.asg.android.poc.room.presentation.home;

import android.os.Bundle;

import com.asg.android.poc.room.R;
import com.asg.android.poc.room.Util.ActivityUtils;
import com.asg.android.poc.room.presentation.base.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    homeFragment, R.id.contentFrame);
        }
    }

}


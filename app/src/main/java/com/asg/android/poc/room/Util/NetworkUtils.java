package com.asg.android.poc.room.Util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.asg.android.poc.room.Util.permission.PermissionListener;
import com.asg.android.poc.room.Util.permission.PermissionManager;
import com.tbruyelle.rxpermissions2.Permission;

/**
 * Created by Avtar Guleria on 12/17/17.
 */

public class
NetworkUtils {
    //TODO: Use correct approach to use permission manager.
    static boolean isNetworkPermissionGranted;

    private NetworkUtils() {
    }

    /**
     * This method is used to check whether network is connected or not.
     *
     * @param context
     * @return boolean
     */
    public static boolean isNetworkConnected(final Context context) {

        //TODO: Use correct approach to use permission manager.
        PermissionManager.getInstance().requestPermission(context,
                new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                new PermissionListener() {
                    @Override
                    public void onPermissionGranted(Permission permission) {
                        isNetworkPermissionGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(Permission permission) {
                        Log.d("NetworkUtils", "Internet access permission denied");
                    }

                    @Override
                    public void onPermissionPermanentlyDenied(Permission permission) {
                        Log.d("NetworkUtils", "Internet access permission denied");


                    }
                });


        if (isNetworkPermissionGranted) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }

        return false;

    }
}

package com.asg.android.poc.room.Util.permission;

import com.tbruyelle.rxpermissions2.Permission;

/**
 * Created by AGularia on 23/01/18.
 */

public interface PermissionListener {
    void onPermissionGranted(Permission permission);

    void onPermissionDenied(Permission permission);

    void onPermissionPermanentlyDenied(Permission permission);
}

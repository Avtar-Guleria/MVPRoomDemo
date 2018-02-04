package com.asg.android.poc.room.Util.permission;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.asg.android.poc.room.BuildConfig;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


/**
 * Created by AGularia on 23/01/18.
 */


public class PermissionManager {
    private static PermissionManager instance;

    private PermissionManager() {
    }

    public static PermissionManager getInstance() {
        if (instance == null) {
            instance = new PermissionManager();
        }
        return instance;
    }


    public void requestPermission(Context context, String[] permissions,
                                           final PermissionListener listener) {

        if (BuildConfig.DEBUG && !isPermissionsDefinedInManifest(context, permissions)) {
            //no need to move this to strings.xml as this will come only in logs.
            throw new UndefinedPermissionException("All permissions must be defined in manifest.xml");
        }

        RxPermissions.getInstance(context)
                .requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                        if (permission.granted) {
                            if (listener != null)
                                listener.onPermissionGranted(permission);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            if (listener != null)
                                listener.onPermissionDenied(permission);
                        } else {
                            //open settings again
                            if (listener != null)
                                listener.onPermissionPermanentlyDenied(permission);
                        }
                    }
                });
    }


    private boolean isPermissionsDefinedInManifest(Context context, String[] neededPermissions) {
        if (neededPermissions != null && neededPermissions.length > 0) {
            for (String neededPermission : neededPermissions) {
                boolean isPermissionDefined = checkPermission(context, neededPermission);
                if (!isPermissionDefined) return false;
            }
        }

        return true;

    }

    private boolean checkPermission(Context context, String neededPermission) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_PERMISSIONS);

            if (!TextUtils.isEmpty(neededPermission) &&
                    info.requestedPermissions != null &&
                    info.requestedPermissions.length > 0) {

                for (String definedPermission : info.requestedPermissions) {
                    if (neededPermission.equals(definedPermission)) {
                        return true;
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //if any of the permission is not defined in manifest return false.
        return false;
    }

}

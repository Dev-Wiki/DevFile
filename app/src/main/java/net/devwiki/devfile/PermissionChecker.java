package net.devwiki.devfile;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * 权限检查器
 * Created by zyz on 2016/12/13.
 */

public class PermissionChecker {

    private final Context mContext;

    public PermissionChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    public boolean isGrantPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isGrantPermissions(String... permissions) {
        for (String permission : permissions) {
            if (!isGrantPermission(permission)) {
                return false;
            }
        }
        return true;
    }
}
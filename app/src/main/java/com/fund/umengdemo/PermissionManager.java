package com.fund.umengdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import io.reactivex.functions.Consumer;

/**
 * @Description: 权限管理
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2017-04-21 11:21
 */
public class PermissionManager {

    private static PermissionManager permissionManager;
    private Activity activity;

    private PermissionManager() {

    }

    public static PermissionManager instance() {
        if (permissionManager == null) {
            synchronized (PermissionManager.class) {
                if (permissionManager == null) {
                    permissionManager = new PermissionManager();
                }
            }
        }
        return permissionManager;
    }

    public PermissionManager with(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void request(final OnPermissionCallback permissionCallback, final String... permissions) {
        if (this.activity != null && permissionCallback != null) {
            RxPermissions rxPermissions = new RxPermissions(this.activity);
            rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        permissionCallback.onRequestAllow(permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        permissionCallback.onRequestRefuse(permission.name);
                    } else {
                        showNormalDialog(permission.name);
                        permissionCallback.onRequestNoAsk(permission.name);
                    }
                }
            });
        }
    }


    private AlertDialog alertDialog;

    private void showNormalDialog(String message) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        if (alertDialog != null && alertDialog.isShowing()) {
            return;
        }

        AlertDialog.Builder normalDialog = new AlertDialog.Builder(activity);
        normalDialog.setTitle("获取权限失败");
        normalDialog.setMessage("您禁止了应用权限，请在设置里面打开应用权限？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =  new Intent(Settings.ACTION_SETTINGS);
                        activity.startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        alertDialog = normalDialog.show();
    }
}


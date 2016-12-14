package net.devwiki.devfile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.devwiki.common.util.ToastUtil;
import net.devwiki.file.FileOperate;
import net.devwiki.file.FileUtil;
import net.devwiki.file.OperateResult;
import net.devwiki.log.DevLog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionChecker checker = new PermissionChecker(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "获得权限!!!----");
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            Log.d(TAG, "没有获得权限!!!----");
        }
        if (checker.isGrantPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.d(TAG, "获得权限!!!");
        } else {
            Log.d(TAG, "没有获得权限!!!");
        }
    }

    public void onClick(View view) {
        DevLog.d("开始复制");
        FileOperate.copy(FileUtil.getSDRootPath() + "文件.pdf")
                .to(FileUtil.getSDRootPath() + "文件2.pdf").start()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OperateResult>() {
                    @Override
                    public void onCompleted() {
                        ToastUtil.showShort(MainActivity.this, "复制完成!!!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MainActivity.this, "复制异常!!!");
                        DevLog.e(e.getMessage());
                    }

                    @Override
                    public void onNext(OperateResult operator) {
                        ToastUtil.showShort(MainActivity.this, "复制结束");
                        DevLog.d(operator.toString());
                    }
                });
    }
}

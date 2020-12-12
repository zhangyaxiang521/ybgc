package mykolin.kolin.com.ybgc.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import mykolin.kolin.com.ybgc.Myapp;
import mykolin.kolin.com.ybgc.R;
import mykolin.kolin.com.ybgc.config.X_SystemBarUI;
import mykolin.kolin.com.ybgc.service.IOnNetworkStateChangedListener;
import mykolin.kolin.com.ybgc.service.NetworkStateHelper;

/**
 * Created by Administrator on 2020/5/29.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        X_SystemBarUI.initSystemBar(this, R.color.colorAccent1);
        Myapp.getInstance().addActivity(this);
        NetworkStateHelper.INSTANCE.init (getApplicationContext ());
        //跳转相机动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        IOnNetworkStateChangedListener listener = new IOnNetworkStateChangedListener () {
            @SuppressLint("WrongConstant")
            public void onDisconnected() {
                Toast.makeText(BaseActivity.this,"网络连接中断",0).show();;
            }

            @SuppressLint("WrongConstant")
            public void onChangeToMobile() {
                Toast.makeText(BaseActivity.this,"移动网络连接",0).show();;
            }

            @SuppressLint("WrongConstant")
            public void onChangeToWifi() {
                Toast.makeText(BaseActivity.this,"wife连接",0).show();;
            }
        };
        NetworkStateHelper.INSTANCE.register(listener);
    }
    public int verifyPermissions(Activity activity, java.lang.String permission) {
        int Permission = ActivityCompat.checkSelfPermission(activity,permission);
        if (Permission == PackageManager.PERMISSION_GRANTED) {
            Log.e("info","已经同意权限");
            return 1;
        }else{
            Log.e("info","没有同意权限");
            return 0;
        }
    }
}

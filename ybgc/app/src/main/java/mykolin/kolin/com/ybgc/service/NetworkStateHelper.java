package mykolin.kolin.com.ybgc.service;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import io.reactivex.annotations.NonNull;
import mykolin.kolin.com.ybgc.utils.NetworkReceiver;

/**
 * Created by Administrator on 2020/6/18.
 */

public enum  NetworkStateHelper {
    // 枚举单例
    INSTANCE;

    private static final String TAG = "NetworkStateHelper";

    private Context mContext;
    private NetworkReceiver mNetworkReceiver;

    public void register(@NonNull IOnNetworkStateChangedListener listener){
        checkInit ();
        mNetworkReceiver.addListener (listener);
    }

    public void unRegister(@NonNull IOnNetworkStateChangedListener listener){
        checkInit ();
        mNetworkReceiver.removeListener (listener);
    }

    public void init(Context context){
        mContext = context;
        mNetworkReceiver = new NetworkReceiver (null);
        // 注册广播
        IntentFilter filter = new IntentFilter ();
        filter.addAction (ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver (mNetworkReceiver, filter);
    }

    private void checkInit(){
        if (mContext == null) {
            Log.e (TAG, "please call NetworkStateHelper.INSTANCE.init(Context context) first!");
        }
    }
}

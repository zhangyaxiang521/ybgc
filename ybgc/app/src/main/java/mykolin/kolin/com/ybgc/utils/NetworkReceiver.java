package mykolin.kolin.com.ybgc.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import mykolin.kolin.com.ybgc.service.IOnNetworkStateChangedListener;

/**
 * Created by Administrator on 2020/6/18.
 */

public class NetworkReceiver extends BroadcastReceiver {
    // 网络状态枚举
    private static final int WIFI = 0;
    private static final int MOBILE = 1;
    private static final int NONE = 2;

    // Android 推荐的常量枚举使用方式
    @IntDef(value = {
            WIFI,
            MOBILE,
            NONE
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface NetState{}

    // 注册的监听者集合（观察者集合）
    private List<IOnNetworkStateChangedListener> mListeners;

    public NetworkReceiver(List<IOnNetworkStateChangedListener> listeners) {
        if (listeners == null) {
            mListeners = new ArrayList<>();
        }else{
            mListeners = listeners;
        }
    }

    /**
     * 添加监听器（订阅者）
     * @param listener 网络状态监听器
     */
    public void addListener(@NonNull IOnNetworkStateChangedListener listener){
        if(mListeners.contains (listener)){
            return;
        }
        mListeners.add (listener);
    }

    /**
     * 移除监听器（订阅者）
     * @param listener 网络状态监听器
     */
    public void removeListener(@NonNull IOnNetworkStateChangedListener listener){
        if(!mListeners.contains (listener)){
            return;
        }
        mListeners.remove (listener);
    }

    public void onReceive(Context context, Intent intent) {
        // 遍历通知所有注册者
        if(mListeners != null && mListeners.size () > 0){
            NetworkInfo info = intent.getParcelableExtra (ConnectivityManager.EXTRA_NETWORK_INFO);
            int state = getNetState (info);
            switch (state){
                case WIFI:
                    for (IOnNetworkStateChangedListener listener : mListeners) {
                        listener.onChangeToWifi ();
                    }
                    break;
                case MOBILE:
                    for (IOnNetworkStateChangedListener listener : mListeners) {
                        listener.onChangeToMobile ();
                    }
                    break;
                case NONE:
                    for (IOnNetworkStateChangedListener listener : mListeners) {
                        listener.onDisconnected ();
                    }
                    break;
            }
        }
    }

    // 判断连接状态
    private @NetState int getNetState(NetworkInfo info){
        if(info.getState () == NetworkInfo.State.CONNECTED){
            // wifi 还是 数据
            if(info.getType () == ConnectivityManager.TYPE_WIFI){
                return WIFI;
            } else if(info.getType () == ConnectivityManager.TYPE_MOBILE){
                return MOBILE;
            }
        }
        return NONE;
}
}

package mykolin.kolin.com.ybgc.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Administrator on 2017/2/16.
 */

public class NetworkConnect {
    public static boolean isNetworkAvailable(Context context) {
        {
            final ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            final android.net.NetworkInfo wifi =connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            final android.net.NetworkInfo mobile =connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if(wifi.isAvailable()||mobile.isAvailable())
                return true;
            else
                return false;
        }
    }
}

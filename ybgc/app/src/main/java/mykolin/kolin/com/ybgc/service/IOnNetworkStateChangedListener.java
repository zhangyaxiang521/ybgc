package mykolin.kolin.com.ybgc.service;

/**
 * Created by Administrator on 2020/6/18.
 */

public interface IOnNetworkStateChangedListener{
        /**
         * 网络中断
         */
        void onDisconnected ();

        /**
         * 网络转换为移动网络
         */
        void onChangeToMobile();

        /**
         * 网络转换为wifi
         */
        void onChangeToWifi();
}

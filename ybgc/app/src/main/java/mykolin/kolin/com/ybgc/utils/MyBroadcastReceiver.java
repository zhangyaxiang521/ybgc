package mykolin.kolin.com.ybgc.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2020/6/20.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private MyReceiver mReceiver;
    private String fruit;
    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播消息
        fruit = intent.getStringExtra("fruit");
        //调用接口MyReceiver里面的interFruit方法传入接收的内容
        mReceiver.interFruit(fruit);
        //使用Toast显示广播消息
        Toast.makeText(context,fruit,Toast.LENGTH_SHORT).show();
    }
    //创建一个接口把接收到的广播内容传递回MainActivity
    public interface MyReceiver{
        void interFruit(String fruit);
    }
    public void MyThis(MyReceiver mr){
        mReceiver = mr;
    }
}

package mykolin.kolin.com.ybgc.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/9.
 */

public class SpUtils {
    /**SharedPreferences名称*/
    public static String STORE_NAME_SETTING = "setting";
    /**
     * 初始化
     * @param context
     * @return
     */
    private static SharedPreferences getSettingPreferences(Context context) {
        SharedPreferences setting = null;
        setting = context.getSharedPreferences(STORE_NAME_SETTING,
                Context.MODE_PRIVATE);
        return setting;
    }

    /**
     * 获得String
     * @param ctx
     * @param key
     * @return
     */
    public static String getStringSp(Context ctx, String key) {
        String value = null;
        getSettingPreferences(ctx).edit();
        if (getSettingPreferences(ctx).contains(key)) {
            value = getSettingPreferences(ctx).getString(key, "");
        }
        return value;
    }

    /**
     * put
     * @param ctx
     * @param key
     * @param value
     */
    public static void putStringSp(Context ctx, String key, String value) {
        SharedPreferences.Editor edit = getSettingPreferences(ctx).edit();
        edit.putString(key, "" + value);
        edit.commit();
    }

    public static boolean getBooleanSp(Context ctx, String key) {
        boolean value = false;
        getSettingPreferences(ctx).edit();
        if (getSettingPreferences(ctx).contains(key)) {
            value = getSettingPreferences(ctx).getBoolean(key, false);
        }
        return value;
    }

    public static void putBooleanSp(Context ctx, String key, boolean value) {
        SharedPreferences.Editor edit = getSettingPreferences(ctx).edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static void removeSpKey(Context ctx, String key) {
        SharedPreferences.Editor edit = getSettingPreferences(ctx).edit();
        edit.remove(key);
        edit.commit();
    }




    public static void clearSp(Context ctx) {
        SharedPreferences.Editor edit = getSettingPreferences(ctx).edit();
        edit.clear();
        edit.commit();
    }
}

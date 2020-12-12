package mykolin.kolin.com.ybgc.utils;

import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/4/1.
 */

public class URLEncode {
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
        }

        return "";
    }
}

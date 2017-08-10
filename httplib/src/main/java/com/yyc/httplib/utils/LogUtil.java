package com.yyc.httplib.utils;

import android.util.Log;

/**
 * @author: Page
 * @time: 17-8-8
 * @description:
 */

public class LogUtil {

    public static final String TAG = "http--";
    private final static boolean ISlOG = true;

    public static void e(String str){
        if (ISlOG)
            e(TAG,str);
    }

    public static void e(String tag, String str){
        if (ISlOG)
            Log.e(tag,str);
    }

    public static void i(String str){
        if (ISlOG)
            i(TAG,str);
    }

    public static void i(String tag, String str){
        if (ISlOG)
            Log.i(tag,str);
    }

    public static void d(String str){
        if (ISlOG)
            d(TAG,str);
    }

    public static void d(String tag, String str){
        if (ISlOG)
            Log.d(TAG,str);
    }
}

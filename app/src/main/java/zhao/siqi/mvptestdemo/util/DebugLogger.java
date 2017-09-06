package zhao.siqi.mvptestdemo.util;

import android.util.Log;

import zhao.siqi.mvptestdemo.BuildConfig;


/**
 * 调试日志工具类。
 * Created by bianque on 6/24/16.
 */
public final class DebugLogger {

    private static boolean sDebugMode = BuildConfig.DEBUG;

    public static boolean isDebugMode() {
        return sDebugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        DebugLogger.sDebugMode = debugMode;
    }

    public static void d(String tag, String msg) {
        if (sDebugMode) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebugMode) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Exception e) {
        if (sDebugMode) {
            Log.e(tag, msg, e);
        }
    }

    public static void i(String tag, String msg) {
        if (sDebugMode) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (sDebugMode) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sDebugMode) {
            Log.w(tag, msg);
        }
    }

}
package vn.app.vinhomesmetropolis;

import android.util.Log;

public class AppTracker {
    private static final String TAG_PREFIX = "XMOB ";
    private static final boolean hasDebug = false;

    public static void log(Object objectPos, String log) {
        String tag = TAG_PREFIX;
        if (objectPos != null) {
            tag = tag + objectPos.getClass().getSimpleName();
        }
        log(tag, log);
    }

    public static void log(String tag, String log) {
    }

    public static void error(Object objectPos, String log) {
        String tag = TAG_PREFIX;
        if (objectPos != null) {
            tag = tag + objectPos.getClass().getSimpleName();
        }
        error(tag, log);
    }

    public static void error(String tag, String log) {
    }

    public static void error(Object objectPos, Exception e) {
        error(objectPos, Log.getStackTraceString(e));
    }

    public static void error(String tag, Exception e) {
        error(tag, Log.getStackTraceString(e));
    }
}

package org.iskcon.icc.lavamatra.util;

import android.util.Log;

/**
 * Created by Ankush on 24-05-2017.
 */

public class LogHelper {

    private static final String LOG_PREFIX = "LavaMatra_";
    public static void log(String tag, String level, String message) {
        String TAG = LOG_PREFIX + tag;
        switch(level) {
            case "debug":
                Log.d(TAG, message);
                break;
            case "info":
                Log.i(TAG, message);
                break;
            default:
                Log.v(TAG, message);
                break;
        }

    }
}

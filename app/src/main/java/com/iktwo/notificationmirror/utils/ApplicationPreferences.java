package com.iktwo.notificationmirror.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationPreferences {
    public static final String KEY_PREFS_URL = "url";
    private static final String APP_SHARED_PREFS = ApplicationPreferences.class.getSimpleName();

    public static String getUrl(Context context) {
        return context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE).getString(KEY_PREFS_URL, "");
    }

    public static void setUrl(Context context, String url) {
        SharedPreferences settings = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_PREFS_URL, url);
        editor.apply();
    }
}

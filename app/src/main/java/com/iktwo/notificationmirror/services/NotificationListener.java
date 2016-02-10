package com.iktwo.notificationmirror.services;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.iktwo.notificationmirror.data.SimplifiedNotification;
import com.iktwo.notificationmirror.utils.ApplicationPreferences;
import com.iktwo.notificationmirror.utils.HttpMirror;

public class NotificationListener extends NotificationListenerService {
    public static final String NOTIFICATION_LISTENER_ACTION = "com.iktwo.notificationmirror.NOTIFICATION_LISTENER_SERVICE";

    private static final String TAG = NotificationListener.class.getSimpleName();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Notification n = sbn.getNotification();
        Bundle extras = n.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);

        if (title == null)
            title = "";

        if (text == null)
            text = "";

        // Log.d(TAG, "Received notification from: " + sbn.getPackageName() + "Title: " + title + " Text: " + text);
        String url = ApplicationPreferences.getUrl(this);

        HttpMirror httpMirror = new HttpMirror(url);
        httpMirror.sendNotification(new SimplifiedNotification(title, text, sbn.getPackageName()));
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Notification n = sbn.getNotification();
        Bundle extras = n.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);

        if (title == null)
            title = "";

        if (text == null)
            text = "";

        Log.d(TAG, "onNotificationRemoved: " + new SimplifiedNotification(title, text, sbn.getPackageName()));
    }
}

package com.iktwo.notificationmirror.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.iktwo.notificationmirror.data.SimplifiedNotification;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpMirror extends NotificationMirror {
    private static final String TAG = HttpMirror.class.getSimpleName();
    private static final String ARG_TITLE = "title";
    private static final String ARG_TEXT = "text";
    private static final String ARG_PACKAGE_NAME = "packagename";
    private String mUrl;

    public HttpMirror(String url) {
        mUrl = url;
    }

    @Override
    public void sendNotification(final SimplifiedNotification notification) {
        final OkHttpClient client = new OkHttpClient();

        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... unused) {
                try {
                    RequestBody formBody = new FormBody.Builder()
                            .add(ARG_TITLE, notification.getTitle())
                            .add(ARG_TEXT, notification.getText())
                            .add(ARG_PACKAGE_NAME, notification.getPackageName())
                            .build();

                    Request request = new Request.Builder()
                            .url(mUrl)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    response.body().close();
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e);
                }

                return null;
            }

            protected void onPostExecute(Void unused) {
            }
        }.execute();
    }
}

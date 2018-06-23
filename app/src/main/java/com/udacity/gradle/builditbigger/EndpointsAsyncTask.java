package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;

    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private EndpointsCallbacks callbacks;

    public EndpointsAsyncTask(Context context) {
        this.mContext = context;
    }

    public EndpointsAsyncTask setCallbacks(EndpointsCallbacks callbacks) {
        this.callbacks = callbacks;
        return this;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/") //"http://192.168.43.206:8080/_ah/api/")//
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            String result = myApiService.getJoke().execute().toString();

            JSONObject json = new JSONObject(result);
            return json.optString("data", null);
        } catch (IOException e) {
            e.getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            if (callbacks != null) {
                callbacks.onEndpointTaskResult(result);
            }
        } else {
            if (callbacks != null) {
                callbacks.onEndpointTaskFailed();
            }
        }
    }

    interface EndpointsCallbacks {
        void onEndpointTaskResult(String result);
        void onEndpointTaskFailed();
    }
}
package com.uwanttolearn.easysocial.webrequests;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hafiz Waleed Hussain on 12/8/2014.
 * This is the Post WebRequest class which is extend from WebRequest.
 */
public class PostWebRequest extends WebRequest{

    private Callback mCallback = null;
    private String mUrl = null;
    private String mUrlParams;

    /**
     * One argument constructor
     * @param callback Take Callback as parameter
     */
    public PostWebRequest(Callback callback) {mCallback = callback;}


    /**
     * This method start our Network communication.
     * @param url Take Url as first argument.
     * @param urlParams Parameters which we send in Post request take as second argument.
     */
    public void executeRequest(String url, String urlParams){
        mUrl = url;
        mUrlParams = urlParams;
        new LocalAsyncTask().execute();
    }

    /**
     * This class is used to do whole network processing in background.
     */
    private class LocalAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(mUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(mUrlParams);
                dataOutputStream.flush();
                dataOutputStream.close();
                return inputStreamParse(connection.getInputStream());

            } catch (MalformedURLException e) {
            e.printStackTrace();
                   return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
       }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mCallback.requestComplete(result);
        }
    }
}

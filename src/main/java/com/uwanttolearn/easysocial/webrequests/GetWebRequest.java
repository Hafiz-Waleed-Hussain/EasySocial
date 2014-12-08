package com.uwanttolearn.easysocial.webrequests;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hafiz Waleed Hussain on 12/8/2014.
 * This is the Get WebRequest class which is extend from WebRequest.
 */
public class GetWebRequest extends WebRequest{


    private Callback _Callback = null;
    private String _Url = null;

    /**
     * One argument constructor
     * @param callback Take Callback as parameter
     */
    public GetWebRequest(Callback callback) {
        _Callback = callback;
    }

    /**
     * This method start our Network communication.
     * @param url Take Url as parameter.
     */
    public void executeRequest(String url) {
        _Url = url;
        new LocalAsyncTask().execute();
    }


    /**
     * This class is used to do whole network processing in background.
     */
    private class LocalAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL(_Url);
                URLConnection connection = url.openConnection();
                return inputStreamParse(connection.getInputStream());

            } catch (MalformedURLException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            _Callback.requestComplete(result);
        }
    }

}


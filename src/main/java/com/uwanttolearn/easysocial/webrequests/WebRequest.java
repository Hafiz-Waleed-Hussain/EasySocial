package com.uwanttolearn.easysocial.webrequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hafiz Waleed Hussain on 12/8/2014.
 * This is the abstract class.
 */
public abstract class WebRequest {

    public interface Callback {
        void requestComplete(String data);
    }

    /**
     * This method is used to parse inputStream into String
     * @param inputStream Take as an argument.
     * @return String data or null.
     */
    protected  String inputStreamParse(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = bufferedReader.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

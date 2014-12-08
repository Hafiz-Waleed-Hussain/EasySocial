package com.uwanttolearn.easysocial;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Hafiz Waleed Hussain on 12/4/2014.
 * This Activity is used to Authenticate user login. This activity always start as a startActivityForResult.
 * If Authentication failed due to any reason we can get the reason in onActivityResult method. The Error code which we get
 * is a part of a WebViewClient for  more information about error code you will refer to {@link android.webkit.WebViewClient} error constants.
 *
 */
public class EasySocialAuthActivity extends Activity implements GetAccessToken.Callback{

    /** WebView is used to get the code which we use to get AccessToken from a social network.*/
    private WebView _WebView;
    /** RedirectUrl is used to authenticate about our app with social network. This url is same which we give when we are creating an app on social network.*/
    private String _RedirectUrl;
    /** Url is a Url of a social network which we are using for authenticate.*/
    private String _Url;
    /** AccessTokenUrl is used to get AccessToken String for the Social Network.*/
    private String _AccessToken;

    /** Constants which we use for Intent data sharing */
    public static final String REDIRECT_URL = "redirect_url";
    public static final String URL = "url";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ERROR_CODE = "error_code";
    private EasyWebViewClient easyWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataFromIntent();
        setContentView(R.layout.activity_easy_social_auth);
        initWebView();
    }
    public String getRedirectUrl() {
        return _RedirectUrl;
    }
    public String getAccessToken() {
        return _AccessToken;
    }

    // Private methods which used for different purposes like Initializations etc.
    private void initDataFromIntent(){
        _RedirectUrl = getIntent().getStringExtra(REDIRECT_URL);
        _Url = getIntent().getStringExtra(URL);
        _AccessToken = getIntent().getStringExtra(ACCESS_TOKEN);

        if(_RedirectUrl == null)
            throw new NullPointerException("Redirect Url is Null");
        if(_Url == null)
            throw new NullPointerException("Authentication Url is Null");
        if(_AccessToken == null)
            throw new NullPointerException("AccessToken Url is Null");
    }

    private void initWebView() {
        _WebView = (WebView) findViewById(R.id.EasySocialAuthActivity_web_view);
        easyWebViewClient = new EasyWebViewClient(this);
        _WebView.setWebViewClient(easyWebViewClient);

        WebSettings webSettings = _WebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        _WebView.loadUrl(_Url);
    }


    @Override
    public void onComplete(String line) {
        easyWebViewClient.get_Dialog().dismiss();
        _WebView.stopLoading();
        _WebView.destroy();
        try {
            Intent data = new Intent();
            data.putExtra("data", line);
            setResult(RESULT_OK,data);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }
}

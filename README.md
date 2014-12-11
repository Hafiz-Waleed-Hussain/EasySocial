EasySocial
==========

Oauth2.0 general implementation for Android.
In our mostly Android apps, we want to integrate Facebook, Google etc for sign-in or share purpose. For that we add Facebook SDK , Google play service for Google+ and we only want one functionality. Its mean we are adding a long list of methods in our app but we only want one method. Instead many developers face multidex issue due to increase in method limit. Also one more issue, first I need to learn how integrate Facebook then Google then Twitter and a long list. So I feel, I create a one generic module which we use for every Social network. So for that purpose I created a Oauth2.0 Module. So any Social Network who uses Oauth2.0 for authentication, you can use this module for that Social Network.


How to Use.

Simple add this module in your project and call the below code for authentication.

        Intent intent = new Intent(this, EasySocialAuthActivity.class);
        String loginUrl = "https://www.facebook.com/dialog/oauth?" +
                "client_id=xxxxxxxxxxxxxx&" +
                "redirect_uri=https://www.uwanttolearn.com/&" +
                "scope=email,publish_actions";
        intent.putExtra(EasySocialAuthActivity.URL, loginUrl);
        intent.putExtra(EasySocialAuthActivity.REDIRECT_URL,      "http://www.uwanttolearn.com/");
        String accessTokenUrl = "https://graph.facebook.com/oauth/access_token?" +
                "client_id=xxxxxxxxxxxxxx&" +
                "redirect_uri=https://www.uwanttolearn.com/&" +
                "client_secret=xxxxxxxxxxxx&" +
                "code=";
        intent.putExtra(EasySocialAuthActivity.ACCESS_TOKEN, accessTokenUrl);
        startActivityForResult(intent, REQUEST_CODE);

And implement the onActivityResult method to get the response of authentication.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE){
                    String accessToken = data.getStringExtra("data");
            }
        }else if(resultCode == RESULT_CANCELED){
            if(requestCode == REQUEST_CODE) {
                Toast.makeText(this, data.getIntExtra(EasySocialAuthActivity.ERROR_CODE, 0) + "", Toast.LENGTH_LONG).show();
                //These error codes are present in WebViewClient.              
                //http://developer.android.com/reference/android/webkit/WebViewClient.html
            }
        }

    }
    
For more information you can read the tutorial where I explain this module completly. How to use this moduel in Android apps.
http://www.uwanttolearn.com/android/android-oauth-2-0/

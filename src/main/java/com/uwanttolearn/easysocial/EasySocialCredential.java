package com.uwanttolearn.easysocial;

/**
 * Created by Hafiz Waleed Hussain on 12/4/2014.
 */
public class EasySocialCredential {

    /** Application Id we get from a social network. When we create an app on the network, it give us Application Id. In some social networks Application Id also called a Consumer Key. */
    private final String _AppId;
    /** ApplicationSecret Id we get from a social network. When we create an app on the network, it give us ApplicationSecret Id. In some social networks ApplicationSecret Id also called a ConsumerSecret Key. */
    private final String _AppSecretId;
    /** RedirectUrl is that url which we set on the social network when creating an application.*/
    private final String _RedirectUrl;
    /** Some Social networks required special permission for special data. So we can get that from the social network api doc.*/
    private final String[] _Permissions;

    /** No argument constructor defined as private so use did not used*/
    private EasySocialCredential(){
        this(new Builder(null,null,null));
    }

    /** One argument constructor*/
    private EasySocialCredential(Builder builder){
        this._AppId = builder._AppId;
        this._AppSecretId = builder._AppSecretId;
        this._RedirectUrl = builder._RedirectUrl;
        this._Permissions = builder._Permissions;
    }

    /** Getter of redirect url */
    public String getRedirectUrl() {
        return _RedirectUrl;
    }

    /** Getter of Social network AppId */
    public String getAppId() {
        return _AppId;
    }

    /** Getter of Social network AppSecretId */
    public String getAppSecretId() {
        return _AppSecretId;
    }

    /** Getter of Social network Permission as String array*/
    public String[] getPermissions() {
        return _Permissions;
    }

    /** Builder pattern is used to create a Credential object*/
    public static class Builder{

        private final String _AppId;
        private final String _AppSecretId;
        private final String _RedirectUrl;
        private String[] _Permissions;

        public Builder(String _AppId, String _AppSecretId, String _RedirectUrl) {
            this._AppId = _AppId;
            this._AppSecretId = _AppSecretId;
            this._RedirectUrl = _RedirectUrl;
        }

        public Builder setPermissions(String[] permissions){
            this._Permissions = permissions;
            return this;
        }

        public EasySocialCredential build(){
            return new EasySocialCredential(this);
        }
    }
}

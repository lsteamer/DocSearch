package lsteamer.elmexicano.com.docsearch.utils;

import android.support.annotation.NonNull;

import java.io.Serializable;


public class UrlContents implements Serializable {

    /*
     * UrlContents will contain the different strings
     * that are needed to create the curl request calls
     */

    private String baseUrl;
    private String fullUrlLogin;
    private String fullUrlList;
    private String pathToken;
    private String pathDoctors;

    private String latKey;
    private String latValue;

    private String lngKey;
    private String lngValue;

    private String usernameKey;
    private String usernameValue;

    private String passwordKey;
    private String passwordValue;

    private String grantTypeKey;

    private String searchKey;
    private String searchValue;

    private String bearer;

    public @NonNull String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public @NonNull String getFullUrlLogin() {
        return fullUrlLogin;
    }

    public void setFullUrlLogin(String fullUrlLogin) {
        this.fullUrlLogin = fullUrlLogin;
    }

    public @NonNull String getFullUrlList() {
        return fullUrlList;
    }

    public void setFullUrlList(String fullUrlList) {
        this.fullUrlList = fullUrlList;
    }

    public @NonNull String getPathToken() {
        return pathToken;
    }

    public void setPathToken(String pathToken) {
        this.pathToken = pathToken;
    }

    public @NonNull String getPathDoctors() {
        return pathDoctors;
    }

    public void setPathDoctors(String pathDoctors) {
        this.pathDoctors = pathDoctors;
    }

    public @NonNull String getLatKey() {
        return latKey;
    }

    public void setLatKey(String latKey) {
        this.latKey = latKey;
    }

    public @NonNull String getLatValue() {
        return latValue;
    }

    public void setLatValue(String latValue) {
        this.latValue = latValue;
    }

    public @NonNull String getLngKey() {
        return lngKey;
    }

    public void setLngKey(String lngKey) {
        this.lngKey = lngKey;
    }

    public @NonNull String getLngValue() {
        return lngValue;
    }

    public void setLngValue(String lngValue) {
        this.lngValue = lngValue;
    }

    public @NonNull String getUsernameKey() {
        return usernameKey;
    }

    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }

    public @NonNull String getUsernameValue() {
        return usernameValue;
    }

    public void setUsernameValue(String usernameValue) {
        this.usernameValue = usernameValue;
    }

    public @NonNull String getPasswordKey() {
        return passwordKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public @NonNull String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public @NonNull String getGrantTypeKey() {
        return grantTypeKey;
    }

    public void setGrantTypeKey(String grantTypeKey) {
        this.grantTypeKey = grantTypeKey;
    }

    public @NonNull String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public @NonNull String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public @NonNull String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}

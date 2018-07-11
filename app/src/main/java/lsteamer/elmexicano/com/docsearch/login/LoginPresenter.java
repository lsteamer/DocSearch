package lsteamer.elmexicano.com.docsearch.login;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.ListActivity;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
import lsteamer.elmexicano.com.docsearch.list.model.DoctorData;
import lsteamer.elmexicano.com.docsearch.login.model.LoginData;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;
import lsteamer.elmexicano.com.docsearch.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class LoginPresenter implements LoginContract.LoginPresenterContract {


    private static String TAG = "LoginPresenter";

    private FusedLocationProviderClient locationClient;
    private Activity loginActivity;
    private LoginContract.LoginViewContract mView;
    private UrlContents urlContents;
    private boolean locationObtainedFlag;


    LoginPresenter(Activity mActivity, LoginContract.LoginViewContract viewContract, FusedLocationProviderClient client, UrlContents urlContents) {
        this.loginActivity = mActivity;
        this.mView = viewContract;
        this.locationClient = client;

        this.locationObtainedFlag = false;

        this.urlContents = urlContents;
        setUrlContentsStrings();

        requestLocationPermission();
        //todo should I call setLocation from within the requestLocationPermission? Try out later
        setLocation();


        mView.setPresenter(this);

    }


    @Override
    public void loginToGetToken() {

        //If we have locationPermission requests AND There's something in the Username/Password fields
        if (requestLocationPermission() && validateTextInput()) {

            //Show a loading bar
            mView.toggleLayoutVisibility();

            String fullUrl = Utils.uriParser(urlContents, 1);

            Call<LoginData> call = Utils.getLoginRequestData(urlContents.getBaseUrl(), fullUrl);


            call.enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {


                    mView.toggleLayoutVisibility();

                    if (response.body() != null) {
                        urlContents.setBearer("Bearer " + response.body().getAccessToken());



                        mView.makeToast(loginActivity.getString(R.string.almost_there));


                        //Call the new activity
                        Intent listIntent = Utils.getIntent(loginActivity, ListActivity.class);


                        //Sending the Array
                        Bundle bundle = Utils.getNewBundle();
                        listIntent.putExtras(bundle);

                        //Ending this Activity
                        loginActivity.finish();
                        //Calling the next one
                        loginActivity.startActivity(listIntent);

                    } else
                        mView.makeToast(loginActivity.getString(R.string.login_fail));

                }

                @Override
                public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
                    mView.toggleLayoutVisibility();
                    mView.makeToast(loginActivity.getString(R.string.login_fail));
                }
            });


        }
    }


    private void setLocation() {
        if (ActivityCompat.checkSelfPermission(loginActivity, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationClient.getLastLocation().addOnSuccessListener(loginActivity, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        urlContents.setLatValue(Utils.getNumericStringTrimmedDecimals(location.getLatitude()));
                        urlContents.setLngValue(Utils.getNumericStringTrimmedDecimals(location.getLongitude()));

                        locationObtainedFlag = true;

                    }
                }
            });

    }


    //todo There must be a better way to do this
    private void setUrlContentsStrings() {

        urlContents.setBaseUrl(loginActivity.getString(R.string.base_url));
        urlContents.setFullUrl(loginActivity.getString(R.string.login_url));
        urlContents.setPath(loginActivity.getString(R.string.token));
        urlContents.setGrantTypeKey(loginActivity.getString(R.string.grant_type));
        urlContents.setPasswordKey(loginActivity.getString(R.string.password));
        urlContents.setUsernameKey(loginActivity.getString(R.string.username));
        urlContents.setLngKey(loginActivity.getString(R.string.lng));
        urlContents.setLatKey(loginActivity.getString(R.string.lat));

    }

    //Checks if the username and Password have contents,
    private boolean validateTextInput() {
        String textPassword = mView.getTextInputPassword();
        String textUsername = mView.getTextInputUsername();
        if (!textPassword.isEmpty() && !textUsername.isEmpty()) {
            mView.setUsernameErrorTitle(null);
            mView.setPasswordErrorTitle(null);
            urlContents.setUsernameValue(textUsername);
            urlContents.setPasswordValue(textPassword);
            return true;
        } else if (textPassword.isEmpty() && textUsername.isEmpty()) {
            mView.setPasswordErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setUsernameErrorTitle(loginActivity.getString(R.string.empty_error));
            return false;
        } else if (textPassword.isEmpty()) {
            mView.setPasswordErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setUsernameErrorTitle(null);
            return false;
        } else {
            mView.setUsernameErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setPasswordErrorTitle(null);
            return false;
        }
    }


    public boolean requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(loginActivity, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(loginActivity, new String[]{ACCESS_COARSE_LOCATION}, 1);
            return false;
        } else
            return true;
    }
}

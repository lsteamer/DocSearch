package lsteamer.elmexicano.com.docsearch.login;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import lsteamer.elmexicano.com.docsearch.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class LoginPresenter implements LoginContract.LoginPresenterContract{


    private static String TAG = "LoginPresenter";

    private FusedLocationProviderClient locationClient;
    private Activity loginActivity;
    private LoginContract.LoginViewContract mView;
    private Location userLocation;


    LoginPresenter(Activity mActivity, LoginContract.LoginViewContract viewContract, FusedLocationProviderClient client){
        this.loginActivity = mActivity;
        this.mView = viewContract;
        this.locationClient = client;

        this.userLocation = null;
        requestLocationPermission();
        setLocation();

        mView.setPresenter(this);
    }


    @Override
    public void checkLoginConditions() {
        if(ActivityCompat.checkSelfPermission(loginActivity, ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED&&validateTextInput()){
            setLocation();
            mView.makeToast("longitude"+userLocation.getLongitude()+ " Latitude"+userLocation.getLatitude());
            mView.toggleLayoutVisibility();

        }


    }

    private void setLocation(){
        if(ActivityCompat.checkSelfPermission(loginActivity, ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        locationClient.getLastLocation().addOnSuccessListener(loginActivity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    userLocation = location;
                }
            }
        });

    }


    private boolean validateTextInput(){
        if(!mView.getTextInputPassword().isEmpty()&&!mView.getTextInputUsername().isEmpty()){
            mView.setUsernameErrorTitle(null);
            mView.setPasswordErrorTitle(null);
            return true;
        }
        else if(mView.getTextInputPassword().isEmpty()&&mView.getTextInputUsername().isEmpty()){
            mView.setPasswordErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setUsernameErrorTitle(loginActivity.getString(R.string.empty_error));
            return false;
        }
        else if(mView.getTextInputPassword().isEmpty()){
            mView.setPasswordErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setUsernameErrorTitle(null);
            return false;
        }
        else{
            mView.setUsernameErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setPasswordErrorTitle(null);
            return false;
        }
    }


    public boolean requestLocationPermission(){
        if (ActivityCompat.checkSelfPermission(loginActivity, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(loginActivity, new String[]{ACCESS_COARSE_LOCATION}, 1);
            return false;
        }
        else
            return true;
    }
}

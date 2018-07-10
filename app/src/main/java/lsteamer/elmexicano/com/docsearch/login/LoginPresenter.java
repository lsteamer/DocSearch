package lsteamer.elmexicano.com.docsearch.login;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
import lsteamer.elmexicano.com.docsearch.list.model.DoctorData;
import lsteamer.elmexicano.com.docsearch.login.model.LoginData;
import lsteamer.elmexicano.com.docsearch.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void loginToGetToken() {
        String[] usernameAndPassword = validateTextInput();
        if(requestLocationPermission()&&usernameAndPassword!=null){

            setLocation();

            mView.toggleLayoutVisibility();


            String[] urlValues = {loginActivity.getString(R.string.login_url),
                    loginActivity.getString(R.string.token),
                    loginActivity.getString(R.string.grant_type),
                    loginActivity.getString(R.string.password),
                    loginActivity.getString(R.string.username),
                    usernameAndPassword[0],
                    loginActivity.getString(R.string.password),
                    usernameAndPassword[1]};



            Call<LoginData> call = Utils.getLoginRequestData(loginActivity.getString(R.string.base_url), Utils.uriParser(urlValues));


            call.enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                    LoginData some = response.body();

                    mView.toggleLayoutVisibility();


                    //todo check the warning
                    String bearer = "Bearer " + some.getAccessToken();

                    dummyCall(bearer);


                    //todo With the token, call for the Doctor List and start the new activity

                }

                @Override
                public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
                }
            });


        }


    }

    private void dummyCall(String wat){


        String[] urlValues = {loginActivity.getString(R.string.doctor_url),
                loginActivity.getString(R.string.doctors),
                loginActivity.getString(R.string.lat),
                "52.534709",
                loginActivity.getString(R.string.lng),
                "13.3976972"};

        mView.makeToast(Utils.uriParser(urlValues));

        Call<DoctorData> call = Utils.getDoctorRequestData(loginActivity.getString(R.string.base_url), Utils.uriParser(urlValues), wat);


        call.enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(@NonNull Call<DoctorData> call, @NonNull Response<DoctorData> response) {
                DoctorData some = response.body();

                List<Doctor> someList = some.getListDoctors();
                mView.makeToast(someList.get(0).getName());


                //todo With the token, call for the Doctor List and start the new activity

            }

            @Override
            public void onFailure(@NonNull Call<DoctorData> call, @NonNull Throwable t) {
            }
        });

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

    //Checks if the username and Password have contents,
    private String[] validateTextInput(){
        String textPassword = mView.getTextInputPassword();
        String textUsername = mView.getTextInputUsername();
        if(!textPassword.isEmpty()&&!textUsername.isEmpty()){
            mView.setUsernameErrorTitle(null);
            mView.setPasswordErrorTitle(null);
            return new String[]{textUsername, textPassword};
        }
        else if(textPassword.isEmpty()&&textUsername.isEmpty()){
            mView.setPasswordErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setUsernameErrorTitle(loginActivity.getString(R.string.empty_error));
            return null;
        }
        else if(textPassword.isEmpty()){
            mView.setPasswordErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setUsernameErrorTitle(null);
            return null;
        }
        else{
            mView.setUsernameErrorTitle(loginActivity.getString(R.string.empty_error));
            mView.setPasswordErrorTitle(null);
            return null;
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

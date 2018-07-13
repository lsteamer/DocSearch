package lsteamer.elmexicano.com.docsearch.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DecimalFormat;
import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.RecylerViewAdapter.DoctorAdapter;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
import lsteamer.elmexicano.com.docsearch.list.model.DoctorData;
import lsteamer.elmexicano.com.docsearch.list.model.DoctorRequestData;
import lsteamer.elmexicano.com.docsearch.login.model.LoginRequestData;
import lsteamer.elmexicano.com.docsearch.login.model.LoginData;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.support.v4.util.Preconditions.checkNotNull;

public class Utils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


    public static UrlContents setUrlContentsStrings(Activity activity) {

        UrlContents urlContents = new UrlContents();

        //todo try to improve this
        urlContents.setBaseUrl(activity.getString(R.string.base_url));
        urlContents.setFullUrlLogin(activity.getString(R.string.login_url));
        urlContents.setFullUrlList(activity.getString(R.string.doctor_url));

        urlContents.setPathToken(activity.getString(R.string.token));
        urlContents.setPathDoctors(activity.getString(R.string.doctors));
        urlContents.setGrantTypeKey(activity.getString(R.string.grant_type));
        urlContents.setPasswordKey(activity.getString(R.string.password));
        urlContents.setUsernameKey(activity.getString(R.string.username));
        urlContents.setLngKey(activity.getString(R.string.lng));
        urlContents.setLatKey(activity.getString(R.string.lat));

        return urlContents;
    }

    public static String uriParser(UrlContents urlContents, int decider){
        Uri uri;

        switch (decider){
            case 1:
                //Access Token request
                uri = Uri.parse(urlContents.getFullUrlLogin()).buildUpon()
                        .appendPath(urlContents.getPathToken())
                        .appendQueryParameter(urlContents.getGrantTypeKey(),urlContents.getPasswordKey())
                        .appendQueryParameter(urlContents.getUsernameKey(), urlContents.getUsernameValue())
                        .appendQueryParameter(urlContents.getPasswordKey(), urlContents.getPasswordValue())
                        .build();
                return uri.toString();
            case 2:
                //Search for doctors with a name parameter
                uri = Uri.parse(urlContents.getFullUrlList()).buildUpon()
                        .appendPath(urlContents.getPathDoctors())
                        .appendQueryParameter(urlContents.getSearchKey(),urlContents.getSearchValue())
                        .appendQueryParameter(urlContents.getLatKey(), urlContents.getLatValue())
                        .appendQueryParameter(urlContents.getLngKey(), urlContents.getLngValue())
                        .build();
                return uri.toString();
            case 3:
                //Search for doctors with no Name parameter
                uri = Uri.parse(urlContents.getFullUrlList()).buildUpon()
                        .appendPath(urlContents.getPathDoctors())
                        .appendQueryParameter(urlContents.getLatKey(), urlContents.getLatValue())
                        .appendQueryParameter(urlContents.getLngKey(), urlContents.getLngValue())
                        .build();
                return uri.toString();
            default:
                return "";

        }
    }

    public static String getNumericStringTrimmedDecimals(double value){
        DecimalFormat df = new DecimalFormat("#.######");
        return df.format(value);
    }




    /*
     * The following are methods that are in place
     * to keep the activities and MVP layers
     * within dependency injection to make it
     * easier to be Unit-tested
     */


    public static Call<LoginData> getLoginRequestData(@NonNull String url, @NonNull String fullURL){
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl( url )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginRequestData loginRequestData = retrofit.create(LoginRequestData.class);
        return loginRequestData.getData(fullURL);
    }

    public static Call<DoctorData> getDoctorRequestData(@NonNull String url, @NonNull String fullURL, @NonNull String token){

        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl( url )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoctorRequestData doctorRequestData = retrofit.create(DoctorRequestData.class);
        return doctorRequestData.getData(fullURL, token);
    }

    public static Intent getIntent(Activity activity, Class classToStart){
        return new Intent(activity,classToStart);

    }

    public static Bundle getNewBundle(){
        return new Bundle();
    }


    public static DoctorAdapter getDoctorAdapter(AppCompatActivity appActivity, List<Doctor> doctorList){
        return new DoctorAdapter(appActivity, doctorList);
    }

}

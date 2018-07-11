package lsteamer.elmexicano.com.docsearch.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import java.text.DecimalFormat;

import lsteamer.elmexicano.com.docsearch.list.model.DoctorData;
import lsteamer.elmexicano.com.docsearch.list.model.DoctorRequestData;
import lsteamer.elmexicano.com.docsearch.login.model.LoginRequestData;
import lsteamer.elmexicano.com.docsearch.login.model.LoginData;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public static String uriParser(UrlContents urlContents, int decider){
        Uri uri;

        switch (decider){
            case 1:
                //Access Token request
                uri = Uri.parse(urlContents.getFullUrl()).buildUpon()
                        .appendPath(urlContents.getPath())
                        .appendQueryParameter(urlContents.getGrantTypeKey(),urlContents.getPasswordKey())
                        .appendQueryParameter(urlContents.getUsernameKey(), urlContents.getUsernameValue())
                        .appendQueryParameter(urlContents.getPasswordKey(), urlContents.getPasswordValue())
                        .build();
                return uri.toString();
            case 2:
                //Search for doctors with a name parameter
                uri = Uri.parse(urlContents.getFullUrl()).buildUpon()
                        .appendPath(urlContents.getPath())
                        .appendQueryParameter(urlContents.getSearchKey(),urlContents.getSearchValue())
                        .appendQueryParameter(urlContents.getLatKey(), urlContents.getLatValue())
                        .appendQueryParameter(urlContents.getLngKey(), urlContents.getLngValue())
                        .build();
                return uri.toString();
            case 3:
                //Search for doctors with no Name parameter
                uri = Uri.parse(urlContents.getFullUrl()).buildUpon()
                        .appendPath(urlContents.getPath())
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

}

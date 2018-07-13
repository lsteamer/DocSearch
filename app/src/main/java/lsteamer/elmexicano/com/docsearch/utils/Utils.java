package lsteamer.elmexicano.com.docsearch.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.List;

import lsteamer.elmexicano.com.docsearch.list.recycler.DoctorAdapter;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
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


    /**
     * Method to create the different urls needed in the project.
     * @param urlContents Helper class with the different Strings needed
     * @param decider int that selects which kind of url is needed
     * @param imageToken Can be null, has the path for the image url.
     * @return
     */
    public static String uriParser(@NonNull UrlContents urlContents, @NonNull int decider, String imageToken) {
        Uri uri;

        switch (decider) {
            case 1:
                //Creating the url for the access Token request
                uri = Uri.parse(urlContents.getFullUrlLogin()).buildUpon()
                        .appendPath(urlContents.getPathToken())
                        .appendQueryParameter(urlContents.getGrantTypeKey(), urlContents.getPasswordKey())
                        .appendQueryParameter(urlContents.getUsernameKey(), urlContents.getUsernameValue())
                        .appendQueryParameter(urlContents.getPasswordKey(), urlContents.getPasswordValue())
                        .build();
                return uri.toString();
            case 2:
                //creating the url for a search of doctors with a name parameter
                uri = Uri.parse(urlContents.getFullUrlList()).buildUpon()
                        .appendPath(urlContents.getPathDoctors())
                        .appendQueryParameter(urlContents.getSearchKey(), urlContents.getSearchValue())
                        .appendQueryParameter(urlContents.getLatKey(), urlContents.getLatValue())
                        .appendQueryParameter(urlContents.getLngKey(), urlContents.getLngValue())
                        .build();
                return uri.toString();
            case 3:
                //creating the url for a search of doctors with no Name parameter
                uri = Uri.parse(urlContents.getFullUrlList()).buildUpon()
                        .appendPath(urlContents.getPathDoctors())
                        .appendQueryParameter(urlContents.getLatKey(), urlContents.getLatValue())
                        .appendQueryParameter(urlContents.getLngKey(), urlContents.getLngValue())
                        .build();
                return uri.toString();
            case 4:
                //creting the url for the search of images
                uri = Uri.parse(urlContents.getFullUrlImage()).buildUpon()
                        .appendPath(imageToken)
                        .build();
                return uri.toString();
            default:
                return "";

        }
    }

    public static String getNumericStringTrimmedDecimals(double value) {
        DecimalFormat df = new DecimalFormat("#.######");
        return df.format(value);
    }




    /*
     * The following are methods that are in place
     * to keep the activities and MVP layers
     * within dependency injection to make them
     * easier to be Unit-tested
     */


    public static Call<LoginData> getLoginRequestData(@NonNull String url, @NonNull String fullURL) {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginRequestData loginRequestData = retrofit.create(LoginRequestData.class);
        return loginRequestData.getData(fullURL);
    }

    public static Call<DoctorData> getDoctorRequestData(@NonNull String url, @NonNull String fullURL, @NonNull String token) {

        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoctorRequestData doctorRequestData = retrofit.create(DoctorRequestData.class);
        return doctorRequestData.getData(fullURL, token);
    }

    public static Intent getIntent(Activity activity, Class classToStart) {
        return new Intent(activity, classToStart);

    }

    public static Bundle getNewBundle() {
        return new Bundle();
    }


    public static DoctorAdapter getDoctorAdapter(AppCompatActivity appActivity, List<Doctor> doctorList, UrlContents urlContents) {
        return new DoctorAdapter(appActivity, doctorList, urlContents);
    }

}

package lsteamer.elmexicano.com.docsearch.utils;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.common.util.Strings;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
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

    public static String urlParser(Activity activity, @NonNull String[] values){
        return activity.getString(R.string.login_url)+
                activity.getString(R.string.username)+ "=" +
                values[0] + "&" +
                activity.getString(R.string.password) + "=" +
                values[1];
    }

    public static String uriParser(List<String> values){
        Uri uri;

        if(values.size()==5){
            uri = Uri.parse(values.get(0)).buildUpon()
                    .appendQueryParameter(values.get(1), values.get(2))
                    .appendQueryParameter(values.get(3), values.get(4))
                    .build();

            return uri.toString();
        }
        return "";
    }


    //todo check this one out
    public static String uriParser(String[] values){
        Uri uri;

        if(values.length==8){
            uri = Uri.parse(values[0]).buildUpon()
                    .appendPath(values[1])
                    .appendQueryParameter(values[2],values[3])
                    .appendQueryParameter(values[4], values[5])
                    .appendQueryParameter(values[6], values[7])
                    .build();

            return uri.toString();
        }
        else if(values.length==6){
            uri = Uri.parse(values[0]).buildUpon()
                    .appendPath(values[1])
                    .appendQueryParameter(values[2],values[3])
                    .appendQueryParameter(values[4], values[5])
                    .build();

            return uri.toString();

        }
        return "";
    }

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

}

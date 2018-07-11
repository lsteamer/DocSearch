package lsteamer.elmexicano.com.docsearch.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;
import lsteamer.elmexicano.com.docsearch.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    private FusedLocationProviderClient locationClient;
    private LoginView loginView;
    private LoginPresenter loginPresenter;
    private UrlContents urlContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //ViewLayer
        loginView = (LoginView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(loginView==null){
            loginView = LoginView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), loginView, R.id.contentFrame);
        }

        urlContents = new UrlContents();

        //Location Permissions
        locationClient = LocationServices.getFusedLocationProviderClient(this);

        //Help Utility Class
        urlContents = new UrlContents();

        //PresenterLayer
        loginPresenter = new LoginPresenter(this, loginView, locationClient, urlContents);



    }
}

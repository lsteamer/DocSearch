package lsteamer.elmexicano.com.docsearch.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;
import lsteamer.elmexicano.com.docsearch.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    public FusedLocationProviderClient locationClient;
    public LoginView loginView;
    public LoginPresenter loginPresenter;
    public UrlContents urlContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //ViewLayer
        loginView = (LoginView) getSupportFragmentManager().findFragmentById(R.id.loginContentFrame);
        if (loginView == null) {
            loginView = LoginView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), loginView, R.id.loginContentFrame);
        }

        //Location Permissions
        locationClient = LocationServices.getFusedLocationProviderClient(this);

        //Help Utility Class
        urlContents = new UrlContents(this);


        //PresenterLayer
        loginPresenter = new LoginPresenter(this, loginView, locationClient, urlContents);

    }
}

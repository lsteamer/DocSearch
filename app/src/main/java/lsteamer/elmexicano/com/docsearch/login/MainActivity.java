package lsteamer.elmexicano.com.docsearch.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient locationClient;

    private LoginView loginView;
    private LoginPresenter loginPresenter;

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

        //Location Permissions
        locationClient = LocationServices.getFusedLocationProviderClient(this);

        //PresenterLayer
        loginPresenter = new LoginPresenter(this, loginView, locationClient);



    }
}

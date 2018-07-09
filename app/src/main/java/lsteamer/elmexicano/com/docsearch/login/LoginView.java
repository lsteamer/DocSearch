package lsteamer.elmexicano.com.docsearch.login;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsteamer.elmexicano.com.docsearch.R;

public class LoginView extends Fragment {


    public LoginView(){

    }

    public static LoginView newInstance() {
        return new LoginView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        return view;
    }



}

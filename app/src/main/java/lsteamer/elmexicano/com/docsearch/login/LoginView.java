package lsteamer.elmexicano.com.docsearch.login;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.docsearch.R;

import static android.support.v4.util.Preconditions.checkNotNull;

public class LoginView extends Fragment implements LoginContract.LoginViewContract {


    private static String TAG = "LoginView";

    private LoginContract.LoginPresenterContract mPresenter;

    public LoginView(){

    }

    public static LoginView newInstance() {
        return new LoginView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(@NonNull LoginContract.LoginPresenterContract presenter) {
        this.mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.button_login)
    void onClickLoginButton(){
        mPresenter.checkLoginConditions();
    }



}

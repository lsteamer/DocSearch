package lsteamer.elmexicano.com.docsearch.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.docsearch.R;

import static android.support.v4.util.Preconditions.checkNotNull;

public class LoginView extends Fragment implements LoginContract.LoginViewContract {


    private static String TAG = "LoginView";

    private LoginContract.LoginPresenterContract mPresenter;


    @BindView(R.id.text_input_username)
    TextInputLayout textInputUsername;
    @BindView(R.id.text_input_password)
    TextInputLayout textInputPassword;

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

    public String getTextInputUsername(){
        return Objects.requireNonNull(textInputUsername.getEditText()).getText().toString().trim();
    }

    public String getTextInputPassword(){
        return Objects.requireNonNull(textInputPassword.getEditText()).getText().toString().trim();
    }

    public void setUsernameErrorTitle(String s){
        textInputUsername.setError(s);
    }

    public void setPasswordErrorTitle(String s){
        textInputPassword.setError(s);
    }



}

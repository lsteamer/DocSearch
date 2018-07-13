package lsteamer.elmexicano.com.docsearch.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.docsearch.R;

import static android.support.v4.util.Preconditions.checkNotNull;

public class LoginView extends Fragment implements LoginContract.LoginViewContract {


    private LoginContract.LoginPresenterContract mPresenter;


    @BindView(R.id.text_input_username)
    TextInputLayout textInputUsername;
    @BindView(R.id.text_input_password)
    TextInputLayout textInputPassword;
    @BindView(R.id.login_constraint_layout)
    ConstraintLayout loginConstraintLayout;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar progressBar;

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
        mPresenter.loginToGetToken();
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

    public void makeToast(String toast){
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }

    public void toggleLayoutVisibility(){
        if(loginConstraintLayout.getVisibility()==View.VISIBLE){
            loginConstraintLayout.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            loginConstraintLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

}

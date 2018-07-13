package lsteamer.elmexicano.com.docsearch.login;

public interface LoginContract {

    interface LoginViewContract {

        void setPresenter(LoginContract.LoginPresenterContract presenter);

        String getTextInputUsername();

        String getTextInputPassword();

        void setUsernameErrorTitle(String s);

        void setPasswordErrorTitle(String s);

        void makeToast(String s);

        void toggleLayoutVisibility();

    }

    interface LoginPresenterContract {

        void loginToGetToken();

        boolean requestLocationPermission();

    }


}

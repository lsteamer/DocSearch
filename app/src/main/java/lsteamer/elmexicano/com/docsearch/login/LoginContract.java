package lsteamer.elmexicano.com.docsearch.login;

public interface LoginContract {

    interface LoginViewContract{

        void setPresenter(LoginContract.LoginPresenterContract presenter);

    }

    interface LoginPresenterContract{

        void checkLoginConditions();
        boolean requestLocationPermission();

    }


}

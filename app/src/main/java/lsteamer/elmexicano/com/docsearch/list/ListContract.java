package lsteamer.elmexicano.com.docsearch.list;

public interface ListContract {

    interface ListViewContract{

        void setPresenter(ListPresenterContract listPresenterContract);
        void makeToast(String string);
        void toogleLayoutVisibility();

    }

    interface ListPresenterContract{

        void getDoctorAPIList();
    }
}

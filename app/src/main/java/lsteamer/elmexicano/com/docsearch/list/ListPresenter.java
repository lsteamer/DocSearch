package lsteamer.elmexicano.com.docsearch.list;

import android.app.Activity;

class ListPresenter implements ListContract.ListPresenterContract {


    private Activity listActivity;
    private ListContract.ListViewContract mView;

    ListPresenter(Activity activity, ListContract.ListViewContract contract){

        this.listActivity = activity;
        this.mView = contract;

    }
}

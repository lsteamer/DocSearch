package lsteamer.elmexicano.com.docsearch.list;

import android.app.Activity;
import android.support.annotation.NonNull;

import lsteamer.elmexicano.com.docsearch.list.model.DoctorData;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;
import lsteamer.elmexicano.com.docsearch.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ListPresenter implements ListContract.ListPresenterContract {


    private Activity listActivity;
    private ListContract.ListViewContract mView;
    private UrlContents urlContents;

    ListPresenter(Activity activity, ListContract.ListViewContract contract, UrlContents urlContents) {
        this.listActivity = activity;
        this.mView = contract;
        this.urlContents = urlContents;
        mView.setPresenter(this);

    }


    @Override
    public void getDoctorAPIList() {

        //mView.toggleLayoutVisibility();
        String fullUrl = Utils.uriParser(urlContents, 3);

        Call<DoctorData> call = Utils.getDoctorRequestData(urlContents.getBaseUrl(), fullUrl, urlContents.getBearer());

        call.enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(@NonNull Call<DoctorData> call, @NonNull Response<DoctorData> response) {

            }

            @Override
            public void onFailure(@NonNull Call<DoctorData> call, @NonNull Throwable t) {

            }
        });

    }
}

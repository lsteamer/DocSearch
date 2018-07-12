package lsteamer.elmexicano.com.docsearch.list;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.RecylerViewAdapter.DoctorAdapter;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
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
    private ArrayList<Doctor> doctorList;

    ListPresenter(Activity activity, ListContract.ListViewContract contract, UrlContents urlContents) {
        this.listActivity = activity;
        this.mView = contract;
        this.urlContents = urlContents;
        doctorList = null;

        mView.setPresenter(this);

    }


    @Override
    public void getDoctorAPIList() {
        String fullUrl = Utils.uriParser(urlContents, 3);

        Call<DoctorData> call = Utils.getDoctorRequestData(urlContents.getBaseUrl(), fullUrl, urlContents.getBearer());

        call.enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(@NonNull Call<DoctorData> call, @NonNull Response<DoctorData> response) {
                mView.toogleLayoutVisibility();

                if (response.body() != null) {
                    doctorList = (ArrayList) response.body().getListDoctors();
                    mView.makeToast(doctorList.get(0).getName());


                    Log.d("SSS",doctorList.get(0).getPhotoId());
                    Log.d("sss",urlContents.getBearer());

                } else
                    mView.makeToast(listActivity.getString(R.string.login_fail));


            }

            @Override
            public void onFailure(@NonNull Call<DoctorData> call, @NonNull Throwable t) {

                mView.makeToast(listActivity.getString(R.string.login_fail));

            }
        });
    }

    DoctorAdapter mAdapter;

    public void setAdapter(){
        RecyclerView doctorRecyclerView = listActivity.findViewById(R.id.recycler_view_doctors);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(listActivity.getApplicationContext()));

        mAdapter = new DoctorAdapter((AppCompatActivity)listActivity, doctorList);
    }

    public boolean isDoctorListWithContents(){
        return doctorList!=null;
    }


}

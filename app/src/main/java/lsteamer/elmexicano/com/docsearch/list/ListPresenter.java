package lsteamer.elmexicano.com.docsearch.list;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    private List<Doctor> doctorList;

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


                if (response.body() != null) {
                    doctorList = response.body().getListDoctors();
                    mView.makeToast(doctorList.get(0).getName());


                    setAdapter();

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



        for(int i = 0; doctorList.size()>i; i++){
            if(doctorList.get(i).getPhotoId()!=null)
                Log.d("Somesomesomesome", doctorList.get(i).getPhotoId());
        }
        Log.d("Somesome",urlContents.getPathToken());
        Log.d("Somesome",urlContents.getPathToken());
        Log.d("Somesome",urlContents.getPathToken());

        mView.toogleLayoutVisibility();
        RecyclerView doctorRecyclerView = listActivity.findViewById(R.id.recycler_view_doctors);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(listActivity.getApplicationContext()));

        mAdapter = new DoctorAdapter((AppCompatActivity)listActivity, doctorList);

        doctorRecyclerView.setAdapter(mAdapter);
    }

    public boolean isDoctorListWithContents(){
        return doctorList!=null;
    }


}

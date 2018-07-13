package lsteamer.elmexicano.com.docsearch.list;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.recycler.DoctorAdapter;
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
    private DoctorAdapter docAdapter;
    private LinearLayoutManager linearLayoutManager;

    ListPresenter(Activity listActivity, ListContract.ListViewContract contract, UrlContents urlContents, LinearLayoutManager linearLayoutManager) {
        this.listActivity = listActivity;
        this.mView = contract;
        this.urlContents = urlContents;
        this.linearLayoutManager = linearLayoutManager;
        doctorList = null;

        mView.setPresenter(this);

    }


    @Override
    public void getDoctorAPIList(int decider) {
        String fullUrl = Utils.uriParser(urlContents, decider,null);

        Call<DoctorData> call = Utils.getDoctorRequestData(urlContents.getBaseUrl(), fullUrl, urlContents.getBearer());

        call.enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(@NonNull Call<DoctorData> call, @NonNull Response<DoctorData> response) {

                if (response.body() != null) {
                    doctorList = response.body().getListDoctors();
                    getDoctorImagesUrl();

                    docAdapter = Utils.getDoctorAdapter((AppCompatActivity)listActivity, doctorList, urlContents);

                    mView.setDoctorAdapter(docAdapter);

                } else
                    mView.makeToast(listActivity.getString(R.string.login_fail));
            }

            @Override
            public void onFailure(@NonNull Call<DoctorData> call, @NonNull Throwable t) {

                mView.makeToast(listActivity.getString(R.string.login_fail));
            }
        });
    }

    private void getDoctorImagesUrl(){
        for(int i = 0; i<doctorList.size(); i++){


            Log.d("Name", "Someneme " + doctorList.get(i).getName());
            Log.d("address", "Someneme " + doctorList.get(i).getAddress());
            Log.d("Doctor's Photo", "Whateva " + doctorList.get(i).getPhotoId());

            doctorList.get(i).setPhotoIdStatus(false);
            if(doctorList.get(i).getPhotoId()!=null){

                Log.d("Doctor's Photo", "TRUE" + doctorList.get(i).getPhotoId());
                doctorList.get(i).setPhotoIdStatus(true);
                String fullImageUrl = Utils.uriParser(urlContents, 4, doctorList.get(i).getPhotoId());
                doctorList.get(i).setPhotoIdUrl(fullImageUrl);
            }
            Log.d("Doctor's Photo Status", "Whateva " + doctorList.get(i).isPhotoIdStatus());
        }

    }

    public void getApictureUrl(){
        for(int i = 0; i<doctorList.size(); i++){
            String wat = "";
            if(doctorList.get(i).getPhotoId()!=null){
                wat = doctorList.get(i).getPhotoId();
            }
            Log.d("watWAt"," only one wat "+ wat);
            Log.d("watWAt"," whatyasay?" + doctorList.get(i).isPhotoIdStatus());

        }
        //Log.d("watWAt"," only one  "+ urlContents.getBearer());

    }

    public void searchDoctors(String doctorSearch){
        if(!doctorSearch.isEmpty()){
            mView.toggleLayoutVisibility();
            urlContents.setSearchValue(doctorSearch);
            getDoctorAPIList(2);
        }

    }

    public boolean isDoctorListWithContents(){
        return doctorList!=null;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }
}

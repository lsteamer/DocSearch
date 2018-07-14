package lsteamer.elmexicano.com.docsearch.list;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.recycler.DoctorAdapter;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
import lsteamer.elmexicano.com.docsearch.list.model.DoctorData;
import lsteamer.elmexicano.com.docsearch.login.LoginActivity;
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

    ListPresenter(Activity listActivity, ListContract.ListViewContract viewContract, UrlContents urlContents, LinearLayoutManager linearLayoutManager) {
        this.listActivity = listActivity;
        this.mView = viewContract;
        this.urlContents = urlContents;
        this.linearLayoutManager = linearLayoutManager;
        doctorList = null;

        mView.setPresenter(this);
    }


    @Override
    public void getDoctorAPIList(int decider) {
        String fullUrl = Utils.uriParser(urlContents, decider, null);

        Call<DoctorData> call = Utils.getDoctorRequestData(urlContents.getBaseUrl(), fullUrl, urlContents.getBearer());

        call.enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(@NonNull Call<DoctorData> call, @NonNull Response<DoctorData> response) {

                if (response.body() != null) {
                    /*
                     * If we have a response that's not null, we
                     * get the Doctor List, we set the complete url for the
                     * images when applicable, and we get a new adapter with it
                     * we then call the View Layer to set the Adapter
                     */
                    doctorList = response.body().getListDoctors();
                    getDoctorImagesUrl();

                    docAdapter = Utils.getDoctorAdapter((AppCompatActivity) listActivity, doctorList, urlContents);

                    mView.setDoctorAdapter(docAdapter);
                } else{
                    mView.makeToast(listActivity.getString(R.string.login_fail));
                    mView.toggleLayoutVisibility();
                    startLoginActivity();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorData> call, @NonNull Throwable t) {
                mView.makeToast(listActivity.getString(R.string.login_fail));
                startLoginActivity();

            }
        });
    }


    /**
     * This Method only gets called if getDoctorAPIList encounters an issue
     * sending the user back to the first activity
     */
    private void startLoginActivity() {
        Intent loginIntent = Utils.getIntent(listActivity, LoginActivity.class);

        //Ending this Activity
        listActivity.finish();
        //Calling the next one
        listActivity.startActivity(loginIntent);
    }


    /*
     *  This method goes through the List<Doctor> and
     *  sets the full url (from the curl command) when it exists
     */
    private void getDoctorImagesUrl() {
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getPhotoId() != null) {
                String fullImageUrl = Utils.uriParser(urlContents, 4, doctorList.get(i).getPhotoId());
                doctorList.get(i).setPhotoIdUrl(fullImageUrl);
            }
        }
    }


    public void searchDoctors(String doctorSearch) {
        if (!doctorSearch.isEmpty()) {
            mView.toggleLayoutVisibility();
            urlContents.setSearchValue(doctorSearch);
            getDoctorAPIList(2);
        }
    }

    public boolean isDoctorListWithContents() {
        return doctorList != null;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }
}

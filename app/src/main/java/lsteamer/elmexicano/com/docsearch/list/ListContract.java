package lsteamer.elmexicano.com.docsearch.list;

import android.support.v7.widget.LinearLayoutManager;

import lsteamer.elmexicano.com.docsearch.list.recycler.DoctorAdapter;

public interface ListContract {

    interface ListViewContract {

        void setPresenter(ListPresenterContract listPresenterContract);

        void makeToast(String string);

        void toggleLayoutVisibility();

        void setDoctorAdapter(DoctorAdapter doctorAdapter);

    }

    interface ListPresenterContract {

        void getDoctorAPIList(int decider);

        boolean isDoctorListWithContents();

        LinearLayoutManager getLinearLayoutManager();

        void searchDoctors(String doctor);
    }
}

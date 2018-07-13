package lsteamer.elmexicano.com.docsearch.list;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.recycler.DoctorAdapter;

public class ListView extends Fragment implements ListContract.ListViewContract {

    private ListContract.ListPresenterContract mPresenter;


    @BindView(R.id.list_constraint_layout)
    ConstraintLayout listConstraintLayout;
    @BindView(R.id.list_loading_indicator)
    ProgressBar progressBar;
    @BindView(R.id.search_doctor_bar)
    EditText editText;
    @BindView(R.id.recycler_view_doctors)
    RecyclerView doctorRecyclerView;

    public ListView(){

    }

    public static ListView newInstance() {
        return new ListView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(!mPresenter.isDoctorListWithContents()){
            mPresenter.getDoctorAPIList(3);
            doctorRecyclerView.setLayoutManager(mPresenter.getLinearLayoutManager());
            makeToast(getString(R.string.patience_from_zhou));
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setPresenter(ListContract.ListPresenterContract listPresenterContract) {
        this.mPresenter = listPresenterContract;
    }

    @OnEditorAction(R.id.search_doctor_bar)
    public boolean searchDoctorAction(TextView v, int actionId, KeyEvent event){
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            mPresenter.searchDoctors(getTextInputSearch(v));
            return true;
        }
        return false;
    }

    private String getTextInputSearch(TextView textView){
        return Objects.requireNonNull(textView.getEditableText().toString());
    }

    public void setDoctorAdapter(DoctorAdapter doctorAdapter){
        toggleLayoutVisibility();
        doctorRecyclerView.setAdapter(doctorAdapter);

    }

    @Override
    public void makeToast(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toggleLayoutVisibility() {
        if(listConstraintLayout.getVisibility()==View.VISIBLE){
            listConstraintLayout.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            listConstraintLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}

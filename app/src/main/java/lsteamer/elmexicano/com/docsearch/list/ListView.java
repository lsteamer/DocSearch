package lsteamer.elmexicano.com.docsearch.list;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import lsteamer.elmexicano.com.docsearch.R;

public class ListView extends Fragment implements ListContract.ListViewContract {

    private ListContract.ListPresenterContract mPresenter;


    @BindView(R.id.list_constraint_layout)
    ConstraintLayout listConstraintLayout;
    @BindView(R.id.list_loading_indicator)
    ProgressBar progressBar;

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
            mPresenter.getDoctorAPIList();
            makeToast(getString(R.string.patience_from_zhou));
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setPresenter(ListContract.ListPresenterContract listPresenterContract) {
        this.mPresenter = listPresenterContract;
    }

    @Override
    public void makeToast(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toogleLayoutVisibility() {
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

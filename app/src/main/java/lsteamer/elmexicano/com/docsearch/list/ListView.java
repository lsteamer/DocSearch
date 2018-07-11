package lsteamer.elmexicano.com.docsearch.list;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsteamer.elmexicano.com.docsearch.R;

public class ListView extends Fragment implements ListContract.ListViewContract {



    public ListView(){

    }

    public static ListView newInstance() {
        return new ListView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);


        return view;
    }
}

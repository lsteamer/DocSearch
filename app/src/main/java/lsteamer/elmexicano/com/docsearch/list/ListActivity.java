package lsteamer.elmexicano.com.docsearch.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.utils.Utils;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);


        //ViewLayer
        listView = (ListView) getSupportFragmentManager().findFragmentById(R.id.listContentFrame);
        if(listView==null){
            listView = ListView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), listView, R.id.listContentFrame);
        }


        //PresenterLayer
        listPresenter = new ListPresenter(this, listView);
    }
}

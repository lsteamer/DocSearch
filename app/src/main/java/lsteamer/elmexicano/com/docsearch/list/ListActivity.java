package lsteamer.elmexicano.com.docsearch.list;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;
import lsteamer.elmexicano.com.docsearch.utils.Utils;

public class ListActivity extends AppCompatActivity {

    public ListView listView;
    public ListPresenter listPresenter;
    public UrlContents urlContents;
    public LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        //We get the urlContents to use when building urls
        urlContents = (UrlContents) getIntent().getSerializableExtra(getString(R.string.url_serializable_tag));

        //ViewLayer
        listView = (ListView) getSupportFragmentManager().findFragmentById(R.id.listContentFrame);
        if (listView == null) {
            listView = ListView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), listView, R.id.listContentFrame);
        }

        //
        layoutManager = new LinearLayoutManager(this);


        //PresenterLayer
        listPresenter = new ListPresenter(this, listView, urlContents, layoutManager);
    }
}

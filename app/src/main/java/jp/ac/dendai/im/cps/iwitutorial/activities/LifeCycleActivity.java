package jp.ac.dendai.im.cps.iwitutorial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import jp.ac.dendai.im.cps.iwitutorial.R;
import jp.ac.dendai.im.cps.iwitutorial.RecyclerViewAdapter;
import jp.ac.dendai.im.cps.iwitutorial.fragments.LifeCycleFragment;

public class LifeCycleActivity extends AppCompatActivity implements LifeCycleFragment.OnFragmentInteractionListener {
    private static final String TAG = LifeCycleActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private int timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        // adapterがnullだと起動できないため
        adapter = new RecyclerViewAdapter(this);
        addLogItem("onCreate");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("LifeCycle");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LifeCycleFragment.newInstance("Activity and Fragment LifeCycle", getString(R.string.lifecycle_description)))
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        addLogItem("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        addLogItem("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        addLogItem("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        addLogItem("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addLogItem("onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addLogItem("onRestart()");
    }

    @Override
    public void onFragmentLogMessage(String tag, String message) {
        timing++;
        String str = "Fragment: " + message;
        adapter.add(timing, str);
        Log.d(tag, message);
    }

    private void addLogItem(String name) {
        timing++;
        String str = "Activity: " + name;
        adapter.add(timing, str);
        Log.d(TAG, name);
    }
}

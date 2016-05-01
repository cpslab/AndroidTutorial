package jp.ac.dendai.im.cps.iwitutorial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import jp.ac.dendai.im.cps.iwitutorial.R;
import jp.ac.dendai.im.cps.iwitutorial.fragments.LifeCycleFragment;

public class LifeCycleActivity extends AppCompatActivity implements LifeCycleFragment.OnFragmentInteractionListener {
    private static final String TAG = LifeCycleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("LifeCycle");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LifeCycleFragment.newInstance("param1", "param2"))
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called with: " + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called with: " + "");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called with: " + "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called with: " + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called with: " + "");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called with: " + "");
    }

    @Override
    public void onFragmentLogMessage(String tag, String message) {
        Log.d(tag, message);
    }
}

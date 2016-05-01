package jp.ac.dendai.im.cps.iwitutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import jp.ac.dendai.im.cps.iwitutorial.R;
import jp.ac.dendai.im.cps.iwitutorial.fragments.MapsActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
//        manager = getSupportFragmentManager();
//
//        manager.beginTransaction()
//                .replace(R.id.container, SurfaceSampleFragment.newInstance())
//                .commit();
    }
}

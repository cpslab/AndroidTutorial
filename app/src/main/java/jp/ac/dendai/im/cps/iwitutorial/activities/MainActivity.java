package jp.ac.dendai.im.cps.iwitutorial.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import jp.ac.dendai.im.cps.iwitutorial.R;
import jp.ac.dendai.im.cps.iwitutorial.fragments.CanvasFragment;
import jp.ac.dendai.im.cps.iwitutorial.fragments.MainFragment;
import jp.ac.dendai.im.cps.iwitutorial.fragments.SensorSampleFragment;
import jp.ac.dendai.im.cps.iwitutorial.fragments.SurfaceSampleFragment;

import static android.support.v4.view.GravityCompat.START;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager manager;
    private Toolbar toolbar;
    private NavigationView navView;
    private DrawerLayout drawer;

    private FrameLayout containerLayout;
    private FrameLayout mapLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        containerLayout = (FrameLayout) findViewById(R.id.container);
        mapLayout = (FrameLayout) findViewById(R.id.map);

        toolbar.setTitle("CPSLAB Android Tutorial");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(START);
            }
        });

        navView.setNavigationItemSelectedListener(this);

        manager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home: {
                toolbar.setTitle("CPSLAB Android Tutorial");
                navView.setCheckedItem(R.id.nav_home);
                manager.beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commit();
                break;
            }
            case R.id.nav_map: {
                toolbar.setTitle("Map");
                navView.setCheckedItem(R.id.nav_map);
                SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                mapFragment.getMapAsync(this);
                manager.beginTransaction()
                        .replace(R.id.container, mapFragment)
                        .commit();
                break;
            }
            case R.id.nav_canvas: {
                toolbar.setTitle("Canvas");
                navView.setCheckedItem(R.id.nav_canvas);
                manager.beginTransaction()
                        .replace(R.id.container, CanvasFragment.newInstance())
                        .commit();
                break;
            }
            case R.id.nav_surface: {
                toolbar.setTitle("Surface");
                navView.setCheckedItem(R.id.nav_surface);
                manager.beginTransaction()
                        .replace(R.id.container, SurfaceSampleFragment.newInstance())
                        .commit();
                break;
            }
            case R.id.nav_lifecycle: {
                navView.setCheckedItem(R.id.nav_lifecycle);
                Intent intent = new Intent(this, LifeCycleActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_sensor: {
                toolbar.setTitle("Sensor");
                navView.setCheckedItem(R.id.nav_sensor);
                manager.beginTransaction()
                        .replace(R.id.container, SensorSampleFragment.newInstance())
                        .commit();
                break;
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latlng = new LatLng(35.749882, 139.804975);
        mMap.addMarker(new MarkerOptions().position(latlng).title("Marker in Sydney"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
    }

    private void visibleMap() {
        mapLayout.setVisibility(View.VISIBLE);
        containerLayout.setVisibility(View.GONE);
    }

    private void visibleContainer() {
        containerLayout.setVisibility(View.VISIBLE);
        mapLayout.setVisibility(View.GONE);
    }
}
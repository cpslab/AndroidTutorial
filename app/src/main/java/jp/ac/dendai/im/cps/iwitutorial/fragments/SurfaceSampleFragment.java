package jp.ac.dendai.im.cps.iwitutorial.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jp.ac.dendai.im.cps.iwitutorial.R;
import jp.ac.dendai.im.cps.iwitutorial.sampleviews.SampleSurfaceView;

public class SurfaceSampleFragment extends Fragment implements SensorEventListener {
    private static final String TAG = SurfaceSampleFragment.class.getSimpleName();

    private SampleSurfaceView sv;
    private SensorManager mSensorManager;
    private List<Sensor> sensorList;
    private final int SENSOR_DELAY = SensorManager.SENSOR_DELAY_GAME;
    // センサーの値
    float[] magneticValues = new float[3];
    float[] accelerometerValues = new float[3];

    private Context mContext;

    public SurfaceSampleFragment() {
        // Required empty public constructor
    }

    public static SurfaceSampleFragment newInstance() {
        SurfaceSampleFragment fragment = new SurfaceSampleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_surface_sample, container, false);

        sv = (SampleSurfaceView) v.findViewById(R.id.surface_view);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager = (SensorManager) mContext.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                mSensorManager.registerListener(this, sensor, SENSOR_DELAY);
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                mSensorManager.registerListener(this, sensor, SENSOR_DELAY);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // センサ値が変わった時に呼び出されるメソッド．レジストしたそれぞれのセンサの値がイベントとして引数に入ってくる．
    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD:
                magneticValues = event.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerValues = event.values.clone();
                break;
        }

        StringBuilder message = new StringBuilder();
        message.append("Accelerometer ")
                .append(String.valueOf(accelerometerValues[0])).append(", ")
                .append(String.valueOf(accelerometerValues[1])).append(", ")
                .append(String.valueOf(accelerometerValues[2]));
        Log.d(TAG, message.toString());

        sv.setAccelerometer(accelerometerValues);
    }

    // センサの精度が変更された時に呼び出されるメソッド
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

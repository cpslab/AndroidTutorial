package jp.ac.dendai.im.cps.iwitutorial;

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
import android.widget.TextView;

import java.util.List;

public class CanvasFragment extends Fragment implements SensorEventListener {
    private static final String TAG = CanvasFragment.class.getSimpleName();

    private Context mContext;
    private SensorManager sensorManager;
    private SampleGraphicsView gv;
    private TextView txtLight;
    float lightValue;

    public CanvasFragment() {
        // Required empty public constructor
    }

    public static CanvasFragment newInstance() {
        CanvasFragment fragment = new CanvasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO:
        }

        //Sensorの値を取得するためにSensorManagerクラスを取得
        //Sensorは複数あるのでListに格納
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //Sensor一つひとつにListenerをセット
        for (Sensor sensor : sensors) {
            if (sensor.getType() == Sensor.TYPE_LIGHT) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_canvas, container, false);

        gv = (SampleGraphicsView) v.findViewById(R.id.canvas);
        txtLight = (TextView) v.findViewById(R.id.txtLight);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // センサー解除
        sensorManager.unregisterListener(this);
    }

    //センサーの値を取得した時に呼ばれる
    @Override
    public void onSensorChanged(SensorEvent event) {
        lightValue = event.values[0];

        Log.d(TAG, "lightValue -> " + Float.toString(lightValue));

        txtLight.setText(String.format("%.2f", lightValue));
        gv.draw(lightValue);
    }

    // センサーの精度が変更された時に呼び出される
    // 第二引数は新しいセンサーの精度
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

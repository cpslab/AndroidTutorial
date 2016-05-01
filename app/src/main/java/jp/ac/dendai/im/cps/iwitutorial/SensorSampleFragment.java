package jp.ac.dendai.im.cps.iwitutorial;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Timer;

public class SensorSampleFragment extends Fragment implements View.OnClickListener, SensorEventListener, LocationListener {
    private final static String TAG = SensorSampleFragment.class.getSimpleName();

    private Context mContext;
    private SensorManager sensorManager; //センサーマネージャ
    private Sensor accelerometer;    //加速度せンサー
    private Sensor orientation; //回転せンサー
    private Sensor light;        //照度センサー
    private LocationManager gpsManager; //GPSサー)ビスT
    private TextView txtAccX;                //X軸の加速度（m/s）
    private TextView txtAccY;                //Y軸の加速度（m/s）
    private TextView txtAccZ;                //Z軸の加速度（m/s）
    private TextView txtAzimuth;            //方位
    private TextView txtPitch;            //ピッチ
    private TextView txtRoll;            //ロール
    private TextView txtLight;        //照度
    private TextView txtGPS;          //GPS

    private boolean isSensing = false;

    public SensorSampleFragment() {
        // Required empty public constructor
    }

    public static SensorSampleFragment newInstance() {
        SensorSampleFragment fragment = new SensorSampleFragment();
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
        View v = inflater.inflate(R.layout.fragment_sensor_sample, container, false);

        ToggleButton toggleButton = (ToggleButton) v.findViewById(R.id.btnSwitch);
        toggleButton.setOnClickListener(this);

        //文字表示の取得
        txtAccX = (TextView) v.findViewById(R.id.txtAccX);                //X軸の加速度（m/s）
        txtAccY = (TextView) v.findViewById(R.id.txtAccY);
        txtAccZ = (TextView) v.findViewById(R.id.txtAccZ);
        txtAzimuth = (TextView) v.findViewById(R.id.txtAzimuth);
        txtPitch = (TextView) v.findViewById(R.id.txtPitch);
        txtRoll = (TextView) v.findViewById(R.id.txtRoll);
        txtLight = (TextView) v.findViewById(R.id.txtLight);
        txtGPS = (TextView) v.findViewById(R.id.txtGPS);

        //センサー取得
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> list;

        /*
                定数名                 センサー名	      単位
                Android 1.6から
                TYPE_ACCELEROMETOR      加速度センサー	  m/s^2
                TYPE_GYROSCOPE	        ジャイロセンサー  rad/s
                TYPE_LIGHT	            照度センサー	  lux
                TYPE_MAGNETIC_FIELD	    磁界センサー	  uT
                TYPE_ORIENTATION	    傾きセンサー	  deg
                TYPE_PRESSURE	        圧力センサー	  hPa
                TYPE_PROXIMITY	        近接センサー	  cm
                TYPE_TEMPERATURE	    温度センサー	  ℃
                注) API Level 8で, TYPE_ORIENTATIONは非推奨となった!　でも書き直すのめんどくさいからそのまま使う

                Android 2.3から
                TYPE_GRAVITY	        重力センサー	  m/s^2
                TYPE_LINEAR_ACCELERATION	直線化速度センサー	m/s^2
                TYPE_ROTATION_VECTOR	回転ベクトルセンサー

                Android 4.0から
                TYPE_AMBIENT_TEMPERATURE	温度センサー	℃
                TYPE_RELATIVE_HUMIDITY	湿度センサー	%
                注) TYPE_AMBIENT_TEMPERATUREの追加により, TYPE_TEMPERATUREは非推奨となった!
         */

        //各センサーを取得
        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0) {
            accelerometer = list.get(0);
        }

        list = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (list.size() > 0) {
            orientation = list.get(0);
        }

        list = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        if (list.size() > 0) {
            light = list.get(0);
        }

        //GPSを取得
        gpsManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

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
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnSwitch) {
            if (!isSensing) {
                Log.d("Main", String.valueOf(v.getId()));

                //各センサーマネージャーに各センサーのリスナーを登録
                if (accelerometer != null)
                    sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);

                if (orientation != null)
                    sensorManager.registerListener(this, orientation, SensorManager.SENSOR_DELAY_UI);

                if (light != null)
                    sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_UI);

                gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

                isSensing = true;

            } else {
                //各センサーのリスナーを解除
                sensorManager.unregisterListener(this);
                gpsManager.removeUpdates(this);
                isSensing = false;
            }

        }

    }

    @Override
    public void onLocationChanged(Location location) {
        txtGPS.setText(String.format("%.8f", location.getLongitude()) + "," + String.format("%.8f", location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //センサーの値が変更されたらtextViewを書き換え
        if (event.sensor == accelerometer) {
            Log.d("Main", String.valueOf(event.values[0]));
            txtAccX.setText(String.format("%.2f", event.values[0]));
            txtAccY.setText(String.format("%.2f", event.values[1]));
            txtAccZ.setText(String.format("%.2f", event.values[2]));
        } else if (event.sensor == orientation) {
            txtAzimuth.setText(String.format("%.2f", event.values[0]));
            txtPitch.setText(String.format("%.2f", event.values[1]));
            txtRoll.setText(String.format("%.2f", event.values[2]));
        } else if (event.sensor == light) {
            txtLight.setText(String.format("%.2f", event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

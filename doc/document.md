# CPSLAB Android Tutorial
この資料はCPSLABのゼミ生に向けたものです。  
いろいろはしょって説明してます。  
おすすめのAndroidチュートリアルは[mixiのAndroidTrainig](https://github.com/mixi-inc/AndroidTraining)  
かなり詳しく説明されている。  
基礎編くらいまで理解できるとまぁまぁ書けるようになると思う  

# Android とは
携帯電話用ソフトウェアのプラットフォーム  
2003年にアンディ・ルービン、リッチ ・マイナー、ニック・シアーズ、クリス・ホワイトがアメリカカリフォルニア州パロアルトに携帯電話向けソフトウェアプラットフォームを開発するAndroid社を設立した。  
2005年にGoogleがAndroid社を買収し，2007年11月5日 携帯電話用ソフトウェアのプラットフォームであるAndroidを、Google、米クアルコム、独通信キャリアのT-モバイル (T-Mobile International) などが中心となり設立した規格団体 「Open Handset Alliance」（オープン・ハンドセット・アライアンス、OHA）が発表した。  
<https://www.android.com/intl/ja_jp/>

## 歴史
Androidのバージョンにはそれぞれコードネームが付いている。

- 1.6 Donut
- 2.1 Eclair
- 2.2 Froyo
- 2.3 Gingerbread
- 3.0 Honeycomb
- 4.0 Ice Cream Sandwich
- 4.1 Jelly Bean
- 4.4 KitKat
- 5.0 Lollipop
- 6.0 Marshmallow
- 7.0 Nougat
- 8.0 Oreo
- Next Android P

# Activity と Fragment
## Activity
ActivityとはAndroidアプリの画面  
MVCで言うとViewとControllerの部分に相当  
昔はActivity自体にボタンやリストなどのコンポーネントを配置してコントロールしていたが、
今の役割は基本的にFragmentの管理

## Fragment
Android DevelopersのFragmentのJavadocには  
UIを持ち、そのUIの振る舞いを管理する、Activityの中に組み込むことが出来るコンポーネントがFragment
となっている。  
つまりViewを持ちながら、そのControllerのロジックを使いまわせるコンポーネントです。  
今の開発では、ActivityにFragmentを配置していく事がほとんどです。

※ android.app.Fragment と android.support.v4.app.Fragment の2つがあるが、基本的に使用するのは後者

# Layout
Androidアプリの画面レイアウトはxmlファイルに記述し定義する。  
配置したコンポーネントにidを付けることにより、コードからアクセスが可能になる。
また、いろいろなパラメータを指定していくことにより、余白は中央寄せ、スタイルなどUIに関する様々なことを指定することができる。
以下、サンプルアプリのMainActivtyのlayoutのコード

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.CoordinatorLayout
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".activities.MainActivity">

        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/AppTheme.AppBarOverlay">

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:elevation="5dp"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:theme="@style/NoActionBarTheme"
                app:popupTheme="@style/AppTheme.PopupOverlay" />

        </android.support.design.widget.AppBarLayout>

        <FrameLayout
            android:id="@+id/container"
            android:layout_marginTop="?attr/actionBarSize"
            android:layout_width="match_parent"
            android:layout_height="match_parent" >

        </FrameLayout>

    </android.support.design.widget.CoordinatorLayout>

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/menu_navigation" />

</android.support.v4.widget.DrawerLayout>
```

# LifeCycle
AndroidアプリにはLifeCycleというものがあり、LifeCycleを意識してプログラムを組むことがあると思います。  
以下の画像がライフサイクルです。左側がActivity、右側がFragment  
また、サンプルプログラムにLifeCycleの項目があります。そこでどのタイミングで呼ばれているか確認してみよう。  

※ サンプルプログラムはActivityとLifeCycleの両方を合わせた順番で表示しています。  

![Activity](image/lifecycle_activity.png)
![Fragment](image/lifecycle_fragment.png)

# Activity
## Context
Application,ActivityはContextクラスを継承している。このクラスはアプリケーションの情報をもっていたり、システムのサービスにアクセスするときに必要になるオブジェクトである。Fragmentは継承していないのでFragmentでContextを使った操作が必要な場合にはonAttachあたりで保持しておくのが一般的。

## 画面遷移
画面遷移にはIntentを使用する。  

```java
// MainActivity → SubActivity への画面遷移の場合
Intent intent = new Intent(this, SubActivity.class);
intent.putExtra(key, value);
startActivity(intent);
```

# Fragment
## FragmentManager
FragmentはFragmentManagerをつかって以下のように管理する。

android.app.Fragment の場合は FragmentManager,  
android.support.v4.app.Fragment の場合は SupportFragmentManager を用いる

```java
FragmentManager manager = getSupportFragmentManager();
MainFragment fragment = MainFragment.newInstance();
FragmentTransaction transaction = manager.beginTransaction();

// transaction.add(R.id.container, fragment);
transaction.replace(R.id.container, fragment);
transaction.commit();
```

`manager.beginTransaction()`でFragmentの操作を有効化しcommit()を呼んだ段階でそれまでの操作がすべて反映される。

## newInstance
Fragmentは様々なタイミングでコンストラクタが呼ばれるため、コンストラクタの引数は空である必要がある。  
そのため、Fragmentに値を渡すにはnewInstanceメソッドを実装し受け渡しをする。  

```java
private static final String ARGS_PARAM1 = "param1";
private static final String ARGS_PARAM2 = "param2";

private String mParam1;
private String mParam2;

public static MainFragment newInstance(String param1, String param2) {
    MainFragment fragment = new MainFragment();
    Bundle args = new Bundle();
    // args.putString(key, value);
    args.putString(ARGS_PARAM1, param1);
    args.putString(ARGS_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
}

...

public void onCreate(Bundle savesInstanceState) {
    super.onCreate(savesInstanceState);

    Bundle args = getArguments();
    if (args == null) {
        return;
    }

    // args.getString(key);
    mParam1 = args.getString(ARGS_PARAM1);
    mParam2 = args.getString(ARGS_PARAM2);
}
```

#### Fragment自身にsetterを実装してオブジェクトを直接渡してあげれば良いのでは？
NGです。Fragmentは様々なタイミングでコンストラクタが呼ばれます。ということはそのタイミングでインスタンスを作りなおしているということです。  
その場合setterで渡した値は`null`になってアプリが落ちます。  
Bundleをかいしてデータを渡すことによりBundleにデータが保存されるため、再び`onCreate()`が呼びだされたタイミングでもデータの取得が可能になります。  
※ 主に`onPause()`からの復帰とかで落ちます。自分も始めの頃は悩まされました…

## FragmentからActivityへのメッセージ
FragmentからActivityに対してメッセージを送りたい場合が結構あります。例えば画面遷移とか。  
その場合は`interface`を使用します。Fragment内で作ったinterfaceを管理しているAvtivityでimplementsすることによりメッセージを送ることが可能になります。  
リストを持つFragmentとかだったらItemがクリックされたら、positionと持ってるデータをActivityに渡したりとか。

##### MainFragment.java
```java
private OnFragmentInteractionListener mListener;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_lifecycle, container, false);

    Button btn = (Button) v.findViewById(R.id.btn);
    btn.setOnClickListener(new OnClickListener() {
            @Override
            onClick(View v) {
                mListener.onFragmentAction("button click");
            }
        }
    );
}

...

@Override
public void onAttach(Context context) {
    super.onAttach(context);

    mListener = (OnFragmentInteractionListener) context;
}

...

public interface OnFragmentInteractionListener {
    void onFragmentAction(String message);
}
```

##### MainActivity.java
```java
public class MainActivity extends AppCompatAvtivity implements MainFragment.OnFragmentInteractionListener {

... 

    void onFragmentAction(String message) {
        Log.d(TAG, message);
    }
}
```

# Sensor
Android端末にはたくさんの種類のセンサが搭載されている。SensorManagerクラスから取得できるセンサは以下のとおり。  
GPSの取得にはLocationManagerクラスを使用する。
※ 端末によって搭載されているセンサが違うため確認が必要  

|定数名|センサ名|単位|
|:--|:--|:--|
|**Android 1.6から**||
|TYPE_ACCELEROMETOR|加速度センサ|m/s^2|
|TYPE_GYROSCOPE|ジャイロセンサー|rad/s|
|TYPE_LIGHT|照度センサ|lux|
|TYPE_MAGNETIC_FIELD|磁界センサ|uT|
|TYPE_ORIENTATION|傾きセンサ|deg|
|TYPE_PRESSURE|圧力センサ|hPa|
|TYPE_PROXIMITY|近接センサー|cm|
|TYPE_TEMPERATURE|温度センサー|℃|
|**Android 2.3から**||
|TYPE_GRAVITY|重力センサ|m/s^2|
|TYPE_LINEAR_ACCELERATION|直線化速度センサー|m/s^2|
|TYPE_ROTATION_VECTOR|回転ベクトルセンサ||
|**Android 4.0から**||
|TYPE_AMBIENT_TEMPERATURE|温度センサー|℃|
|TYPE_RELATIVE_HUMIDITY|湿度センサー|%

## Sensorの使用方法
Sensorの値はListenerを通して取得できる。そのためアプリ側で各Listenerの登録が必要。  

```java
private SensorManager sensorManager; //センサーマネージャ
private LocationManager gpsManager; //GPSマネージャ
private Sensor accelerometer;    //加速度せンサー
private Sensor orientation; //回転せンサー
private Sensor light;        //照度センサー

...

// センサの取得
sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
gpsManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

// 各センサーを取得
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

// Listenerの登録
sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
sensorManager.registerListener(this, orientation, SensorManager.SENSOR_DELAY_UI);
sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_UI);
gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
```

またGPSはAndroidManifestでPermissionの追加と、Android6.0以降を対象にパーミッションリクエストが必須

##### AndroidManifest.xml

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

##### 実行時のパーミッションリクエスト
[実行時のパーミッションリクエスト Android Developers](https://developer.android.com/training/permissions/requesting.html?hl=ja)
Android6.0以降では、GPSなど一部の機能の実行時にパーミッションリクエストが必要
(ユーザにこの機能を使ってもいいか？を確認する)

```java
// 1. 実行したい機能のパーミッションが許可されているか？確認
if (ActivityCompat.checkSelfPermission(Context, Manifest.permission.{実行したい機能}) != PackageManager.PERMISSION_GRANTED) {
    // 2. 許可されていないので、パーミッションリクエストをすべきか確認する (以前既にしているかどうか)
    if (ActivityCompat.shouldShowRequestPermissionRationale(Context, Manifest.permission.{実行したい機能}))) {
        // 3.a 既にリクエストをしていた
    } else {
        // 3.b まだリクエストをしてないので、する
        requestPermissions(new String[]{実行したい機能}, REQUEST_CODE_PERMISSION);
    }
}
```

### Listenerの解除
Listenerは必ず解除が必要。解除を忘れるとアプリを終了してもセンサだけが動き続ける場合があるので注意

```java
// Listenerの解除
sensorManager.unregisterListener(this);
gpsManager.removeUpdates(this);
```

## SensorEventListener
Sensorの値が更新されてるたびにこのクラスのメソッドを通して値が受け取れる。  
このクラスをimplementsすると以下のメソッドがOverrideされる。  

- onSensorChanged(SensorEvent event)
- onAccuracyChanged(Sensor sensor, int accuracy)

```java
@Override
public void onSensorChanged(SensorEvent event) {
    if (event.sensor == accelerometer) {
        accelerometerX = event.values[0];
        accelerometerY = event.values[1];
        accelerometerZ = event.values[2];
    } else if (event.sensor == orientation) {
        orientation = event.values[0];
        orientation = event.values[1];
        orientation = event.values[2];
    } else if (event.sensor == light) {
        light = event.values[0];
    }
}
```

### SensorEventListener2
Android 4.4からはSensorEventListener2がある。これは従来より消費電力が抑えられ、また画面がオフでもセンサーイベントが取得できるようになった。  

## LocationListener
Locationの値が更新されるたびにこのクラスのメソッドを通して値が受け取れる。  
このクラスをimplementsすると以下のメソッドがOverrideされる。  

- onLocationChanged(Location location)
- onStatusChanged(String provider, int status, Bundle extras)
- onProviderEnabled(String provider)
- onProviderDisabled(String provider)

```java
@Override
public void onLocationChanged(Location location) {
    lat = location.getLatitude();
    lng = location.getLongitude();
}
```

# Support Library
新しいAPIを昔のプラットフォームでも動かせるようにしたもの。  
よく使うのはandroid.support.v4とandroid.support.v7  
v4とかv7はAPI4，7以上の端末なら動きますよってこと  

# Support Design Library
Marshmallow以降ではデフォルトになっているMaterial Designを昔のプラットフォームでも適応させるためのもの  

- Snackbar
- Floating Action Button
- Floating labels for editing text
- Navigation View
- Tabs
- CoordinatorLayout and etc..
- Collapsing Toolbars

# ConstraintLayout
LinearLayoutやRelativeLayoutにつぐ、新たに登場したレイアウト  
View制約を付与する事で、配置を決める事ができる  
また、レイアウトエディタを用いてGUI上で簡単に組むことができる

LinearLayoutに比べてネストが深くなりづらく、RelativeLayoutに比べてパフォーマンスが良いという利点がある  
RelativeLayoutの代替となる日も近いかもしれない

### 参考
- [Build a Responsive UI with ConstraintLayout](https://developer.android.com/training/constraint-layout/)
- [Yukiの技折 ConstraintLayout](http://yuki312.blogspot.jp/2017/03/constraintlayout.html)

# RecyclerView
ListViewにかわる新たなリストのコンポーネント  
ListViewはすこし凝ったことをしようとするとすぐに限界がくる。  
RecyclerViewはListViewをより自由にカスタマイズできるもの  
しかし、今までListViewの内部で実装されていたものが実装されていないので、はじめは辛いかもしれない。  
例えば、OnItemClickListenerとかデフォルトではItem間のdividerがなかったりとか  

```java
mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
mRecyclerView.setAdapter(adapter);
```

しかし横スクロールのViewであったりGridのListViewであったりとかのレイアウトに対してのカスタマイズは協力である。  
上のコードのsetLayoutManagerの部分でGridLayoutManagerにしたり、カスタムLayoutManagerにしたりと簡単に変えることができる。

また、カスタマイズしたい部分のみカスタマイズできる点は非常に協力だと思う。  

## RecyclerViewはListViewの代替じゃない？
参考<http://sys1yagi.hatenablog.com/entry/2015/01/09/090000>

# ベストプラクティス
<https://github.com/futurice/android-best-practices/blob/master/translations/Japanese/README.ja.md>

# Core ライブラリ
<https://github.com/wasabeef/awesome-android-libraries>

# Material Design
<https://www.google.com/design/spec/material-design/introduction.html>

# Material icons
<https://design.google.com/icons/>

# RxJava/RxAndroid and RecyclerView
<https://github.com/cpslab/GoldenProfiles>

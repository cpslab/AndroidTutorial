package jp.ac.dendai.im.cps.iwitutorial.sampleviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import jp.ac.dendai.im.cps.iwitutorial.DroidSan;
import jp.ac.dendai.im.cps.iwitutorial.R;

/**
 * Created by naoya on 2015/04/11.
 */
public class SampleSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    final String TAG = "SampleSurfaceView";

    private DroidSan droidsan;
    private SurfaceHolder mHolder;
    private Thread looper;

    private int mHeight;
    private int mWidth;

    private float[] accelerometer = new float[3];

    private long mTime = 0;

    public SampleSurfaceView(Context context) {
        super(context);

        init();
    }

    public SampleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SampleSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SampleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    public void init() {
        getHolder().addCallback(this);
        droidsan = new DroidSan(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
        looper = new Thread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (looper != null) {
            mHeight = height;
            mWidth = width;
            droidsan.setPositionLimitX(mWidth);
            droidsan.setPositionLimitY(mHeight);
            mTime = System.currentTimeMillis();
            looper.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        looper = null;
    }

    @Override
    public void run() {
        while (looper != null) {
            doDraw();

            long delta = System.currentTimeMillis() - mTime;
            mTime = System.currentTimeMillis();
            droidsan.move(accelerometer, delta);
        }
    }

    // 描画処理をまとめて抜き出したメソッド
    private void doDraw() {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas == null) return;
        Paint paint = new Paint();
        canvas.drawColor(Color.WHITE);
//        canvas.drawText();
        canvas.drawBitmap(droidsan.getBitmap(),
                droidsan.getPositionX(),
                droidsan.getPositionY(),
                paint);

        mHolder.unlockCanvasAndPost(canvas);
    }

    public void setAccelerometer(float[] accelerometer) {
        this.accelerometer = accelerometer;
    }
}

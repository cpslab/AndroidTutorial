package jp.ac.dendai.im.cps.iwitutorial;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SampleGraphicsView extends View {

    final static String TAG = "GraphicsView";

    Paint paint;
    float lightValue;

    public SampleGraphicsView(Context context) {
        super(context);

        init();
    }

    public SampleGraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SampleGraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SampleGraphicsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    //コンストラクタが複数あるため初期化処理をまとめる
    public void init() {
        //バックグラウンドの色を指定
        setBackgroundColor(Color.BLACK);

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        //描画オブジェクトの生成
        paint.setAntiAlias(true);

        //ログの吐き出し
        //第一引数はタグ、第二引数は表示させたいメッセージ
        //第一引数は23文字までしか入力できない
        Log.d(TAG, "draw method called");

        if (lightValue > 255f) {
            lightValue = 255f;
        }
        int alpha = 255 - (int) lightValue;

        //ログの吐き出し
        //第一引数はタグ、第二引数は表示させたいメッセージ
        //第一引数は23文字までしか入力できない
        Log.d(TAG, "Alpha : " + Integer.toString(alpha));

        //ラインの描画
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(alpha, 255, 0, 0));
        canvas.drawLine(50,10,50,10+80,paint);

        //パスの描画
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(alpha,255,0,0));
        Path path=new Path();
        path.moveTo(110+ 0,10+ 0);
        path.lineTo(110+60,10+10);
        path.lineTo(110+20,10+40);
        path.lineTo(110+80,10+50);
        path.lineTo(110+ 0,10+80);
        canvas.drawPath(path,paint);

        //四角形の描画
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(alpha,0,0,255));
        canvas.drawRect(new Rect(10+0,100+0,10+80,100+80),paint);

        //四角形の塗り潰し
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(alpha,0,0,255));
        canvas.drawRect(new Rect(110+0,100+0,110+80,100+80),paint);

        //角丸矩形の描画
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(alpha,0,255,0));
        canvas.drawRoundRect(new RectF(10+0,200+0,10+80,200+80),20,20,paint);

        //角丸矩形の塗り潰し
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(alpha,0,255,0));
        canvas.drawRoundRect(new RectF(110+0,200+0,110+80,200+80),20,20,paint);

        //円の描画
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.argb(alpha,255,255,0));
        canvas.drawCircle(50,340,40,paint);

        //円の塗り潰し
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(alpha,255,255,0));
        canvas.drawCircle(150,340,40,paint);
    }

    public void draw(float lightValue) {
        this.lightValue = lightValue;

        //onDrawメソッドの呼び出し
        this.invalidate();
    }

}

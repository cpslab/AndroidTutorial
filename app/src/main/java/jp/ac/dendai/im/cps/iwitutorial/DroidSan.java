package jp.ac.dendai.im.cps.iwitutorial;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by naoya on 2015/04/11.
 */
public class DroidSan {

    final String TAG = "DroidSan";

    private Bitmap bitmap;
    private int accelerationX;
    private int accelerationY;
    private int positionX;
    private int positionY;
    private int accelerationLimitX = 500;
    private int accelerationLimitY = 500;
    private int positionLimitX;
    private int positionLimitY;

    public int getPositionLimitY() {
        return positionLimitY;
    }

    public void setPositionLimitY(int positionLimitY) {
        this.positionLimitY = positionLimitY - bitmap.getHeight();
    }

    public int getPositionLimitX() {
        return positionLimitX;
    }

    public void setPositionLimitX(int positionLimitX) {
        this.positionLimitX = positionLimitX - bitmap.getWidth();
    }

    public int getAccelerationX() {
        return accelerationX;
    }

    public int getAccelerationY() {
        return accelerationY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public DroidSan(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void move(float[] accelerometer, long delta) {
        accele(accelerometer);
        positionX += (int) ((delta / 1000.0) * accelerationX);
        positionY += (int) ((delta / 1000.0) * accelerationY);

        // 画像が画面外へ出ないようにposition調整
        if (positionX < 0) {
            positionX = 0;
        } else if (positionX > positionLimitX) {
            positionX = positionLimitX;
        }

        if (positionY < 0) {
            positionY = 0;
        } else if (positionY > positionLimitY) {
            positionY = positionLimitY;
        }

        StringBuilder message = new StringBuilder();
        message.append("accelerationX ").append(String.valueOf(accelerationX)).append(", ")
            .append("accelerationY ").append(String.valueOf(accelerationY)).append("\n")
            .append("positionX ").append(String.valueOf(positionX)).append(", ")
            .append("positionY ").append(String.valueOf(positionY));

        Log.d(TAG, message.toString());
    }

    private void accele(float[] accelerometer) {
        accelerationX += accelerometer[0] < 0 ? 10 : -10;
        accelerationY += accelerometer[1] < 0 ? -10 : 10;

        if (accelerationX < -accelerationLimitX) {
            accelerationX = -accelerationLimitX;
        } else if (accelerationX > accelerationLimitX) {
            accelerationX = accelerationLimitX;
        }

        if (accelerationY > accelerationLimitY) {
            accelerationY = accelerationLimitY;
        } else if (accelerationY < -accelerationLimitY) {
            accelerationY = -accelerationLimitY;
        }
    }
}

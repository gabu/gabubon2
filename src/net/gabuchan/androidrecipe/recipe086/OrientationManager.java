
package net.gabuchan.androidrecipe.recipe086;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;

/**
 * Sensor.TYPE_ORIENTATION の代わりに傾きを計算します。
 * 
 * @author gabu
 */
public class OrientationManager implements SensorEventListener {
    // 画面の回転
    private int mDisplayRotation;
    // SensorManagerオブジェクト
    private SensorManager mSensorManager;
    // 地磁気センサーの値
    private float[] mMagneticValues;
    // 加速度センサーの値
    private float[] mAccelerometerValues;
    // 傾きが取れた時のリスナー
    private OnOrientationChangedListener mListener;

    /**
     * 画面の回転ナシとして OrientationManager オブジェクトを生成します。
     * 
     * @param sensorManager SensorManager オブジェクト
     */
    public OrientationManager(SensorManager sensorManager) {
        this(sensorManager, Surface.ROTATION_0);
    }

    /**
     * 指定された画面の回転を使う OrientationManager オブジェクトを生成します。
     * getWindowManager().getDefaultDisplay().getRotation() で取得できる値を想定しています。
     * 
     * @param sensorManager SensorManager オブジェクト
     * @param displayRotaion 画面の回転
     */
    public OrientationManager(SensorManager sensorManager, int displayRotaion) {
        mSensorManager = sensorManager;
        mDisplayRotation = displayRotaion;
        // 地磁気センサーにリスナーを登録
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
        // 加速度センサーにリスナーを登録
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 傾きが計算できた時に呼び出されるリスナーをセットします。
     * 
     * @param listener OnOrientationChangedListener オブジェクト
     */
    public void setOnOrientationChangedListener(OnOrientationChangedListener listener) {
        mListener = listener;
    }

    /**
     * 内部で使っているリスナーを SensorManager から解除します。
     */
    public void release() {
        // リスナーを解除
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD:
                // 地磁気センサーの値をコピー
                mMagneticValues = event.values.clone();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                // 加速度センサーの値をコピー
                mAccelerometerValues = event.values.clone();
                break;
        }
        if (mMagneticValues == null || mAccelerometerValues == null) {
            // 両方の値が揃うまで何もしない
            return;
        }
        // 回転行列Rを取得
        float[] R = new float[16];
        SensorManager.getRotationMatrix(R, null, mAccelerometerValues, mMagneticValues);

        // 傾きを取得
        float[] values = new float[3];
        getOrientation(R, values);

        if (mListener != null) {
            // リスナーがセットされていれば呼ぶ
            mListener.onOrientationChanged(values);
        }
    }

    private void getOrientation(float[] inR, float[] values) {
        int x_axis = SensorManager.AXIS_X;
        int y_axis = SensorManager.AXIS_Y;

        // 画面の回転によって軸を変える
        switch (mDisplayRotation) {
            case Surface.ROTATION_0:
                break;
            case Surface.ROTATION_90:
                x_axis = SensorManager.AXIS_Y;
                y_axis = SensorManager.AXIS_MINUS_X;
                break;
            case Surface.ROTATION_180:
                y_axis = SensorManager.AXIS_MINUS_Y;
                break;
            case Surface.ROTATION_270:
                x_axis = SensorManager.AXIS_MINUS_Y;
                y_axis = SensorManager.AXIS_X;
                break;
        }

        float[] outR = new float[16];
        // 回転行列を変換
        SensorManager.remapCoordinateSystem(inR, x_axis, y_axis, outR);
        // 傾きを取得
        SensorManager.getOrientation(outR, values);
    }

    /**
     * 傾きが計算できた時に呼び出されるリスナーです。
     * 
     * @author gabu
     */
    public interface OnOrientationChangedListener {
        /**
         * 傾きが計算できた時に呼び出されます。
         * 
         * @param values 傾き [0]:azimuth [1]:pitch [2]:roll
         */
        void onOrientationChanged(float[] values);
    }
}

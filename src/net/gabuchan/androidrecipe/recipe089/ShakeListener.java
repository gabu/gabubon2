
package net.gabuchan.androidrecipe.recipe089;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeListener implements SensorEventListener {
    private SensorManager mSensorManager;
    private OnShakeListener mListener;
    private long mPreTime;
    private float mLastX;
    private float mLastY;
    private float mLastZ;
    private int mShakeCount;

    /**
     * シェイクを感知した時にonShakeメソッドを呼び出します。 
     * setOnShakeListenerメソッドでセットしてください。
     * 
     * @author gabu
     */
    public interface OnShakeListener {
        /**
         * シェイクを感知した時に呼び出されます。
         */
        void onShake();
    }

    public void setOnShakeListener(OnShakeListener listener) {
        mListener = listener;
    }

    public ShakeListener(SensorManager sensorManager) {
        mSensorManager = sensorManager;
        // 加速度センサーを取得
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // 加速度センサーにリスナーを登録
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void release() {
        // リスナーを解除
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    // センサーの値が変わったら呼び出される
    public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        long diffTime = curTime - mPreTime;
        // 物凄い頻度でイベントが発生するので
        // 100msに1回計算するように間引く
        if (diffTime > 100) {
            // 現在の値をとって
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            // 前回の値との差からスピードを計算
            float speed = Math.abs(x + y + z - mLastX - mLastY - mLastZ)
                    / diffTime * 10000;
            // スピードが300以上なら（お好みで変えてください）
            if (speed > 300) {
                // シェイクカウントをインクリメント
                mShakeCount++;
                // 4回連続スピードが300以上なら
                // シェイクと認定（お好みで変えてください）
                if (mShakeCount > 3) {
                    mShakeCount = 0;
                    // リスナーがセットされていれば
                    if (mListener != null) {
                        // onShakeメソッドを呼び出す
                        mListener.onShake();
                    }
                }
            } else {
                // 300以下ならリセット
                mShakeCount = 0;
            }
            // 前回値として保存
            mPreTime = curTime;
            mLastX = x;
            mLastY = y;
            mLastZ = z;
        }
    }
}

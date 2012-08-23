
package net.gabuchan.androidrecipe.recipe084;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class Recipe084Activity extends Activity {
    private SensorManager mSensorManager;
    private float mPreValue = -1;

    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // 近接センサーの値
            float value = event.values[0];

            // 初回は値を入れるだけで何もしない
            if (mPreValue == -1) {
                mPreValue = value;
                return;
            }

            if (value < mPreValue) {
                // 前回の値より小さければ近づいたということ
                showToast("近づいた！ " + value);
            } else {
                // 前回の値より大きければ離れたということ
                showToast("離れた！ " + value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_084);

        // SensorManagerを取得
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 近接センサーを取得
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // センサーがない場合
        if (sensor == null) {
            Toast.makeText(this, "近接センサーが見つかりませんでした＞＜", Toast.LENGTH_SHORT).show();
        }
        // リスナーを登録
        mSensorManager.registerListener(mListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // リスナーを解除
        mSensorManager.unregisterListener(mListener);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

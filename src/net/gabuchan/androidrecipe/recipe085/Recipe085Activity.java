
package net.gabuchan.androidrecipe.recipe085;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe085Activity extends Activity {
    private SensorManager mSensorManager;
    private TextView mTextView;

    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // 明るさセンサーの値
            float value = event.values[0];

            mTextView.setText("ただいまの明るさ = " + value);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_085);

        // SensorManagerを取得
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // TextViewを取得
        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 明るさセンサーを取得
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        // リスナーを登録
        mSensorManager.registerListener(mListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // リスナーを解除
        mSensorManager.unregisterListener(mListener);
    }
}

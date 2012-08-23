
package net.gabuchan.androidrecipe.recipe083;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe083Activity extends Activity {
    private SensorManager mSensorManager;
    private TextView mTextView;

    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // X方向
            float x = event.values[0];
            // Y方向
            float y = event.values[1];
            // Z方向
            float z = event.values[2];

            StringBuffer sb = new StringBuffer();
            sb.append("x = " + x);
            sb.append("\n");
            sb.append("y = " + y);
            sb.append("\n");
            sb.append("z = " + z);
            mTextView.setText(sb);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_083);

        // 表示用のTextViewを取得
        mTextView = (TextView) findViewById(R.id.text_view);
        // SensorManagerを取得
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 加速度センサーを取得
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // センサーがない場合
        if (sensor == null) {
            Toast.makeText(this, "加速度センサーが見つかりませんでした＞＜", Toast.LENGTH_SHORT).show();
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
}

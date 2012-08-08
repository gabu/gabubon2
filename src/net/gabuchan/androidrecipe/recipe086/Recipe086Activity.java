
package net.gabuchan.androidrecipe.recipe086;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.recipe086.OrientationManager.OnOrientationChangedListener;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe086Activity extends Activity {
    private OrientationManager mOrientationManager;
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_086);

        // TextViewを取得
        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // SensorManagerを取得して
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 画面の回転を取得して
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        // OrientationManagerを生成
        mOrientationManager = new OrientationManager(sensorManager, rotation);
        // 傾きが取得できた時のリスナーをセット
        mOrientationManager.setOnOrientationChangedListener(new OnOrientationChangedListener() {
            @Override
            public void onOrientationChanged(float[] values) {
                // 傾きが取得できた時に呼び出される
                StringBuffer sb = new StringBuffer();
                sb.append("azimuth = " + Math.toDegrees(values[0]));
                sb.append("\n");
                sb.append("pitch = " + Math.toDegrees(values[1]));
                sb.append("\n");
                sb.append("roll = " + Math.toDegrees(values[2]));
                // TextViewに描画
                mTextView.setText(sb);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // OrientationManagerを解放
        mOrientationManager.release();
    }
}

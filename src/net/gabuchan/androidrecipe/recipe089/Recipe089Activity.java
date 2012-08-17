
package net.gabuchan.androidrecipe.recipe089;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.recipe089.ShakeListener.OnShakeListener;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe089Activity extends Activity {
    private ShakeListener mShakeListener;
    private TextView mTextView;
    private int mCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_089);

        // 表示用のTextViewを取得
        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // SensorManagerを取得して
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // ShakeListenerを作って
        mShakeListener = new ShakeListener(sensorManager);
        mShakeListener.setOnShakeListener(new OnShakeListener() {
            @Override
            public void onShake() {
                // シェイクを検知した時に呼び出される
                mCount++;
                mTextView.setText(""+mCount);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ShakeListenerを解放
        mShakeListener.release();
    }
}

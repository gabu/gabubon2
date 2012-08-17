
package net.gabuchan.androidrecipe.recipe089;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.recipe089.ShakeListener.OnShakeListener;
import android.app.Activity;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe089Activity extends Activity {
    // シェイクリスナー
    private ShakeListener mShakeListener;
    private TextView mTextView;
    private int mCount;
    // サウンドプール
    private SoundPool mSoundPool;
    // サウンドID
    private int mSoundId;

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
        // SoundPoolを生成
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        // ロードしてサウンドIDをフィールドに保持
        mSoundId = mSoundPool.load(this, R.raw.sound, 1);

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
                mSoundPool.play(mSoundId, 1, 1, 0, 0, 1.0f);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // SoundPoolのロードしたデータをメモリから解放
        mSoundPool.release();
        // ShakeListenerを解放
        mShakeListener.release();
    }
}

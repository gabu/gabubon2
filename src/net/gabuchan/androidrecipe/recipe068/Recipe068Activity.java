
package net.gabuchan.androidrecipe.recipe068;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class Recipe068Activity extends Activity {
    // サウンドプール
    SoundPool mSoundPool;
    // サウンドID
    int mSoundId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_068);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // SoundPoolを生成
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        // ロードしてサウンドIDをフィールドに保持
        mSoundId = mSoundPool.load(this, R.raw.sound, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // SoundPoolのロードしたデータをメモリから解放
        mSoundPool.release();
    }

    public void onButtonClick(View view) {
        float rate = 1;
        switch (view.getId()) {
            case R.id.sound_slow:
                rate = 0.5f;
                break;
            case R.id.sound_high:
                rate = 2.0f;
                break;
        }
        // 再生！
        mSoundPool.play(mSoundId, 1, 1, 0, 0, rate);
    }
}

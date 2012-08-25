
package net.gabuchan.androidrecipe.recipe069;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Recipe069Activity extends Activity {
    // メディアプレイヤー
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_069);

        // MediaPlayerを生成
        mMediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mMediaPlayer.setLooping(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onResume()で再生しちゃうと
        // ロックスクリーンの時点で再生されてしまうので
        // onWindowFocusChanged()で再生するよ
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // 音声再生をスタート
            mMediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 再生中なら一時停止
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解放
        mMediaPlayer.release();
    }
}

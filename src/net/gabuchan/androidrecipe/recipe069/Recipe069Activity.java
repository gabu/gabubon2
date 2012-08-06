
package net.gabuchan.androidrecipe.recipe069;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;

public class Recipe069Activity extends Activity {
    // メディアプレイヤー
    MediaPlayer mMediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_069);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // MediaPlayerを生成
        mMediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 再生中なら停止
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        // 解放
        mMediaPlayer.release();
    }
}


package net.gabuchan.androidrecipe.recipe074;

import net.gabuchan.androidrecipe.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

@TargetApi(9)
public class Recipe074Activity extends Activity {
    // メディアプレイヤー
    private MediaPlayer mMediaPlayer;

    // イコライザ
    private Equalizer mEqualizer;

    // イコライザを表示するView
    private LinearLayout mLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_074);

        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // MediaPlayerを生成
        mMediaPlayer = MediaPlayer.create(this, R.raw.sound);
        // 無限ループ再生
        mMediaPlayer.setLooping(true);

        // イコライザを準備
        setupEqualizerFxAndUI();

        // 音声再生をスタート
        mMediaPlayer.start();
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
        mEqualizer.release();
    }

    private void setupEqualizerFxAndUI() {
        // イコライザを作って
        mEqualizer = new Equalizer(0, mMediaPlayer.getAudioSessionId());
        // 有効にして
        mEqualizer.setEnabled(true);
        // バンドの数を取得
        short bands = mEqualizer.getNumberOfBands();
        // バンドの最小値
        final short minEQLevel = mEqualizer.getBandLevelRange()[0];
        // バンドの最大値
        final short maxEQLevel = mEqualizer.getBandLevelRange()[1];
        // バンドの数だけシークバーを作るためにループ
        for (short i = 0; i < bands; i++) {
            final short band = i;
            // Hzを表示する
            TextView freqTextView = new TextView(this);
            freqTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            freqTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            freqTextView.setText((mEqualizer.getCenterFreq(band) / 1000) + " Hz");
            mLinearLayout.addView(freqTextView);

            // ここからシークバー
            // -15 dB <--- シークバー ---> 15dB
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            // 左側の dB
            TextView minDbTextView = new TextView(this);
            minDbTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            minDbTextView.setText((minEQLevel / 100) + " dB");

            // 右側の dB
            TextView maxDbTextView = new TextView(this);
            maxDbTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            maxDbTextView.setText((maxEQLevel / 100) + " dB");

            // シークバー
            SeekBar bar = new SeekBar(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            bar.setLayoutParams(layoutParams);
            // シークバーの最大値
            bar.setMax(maxEQLevel - minEQLevel);
            // シークバーの現在値
            bar.setProgress(mEqualizer.getBandLevel(band));
            // リスナー
            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress,
                        boolean fromUser) {
                    // バンドレベルをセット！
                    mEqualizer.setBandLevel(band, (short) (progress + minEQLevel));
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            // 左、真ん中、右と追加して
            row.addView(minDbTextView);
            row.addView(bar);
            row.addView(maxDbTextView);

            // これで1行完成
            mLinearLayout.addView(row);
        }
    }
}

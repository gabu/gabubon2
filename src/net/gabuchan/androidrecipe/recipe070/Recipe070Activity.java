
package net.gabuchan.androidrecipe.recipe070;

import java.io.File;
import java.io.IOException;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Recipe070Activity extends Activity {
    // メディアレコーダー
    MediaRecorder mRecorder;
    // 録音中フラグ
    boolean isRecording = false;
    // 録音ボタン
    Button mRecordButton;
    // 再生ボタン
    Button mPlayButton;
    // 録音した音声ファイルのパス
    String mPath;
    // 確認用のメディアプレイヤー
    MediaPlayer mPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_070);

        // ボタンを取得しておく
        mRecordButton = (Button) findViewById(R.id.record_button);
        mPlayButton = (Button) findViewById(R.id.play_button);

        // プレイヤー準備
        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                // 再生！
                player.start();
                mPlayButton.setText(R.string.now_playing);
            }
        });
        mPlayer.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.reset();
                mPlayButton.setText(R.string.play);
            }
        });
    }

    public void onRecordingClick(View view) {
        if (isRecording) {
            // 録音中だったら
            isRecording = false; // 録音中フラグを解除
            // ボタンのテキストを変更
            mRecordButton.setText(R.string.recording_start);
            stopRecording();
        } else {
            // 録音中じゃなかったら
            isRecording = true; // 録音中フラグをセット
            // ボタンのテキストを変更
            mRecordButton.setText(R.string.recording_stop);
            // 録音スタート！
            startRecording();
        }
    }

    public void onPlayClick(View view) {
        if (mPath == null) {
            showToast("録音してから再生してください。");
            return;
        }
        if (mPlayer.isPlaying()) {
            showToast("再生中です。");
            return;
        }
        try {
            // ファイルパスをセットして
            mPlayer.setDataSource(mPath);
            // 準備 -> onPreparedで再生される
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startRecording() {
        // 保存先のディレクトリのFileオブジェクトを生成
        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PODCASTS), "gabubon2");
        // ディレクトリがなければ作成する
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                showToast("SDカードにディレクトリが作成できませんでした。");
            }
        }
        // ファイル名を時間から生成
        String fileName = System.currentTimeMillis() + ".3gp";
        // ディレクトリとファイル名を繋げてFileオブジェクトを作る
        File file = new File(dir, fileName);
        // 出力ファイルのパス
        mPath = file.getAbsolutePath();

        mRecorder = new MediaRecorder();
        // 入力ソースにマイクを指定
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 出力フォーマットに3gpを指定
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 音声エンコーダにAMRを指定
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // 出力ファイルのパスを指定
        mRecorder.setOutputFile(mPath);
        try {
            // 準備して
            mRecorder.prepare();
            // 録音スタート！
            mRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        // 録音を停止して
        mRecorder.stop();
        // 解放
        mRecorder.release();
        // トースト表示
        showToast(mPath + "に保存しました。");
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

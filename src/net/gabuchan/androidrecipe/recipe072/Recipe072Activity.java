
package net.gabuchan.androidrecipe.recipe072;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Recipe072Activity extends Activity {
    private static final int REQUEST_CODE = 1;

    private VideoView mVideoView;
    private MediaController mMediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_072);

        // VideoViewを取得
        mVideoView = (VideoView) findViewById(R.id.video_view);
        // MediaControllerを作って
        mMediaController = new MediaController(this);
        // MediaControllerをセット
        mVideoView.setMediaController(mMediaController);
        // VideoViewで動画を再生する準備ができた時に
        // 呼び出されるリスナー
        mVideoView.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                // 3,4秒でMediaControllerが消えちゃうので
                // 常に表示させておきたいんだけど
                // showメソッドが期待通りに動作してくれない
                // durationは正しく取得できてる。
                int duration = mVideoView.getDuration();
                mMediaController.show(duration);
                // リファレンスを信じて0にしてもダメ
                // mMediaController.show(0);

                // まぁ気にせず再生スタート！
                mVideoView.start();
            }
        });
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // 選択された動画のUriを取得
            Uri uri = data.getData();
            // VideoViewにセット
            mVideoView.setVideoURI(uri);

            // // ACTION_VIEWなIntentを作って
            // Intent intent = new Intent(Intent.ACTION_VIEW);
            // // Uriをセット
            // intent.setData(uri);
            // startActivity(intent);
        }
    }
}

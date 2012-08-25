
package net.gabuchan.androidrecipe.recipe076;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.view.CameraPreview;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Recipe076Activity extends Activity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_076);

        // CameraPreviewを生成
        mCameraPreview = new CameraPreview(this);
        // LinearLayoutに乗っける
        LinearLayout container = (LinearLayout) findViewById(R.id.preview_container);
        container.addView(mCameraPreview);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // カメラを取得
        mCamera = Camera.open();
        // カメラがなかったら
        if (mCamera == null) {
            Toast.makeText(this, "カメラが搭載されていないか接続できませんでした。",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        // 今回は縦固定なのでプレビューを90度回転する
        mCamera.setDisplayOrientation(90);
        // CameraPreviewにCameraを渡してあげる
        mCameraPreview.setCamera(mCamera);
        // プレビュースタート
        mCamera.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            // カメラを解放
            mCamera.release();
        }
    }

    public void onCaptureClick(View view) {
        Toast.makeText(this, "このボタンは以降のレシピで使うので今はダミーです。", Toast.LENGTH_SHORT).show();
    }
}

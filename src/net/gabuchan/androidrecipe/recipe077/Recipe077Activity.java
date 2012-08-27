
package net.gabuchan.androidrecipe.recipe077;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.view.CameraPreview;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Recipe077Activity extends Activity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_077);

        // CameraPreviewを生成
        mCameraPreview = new CameraPreview(this);
        // RelativeLayoutに乗っける
        RelativeLayout container = (RelativeLayout) findViewById(R.id.preview_container);
        container.addView(mCameraPreview);

        // ImageViewを真ん中に表示してみる
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.gabu);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        container.addView(imageView, params);
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

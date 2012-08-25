
package net.gabuchan.androidrecipe.recipe077;

import net.gabuchan.androidrecipe.R;
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus == false) {
            // フォーカスを失う時は何もしない
            return;
        }
        // RelativeLayoutに
        RelativeLayout container = (RelativeLayout) findViewById(R.id.preview_container);
        // OverlayViewを
        OverlayView overlayView = new OverlayView(this,
                container.getWidth(),
                container.getHeight());
        // 追加して重ねる！
        container.addView(overlayView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // カメラを取得
        mCamera = Camera.open();
        mCameraPreview.setCamera(mCamera);
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

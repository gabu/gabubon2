
package net.gabuchan.androidrecipe.recipe076;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.LinearLayout;

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
}

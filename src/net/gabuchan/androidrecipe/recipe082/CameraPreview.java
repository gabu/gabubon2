
package net.gabuchan.androidrecipe.recipe082;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = CameraPreview.class.getSimpleName();
    private Camera mCamera;

    public CameraPreview(Context context) {
        super(context);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        // 非推奨メソッドだけどAndroid 3.0未満のためにもセット
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void setCamera(Camera camera) {
        mCamera = camera;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // カメラのプレビューストップ
        mCamera.stopPreview();
        // 今回は縦固定なので90度回転
        mCamera.setDisplayOrientation(90);

        // Surfaceのサイズを出してみる
        Log.d(TAG, String.format("Before width=%d, height=%d", width, height));

        // プレビューサイズをログに出してみる
        // Galaxy Nexus PreviewSize width=640, height=480
        Size size = mCamera.getParameters().getPreviewSize();
        Log.d(TAG, String.format("PreviewSize width=%d, height=%d", size.width, size.height));

        // プレビューサイズのアスペクト比を計算して
        float ratio = size.width / (float) size.height;
        // Surfaceのサイズを補正
        height = (int) (width * ratio);
        // LayoutParamsをセットし直す
        LayoutParams params = getLayoutParams();
        // 補正後のSurfaseのサイズ
        Log.d(TAG, String.format("New width=%d, height=%d", width, height));
        params.width = width;
        params.height = height;
        // 補正後のLayoutParamsをセット
        setLayoutParams(params);

        // カメラのプレビュースタート
        mCamera.startPreview();
    }
}

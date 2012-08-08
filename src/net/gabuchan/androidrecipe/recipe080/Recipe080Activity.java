
package net.gabuchan.androidrecipe.recipe080;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Recipe080Activity extends Activity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    // 選択ダイアログ表示用の文字列の配列
    private String[] mSupportedFlashModes;
    // 現在選ばれているフラッシュモードのインデックス
    private int mCurrentFlashModeIndex;

    // 撮影時のコールバック
    private PictureCallback mPictureCallback = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 保存先のディレクトリのFileオブジェクトを生成
            File dir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "gabubon2");
            // ディレクトリがなければ作成する
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    showToast("SDカードにディレクトリが作成できませんでした。");
                }
            }
            // ファイル名を時間から生成
            String fileName = System.currentTimeMillis() + ".jpg";
            // ディレクトリとファイル名を繋げてFileオブジェクトを作る
            File file = new File(dir, fileName);

            try {
                // FileオブジェクトからFileOutputStreamを作る
                FileOutputStream stream = new FileOutputStream(file);
                // ファイルに書き出す
                stream.write(data);
                stream.close();
                showToast("SDカードに保存しました。" + file.getAbsolutePath());

                // スキャンさせる（ギャラリーに反映が目的）
                MediaScannerConnection.scanFile(Recipe080Activity.this, new String[] {
                        file.toString()
                }, null, null);

                // ギャラリーで見てみる
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "image/jpeg");
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
                showToast("ファイルが保存できませんでした。");
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_080);

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

        // カメラパラメータを取得して
        Parameters params = mCamera.getParameters();
        // 現在のフラッシュモード
        String currentFlashMode = params.getFlashMode();
        // サポートされているフラッシュモードのリストを取得
        List<String> supportedFlashModes = params.getSupportedFlashModes();
        // 選択ダイアログ表示用の文字列の配列を作る
        mSupportedFlashModes = new String[supportedFlashModes.size()];
        for (int i = 0; i < mSupportedFlashModes.length; i++) {
            // 1つ取り出して
            String flashMode = supportedFlashModes.get(i);
            // 文字列の配列に入れて
            mSupportedFlashModes[i] = flashMode;
            // 現在のフラッシュモードと比較して
            if (currentFlashMode.equals(flashMode)) {
                // 選択ダイアログの初期値としてインデックスを覚えておく
                mCurrentFlashModeIndex = i;
            }
        }
        // 撮影された写真も90度回転する
        params.setRotation(90);
        // 90度回転したパラメータをセット
        mCamera.setParameters(params);
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
        // 撮影！
        mCamera.takePicture(null, null, mPictureCallback);
    }

    public void onFlashModeClick(View view) {
        // 選択ダイアログを表示する
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(mSupportedFlashModes,
                        mCurrentFlashModeIndex,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int witch) {
                                // 選択されたインデックスを覚えておく
                                mCurrentFlashModeIndex = witch;
                                // カメラパラメータを取得して
                                Parameters params = mCamera.getParameters();
                                // フラッシュモードを取得して
                                String flashMode = mSupportedFlashModes[witch];
                                // カメラパラメータにフラッシュモードをセット
                                params.setFlashMode(flashMode);
                                // カメラパラメータをカメラにセット
                                mCamera.setParameters(params);
                                // 勝手に閉じないので閉じる
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

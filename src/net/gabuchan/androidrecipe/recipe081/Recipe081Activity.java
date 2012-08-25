
package net.gabuchan.androidrecipe.recipe081;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.view.CameraPreview;
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

public class Recipe081Activity extends Activity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    // 選択ダイアログ表示用の文字列の配列
    private String[] mSupportedFocusModes;
    // 現在選ばれているフォーカスモードのインデックス
    private int mCurrentFocusModeIndex;

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
                MediaScannerConnection.scanFile(Recipe081Activity.this, new String[] {
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
        setContentView(R.layout.activity_recipe_081);

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
        // 現在のフォーカスモード
        String currentFocusMode = params.getFocusMode();
        // サポートされているフォーカスモードのリストを取得
        List<String> supportedFocusModes = params.getSupportedFocusModes();
        // 選択ダイアログ表示用の文字列の配列を作る
        mSupportedFocusModes = new String[supportedFocusModes.size()];
        for (int i = 0; i < mSupportedFocusModes.length; i++) {
            // 1つ取り出して
            String focushMode = supportedFocusModes.get(i);
            // 文字列の配列に入れて
            mSupportedFocusModes[i] = focushMode;
            // 現在のフォーカスモードと比較して
            if (currentFocusMode.equals(focushMode)) {
                // 選択ダイアログの初期値としてインデックスを覚えておく
                mCurrentFocusModeIndex = i;
            }
        }
        // 今回は縦固定なので撮影する写真を90度回転する
        params.setRotation(90);
        // パラメータをセット
        mCamera.setParameters(params);
        // 今回は縦固定なのでプレビューを90度回転する
        mCamera.setDisplayOrientation(90);
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
        // 撮影！
        mCamera.takePicture(null, null, mPictureCallback);
    }

    public void onFocusModeClick(View view) {
        // 選択ダイアログを表示する
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(mSupportedFocusModes,
                        mCurrentFocusModeIndex,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int witch) {
                                // 選択されたインデックスを覚えておく
                                mCurrentFocusModeIndex = witch;
                                // カメラパラメータを取得して
                                Parameters params = mCamera.getParameters();
                                // フォーカスモードを取得して
                                String focusMode = mSupportedFocusModes[witch];
                                // カメラパラメータにフォーカスモードをセット
                                params.setFocusMode(focusMode);
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

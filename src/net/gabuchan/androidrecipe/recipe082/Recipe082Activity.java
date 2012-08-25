
package net.gabuchan.androidrecipe.recipe082;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.gabuchan.androidrecipe.R;
import net.gabuchan.androidrecipe.view.CameraPreview;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

@TargetApi(9)
public class Recipe082Activity extends Activity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    // サポートされている撮影サイズのリスト
    private List<Size> mSupportedPictureSizes;
    // 選択ダイアログ表示用の文字列の配列
    private String[] mSupportedPictureSizeNames;
    // 現在選ばれている撮影サイズのインデックス
    private int mCurrentPictureSizeIndex;

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
                // 左右反転するBitmapを作って
                Bitmap bitmap = createMirrorBtimap(data);
                // ファイルに書き出す
                boolean success = bitmap.compress(CompressFormat.JPEG, 100, stream);
                stream.close();
                if (success) {
                    showToast("SDカードに保存しました。" + file.getAbsolutePath());

                    // スキャンさせる（ギャラリーに反映が目的）
                    MediaScannerConnection.scanFile(Recipe082Activity.this, new String[] {
                            file.toString()
                    }, null, null);

                    // ギャラリーで見てみる
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "image/jpeg");
                    startActivity(intent);
                } else {
                    showToast("ファイルが保存できませんでした。");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showToast("ファイルが保存できませんでした。");
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_078);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            Toast.makeText(this, "このレシピは、API Level 9(Android 2.3)からのみ使えます。",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // CameraPreviewを生成
        mCameraPreview = new CameraPreview(this);
        // LinearLayoutに乗っける
        LinearLayout container = (LinearLayout) findViewById(R.id.preview_container);
        container.addView(mCameraPreview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Camera.getNumberOfCameras() < 2) {
            showToast("このレシピは、フロントカメラがある端末のみ使えます。");
            finish();
            return;
        }

        // フロントカメラを取得
        mCamera = Camera.open(1);

        // カメラパラメータを取得して
        Parameters params = mCamera.getParameters();
        // 現在の撮影サイズ
        Size currentPictureSize = params.getPictureSize();
        // サポートされている撮影サイズのリストを取得
        mSupportedPictureSizes = params.getSupportedPictureSizes();
        // 選択ダイアログ表示用の文字列の配列を作る
        mSupportedPictureSizeNames = new String[mSupportedPictureSizes.size()];
        for (int i = 0; i < mSupportedPictureSizeNames.length; i++) {
            // 1つ取り出して
            Size size = mSupportedPictureSizes.get(i);
            // サイズを文字列表記にする
            mSupportedPictureSizeNames[i] =
                    // 縦向きなので表記は逆にする
                    String.format("%d x %d", size.height, size.width);
            // 現在の撮影サイズと比較して
            if (currentPictureSize.equals(size)) {
                // 選択ダイアログの初期値としてインデックスを覚えておく
                mCurrentPictureSizeIndex = i;
            }
        }
        // 撮影された写真をフロントカメラ+縦持ちの場合は270度回転する
        params.setRotation(270);
        // パラメータをセット
        mCamera.setParameters(params);
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
        // 撮影！
        mCamera.takePicture(null, null, mPictureCallback);
    }

    public void onPictureSizeClick(View view) {
        // 選択ダイアログを表示する
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(mSupportedPictureSizeNames,
                        mCurrentPictureSizeIndex,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int witch) {
                                // 選択されたインデックスを覚えておく
                                mCurrentPictureSizeIndex = witch;
                                // カメラパラメータを取得して
                                Parameters params = mCamera.getParameters();
                                // 撮影サイズを取得して
                                Size size = mSupportedPictureSizes.get(witch);
                                // カメラパラメータにセット
                                params.setPictureSize(size.width, size.height);
                                // 撮影サイズをセットしたカメラパラメータをカメラにセット
                                mCamera.setParameters(params);
                                // 勝手に閉じないので閉じる
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private Bitmap createMirrorBtimap(byte[] data) {
        // 撮影サイズが大きすぎる時はレシピ065の方法で
        // inSampleSizeを使って縮小してください。
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        // 左右反転マトリックスを作る
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        // マトリックスで左右反転させた新しいBtimapを作って返す
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
    }
}

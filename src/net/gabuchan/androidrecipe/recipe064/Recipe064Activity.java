
package net.gabuchan.androidrecipe.recipe064;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

public class Recipe064Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_064);
    }

    public void onCompositeClick(View view) {
        // ベースにする画像をリソースから読み込む
        Bitmap baseBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.recipe_042_3);
        // 読み込んだBitmapはimmutableなのでコピーする
        baseBitmap = baseBitmap.copy(Bitmap.Config.ARGB_8888, true);

        // 合成したい画像をリソースから読み込む
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.gabu);
        // ベースにするBitmapを指定してCanvasを生成
        Canvas canvas = new Canvas(baseBitmap);
        // 描画する位置を計算して
        float left = baseBitmap.getWidth() / 2f;
        float top = baseBitmap.getHeight() / 2f;
        // canvasに合成したい画像を描画
        canvas.drawBitmap(bitmap, left, top, null);

        // 保存！
        save(baseBitmap);
    }

    private void save(Bitmap bitmap) {
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
            boolean isSuccess = bitmap.compress(CompressFormat.JPEG, 100, stream);
            if (isSuccess) {
                showToast("SDカードに保存しました。" + file.getAbsolutePath());
            } else {
                showToast("ファイルが保存できませんでした。");
            }
            stream.close();

            // スキャンさせる（ギャラリーに反映が目的）
            MediaScannerConnection.scanFile(this, new String[] {
                    file.toString()
            }, null, null);
        } catch (IOException e) {
            e.printStackTrace();
            showToast("ファイルが保存できませんでした。");
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

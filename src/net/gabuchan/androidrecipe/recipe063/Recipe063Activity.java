
package net.gabuchan.androidrecipe.recipe063;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Recipe063Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_063);
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

        // 合成結果を表示するImageViewを作る
        ImageView imageView = new ImageView(this);
        // 合成したBitmap（画像）をセットする
        imageView.setImageBitmap(baseBitmap);

        new AlertDialog.Builder(this)
                .setView(imageView)
                .create()
                .show();
    }
}

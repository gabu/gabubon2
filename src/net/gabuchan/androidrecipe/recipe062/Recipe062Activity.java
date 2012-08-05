
package net.gabuchan.androidrecipe.recipe062;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class Recipe062Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_062);

        // 元画像をリソースから読み込む
        Bitmap src = BitmapFactory.decodeResource(getResources(),
                R.drawable.gabu);

        // Matrixを作る
        Matrix matrix = new Matrix();
        // 90度回転
        matrix.postRotate(90);

        // 回転！
        Bitmap dst = Bitmap.createBitmap(
                src, // 元画像
                0, // 開始X座標
                0, // 開始Y座標
                src.getWidth(), // 元画像のwidth
                src.getHeight(), // 元画像のheight
                matrix, // Matrix
                true); // アンチエイリアス

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageBitmap(dst);
    }
}

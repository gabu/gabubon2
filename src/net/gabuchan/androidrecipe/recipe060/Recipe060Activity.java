
package net.gabuchan.androidrecipe.recipe060;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class Recipe060Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_060);

        // 元画像をリソースから読み込む
        Bitmap src = BitmapFactory.decodeResource(getResources(),
                R.drawable.gabu);
        int srcWidth = src.getWidth(); // 元画像のwidth
        int srcHeight = src.getHeight(); // 元画像のheight

        // リサイズしたい新しい画像のサイズ
        Matrix matrix = new Matrix();

        float dstWidth = 50; // リサイズ後のwidth
        float dstHeight = 50; // リサイズ後のheight

        // Scaleなので、新しいサイズ / 元のサイズ の値を設定する
        matrix.postScale(dstWidth / srcWidth, dstHeight / srcHeight);

        // リサイズ！
        Bitmap dst = Bitmap.createBitmap(
                src, // 元画像
                0, // 開始X座標
                0, // 開始Y座標
                srcWidth, // 元画像のwidth
                srcHeight, // 元画像のheight
                matrix, // Matrix
                true); // アンチエイリアス

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageBitmap(dst);
    }
}

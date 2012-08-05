
package net.gabuchan.androidrecipe.recipe061;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class Recipe061Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_061);

        // 元画像をリソースから読み込む
        Bitmap src = BitmapFactory.decodeResource(getResources(),
                R.drawable.gabu);
        // 切り取り！
        Bitmap dst = Bitmap.createBitmap(
                src, // 元画像
                20, // 開始X座標
                20, // 開始Y座標
                50, // 切り取るwidth
                50); // 切り取るheight

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageBitmap(dst);
    }
}

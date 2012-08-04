
package net.gabuchan.androidrecipe.recipe048;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

public class Recipe048Activity extends Activity {
    Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_048);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // FrameLayoutからImageViewを削除
                View view = findViewById(R.id.image_view);
                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
                frameLayout.removeView(view);
            }
        }, 2 * 1000); // 2000ミリ秒後（2秒後）に実行
    }
}

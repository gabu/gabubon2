
package net.gabuchan.androidrecipe.recipe047;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Recipe047Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // タイトルバーを非表示にする（setContentViewの前に呼ぶこと）
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_recipe_047);
    }
}

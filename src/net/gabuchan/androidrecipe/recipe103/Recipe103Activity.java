
package net.gabuchan.androidrecipe.recipe103;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class Recipe103Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_103);

        // 自動的にスリープしないようにする
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

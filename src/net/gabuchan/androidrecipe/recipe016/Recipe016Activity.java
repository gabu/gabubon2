
package net.gabuchan.androidrecipe.recipe016;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Recipe016Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_016);
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showToast();
            }
        });
    }

    public void onButtonClick(View view) {
        showToast();
    }
    
    private void showToast() {
        Toast.makeText(this, R.string.recipe_016_title, Toast.LENGTH_SHORT).show();
    }
}

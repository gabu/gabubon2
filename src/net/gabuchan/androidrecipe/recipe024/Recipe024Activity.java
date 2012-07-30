
package net.gabuchan.androidrecipe.recipe024;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Recipe024Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_024);
    }

    public void onToggleClick(View view) {
        ToggleButton toggle = (ToggleButton) view;
        if (toggle.isChecked()) {
            showToast("ON!");
        } else {
            showToast("OFF!");
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

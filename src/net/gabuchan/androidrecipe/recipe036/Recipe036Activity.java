
package net.gabuchan.androidrecipe.recipe036;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Recipe036Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_036);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_036, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                // Settingsが選択された時の処理
                return true;
            case R.id.menu_camera:
                // Cameraが選択された時の処理
                return true;
            case R.id.menu_delete:
                // Deleteが選択された時の処理
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

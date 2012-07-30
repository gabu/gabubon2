
package net.gabuchan.androidrecipe.recipe020;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Recipe020Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_020);
    }
    
    public void onButtonClick(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_text);
        Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
    }
}

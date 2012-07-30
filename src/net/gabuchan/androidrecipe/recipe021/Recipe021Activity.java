
package net.gabuchan.androidrecipe.recipe021;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Recipe021Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_021);
    }

    public void onRadioButtonClick(View view) {
        switch (view.getId()) {
            case R.id.radio_yes:
                showToast("Yes");
                break;
            case R.id.radio_no:
                showToast("No");
                break;
        }
    }

    public void onButtonClick(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int id = radioGroup.getCheckedRadioButtonId();

        // 説明のためあえてメソッド化してない
        switch (id) {
            case R.id.radio_yes:
                showToast("Yes");
                break;
            case R.id.radio_no:
                showToast("No");
                break;
            default:
                showToast("Not checked");
                break;
        }
    }

    public void onClearButtonClick(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.clearCheck();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}

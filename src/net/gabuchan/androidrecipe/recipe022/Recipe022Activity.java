
package net.gabuchan.androidrecipe.recipe022;

import net.gabuchan.androidrecipe.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Recipe022Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_022);
    }

    public void onCheckBoxClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean checked = checkBox.isChecked();
        String text = checkBox.getText().toString();
        if (checked) {
            text += "がチェックされた";
        } else {
            text += "のチェックが外された";
        }
        showToast(text);

        switch (checkBox.getId()) {
            case R.id.check_box_a:
                if (checked) {
                    // チェックされた時の処理
                } else {
                    // チェックを外された時の処理
                }
            case R.id.check_box_b:
                if (checked) {
                    // チェックされた時の処理
                } else {
                    // チェックを外された時の処理
                }
            case R.id.check_box_c:
                if (checked) {
                    // チェックされた時の処理
                } else {
                    // チェックを外された時の処理
                }
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
